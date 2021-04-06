package com.bayee.political.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bayee.political.domain.Calculation;
import com.bayee.political.domain.CalculationFactor;
import com.bayee.political.domain.CalculationAlarm;
import com.bayee.political.domain.CalculationAlarmTrend;
import com.bayee.political.domain.CalculationDetail;
import com.bayee.political.domain.CalculationPoliceData;
import com.bayee.political.domain.CalculationPolicePost;
import com.bayee.political.domain.CalculationPoliceStation;
import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.CalculationColumn;
import com.bayee.political.domain.CalculationProject;
import com.bayee.political.domain.CalculationResourceAllocate;
import com.bayee.political.domain.PoliceStation;
import com.bayee.political.domain.PoliceStationPost;
import com.bayee.political.service.CalculationService;
import com.bayee.political.service.DepartmentService;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.StatusCode;
import com.fasterxml.jackson.databind.Module.SetupContext;

import cn.hutool.core.date.DateUtil;

/**
 * 警力测算
 * 
 * @author shentuqiwei
 * @version 2020年5月20日 下午2:45:46
 */
@Controller
public class CalculationController extends BaseController {

	@Autowired
	private CalculationService calculationService;

	@Autowired
	private DepartmentService departmentService;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// 警力测算饼图统计
	@RequestMapping(value = "/calculation/pie/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> pieStatisticsList(
			@RequestParam(value = "policeType", required = false) Integer policeType) {
		if (policeType == null) {
			policeType = 1;
		}
		Integer postType = policeType;
		DataListReturn dlr = new DataListReturn();
		List<CalculationChart> policeStatisticsList = calculationService.policeStatisticsList(policeType, postType);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(policeStatisticsList);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警力测算柱状图统计
	@RequestMapping(value = "/calculation/column/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> columnStatisticsList(
			@RequestParam(value = "policeType", required = false) Integer policeType) {
		if (policeType == null) {
			policeType = 1;
		}
		DataListReturn dlr = new DataListReturn();
		// 实际警力查询(api)
		List<CalculationChart> actualStatisticsList = calculationService.actualStatisticsApiList(policeType);
		// 所需警力查询(api)
		List<CalculationChart> getStatisticsList = calculationService.getStatisticsApiList(policeType);
		// 最后更新时间查询(api)
		Calculation lastTimeItem = calculationService.lastTimeApiItem(policeType);
		CalculationColumn cNum = new CalculationColumn();
		cNum.setActualList(actualStatisticsList);
		cNum.setGetList(getStatisticsList);
		if (lastTimeItem.getUpdateDate() != null) {
			String str = sdf.format(lastTimeItem.getUpdateDate());
			cNum.setUpdateDate(str);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(cNum);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 各派出所警力分配详情
	@RequestMapping(value = "/calculation/distribute/item", method = RequestMethod.GET)
	public ResponseEntity<?> calculationDistributeItem(
			@RequestParam(value = "policeStationId", required = false) Integer policeStationId,
			@RequestParam(value = "policeType", required = false) Integer policeType) {
		DataListReturn dlr = new DataListReturn();
		if (policeType == null) {
			policeType = 1;
		}
		// 派出所查询(api)
		List<PoliceStation> policeStationList = departmentService.policeStationApiList(policeStationId);
		for (PoliceStation x : policeStationList) {
			// 派出所民警岗位查询(api)
			List<PoliceStationPost> policeStationPostList = departmentService.policeStationPostApiList(policeType);
			x.setPoliceStationPostList(policeStationPostList);

			Integer actualPoliceNumTotal = 0;// 实际警力数总计
			Integer getPoliceNumTotal = 0;// 所需警力数总计
			Integer differPoliceNumTotal = 0;// 缺少警力数总计

			Integer policeTypeTotal = 0;// 警员类型
			Integer policeStationIdTotal = 0;

			for (PoliceStationPost y : policeStationPostList) {
				// 测算结果查询
				List<Calculation> calculationOverList = calculationService.calculationOverList(x.getId(), y.getId());
				y.setCalculationOverList(calculationOverList);

				policeTypeTotal = calculationOverList.get(0).getPoliceType();
				policeStationIdTotal = calculationOverList.get(0).getPoliceStationId();
				// actualPoliceNumTotal += calculationOverList.get(0).getActualPoliceNum();
				getPoliceNumTotal += calculationOverList.get(0).getGetPoliceNum();
				differPoliceNumTotal += calculationOverList.get(0).getDifferPoliceNum();
			}
			// 添加总人数
			Calculation calculation = new Calculation();

			// 查询实际警力数总数
			CalculationPoliceStation actualPoliceSum = calculationService.getAllByPoliceIdType(x.getId(), policeType);
			actualPoliceNumTotal = actualPoliceSum.getPoliceNum();
			calculation.setActualPoliceNum(actualPoliceNumTotal);

			calculation.setGetPoliceNum(getPoliceNumTotal);
			calculation.setDifferPoliceNum(actualPoliceNumTotal - getPoliceNumTotal);
			calculation.setPoliceType(policeTypeTotal);
			calculation.setPoliceStationId(policeStationIdTotal);
			if (actualPoliceNumTotal > getPoliceNumTotal) {
				calculation.setIsLack(2);
			} else if (actualPoliceNumTotal < getPoliceNumTotal) {
				calculation.setIsLack(0);
			} else {
				calculation.setIsLack(1);
			}

			PoliceStationPost policeStationPost = new PoliceStationPost();

			policeStationPost.setPostName("总计");
			policeStationPost.setPostType(policeTypeTotal);

			List<Calculation> list = new ArrayList<Calculation>();
			list.add(calculation);
			policeStationPost.setCalculationOverList(list);
			policeStationPostList.add(policeStationPost);

		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(policeStationList);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警力测算各类案件数
	@RequestMapping(value = "/calculation/detail/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> calculationDetailStatistics(
			@RequestParam(value = "policeType", required = false) Integer policeType) {
		DataListReturn dlr = new DataListReturn();
		if (policeType == null) {
			policeType = 1;
		}
		// 案件类型
		List<CalculationProject> projectList = calculationService.projectList();

		for (CalculationProject x : projectList) {
			// 各派出所案件数量查询
			List<CalculationDetail> calculationDetailList = calculationService.calculationDetailApiList(x.getId(),
					policeType);
			x.setCalculationDetailList(calculationDetailList);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(projectList);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警力测算新增
//	@RequestMapping(value = { "/calculation/detail/submit" }, method = RequestMethod.POST)
//	public ResponseEntity<?> calculationDetailCreat(
//			@RequestParam(value = "calculationJson", required = false) String calculationJson) throws ParseException {
//		DataListReturn dlr = new DataListReturn();
//		CalculationDetail calculationDetail = new CalculationDetail();
////			String ss = "[{'id':1,'caseNum':26},{'id':2,'caseNum':20},{'id':3,'caseNum':22}]";
//		JSONArray json = (JSONArray) JSONArray.parse(calculationJson);
//		int count = 0;
//		for (Object obj : json) {
//			JSONObject jo = (JSONObject) obj;
//			calculationDetail.setId(jo.getInteger("id"));
//			calculationDetail.setCaseNum(jo.getInteger("caseNum"));
//			calculationDetail.setUpdateDate(new Date());
//			count = calculationService.calculationDetailUpdate(calculationDetail);
//		}
//		// 查询打击岗警力数据计算列表(api)
//		List<Calculation> strikeList = calculationService.strikeList(1, 1);
//		statisticsUpdate(strikeList, 1);
//		// 查询基础岗警力数据计算列表(api)
//		List<Calculation> baseList = calculationService.baseList(1, 2);
//		statisticsUpdate(baseList, 1);
//		// 查询综合岗警力数据计算列表(api)
//		List<Calculation> overAllList = calculationService.overAllList(1, 3);
//		statisticsUpdate(overAllList, 1);
//		// 查询综合勤务室辅警数据计算列表(api)
//		List<Calculation> serviceRoomList = calculationService.serviceRoomList(2, 6);
//		statisticsUpdate(serviceRoomList, 2);
//		// 查询法制室辅警数据计算列表(api)
//		List<Calculation> legalRoomList = calculationService.legalRoomList(2, 9);
//		statisticsUpdate(legalRoomList, 2);
//		// 查询户籍室辅警数据计算列表(api)
//		List<Calculation> registerRoomList = calculationService.registerRoomList(2, 10);
//		statisticsUpdate(registerRoomList, 2);
//		// 查询其他辅警警力辅警数据计算列表(api)
//		List<Calculation> otherList = calculationService.otherList(2, 11);
//		statisticsUpdate(otherList, 2);
//		if (count > 0) {
//			dlr.setMessage("success");
//			dlr.setCode(StatusCode.getSuccesscode());
//			dlr.setStatus(true);
//			dlr.setResult(count);
//		} else {
//			dlr.setMessage("fail");
//			dlr.setStatus(false);
//			dlr.setCode(StatusCode.getFailcode());
//		}
//		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
//	}

	// 最新警力预警
	@RequestMapping(value = "/calculation/alarm/newest/list", method = RequestMethod.GET)
	public ResponseEntity<?> calculationAlarmNewest(
			@RequestParam(value = "policeType", required = false) Integer policeType) {
		DataListReturn dlr = new DataListReturn();
		if (policeType == null || policeType == 0) {
			policeType = null;
		}
		// 最新警力预警查询
		List<Calculation> list = calculationService.calculationAlarmNewest(policeType);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警力测算指标数据情况
	@RequestMapping(value = "/calculation/measurement/police/data/item", method = RequestMethod.GET)
	public ResponseEntity<?> calculationMeasurementPoliceDataItem(
			@RequestParam(value = "policeType", required = false) Integer policeType,
			@RequestParam(value = "policeStationId", required = false) Integer policeStationId) {
		DataListReturn dlr = new DataListReturn();
		CalculationPoliceData item = new CalculationPoliceData();
		// 警力测算指标数据情况(刑事治安)
		CalculationFactor dItem1 = calculationService.administrativeDetentionItem(null, 1, policeStationId);
		if (dItem1 != null) {
			item.setCriminalSecurityNum(dItem1.getNum());
			item.setCriminalSecurityRate(dItem1.getIncrement());
		}
		// 警力测算指标数据情况(刑拘)
		CalculationFactor dItem2 = calculationService.administrativeDetentionItem(null, 2, policeStationId);
		if (dItem2 != null) {
			item.setCriminalDetentionNum(dItem2.getNum());
			item.setCriminalDetentionRate(dItem2.getIncrement());
		}
		// 警力测算指标数据情况(行政拘留)
		CalculationFactor dItem3 = calculationService.administrativeDetentionItem(null, 3, policeStationId);
		if (dItem3 != null) {
			item.setAdministrativeDetentionNum(dItem3.getNum());
			item.setAdministrativeDetentionRate(dItem3.getIncrement());
		}
		// 警力测算指标数据情况(有效报警)
		CalculationFactor dItem4 = calculationService.administrativeDetentionItem(null, 4, policeStationId);
		if (dItem4 != null) {
			item.setEffectiveAlarmNum(dItem4.getNum());
			item.setEffectiveAlarmRate(dItem4.getIncrement());
		}
		// 警力测算指标数据情况(所面积)
		CalculationFactor dItem5 = calculationService.administrativeDetentionItem(null, 5, policeStationId);
		if (dItem5 != null) {
			item.setJurisdictionAreaNum(dItem5.getNum());
			item.setJurisdictionAreaRate(dItem5.getIncrement());
		}
		// 警力测算指标数据情况(行业场所)
		CalculationFactor dItem6 = calculationService.administrativeDetentionItem(null, 6, policeStationId);
		if (dItem6 != null) {
			item.setIndustryPlaceNum(dItem6.getNum());
			item.setIndustryPlaceRate(dItem6.getIncrement());
		}
		// 警力测算指标数据情况(常住人口)
		CalculationFactor dItem7 = calculationService.administrativeDetentionItem(null, 7, policeStationId);
		if (dItem7 != null) {
			item.setResidentPopulationNum(dItem7.getNum());
			item.setResidentPopulationRate(dItem7.getIncrement());
		}
		// 警力测算指标数据情况(外来人口)
		CalculationFactor dItem8 = calculationService.administrativeDetentionItem(null, 8, policeStationId);
		if (dItem8 != null) {
			item.setFloatingPopulationNum(dItem8.getNum());
			item.setFloatingPopulationRate(dItem8.getIncrement());
		}
		// 警力测算指标数据情况(户籍业务量)
		CalculationFactor dItem9 = calculationService.administrativeDetentionItem(null, 9, policeStationId);
		if (dItem9 != null) {
			item.setRegistryOfficeNum(dItem9.getNum());
			item.setRegistryOfficeRate(dItem9.getIncrement());
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 资源调配建议
	@RequestMapping(value = "/calculation/resource/allocate/advise/list", method = RequestMethod.GET)
	public ResponseEntity<?> calculationResourceAllocateAdviseList(
			@RequestParam(value = "policeType", required = false) Integer policeType) {
		DataListReturn dlr = new DataListReturn();
		if (policeType == null || policeType == 0) {
			policeType = null;
		}
		// 资源调配建议
		List<CalculationResourceAllocate> list = calculationService.calculationResourceAllocateAdviseList(policeType);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警力预警趋势
	@RequestMapping(value = "/calculation/alarm/trend/list", method = RequestMethod.GET)
	public ResponseEntity<?> calculationAlarmTrendList(
			@RequestParam(value = "policeType", required = false) Integer policeType,
			@RequestParam(value = "policeStationId", required = false) Integer policeStationId,
			@RequestParam(value = "isLack", required = false) Integer isLack) {
		DataListReturn dlr = new DataListReturn();
		if (policeType == null || policeType == 0) {
			policeType = null;
		}
		List<CalculationAlarmTrend> list = new ArrayList<CalculationAlarmTrend>();
		// 查询预警派出所
		List<PoliceStation> stationList = calculationService.calculationStationList(policeType);
		if (stationList.size() > 0) {
			for (int i = 0; i < stationList.size(); i++) {
				CalculationAlarmTrend item = new CalculationAlarmTrend();
				item.setPoliceStationId(stationList.get(i).getId());
				item.setPoliceStationName(stationList.get(i).getPoliceStationName());
				// 警力预警趋势查询(缺少)
				List<CalculationChart> list1 = calculationService.calculationAlarmTrendList(policeType,
						stationList.get(i).getId(), 0);
				item.setLackList(list1);
				// 警力预警趋势查询(超过)
				List<CalculationChart> list2 = calculationService.calculationAlarmTrendList(policeType,
						stationList.get(i).getId(), 2);
				item.setExceedList(list2);
				list.add(item);
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警力预警趋势详情
	@RequestMapping(value = "/calculation/alarm/trend/item", method = RequestMethod.GET)
	public ResponseEntity<?> calculationAlarmTrendItem(
			@RequestParam(value = "policeType", required = false) Integer policeType,
			@RequestParam(value = "policeStationId", required = false) Integer policeStationId) {
		DataListReturn dlr = new DataListReturn();
		if (policeType == null || policeType == 0) {
			policeType = null;
		}
		CalculationAlarmTrend item = new CalculationAlarmTrend();
		// 警力预警趋势查询(缺少)
		List<CalculationChart> list1 = calculationService.calculationAlarmTrendList(policeType, policeStationId, 0);
		item.setLackList(list1);
		// 警力预警趋势查询(超过)
		List<CalculationChart> list2 = calculationService.calculationAlarmTrendList(policeType, policeStationId, 2);
		item.setExceedList(list2);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// **************************** 调皮的分割线***********************后台接口

	// 警力测算实际民警/辅警页面列表
	@RequestMapping(value = { "/calculation/data/list" })
	public @ResponseBody byte[] calculationDataList(@Param("policeType") Integer policeType)
			throws UnsupportedEncodingException {
		if (policeType == null) {
			policeType = 1;
		}
		ArrayList<Object> list = new ArrayList<Object>();
//		// 派出所民警岗位查询(api)
//		List<PoliceStationPost> policeStationPostList = departmentService.policeStationPostApiList(policeType);
//		for (PoliceStationPost x : policeStationPostList) {
//			// 各派出所岗位人员数据查询(后台)
//			List<Calculation> calculationPageList = calculationService.calculationPageList(policeType, x.getId());
//			x.setCalculationOverList(calculationPageList);
//		}
//		list.add(policeStationPostList);
		// 派出所查询(api)
		List<PoliceStation> policeStationList = departmentService.policeStationApiListSum(policeType);
		for (PoliceStation x : policeStationList) {
			// 派出所民警岗位查询(api)
			List<PoliceStationPost> policeStationPostList = departmentService.policeStationPostApiListSum(policeType);
			x.setPoliceStationPostList(policeStationPostList);
			for (PoliceStationPost y : policeStationPostList) {
				// 测算结果查询
				List<Calculation> calculationOverList = calculationService.calculationOverList(x.getId(), y.getId());
				y.setCalculationOverList(calculationOverList);
			}
		}

		// 设置最后一项的calculationOverList
		PoliceStation policeStation = policeStationList.get(policeStationList.size() - 1);

		// 总计数量
		List<Calculation> calculationOverListSum = calculationService.calculationOverListSum(policeType);

		// 修改最后一行
		for (int i = 0; i < policeStation.getPoliceStationPostList().size(); i++) {

			List<Calculation> calculationList = new ArrayList<Calculation>();

			calculationList.add(calculationOverListSum.get(i));

			policeStation.getPoliceStationPostList().get(i).setCalculationOverList(calculationList);

		}

		// 修改除最后一个每个的总计
		// 总计数量
		List<Calculation> calculationOverListSumY = calculationService.calculationOverListSumY(policeType);

		for (int i = 0; i < policeStationList.size() - 1; i++) {

			List<PoliceStationPost> policeStationPostList = policeStationList.get(i).getPoliceStationPostList();

			PoliceStationPost policeStationPost = policeStationPostList.get(policeStationPostList.size() - 1);

			List<Calculation> calculationList = new ArrayList<Calculation>();

			calculationList.add(calculationOverListSumY.get(i));

			policeStationPost.setCalculationOverList(calculationList);

		}

		list.add(policeStationList);
		return gson.toJson(list).getBytes("utf-8");
	}

	// 民警（辅警）实际警力新增or修改
	@RequestMapping(value = { "/calculation/police/submit" }, method = RequestMethod.POST)
	public @ResponseBody byte[] calculationPoliceSubmit(@RequestParam(value = "id", required = false) Integer id[],
			@RequestParam(value = "policeType", required = false) Integer policeType[],
			@RequestParam(value = "policeStationId", required = false) Integer policeStationId[],
			@RequestParam(value = "stationPostId", required = false) Integer stationPostId[],
			@RequestParam(value = "actualPoliceNum", required = false) Integer actualPoliceNum[])
			throws IOException, ParseException {
		boolean isUpdate = false;

		for (int i = 0; i < id.length; i++) {

			Calculation item = calculationService.calculationItem(id[i], policeType[i], policeStationId[i],
					stationPostId[i]);
			if (item == null) {
				item = new Calculation();
				if (stationPostId[i] == 4 || stationPostId[i] == 5 || stationPostId[i] == 7 || stationPostId[i] == 8) {
					item.setGetPoliceNum(actualPoliceNum[i]);
					item.setDifferPoliceNum(0);
					item.setIsLack(1);
				} else {
					item.setDifferPoliceNum(0);
					item.setGetPoliceNum(0);
					item.setIsLack(0);
				}
				item.setCreationDate(new Date());
			} else {
				isUpdate = true;
				if (stationPostId[i] == 4 || stationPostId[i] == 5 || stationPostId[i] == 7 || stationPostId[i] == 8) {
					item.setGetPoliceNum(actualPoliceNum[i]);
					item.setDifferPoliceNum(0);
					item.setIsLack(1);
				} else {
					item.setDifferPoliceNum(actualPoliceNum[i] - item.getGetPoliceNum());
				}
				item.setUpdateDate(new Date());
			}
			item.setPoliceType(policeType[i]);
			item.setPoliceStationId(policeStationId[i]);
			item.setStationPostId(stationPostId[i]);
			item.setActualPoliceNum(actualPoliceNum[i]);

			if (!isUpdate) { // 新增
				calculationService.calculationCreat(item);
			} else {
				calculationService.calculationStatisticsUpdate(item);
			}

		}

		return gson.toJson("ok").getBytes("utf-8");
	}

	@RequestMapping(value = { "/calculation/police/update" }, method = RequestMethod.POST)
	public @ResponseBody byte[] calculationPoliceUpdate(@RequestParam(value = "id", required = false) Integer id[],
			@RequestParam(value = "sum", required = false) Integer sum,
			@RequestParam(value = "actual", required = false) Integer actual[],
			@RequestParam(value = "stationId", required = false) Integer stationId,
			@RequestParam(value = "type", required = false) Integer type)
			throws UnsupportedEncodingException, ParseException {
		int actualNumInt = 0;
		int getNumInt = 0;
		for (int i = 0; i < actual.length; i++) {
			// 警力详情查询
			Calculation cItem = calculationService.calculationItem(id[i], null, null, null);
			int diffNum = actual[i] - cItem.getGetPoliceNum();
			cItem.setId(id[i]);
			cItem.setActualPoliceNum(actual[i]);
			cItem.setDifferPoliceNum(actual[i] - cItem.getGetPoliceNum());
			if (diffNum > 0) {
				cItem.setIsLack(2);
			} else if (diffNum < 0) {
				cItem.setIsLack(0);
			} else if (diffNum == 0) {
				cItem.setIsLack(1);
			}
			cItem.setUpdateDate(new Date());
			// 根据派出所id和岗位id修改(api)
			calculationService.calculationIdUpdate(cItem);
			actualNumInt = actualNumInt + actual[i];
			getNumInt = getNumInt + cItem.getGetPoliceNum();
			// calculationService.calculationPolicePostUpdateByid(id[i], sum);
			// calculationService.CalculationPoliceStationUpdateByid(id[i], sum);

			// 根据预警类型及派出所获取警力数总量
//			Integer total = calculationService.getSumByCondition(type, stationId);
//			int num = Math.abs(actual[i] - total);

			// 判断是否改变
//			CalculationAlarm calculationAlarmObj = calculationService.getCalculationAlarmBaseNew(type, stationId);

			// 查询当前岗位数量
			CalculationPolicePost postItem = calculationService.calculationPolicePostNumItem(type,
					cItem.getStationPostId());
			// 根据派出所id和岗位id修改岗位总数(api)
			CalculationPolicePost psPost = new CalculationPolicePost();
			psPost.setPoliceNum(postItem.getPoliceNum());
			psPost.setPoliceType(type);
			psPost.setPolicePostId(cItem.getStationPostId());
			psPost.setUpdateDate(new Date());
			calculationService.calculationPolicePostUpdate(psPost);
		}
		int diffNumInt = actualNumInt - getNumInt;
		if (diffNumInt != 1) {
			// 新增预警表
			CalculationAlarm calculationAlarm = new CalculationAlarm();
			calculationAlarm.setPoliceType(type);
			calculationAlarm.setPoliceStationId(stationId);
			calculationAlarm.setActualPoliceNum(actualNumInt);
			calculationAlarm.setGetPoliceNum(getNumInt);
			calculationAlarm.setDifferPoliceNum(diffNumInt);
			calculationAlarm.setIsLack(diffNumInt < 0 ? 0 : diffNumInt == 0 ? 1 : 2);
			calculationAlarm.setCreationDate(new Date());
			calculationService.calculationAlarmCreat(calculationAlarm);
		}
		CalculationPoliceStation pStation = new CalculationPoliceStation();
		pStation.setPoliceType(type);
		pStation.setPoliceNum(actualNumInt);
		pStation.setPoliceStationId(stationId);
		pStation.setUpdateDate(new Date());
		calculationService.pStationUpdate(pStation);
		// 查询打击岗警力数据计算列表(api)
		List<Calculation> strikeList = calculationService.strikeList(1, 1, stationId);
		statisticsUpdate(strikeList, 1);
		// 查询基础岗警力数据计算列表(api)
		List<Calculation> baseList = calculationService.baseList(1, 2, stationId);
		statisticsUpdate(baseList, 1);
		// 查询综合岗警力数据计算列表(api)
		List<Calculation> overAllList = calculationService.overAllList(1, 3, stationId);
		statisticsUpdate(overAllList, 1);
		// 查询综合勤务室辅警数据计算列表(api)
		List<Calculation> serviceRoomList = calculationService.serviceRoomList(2, 6, stationId);
		statisticsUpdate(serviceRoomList, 2);
		// 查询法制室辅警数据计算列表(api)
		List<Calculation> legalRoomList = calculationService.legalRoomList(2, 9, stationId);
		statisticsUpdate(legalRoomList, 2);
		// 查询户籍室辅警数据计算列表(api)
		List<Calculation> registerRoomList = calculationService.registerRoomList(2, 10, stationId);
		statisticsUpdate(registerRoomList, 2);
		// 查询其他辅警警力辅警数据计算列表(api)
		List<Calculation> otherList = calculationService.otherList(2, 11, stationId);
		statisticsUpdate(otherList, 2);
		DataListReturn dlr = new DataListReturn();
		dlr.setCode(StatusCode.getSuccesscode());
		dlr.setMessage("message");
		dlr.setStatus(true);
		return gson.toJson(dlr).getBytes("utf-8");

	}

	// 警力测算因子际民警/辅警页面列表
	@RequestMapping(value = { "/calculation/factor/list" })
	public @ResponseBody byte[] calculationFactorList(@Param("policeType") Integer policeType)
			throws UnsupportedEncodingException {
		if (policeType == null) {
			policeType = 1;
		}
		ArrayList<Object> list = new ArrayList<Object>();
		if (policeType == 1) {// 民警
			// 派出所查询(api)
			List<PoliceStation> policeStationList = departmentService.policeStationApiList(null);
			for (PoliceStation x : policeStationList) {
				// 民警案件类型查询（后台）
				List<CalculationProject> policeProjectList = calculationService.policeProjectList(null);
				x.setPoliceProjectList(policeProjectList);
				for (CalculationProject y : policeProjectList) {
					// 各派出所案件数据查询(后台)
					List<CalculationDetail> calDetailsList = calculationService.calDetailsList(y.getId(), x.getId());
					y.setCalculationDetailList(calDetailsList);
				}
			}
			list.add(policeStationList);
		} else {// 辅警
			// 派出所查询(api)
			List<PoliceStation> policeStationList = departmentService.policeStationApiList(null);
			for (PoliceStation x : policeStationList) {
				// 民警案件类型查询（后台）
				List<CalculationProject> auxiliaryPoliceProjectList = calculationService
						.auxiliaryPoliceProjectList(null);
				x.setPoliceProjectList(auxiliaryPoliceProjectList);
				for (CalculationProject y : auxiliaryPoliceProjectList) {
					// 各派出所案件数据查询(后台)
					List<CalculationDetail> calDetailsList = calculationService.calDetailsList(y.getId(), x.getId());
					y.setCalculationDetailList(calDetailsList);
				}
			}
			list.add(policeStationList);
		}
		return gson.toJson(list).getBytes("utf-8");
	}

	// 案件新增or修改
	@RequestMapping(value = { "/calculation/case/submit" }, method = RequestMethod.POST)
	public @ResponseBody byte[] calculationCaseSubmit(@RequestParam(value = "id", required = false) Integer id[],
			@RequestParam(value = "policeType", required = false) Integer policeType,
			@RequestParam(value = "policeStationId", required = false) Integer policeStationId,
			@RequestParam(value = "caseId", required = false) Integer caseId[],
			@RequestParam(value = "caseNum", required = false) Integer caseNum[]) throws IOException, ParseException {
		boolean isUpdate = false;
		for (int i = 0; i < id.length; i++) {
			CalculationDetail item = calculationService.calculationDetailItem(id[i], policeStationId, caseId[i]);
			int baseCount = 0;
			CalculationFactor dItem = new CalculationFactor();
			if (item == null) {
				item = new CalculationDetail();
				item.setCreationDate(new Date());
				dItem.setCaseId(caseId[i]);
				dItem.setPoliceStationId(policeStationId);
				dItem.setNum(caseNum[i]);
				dItem.setIncrement(0.0);
				dItem.setCreationDate(new Date());
				calculationService.administrativeDetentionCreat(dItem);
			} else {
				isUpdate = true;
				item.setUpdateDate(new Date());
				baseCount = item.getCaseNum();
			}
			item.setPoliceType(policeType);
			item.setPoliceStationId(policeStationId);
			item.setCaseId(caseId[i]);
			item.setCaseNum(caseNum[i]);
			if (!isUpdate) { // 新增
				calculationService.calculationDetailCreat(item);
			} else {
				int baseCaseNum = caseNum[i];
				if (baseCount != baseCaseNum) {
					double basicsNum = baseCount;
					double nowNum = baseCaseNum;
					dItem.setCaseId(caseId[i]);
					dItem.setPoliceStationId(policeStationId);
					dItem.setNum(caseNum[i]);
					if (nowNum != 0) {						
						dItem.setIncrement(Double.valueOf(String.format("%.2f", (nowNum - basicsNum) / nowNum * 100)));
					} else {
						dItem.setIncrement(Double.valueOf(String.format("%.2f", (nowNum - basicsNum) * 100)));
					}
					
					dItem.setCreationDate(new Date());
					calculationService.administrativeDetentionCreat(dItem);
				}
				calculationService.calculationDetailUpdate(item);
			}
		}
		// 查询打击岗警力数据计算列表(api)
		List<Calculation> strikeList = calculationService.strikeList(1, 1, policeStationId);
		statisticsUpdate(strikeList, 1);
		// 查询基础岗警力数据计算列表(api)
		List<Calculation> baseList = calculationService.baseList(1, 2, policeStationId);
		statisticsUpdate(baseList, 1);
		// 查询综合岗警力数据计算列表(api)
		List<Calculation> overAllList = calculationService.overAllList(1, 3, policeStationId);
		statisticsUpdate(overAllList, 1);
		// 查询综合勤务室辅警数据计算列表(api)
		List<Calculation> serviceRoomList = calculationService.serviceRoomList(2, 6, policeStationId);
		statisticsUpdate(serviceRoomList, 2);
		// 查询法制室辅警数据计算列表(api)
		List<Calculation> legalRoomList = calculationService.legalRoomList(2, 9, policeStationId);
		statisticsUpdate(legalRoomList, 2);
		// 查询户籍室辅警数据计算列表(api)
		List<Calculation> registerRoomList = calculationService.registerRoomList(2, 10, policeStationId);
		statisticsUpdate(registerRoomList, 2);
		// 查询其他辅警警力辅警数据计算列表(api)
		List<Calculation> otherList = calculationService.otherList(2, 11, policeStationId);
		statisticsUpdate(otherList, 2);
		CalculationAlarm alarmItem = calculationService.policeStationNewData(policeType, policeStationId);
		if (alarmItem.getIsLack() != 1) {
			// 新增预警表
			CalculationAlarm calculationAlarm = new CalculationAlarm();
			calculationAlarm.setPoliceType(policeType);
			calculationAlarm.setPoliceStationId(policeStationId);
			calculationAlarm.setActualPoliceNum(alarmItem.getActualPoliceNum());
			calculationAlarm.setGetPoliceNum(alarmItem.getGetPoliceNum());
			calculationAlarm.setDifferPoliceNum(alarmItem.getDifferPoliceNum());
			calculationAlarm.setIsLack(alarmItem.getIsLack());
			calculationAlarm.setCreationDate(new Date());
			calculationService.calculationAlarmCreat(calculationAlarm);
		}
		return gson.toJson("ok").getBytes("utf-8");
	}

	// 案件新增or修改
//		@RequestMapping(value = { "/calculation/case/submit" }, method = RequestMethod.POST)
//		public @ResponseBody byte[] calculationCaseSubmit(@RequestParam(value = "id", required = false) Integer id[],
//				@RequestParam(value = "policeType", required = false) Integer policeType[],
//				@RequestParam(value = "policeStationId", required = false) Integer policeStationId[],
//				@RequestParam(value = "caseId", required = false) Integer caseId[],
//				@RequestParam(value = "caseNum", required = false) Integer caseNum[]) throws IOException, ParseException {
//			boolean isUpdate = false;
//			for (int i = 0; i < id.length; i++) {
//				CalculationDetail item = calculationService.calculationDetailItem(id[i], policeStationId[i], caseId[i]);
//				if (item == null) {
//					item = new CalculationDetail();
//					item.setCreationDate(new Date());
//				} else {
//					isUpdate = true;
//					item.setUpdateDate(new Date());
//				}
//				item.setPoliceType(policeType[i]);
//				item.setPoliceStationId(policeStationId[i]);
//				item.setCaseId(caseId[i]);
//				item.setCaseNum(caseNum[i]);
//				if (!isUpdate) { // 新增
//					calculationService.calculationDetailCreat(item);
//				} else {
//					calculationService.calculationDetailUpdate(item);
//				}
//			}
//			return gson.toJson("ok").getBytes("utf-8");
//		}

	// 测算修改方法(api)
	private void statisticsUpdate(List<Calculation> calculationList, Integer policeType) throws ParseException {
		for (int i = 0; i < calculationList.size(); i++) {
			Calculation calculation = new Calculation();
			calculation.setPoliceType(policeType);
			calculation.setPoliceStationId(calculationList.get(i).getPoliceStationId());
			calculation.setStationPostId(calculationList.get(i).getStationPostId());
			calculation.setGetPoliceNum(calculationList.get(i).getGetPoliceNum());
			calculation.setDifferPoliceNum(calculationList.get(i).getDifferPoliceNum());
			calculation.setIsLack(calculationList.get(i).getIsLack());
			calculation.setUpdateDate(new Date());
			// 根据派出所id和岗位id修改(api)
			calculationService.calculationStatisticsUpdate(calculation);
		}
	}

	/**
	 * 主页警力在线数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/calculation/index/get-data", method = RequestMethod.POST)
	public ResponseEntity<?> calculationIndex() {

		// 最新两条民警超出或缺少的派出所(该派出所的最新记录)
		List<CalculationAlarm> minPolice = calculationService.calculationRandTwo(1);
		// 最新两条辅警超出或缺少的派出所(该派出所的最新记录)
		List<CalculationAlarm> fuPolice = calculationService.calculationRandTwo(2);

		// 民警总数
		Integer minPoliceCount = calculationService.sumPoliceNum(1);
		// 辅警总数
		Integer fuPoliceCount = calculationService.sumPoliceNum(2);

		// 创建集合装
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("minPolice", minPolice);
		map.put("fuPolice", fuPolice);
		map.put("minPoliceCount", minPoliceCount);
		map.put("fuPoliceCount", fuPoliceCount);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(map);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 查询近30天的超出或紧缺人数
	 * 
	 * @param policeStationId 派出所id
	 * @param isLack          0紧缺2超出
	 * @param policeType      1民警2辅警
	 * @param day             近多少天
	 * @return
	 */
	@RequestMapping(value = "/calculation/nearday-differpolice-num", method = RequestMethod.POST)
	public ResponseEntity<?> getNearDayDifferPoliceNum(@Param("policeStationId") Integer policeStationId,
			@Param("policeType") Integer policeType) {

		// 近30天
		String dayArray[] = new String[30];
		// 相差数
		Integer lackArray[] = new Integer[dayArray.length];
		for (int i = 0; i < 30; i ++) {
			Calendar now = Calendar.getInstance();
			now.add(Calendar.DAY_OF_MONTH, -(30 - i));
       	 	dayArray[i] = new SimpleDateFormat("MM/dd").format(now.getTime());
       	 	
       	 	Integer lack = calculationService.nearDayDifferPoliceNum(policeStationId, policeType, 
       	 			new SimpleDateFormat("yyyy-MM-dd").format(now.getTime()));
       	 	
       	 	lackArray[i] = null == lack ? 0 : lack;
        }

		LinkedHashMap<String, Object[]> map = new LinkedHashMap<String, Object[]>();
		map.put("dayArray", dayArray);
		map.put("lackArray", lackArray);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(map);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 近30天紧缺或超出最大的派出所
	 * 
	 * @param policeType
	 * @return
	 */
	@RequestMapping(value = "/calculation/max-differpolicenum-thirty", method = RequestMethod.POST)
	public ResponseEntity<?> getMaxDifferPoliceNumThirty(@Param("policeType") Integer policeType) {

		CalculationAlarm minMaxDifferPoliceNum = calculationService.maxDifferPoliceNumThirty(0, policeType, 30);
		CalculationAlarm fuMaxDifferPoliceNum = calculationService.maxDifferPoliceNumThirty(2, policeType, 30);

		LinkedHashMap<String, CalculationAlarm> map = new LinkedHashMap<String, CalculationAlarm>();
		map.put("minMaxDifferPoliceNum", minMaxDifferPoliceNum);
		map.put("fuMaxDifferPoliceNum", fuMaxDifferPoliceNum);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(map);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 今天紧缺或超出最大的派出所
	 * 
	 * @param policeType
	 * @return
	 */
	@RequestMapping(value = "/calculation/max-differpolicenum", method = RequestMethod.POST)
	public ResponseEntity<?> getMaxDifferPoliceNum(@Param("policeType") Integer policeType) {

		CalculationAlarm minMaxDifferPoliceNum = calculationService.maxDifferPoliceNum(0, policeType);
		CalculationAlarm fuMaxDifferPoliceNum = calculationService.maxDifferPoliceNum(2, policeType);

		LinkedHashMap<String, CalculationAlarm> map = new LinkedHashMap<String, CalculationAlarm>();
		map.put("minMaxDifferPoliceNum", minMaxDifferPoliceNum);
		map.put("fuMaxDifferPoliceNum", fuMaxDifferPoliceNum);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(map);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 查询单位最新民警/辅警实际数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/calculation/new-policestation-actual", method = RequestMethod.POST)
	public ResponseEntity<?> getNewPoliceStationActual(@Param("policeType") Integer policeType) {

		List<CalculationAlarm> newPoliceStationActual = calculationService.newPoliceStationActual(policeType);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(newPoliceStationActual);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 查询某派出所最新数据
	 * 
	 * @param policeType
	 * @param policeStationId
	 * @return
	 */
	@RequestMapping(value = "/calculation/policestation-new-data", method = RequestMethod.POST)
	public ResponseEntity<?> getPoliceStationNewData(@Param("policeType") Integer policeType,
			@Param("policeStationId") Integer policeStationId) {

		CalculationAlarm policeStationNewData = calculationService.policeStationNewData(policeType, policeStationId);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(policeStationNewData);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 获得民警数据
	 * 
	 * @param policeType
	 * @param policeStationId
	 * @return
	 */
	@RequestMapping(value = "/calculation/get-alarm-police-data", method = RequestMethod.POST)
	public ResponseEntity<?> getAlarmPoliceData(Integer policeType) {
		if (policeType == null) {
			policeType = 1;
		}
		ArrayList<Object> list1 = new ArrayList<Object>();
		// 求和
		List<Calculation> calculationListSum = new ArrayList<Calculation>();

		// 派出所查询(api)
		List<PoliceStation> policeStationList = departmentService.policeStationApiListSum(policeType);
		for (PoliceStation x : policeStationList) {
			// 派出所民警岗位查询(api)
			List<PoliceStationPost> policeStationPostList = departmentService.policeStationPostApiListSum(policeType);
			x.setPoliceStationPostList(policeStationPostList);

			int sum1 = 0;

			for (PoliceStationPost y : policeStationPostList) {
				// 测算结果查询
				List<Calculation> calculationOverList = calculationService.calculationOverList(x.getId(), y.getId());

				if (calculationOverList.size() > 0) {
					// 累计总和
					sum1 += calculationOverList.get(0).getActualPoliceNum();
				}

				y.setCalculationOverList(calculationOverList);
			}

			Calculation calculation = new Calculation();
			calculation.setActualPoliceNum(sum1);
			calculationListSum.add(calculation);

		}

		// 设置最后一项的calculationOverList
		PoliceStation policeStation = policeStationList.get(policeStationList.size() - 1);

		// 总计数量
		// List<Calculation> calculationOverListSum =
		// calculationService.calculationOverListSum(policeType);

		// 修改最后一行
		for (int i = 0; i < policeStation.getPoliceStationPostList().size(); i++) {

			List<Calculation> calculationList = new ArrayList<Calculation>();

			// calculationList.add(calculationOverListSum.get(i));

			calculationList.add(calculationListSum.get(i));

			policeStation.getPoliceStationPostList().get(i).setCalculationOverList(calculationList);

		}

		// 修改除最后一个每个的总计
		// 总计数量
		// List<Calculation> calculationOverListSumY =
		// calculationService.calculationOverListSumY(policeType);

		for (int i = 0; i < policeStationList.size() - 1; i++) {

			List<PoliceStationPost> policeStationPostList = policeStationList.get(i).getPoliceStationPostList();

			PoliceStationPost policeStationPost = policeStationPostList.get(policeStationPostList.size() - 1);

			List<Calculation> calculationList = new ArrayList<Calculation>();

			// calculationList.add(calculationOverListSumY.get(i));

			calculationList.add(calculationListSum.get(i));

			policeStationPost.setCalculationOverList(calculationList);

		}

		list1.add(policeStationList);// 各派出所警力

		ArrayList<Object> list2 = new ArrayList<Object>();
		ArrayList<CalculationAlarm> list3 = new ArrayList<CalculationAlarm>();
		if (policeType == 1) {// 民警
			// 派出所查询(api)
			List<PoliceStation> policeStationList2 = departmentService.policeStationApiList(null);
			for (PoliceStation x : policeStationList2) {
				// 民警案件类型查询（后台）
				List<CalculationProject> policeProjectList = calculationService.policeProjectList(null);
				x.setPoliceProjectList(policeProjectList);

				// 根据派出所查询各派出所最新数据放入集合
				CalculationAlarm policeStationNewData = calculationService.policeStationNewData(policeType, x.getId());
				list3.add(policeStationNewData);

				for (CalculationProject y : policeProjectList) {
					// 各派出所案件数据查询(后台)
					List<CalculationDetail> calDetailsList = calculationService.calDetailsList(y.getId(), x.getId());
					y.setCalculationDetailList(calDetailsList);
				}
			}
			list2.add(policeStationList2);
		} else {// 辅警
			// 派出所查询(api)
			List<PoliceStation> policeStationList2 = departmentService.policeStationApiList(null);
			for (PoliceStation x : policeStationList2) {
				// 民警案件类型查询（后台）
				List<CalculationProject> auxiliaryPoliceProjectList = calculationService
						.auxiliaryPoliceProjectList(null);
				x.setPoliceProjectList(auxiliaryPoliceProjectList);

				// 根据派出所查询各派出所最新数据放入集合
				CalculationAlarm policeStationNewData = calculationService.policeStationNewData(policeType, x.getId());
				list3.add(policeStationNewData);

				for (CalculationProject y : auxiliaryPoliceProjectList) {
					// 各派出所案件数据查询(后台)
					List<CalculationDetail> calDetailsList = calculationService.calDetailsList(y.getId(), x.getId());
					y.setCalculationDetailList(calDetailsList);
				}
			}
			list2.add(policeStationList2); // 各派出所警力测试因子
		}

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("list1", list1);
		map.put("list2", list2);
		map.put("list3", list3);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(map);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 查询民警辅警总数
	 * 
	 * @param policeType
	 * @return
	 */
	@RequestMapping(value = "/calculation/police-num", method = RequestMethod.POST)
	public ResponseEntity<?> getPoliceNum() {

		Integer minSumPoliceNum = calculationService.sumPoliceNum(1);
		Integer fuSumPoliceNum = calculationService.sumPoliceNum(2);

		LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("minSumPoliceNum", minSumPoliceNum);
		map.put("fuSumPoliceNum", fuSumPoliceNum);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(map);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	// 警力岗位人数计算
	@RequestMapping(value = "/calculation/police/pie/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> pieCalculationStatisticsList(
			@RequestParam(value = "policeType", required = false) Integer policeType) {
		if (policeType == null) {
			policeType = 1;
		}
		Integer postType = policeType;
		DataListReturn dlr = new DataListReturn();
		List<CalculationChart> policeStatisticsList = calculationService.policepieStatisticsList(policeType, postType);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(policeStatisticsList);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

}

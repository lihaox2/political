package com.bayee.political.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bayee.political.domain.Calculation;
import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.EvaluateTask;
import com.bayee.political.domain.EvaluateTaskParticipant;
import com.bayee.political.domain.HomePageModularPersonalStatistics;
import com.bayee.political.domain.HomePageModularStatistics;
import com.bayee.political.domain.LeaveAlarmLeaderStatistics;
import com.bayee.political.service.CalculationService;
import com.bayee.political.service.EvaluationService;
import com.bayee.political.service.HomePageService;
import com.bayee.political.utils.DataListChart;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.StatusCode;
import com.taobao.api.ApiException;

/**
 * @author shentuqiwei
 * @version 2020年6月13日 下午3:38:13
 */
@Controller
public class HomePageController extends BaseController {

	@Autowired
	HomePageService homePageService;

	@Autowired
	EvaluationService evaluationService;

	@Autowired
	private CalculationService calculationService;

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// 评价任务Top5(后台)
	@RequestMapping(value = "/home/evaluation/list", method = RequestMethod.GET)
	public ResponseEntity<?> homeEvaluationList() throws UnsupportedEncodingException {
		DataListReturn dlr = new DataListReturn();
		// 评价任务Top5列表查询(后台)
		List<EvaluateTask> homeEvaluationList = homePageService.homeEvaluationList();
		for (int i = 0; i < homeEvaluationList.size(); i++) {
			int totalNum = evaluationService.evaluateTaskParticipantTotal(homeEvaluationList.get(i).getId());
			int overNum = evaluationService.evaluateTaskParticipantOver(homeEvaluationList.get(i).getId());
			homeEvaluationList.get(i).setTotalNum(totalNum);
			homeEvaluationList.get(i).setOverNum(overNum);
			double f = (double) overNum / totalNum;
			String fString = String.format("%.3f", f);
			homeEvaluationList.get(i).setCompletionRate(Double.valueOf(fString) * 100);
			homeEvaluationList.get(i).setStrCreationDate(format.format(homeEvaluationList.get(i).getCreationDate()));
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(homeEvaluationList);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 评价任务类型饼图(后台)
	@RequestMapping(value = "/home/evaluation/pie/chart", method = RequestMethod.GET)
	public ResponseEntity<?> homeEvaluationPieChart() throws UnsupportedEncodingException {
		DataListReturn dlr = new DataListReturn();
		// 评价任务类型饼图(后台)
		List<CalculationChart> homeEvaluationPieChart = homePageService.homeEvaluationPieChart();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(homeEvaluationPieChart);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 评价任务类型折线图(后台)
	@RequestMapping(value = "/home/evaluation/line/chart", method = RequestMethod.GET)
	public ResponseEntity<?> homeEvaluationLineChart() throws UnsupportedEncodingException {
		DataListChart dlr = new DataListChart();
		List<String> strList = new ArrayList<String>();
		strList.add("1");
		strList.add("2");
		strList.add("3");
		strList.add("4");
		List<String> strList2 = new ArrayList<String>(strList);
		List<String> strList3 = new ArrayList<String>(strList);
		List<String> strList4 = new ArrayList<String>(strList);
		// 总数统计
		List<CalculationChart> homeLineChart1 = homePageService.homeLineChart(null);
		List<String> chartList1 = new ArrayList<String>();
		for (int i = 0; i < homeLineChart1.size(); i++) {
			chartList1.add(homeLineChart1.get(i).getName());
		}
		strList.removeAll(chartList1);
		if (strList.size() > 0) {
			for (int i = 0; i < strList.size(); i++) {
				CalculationChart chart = new CalculationChart();
				chart.setId(Integer.valueOf(strList.get(i)));
				chart.setName(strList.get(i));
				chart.setNum(0);
				homeLineChart1.add(chart);
			}
		}
		// 个人统计
		List<CalculationChart> homeLineChart2 = homePageService.homeLineChart(1);
		List<String> chartList2 = new ArrayList<String>();
		for (int i = 0; i < homeLineChart2.size(); i++) {
			chartList2.add(homeLineChart2.get(i).getName());
		}
		strList2.removeAll(chartList2);
		if (strList2.size() > 0) {
			for (int i = 0; i < strList2.size(); i++) {
				CalculationChart chart = new CalculationChart();
				chart.setId(Integer.valueOf(strList2.get(i)));
				chart.setName(strList2.get(i));
				chart.setNum(0);
				homeLineChart2.add(chart);
			}
		}
		// 单位统计
		List<CalculationChart> homeLineChart3 = homePageService.homeLineChart(2);
		List<String> chartList3 = new ArrayList<String>();
		for (int i = 0; i < homeLineChart3.size(); i++) {
			chartList3.add(homeLineChart3.get(i).getName());
		}
		strList3.removeAll(chartList3);
		if (strList3.size() > 0) {
			for (int i = 0; i < strList3.size(); i++) {
				CalculationChart chart = new CalculationChart();
				chart.setId(Integer.valueOf(strList3.get(i)));
				chart.setName(strList3.get(i));
				chart.setNum(0);
				homeLineChart3.add(chart);
			}
		}
		// 项目统计
		List<CalculationChart> homeLineChart4 = homePageService.homeLineChart(3);
		List<String> chartList4 = new ArrayList<String>();
		for (int i = 0; i < homeLineChart4.size(); i++) {
			chartList4.add(homeLineChart4.get(i).getName());
		}
		strList4.removeAll(chartList4);
		if (strList4.size() > 0) {
			for (int i = 0; i < strList4.size(); i++) {
				CalculationChart chart = new CalculationChart();
				chart.setId(Integer.valueOf(strList4.get(i)));
				chart.setName(strList4.get(i));
				chart.setNum(0);
				homeLineChart4.add(chart);
			}
		}
		List<CalculationChart> slList1 = sortListData(homeLineChart1);
		List<CalculationChart> slList2 = sortListData(homeLineChart2);
		List<CalculationChart> slList3 = sortListData(homeLineChart3);
		List<CalculationChart> slList4 = sortListData(homeLineChart4);
		for (int i = 0; i < slList1.size(); i++) {
			if (Integer.valueOf(slList1.get(i).getName()) == 1) {
				slList1.get(i).setName("第一季度");
				slList2.get(i).setName("第一季度");
				slList3.get(i).setName("第一季度");
				slList4.get(i).setName("第一季度");
			} else if (Integer.valueOf(slList1.get(i).getName()) == 2) {
				slList1.get(i).setName("第二季度");
				slList2.get(i).setName("第二季度");
				slList3.get(i).setName("第二季度");
				slList4.get(i).setName("第二季度");
			} else if (Integer.valueOf(slList1.get(i).getName()) == 3) {
				slList1.get(i).setName("第三季度");
				slList2.get(i).setName("第三季度");
				slList3.get(i).setName("第三季度");
				slList4.get(i).setName("第三季度");
			} else if (Integer.valueOf(slList1.get(i).getName()) == 4) {
				slList1.get(i).setName("第四季度");
				slList2.get(i).setName("第四季度");
				slList3.get(i).setName("第四季度");
				slList4.get(i).setName("第四季度");
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setTotalResult(slList1);
		dlr.setPersonResult(slList2);
		dlr.setCompanyResult(slList3);
		dlr.setObjectResult(slList4);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListChart>(dlr, HttpStatus.OK);
	}

	// 通过季度进行排序
	private List<CalculationChart> sortListData(List<CalculationChart> finalList) {
		Collections.sort(finalList, (o1, o2) -> {
			if (o2.getId() < o1.getId()) {
				return 1;
			}
			return -1;
		});
		return finalList;
	}

	// 最新审核查询(后台)
	@RequestMapping(value = "/home/evaluation/check", method = RequestMethod.GET)
	public ResponseEntity<?> homeEvaluationcheck() throws UnsupportedEncodingException {
		DataListReturn dlr = new DataListReturn();
		// 最新差评查询(后台)(2条)
		List<EvaluateTaskParticipant> checkList = homePageService.homeEvaluationCheckList();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(checkList);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 新评价审核忽略
	@RequestMapping(value = { "/home/evaluation/check/update" }, method = RequestMethod.POST)
	public @ResponseBody byte[] evaluationAuthoritySubmit(@RequestParam(value = "id", required = false) Integer id)
			throws IOException, ParseException {
		DataListReturn dlr = new DataListReturn();
		EvaluateTaskParticipant item = new EvaluateTaskParticipant();
		item.setId(id);
		item.setIsIgnore(1);
		item.setUpdateDate(new Date());
		int count = evaluationService.evaluateTaskParticipantUpdate(item);
		if (count > 0) {
			dlr.setStatus(true);
			dlr.setMessage("success");
			dlr.setResult(count);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("fail");
			dlr.setResult(count);
			dlr.setCode(StatusCode.getFailcode());
		}
		return gson.toJson(dlr).getBytes("utf-8");
	}

	// 首页局领导综合数据统计
	@RequestMapping(value = "/home/page/modular/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> homePageModularStatistics(
			@RequestParam(value = "policeType", required = false) Integer policeType) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		HomePageModularStatistics item = new HomePageModularStatistics();
		// 今日出勤统计
		HomePageModularStatistics todayWorkItem = homePageService.todayWorkItem();
		item.setWorkPoliceNum(todayWorkItem.getWorkPoliceNum());
		item.setOnDutyPoliceNum(todayWorkItem.getOnDutyPoliceNum());
		item.setAskForLeavePoliceNum(todayWorkItem.getAskForLeavePoliceNum());
		item.setLeavePoliceNum(todayWorkItem.getLeavePoliceNum());
		// 今日训练统计
		HomePageModularStatistics todayTrainItem = homePageService.todayTrainItem();
		item.setTrainNum(todayTrainItem.getTrainNum());
		item.setTrainPoliceNum(todayTrainItem.getTrainPoliceNum());
		item.setTrainPassNum(todayTrainItem.getTrainPassNum());
		item.setTrainFailNum(todayTrainItem.getTrainFailNum());
		// 今日预警统计
		HomePageModularStatistics todayAlarmItem = homePageService.todayAlarmItem();
		item.setAlarmNum(todayAlarmItem.getAlarmNum());
		item.setAlarmCheckNum(todayAlarmItem.getAlarmCheckNum());
		item.setAlarmEntryAndExitNum(todayAlarmItem.getAlarmEntryAndExitNum());
		item.setAlarmTalkNum(todayAlarmItem.getAlarmTalkNum());
		// 今日赛事统计
		HomePageModularStatistics todayMatchItem = homePageService.todayMatchItem();
		item.setMatchOngoingNum(todayMatchItem.getMatchOngoingNum());
		item.setMatchJoinNum(todayMatchItem.getMatchJoinNum());
		item.setMatchDepNum(todayMatchItem.getMatchDepNum());
		item.setMatchBranchOfficeNum(todayMatchItem.getMatchBranchOfficeNum());
		if (policeType == null) {
			policeType = 1;
		}
		// 今日警力查询
		List<Calculation> list = calculationService.todayCalculationList(policeType);
		item.setPoliceList(list);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 首页个人综合数据统计
	@RequestMapping(value = "/home/page/modular/personal/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> homePageModularPersonalStatistics(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		HomePageModularPersonalStatistics item = new HomePageModularPersonalStatistics();
		// 我的考勤
		HomePageModularPersonalStatistics myAttendanceItem = homePageService.myAttendanceItem(policeId, dateTime);
		item.setAttendanceDays(myAttendanceItem.getAttendanceDays());
		item.setLeaveDays(myAttendanceItem.getLeaveDays());
		item.setOvertimeHours(myAttendanceItem.getOvertimeHours());
		// 我的训练
		HomePageModularPersonalStatistics myTrainItem = homePageService.myTrainItem(policeId, dateTime);
		item.setTrainNum(myTrainItem.getTrainNum());
		item.setTrainPhysicalNum(myTrainItem.getTrainPhysicalNum());
		item.setTrainFirearmNum(myTrainItem.getTrainFirearmNum());
		item.setTrainPassNum(myTrainItem.getTrainPassNum());
		item.setTrainFailNum(myTrainItem.getTrainFailNum());
		// 我的赛事
		HomePageModularPersonalStatistics myMatchItem = homePageService.myMatchItem(policeId, dateTime);
		item.setMatchNum(myMatchItem.getMatchNum());
		item.setMatchBranchOfficeNum(myMatchItem.getMatchBranchOfficeNum());
		item.setMatchDepNum(myMatchItem.getMatchDepNum());
		// 我的预警
		HomePageModularPersonalStatistics myAlarmItem = homePageService.myAlarmItem(policeId, dateTime);
		item.setAlarmCheckNum(myAlarmItem.getAlarmCheckNum());
		item.setAlarmEntryAndExitNum(myAlarmItem.getAlarmEntryAndExitNum());
		item.setAlarmTalkNum(myAlarmItem.getAlarmTalkNum());
		// 评价任务
		HomePageModularPersonalStatistics myEvaluateItem = homePageService.myEvaluateItem(policeId, dateTime);
		item.setEvaluateNum(myEvaluateItem.getEvaluateNum());
		item.setEvaluatePersonalNum(myEvaluateItem.getEvaluatePersonalNum());
		item.setEvaluateDepNum(myEvaluateItem.getEvaluateDepNum());
		item.setEvaluateProjectNum(myEvaluateItem.getEvaluateProjectNum());
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

}

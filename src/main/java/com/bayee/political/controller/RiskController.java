package com.bayee.political.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.bayee.political.algorithm.RiskCompute;
import com.bayee.political.domain.*;
import com.bayee.political.json.ChartResult;
import com.bayee.political.json.RiskHealthIndexItemResult;
import com.bayee.political.json.RiskRelevantItemResult;
import com.bayee.political.pojo.GlobalIndexNumResultDO;
import com.bayee.political.pojo.RiskRelevantItemRecordResultDO;
import com.bayee.political.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bayee.political.utils.DataListPage;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
import com.bayee.political.utils.StatusCode;
import com.taobao.api.ApiException;

/**
 * @author shentuqiwei
 * @version 2021年3月26日 上午10:06:18
 */
@Controller
@Component
public class RiskController extends BaseController {

	@Autowired
	private RiskService riskService;

	@Autowired
	private AlarmService alarmService;
	
	@Autowired
	private TrainService trainService;
	
	@Autowired
	private UserService userService;

	@Autowired
	RiskRelevantService riskRelevantService;

	@Autowired
	RiskRelevantRecordService riskRelevantRecordService;

	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");

	DecimalFormat df = new DecimalFormat(".00");

	// 警员风险分页查询
		@RequestMapping(value = "/risk/page/list", method = RequestMethod.GET)
		public ResponseEntity<?> riskPageList(@RequestParam(value = "keyWords", required = false) String keyWords,
				@RequestParam(value = "alarmType", required = false) Integer alarmType,
				@RequestParam(value = "dateTime", required = false) String dateTime,
				@RequestParam(value = "sortType", required = false) Integer sortType,
				@RequestParam(value = "pageSize", required = false) Integer pageSize,
				@RequestParam(value = "pageNum", required = false) Integer pageNum,
				@RequestParam(value = "num", required = false) Double num,
				@RequestParam("deptId") Integer deptId) throws ApiException, ParseException {
			DataListPage dlr = new DataListPage();
			
			if(num==null) {
				num=1.0;
			}
			
			int userNum=userService.countTotal();
			String sortName = null;
			String orderName = null;
			if (sortType == null || sortType == 11001) {
				sortName = "a.total_num";
				orderName = "total_num";
			} else if (sortType == 11002) {
				sortName = "a.conduct_num";
				orderName = "conduct_num";
			} else if (sortType == 11003) {
				sortName = "a.handling_case_num";
				orderName = "handling_case_num";
			} else if (sortType == 11004) {
				sortName = "a.duty_num";
				orderName = "duty_num";
			} else if (sortType == 11005) {
				sortName = "a.train_num";
				orderName = "train_num";
			} else if (sortType == 11006) {
				sortName = "a.social_contact_num";
				orderName = "social_contact_num";
			} else if (sortType == 11007) {
				sortName = "a.amily_evaluation_num";
				orderName = "amily_evaluation_num";
			} else if (sortType == 11008) {
				sortName = "a.health_num";
				orderName = "health_num";
			} else if (sortType == 11010) {
				sortName = "a.relevant_num";
				orderName = "rr.index_num";
			}
			if (dateTime == null) {
				dateTime = sd.format(new Date());
			}
			Date currdate = sd.parse(dateTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currdate);
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
			String lastDateTime = sd.format(calendar.getTime());
			String lastMonthTime = DateUtils.lastMonthTime();
			Integer pageNums = pageNum;
			if (pageSize == null) {
				pageSize = 10;
			}
			pageNum = ((pageNum) - 1) * pageSize;
			// 警员风险分页查询
			List<RiskReportRecord> list = riskService.riskPageList(keyWords, alarmType, sortName, dateTime, lastDateTime,
					lastMonthTime, pageSize, pageNum,(int)(userNum*num),orderName, deptId);
			for (int i = 0; i < list.size(); i++) {
				// 警员健康风险指数查询
				RiskHealth item = riskService.riskHealthIndexNewestItem(list.get(i).getPoliceId());
				if (item != null && item.getIndexNum() != null) {
					list.get(i).setHealthNum(item.getIndexNum());
				} else {
					list.get(i).setHealthNum(0.0);
				}
				/*Double totalNum = list.get(i).getConductNum() + list.get(i).getHandlingCaseNum() + list.get(i).getDutyNum()
						+ list.get(i).getTrainNum() + list.get(i).getSocialContactNum()
						+ list.get(i).getAmilyEvaluationNum() + list.get(i).getDrinkNum() + list.get(i).getStudyNum()
						+ list.get(i).getWorkNum() + list.get(i).getHealthNum();
				list.get(i).setTotalNum(Double.valueOf(df.format(Math.min(totalNum, 100))));*/
				// 警员风险雷达图
				List<ScreenDoubeChart> list2 = riskService.riskChartList(list.get(i).getPoliceId(), dateTime, lastMonthTime,
						1);
				list.get(i).setChartList(list2);
			}
			// 警员风险列表总数
			int total = riskService.riskPageCount(keyWords, alarmType, dateTime, lastDateTime, lastMonthTime,(int)(userNum*num),orderName, deptId);
			dlr.setStatus(true);
			dlr.setMessage("success");
			if(sortType == null || sortType == 11001) {
				dlr.setResult(list.stream().sorted(Comparator.comparing(RiskReportRecord::getTotalNum).reversed()).collect(Collectors.toList()));
			} else if (sortType == 11002) {
				dlr.setResult(list.stream().sorted(Comparator.comparing(RiskReportRecord::getConductNum).reversed()).collect(Collectors.toList()));
			} else if (sortType == 11003) {
				dlr.setResult(list.stream().sorted(Comparator.comparing(RiskReportRecord::getHandlingCaseNum).reversed()).collect(Collectors.toList()));
			} else if (sortType == 11004) {
				dlr.setResult(list.stream().sorted(Comparator.comparing(RiskReportRecord::getDutyNum).reversed()).collect(Collectors.toList()));
			} else if (sortType == 11005) {
				dlr.setResult(list.stream().sorted(Comparator.comparing(RiskReportRecord::getTrainNum).reversed()).collect(Collectors.toList()));
			} else if (sortType == 11006) {
				dlr.setResult(list.stream().sorted(Comparator.comparing(RiskReportRecord::getSocialContactNum).reversed()).collect(Collectors.toList()));
			} else if (sortType == 11007) {
				dlr.setResult(list.stream().sorted(Comparator.comparing(RiskReportRecord::getAmilyEvaluationNum).reversed()).collect(Collectors.toList()));
			}else if (sortType == 11008) {
				dlr.setResult(list.stream().sorted(Comparator.comparing(RiskReportRecord::getHealthNum).reversed()).collect(Collectors.toList()));
			}else {
				dlr.setResult(list);
			}

			dlr.setCode(StatusCode.getSuccesscode());
			dlr.setTotal(total);
			dlr.setPageNum(pageNums);
			dlr.setPageSize(pageSize);
			dlr.setTotalPage(((int) Math.ceil((double) total / (double) pageSize)));
			int count = ((int) Math.ceil((double) total / (double) pageSize));
			if (count - pageNums > 0) {
				dlr.setPageNext(1);
			} else {
				dlr.setPageNext(0);
			}
			return new ResponseEntity<DataListPage>(dlr, HttpStatus.OK);
		}
	
	
	
	// 警员风险分页查询
			@RequestMapping(value = "/risk/year/alarm/list", method = RequestMethod.GET)
			public ResponseEntity<?> riskYearAlarmList(@RequestParam(value = "keyWords", required = false) String keyWords,
					@RequestParam(value = "alarmType", required = false) Integer alarmType,
					@RequestParam(value = "dateTime", required = false) String dateTime,
					@RequestParam(value = "sortType", required = false) Integer sortType,
					@RequestParam(value = "pageSize", required = false) Integer pageSize,
					@RequestParam(value = "pageNum", required = false) Integer pageNum,
					@RequestParam(value = "num", required = false) Double num) throws ApiException, ParseException {
				DataListPage dlr = new DataListPage();
				
				if(num==null) {
					num=1.0;
				}
				int userNum=userService.countTotal();
				String sortName = null;
				String orderName = null;
				if (sortType == null || sortType == 11001) {
					sortName = "a.total_num";
					orderName = "total_num";
				} else if (sortType == 11002) {
					sortName = "a.conduct_num";
					orderName = "conduct_num";
				} else if (sortType == 11003) {
					sortName = "a.handling_case_num";
					orderName = "handling_case_num";
				} else if (sortType == 11004) {
					sortName = "a.duty_num";
					orderName = "duty_num";
				} else if (sortType == 11005) {
					sortName = "a.train_num";
					orderName = "train_num";
				} else if (sortType == 11006) {
					sortName = "a.social_contact_num";
					orderName = "social_contact_num";
				} else if (sortType == 11007) {
					sortName = "a.amily_evaluation_num";
					orderName = "amily_evaluation_num";
				} else if (sortType == 11008) {
					sortName = "a.health_num";
					orderName = "health_num";
				}
				if (dateTime == null) {
					dateTime = sd.format(new Date());
				}
				Date currdate = sd.parse(dateTime);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(currdate);
				calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
				String lastDateTime = sd.format(calendar.getTime());
				String lastMonthTime = DateUtils.lastMonthTime();
				// 警员风险分页查询
				List<RiskReportRecord> list = riskService.riskPageList(keyWords, alarmType, sortName, dateTime, lastDateTime,
						lastMonthTime, 10, 0,(int)(userNum*num),orderName, null);
				
				for (int i = 0; i < list.size(); i++) {
					// 警员健康风险指数查询
					RiskHealth item = riskService.riskHealthIndexNewestItem(list.get(i).getPoliceId());
					if (item != null && item.getIndexNum() != null) {
						list.get(i).setHealthNum(item.getIndexNum());
					} else {
						list.get(i).setHealthNum(0.0);
					}
					/*Double totalNum = list.get(i).getConductNum() + list.get(i).getHandlingCaseNum() + list.get(i).getDutyNum()
							+ list.get(i).getTrainNum() + list.get(i).getSocialContactNum()
							+ list.get(i).getAmilyEvaluationNum() + list.get(i).getDrinkNum() + list.get(i).getStudyNum()
							+ list.get(i).getWorkNum() + list.get(i).getHealthNum();
					list.get(i).setTotalNum(Double.valueOf(df.format(totalNum)));*/
				}
				dlr.setMessage("success");
				if(sortType == null || sortType == 11001) {
					dlr.setResult(list.stream().sorted(Comparator.comparing(RiskReportRecord::getTotalNum).reversed()).collect(Collectors.toList()));
				}else {
					dlr.setResult(list);
				}

				dlr.setCode(StatusCode.getSuccesscode());
				dlr.setPageNext(0);
				return new ResponseEntity<DataListPage>(dlr, HttpStatus.OK);
			}

	// 警员风险详情查询
	@RequestMapping(value = "/risk/report/record/item", method = RequestMethod.GET)
	public ResponseEntity<?> riskReportRecordItem(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "dateTime", required = false) String dateTime,
			@RequestParam(value = "timeType", required = false) Integer timeType) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		if (timeType == null) {// 1总计2月份
			timeType = 1;
		}
		if (dateTime == null) {
			dateTime = sd.format(new Date());
		}
		Date currdate = sd.parse(dateTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currdate);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		String lastDateTime = sd.format(calendar.getTime());
		String lastMonthTime = DateUtils.lastMonthTime();

		// 警员风险详情查询
		RiskReportRecord item = riskService.riskReportRecordItem(id, policeId, dateTime, lastDateTime, lastMonthTime,
				timeType);
		if (item != null) {
			User user = userService.findByPoliceId(policeId);
			item.setHealthShowFlag(user.getRiskHealthShowFlag());
			// 警员健康风险指数查询
			RiskHealth ritem = riskService.riskHealthIndexItem(policeId, dateTime);
			if (ritem != null && ritem.getIndexNum() != null) {
				item.setHealthNum(ritem.getIndexNum());
			} else {
				item.setHealthNum(0.0);
			}
			/*Double totalNum = item.getConductNum() + item.getHandlingCaseNum() + item.getDutyNum() + item.getTrainNum()
					+ item.getSocialContactNum() + item.getAmilyEvaluationNum() + item.getHealthNum();
			item.setTotalNum(Double.valueOf(df.format(totalNum)));*/
			// 警员风险雷达图
			List<ScreenDoubeChart> list2 = riskService.riskChartList(item.getPoliceId(), dateTime, lastMonthTime,
					timeType);
			item.setChartList(list2);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警员预警类型查询
	@RequestMapping(value = "/risk/alarm/type/list", method = RequestMethod.GET)
	public ResponseEntity<?> riskAlarmTypeList(@RequestParam(value = "id", required = false) Integer id)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 警员预警类型查询
		List<RiskAlarmType> list = riskService.riskAlarmTypeList(id);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警员预警分页查询
	@RequestMapping(value = "/risk/alarm/page/list", method = RequestMethod.GET)
	public ResponseEntity<?> riskAlarmPageList(@RequestParam(value = "policeId", required = false) String policeId,
												@RequestParam(value = "type", required = false) Integer type,
												@RequestParam(value = "pageSize", required = false) Integer pageSize,
												@RequestParam(value = "pageNum", required = false) Integer pageNum,
											   	@RequestParam(value = "month", required = false) Integer month) throws ApiException, ParseException {
		DataListPage dlr = new DataListPage();
		
		Calendar cal = Calendar.getInstance();
		Integer year = cal.get(Calendar.YEAR);
		String yearStr=year.toString();
		String monthStr=month.toString();
		if(month<10) {
			monthStr="0"+monthStr;
		}
		String dateTime=yearStr+"-"+monthStr;
		if (type == null) {
			type = 1;
		}
		Integer pageNums = pageNum;
		if (pageSize == null) {
			pageSize = 10;
		}
		pageNum = ((pageNum) - 1) * pageSize;
		String startTime = null;
		String endTime = null;
		if (type == 1) {// 当前月
			startTime = DateUtils.fillDate(null, 2).getStartTime();
			endTime = DateUtils.fillDate(null, 2).getEndTime();
		} else if (type == 2) {// 当前季
			startTime = DateUtils.fillDate(null, 3).getStartTime();
			endTime = DateUtils.fillDate(null, 3).getEndTime();
		} else if (type == 3) {// 当前年
			startTime = DateUtils.fillDate(null, 5).getStartTime();
			endTime = DateUtils.fillDate(null, 5).getEndTime();
		}
		// 警员预警分页查询
		List<RiskAlarm> list = riskService.riskAlarmPageList(startTime, endTime, pageSize, pageNum,dateTime);
		// 警员预警列表总数
		int total = riskService.riskAlarmPageCount(startTime, endTime,dateTime);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		dlr.setTotal(total);
		dlr.setPageNum(pageNums);
		dlr.setPageSize(pageSize);
		dlr.setTotalPage(((int) Math.ceil((double) total / (double) pageSize)));
		int count = ((int) Math.ceil((double) total / (double) pageSize));
		if (count - pageNums > 0) {
			dlr.setPageNext(1);
		} else {
			dlr.setPageNext(0);
		}
		return new ResponseEntity<DataListPage>(dlr, HttpStatus.OK);
	}

	@GetMapping("/risk/relevant/item")
	public ResponseEntity<?> riskRelevantItem(@RequestParam(value = "policeId", required = false) String policeId,
											  @RequestParam(value = "dateTime", required = false) String dateTime,
											  @RequestParam(value = "timeType", required = false) Integer timeType) {
		if (timeType == null) {
			timeType = 1;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);

		RiskRelevant riskRelevant = riskRelevantService.riskRelevantItem(policeId, dateTime, timeType);
		RiskRelevant thisMonth = riskRelevantService.riskRelevantItem(policeId, DateUtils.formatDate(new Date(), "yyyy-MM"), 2);
		RiskRelevant lastMonth = riskRelevantService.riskRelevantItem(policeId, DateUtils.formatDate(calendar.getTime(), "yyyy-MM"), 2);
		RiskRelevantItemRecordResultDO recordResultDO = riskRelevantRecordService.riskRelevantItemRecord(policeId, dateTime, timeType);

		ChartResult thisMonthChart = new ChartResult();
		thisMonthChart.setId(1);
		thisMonthChart.setName("本月");
		thisMonthChart.setValue(thisMonth.getIndexNum());

		ChartResult lastMonthChart = new ChartResult();
		lastMonthChart.setId(2);
		lastMonthChart.setName("上月");
		lastMonthChart.setValue(lastMonth.getIndexNum());

		RiskRelevantItemResult result = new RiskRelevantItemResult();
		result.setMajorEventsNum(0d);
		result.setPoliticalAction(0d);
		result.setIdeologicalDynamicsNum(0d);
		result.setPotentialRisk(0d);
		if (recordResultDO != null) {
			result.setMajorEventsNum(recordResultDO.getMajorEventsNum());
			result.setPoliticalAction(recordResultDO.getPoliticalAction());
			result.setIdeologicalDynamicsNum(recordResultDO.getIdeologicalDynamicsNum());
			result.setPotentialRisk(recordResultDO.getPotentialRisk());
		}
		result.setIndexNum(riskRelevant.getIndexNum());
		result.setThisMonthChart(Arrays.asList(thisMonthChart, lastMonthChart));
		result.setNearSixMonthChart(riskRelevantService.riskNearSixMonthChart(policeId));

		return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
	}

	// 警员执法办案风险查询
	@RequestMapping(value = "/risk/case/index/item", method = RequestMethod.GET)
	public ResponseEntity<?> riskCaseIndexItem(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "dateTime", required = false) String dateTime,
			@RequestParam(value = "timeType", required = false) Integer timeType) throws ApiException, ParseException {
		if (timeType == null) {
			timeType = 1;
		}
		String lastMonthTime = DateUtils.lastMonthTime();
		if (dateTime == null) {
			dateTime = sd.format(new Date());
		}
		Date currdate = sd.parse(dateTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currdate);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);

		String lastDateTime = sd.format(calendar.getTime());
		// 警员执法办案风险指数查询
		RiskCase item = riskService.riskCaseIndexItem(policeId, dateTime, lastMonthTime, timeType);
		if (item != null) {
			List<ScreenDoubeChart> list = new ArrayList<ScreenDoubeChart>();
			// 上个月警员接警执勤指数查询
			RiskCase item2 = riskService.riskCaseIndexItem(policeId, lastDateTime, lastMonthTime, 2);
			ScreenDoubeChart itemChart2 = new ScreenDoubeChart();
			itemChart2.setId(1);
			itemChart2.setName("上月");
			if (item2 != null) {
				itemChart2.setValue(item2.getIndexNum());
			} else {
				itemChart2.setValue(0.0);
			}
			list.add(itemChart2);
			// 本月警员执法办案风险指数查询
			RiskCase item3 = riskService.riskCaseIndexItem(policeId, dateTime, lastMonthTime, 2);
			ScreenDoubeChart itemChart1 = new ScreenDoubeChart();
			itemChart1.setId(2);
			itemChart1.setName("本月");
			if (item3 != null) {
				itemChart1.setValue(item3.getIndexNum());
			} else {
				itemChart1.setValue(0.0);
			}
			list.add(itemChart1);
			item.setList(list);
		} else {
			item = new RiskCase();
		}

		return new ResponseEntity<DataListReturn>(DataListReturn.ok(item), HttpStatus.OK);
	}

	// 执法办案风险指数图例
	@RequestMapping(value = "/risk/case/index/chart", method = RequestMethod.GET)
	public ResponseEntity<?> riskCaseIndexChart(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		// 半年内接警执勤风险指数
		List<ScreenDoubeChart> list = riskService.riskCaseIndexChart(policeId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警员接警执勤指数查询
	@RequestMapping(value = "/risk/duty/index/item", method = RequestMethod.GET)
	public ResponseEntity<?> riskDutyIndexItem(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "dateTime", required = false) String dateTime,
			@RequestParam(value = "timeType", required = false) Integer timeType) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		if (timeType == null) {
			timeType = 1;
		}
		String lastMonthTime = DateUtils.lastMonthTime();
		if (dateTime == null) {
			dateTime = sd.format(new Date());
		}
		Date currdate = sd.parse(dateTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currdate);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		String lastDateTime = sd.format(calendar.getTime());
		// 警员接警执勤指数查询
		RiskDuty item = riskService.riskDutyIndexItem(policeId, dateTime, lastMonthTime, timeType);
		if (item != null) {
			// 警员接警执勤数据列表查询
			List<RiskDutyDealPoliceRecord> rList = riskService.riskDutyRecordList(policeId, dateTime, lastMonthTime,
					timeType);
			if (rList.size() > 0) {
				item.setIsDisplay(1);
			} else {
				item.setIsDisplay(0);
			}
			List<ScreenDoubeChart> list = new ArrayList<ScreenDoubeChart>();
			// 上个月警员接警执勤指数查询
			RiskDuty item2 = riskService.riskDutyIndexItem(policeId, lastDateTime, lastMonthTime, 2);
			ScreenDoubeChart itemChart2 = new ScreenDoubeChart();
			itemChart2.setId(1);
			itemChart2.setName("上月");
			if (item2 != null) {
				itemChart2.setValue(item2.getIndexNum());
			} else {
				itemChart2.setValue(0.0);
			}
			list.add(itemChart2);
			// 本月警员接警执勤指数查询
			RiskDuty item3 = riskService.riskDutyIndexItem(policeId, dateTime, lastMonthTime, 2);
			ScreenDoubeChart itemChart1 = new ScreenDoubeChart();
			itemChart1.setId(2);
			itemChart1.setName("本月");
			if (item3 != null) {
				itemChart1.setValue(item3.getIndexNum());
			} else {
				itemChart1.setValue(0.0);
			}
			list.add(itemChart1);
			item.setList(list);
		} else {
			item = new RiskDuty();
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警员接警执勤数据列表查询
	@RequestMapping(value = "/risk/duty/record/list", method = RequestMethod.GET)
	public ResponseEntity<?> riskDutyRecordItem(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "dateTime", required = false) String dateTime,
			@RequestParam(value = "timeType", required = false) Integer timeType) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		if (timeType == null) {
			timeType = 1;
		}
		String lastMonthTime = DateUtils.lastMonthTime();
		if (dateTime == null) {
			dateTime = sd.format(new Date());
		}
		// 警员接警执勤数据列表查询
		List<RiskDutyDealPoliceRecord> list = riskService.riskDutyRecordList(policeId, dateTime, lastMonthTime,
				timeType);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 半年内接警执勤风险指数
	@RequestMapping(value = "/risk/duty/index/chart", method = RequestMethod.GET)
	public ResponseEntity<?> riskDutyIndexChart(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		// 半年内接警执勤风险指数
		List<ScreenDoubeChart> list = riskService.riskDutyIndexChart(policeId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警员警务技能指数查询
	@RequestMapping(value = "/risk/train/index/item", method = RequestMethod.GET)
	public ResponseEntity<?> riskTrainIndexItem(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "dateTime", required = false) String dateTime,
			@RequestParam(value = "timeType", required = false) Integer timeType) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		if (timeType == null) {
			timeType = 1;
		}
		String lastMonthTime = DateUtils.lastMonthTime();
		if (dateTime == null) {
			dateTime = sd.format(new Date());
		}
		// 警员警务技能指数查询
		RiskTrain item = riskService.riskTrainIndexItem(policeId, dateTime, lastMonthTime, timeType);
		if (item == null) {
			item = new RiskTrain();
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警员警务不合格趋势图
	@RequestMapping(value = "/risk/train/fail/chart", method = RequestMethod.GET)
	public ResponseEntity<?> riskTrainFailChart(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		RiskTrainFailChart item = new RiskTrainFailChart();
		// 警员综合训练不合格趋势图
		List<ScreenChart> physicalFailList = riskService.riskTrainFailChart(policeId, "physical_fail_num");
		item.setPhysicalFailList(physicalFailList);
		// 警员枪械不合格趋势图
		List<ScreenChart> firearmFailList = riskService.riskTrainFailChart(policeId, "firearm_fail_num");
		item.setFirearmFailList(firearmFailList);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警员健康风险指数查询
	@RequestMapping(value = "/risk/health/index/item", method = RequestMethod.GET)
	public ResponseEntity<?> riskHealthIndexItem(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException {
		if (dateTime == null) {
			dateTime = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		} else {
			dateTime = dateTime.substring(0, 4);
		}
		// 警员健康风险指数查询
		RiskHealth item = riskService.riskHealthIndexItem(policeId, dateTime);
		if (item == null) {
			item = new RiskHealth();
		}
		RiskHealthIndexItemResult result = new RiskHealthIndexItemResult();
		result.setYear(item.getYear());
		result.setHeight(item.getHeight());
		result.setWeight(item.getWeight());
		result.setIndexNum(item.getIndexNum());
		result.setBmi(item.getBmi());
		result.setBmiId(item.getBmiId());
		result.setBmiName(item.getBmiName());
		result.setBlood(item.getBlood());
		result.setOverweightNum(item.getOverweightNum() == null ? "否" : item.getOverweightNum() > 0 ? "是" : "否");
		result.setHyperlipidemiaNum(item.getHyperlipidemiaNum() == null ? "否" : item.getHyperlipidemiaNum() > 0 ? "是" : "否");
		result.setHypertensionNum(item.getHypertensionNum() == null ? "否" : item.getHypertensionNum() > 0 ? "是" : "否");
		result.setHyperglycemiaNum(item.getHyperglycemiaNum() == null ? "否" : item.getHyperglycemiaNum() > 0 ? "是" : "否");
		result.setHyperuricemiaNum(item.getHyperuricemiaNum() == null ? "否" : item.getHyperuricemiaNum() > 0 ? "是" : "否");
		result.setProstateNum(item.getProstateNum() == null ? "否" : item.getProstateNum() > 0 ? "是" : "否");
		result.setMajorDiseasesNum(item.getMajorDiseasesNum() == null ? "无" : item.getMajorDiseasesNum() > 0 ? "有" : "无");
		result.setHeartNum(item.getHeartNum() == null ? "无" : item.getHeartNum() > 0 ? "有" : "无");
		result.setTumorAntigenNum(item.getTumorAntigenNum() == null ? "无" : item.getTumorAntigenNum() > 0 ? "有" : "无");
		result.setOrthopaedicsNum(item.getOrthopaedicsNum() == null ? "无" : item.getOrthopaedicsNum() > 0 ? "有" : "无");

		return new ResponseEntity<DataListReturn>(DataListReturn.ok(result), HttpStatus.OK);
	}

	// 警员健康风险去年今年折线图
	@RequestMapping(value = "/risk/health/index/line/chart", method = RequestMethod.GET)
	public ResponseEntity<?> riskHealthIndexLineChart(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		List<ScreenDoubeChart> list = new ArrayList<ScreenDoubeChart>();
		String lastDateTime = null;
		if (dateTime == null) {
			dateTime = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		} else {
			dateTime = dateTime.substring(0, 4);
		}
		lastDateTime = String.valueOf((Integer.valueOf(dateTime) - 1));
		// 去年年查询
		RiskHealth item2 = riskService.riskHealthIndexItem(policeId, lastDateTime);
		ScreenDoubeChart itemChart2 = new ScreenDoubeChart();
		itemChart2.setId(1);
		itemChart2.setName("去年");
		if (item2 != null) {
			itemChart2.setValue(item2.getIndexNum());
		} else {
			itemChart2.setValue(0.0);
		}
		list.add(itemChart2);
		// 当前年查询
		RiskHealth item1 = riskService.riskHealthIndexItem(policeId, dateTime);
		ScreenDoubeChart itemChart1 = new ScreenDoubeChart();
		itemChart1.setId(1);
		itemChart1.setName("今年");
		if (item1 != null) {
			itemChart1.setValue(item1.getIndexNum());
		} else {
			itemChart1.setValue(0.0);
		}
		list.add(itemChart1);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	@GetMapping("/risk/history/year/list")
	public ResponseEntity<?> riskHistoryYearList(@RequestParam(value = "policeId") String policeId) {
		return new ResponseEntity<>(DataListReturn.ok(riskService.riskHistoryYearList(policeId)), HttpStatus.OK);
	}

	// 警员历史风险报告查询
	@RequestMapping(value = "/risk/history/report/list", method = RequestMethod.GET)
	public ResponseEntity<?> riskHistoryReportList(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		if (dateTime == null) {
			dateTime = sd.format(new Date());
		}
		dateTime = dateTime.substring(0, 4);

		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(riskService.riskHistoryReportList(policeId, ""));
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警员风险指标监测
	@RequestMapping(value = "/risk/index/monitor/item", method = RequestMethod.GET)
	public ResponseEntity<?> riskIndexMonitorItem(@RequestParam(value = "dateTime", required = false) String dateTime,
			@RequestParam(value = "num1", required = false) Double num1,
			@RequestParam(value = "num2", required = false) Double num2,
			@RequestParam(value = "num3", required = false) Double num3,
			@RequestParam(value = "num4", required = false) Double num4,
			@RequestParam(value = "num5", required = false) Double num5,
			@RequestParam(value = "num6", required = false) Double num6,
			@RequestParam(value = "num7", required = false) Double num7,
			@RequestParam(value = "num8", required = false) Double num8)
			throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		
		
		if(num1==null) {
			num1=0.1;
		}
		
		if(num2==null) {
			num2=0.1;
		}
		
		if(num3==null) {
			num3=0.1;
		}
		if(num4==null) {
			num4=0.1;
		}
		if(num5==null) {
			num5=0.1;
		}
		if(num6==null) {
			num6=0.1;
		}
		if(num7==null) {
			num7=0.1;
		}
		if(num8==null) {
			num8=0.1;
		}
		
		int userNum=userService.countTotal();
		
		
		if (dateTime == null) {
			dateTime = sd.format(new Date());
		}
		String year = dateTime.substring(0, 4);
		List<RiskIndexMonitorChild> list = new ArrayList<RiskIndexMonitorChild>();
		RiskIndexMonitorChild item0 = new RiskIndexMonitorChild();
		item0.setIndexPoliceNum(0);
		item0.setAlarmPoliceRate(0);
		item0.setAlarmPoliceNum(0);
		item0.setTalkPoliceNum(0);
		item0.setIsDisplay(0);
		// 综合指数风险
		RiskIndexMonitorChild item1 = riskService.comprehensiveIndex(dateTime,(int) (userNum*num1));
		if (item1 != null) {
			item1.setId(11001);
			item1.setName("综合指数风险");
			if(item1.getAlarmPoliceRate()==0) {
				item1.setAlarmPoliceNum(0);
			}else {
				item1.setAlarmPoliceNum((int) (userNum*num1));
			}
			
			list.add(item1);
		} else {
			item0.setId(11001);
			item0.setName("综合指数风险");
			list.add(item0);
		}
		// 行为规范风险
		RiskIndexMonitorChild item2 = riskService.conductIndex(dateTime,(int) (userNum*num2));
		if (item2 != null) {
			item2.setId(11002);
			item2.setName("行为风险");
			if(item2.getAlarmPoliceRate()==0) {
				item2.setAlarmPoliceNum(0);
			}else {
				item2.setAlarmPoliceNum((int) (userNum*num2));
			}
			list.add(item2);
		} else {
			item0.setId(11002);
			item0.setName("行为风险");
			list.add(item0);
		}
		// 执法办案风险
		RiskIndexMonitorChild item3 = riskService.caseIndex(dateTime,(int) (userNum*num3));
		if (item3 != null) {
			item3.setId(11003);
			item3.setName("执法风险");
			if(item3.getAlarmPoliceRate()==0) {
				item3.setAlarmPoliceNum(0);
			}else {
				item3.setAlarmPoliceNum((int) (userNum*num3));
			}
			list.add(item3);
		} else {
			item0.setId(11003);
			item0.setName("执法风险");
			list.add(item0);
		}
		// 接警执勤风险
		RiskIndexMonitorChild item4 = riskService.dutyIndex(dateTime,(int) (userNum*num4));
		if (item4 != null) {
			item4.setId(11004);
			item4.setName("接处警风险");
			if(item4.getAlarmPoliceRate()==0) {
				item4.setAlarmPoliceNum(0);
			}else {
				item4.setAlarmPoliceNum((int) (userNum*num4));
			}
			list.add(item4);
		} else {
			item0.setId(11004);
			item0.setName("接处警风险");
			list.add(item0);
		}
		// 警务技能风险
		RiskIndexMonitorChild item5 = riskService.trainIndex(dateTime,(int) (userNum*num5));
		if (item5 != null) {
			item5.setId(11005);
			item5.setName("训练风险");
			if(item5.getAlarmPoliceRate()==0) {
				item5.setAlarmPoliceNum(0);
			}else {
				item5.setAlarmPoliceNum((int) (userNum*num5));
			}
			list.add(item5);
		} else {
			item0.setId(11005);
			item0.setName("训练风险");
			list.add(item0);
		}
		// 社交风险
		RiskIndexMonitorChild item6 = riskService.socialContactIndex(dateTime,(int) (userNum*num6));
		if (item6 != null) {
			item6.setId(11006);
			item6.setName("社交风险");
			if(item6.getAlarmPoliceRate()==0) {
				item6.setAlarmPoliceNum(0);
			}else {
				item6.setAlarmPoliceNum((int) (userNum*num6));
			}
			list.add(item6);
		} else {
			item0.setId(11006);
			item0.setName("社交风险");
			list.add(item0);
		}
		// 家属评价风险
		RiskIndexMonitorChild item7 = riskService.familyEvaluationIndex(dateTime,(int) (userNum*num7));
		if (item7 != null) {
			item7.setId(11007);
			item7.setName("评价风险");
			if(item7.getAlarmPoliceRate()==0) {
				item7.setAlarmPoliceNum(0);
			}else {
				item7.setAlarmPoliceNum((int) (userNum*num7));
			}
			list.add(item7);
		} else {
			item0.setId(11007);
			item0.setName("评价风险");
			list.add(item0);
		}
		// 健康风险
		RiskIndexMonitorChild item8 = riskService.healthIndex(year, dateTime,(int) (userNum*num8));
		if (item8 != null) {
			item8.setId(11008);
			item8.setName("健康风险");
			if(item8.getAlarmPoliceRate()==0) {
				item8.setAlarmPoliceNum(0);
			}else {
				item8.setAlarmPoliceNum((int) (userNum*num8));
			}
			list.add(item8);
		} else {
			item0.setId(11008);
			item0.setName("健康风险");
			list.add(item0);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警员社交风险查询
	@RequestMapping(value = "/risk/social/contact/index/item", method = RequestMethod.GET)
	public ResponseEntity<?> riskSocialContactIndexItem(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "dateTime", required = false) String dateTime,
			@RequestParam(value = "timeType", required = false) Integer timeType) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		if (timeType == null) {//
			timeType = 1;
		}
		if (dateTime == null) {
			dateTime = sd.format(new Date());
		}
		Date currdate = sd.parse(dateTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currdate);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		String lastDateTime = sd.format(calendar.getTime());
		String lastMonthTime = DateUtils.lastMonthTime();
		// 警员社交风险查询
		RiskSocialContact item = riskService.riskSocialContactIndexItem(policeId, dateTime, lastMonthTime, timeType);
		if (item != null) {
			List<ScreenDoubeChart> list = new ArrayList<ScreenDoubeChart>();
			// 上个月警员社交风险查询
			RiskSocialContact item2 = riskService.riskSocialContactIndexItem(policeId, lastDateTime, lastMonthTime, 2);
			ScreenDoubeChart itemChart2 = new ScreenDoubeChart();
			itemChart2.setId(1);
			itemChart2.setName("上月");
			if (item2 != null) {
				itemChart2.setValue(item2.getIndexNum());
			} else {
				itemChart2.setValue(0.0);
			}
			list.add(itemChart2);
			ScreenDoubeChart itemChart1 = new ScreenDoubeChart();
			itemChart1.setId(2);
			itemChart1.setName("本月");
			itemChart1.setValue(item.getIndexNum());
			list.add(itemChart1);
			item.setList(list);
			// 社交详情记录
			List<RiskSocialContactRecord> recordList = riskService.riskSocialContactRecordList(item.getId());
			item.setRecordList(recordList);
		} else {
			item = new RiskSocialContact();
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 半年内社交风险指数
	@RequestMapping(value = "/risk/social/contact/index/chart", method = RequestMethod.GET)
	public ResponseEntity<?> riskSocialContactIndexChart(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		// 半年内社交风险指数
		List<ScreenDoubeChart> list = riskService.riskSocialContactIndexChart(policeId, "risk_social_contact");
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	@GetMapping("/risk/conduct/bureau/role/index")
	public ResponseEntity<?> riskConductBureauRole(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "dateTime", required = false) String dateTime,
			@RequestParam(value = "timeType", required = false) Integer timeType) throws ParseException {
		DataListReturn dlr = new DataListReturn();
		String lastMonthTime = DateUtils.lastMonthTime();
		if (timeType == null) {// 1总计2月份
			timeType = 1;
		}
		if (dateTime == null) {
			dateTime = sd.format(new Date());
		}
		Date currdate = sd.parse(dateTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currdate);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		String lastDateTime = sd.format(calendar.getTime());
		// 警员局规计分风险查询
		RiskConductBureauRule item = riskService.riskConductBureauRuleIndexItem(policeId, dateTime, lastMonthTime,
				timeType);
		if (item != null) {
			List<ScreenDoubeChart> list = new ArrayList<ScreenDoubeChart>();
			// 上个月警员局规计分风险查询
			RiskConductBureauRule item2 = riskService.riskConductBureauRuleIndexItem(policeId, lastDateTime,
					lastMonthTime, 2);
			ScreenDoubeChart itemChart2 = new ScreenDoubeChart();
			itemChart2.setId(1);
			itemChart2.setName("上月");
			if (item2 != null) {
				itemChart2.setValue(item2.getIndexNum());
			} else {
				itemChart2.setValue(0.0);
			}
			list.add(itemChart2);
			// 本月警员局规计分风险查询
			RiskConductBureauRule item3 = riskService.riskConductBureauRuleIndexItem(policeId, dateTime, lastMonthTime,
					2);
			ScreenDoubeChart itemChart1 = new ScreenDoubeChart();
			itemChart1.setId(2);
			itemChart1.setName("本月");
			if (item3 != null) {
				itemChart1.setValue(item3.getIndexNum());
			} else {
				itemChart1.setValue(0.0);
			}
			list.add(itemChart1);
			item.setList(list);
		} else {
			item = new RiskConductBureauRule();
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	@GetMapping("/risk/conduct/bureau/role/chart")
	public ResponseEntity<?> riskConductBureauRoleChart(
			@RequestParam(value = "policeId", required = false) String policeId) {
		DataListReturn dlr = new DataListReturn();
		// 警员局规计分风险指数
		List<ScreenDoubeChart> list = riskService.riskConductBureauRoleChart(policeId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	@GetMapping("/risk/conduct/bureau/role/record/list")
	public ResponseEntity<?> riskConductBureauRoleRecordList(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "dateTime", required = false) String dateTime,
			@RequestParam(value = "timeType", required = false) Integer timeType) throws ParseException {
		DataListReturn dlr = new DataListReturn();
		String lastMonthTime = DateUtils.lastMonthTime();
		if (timeType == null) {// 1总计2月份
			timeType = 1;
		}
		if (dateTime == null) {
			dateTime = sd.format(new Date());
		}
		// 警员局规计分数据列表查询

		List<RiskConductBureauRuleRecord> list = riskService.findRiskConductBureauRuleRecord(policeId, dateTime,
				lastMonthTime, timeType);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警员警务技能综合训练数据列表查询
	@RequestMapping(value = "/risk/train/physical/record/list", method = RequestMethod.GET)
	public ResponseEntity<?> riskTrainPhysicalRecordList(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "dateTime", required = false) String dateTime,
			@RequestParam(value = "timeType", required = false) Integer timeType) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		String lastMonthTime = DateUtils.lastMonthTime();
		if (timeType == null) {// 1总计2月份
			timeType = 1;
		}
		if (dateTime == null) {
			dateTime = sd.format(new Date());
		}
		// 警员警务技能综合训练数据列表查询
		List<RiskTrainPhysicalRecord> list = trainService.riskTrainPhysicalRecordList(policeId, dateTime, lastMonthTime,
				timeType);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				boolean flag = true;
				List<RiskTrainPhysicalAchievementDetails> projectList = trainService
						.riskTrainSignInProjectList(list.get(i).getId(), null, policeId, null);
				list.get(i).setTrainList(projectList);
				for (int j= 0; j < projectList.size(); j++) {
					if (projectList.get(j).getAchievementStr() != null){
						flag = false;
						break;
					}
				}
				list.get(i).setAchievementGradeStr(flag ? "未参加" : list.get(i).getAchievementGradeStr());
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警员警务技能枪械数据列表查询
	@RequestMapping(value = "/risk/train/firearm/record/list", method = RequestMethod.GET)
	public ResponseEntity<?> riskTrainFirearmRecordList(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "dateTime", required = false) String dateTime,
			@RequestParam(value = "timeType", required = false) Integer timeType) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		String lastMonthTime = DateUtils.lastMonthTime();
		if (timeType == null) {// 1总计2月份
			timeType = 1;
		}
		if (dateTime == null) {
			dateTime = sd.format(new Date());
		}
		// 警员警务技能枪械数据列表查询
		List<RiskTrainFirearmRecord> list = trainService.riskTrainFirearmRecordList(policeId, dateTime, lastMonthTime,
				timeType);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

}

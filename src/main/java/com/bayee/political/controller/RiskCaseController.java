package com.bayee.political.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.bayee.political.domain.*;
import com.bayee.political.service.*;
import com.bayee.political.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.StatusCode;
import com.taobao.api.ApiException;

@Controller
public class RiskCaseController extends BaseController {

	@Autowired
	private RiskCaseAbilityService riskCaseAbilityService;

	@Autowired
	private RiskCaseLawEnforcementService riskCaseLawEnforcementService;

	@Autowired
	private RiskCaseTestRecordService riskCaseTestRecordService;

	@Autowired
	private RiskFamilyEvaluationService riskFamilyEvaluationService;

	@Autowired
	private RiskCaseLawEnforcementRecordService riskCaseLawEnforcementRecordService;

	@Autowired
	private RiskConductVisitService riskConductVisitService;

	@Autowired
	private RiskConductVisitRecordService riskConductVisitRecordService;

	@Autowired
	private RiskConductTrafficViolationService riskConductTrafficViolationService;

	@Autowired
	private RiskConductTrafficViolationRecordService riskConductTrafficViolationRecordService;

	@Autowired
	private RiskConductService riskConductService;

	@Autowired
	private RiskService riskService;

	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");

	// 警员执法能力风险查询
	@RequestMapping(value = "/risk/case/Ability/item", method = RequestMethod.GET)
	public ResponseEntity<?> riskCaseAbilityItem(@RequestParam(value = "policeId", required = false) String policeId,
												 @RequestParam(value = "dateTime", required = false) String dateTime,
												 @RequestParam(value = "timeType", required = false) Integer timeType)
			throws ApiException, ParseException {
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
		// 警员执法能力风险查询
		RiskCaseAbility item = riskService.riskCaseAbilityIndexItem(policeId, dateTime, lastMonthTime, timeType);
		if (item != null) {
			List<ScreenDoubeChart> list = new ArrayList<ScreenDoubeChart>();
			// 上个月警员执法能力风险查询
			RiskCaseAbility item2 = riskService.riskCaseAbilityIndexItem(policeId, lastDateTime, lastMonthTime, 2);
			ScreenDoubeChart itemChart2 = new ScreenDoubeChart();
			itemChart2.setId(1);
			itemChart2.setName("上月");
			if (item2 != null) {
				itemChart2.setValue(item2.getIndexNum());
			} else {
				itemChart2.setValue(0.0);
			}
			list.add(itemChart2);
			// 本月警员执法能力风险查询
			RiskCaseAbility item3 = riskService.riskCaseAbilityIndexItem(policeId, dateTime, lastMonthTime, 2);
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
			item = new RiskCaseAbility();
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 执法能力风险指数图例
	@RequestMapping(value = "/risk/case/Ability/chart", method = RequestMethod.GET)
	public ResponseEntity<?> riskCaseAbilityChart(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		// 半年内接警执勤风险指数
		List<ScreenDoubeChart> list = riskCaseAbilityService.riskCaseAbilityChart(policeId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警员执法管理风险查询
	@RequestMapping(value = "/risk/case/law/enforcement/item", method = RequestMethod.GET)
	public ResponseEntity<?> riskCaseLawEnforcementItem(
			@RequestParam(value = "policeId", required = false) String policeId,
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
		// 警员执法管理风险查询
		RiskCaseLawEnforcement item = riskService.riskCaseLawEnforcementIndexItem(policeId, dateTime, lastMonthTime,
				timeType);
		if (item != null) {
			// 警员执法管理数据列表查询
			List<RiskCaseLawEnforcementRecord> rList = riskService.riskCaseLawEnforcementRecordList(policeId, dateTime,
					lastMonthTime, timeType);
			if (rList.size() > 0) {
				item.setIsDisplay(1);
			} else {
				item.setIsDisplay(0);
			}
			List<ScreenDoubeChart> list = new ArrayList<ScreenDoubeChart>();
			// 上个月警员执法管理风险查询
			RiskCaseLawEnforcement item2 = riskService.riskCaseLawEnforcementIndexItem(policeId, lastDateTime,
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
			// 本月警员执法管理风险查询
			RiskCaseLawEnforcement item3 = riskService.riskCaseLawEnforcementIndexItem(policeId, dateTime,
					lastMonthTime, 2);
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
			item = new RiskCaseLawEnforcement();
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 执法管理风险指数图例
	@RequestMapping(value = "/risk/case/law/enforcement/chart", method = RequestMethod.GET)
	public ResponseEntity<?> riskCaseLawEnforcementChart(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		// 半年内接警执勤风险指数
		List<ScreenDoubeChart> list = riskCaseLawEnforcementService.riskCaseLawEnforcementChart(policeId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警员执法考试风险查询
	@RequestMapping(value = "/risk/case/Test/item", method = RequestMethod.GET)
	public ResponseEntity<?> riskCaseTestItem(@RequestParam(value = "policeId", required = false) String policeId,
											  @RequestParam(value = "dateTime", required = false) String dateTime,
											  @RequestParam(value = "timeType", required = false) Integer timeType)
			throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		String lastMonthTime = DateUtils.lastMonthTime();
		if (timeType == null) {
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
//		int month = Integer.valueOf(dateTime.substring(5, 7));
//		int year = Integer.valueOf(dateTime.substring(0, 4));
		// 警员执法考试风险查询
		RiskCaseTest item = riskService.riskCaseTestIndexItem(policeId, dateTime, lastMonthTime, timeType);
		if (item != null) {
			// 警员执法考试数据列表查询
			List<RiskCaseTestRecord> rList = riskService.riskCaseTestRecordList(policeId, dateTime, lastMonthTime,
					timeType);
			if (rList.size() > 0) {
				item.setIsDisplay(1);
			} else {
				item.setIsDisplay(0);
			}
			if (item.getCumulativeNum() != null) {
				if (item.getPassNum() != null) {
					item.setFailNum(item.getCumulativeNum() - item.getPassNum());
				} else {
					item.setFailNum(item.getCumulativeNum());
				}
			}
			List<ScreenDoubeChart> list = new ArrayList<ScreenDoubeChart>();
			// 上个月警员执法考试风险查询
			RiskCaseTest item2 = riskService.riskCaseTestIndexItem(policeId, lastDateTime, lastMonthTime, 2);
			ScreenDoubeChart itemChart2 = new ScreenDoubeChart();
			itemChart2.setId(1);
			itemChart2.setName("上期");
			if (item2 != null) {
				itemChart2.setValue(item2.getIndexNum());
			} else {
				itemChart2.setValue(0.0);
			}
			list.add(itemChart2);
			// 警员执法考试风险查询
			RiskCaseTest item3 = riskService.riskCaseTestIndexItem(policeId, dateTime, lastMonthTime, 2);
			ScreenDoubeChart itemChart1 = new ScreenDoubeChart();
			itemChart1.setId(2);
			itemChart1.setName("本期");
			if (item3 != null) {
				itemChart1.setValue(item3.getIndexNum());
			} else {
				itemChart1.setValue(0.0);
			}
			list.add(itemChart1);
			item.setList(list);
		} else {
			item = new RiskCaseTest();
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 执法考试风险指数图例
	@RequestMapping(value = "/risk/case/test/index/chart", method = RequestMethod.GET)
	public ResponseEntity<?> riskTestIndexChart(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		// 半年内接警执勤风险指数
		List<ScreenDoubeChart> list = riskCaseTestRecordService.riskCaseTestChart(policeId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警员家属评价风险查询
	@RequestMapping(value = "/risk/family/evaluation/item", method = RequestMethod.GET)
	public ResponseEntity<?> riskFamilyEvaluationItem(
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
		// 警员执法办案风险指数查询
		RiskFamilyEvaluation item = riskFamilyEvaluationService.riskFamilyEvaluationItem(policeId, dateTime);
		if (item != null) {
			List<ScreenDoubeChart> list = new ArrayList<ScreenDoubeChart>();
			// 上个月警员接警执勤指数查询
			RiskFamilyEvaluation item2 = riskFamilyEvaluationService.riskFamilyEvaluationItem(policeId, lastDateTime);
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
		} else {
			item = new RiskFamilyEvaluation();
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 家属评价风险指数图例
	@RequestMapping(value = "/risk/family/evaluation/chart", method = RequestMethod.GET)
	public ResponseEntity<?> riskFamilyEvaluationChart(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		// 半年内接警执勤风险指数
		List<ScreenDoubeChart> list = riskFamilyEvaluationService.riskFamilyEvaluationChart(policeId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警员执法管理扣分详情
	@RequestMapping(value = "/risk/law/enforcement/list", method = RequestMethod.GET)
	public ResponseEntity<?> riskDutyRecordItem(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		if (dateTime == null) {
			dateTime = sd.format(new Date());
		}
		// 警员执法管理扣分详情查询
		List<RiskCaseLawEnforcementRecord> list = riskCaseLawEnforcementRecordService
				.riskCaseLawEnforcementRecordList(policeId, dateTime);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警员信访投诉风险查询
	@RequestMapping(value = "/risk/conduct/visit/item", method = RequestMethod.GET)
	public ResponseEntity<?> riskConductVisitItem(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "dateTime", required = false) String dateTime,
			@RequestParam(value = "timeType", required = false) Integer timeType) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		if (timeType == null) {
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
		// 警员执法办案风险指数查询
		RiskConductVisit item = riskConductVisitService.riskConductVisitItem(policeId, dateTime, lastMonthTime, timeType);
		if (item != null) {
			List<RiskDutyDealPoliceRecord> rList = riskService.riskDutyRecordList(policeId, dateTime, lastMonthTime,
					timeType);
			if (rList.size() > 0) {
				item.setIsDisplay(1);
			} else {
				item.setIsDisplay(0);
			}
			List<ScreenDoubeChart> list = new ArrayList<ScreenDoubeChart>();
			// 上个月警员接警执勤指数查询
			RiskConductVisit item2 = riskConductVisitService.riskConductVisitItem(policeId, lastDateTime, lastMonthTime, 2);
			ScreenDoubeChart itemChart2 = new ScreenDoubeChart();
			itemChart2.setId(1);
			itemChart2.setName("上月");
			if (item2 != null) {
				itemChart2.setValue(item2.getIndexNum());
			} else {
				itemChart2.setValue(0.0);
			}
			list.add(itemChart2);
			RiskConductVisit item3 = riskConductVisitService.riskConductVisitItem(policeId, dateTime, lastMonthTime, 2);
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
			item = new RiskConductVisit();
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 信访投诉风险指数图例
	@RequestMapping(value = "/risk/conduct/visit/chart", method = RequestMethod.GET)
	public ResponseEntity<?> riskConductVisitChart(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		// 半年内接警执勤风险指数
		List<ScreenDoubeChart> list = riskConductVisitService.riskConductVisitChart(policeId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警员信访投诉扣分详情
	@RequestMapping(value = "/risk/conduct/visit/list", method = RequestMethod.GET)
	public ResponseEntity<?> riskConductVisitList(@RequestParam(value = "policeId", required = false) String policeId,
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
		// 警员信访投诉扣分详情
		List<RiskConductVisitRecord> list = riskConductVisitRecordService.riskConductVisitRecordList(policeId,
				dateTime,lastMonthTime,timeType);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警员交通违章风险查询
	@RequestMapping(value = "/risk/traffic/violation/item", method = RequestMethod.GET)
	public ResponseEntity<?> riskTrafficViolationItem(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		if (dateTime == null) {
			dateTime = sd.format(new Date());
		}
		Date currdate = sd.parse(dateTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currdate);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		String lastDateTime = sd.format(calendar.getTime());
		// 警员执法办案风险指数查询
		RiskConductTrafficViolation item = riskConductTrafficViolationService.riskConductTrafficViolationItem(policeId,
				dateTime);
		if (item != null) {
			List<ScreenDoubeChart> list = new ArrayList<ScreenDoubeChart>();
			// 上个月警员接警执勤指数查询
			RiskConductTrafficViolation item2 = riskConductTrafficViolationService
					.riskConductTrafficViolationItem(policeId, lastDateTime);
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
		} else {
			item = new RiskConductTrafficViolation();
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 交通违章风险指数图例
	@RequestMapping(value = "/risk/traffic/violation/chart", method = RequestMethod.GET)
	public ResponseEntity<?> riskTrafficViolationChart(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		// 半年内接警执勤风险指数
		List<ScreenDoubeChart> list = riskConductTrafficViolationService.riskConductTrafficViolationChart(policeId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警员交通违章扣分详情
	@RequestMapping(value = "/risk/traffic/violation/list", method = RequestMethod.GET)
	public ResponseEntity<?> riskTrafficViolationList(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		if (dateTime == null) {
			dateTime = sd.format(new Date());
		}
		// 警员执法管理扣分详情查询
		List<RiskConductTrafficViolationRecord> list = riskConductTrafficViolationRecordService
				.riskConductTrafficViolationRecordList(policeId, dateTime);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警员行为规范风险查询
	@RequestMapping(value = "/risk/conduct/item", method = RequestMethod.GET)
	public ResponseEntity<?> riskConductItem(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		if (dateTime == null) {
			dateTime = sd.format(new Date());
		}
		Date currdate = sd.parse(dateTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currdate);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		String lastDateTime = sd.format(calendar.getTime());
		// 警员执法办案风险指数查询
		RiskConduct item = riskConductService.riskConductItem(policeId, dateTime);
		if (item != null) {
			List<ScreenDoubeChart> list = new ArrayList<ScreenDoubeChart>();
			// 上个月警员接警执勤指数查询
			RiskConduct item2 = riskConductService.riskConductItem(policeId, lastDateTime);
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
		} else {
			item = new RiskConduct();
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 行为规范指数图例
	@RequestMapping(value = "/risk/conduct/chart", method = RequestMethod.GET)
	public ResponseEntity<?> riskConductChart(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException, ParseException {
		DataListReturn dlr = new DataListReturn();
		// 半年内接警执勤风险指数
		List<ScreenDoubeChart> list = riskConductService.riskConductChart(policeId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

}

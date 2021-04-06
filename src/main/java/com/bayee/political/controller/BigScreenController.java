package com.bayee.political.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bayee.political.domain.BigScreenPoliceForces;
import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.LeaveChart;
import com.bayee.political.domain.ScreenAlarmAnalysis;
import com.bayee.political.domain.ScreenChart;
import com.bayee.political.domain.ScreenColumnList;
import com.bayee.political.domain.ScreenDoubeChart;
import com.bayee.political.domain.ScreenEvaluationStatistics;
import com.bayee.political.domain.ScreenLeaveVacationChart;
import com.bayee.political.domain.SreenLeaveOverDutyStatistics;
import com.bayee.political.domain.TrainMatch;
import com.bayee.political.domain.TrainRank;
import com.bayee.political.domain.User;
import com.bayee.political.service.BigScreenService;
import com.bayee.political.service.CalculationService;
import com.bayee.political.service.ClockService;
import com.bayee.political.service.TrainMatchService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.StatusCode;
import com.taobao.api.ApiException;

/**
 * @author shentuqiwei
 * @version 2021年2月24日 上午11:19:32
 */
@Controller
@Component
public class BigScreenController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private CalculationService calculationService;

	@Autowired
	private BigScreenService bigScreenService;

	@Autowired
	private TrainMatchService trainMatchService;

	@Autowired
	private ClockService clockService;

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	SimpleDateFormat sdfs = new SimpleDateFormat("yyyy年MM月dd日");

	String currentYearStr = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));

	DecimalFormat df = new DecimalFormat("#.00");

	// 全局年休假使用率
	@RequestMapping(value = "/screen/leave/annual/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> screenLeaveAnnualStatistics() throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 全局年休假统计
		List<ScreenDoubeChart> list = bigScreenService.screenLeaveAnnualStatisticsList();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 各所年休假使用率
	@RequestMapping(value = "/screen/station/leave/annual/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> screenLStationeaveAnnualStatistics() throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 各所年休假使用率
		List<ScreenDoubeChart> list = bigScreenService.screenLStationeaveAnnualStatisticsList();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 每月请假趋势
	@RequestMapping(value = "/screen/leave/vacation/chart", method = RequestMethod.GET)
	public ResponseEntity<?> leaveVacationChartList() throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 每月请假总人数查询（api）
		List<ScreenChart> leavetotalNumList = bigScreenService.screenLeavetotalNumList();
		// 每月休年假人数查询（api）
		List<ScreenChart> leaveAnnualNumList = bigScreenService.screenLAnnualNumList();
		// 每月病假人数查询（api）
		List<ScreenChart> leaveOffNumList = bigScreenService.screenLOffNumList();
		// 每月事假人数查询（api）
		List<ScreenChart> leaveNumList = bigScreenService.screenLNumList();
		ScreenLeaveVacationChart leaveLine = new ScreenLeaveVacationChart();
		leaveLine.setLeavetotalNumList(leavetotalNumList);
		leaveLine.setLeaveAnnualNumList(leaveAnnualNumList);
		leaveLine.setLeaveOffNumList(leaveOffNumList);
		leaveLine.setLeaveNumList(leaveNumList);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(leaveLine);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 请假类型占比
	@RequestMapping(value = "/screen/leave/type/list", method = RequestMethod.GET)
	public ResponseEntity<?> screenLeaveTypeList() {
		DataListReturn dlr = new DataListReturn();
		List<CalculationChart> list = bigScreenService.screenLeaveTypeList();
		List<ScreenChart> list2 = new ArrayList<ScreenChart>();
		for (int i = 0; i < list.size(); i++) {
			ScreenChart item = new ScreenChart();
			item.setName(list.get(i).getName());
			item.setValue(list.get(i).getNum());
			list2.add(item);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list2);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 加值班统计分析
	@RequestMapping(value = "/screen/leave/overtime/duty/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> leaveOvertimeDutyStatistics(
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		SreenLeaveOverDutyStatistics item = new SreenLeaveOverDutyStatistics();
		// 民警总数
		int policeNum = userService.userListCount(departmentId, null, null, null, null);
		item.setPoliceNum(policeNum);
		// 加班折线图查询（api）
		List<LeaveChart> leaveOvertimeList = clockService.leaveOvertimeList(departmentId);
		List<LeaveChart> overtimeList = new ArrayList<LeaveChart>();
		overtimeList.add(leaveOvertimeList.get(leaveOvertimeList.size() - 2));
		overtimeList.add(leaveOvertimeList.get(leaveOvertimeList.size() - 1));
		List<ScreenDoubeChart> list2 = new ArrayList<ScreenDoubeChart>();
		for (int i = 0; i < overtimeList.size(); i++) {
			ScreenDoubeChart sitem = new ScreenDoubeChart();
			if (i == 0) {
				sitem.setName("上月");
			} else {
				sitem.setName("本月");
			}
			sitem.setValue(overtimeList.get(i).getNum());
			list2.add(sitem);
		}
		item.setOvertimeList(list2);
		item.setOverTime(leaveOvertimeList.get(leaveOvertimeList.size() - 1).getNum());
		double numDou = policeNum;
		double nowNum = leaveOvertimeList.get(leaveOvertimeList.size() - 1).getNum();
		if (policeNum > 0) {
			item.setOverTimeAve(Double.valueOf(df.format(nowNum / numDou)));
		} else {
			item.setOverTimeAve(0.0);
		}
		// 值班折线图查询（api）
		List<LeaveChart> leaveDutyList = clockService.leaveDutyList(departmentId);
		for (int i = 0; i < leaveDutyList.size(); i++) {
			leaveDutyList.get(i).setNum(Double.valueOf(df.format(leaveDutyList.get(i).getNum() / 24)));
		}
		List<LeaveChart> dutyList = new ArrayList<LeaveChart>();
		dutyList.add(leaveDutyList.get(leaveDutyList.size() - 2));
		dutyList.add(leaveDutyList.get(leaveDutyList.size() - 1));
		List<ScreenDoubeChart> list3 = new ArrayList<ScreenDoubeChart>();
		for (int i = 0; i < dutyList.size(); i++) {
			ScreenDoubeChart sitem = new ScreenDoubeChart();
			if (i == 0) {
				sitem.setName("上月");
			} else {
				sitem.setName("本月");
			}
			sitem.setValue(dutyList.get(i).getNum());
			list3.add(sitem);
		}
		item.setDutyList(list3);
		item.setDutyTime(Double.valueOf(df.format(leaveDutyList.get(leaveDutyList.size() - 1).getNum())));
		double numDutyDou = policeNum;
		double nowDutyNum = leaveDutyList.get(leaveDutyList.size() - 1).getNum();
		if (policeNum > 0) {
			item.setDutyTimeAve(Double.valueOf(df.format(nowDutyNum / numDutyDou)));
		} else {
			item.setDutyTimeAve(0.0);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 每月加班时长趋势
	@RequestMapping(value = "/screen/over/time/line/chart", method = RequestMethod.GET)
	public ResponseEntity<?> screenOverTimeList() throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 每月加班时长趋势
		List<LeaveChart> list = bigScreenService.screenOverTimeList();
		for (int i = 0; i < list.size(); i++) {
			int num = Integer.valueOf(list.get(i).getName().substring(5, 7));
			list.get(i).setName(monthName(num));
		}
		List<ScreenDoubeChart> list2 = new ArrayList<ScreenDoubeChart>();
		for (int i = 0; i < list.size(); i++) {
			ScreenDoubeChart item = new ScreenDoubeChart();
			item.setName(list.get(i).getName());
			item.setValue(list.get(i).getNum());
			list2.add(item);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list2);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 每月值班时长趋势
	@RequestMapping(value = "/screen/duty/line/chart", method = RequestMethod.GET)
	public ResponseEntity<?> screenDutyLineChartList() throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 每月值班时长趋势
		List<LeaveChart> list = bigScreenService.screenDutyLineChartList();
		for (int i = 0; i < list.size(); i++) {
			int num = Integer.valueOf(list.get(i).getName().substring(5, 7));
			list.get(i).setName(monthName(num));
		}
		List<ScreenDoubeChart> list2 = new ArrayList<ScreenDoubeChart>();
		for (int i = 0; i < list.size(); i++) {
			ScreenDoubeChart item = new ScreenDoubeChart();
			item.setName(list.get(i).getName());
			item.setValue(list.get(i).getNum());
			list2.add(item);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list2);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	private String monthName(int num) {
		String strMonth = null;
		if (num == 1) {
			strMonth = "1月";
		} else if (num == 2) {
			strMonth = "2月";
		} else if (num == 3) {
			strMonth = "3月";
		} else if (num == 4) {
			strMonth = "4月";
		} else if (num == 5) {
			strMonth = "5月";
		} else if (num == 6) {
			strMonth = "6月";
		} else if (num == 7) {
			strMonth = "7月";
		} else if (num == 8) {
			strMonth = "8月";
		} else if (num == 9) {
			strMonth = "9月";
		} else if (num == 10) {
			strMonth = "10月";
		} else if (num == 11) {
			strMonth = "11月";
		} else if (num == 12) {
			strMonth = "12月";
		}
		return strMonth;
	}

	// 派出所警力数
	@RequestMapping(value = "/screen/police/num/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> columnStatisticsList(
			@RequestParam(value = "policeType", required = false) Integer policeType) {
		if (policeType == null) {
			policeType = 1;
		}
		DataListReturn dlr = new DataListReturn();
		// 实际警力查询(api)
		List<CalculationChart> actualStatisticsList = calculationService.actualStatisticsApiList(policeType);
		int num = 0;
		for (int i = 0; i < actualStatisticsList.size(); i++) {
			num = num + actualStatisticsList.get(i).getNum();
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(num);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 各派出所岗位人数
	@RequestMapping(value = "/screen/calculation/column/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> screenColumnStatisticsList(
			@RequestParam(value = "policeType", required = false) Integer policeType) {
		if (policeType == null) {
			policeType = 1;
		}
		DataListReturn dlr = new DataListReturn();
		ScreenColumnList cNum = new ScreenColumnList();
		// 实际警力查询(api)
		List<CalculationChart> actualStatisticsList = calculationService.actualStatisticsApiList(policeType);
		List<ScreenChart> list2 = new ArrayList<ScreenChart>();
		for (int i = 0; i < actualStatisticsList.size(); i++) {
			ScreenChart item = new ScreenChart();
			item.setName(actualStatisticsList.get(i).getName());
			item.setValue(actualStatisticsList.get(i).getNum());
			list2.add(item);
		}
		cNum.setActualList(list2);
		// 所需警力查询(api)
		List<CalculationChart> getStatisticsList = calculationService.getStatisticsApiList(policeType);
		List<ScreenChart> list3 = new ArrayList<ScreenChart>();
		for (int i = 0; i < getStatisticsList.size(); i++) {
			ScreenChart item = new ScreenChart();
			item.setName(getStatisticsList.get(i).getName());
			item.setValue(getStatisticsList.get(i).getNum());
			list3.add(item);
		}
		cNum.setGetList(list3);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(cNum);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 各派出所岗位比例
	@RequestMapping(value = "/screen/police/station/post/list", method = RequestMethod.GET)
	public ResponseEntity<?> screenPoliceStationPostList() {
		DataListReturn dlr = new DataListReturn();
		// 各派出所岗位比例
		List<CalculationChart> list = bigScreenService.screenPoliceStationPostList();
		List<ScreenChart> list3 = new ArrayList<ScreenChart>();
		for (int i = 0; i < list.size(); i++) {
			ScreenChart item = new ScreenChart();
			item.setName(list.get(i).getName());
			item.setValue(list.get(i).getNum());
			list3.add(item);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list3);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 各派出所警力分布
	@RequestMapping(value = "/screen/police/station/forces/list", method = RequestMethod.GET)
	public ResponseEntity<?> screenPoliceStationForcesList() {
		DataListReturn dlr = new DataListReturn();
		BigScreenPoliceForces item = new BigScreenPoliceForces();
		// 各派出所警力分布(打击岗)
		List<CalculationChart> strikeList = bigScreenService.screenPoliceStationForcesList(1);
		List<ScreenChart> list1 = new ArrayList<ScreenChart>();
		for (int i = 0; i < strikeList.size(); i++) {
			ScreenChart sitem = new ScreenChart();
			sitem.setName(strikeList.get(i).getName());
			sitem.setValue(strikeList.get(i).getNum());
			list1.add(sitem);
		}
		item.setStrikeList(list1);
		// 各派出所警力分布(基础防控)
		List<CalculationChart> basicList = bigScreenService.screenPoliceStationForcesList(2);
		List<ScreenChart> list2 = new ArrayList<ScreenChart>();
		for (int i = 0; i < basicList.size(); i++) {
			ScreenChart sitem = new ScreenChart();
			sitem.setName(basicList.get(i).getName());
			sitem.setValue(basicList.get(i).getNum());
			list2.add(sitem);
		}
		item.setBasicList(list2);
		// 各派出所警力分布(综合岗)
		List<CalculationChart> comprehensiveList = bigScreenService.screenPoliceStationForcesList(3);
		List<ScreenChart> list3 = new ArrayList<ScreenChart>();
		for (int i = 0; i < comprehensiveList.size(); i++) {
			ScreenChart sitem = new ScreenChart();
			sitem.setName(comprehensiveList.get(i).getName());
			sitem.setValue(comprehensiveList.get(i).getNum());
			list3.add(sitem);
		}
		item.setComprehensiveList(list3);
		// 各派出所警力分布(户籍内勤)
		List<CalculationChart> backOfficeList = bigScreenService.screenPoliceStationForcesList(4);
		List<ScreenChart> list4 = new ArrayList<ScreenChart>();
		for (int i = 0; i < backOfficeList.size(); i++) {
			ScreenChart sitem = new ScreenChart();
			sitem.setName(backOfficeList.get(i).getName());
			sitem.setValue(backOfficeList.get(i).getNum());
			list4.add(sitem);
		}
		item.setBackOfficeList(list4);
		// 各派出所警力分布(所领导)
		List<CalculationChart> leaderList = bigScreenService.screenPoliceStationForcesList(5);
		List<ScreenChart> list5 = new ArrayList<ScreenChart>();
		for (int i = 0; i < leaderList.size(); i++) {
			ScreenChart sitem = new ScreenChart();
			sitem.setName(leaderList.get(i).getName());
			sitem.setValue(leaderList.get(i).getNum());
			list5.add(sitem);
		}
		item.setLeaderList(list5);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 训练成绩合格率前5名
	@RequestMapping(value = "/screen/train/newest/rank/list", method = RequestMethod.GET)
	public ResponseEntity<?> screenTrainNewestRankList() throws ApiException {
		DataListReturn dlr = new DataListReturn();
		List<TrainRank> rankList = bigScreenService.screenTrainNewestRankList();
		if (rankList.size() > 0) {
			int num = 1;
			for (int i = 0; i < rankList.size(); i++) {
				rankList.get(i).setRankId(num);
				num = num + 1;
			}
		}
		dlr.setResult(rankList);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);

	}

	// 比赛成绩最新前5名
	@RequestMapping(value = "/screen/match/newest/rank/list", method = RequestMethod.GET)
	public ResponseEntity<?> screenMatchNewestRankList() throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 最新赛事详情查询
		TrainMatch fitem = bigScreenService.screenMatchNewest();
		if (fitem != null) {
			String sortStr = null;
			if (fitem.getSort() == 1) {// 升序
				sortStr = "asc";
			} else if (fitem.getSort() == 2) {// 降序
				sortStr = "desc";
			}
			// 赛事更多榜单排名榜查询
			List<TrainRank> rankList = trainMatchService.matchMyEntryMoreRankList(fitem.getId(), sortStr);
			if (rankList.size() > 0) {
				for (int i = 0; i < rankList.size(); i++) {
					// 根据policeId查询部门
					User user = userService.policeItem(rankList.get(i).getPoliceId(), null, null);
					rankList.get(i).setDepartmentName(user.getDepartmentName());
				}
				if (rankList.size() <= 5) {
					dlr.setResult(rankList);
				} else {
					List<TrainRank> list = new ArrayList<TrainRank>();
					int num = 1;
					for (int j = 0; j < rankList.size(); j++) {
						list.add(rankList.get(j));
						num = num + 1;
						if (num > 5) {
							break;
						}
					}
					dlr.setResult(list);
				}
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);

	}

	// 智能预警分析
	@RequestMapping(value = "/screen/alarm/analysis", method = RequestMethod.GET)
	public ResponseEntity<?> screenAlarmAnalysis() throws ApiException {
		DataListReturn dlr = new DataListReturn();
		ScreenAlarmAnalysis item = new ScreenAlarmAnalysis();
		// 近12个月记分人数查询
		List<CalculationChart> scoreList = bigScreenService.screenScoreList();
		List<ScreenChart> list2 = new ArrayList<ScreenChart>();
		for (int i = 0; i < scoreList.size(); i++) {
			ScreenChart sitem = new ScreenChart();
			sitem.setName(scoreList.get(i).getName());
			sitem.setValue(scoreList.get(i).getNum());
			list2.add(sitem);
		}
		item.setScoreList(list2);
		item.setMonthScorePersonNum(scoreList.get(scoreList.size() - 1).getNum());
		double currentMonthScoreNum = scoreList.get(scoreList.size() - 1).getNum();
		double lastMonthScoreNum = scoreList.get(scoreList.size() - 2).getNum();
		if (scoreList.get(scoreList.size() - 2).getNum() > 0) {
			double monthScoreRate = (currentMonthScoreNum - lastMonthScoreNum) / lastMonthScoreNum * 100.0;
			item.setMonthScorePersonRate(Double.valueOf(df.format(monthScoreRate)));
		} else {
			item.setMonthScorePersonRate(0);
		}
		// 近12个月预警人数查询
		List<CalculationChart> alarmList = bigScreenService.screenAlarmList();
		List<ScreenChart> list3 = new ArrayList<ScreenChart>();
		for (int i = 0; i < alarmList.size(); i++) {
			ScreenChart sitem = new ScreenChart();
			sitem.setName(alarmList.get(i).getName());
			sitem.setValue(alarmList.get(i).getNum());
			list3.add(sitem);
		}
		item.setAlarmList(list3);
		item.setAlarmScorePersonNum(alarmList.get(alarmList.size() - 1).getNum());
		double currentAlarmScoreNum = alarmList.get(alarmList.size() - 1).getNum();
		double lastAlarmScoreNum = alarmList.get(alarmList.size() - 2).getNum();
		if (alarmList.get(alarmList.size() - 2).getNum() > 0) {
			double alarmScoreRate = (currentAlarmScoreNum - lastAlarmScoreNum) / lastAlarmScoreNum * 100.0;
			item.setAlarmScorePersonRate(Double.valueOf(df.format(alarmScoreRate)));
		} else {
			item.setAlarmScorePersonRate(0);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 计分行为占比
	@RequestMapping(value = "/screen/alarm/type/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> screenAlarmTypeStatistics() throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 计分行为占比统计
		List<CalculationChart> list = bigScreenService.screenAlarmTypeStatisticsList();
		List<ScreenChart> list2 = new ArrayList<ScreenChart>();
		for (int i = 0; i < list.size(); i++) {
			ScreenChart item = new ScreenChart();
			item.setName(list.get(i).getName());
			item.setValue(list.get(i).getNum());
			list2.add(item);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list2);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 每月最高负分
	@RequestMapping(value = "/screen/alarm/max/negative/score/list", method = RequestMethod.GET)
	public ResponseEntity<?> screenAlarmMaxNegativeScoreList() throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 每月最高负分
		List<LeaveChart> list = bigScreenService.screenAlarmMaxNegativeScoreList();
		List<ScreenDoubeChart> list2 = new ArrayList<ScreenDoubeChart>();
		for (int i = 0; i < list.size(); i++) {
			ScreenDoubeChart item = new ScreenDoubeChart();
			item.setName(list.get(i).getName());
			item.setValue(list.get(i).getNum());
			list2.add(item);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list2);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 评价任务数量、评价人次统计
	@RequestMapping(value = "/screen/evaluation/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> screenEvaluationStatistics() throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 评价任务数量、评价人次统计
		ScreenEvaluationStatistics item = bigScreenService.screenEvaluationStatistics();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 评价任务类型统计
	@RequestMapping(value = "/screen/evaluation/type/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> screenEvaluationTypeStatistics() throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 评价任务类型统计
		List<CalculationChart> list = bigScreenService.screenEvaluationTypeList();
		List<ScreenChart> list2 = new ArrayList<ScreenChart>();
		for (int i = 0; i < list.size(); i++) {
			ScreenChart item = new ScreenChart();
			item.setName(list.get(i).getName());
			item.setValue(list.get(i).getNum());
			list2.add(item);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list2);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}
}

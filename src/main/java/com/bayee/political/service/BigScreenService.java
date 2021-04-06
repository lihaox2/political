package com.bayee.political.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.LeaveChart;
import com.bayee.political.domain.ScreenChart;
import com.bayee.political.domain.ScreenDoubeChart;
import com.bayee.political.domain.ScreenEvaluationStatistics;
import com.bayee.political.domain.TrainMatch;
import com.bayee.political.domain.TrainRank;

/**
 * @author shentuqiwei
 * @version 2021年2月24日 上午11:21:58
 */
@Service
public interface BigScreenService {

	// 评价任务类型统计
	List<CalculationChart> screenEvaluationTypeList();

	// 评价任务数量、评价人次统计
	ScreenEvaluationStatistics screenEvaluationStatistics();

	// 计分行为占比统计
	List<CalculationChart> screenAlarmTypeStatisticsList();

	// 每月最高负分
	List<LeaveChart> screenAlarmMaxNegativeScoreList();

	// 近12个月记分人数查询
	List<CalculationChart> screenScoreList();

	// 近12个月预警人数查询
	List<CalculationChart> screenAlarmList();

	// 各派出所岗位比例
	List<CalculationChart> screenPoliceStationPostList();

	// 各派出所警力分布
	List<CalculationChart> screenPoliceStationForcesList(Integer stationPostId);

	// 最新赛事详情查询
	TrainMatch screenMatchNewest();

	// 训练成绩合格率前5名
	List<TrainRank> screenTrainNewestRankList();

	// 请假类型占比
	List<CalculationChart> screenLeaveTypeList();

	// 每月加班时长趋势
	List<LeaveChart> screenOverTimeList();

	// 每月值班时长趋势
	List<LeaveChart> screenDutyLineChartList();

	// 全局年休假统计
	List<ScreenDoubeChart> screenLeaveAnnualStatisticsList();

	// 各所年休假使用率
	List<ScreenDoubeChart> screenLStationeaveAnnualStatisticsList();

	// 每月请假总人数查询（api）
	List<ScreenChart> screenLeavetotalNumList();

	// 每月休年假人数查询（api）
	List<ScreenChart> screenLAnnualNumList();

	// 每月病假人数查询（api）
	List<ScreenChart> screenLOffNumList();

	// 每月事假人数查询（api）
	List<ScreenChart> screenLNumList();

}

package com.bayee.political.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.LeaveAnnual;
import com.bayee.political.domain.LeaveChart;
import com.bayee.political.domain.ScreenChart;
import com.bayee.political.domain.ScreenDoubeChart;
import com.bayee.political.domain.ScreenEvaluationStatistics;
import com.bayee.political.domain.TrainMatch;
import com.bayee.political.domain.TrainRank;
import com.bayee.political.mapper.AlarmEvaluationMapper;
import com.bayee.political.mapper.CalculationMapper;
import com.bayee.political.mapper.ClockRecordMapper;
import com.bayee.political.mapper.EvaluateTaskMapper;
import com.bayee.political.mapper.LeaveAnnualMapper;
import com.bayee.political.mapper.LeaveProcessMapper;
import com.bayee.political.mapper.TrainMatchMapper;
import com.bayee.political.mapper.TrainPhysicalMapper;
import com.bayee.political.service.BigScreenService;

/**
 * @author shentuqiwei
 * @version 2021年2月24日 上午11:22:17
 */
@Service
public class BigScreenServiceImpl implements BigScreenService {

	@Autowired
	EvaluateTaskMapper evaluateTaskMapper;// 评价任务

	@Autowired
	private AlarmEvaluationMapper alarmEvaluationMapper;// 预警评价表

	@Autowired
	CalculationMapper calculationMapper;// 警力测算

	@Autowired
	TrainMatchMapper trainMatchMapper;// 赛事

	@Autowired
	TrainPhysicalMapper trainPhysicalMapper;// 综合体能

	@Autowired
	LeaveProcessMapper leaveProcessMapper;// 请假数据

	@Autowired
	ClockRecordMapper clockRecordMapper;// 打卡记录

	@Autowired
	LeaveAnnualMapper leaveAnnualMapper;// 年休假

	// 评价任务类型统计
	@Override
	public List<CalculationChart> screenEvaluationTypeList() {
		return evaluateTaskMapper.screenEvaluationTypeList();
	}

	// 评价任务数量、评价人次统计
	@Override
	public ScreenEvaluationStatistics screenEvaluationStatistics() {
		return evaluateTaskMapper.screenEvaluationStatistics();
	}

	// 计分行为占比统计
	@Override
	public List<CalculationChart> screenAlarmTypeStatisticsList() {
		return alarmEvaluationMapper.screenAlarmTypeStatisticsList();
	}

	// 每月最高负分
	@Override
	public List<LeaveChart> screenAlarmMaxNegativeScoreList() {
		return alarmEvaluationMapper.screenAlarmMaxNegativeScoreList();
	}

	// 近12个月记分人数查询
	@Override
	public List<CalculationChart> screenScoreList() {
		return alarmEvaluationMapper.screenScoreList();
	}

	// 近12个月预警人数查询
	@Override
	public List<CalculationChart> screenAlarmList() {
		return alarmEvaluationMapper.screenAlarmList();
	}

	// 各派出所岗位比例
	@Override
	public List<CalculationChart> screenPoliceStationPostList() {
		return calculationMapper.screenPoliceStationPostList();
	}

	// 各派出所警力分布
	@Override
	public List<CalculationChart> screenPoliceStationForcesList(Integer stationPostId) {
		return calculationMapper.screenPoliceStationForcesList(stationPostId);
	}

	// 最新赛事详情查询
	@Override
	public TrainMatch screenMatchNewest() {
		return trainMatchMapper.screenMatchNewest();
	}

	// 训练成绩合格率前5名
	@Override
	public List<TrainRank> screenTrainNewestRankList() {
		return trainPhysicalMapper.screenTrainNewestRankList();
	}

	// 请假类型占比
	@Override
	public List<CalculationChart> screenLeaveTypeList() {
		return leaveProcessMapper.screenLeaveTypeList();
	}

	// 每月加班时长趋势
	@Override
	public List<LeaveChart> screenOverTimeList() {
		return clockRecordMapper.screenOverTimeList();
	}

	// 每月值班时长趋势
	@Override
	public List<LeaveChart> screenDutyLineChartList() {
		return clockRecordMapper.screenDutyLineChartList();
	}

	// 全局年休假统计
	@Override
	public List<ScreenDoubeChart> screenLeaveAnnualStatisticsList() {
		return leaveAnnualMapper.screenLeaveAnnualStatisticsList();
	}

	// 各所年休假使用率
	@Override
	public List<ScreenDoubeChart> screenLStationeaveAnnualStatisticsList() {
		return leaveAnnualMapper.screenLStationeaveAnnualStatisticsList();
	}

	// 每月请假总人数查询（api）
	@Override
	public List<ScreenChart> screenLeavetotalNumList() {
		return leaveAnnualMapper.screenLeavetotalNumList();
	}

	// 每月休年假人数查询（api）
	@Override
	public List<ScreenChart> screenLAnnualNumList() {
		return leaveAnnualMapper.screenLAnnualNumList();
	}

	// 每月病假人数查询（api）
	@Override
	public List<ScreenChart> screenLOffNumList() {
		return leaveAnnualMapper.screenLOffNumList();
	}

	// 每月事假人数查询（api）
	@Override
	public List<ScreenChart> screenLNumList() {
		return leaveAnnualMapper.screenLNumList();
	}

}

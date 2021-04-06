package com.bayee.political.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.EvaluateTask;
import com.bayee.political.domain.EvaluateTaskParticipant;
import com.bayee.political.domain.HomePageModularPersonalStatistics;
import com.bayee.political.domain.HomePageModularStatistics;
import com.bayee.political.mapper.EvaluateTaskMapper;
import com.bayee.political.mapper.EvaluateTaskParticipantMapper;
import com.bayee.political.mapper.HomePageMapper;
import com.bayee.political.service.HomePageService;

/**
 * @author shentuqiwei
 * @version 2020年6月13日 下午3:39:31
 */
@Service
public class HomePageServiceImpl implements HomePageService {

	@Autowired
	HomePageMapper homePageMapper;// 首页

	@Autowired
	EvaluateTaskMapper evaluateTaskMapper;// 评价任务

	@Autowired
	EvaluateTaskParticipantMapper evaluateTaskParticipantMapper;// 评价参与人评价数据

	// 评价任务Top5列表查询(后台)
	@Override
	public List<EvaluateTask> homeEvaluationList() {
		return evaluateTaskMapper.homeEvaluationList();
	}

	// 评价任务类型饼图(后台)
	@Override
	public List<CalculationChart> homeEvaluationPieChart() {
		return evaluateTaskMapper.homeEvaluationPieChart();
	}

	// 评价任务类型折线图-统计总数(后台)
	@Override
	public List<CalculationChart> homeLineTotalChart() {
		return evaluateTaskMapper.homeLineTotalChart();
	}

	// 评价任务类型折线图-类型数量(后台)
	@Override
	public List<CalculationChart> homeLineChart(Integer type) {
		return evaluateTaskMapper.homeLineChart(type);
	}

	// 最新差评查询(后台)(2条)
	@Override
	public List<EvaluateTaskParticipant> homeEvaluationCheckList() {
		return evaluateTaskParticipantMapper.homeEvaluationCheckList();
	}

	// 今日出勤统计
	@Override
	public HomePageModularStatistics todayWorkItem() {
		return homePageMapper.todayWorkItem();
	}

	// 今日训练统计
	@Override
	public HomePageModularStatistics todayTrainItem() {
		return homePageMapper.todayTrainItem();
	}

	// 今日预警统计
	@Override
	public HomePageModularStatistics todayAlarmItem() {
		return homePageMapper.todayAlarmItem();
	}

	// 今日赛事统计
	@Override
	public HomePageModularStatistics todayMatchItem() {
		return homePageMapper.todayMatchItem();
	}

	// 我的考勤
	@Override
	public HomePageModularPersonalStatistics myAttendanceItem(String policeId, String dateTime) {
		return homePageMapper.myAttendanceItem(policeId, dateTime);
	}

	// 我的训练
	@Override
	public HomePageModularPersonalStatistics myTrainItem(String policeId, String dateTime) {
		return homePageMapper.myTrainItem(policeId, dateTime);
	}

	// 我的赛事
	@Override
	public HomePageModularPersonalStatistics myMatchItem(String policeId, String dateTime) {
		return homePageMapper.myMatchItem(policeId, dateTime);
	}

	// 我的预警
	@Override
	public HomePageModularPersonalStatistics myAlarmItem(String policeId, String dateTime) {
		return homePageMapper.myAlarmItem(policeId, dateTime);
	}

	// 评价任务
	@Override
	public HomePageModularPersonalStatistics myEvaluateItem(String policeId, String dateTime) {
		return homePageMapper.myEvaluateItem(policeId, dateTime);
	}

}

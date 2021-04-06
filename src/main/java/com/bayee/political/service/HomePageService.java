package com.bayee.political.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.EvaluateTask;
import com.bayee.political.domain.EvaluateTaskParticipant;
import com.bayee.political.domain.HomePageModularPersonalStatistics;
import com.bayee.political.domain.HomePageModularStatistics;

/**
 * @author shentuqiwei
 * @version 2020年6月13日 下午3:39:13
 */
@Service
public interface HomePageService {

	// 评价任务Top5列表查询(后台)
	List<EvaluateTask> homeEvaluationList();

	// 评价任务类型饼图(后台)
	List<CalculationChart> homeEvaluationPieChart();

	// 评价任务类型折线图-统计总数(后台)
	List<CalculationChart> homeLineTotalChart();

	// 评价任务类型折线图-类型数量(后台)
	List<CalculationChart> homeLineChart(Integer type);

	// 最新差评查询(后台)(2条)
	List<EvaluateTaskParticipant> homeEvaluationCheckList();

	// 今日出勤统计
	HomePageModularStatistics todayWorkItem();

	// 今日训练统计
	HomePageModularStatistics todayTrainItem();

	// 今日预警统计
	HomePageModularStatistics todayAlarmItem();

	// 今日赛事统计
	HomePageModularStatistics todayMatchItem();

	// 我的考勤
	HomePageModularPersonalStatistics myAttendanceItem(String policeId, String dateTime);

	// 我的训练
	HomePageModularPersonalStatistics myTrainItem(String policeId, String dateTime);

	// 我的赛事
	HomePageModularPersonalStatistics myMatchItem(String policeId, String dateTime);

	// 我的预警
	HomePageModularPersonalStatistics myAlarmItem(String policeId, String dateTime);

	// 评价任务
	HomePageModularPersonalStatistics myEvaluateItem(String policeId, String dateTime);

}

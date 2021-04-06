package com.bayee.political.mapper;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.HomePageModularPersonalStatistics;
import com.bayee.political.domain.HomePageModularStatistics;

/**
 * @author shentuqiwei
 * @version 2020年11月26日 下午9:40:58
 */
public interface HomePageMapper {

	// 今日出勤统计
	HomePageModularStatistics todayWorkItem();

	// 今日训练统计
	HomePageModularStatistics todayTrainItem();

	// 今日预警统计
	HomePageModularStatistics todayAlarmItem();

	// 今日赛事统计
	HomePageModularStatistics todayMatchItem();

	// 我的考勤
	HomePageModularPersonalStatistics myAttendanceItem(@Param("policeId") String policeId,
			@Param("dateTime") String dateTime);

	// 我的训练
	HomePageModularPersonalStatistics myTrainItem(@Param("policeId") String policeId,
			@Param("dateTime") String dateTime);

	// 我的赛事
	HomePageModularPersonalStatistics myMatchItem(@Param("policeId") String policeId,
			@Param("dateTime") String dateTime);

	// 我的预警
	HomePageModularPersonalStatistics myAlarmItem(@Param("policeId") String policeId,
			@Param("dateTime") String dateTime);

	// 我的评价任务
	HomePageModularPersonalStatistics myEvaluateItem(@Param("policeId") String policeId,
			@Param("dateTime") String dateTime);
}

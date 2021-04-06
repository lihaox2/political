package com.bayee.political.mapper;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.TrainMatchBestAchievement;

public interface TrainMatchBestAchievementMapper {

	// 个人赛事最好成绩新增
	int matchBestAchievementCreat(TrainMatchBestAchievement record);

	// 个人赛事最好成绩修改
	int matchBestAchievementUpdate(TrainMatchBestAchievement record);

	// 个人赛事最好成绩详情查询
	TrainMatchBestAchievement matchBestAchievementItem(@Param("id") Integer id,
			@Param("trainMatchId") Integer trainMatchId, @Param("type") Integer type,
			@Param("policeId") String policeId, @Param("departmentId") Integer departmentId);

}
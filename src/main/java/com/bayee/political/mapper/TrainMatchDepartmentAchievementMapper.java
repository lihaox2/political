package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.TrainMatchDepartmentAchievement;
import com.bayee.political.domain.TrainRank;
import com.bayee.political.domain.TrainRecordPolice;

public interface TrainMatchDepartmentAchievementMapper {
	int deleteByPrimaryKey(Integer id);

	// 赛事部门成绩新增
	int matchDepartmentAchievementCreat(TrainMatchDepartmentAchievement record);

	// 赛事部门成绩修改
	int matchDepartmentAchievementUpdate(TrainMatchDepartmentAchievement record);

	// 赛事详情查询
	TrainMatchDepartmentAchievement trainMatchDepartmentAchievementItem(@Param("id") Integer id,
			@Param("trainMatchId") Integer trainMatchId, @Param("departmentId") Integer departmentId);

	// 已签到部门统计
	TrainRecordPolice matchRecordDepartmentItem(@Param("trainMatchId") Integer trainMatchId);

	// 赛事部门成绩查询
	List<TrainRank> matchRecordDepartmentScoreList(@Param("trainMatchId") Integer trainMatchId);
}
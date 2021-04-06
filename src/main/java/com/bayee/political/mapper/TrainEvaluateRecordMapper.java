package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.TrainEvaluateRecord;

public interface TrainEvaluateRecordMapper {

	// 评论删除
	int trainEvaluateRecordDelete(@Param("id") Integer id);

	// 评论新增
	int trainEvaluateRecordCreat(TrainEvaluateRecord record);

	// 评论修改
	int trainEvaluateRecordUpdate(TrainEvaluateRecord record);

	// 评论详情查询
	TrainEvaluateRecord trainEvaluateRecordItem(@Param("id") Integer id, @Param("objectId") Integer objectId,
			@Param("activityId") Integer activityId, @Param("policeId") String policeId);

	// 评论详情查询
	List<TrainEvaluateRecord> trainEvaluateRecordList(@Param("objectId") Integer objectId,
			@Param("activityId") Integer activityId, @Param("policeId") String policeId);
}
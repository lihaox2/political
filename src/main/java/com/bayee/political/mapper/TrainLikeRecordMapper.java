package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.TrainLikeRecord;

public interface TrainLikeRecordMapper {

	// 点赞删除
	int trainLikeRecordDelete(@Param("objectId") Integer objectId, @Param("activityId") Integer activityId,
			@Param("policeId") String policeId);

	// 点赞新增
	int trainLikeRecordCreat(TrainLikeRecord record);

	// 点赞修改
	int trainLikeRecordUpdate(TrainLikeRecord record);

	// 点赞详情查询
	TrainLikeRecord trainLikeRecordItem(@Param("id") Integer id, @Param("objectId") Integer objectId,
			@Param("activityId") Integer activityId, @Param("policeId") String policeId);

	// 点赞详情查询
	List<TrainLikeRecord> trainLikeRecordList(@Param("objectId") Integer objectId,
			@Param("activityId") Integer activityId, @Param("policeId") String policeId);

}
package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.EvaluateTaskParticipantTemp;

public interface EvaluateTaskParticipantTempMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(EvaluateTaskParticipantTemp record);

	int updateByPrimaryKeySelective(EvaluateTaskParticipantTemp record);

	// 评价对象临时表批量新增(api)
	void tempBatch(List<EvaluateTaskParticipantTemp> tempList);

	// 查询是否有超过最大数的规则类型（api）
	EvaluateTaskParticipantTemp participantTempItem(@Param("policeId") String policeId, @Param("taskId") Integer taskId);

	// 删除临时表全部数据
	int tempDeleteAll(@Param("policeId") String policeId, @Param("taskId") Integer taskId);
}
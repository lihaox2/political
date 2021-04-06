package com.bayee.political.mapper;

import java.util.List;

import com.bayee.political.domain.EvaluateParticipant;

public interface EvaluateParticipantMapper {
	
	// 评价参与人列表查询(后台)
	List<EvaluateParticipant> evaluateParticipantList();
}
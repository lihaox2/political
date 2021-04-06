package com.bayee.political.mapper;

import java.util.List;

import com.bayee.political.domain.EvaluateTarget;

public interface EvaluateTargetMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(EvaluateTarget record);

	int insertSelective(EvaluateTarget record);

	EvaluateTarget selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(EvaluateTarget record);

	// 评价对象列表查询(后台)
	List<EvaluateTarget> evaluateTargetList();
}
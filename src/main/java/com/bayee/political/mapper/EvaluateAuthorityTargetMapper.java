package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.EvaluateAuthorityTarget;

public interface EvaluateAuthorityTargetMapper {

	// 评价对象列表新增(后台)
	int evaluateAuthorityTargetCreat(EvaluateAuthorityTarget targetItem);

	// 评价对象列表修改(后台)
	int evaluateAuthorityTargetUpdate(EvaluateAuthorityTarget targetItem);

	// 评价对象列表根据authorityId删除(后台)
	int evaluateAuthorityTargetTotalDelete(@Param("authorityId") Integer authorityId);

	// 评价对象数据列表查询(后台)
	List<EvaluateAuthorityTarget> evaluateAuthorityTargetList(@Param("id") Integer id);

	// 评价对象超过100%验证
	EvaluateAuthorityTarget moreOneHundredItem(@Param("targetId") Integer targetId);

}
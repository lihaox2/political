package com.bayee.political.mapper;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskHealth;

public interface RiskHealthMapper {

	// 警员健康风险指数新增
	int riskHealthCreat(RiskHealth record);

	// 警员健康风险指数修改
	int riskHealthUpdate(RiskHealth record);

	// 警员健康风险指数查询
	RiskHealth riskHealthIndexItem(@Param("policeId") String policeId, @Param("dateTime") String dateTime);
}
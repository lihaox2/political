package com.bayee.political.mapper;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.CalculationFactor;

public interface CalculationFactorMapper {

	// 警力行政拘留新增
	int administrativeDetentionCreat(CalculationFactor record);

	// 警力行政拘留详情查询
	CalculationFactor administrativeDetentionItem(@Param("id") Integer id,
			@Param("caseId") Integer caseId, @Param("policeStationId") Integer policeStationId);

	// 警力行政拘留修改
	int administrativeDetentionUpdate(CalculationFactor record);

}
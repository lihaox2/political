package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.CalculationDetail;

public interface CalculationDetailMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(CalculationDetail record);

	int insertSelective(CalculationDetail record);

	CalculationDetail selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(CalculationDetail record);

	int updateByPrimaryKey(CalculationDetail record);

	// 各派出所案件数量查询(api)
	List<CalculationDetail> calculationDetailApiList(@Param("caseId") Integer caseId,
			@Param("policeType") Integer policeType);

	// 测算详情案件数据新增(api)
	int calculationDetailCreat(CalculationDetail calculationDetail);

	// 测算详情案件数据修改(api)
	int calculationDetailUpdate(CalculationDetail calculationDetail);

	// 各派出所案件数据查询(后台)
	List<CalculationDetail> calDetailsList(@Param("caseId") Integer caseId,
			@Param("policeStationId") Integer policeStationId);

	// 案件详情查询(后台)
	CalculationDetail calculationDetailItem(@Param("id") Integer id, @Param("caseId") Integer caseId,
			@Param("policeStationId") Integer policeStationId);
}
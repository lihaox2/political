package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.CalculationProject;

public interface CalculationProjectMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(CalculationProject record);

	int insertSelective(CalculationProject record);

	CalculationProject selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(CalculationProject record);

	int updateByPrimaryKey(CalculationProject record);

	// 案件类型
	List<CalculationProject> projectList();

	// 民警案件类型查询（后台）
	List<CalculationProject> policeProjectList(@Param("policeStationId") Integer policeStationId);

	// 辅警案件类型查询（后台）
	List<CalculationProject> auxiliaryPoliceProjectList(@Param("policeStationId") Integer policeStationId);
}
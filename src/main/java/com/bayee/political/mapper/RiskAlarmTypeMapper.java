package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskAlarmType;

public interface RiskAlarmTypeMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(RiskAlarmType record);

	int insertSelective(RiskAlarmType record);

	RiskAlarmType selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(RiskAlarmType record);

	int updateByPrimaryKey(RiskAlarmType record);

	// 警员预警类型查询
	List<RiskAlarmType> riskAlarmTypeList(@Param("id") Integer id);
}
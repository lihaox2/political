package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskAlarm;

public interface RiskAlarmMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(RiskAlarm record);

	int insertSelective(RiskAlarm record);

	RiskAlarm selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(RiskAlarm record);

	int updateByPrimaryKey(RiskAlarm record);

	// 警员预警分页查询
	List<RiskAlarm> riskAlarmPageList(@Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum);

	// 警员预警列表总数
	int riskAlarmPageCount(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
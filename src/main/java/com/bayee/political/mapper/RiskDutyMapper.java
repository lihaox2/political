package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskDuty;
import com.bayee.political.domain.ScreenDoubeChart;

public interface RiskDutyMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(RiskDuty record);

	int insertSelective(RiskDuty record);

	RiskDuty selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(RiskDuty record);

	int updateByPrimaryKey(RiskDuty record);

	// 警员接警执勤指数查询
	RiskDuty riskDutyIndexItem(@Param("policeId") String policeId, @Param("dateTime") String dateTime,
							   @Param("lastMonthTime") String lastMonthTime, @Param("timeType") Integer timeType);

	// 半年内接警执勤风险指数
	List<ScreenDoubeChart> riskDutyIndexChart(@Param("policeId") String policeId);
}
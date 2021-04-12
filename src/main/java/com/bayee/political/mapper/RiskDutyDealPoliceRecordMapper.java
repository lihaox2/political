package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskDutyDealPoliceRecord;

public interface RiskDutyDealPoliceRecordMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(RiskDutyDealPoliceRecord record);

	int insertSelective(RiskDutyDealPoliceRecord record);

	RiskDutyDealPoliceRecord selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(RiskDutyDealPoliceRecord record);

	int updateByPrimaryKey(RiskDutyDealPoliceRecord record);

	// 警员接警执勤数据列表查询
	List<RiskDutyDealPoliceRecord> riskDutyRecordList(@Param("policeId") String policeId,
													  @Param("dateTime") String dateTime, @Param("lastMonthTime") String lastMonthTime,
													  @Param("timeType") Integer timeType);
}
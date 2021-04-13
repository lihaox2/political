package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskConductTrafficViolationRecord;

public interface RiskConductTrafficViolationRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskConductTrafficViolationRecord record);

    int insertSelective(RiskConductTrafficViolationRecord record);

    RiskConductTrafficViolationRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConductTrafficViolationRecord record);

    int updateByPrimaryKey(RiskConductTrafficViolationRecord record);
    
    List<RiskConductTrafficViolationRecord> riskConductTrafficViolationRecordList(@Param("policeId")String policeId,@Param("dateTime")String dateTime);

    List<RiskConductTrafficViolationRecord> findRiskConductTrafficViolationRecordList(@Param("policeId") String policeId,
                                                                                      @Param("dateTime") String dateTime,
                                                                                      @Param("lastMonthTime") String lastMonthTime,
                                                                                      @Param("timeType") Integer timeType);

}
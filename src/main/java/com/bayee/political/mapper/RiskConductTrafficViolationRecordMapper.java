package com.bayee.political.mapper;

import com.bayee.political.domain.RiskConductTrafficViolationRecord;

public interface RiskConductTrafficViolationRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskConductTrafficViolationRecord record);

    int insertSelective(RiskConductTrafficViolationRecord record);

    RiskConductTrafficViolationRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConductTrafficViolationRecord record);

    int updateByPrimaryKey(RiskConductTrafficViolationRecord record);
}
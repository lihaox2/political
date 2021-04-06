package com.bayee.political.mapper;

import com.bayee.political.domain.RiskConductTrafficViolation;

public interface RiskConductTrafficViolationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskConductTrafficViolation record);

    int insertSelective(RiskConductTrafficViolation record);

    RiskConductTrafficViolation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConductTrafficViolation record);

    int updateByPrimaryKey(RiskConductTrafficViolation record);
}
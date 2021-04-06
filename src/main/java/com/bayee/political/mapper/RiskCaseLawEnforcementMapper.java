package com.bayee.political.mapper;

import com.bayee.political.domain.RiskCaseLawEnforcement;

public interface RiskCaseLawEnforcementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskCaseLawEnforcement record);

    int insertSelective(RiskCaseLawEnforcement record);

    RiskCaseLawEnforcement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskCaseLawEnforcement record);

    int updateByPrimaryKey(RiskCaseLawEnforcement record);
}
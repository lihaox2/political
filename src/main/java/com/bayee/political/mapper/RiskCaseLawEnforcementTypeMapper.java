package com.bayee.political.mapper;

import com.bayee.political.domain.RiskCaseLawEnforcementType;

public interface RiskCaseLawEnforcementTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskCaseLawEnforcementType record);

    int insertSelective(RiskCaseLawEnforcementType record);

    RiskCaseLawEnforcementType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskCaseLawEnforcementType record);

    int updateByPrimaryKey(RiskCaseLawEnforcementType record);
}
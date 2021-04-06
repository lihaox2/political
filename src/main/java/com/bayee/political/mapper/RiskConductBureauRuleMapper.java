package com.bayee.political.mapper;

import com.bayee.political.domain.RiskConductBureauRule;

public interface RiskConductBureauRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskConductBureauRule record);

    int insertSelective(RiskConductBureauRule record);

    RiskConductBureauRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConductBureauRule record);

    int updateByPrimaryKey(RiskConductBureauRule record);
}
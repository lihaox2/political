package com.bayee.political.mapper;

import com.bayee.political.domain.RiskHealthBmiStandard;

public interface RiskHealthBmiStandardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskHealthBmiStandard record);

    int insertSelective(RiskHealthBmiStandard record);

    RiskHealthBmiStandard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskHealthBmiStandard record);

    int updateByPrimaryKey(RiskHealthBmiStandard record);
}
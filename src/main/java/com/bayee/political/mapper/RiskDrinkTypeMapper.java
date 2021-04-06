package com.bayee.political.mapper;

import com.bayee.political.domain.RiskDrinkType;

public interface RiskDrinkTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskDrinkType record);

    int insertSelective(RiskDrinkType record);

    RiskDrinkType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskDrinkType record);

    int updateByPrimaryKey(RiskDrinkType record);
}
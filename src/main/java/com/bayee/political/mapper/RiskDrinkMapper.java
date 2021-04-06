package com.bayee.political.mapper;

import com.bayee.political.domain.RiskDrink;

public interface RiskDrinkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskDrink record);

    int insertSelective(RiskDrink record);

    RiskDrink selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskDrink record);

    int updateByPrimaryKey(RiskDrink record);
}
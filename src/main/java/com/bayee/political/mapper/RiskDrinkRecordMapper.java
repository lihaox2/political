package com.bayee.political.mapper;

import com.bayee.political.domain.RiskDrinkRecord;

public interface RiskDrinkRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskDrinkRecord record);

    int insertSelective(RiskDrinkRecord record);

    RiskDrinkRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskDrinkRecord record);

    int updateByPrimaryKey(RiskDrinkRecord record);
}
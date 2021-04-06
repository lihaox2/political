package com.bayee.political.mapper;

import com.bayee.political.domain.RiskStutyUnitTrain;

public interface RiskStutyUnitTrainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskStutyUnitTrain record);

    int insertSelective(RiskStutyUnitTrain record);

    RiskStutyUnitTrain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskStutyUnitTrain record);

    int updateByPrimaryKey(RiskStutyUnitTrain record);
}
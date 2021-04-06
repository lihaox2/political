package com.bayee.political.mapper;

import com.bayee.political.domain.RiskStutyUnitTrainRecord;

public interface RiskStutyUnitTrainRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskStutyUnitTrainRecord record);

    int insertSelective(RiskStutyUnitTrainRecord record);

    RiskStutyUnitTrainRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskStutyUnitTrainRecord record);

    int updateByPrimaryKey(RiskStutyUnitTrainRecord record);
}
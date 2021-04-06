package com.bayee.political.mapper;

import com.bayee.political.domain.RiskHealthRecord;

public interface RiskHealthRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskHealthRecord record);

    int insertSelective(RiskHealthRecord record);

    RiskHealthRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskHealthRecord record);

    int updateByPrimaryKey(RiskHealthRecord record);
}
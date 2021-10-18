package com.bayee.political.mapper;

import com.bayee.political.domain.RiskRelevant;

public interface RiskRelevantMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskRelevant record);

    int insertSelective(RiskRelevant record);

    RiskRelevant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskRelevant record);

    int updateByPrimaryKey(RiskRelevant record);


}
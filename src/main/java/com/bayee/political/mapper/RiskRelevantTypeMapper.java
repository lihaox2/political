package com.bayee.political.mapper;

import com.bayee.political.domain.RiskRelevantType;

import java.util.List;

public interface RiskRelevantTypeMapper {
    int deleteByPrimaryKey(String code);

    int insert(RiskRelevantType record);

    int insertSelective(RiskRelevantType record);

    RiskRelevantType selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(RiskRelevantType record);

    int updateByPrimaryKey(RiskRelevantType record);

    /**
     * 查询所有
     * @return
     */
    List<RiskRelevantType> queryAll();
}
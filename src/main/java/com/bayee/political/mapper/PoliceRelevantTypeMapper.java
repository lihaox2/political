package com.bayee.political.mapper;

import com.bayee.political.domain.PoliceRelevantType;

import java.util.List;

public interface PoliceRelevantTypeMapper {
    int deleteByPrimaryKey(String code);

    int insert(PoliceRelevantType record);

    int insertSelective(PoliceRelevantType record);

    PoliceRelevantType selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(PoliceRelevantType record);

    int updateByPrimaryKey(PoliceRelevantType record);

    /**
     * 查询所有
     * @return
     */
    List<PoliceRelevantType> queryAll();
}
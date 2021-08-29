package com.bayee.political.mapper;

import com.bayee.political.domain.RiskHonourType;

import java.util.List;

public interface RiskHonourTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskHonourType record);

    int insertSelective(RiskHonourType record);

    RiskHonourType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskHonourType record);

    int updateByPrimaryKey(RiskHonourType record);

    /**
     * 查询所有表彰奖励类型
     * @return
     */
    List<RiskHonourType> queryAll();

    /**
     * 详情查询
     * @param typeCode
     * @return
     */
    RiskHonourType findByTypeCode(String typeCode);

    /**
     * 根据类型名称查询
     * @param typeName
     * @return
     */
    RiskHonourType findByTypeName(String typeName);
}
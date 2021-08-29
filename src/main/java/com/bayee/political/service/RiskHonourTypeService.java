package com.bayee.political.service;

import com.bayee.political.domain.RiskHonourType;

import java.util.List;

/**
 * @author xxl
 * @date 2021/8/28 15:32
 */
public interface RiskHonourTypeService {

    /**
     * 查询所有奖励类型
     * @return
     */
    List<RiskHonourType> queryAllHonourType();

    /**
     * 详情查询
     * @param id
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

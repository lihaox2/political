package com.bayee.political.service;

import com.bayee.political.domain.RiskRelevantType;

import java.util.List;

/**
 * @author xxl
 * @date 2021/9/4 19:17
 */
public interface PoliceRelevantTypeService {

    /**
     * 查询所有
     * @return
     */
    List<RiskRelevantType> queryAll();

    /**
     * 根据code查询
     * @param code
     * @return
     */
    RiskRelevantType findByCode(String code);

}

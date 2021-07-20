package com.bayee.political.service;

import com.bayee.political.domain.RiskConductVisitOrigin;

import java.util.List;

/**
 * @author xxl
 * @date 2021/7/20
 */
public interface RiskConductVisitOriginService {

    /**
     * 查询所有信访来源
     * @return
     */
    List<RiskConductVisitOrigin> findAllVisitOrigin();

    /**
     * 查询信访来源详情
     * @param id
     * @return
     */
    RiskConductVisitOrigin findById(Integer id);

}

package com.bayee.political.service;

import com.bayee.political.domain.RiskCaseLawEnforcementType;

import java.util.List;

/**
 * @author xxl
 * @date 2021/5/8
 */
public interface RiskCaseLawEnforcementTypeService {

    /**
     * 获取执法管理类型
     * @return
     */
    List<RiskCaseLawEnforcementType> getLawEnforcementType();

    /**
     * 获取接处警类型
     * @return
     */
    List<RiskCaseLawEnforcementType> getDutyType();

    /**
     * 通过类型名称查询id
     * @param name
     * @param parentId
     * @return
     */
    Integer findByNameAndParentId(String name, Integer parentId);

}

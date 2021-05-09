package com.bayee.political.mapper;

import com.bayee.political.domain.RiskCaseLawEnforcementType;

import java.util.List;

public interface RiskCaseLawEnforcementTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskCaseLawEnforcementType record);

    int insertSelective(RiskCaseLawEnforcementType record);

    RiskCaseLawEnforcementType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskCaseLawEnforcementType record);

    int updateByPrimaryKey(RiskCaseLawEnforcementType record);

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

}
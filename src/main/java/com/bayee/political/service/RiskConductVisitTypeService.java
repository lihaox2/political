package com.bayee.political.service;

import com.bayee.political.domain.RiskConductVisitType;

public interface RiskConductVisitTypeService {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskConductVisitType record);

    int insertSelective(RiskConductVisitType record);

    RiskConductVisitType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConductVisitType record);

    int updateByPrimaryKey(RiskConductVisitType record);
    
    Integer selectByName(String name);
}
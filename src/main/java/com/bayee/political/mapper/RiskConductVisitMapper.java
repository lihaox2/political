package com.bayee.political.mapper;

import com.bayee.political.domain.RiskConductVisit;

public interface RiskConductVisitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskConductVisit record);

    int insertSelective(RiskConductVisit record);

    RiskConductVisit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConductVisit record);

    int updateByPrimaryKey(RiskConductVisit record);
}
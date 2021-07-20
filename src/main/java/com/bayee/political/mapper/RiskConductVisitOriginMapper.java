package com.bayee.political.mapper;

import com.bayee.political.domain.RiskConductVisitOrigin;

import java.util.List;

public interface RiskConductVisitOriginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskConductVisitOrigin record);

    int insertSelective(RiskConductVisitOrigin record);

    RiskConductVisitOrigin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConductVisitOrigin record);

    int updateByPrimaryKey(RiskConductVisitOrigin record);

    /**
     * 查询所有信访来源
     * @return
     */
    List<RiskConductVisitOrigin> findAllVisitOrigin();
}
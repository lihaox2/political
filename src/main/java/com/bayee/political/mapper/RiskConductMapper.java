package com.bayee.political.mapper;

import com.bayee.political.domain.RiskConduct;

public interface RiskConductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskConduct record);

    int insertSelective(RiskConduct record);

    RiskConduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConduct record);

    int updateByPrimaryKey(RiskConduct record);
}
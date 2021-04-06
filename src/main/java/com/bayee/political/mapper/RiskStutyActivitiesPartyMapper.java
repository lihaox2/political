package com.bayee.political.mapper;

import com.bayee.political.domain.RiskStutyActivitiesParty;

public interface RiskStutyActivitiesPartyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskStutyActivitiesParty record);

    int insertSelective(RiskStutyActivitiesParty record);

    RiskStutyActivitiesParty selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskStutyActivitiesParty record);

    int updateByPrimaryKey(RiskStutyActivitiesParty record);
}
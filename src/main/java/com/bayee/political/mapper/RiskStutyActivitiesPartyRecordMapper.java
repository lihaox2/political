package com.bayee.political.mapper;

import com.bayee.political.domain.RiskStutyActivitiesPartyRecord;

public interface RiskStutyActivitiesPartyRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskStutyActivitiesPartyRecord record);

    int insertSelective(RiskStutyActivitiesPartyRecord record);

    RiskStutyActivitiesPartyRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskStutyActivitiesPartyRecord record);

    int updateByPrimaryKey(RiskStutyActivitiesPartyRecord record);
}
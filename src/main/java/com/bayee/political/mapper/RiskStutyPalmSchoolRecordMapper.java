package com.bayee.political.mapper;

import com.bayee.political.domain.RiskStutyPalmSchoolRecord;

public interface RiskStutyPalmSchoolRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskStutyPalmSchoolRecord record);

    int insertSelective(RiskStutyPalmSchoolRecord record);

    RiskStutyPalmSchoolRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskStutyPalmSchoolRecord record);

    int updateByPrimaryKey(RiskStutyPalmSchoolRecord record);
}
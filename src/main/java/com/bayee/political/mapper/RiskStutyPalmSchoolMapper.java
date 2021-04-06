package com.bayee.political.mapper;

import com.bayee.political.domain.RiskStutyPalmSchool;

public interface RiskStutyPalmSchoolMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskStutyPalmSchool record);

    int insertSelective(RiskStutyPalmSchool record);

    RiskStutyPalmSchool selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskStutyPalmSchool record);

    int updateByPrimaryKey(RiskStutyPalmSchool record);
}
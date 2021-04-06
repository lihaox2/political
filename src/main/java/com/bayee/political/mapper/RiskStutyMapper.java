package com.bayee.political.mapper;

import com.bayee.political.domain.RiskStuty;

public interface RiskStutyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskStuty record);

    int insertSelective(RiskStuty record);

    RiskStuty selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskStuty record);

    int updateByPrimaryKey(RiskStuty record);
}
package com.bayee.political.mapper;

import com.bayee.political.domain.RiskCaseTest;

public interface RiskCaseTestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskCaseTest record);

    int insertSelective(RiskCaseTest record);

    RiskCaseTest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskCaseTest record);

    int updateByPrimaryKey(RiskCaseTest record);
}
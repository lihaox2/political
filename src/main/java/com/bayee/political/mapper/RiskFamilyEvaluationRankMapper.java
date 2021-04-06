package com.bayee.political.mapper;

import com.bayee.political.domain.RiskFamilyEvaluationRank;

public interface RiskFamilyEvaluationRankMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskFamilyEvaluationRank record);

    int insertSelective(RiskFamilyEvaluationRank record);

    RiskFamilyEvaluationRank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskFamilyEvaluationRank record);

    int updateByPrimaryKey(RiskFamilyEvaluationRank record);
}
package com.bayee.political.mapper;

import com.bayee.political.domain.RiskFamilyEvaluationRecord;

public interface RiskFamilyEvaluationRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskFamilyEvaluationRecord record);

    int insertSelective(RiskFamilyEvaluationRecord record);

    RiskFamilyEvaluationRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskFamilyEvaluationRecord record);

    int updateByPrimaryKey(RiskFamilyEvaluationRecord record);
}
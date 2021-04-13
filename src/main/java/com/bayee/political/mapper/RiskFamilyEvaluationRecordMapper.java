package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskFamilyEvaluationRecord;

public interface RiskFamilyEvaluationRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskFamilyEvaluationRecord record);

    int insertSelective(RiskFamilyEvaluationRecord record);

    RiskFamilyEvaluationRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskFamilyEvaluationRecord record);

    int updateByPrimaryKey(RiskFamilyEvaluationRecord record);
    
    List<RiskFamilyEvaluationRecord> findByYearAndMonth(@Param("year") String year,@Param("month") String month);
}
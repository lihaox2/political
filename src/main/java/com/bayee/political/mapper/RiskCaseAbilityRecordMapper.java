package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskCaseAbilityRecord;

public interface RiskCaseAbilityRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskCaseAbilityRecord record);

    int insertSelective(RiskCaseAbilityRecord record);

    RiskCaseAbilityRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskCaseAbilityRecord record);

    int updateByPrimaryKey(RiskCaseAbilityRecord record);
    
    /**
     * 查询警员执法数据
     * @param policeId 警号
     * @param date 时间
     * @return
     */
    List<RiskCaseAbilityRecord> findPoliceCaseData(@Param("policeId") String policeId,@Param("date") String date);
    
    
    Integer getByYearAndPoliceId(@Param("year")String year,@Param("policeId") String policeId);
}
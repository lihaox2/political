package com.bayee.political.mapper;

import com.bayee.political.domain.RiskCaseAbilityRecord;

public interface RiskCaseAbilityRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskCaseAbilityRecord record);

    int insertSelective(RiskCaseAbilityRecord record);

    RiskCaseAbilityRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskCaseAbilityRecord record);

    int updateByPrimaryKey(RiskCaseAbilityRecord record);
}
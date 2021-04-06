package com.bayee.political.mapper;

import com.bayee.political.domain.RiskCaseLawEnforcementRecord;

public interface RiskCaseLawEnforcementRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskCaseLawEnforcementRecord record);

    int insertSelective(RiskCaseLawEnforcementRecord record);

    RiskCaseLawEnforcementRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskCaseLawEnforcementRecord record);

    int updateByPrimaryKey(RiskCaseLawEnforcementRecord record);
}
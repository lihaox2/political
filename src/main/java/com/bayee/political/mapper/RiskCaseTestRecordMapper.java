package com.bayee.political.mapper;

import com.bayee.political.domain.RiskCaseTestRecord;

public interface RiskCaseTestRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskCaseTestRecord record);

    int insertSelective(RiskCaseTestRecord record);

    RiskCaseTestRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskCaseTestRecord record);

    int updateByPrimaryKey(RiskCaseTestRecord record);
}
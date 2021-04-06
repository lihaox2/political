package com.bayee.political.service;

import java.util.List;

import com.bayee.political.domain.RiskConductTrafficViolationRecord;

public interface RiskConductTrafficViolationRecordService {

	int deleteByPrimaryKey(Integer id);

    int insert(RiskConductTrafficViolationRecord record);

    int insertSelective(RiskConductTrafficViolationRecord record);

    RiskConductTrafficViolationRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConductTrafficViolationRecord record);

    int updateByPrimaryKey(RiskConductTrafficViolationRecord record);
    
    List<RiskConductTrafficViolationRecord> riskConductTrafficViolationRecordList(String policeId,String dateTime);
}

package com.bayee.political.service;


import com.bayee.political.domain.RiskCaseAbilityRecord;

public interface RiskCaseAbilityRecordService {
	
	Integer getByYearAndPoliceId(String year,String policeId);
	
	int updateByPrimaryKeySelective(RiskCaseAbilityRecord record);
	
	int insertSelective(RiskCaseAbilityRecord record);

}

package com.bayee.political.service;


import com.bayee.political.domain.RiskCaseAbilityRecord;

import java.util.List;

public interface RiskCaseAbilityRecordService {
	
	Integer getByYearAndPoliceId(String year,String policeId);
	
	int updateByPrimaryKeySelective(RiskCaseAbilityRecord record);
	
	int insertSelective(RiskCaseAbilityRecord record);

	int deleteByPrimaryKey(Integer id);

	RiskCaseAbilityRecord selectByPrimaryKey(Integer id);

}

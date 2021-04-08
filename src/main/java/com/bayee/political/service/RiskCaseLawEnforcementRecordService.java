package com.bayee.political.service;

import java.util.List;

import com.bayee.political.domain.RiskCaseLawEnforcementRecord;

public interface RiskCaseLawEnforcementRecordService {

	List<RiskCaseLawEnforcementRecord> riskCaseLawEnforcementRecordList(String policeId,String dateTime);
	
	int insertSelective(RiskCaseLawEnforcementRecord record);
}

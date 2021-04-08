package com.bayee.political.service;

import com.bayee.political.domain.RiskStutyPalmSchoolRecord;

public interface RiskStutyPalmSchoolRecordService {
	
	Integer getByIdAndYearMonth(String yearMonth,String policeId);
	
	int updateByPrimaryKeySelective(RiskStutyPalmSchoolRecord record);
	
	int insertSelective(RiskStutyPalmSchoolRecord record);

}

package com.bayee.political.service;

import com.bayee.political.domain.RiskStutyActivitiesPartyRecord;

public interface RiskStutyActivitiesPartyRecordService{

	int insertSelective(RiskStutyActivitiesPartyRecord record);
	
	int updateByPrimaryKeySelective(RiskStutyActivitiesPartyRecord record);
	
	Integer getByIdAndYearMonth(String yearMonth,String policeId);
}

package com.bayee.political.service;

import com.bayee.political.domain.RiskStutyUnitTrainRecord;

public interface RiskStutyUnitTrainRecordService {

	Integer getByIdAndYearMonth(String yearMonth,String policeId);
	
	int updateByPrimaryKeySelective(RiskStutyUnitTrainRecord record);
	
	int insertSelective(RiskStutyUnitTrainRecord record);
}

package com.bayee.political.service;

import com.bayee.political.domain.RiskHealthRecord;
import com.bayee.political.domain.RiskReportRecord;

public interface RiskHealthRecordService {

	int deleteByPrimaryKey(Integer id);

    int insert(RiskHealthRecord record);

    int insertSelective(RiskHealthRecord record);

    RiskHealthRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskHealthRecord record);

    int updateByPrimaryKey(RiskHealthRecord record);
    
    Integer getByIdAndYear(String policeId,String year);
    
    RiskReportRecord getByPoliceIdMonth(String year,String month,String policeId);
}

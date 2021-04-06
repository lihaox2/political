package com.bayee.political.service;

import java.util.List;

import com.bayee.political.domain.RiskConductVisitRecord;

public interface RiskConductVisitRecordService {
	
	int deleteByPrimaryKey(Integer id);

    int insert(RiskConductVisitRecord record);

    int insertSelective(RiskConductVisitRecord record);

    RiskConductVisitRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConductVisitRecord record);

    int updateByPrimaryKey(RiskConductVisitRecord record);
    
    List<RiskConductVisitRecord> riskConductVisitRecordList(String policeId,String dateTime);

}

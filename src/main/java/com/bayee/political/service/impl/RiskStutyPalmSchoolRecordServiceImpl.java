package com.bayee.political.service.impl;

import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskStutyPalmSchoolRecord;
import com.bayee.political.mapper.RiskStutyPalmSchoolRecordMapper;
import com.bayee.political.service.RiskStutyPalmSchoolRecordService;

@Service
public class RiskStutyPalmSchoolRecordServiceImpl implements RiskStutyPalmSchoolRecordService{
	
	RiskStutyPalmSchoolRecordMapper riskStutyPalmSchoolRecordMapper;
	
	@Override
	public Integer getByIdAndYearMonth(String yearMonth, String policeId) {
		// TODO Auto-generated method stub
		return riskStutyPalmSchoolRecordMapper.getByIdAndYearMonth(yearMonth, policeId);
	}

	@Override
	public int updateByPrimaryKeySelective(RiskStutyPalmSchoolRecord record) {
		// TODO Auto-generated method stub
		return riskStutyPalmSchoolRecordMapper.updateByPrimaryKey(record);
	}

	@Override
	public int insertSelective(RiskStutyPalmSchoolRecord record) {
		// TODO Auto-generated method stub
		return riskStutyPalmSchoolRecordMapper.insertSelective(record);
	}

}

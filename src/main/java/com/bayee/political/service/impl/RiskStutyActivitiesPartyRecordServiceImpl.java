package com.bayee.political.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskStutyActivitiesPartyRecord;
import com.bayee.political.mapper.RiskStutyActivitiesPartyRecordMapper;
import com.bayee.political.service.RiskStutyActivitiesPartyRecordService;
@Service
public class RiskStutyActivitiesPartyRecordServiceImpl implements RiskStutyActivitiesPartyRecordService{
	
	@Autowired
	RiskStutyActivitiesPartyRecordMapper riskStutyActivitiesPartyRecordMapper;

	@Override
	public int insertSelective(RiskStutyActivitiesPartyRecord record) {
		// TODO Auto-generated method stub
		return riskStutyActivitiesPartyRecordMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(RiskStutyActivitiesPartyRecord record) {
		// TODO Auto-generated method stub
		return riskStutyActivitiesPartyRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Integer getByIdAndYearMonth(String yearMonth, String policeId) {
		// TODO Auto-generated method stub
		return riskStutyActivitiesPartyRecordMapper.getByIdAndYearMonth(yearMonth, policeId);
	}

}

package com.bayee.political.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskCaseAbilityRecord;
import com.bayee.political.mapper.RiskCaseAbilityRecordMapper;
import com.bayee.political.service.RiskCaseAbilityRecordService;
@Service
public class RiskCaseAbilityRecordServiceImpl implements RiskCaseAbilityRecordService{
	
	@Autowired
	RiskCaseAbilityRecordMapper riskCaseAbilityRecordMapper;

	@Override
	public Integer getByYearAndPoliceId(String year, String policeId) {
		// TODO Auto-generated method stub
		return riskCaseAbilityRecordMapper.getByYearAndPoliceId(year, policeId);
	}

	@Override
	public int updateByPrimaryKeySelective(RiskCaseAbilityRecord record) {
		// TODO Auto-generated method stub
		return riskCaseAbilityRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int insertSelective(RiskCaseAbilityRecord record) {
		// TODO Auto-generated method stub
		return riskCaseAbilityRecordMapper.insertSelective(record);
	}

}

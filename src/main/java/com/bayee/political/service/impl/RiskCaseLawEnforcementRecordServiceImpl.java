package com.bayee.political.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskCaseLawEnforcementRecord;
import com.bayee.political.mapper.RiskCaseLawEnforcementRecordMapper;
import com.bayee.political.service.RiskCaseLawEnforcementRecordService;

@Service
public class RiskCaseLawEnforcementRecordServiceImpl implements RiskCaseLawEnforcementRecordService{
	@Autowired
	private RiskCaseLawEnforcementRecordMapper riskCaseLawEnforcementRecordMapper;

	@Override
	public List<RiskCaseLawEnforcementRecord> riskCaseLawEnforcementRecordList(String policeId,String dateTime) {
		// TODO Auto-generated method stub
		return riskCaseLawEnforcementRecordMapper.riskCaseLawEnforcementRecordList(policeId,dateTime, null , 2);
	}

	@Override
	public int insertSelective(RiskCaseLawEnforcementRecord record) {
		// TODO Auto-generated method stub
		return riskCaseLawEnforcementRecordMapper.insertSelective(record);
	}

}

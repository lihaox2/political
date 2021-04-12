package com.bayee.political.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskDutyDealPoliceRecord;
import com.bayee.political.mapper.RiskDutyDealPoliceRecordMapper;
import com.bayee.political.service.RiskDutyDealPoliceRecordService;

@Service
public class RiskDutyDealPoliceRecordServiceImpl implements RiskDutyDealPoliceRecordService{

	@Autowired
	RiskDutyDealPoliceRecordMapper riskDutyDealPoliceRecordMapper;
	@Override
	public int insertSelective(RiskDutyDealPoliceRecord record) {
		// TODO Auto-generated method stub
		return riskDutyDealPoliceRecordMapper.insertSelective(record);
	}
}

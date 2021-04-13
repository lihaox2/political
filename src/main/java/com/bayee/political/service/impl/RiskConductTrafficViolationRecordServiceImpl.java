package com.bayee.political.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskConductTrafficViolationRecord;
import com.bayee.political.mapper.RiskConductTrafficViolationRecordMapper;
import com.bayee.political.service.RiskConductTrafficViolationRecordService;

@Service
public class RiskConductTrafficViolationRecordServiceImpl implements RiskConductTrafficViolationRecordService{
	
	@Autowired
	private RiskConductTrafficViolationRecordMapper riskConductTrafficViolationRecordMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskConductTrafficViolationRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RiskConductTrafficViolationRecord record) {
		// TODO Auto-generated method stub
		return riskConductTrafficViolationRecordMapper.insert(record);
	}

	@Override
	public int insertSelective(RiskConductTrafficViolationRecord record) {
		// TODO Auto-generated method stub
		return riskConductTrafficViolationRecordMapper.insertSelective(record);
	}

	@Override
	public RiskConductTrafficViolationRecord selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskConductTrafficViolationRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RiskConductTrafficViolationRecord record) {
		// TODO Auto-generated method stub
		return riskConductTrafficViolationRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RiskConductTrafficViolationRecord record) {
		// TODO Auto-generated method stub
		return riskConductTrafficViolationRecordMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<RiskConductTrafficViolationRecord> riskConductTrafficViolationRecordList(String policeId, String dateTime) {
		// TODO Auto-generated method stub
		return riskConductTrafficViolationRecordMapper.riskConductTrafficViolationRecordList(policeId, dateTime);
	}

	@Override
	public List<RiskConductTrafficViolationRecord> riskConductTrafficViolationRecordList(String policeId, String dateTime,
																						 String lastMonthTime, Integer timeType) {
		return riskConductTrafficViolationRecordMapper.findRiskConductTrafficViolationRecordList(policeId, dateTime, lastMonthTime, timeType);
	}

}

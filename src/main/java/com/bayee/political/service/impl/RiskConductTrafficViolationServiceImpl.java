package com.bayee.political.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskConductTrafficViolation;
import com.bayee.political.domain.ScreenDoubeChart;
import com.bayee.political.mapper.RiskConductTrafficViolationMapper;
import com.bayee.political.service.RiskConductTrafficViolationService;

@Service
public class RiskConductTrafficViolationServiceImpl implements RiskConductTrafficViolationService{
	
	@Autowired
	private RiskConductTrafficViolationMapper riskConductTrafficViolationMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskConductTrafficViolationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RiskConductTrafficViolation record) {
		// TODO Auto-generated method stub
		return riskConductTrafficViolationMapper.insert(record);
	}

	@Override
	public int insertSelective(RiskConductTrafficViolation record) {
		// TODO Auto-generated method stub
		return riskConductTrafficViolationMapper.insertSelective(record);
	}

	@Override
	public RiskConductTrafficViolation selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskConductTrafficViolationMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RiskConductTrafficViolation record) {
		// TODO Auto-generated method stub
		return riskConductTrafficViolationMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RiskConductTrafficViolation record) {
		// TODO Auto-generated method stub
		return riskConductTrafficViolationMapper.updateByPrimaryKey(record);
	}

	@Override
	public RiskConductTrafficViolation riskConductTrafficViolationItem(String policeId, String dateTime, String lastMonthTime,
																			 Integer timeType) {
		// TODO Auto-generated method stub
		return riskConductTrafficViolationMapper.riskConductTrafficViolationItem(policeId, dateTime, lastMonthTime, timeType);
	}

	@Override
	public List<ScreenDoubeChart> riskConductTrafficViolationChart(String policeId) {
		// TODO Auto-generated method stub
		return riskConductTrafficViolationMapper.riskConductTrafficViolationChart(policeId);
	}

}

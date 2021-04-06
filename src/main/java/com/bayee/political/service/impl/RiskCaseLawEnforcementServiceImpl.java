package com.bayee.political.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskCaseLawEnforcement;
import com.bayee.political.domain.ScreenDoubeChart;
import com.bayee.political.mapper.RiskCaseLawEnforcementMapper;
import com.bayee.political.service.RiskCaseLawEnforcementService;

@Service
public class RiskCaseLawEnforcementServiceImpl implements RiskCaseLawEnforcementService{
	
	@Autowired
	RiskCaseLawEnforcementMapper riskCaseLawEnforcementMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskCaseLawEnforcementMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RiskCaseLawEnforcement record) {
		// TODO Auto-generated method stub
		return riskCaseLawEnforcementMapper.insert(record);
	}

	@Override
	public int insertSelective(RiskCaseLawEnforcement record) {
		// TODO Auto-generated method stub
		return riskCaseLawEnforcementMapper.insertSelective(record);
	}

	@Override
	public RiskCaseLawEnforcement selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskCaseLawEnforcementMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RiskCaseLawEnforcement record) {
		// TODO Auto-generated method stub
		return riskCaseLawEnforcementMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RiskCaseLawEnforcement record) {
		// TODO Auto-generated method stub
		return riskCaseLawEnforcementMapper.updateByPrimaryKey(record);
	}

	@Override
	public RiskCaseLawEnforcement riskCaseLawEnforcementItem(String policeId, String dateTime) {
		// TODO Auto-generated method stub
		return riskCaseLawEnforcementMapper.riskCaseLawEnforcementItem(policeId, dateTime);
	}

	@Override
	public List<ScreenDoubeChart> riskCaseLawEnforcementChart(String policeId) {
		// TODO Auto-generated method stub
		return riskCaseLawEnforcementMapper.riskCaseLawEnforcementChart(policeId);
	}

	
}

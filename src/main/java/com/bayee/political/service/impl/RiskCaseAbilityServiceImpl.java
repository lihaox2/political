package com.bayee.political.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskCaseAbility;
import com.bayee.political.domain.ScreenDoubeChart;
import com.bayee.political.mapper.RiskCaseAbilityMapper;
import com.bayee.political.service.RiskCaseAbilityService;

@Service
public class RiskCaseAbilityServiceImpl implements RiskCaseAbilityService{
	
	@Autowired
	private RiskCaseAbilityMapper riskCaseAbilityMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskCaseAbilityMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RiskCaseAbility record) {
		// TODO Auto-generated method stub
		return riskCaseAbilityMapper.insert(record);
	}

	@Override
	public int insertSelective(RiskCaseAbility record) {
		// TODO Auto-generated method stub
		return riskCaseAbilityMapper.insertSelective(record);
	}

	@Override
	public RiskCaseAbility selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskCaseAbilityMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RiskCaseAbility record) {
		// TODO Auto-generated method stub
		return riskCaseAbilityMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RiskCaseAbility record) {
		// TODO Auto-generated method stub
		return riskCaseAbilityMapper.updateByPrimaryKey(record);
	}

	@Override
	public RiskCaseAbility riskCaseAbilityItem(String policeId, String dateTime) {
		// TODO Auto-generated method stub
		return riskCaseAbilityMapper.riskCaseAbilityItem(policeId, dateTime);
	}

	@Override
	public List<ScreenDoubeChart> riskCaseAbilityChart(String policeId) {
		// TODO Auto-generated method stub
		return riskCaseAbilityMapper.riskCaseAbilityChart(policeId);
	}

	@Override
	public boolean checkPoliceDeductionStatus(String policeId, String beginDate, String endDate) {
		Integer flag = riskCaseAbilityMapper.checkPoliceDeductionStatus(policeId, beginDate, endDate);
		return !(flag != null && flag > 0);
	}

}

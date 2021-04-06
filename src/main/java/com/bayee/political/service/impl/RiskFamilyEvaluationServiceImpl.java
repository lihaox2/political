package com.bayee.political.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskFamilyEvaluation;
import com.bayee.political.domain.ScreenDoubeChart;
import com.bayee.political.mapper.RiskFamilyEvaluationMapper;
import com.bayee.political.service.RiskFamilyEvaluationService;

@Service
public class RiskFamilyEvaluationServiceImpl implements RiskFamilyEvaluationService{
	
	@Autowired
	private RiskFamilyEvaluationMapper riskFamilyEvaluationMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskFamilyEvaluationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RiskFamilyEvaluation record) {
		// TODO Auto-generated method stub
		return riskFamilyEvaluationMapper.insert(record);
	}

	@Override
	public int insertSelective(RiskFamilyEvaluation record) {
		// TODO Auto-generated method stub
		return riskFamilyEvaluationMapper.insertSelective(record);
	}

	@Override
	public RiskFamilyEvaluation selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskFamilyEvaluationMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RiskFamilyEvaluation record) {
		// TODO Auto-generated method stub
		return riskFamilyEvaluationMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RiskFamilyEvaluation record) {
		// TODO Auto-generated method stub
		return riskFamilyEvaluationMapper.updateByPrimaryKey(record);
	}

	@Override
	public RiskFamilyEvaluation riskFamilyEvaluationItem(String policeId, String dateTime) {
		// TODO Auto-generated method stub
		return riskFamilyEvaluationMapper.riskFamilyEvaluationItem(policeId, dateTime);
	}

	@Override
	public List<ScreenDoubeChart> riskFamilyEvaluationChart(String policeId) {
		// TODO Auto-generated method stub
		return riskFamilyEvaluationMapper.riskFamilyEvaluationChart(policeId);
	}

}

package com.bayee.political.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskCaseLawEnforcement;
import com.bayee.political.domain.RiskConductVisit;
import com.bayee.political.domain.ScreenDoubeChart;
import com.bayee.political.mapper.RiskConductVisitMapper;
import com.bayee.political.service.RiskConductVisitService;

@Service
public class RiskConductVisitServiceImpl implements RiskConductVisitService{
	
	@Autowired
	private RiskConductVisitMapper riskConductVisitMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskConductVisitMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RiskConductVisit record) {
		// TODO Auto-generated method stub
		return riskConductVisitMapper.insert(record);
	}

	@Override
	public int insertSelective(RiskConductVisit record) {
		// TODO Auto-generated method stub
		return riskConductVisitMapper.insertSelective(record);
	}

	@Override
	public RiskConductVisit selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskConductVisitMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RiskConductVisit record) {
		// TODO Auto-generated method stub
		return riskConductVisitMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RiskConductVisit record) {
		// TODO Auto-generated method stub
		return riskConductVisitMapper.updateByPrimaryKey(record);
	}

	@Override
	public RiskConductVisit riskConductVisitItem(String policeId, String dateTime, String lastMonthTime, Integer timeType) {
		// TODO Auto-generated method stub
		return riskConductVisitMapper.riskConductVisitItem(policeId, dateTime, lastMonthTime, timeType);
	}

	@Override
	public List<ScreenDoubeChart> riskConductVisitChart(String policeId) {
		// TODO Auto-generated method stub
		return riskConductVisitMapper.riskConductVisitChart(policeId);
	}

}

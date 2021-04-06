package com.bayee.political.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskConduct;
import com.bayee.political.domain.ScreenDoubeChart;
import com.bayee.political.mapper.RiskConductMapper;
import com.bayee.political.service.RiskConductService;

@Service
public class RiskConductServiceImpl implements RiskConductService{
	
	@Autowired
	private RiskConductMapper riskConductMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskConductMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RiskConduct record) {
		// TODO Auto-generated method stub
		return riskConductMapper.insert(record);
	}

	@Override
	public int insertSelective(RiskConduct record) {
		// TODO Auto-generated method stub
		return riskConductMapper.insertSelective(record);
	}

	@Override
	public RiskConduct selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskConductMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RiskConduct record) {
		// TODO Auto-generated method stub
		return riskConductMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RiskConduct record) {
		// TODO Auto-generated method stub
		return riskConductMapper.updateByPrimaryKey(record);
	}

	@Override
	public RiskConduct riskConductItem(String policeId, String dateTime) {
		// TODO Auto-generated method stub
		return riskConductMapper.riskConductItem(policeId, dateTime);
	}

	@Override
	public List<ScreenDoubeChart> riskConductChart(String policeId) {
		// TODO Auto-generated method stub
		return riskConductMapper.riskConductChart(policeId);
	}

}

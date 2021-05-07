package com.bayee.political.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskCaseTestRecord;
import com.bayee.political.domain.ScreenDoubeChart;
import com.bayee.political.mapper.RiskCaseTestRecordMapper;
import com.bayee.political.service.RiskCaseTestRecordService;

@Service
public class RiskCaseTestRecordServiceImpl implements RiskCaseTestRecordService{
	
	@Autowired
	private RiskCaseTestRecordMapper riskCaseTestRecordMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskCaseTestRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(RiskCaseTestRecord record) {
		// TODO Auto-generated method stub
		return riskCaseTestRecordMapper.insertSelective(record);
	}

	@Override
	public RiskCaseTestRecord selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskCaseTestRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RiskCaseTestRecord record) {
		// TODO Auto-generated method stub
		return riskCaseTestRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RiskCaseTestRecord record) {
		// TODO Auto-generated method stub
		return riskCaseTestRecordMapper.updateByPrimaryKey(record);
	}

	@Override
	public RiskCaseTestRecord riskCaseTestItem(String policeId, String dateTime) {
		// TODO Auto-generated method stub
		return riskCaseTestRecordMapper.riskCaseTestItem(policeId, dateTime);
	}

	@Override
	public List<ScreenDoubeChart> riskCaseTestChart(String policeId) {
		// TODO Auto-generated method stub
		return riskCaseTestRecordMapper.riskCaseTestChart(policeId);
	}

	@Override
	public int insertTest(RiskCaseTestRecord record) {
		// TODO Auto-generated method stub
		return riskCaseTestRecordMapper.insertTest(record);
	}

	@Override
	public Integer isExistence(String policeId, String year, Integer semester) {
		// TODO Auto-generated method stub
		return riskCaseTestRecordMapper.isExistence(policeId, year, semester);
	}

	@Override
	public List<RiskCaseTestRecord> riskCaseTestRecordPage(Integer pageIndex, Integer pageSize) {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		pageIndex = (pageIndex - 1) * pageSize;
		return riskCaseTestRecordMapper.riskCaseTestRecordPage(pageIndex, pageSize);
	}

	@Override
	public Integer riskCaseTestRecordPageCount() {
		return riskCaseTestRecordMapper.riskCaseTestRecordPageCount();
	}


}

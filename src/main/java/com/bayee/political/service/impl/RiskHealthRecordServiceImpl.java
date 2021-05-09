package com.bayee.political.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskHealthRecord;
import com.bayee.political.domain.RiskReportRecord;
import com.bayee.political.mapper.RiskHealthRecordMapper;
import com.bayee.political.mapper.RiskReportRecordMapper;
import com.bayee.political.service.RiskHealthRecordService;

import java.util.List;

@Service
public class RiskHealthRecordServiceImpl implements RiskHealthRecordService{
	
	@Autowired
	private RiskHealthRecordMapper riskHealthRecordMapper;
	
	@Autowired
	private RiskReportRecordMapper riskReportRecordMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskHealthRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RiskHealthRecord record) {
		// TODO Auto-generated method stub
		return riskHealthRecordMapper.insert(record);
	}

	@Override
	public int insertSelective(RiskHealthRecord record) {
		// TODO Auto-generated method stub
		return riskHealthRecordMapper.insertSelective(record);
	}

	@Override
	public RiskHealthRecord selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskHealthRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RiskHealthRecord record) {
		// TODO Auto-generated method stub
		return riskHealthRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RiskHealthRecord record) {
		// TODO Auto-generated method stub
		return riskHealthRecordMapper.updateByPrimaryKey(record);
	}

	@Override
	public Integer getByIdAndYear(String policeId, String year) {
		// TODO Auto-generated method stub
		return riskHealthRecordMapper.getByIdAndYear(policeId, year);
	}

	@Override
	public RiskReportRecord getByPoliceIdMonth(String year, String month, String policeId) {
		// TODO Auto-generated method stub
		return riskReportRecordMapper.getByPoliceIdMonth(year, month, policeId);
	}

	@Override
	public List<RiskHealthRecord> riskRiskHealthRecordPage(Integer pageIndex, Integer pageSize) {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		pageIndex = (pageIndex - 1) * pageSize;
		return riskHealthRecordMapper.riskRiskHealthRecordPage(pageIndex, pageSize);
	}

	@Override
	public Integer getRiskReportRecordPageCount() {
		return riskHealthRecordMapper.getRiskReportRecordPageCount();
	}

}

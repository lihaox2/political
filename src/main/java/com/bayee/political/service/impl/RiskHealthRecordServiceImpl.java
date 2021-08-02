package com.bayee.political.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
	public Integer getByIdAndYear(String policeId, String year, Integer id) {
		// TODO Auto-generated method stub
		return riskHealthRecordMapper.getByIdAndYear(policeId, year, id);
	}

	@Override
	public RiskReportRecord getByPoliceIdMonth(String year, String month, String policeId) {
		// TODO Auto-generated method stub
		return riskReportRecordMapper.getByPoliceIdMonth(year, month, policeId);
	}

	@Override
	public List<RiskHealthRecord> riskRiskHealthRecordPage(Integer pageIndex, Integer pageSize,
														   List<String> columnList,Integer typeFlag,
														   String key, Integer deptId) {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		pageIndex = (pageIndex - 1) * pageSize;
		return riskHealthRecordMapper.riskRiskHealthRecordPage(pageIndex, pageSize,columnList,typeFlag,key, deptId);
	}

	@Override
	public Integer getRiskReportRecordPageCount(List<String> columnList,Integer typeFlag,
            String key, Integer deptId) {
		return riskHealthRecordMapper.getRiskReportRecordPageCount(columnList,typeFlag,key, deptId);
	}

	@Override
	public Integer countAll() {
		return riskHealthRecordMapper.countAll();
	}

	@Override
	public RiskHealthRecord findByPoliceIdAndYear(String policeId, String year) {
		return riskHealthRecordMapper.findByPoliceIdAndYear(policeId, year);
	}

}

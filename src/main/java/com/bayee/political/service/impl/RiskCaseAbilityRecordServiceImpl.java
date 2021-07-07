package com.bayee.political.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskCaseAbilityRecord;
import com.bayee.political.mapper.RiskCaseAbilityRecordMapper;
import com.bayee.political.service.RiskCaseAbilityRecordService;

import java.util.List;

@Service
public class RiskCaseAbilityRecordServiceImpl implements RiskCaseAbilityRecordService{
	
	@Autowired
	RiskCaseAbilityRecordMapper riskCaseAbilityRecordMapper;

	@Override
	public Integer getByYearAndPoliceId(String year, String policeId) {
		// TODO Auto-generated method stub
		return riskCaseAbilityRecordMapper.getByYearAndPoliceId(year, policeId);
	}

	@Override
	public int updateByPrimaryKeySelective(RiskCaseAbilityRecord record) {
		// TODO Auto-generated method stub
		return riskCaseAbilityRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int insertSelective(RiskCaseAbilityRecord record) {
		// TODO Auto-generated method stub
		return riskCaseAbilityRecordMapper.insertSelective(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return riskCaseAbilityRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public RiskCaseAbilityRecord selectByPrimaryKey(Integer id) {
		return riskCaseAbilityRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<RiskCaseAbilityRecord> riskCaseAbilityRecordPage(Integer pageIndex, Integer pageSize,
																 List<String> columnList, Integer typeFlag,
																 String key, String date, Integer deptId) {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		pageIndex = (pageIndex - 1) * pageSize;
		if (columnList.size() <= 0) {
			columnList = null;
		}

		return riskCaseAbilityRecordMapper.riskCaseAbilityRecordPage(pageIndex, pageSize, columnList, typeFlag, key, date, deptId);
	}

	@Override
	public Integer getRiskCaseAbilityRecordPageCount(List<String> columnList, Integer typeFlag, String key,
													 String date, Integer deptId) {
		return riskCaseAbilityRecordMapper.getRiskCaseAbilityRecordPageCount(columnList, typeFlag, key, date, deptId);
	}

	@Override
	public Integer getIdByDateAndPoliceId(String date, String policeId, Integer id) {
		return riskCaseAbilityRecordMapper.getIdByDateAndPoliceId(date, policeId, id);
	}

}

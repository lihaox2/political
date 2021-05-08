package com.bayee.political.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskConductBureauRuleRecord;
import com.bayee.political.mapper.RiskConductBureauRuleRecordMapper;
import com.bayee.political.service.RiskConductBureauRuleRecordService;

import java.util.List;

@Service
public class RiskConductBureauRuleRecordServiceImpl implements RiskConductBureauRuleRecordService{
	
	@Autowired
	private RiskConductBureauRuleRecordMapper riskConductBureauRuleRecordMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskConductBureauRuleRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RiskConductBureauRuleRecord record) {
		// TODO Auto-generated method stub
		return riskConductBureauRuleRecordMapper.insert(record);
	}

	@Override
	public int insertSelective(RiskConductBureauRuleRecord record) {
		// TODO Auto-generated method stub
		return riskConductBureauRuleRecordMapper.insertSelective(record);
	}

	@Override
	public RiskConductBureauRuleRecord selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskConductBureauRuleRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RiskConductBureauRuleRecord record) {
		// TODO Auto-generated method stub
		return riskConductBureauRuleRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RiskConductBureauRuleRecord record) {
		// TODO Auto-generated method stub
		return riskConductBureauRuleRecordMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<RiskConductBureauRuleRecord> riskConductBureauRuleRecordPage(Integer pageIndex, Integer pageSize) {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		pageIndex = (pageIndex - 1) * pageSize;

		return riskConductBureauRuleRecordMapper.riskConductBureauRuleRecordPage(pageIndex, pageSize);
	}

	@Override
	public Integer getRiskConductBureauRuleRecordPageCount() {
		return riskConductBureauRuleRecordMapper.getRiskConductBureauRuleRecordPageCount();
	}

	@Override
	public Integer countByBureauRuleType(Integer typeId) {
		return riskConductBureauRuleRecordMapper.countByBureauRuleType(typeId);
	}

}

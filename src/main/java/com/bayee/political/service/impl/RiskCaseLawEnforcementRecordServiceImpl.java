package com.bayee.political.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskCaseLawEnforcementRecord;
import com.bayee.political.mapper.RiskCaseLawEnforcementRecordMapper;
import com.bayee.political.service.RiskCaseLawEnforcementRecordService;

@Service
public class RiskCaseLawEnforcementRecordServiceImpl implements RiskCaseLawEnforcementRecordService{
	@Autowired
	private RiskCaseLawEnforcementRecordMapper riskCaseLawEnforcementRecordMapper;

	@Override
	public List<RiskCaseLawEnforcementRecord> riskCaseLawEnforcementRecordList(String policeId,String dateTime) {
		// TODO Auto-generated method stub
		return riskCaseLawEnforcementRecordMapper.riskCaseLawEnforcementRecordList(policeId,dateTime, null , 2);
	}

	@Override
	public int insertSelective(RiskCaseLawEnforcementRecord record) {
		// TODO Auto-generated method stub
		return riskCaseLawEnforcementRecordMapper.insertSelective(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return riskCaseLawEnforcementRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RiskCaseLawEnforcementRecord record) {
		return riskCaseLawEnforcementRecordMapper.insert(record);
	}

	@Override
	public RiskCaseLawEnforcementRecord selectByPrimaryKey(Integer id) {
		return riskCaseLawEnforcementRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RiskCaseLawEnforcementRecord record) {
		return riskCaseLawEnforcementRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<RiskCaseLawEnforcementRecord> riskCaseLawEnforcementRecordPage(Integer pageIndex, Integer pageSize) {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		pageIndex = (pageIndex - 1) * pageSize;

		return riskCaseLawEnforcementRecordMapper.riskCaseLawEnforcementRecordPage(pageIndex, pageSize);
	}

	@Override
	public Integer riskCaseLawEnforcementRecordPageCount() {
		return riskCaseLawEnforcementRecordMapper.riskCaseLawEnforcementRecordPageCount();
	}

}

package com.bayee.political.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskStutyUnitTrainRecord;
import com.bayee.political.mapper.RiskStutyUnitTrainRecordMapper;
import com.bayee.political.service.RiskStutyUnitTrainRecordService;

@Service
public class RiskStutyUnitTrainRecordServiceImpl implements RiskStutyUnitTrainRecordService{

	@Autowired
	RiskStutyUnitTrainRecordMapper riskStutyUnitTrainRecordMapper;
	
	@Override
	public Integer getByIdAndYearMonth(String yearMonth, String policeId) {
		// TODO Auto-generated method stub
		return riskStutyUnitTrainRecordMapper.getByIdAndYearMonth(yearMonth, policeId);
	}

	@Override
	public int updateByPrimaryKeySelective(RiskStutyUnitTrainRecord record) {
		// TODO Auto-generated method stub
		return riskStutyUnitTrainRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int insertSelective(RiskStutyUnitTrainRecord record) {
		// TODO Auto-generated method stub
		return riskStutyUnitTrainRecordMapper.insertSelective(record);
	}

}

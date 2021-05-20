package com.bayee.political.service.impl;

import com.bayee.political.pojo.dto.DutyDetailsDO;
import com.bayee.political.pojo.dto.DutyPageDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskDutyDealPoliceRecord;
import com.bayee.political.mapper.RiskDutyDealPoliceRecordMapper;
import com.bayee.political.service.RiskDutyDealPoliceRecordService;

import java.util.List;

@Service
public class RiskDutyDealPoliceRecordServiceImpl implements RiskDutyDealPoliceRecordService{

	@Autowired
	RiskDutyDealPoliceRecordMapper riskDutyDealPoliceRecordMapper;

	@Override
	public int insertSelective(RiskDutyDealPoliceRecord record) {
		// TODO Auto-generated method stub
		return riskDutyDealPoliceRecordMapper.insertSelective(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return riskDutyDealPoliceRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RiskDutyDealPoliceRecord record) {
		return riskDutyDealPoliceRecordMapper.insert(record);
	}

	@Override
	public RiskDutyDealPoliceRecord selectByPrimaryKey(Integer id) {
		return riskDutyDealPoliceRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RiskDutyDealPoliceRecord record) {
		return riskDutyDealPoliceRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<DutyPageDO> riskDutyDealPoliceRecordPage(Integer pageIndex, Integer pageSize,
														 Integer informationId, Integer errorId, String key) {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		pageIndex = (pageIndex - 1) * pageSize;
		return riskDutyDealPoliceRecordMapper.riskDutyDealPoliceRecordPage(pageIndex, pageSize, informationId, errorId, key);
	}

	@Override
	public Integer riskDutyDealPoliceRecordPageCount(Integer informationId, Integer errorId, String key) {
		return riskDutyDealPoliceRecordMapper.riskDutyDealPoliceRecordPageCount(informationId, errorId, key);
	}

	@Override
	public DutyDetailsDO findById(Integer id) {
		return riskDutyDealPoliceRecordMapper.findById(id);
	}
}

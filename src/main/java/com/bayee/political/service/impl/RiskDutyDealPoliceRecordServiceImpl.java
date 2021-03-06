package com.bayee.political.service.impl;

import com.bayee.political.domain.RiskConductVisitRecord;
import com.bayee.political.pojo.dto.DutyDetailsDO;
import com.bayee.political.pojo.dto.DutyPageDO;
import com.bayee.political.pojo.dto.RiskPoliceDutyResultDO;
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
														 List<Integer> informationId, Integer errorId, String key, Integer deptId) {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		pageIndex = (pageIndex - 1) * pageSize;
		return riskDutyDealPoliceRecordMapper.riskDutyDealPoliceRecordPage(pageIndex, pageSize, informationId, errorId, key, deptId);
	}

	@Override
	public Integer riskDutyDealPoliceRecordPageCount(List<Integer> informationId, Integer errorId, String key, Integer deptId) {
		return riskDutyDealPoliceRecordMapper.riskDutyDealPoliceRecordPageCount(informationId, errorId, key, deptId);
	}

	@Override
	public DutyDetailsDO findById(Integer id) {
		return riskDutyDealPoliceRecordMapper.findById(id);
	}

	@Override
	public Integer countAll() {
		return riskDutyDealPoliceRecordMapper.countAll();
	}

	@Override
	public Integer getReplaceErrorCount(String policeId, Integer type) {
		return riskDutyDealPoliceRecordMapper.getReplaceErrorCount(policeId, type);
	}

	@Override
	public List<RiskPoliceDutyResultDO> findDutyRecordYearAndMont(String year,
																  String month, String policeId) {
		return riskDutyDealPoliceRecordMapper.findDutyRecordYearAndMont(year, month, policeId);
	}
}

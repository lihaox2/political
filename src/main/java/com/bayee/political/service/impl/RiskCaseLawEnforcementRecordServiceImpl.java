package com.bayee.political.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.bayee.political.pojo.dto.CaseLawEnforcementDetailsDO;
import com.bayee.political.pojo.dto.CaseLawEnforcementPageDO;
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
	public List<CaseLawEnforcementPageDO> riskCaseLawEnforcementRecordPage(Integer pageIndex, Integer pageSize,
																		   String type, String key, Integer deptId) {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		pageIndex = (pageIndex - 1) * pageSize;
		List<Integer> typeList = new ArrayList<>();
		if (type != null && !"".equals(type)) {
			typeList = Arrays.asList(type.split(",")).stream().map(e -> Integer.valueOf(e)).collect(Collectors.toList());
		}

		return riskCaseLawEnforcementRecordMapper.riskCaseLawEnforcementRecordPage(pageIndex, pageSize, typeList, key, deptId);
	}

	@Override
	public Integer riskCaseLawEnforcementRecordPageCount(String type, String key, Integer deptId) {
		List<Integer> typeList = new ArrayList<>();
		if (type != null && !"".equals(type)) {
			typeList = Arrays.asList(type.split(",")).stream().map(e -> Integer.valueOf(e)).collect(Collectors.toList());
		}

		return riskCaseLawEnforcementRecordMapper.riskCaseLawEnforcementRecordPageCount(typeList, key, deptId);
	}

	@Override
	public CaseLawEnforcementDetailsDO findById(Integer id) {
		return riskCaseLawEnforcementRecordMapper.findDetailsDOById(id);
	}

	@Override
	public Integer countAll() {
		return riskCaseLawEnforcementRecordMapper.countAll();
	}

	@Override
	public Integer getPoliceReplaceErrorCount(String policeId, Integer type) {
		return riskCaseLawEnforcementRecordMapper.getPoliceReplaceErrorCount(policeId, type);
	}

	@Override
	public List<RiskCaseLawEnforcementRecord> findCaseLawEnforcementRecordYearAndMonth(String caseLawEnforcementRecordYear,
																					   String caseLawEnforcementRecordMonth,
																					   String policeId) {
		return riskCaseLawEnforcementRecordMapper.findCaseLawEnforcementRecordYearAndMonth(caseLawEnforcementRecordYear,
				caseLawEnforcementRecordMonth,policeId);
	}

	@Override
	public boolean checkPoliceDeductionScoreStatus(String policeId, String beginDate, String endDate) {
		Integer flag = riskCaseLawEnforcementRecordMapper.checkPoliceDeductionScoreStatus(policeId, beginDate, endDate);
		return !(flag != null && flag > 0);
	}

}

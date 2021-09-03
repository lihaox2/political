package com.bayee.political.service.impl;

import com.bayee.political.domain.ScreenChart;
import com.bayee.political.pojo.dto.ConductBureauRuleDetailsDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskConductBureauRuleRecord;
import com.bayee.political.mapper.RiskConductBureauRuleRecordMapper;
import com.bayee.political.service.RiskConductBureauRuleRecordService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
	public List<RiskConductBureauRuleRecord> riskConductBureauRuleRecordPage(Integer pageIndex, Integer pageSize,
																			 String type, String key, Integer deptId) {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		pageIndex = (pageIndex - 1) * pageSize;
		List<Integer> typeList = new ArrayList<>();
		if (type != null && !"".equals(type)) {
			typeList = Arrays.asList(type.split(",")).stream().map(e -> Integer.valueOf(e)).collect(Collectors.toList());
		}

		return riskConductBureauRuleRecordMapper.riskConductBureauRuleRecordPage(pageIndex, pageSize, typeList,key, deptId);
	}

	@Override
	public Integer getRiskConductBureauRuleRecordPageCount(String type, String key, Integer deptId) {
		List<Integer> typeList = new ArrayList<>();
		if (type != null && !"".equals(type)) {
			typeList = Arrays.asList(type.split(",")).stream().map(e -> Integer.valueOf(e)).collect(Collectors.toList());
		}

		return riskConductBureauRuleRecordMapper.getRiskConductBureauRuleRecordPageCount(typeList,key, deptId);
	}

	@Override
	public Integer countByBureauRuleType(Integer typeId) {
		return riskConductBureauRuleRecordMapper.countByBureauRuleType(typeId);
	}

	@Override
	public ConductBureauRuleDetailsDO findById(Integer id) {
		return riskConductBureauRuleRecordMapper.findById(id);
	}

	@Override
	public Integer countAll() {
		return riskConductBureauRuleRecordMapper.countAll();
	}

	@Override
	public List<ScreenChart> getConductBureauChart() {
		return riskConductBureauRuleRecordMapper.getConductBureauChart();
	}

	@Override
	public List<RiskConductBureauRuleRecord> findConductBureauRuleRecordYearAndMont(String conductBureauRuleRecordYear,
																					String conductBureauRuleRecordMonth,
																					String policeId) {
		return riskConductBureauRuleRecordMapper.findConductBureauRuleRecordYearAndMonth(conductBureauRuleRecordYear,
				conductBureauRuleRecordMonth, policeId);
	}

	@Override
	public Integer findIdByInspectorKey(String inspectorKey) {
		return riskConductBureauRuleRecordMapper.findIdByInspectorKey(inspectorKey);
	}

}

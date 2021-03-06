package com.bayee.political.service.impl;

import java.util.List;

import com.bayee.political.algorithm.RiskCompute;
import com.bayee.political.mapper.RiskConductMapper;
import com.bayee.political.pojo.GlobalIndexNumResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskCaseLawEnforcement;
import com.bayee.political.domain.RiskConductVisit;
import com.bayee.political.domain.ScreenDoubeChart;
import com.bayee.political.mapper.RiskConductVisitMapper;
import com.bayee.political.service.RiskConductVisitService;

@Service
public class RiskConductVisitServiceImpl implements RiskConductVisitService{
	
	@Autowired
	private RiskConductVisitMapper riskConductVisitMapper;

	@Autowired
	private RiskConductMapper riskConductMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskConductVisitMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RiskConductVisit record) {
		// TODO Auto-generated method stub
		return riskConductVisitMapper.insert(record);
	}

	@Override
	public int insertSelective(RiskConductVisit record) {
		// TODO Auto-generated method stub
		return riskConductVisitMapper.insertSelective(record);
	}

	@Override
	public RiskConductVisit selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskConductVisitMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RiskConductVisit record) {
		// TODO Auto-generated method stub
		return riskConductVisitMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RiskConductVisit record) {
		// TODO Auto-generated method stub
		return riskConductVisitMapper.updateByPrimaryKey(record);
	}

	@Override
	public RiskConductVisit riskConductVisitItem(String policeId, String dateTime, String lastMonthTime, Integer timeType) {
		RiskConductVisit conductVisit = riskConductVisitMapper.riskConductVisitItem(policeId, dateTime, lastMonthTime, timeType);
		if (conductVisit == null) {
			conductVisit = new RiskConductVisit();
			conductVisit.setPoliceId(policeId);
			conductVisit.setIndexNum(0d);
			conductVisit.setDeductionScoreCount(0);
			conductVisit.setTotalDeductionScore(0d);
		}
		if (timeType == 1) {
			GlobalIndexNumResultDO visitResultDO = riskConductMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "visit_score");

			conductVisit.setIndexNum(RiskCompute.normalizationCompute(visitResultDO.getMaxNum(), visitResultDO.getMinNum(), conductVisit.getIndexNum()));
		}
		return conductVisit;
	}

	@Override
	public List<ScreenDoubeChart> riskConductVisitChart(String policeId) {
		// TODO Auto-generated method stub
		return riskConductVisitMapper.riskConductVisitChart(policeId);
	}

}

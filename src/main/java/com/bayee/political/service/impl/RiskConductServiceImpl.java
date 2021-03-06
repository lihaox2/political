package com.bayee.political.service.impl;

import java.util.List;

import com.bayee.political.algorithm.RiskCompute;
import com.bayee.political.pojo.GlobalIndexNumResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskConduct;
import com.bayee.political.domain.ScreenDoubeChart;
import com.bayee.political.mapper.RiskConductMapper;
import com.bayee.political.service.RiskConductService;

@Service
public class RiskConductServiceImpl implements RiskConductService{
	
	@Autowired
	private RiskConductMapper riskConductMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskConductMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RiskConduct record) {
		// TODO Auto-generated method stub
		return riskConductMapper.insert(record);
	}

	@Override
	public int insertSelective(RiskConduct record) {
		// TODO Auto-generated method stub
		return riskConductMapper.insertSelective(record);
	}

	@Override
	public RiskConduct selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskConductMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RiskConduct record) {
		// TODO Auto-generated method stub
		return riskConductMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RiskConduct record) {
		// TODO Auto-generated method stub
		return riskConductMapper.updateByPrimaryKey(record);
	}

	@Override
	public RiskConduct riskConductItem(String policeId, String dateTime, String lastMonthTime, Integer timeType) {
		RiskConduct conduct = riskConductMapper.riskConductItem(policeId, dateTime,lastMonthTime,timeType);
		if (conduct != null && timeType == 1) {
			GlobalIndexNumResultDO indexResultDO = riskConductMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "index_num");
			GlobalIndexNumResultDO bureauRuleResultDO = riskConductMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "bureau_rule_score");
			GlobalIndexNumResultDO visitResultDO = riskConductMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "visit_score");
			GlobalIndexNumResultDO relevantResultDO = riskConductMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "relevant_score");

			conduct.setIndexNum(RiskCompute.normalizationCompute(indexResultDO.getMaxNum(), indexResultDO.getMinNum(), conduct.getIndexNum()));
			conduct.setBureauRuleScore(RiskCompute.normalizationCompute(bureauRuleResultDO.getMaxNum(), bureauRuleResultDO.getMinNum(), conduct.getBureauRuleScore()));
			conduct.setVisitScore(RiskCompute.normalizationCompute(visitResultDO.getMaxNum(), visitResultDO.getMinNum(), conduct.getVisitScore()));
			conduct.setRelevantScore(RiskCompute.normalizationCompute(relevantResultDO.getMaxNum(), relevantResultDO.getMinNum(), conduct.getRelevantScore()));
		}
		return conduct;
	}

	@Override
	public List<ScreenDoubeChart> riskConductChart(String policeId) {
		// TODO Auto-generated method stub
		return riskConductMapper.riskConductChart(policeId);
	}

	@Override
	public void insertRiskConductList(List<RiskConduct> riskConductList) {
		riskConductMapper.insertRiskConductList(riskConductList);
	}

}

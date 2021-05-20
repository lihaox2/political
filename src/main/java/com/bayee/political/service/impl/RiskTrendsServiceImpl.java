package com.bayee.political.service.impl;

import java.util.List;
import java.util.Map;

import com.bayee.political.pojo.dto.RiskAlarmTypeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskAlarm;
import com.bayee.political.domain.RiskReportRecord;
import com.bayee.political.domain.RiskTrends;
import com.bayee.political.mapper.RiskAlarmMapper;
import com.bayee.political.mapper.RiskTrendsMapper;
import com.bayee.political.service.RiskTrendsService;
@Service
public class RiskTrendsServiceImpl implements RiskTrendsService{
	
	@Autowired
	private RiskTrendsMapper riskTrendsMapper;
	
	@Autowired
	private RiskAlarmMapper riskAlarmMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskTrendsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RiskTrends record) {
		// TODO Auto-generated method stub
		return riskTrendsMapper.insert(record);
	}

	@Override
	public int insertSelective(RiskTrends record) {
		// TODO Auto-generated method stub
		return riskTrendsMapper.insertSelective(record);
	}

	@Override
	public RiskTrends selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskTrendsMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RiskTrends record) {
		// TODO Auto-generated method stub
		return riskTrendsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RiskTrends record) {
		// TODO Auto-generated method stub
		return riskTrendsMapper.updateByPrimaryKey(record);
	}

	@Override
	public Integer selectRiskTotal(String startTime, String endTime) {
		// TODO Auto-generated method stub
		return riskTrendsMapper.selectRiskTotal(startTime, endTime);
	}

	@Override
	public List<Map<String,Object>>  selectRiskTrends() {
		// TODO Auto-generated method stub
		return riskTrendsMapper.selectRiskTrends();
	}

	@Override
	public List<Map<String,Object>> selectDeptTopFive(String startTime, String endTime) {
		// TODO Auto-generated method stub
		return riskTrendsMapper.selectDeptTopFive(startTime, endTime);
	}

	@Override
	public List<RiskReportRecord> selectPoliceRiskTopTen(String sortName, String dateTime, String lastMonthTime,
			String orderName) {
		// TODO Auto-generated method stub
		return riskTrendsMapper.selectPoliceRiskTopTen(sortName, dateTime, lastMonthTime, orderName);
	}

	@Override
	public List<Map<String, Object>> selectPolice(String content) {
		// TODO Auto-generated method stub
		return riskTrendsMapper.selectPolice(content);
	}

	@Override
	public Integer comprehensiveIndex(String dateTime, String lastMonthTime) {
		// TODO Auto-generated method stub
		return riskTrendsMapper.comprehensiveIndex(dateTime, lastMonthTime);
	}

	@Override
	public Integer drinkIndex(String dateTime, String lastMonthTime) {
		// TODO Auto-generated method stub
		return riskTrendsMapper.drinkIndex(dateTime, lastMonthTime);
	}

	@Override
	public Integer conductIndex(String dateTime, String lastMonthTime) {
		// TODO Auto-generated method stub
		return riskTrendsMapper.conductIndex(dateTime, lastMonthTime);
	}

	@Override
	public Integer caseIndex(String dateTime, String lastMonthTime) {
		// TODO Auto-generated method stub
		return riskTrendsMapper.caseIndex(dateTime, lastMonthTime);
	}

	@Override
	public Integer dutyIndex(String dateTime, String lastMonthTime) {
		// TODO Auto-generated method stub
		return riskTrendsMapper.dutyIndex(dateTime, lastMonthTime);
	}

	@Override
	public Integer trainIndex(String dateTime, String lastMonthTime) {
		// TODO Auto-generated method stub
		return riskTrendsMapper.trainIndex(dateTime, lastMonthTime);
	}

	@Override
	public Integer studyIndex(String dateTime, String lastMonthTime) {
		// TODO Auto-generated method stub
		return riskTrendsMapper.studyIndex(dateTime, lastMonthTime);
	}

	@Override
	public Integer healthIndex(String dateTime, String lastMonthTime) {
		// TODO Auto-generated method stub
		return riskTrendsMapper.healthIndex(dateTime, lastMonthTime);
	}

	@Override
	public List<Map<String,Object>> avgNum(String sortName) {
		// TODO Auto-generated method stub
		return riskTrendsMapper.avgNum(sortName);
	}

	@Override
	public List<Map<String,Object>> riskPersonnelTrend(Integer alamType) {
		// TODO Auto-generated method stub
		return riskTrendsMapper.riskPersonnelTrend(alamType);
	}

	@Override
	public Map<String,Object> theMonthAlamTotal() {
		// TODO Auto-generated method stub
		return riskTrendsMapper.theMonthAlamTotal();
	}

	@Override
	public List<RiskAlarm> theMonthAlarm() {
		// TODO Auto-generated method stub
		return riskAlarmMapper.theMonthAlarm();
	}

	@Override
	public String findAlarmType(Integer id) {
		// TODO Auto-generated method stub
		return riskAlarmMapper.findAlarmType(id);
	}

	@Override
	public List<Map<String, Object>> findByPoliceIdRiskAlarm(String policeId) {
		// TODO Auto-generated method stub
		return riskAlarmMapper.findByPoliceIdRiskAlarm(policeId);
	}

	@Override
	public List<Map<String, Object>> continuityAlarm() {
		// TODO Auto-generated method stub
		return riskAlarmMapper.continuityAlarm();
	}

	@Override
	public List<Map<String, Object>> continuityAlarmDetails(String policeId) {
		// TODO Auto-generated method stub
		return riskAlarmMapper.continuityAlarmDetails(policeId);
	}

	@Override
	public Integer countRiskAlarmByPolice() {
		return riskAlarmMapper.countRiskAlarmByPolice();
	}

	@Override
	public List<RiskAlarmTypeDO> getRiskAlarmType() {
		return riskTrendsMapper.getRiskAlarmType();
	}

	@Override
	public List<RiskAlarm> theMonthAlarm(Integer alarmType) {
		return riskAlarmMapper.theMonthAlarmByAlarmType(alarmType);
	}

	@Override
	public Integer selectIndexRiskTotal() {
		// TODO Auto-generated method stub
		return riskTrendsMapper.selectIndexRiskTotal();
	}

	@Override
	public Integer selectTheMonthRiskTotal(String month) {
		// TODO Auto-generated method stub
		return riskTrendsMapper.selectTheMonthRiskTotal(month);
	}

	@Override
	public List<Map<String, Object>> caseLawTrends() {
		// TODO Auto-generated method stub
		return riskTrendsMapper.caseLawTrends();
	}

	@Override
	public Integer caseLawPepolNum() {
		// TODO Auto-generated method stub
		return riskTrendsMapper.caseLawPepolNum();
	}

	@Override
	public Integer caseLawThisMonthNum() {
		// TODO Auto-generated method stub
		return riskTrendsMapper.caseLawThisMonthNum();
	}

	@Override
	public Integer caseLawRepeatNum() {
		// TODO Auto-generated method stub
		return riskTrendsMapper.caseLawRepeatNum();
	}

	@Override
	public List<Map<String, Object>> dutyDealTrends() {
		// TODO Auto-generated method stub
		return riskTrendsMapper.dutyDealTrends();
	}

	@Override
	public Integer dutyDealPepolNum() {
		// TODO Auto-generated method stub
		return riskTrendsMapper.dutyDealPepolNum();
	}

	@Override
	public Integer dutyDealThisMonthNum() {
		// TODO Auto-generated method stub
		return riskTrendsMapper.dutyDealThisMonthNum();
	}

	@Override
	public Integer dutyDealRepeatNum() {
		// TODO Auto-generated method stub
		return riskTrendsMapper.dutyDealRepeatNum();
	}

	@Override
	public Map<String, Object> ishealth() {
		// TODO Auto-generated method stub
		return riskTrendsMapper.ishealth();
	}

	@Override
	public Map<String, Object> nohealth() {
		// TODO Auto-generated method stub
		return riskTrendsMapper.nohealth();
	}

	@Override
	public Integer inspectNums() {
		// TODO Auto-generated method stub
		return riskTrendsMapper.inspectNum();
	}

	@Override
	public Integer healthNum() {
		// TODO Auto-generated method stub
		return riskTrendsMapper.healthNum();
	}

	@Override
	public Integer healthRiskNum() {
		// TODO Auto-generated method stub
		return riskTrendsMapper.healthRiskNum();
	}

	@Override
	public Integer qualifiedNum() {
		// TODO Auto-generated method stub
		return riskTrendsMapper.qualifiedNum();
	}

	@Override
	public Double qualifiedRate() {
		// TODO Auto-generated method stub
		return riskTrendsMapper.qualifiedRate();
	}

	@Override
	public List<Map<String, Object>> qualifiedRateEcharts() {
		// TODO Auto-generated method stub
		return riskTrendsMapper.qualifiedRateEcharts();
	}

}

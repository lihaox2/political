package com.bayee.political.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bayee.political.algorithm.RiskCompute;
import com.bayee.political.json.ChartResult;
import com.bayee.political.mapper.RiskReportRecordMapper;
import com.bayee.political.pojo.GlobalIndexNumResultDO;
import com.bayee.political.pojo.dto.RiskAlarmTypeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskAlarm;
import com.bayee.political.domain.RiskReportRecord;
import com.bayee.political.domain.RiskTrends;
import com.bayee.political.mapper.RiskAlarmMapper;
import com.bayee.political.mapper.RiskTrendsMapper;
import com.bayee.political.service.RiskTrendsService;

import javax.xml.crypto.Data;

@Service
public class RiskTrendsServiceImpl implements RiskTrendsService{
	
	@Autowired
	private RiskTrendsMapper riskTrendsMapper;
	
	@Autowired
	private RiskAlarmMapper riskAlarmMapper;

	@Autowired
	RiskReportRecordMapper riskReportRecordMapper;// 警员风险记录

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

	/**
	 * 查询本年度存在风险的人数
	 * @param year 年份
	 * @return
	 */
	@Override
	public Integer selectRiskTotals(String year) {
		return riskTrendsMapper.selectRiskTotals(year);
	}

	@Override
	public List<Map<String,Object>>  selectRiskTrends() {
		// TODO Auto-generated method stub
		return riskTrendsMapper.selectRiskTrends();
	}

	@Override
	public List<Map<String,Object>> selectDeptTopFive(String startTime, String endTime) {
		// TODO Auto-generated method stub
		System.out.println(startTime+"=========="+endTime);
		return riskTrendsMapper.selectDeptTopFive();
	}

	@Override
	public List<RiskReportRecord> selectPoliceRiskTopTen(String sortName, String dateTime, String lastMonthTime,
			String orderName) {
		List<RiskReportRecord> result = riskTrendsMapper.selectPoliceRiskTopTen(sortName, dateTime, lastMonthTime, orderName);

		GlobalIndexNumResultDO indexDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "total_num");
		GlobalIndexNumResultDO conductDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "conduct_num");
		GlobalIndexNumResultDO caseDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "handling_case_num");
		GlobalIndexNumResultDO dutyDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "duty_num");
		GlobalIndexNumResultDO trainDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "train_num");
		GlobalIndexNumResultDO socialContactDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "social_contact_num");
		GlobalIndexNumResultDO amilyEvaluationDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "amily_evaluation_num");
		GlobalIndexNumResultDO healthDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "health_num");

		return result.parallelStream().peek(e -> {
			e.setTotalNum(RiskCompute.normalizationCompute(indexDO.getMaxNum(), indexDO.getMinNum(), e.getTotalNum()));
			e.setConductNum(RiskCompute.normalizationCompute(conductDO.getMaxNum(), conductDO.getMinNum(), e.getConductNum()));
			e.setHandlingCaseNum(RiskCompute.normalizationCompute(caseDO.getMaxNum(), caseDO.getMinNum(), e.getHandlingCaseNum()));
			e.setDutyNum(RiskCompute.normalizationCompute(dutyDO.getMaxNum(), dutyDO.getMinNum(), e.getDutyNum()));
			e.setTrainNum(RiskCompute.normalizationCompute(trainDO.getMaxNum(), trainDO.getMinNum(), e.getTrainNum()));
			e.setSocialContactNum(RiskCompute.normalizationCompute(socialContactDO.getMaxNum(), socialContactDO.getMinNum(), e.getSocialContactNum()));
			e.setAmilyEvaluationNum(RiskCompute.normalizationCompute(amilyEvaluationDO.getMaxNum(), amilyEvaluationDO.getMinNum(), e.getAmilyEvaluationNum()));
			e.setHealthNum(RiskCompute.normalizationCompute(healthDO.getMaxNum(), healthDO.getMinNum(), e.getHealthNum()));
		}).collect(Collectors.toList());
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
	public Integer comprehensiveIndexs() {
		// TODO Auto-generated method stub
		return riskTrendsMapper.comprehensiveIndexs();
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
	public Integer VariousRisks(Integer type) {
		// TODO Auto-generated method stub
		return riskTrendsMapper.VariousRisks(type);
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
//		return riskAlarmMapper.continuityAlarm();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String format = sdf.format(date);
		return riskAlarmMapper.continuityAlarmDataList(format+"-01-01",format+"-12-31");
	}

	/**
	 * 当前的连续预警
	 * @return
	 */
	@Override
	public List<Map<String, Object>> newContinuityAlarmDateList() {
		// TODO Auto-generated method stub
		return riskAlarmMapper.newContinuityAlarmDateList();
	}

	@Override
	public List<Map<String, Object>> continuityAlarmDetails(String policeId) {
		// TODO Auto-generated method stub
		return riskAlarmMapper.continuityAlarmDetails(policeId);
	}

	@Override
	public List<Map<String, Object>> newContinuityAlarmDetails(String policeId) {
		// TODO Auto-generated method stub
		return riskAlarmMapper.newContinuityAlarmDetails(policeId);
	}
	@Override
	public Integer selectPoliceIdCount(String policeId,Integer value) {
		// TODO Auto-generated method stub
		return riskAlarmMapper.selectPoliceIdCount(policeId,value);
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

	@Override
	public List<ChartResult> riskDeptAlarmChart(String date) {
		return riskTrendsMapper.riskDeptAlarmChart(date);
	}

	@Override
	public List<ChartResult> caseDeptChart(String date) {
		return riskTrendsMapper.caseDeptChart(date);
	}

	@Override
	public List<ChartResult> dutyDeptChart(String date) {
		return riskTrendsMapper.dutyDeptChart(date);
	}

	@Override
	public List<ChartResult> physicalTrainDeptChart(Integer id) {
		return riskTrendsMapper.physicalTrainDeptChart(id);
	}

	@Override
	public List<ChartResult> firearmTrainDeptChart(Integer id) {
		return riskTrendsMapper.firearmTrainDeptChart(id);
	}

	@Override
	public List<ChartResult> healthAlarmDeptChart(String key) {
		return riskTrendsMapper.healthAlarmDeptChart(key);
	}

	/**
	 * 新本月新增预警人数
	 * @return
	 */
	@Override
	public Integer newTheMonthAlamTotal() {
		return riskTrendsMapper.newTheMonthAlamTotal();
	}

	/**
	 * 较上月新增预警人数
	 * @return
	 */
	@Override
	public Integer comparedWithLastMonthAlamTotal() {
		return riskTrendsMapper.comparedWithLastMonthAlamTotal();
	}

}

package com.bayee.political.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bayee.political.algorithm.RiskCompute;
import com.bayee.political.domain.*;
import com.bayee.political.json.ChartResult;
import com.bayee.political.mapper.*;
import com.bayee.political.pojo.GlobalIndexNumResultDO;
import com.bayee.political.pojo.RiskHistoryYearListResultDO;
import com.bayee.political.pojo.RiskReportTypeStatisticsDO;
import com.bayee.political.pojo.dto.*;

import com.bayee.political.service.RiskConductVisitService;
import com.bayee.political.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.mapper.RiskAlarmMapper;
import com.bayee.political.mapper.RiskAlarmTypeMapper;
import com.bayee.political.mapper.RiskCaseAbilityRecordMapper;
import com.bayee.political.mapper.RiskCaseLawEnforcementMapper;
import com.bayee.political.mapper.RiskCaseLawEnforcementRecordMapper;
import com.bayee.political.mapper.RiskCaseLawEnforcementTypeMapper;
import com.bayee.political.mapper.RiskCaseMapper;
import com.bayee.political.mapper.RiskCaseTestRecordMapper;
import com.bayee.political.mapper.RiskDutyDealPoliceRecordMapper;
import com.bayee.political.mapper.RiskDutyMapper;
import com.bayee.political.mapper.RiskHealthBmiStandardMapper;
import com.bayee.political.mapper.RiskHealthMapper;
import com.bayee.political.mapper.RiskHealthRecordMapper;
import com.bayee.political.mapper.RiskReportRecordMapper;
import com.bayee.political.mapper.RiskTrainMapper;
import com.bayee.political.service.RiskService;

/**
 * @author shentuqiwei
 * @version 2021年3月26日 上午10:07:52
 */
@Service
public class RiskServiceImpl implements RiskService {

	@Autowired
	RiskHealthMapper riskHealthMapper;// 警员风险指标记录

	@Autowired
	RiskHealthBmiStandardMapper riskHealthBmiStandardMapper;// 警员BMI值

	@Autowired
	RiskHealthRecordMapper riskHealthRecordMapper;// 警员体检数据记录

	@Autowired
	RiskAlarmMapper riskAlarmMapper;// 警员预警记录

	@Autowired
	RiskAlarmTypeMapper riskAlarmTypeMapper;// 警员预警类型记录

	@Autowired
	RiskReportRecordMapper riskReportRecordMapper;// 警员风险记录

	@Autowired
	RiskTrainMapper riskTrainMapper;// 警员训练风险

	@Autowired
	RiskDutyMapper riskDutyMapper;// 警员接警执勤风险

	@Autowired
	RiskDutyDealPoliceRecordMapper riskDutyDealPoliceRecordMapper;// 警员接警执勤记录

	@Autowired
	RiskCaseMapper riskCaseMapper;// 警员执法办案风险

	@Autowired
	RiskCaseAbilityRecordMapper riskCaseAbilityRecordMapper;// 警员执法能力记录

	@Autowired
	RiskCaseLawEnforcementRecordMapper riskCaseLawEnforcementRecordMapper;// 警员执法管理记录

	@Autowired
	RiskCaseLawEnforcementMapper riskCaseLawEnforcementMapper;// 警员执法管理风险

	@Autowired
	RiskCaseLawEnforcementTypeMapper riskCaseLawEnforcementTypeMapper;// 警员执法管理类型

	@Autowired
	RiskCaseTestRecordMapper riskCaseTestRecordMapper;// 警员考试记录

	@Autowired
	RiskSocialContactMapper riskSocialContactMapper;// 警员社交风险

	@Autowired
	RiskSocialContactRecordMapper riskSocialContactRecordMapper;// 警员社交记录

	/**
	 * 警员行为规范
	 */
	@Autowired
	RiskConductMapper riskConductMapper;

	/**
	 * 局规计分
	 */
	@Autowired
	RiskConductBureauRuleMapper riskConductBureauRuleMapper;

	/**
	 * 局规计分原数据
	 */
	@Autowired
	RiskConductBureauRuleRecordMapper riskConductBureauRuleRecordMapper;
	
	@Autowired
	RiskDrinkRecordMapper riskDrinkRecordMapper;// 警员饮酒记录

	@Autowired
	RiskCaseAbilityMapper riskCaseAbilityMapper;// 警员执法能力风险

	@Autowired
	RiskCaseTestMapper riskCaseTestMapper;// 警员执法考试风险

	@Autowired
	RiskConductTrafficViolationRecordMapper riskConductTrafficViolationRecordMapper;

	@Autowired
	RiskConductVisitMapper riskConductVisitMapper;

	@Override
	public int insertSelective(RiskHealth record) {
		return riskHealthMapper.insertSelective(record);
	}

	// 警员健康风险指数查询
	@Override
	public RiskHealth riskHealthIndexItem(String policeId, String dateTime) {
//		GlobalIndexNumResultDO healthDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "health_num");
		return riskHealthMapper.riskHealthIndexItem(policeId, dateTime);
	}

	// 警员健康风险指数新增
	@Override
	public int riskHealthCreat(RiskHealth record) {
		return riskHealthMapper.riskHealthCreat(record);
	}

	// 警员健康风险指数修改
	@Override
	public int riskHealthUpdate(RiskHealth record) {
		return riskHealthMapper.riskHealthUpdate(record);
	}

	// 警员预警分页查询
	@Override
	public List<RiskAlarm> riskAlarmPageList(String startTime, String endTime, Integer pageSize, Integer pageNum,String dateTime) {
		return riskAlarmMapper.riskAlarmPageList(startTime, endTime, pageSize, pageNum,dateTime);
	}

	// 警员预警列表总数
	@Override
	public int riskAlarmPageCount(String startTime, String endTime,String dateTime) {
		return riskAlarmMapper.riskAlarmPageCount(startTime, endTime,dateTime);
	}

	// 警员风险分页查询
	@Override
	public List<RiskReportRecord> riskPageList(String keyWords, Integer alarmType, String sortName, String dateTime,
			String lastDateTime, String lastMonthTime, Integer pageSize, Integer pageNum,Integer num,String orderName, Integer deptId) {
		List<RiskReportRecord> result = riskReportRecordMapper.riskPageList(keyWords, alarmType, sortName, dateTime, lastDateTime, lastMonthTime,
				pageSize, pageNum,num,orderName,deptId);

		GlobalIndexNumResultDO currentMonth = riskReportRecordMapper.findRiskReportRecordGlobalTotalNum(dateTime);
		GlobalIndexNumResultDO lastMonth = riskReportRecordMapper.findRiskReportRecordGlobalTotalNum(lastDateTime);
		GlobalIndexNumResultDO indexDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "total_num");
		GlobalIndexNumResultDO conductDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "conduct_num");
		GlobalIndexNumResultDO caseDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "handling_case_num");
		GlobalIndexNumResultDO dutyDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "duty_num");
		GlobalIndexNumResultDO trainDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "train_num");
		GlobalIndexNumResultDO socialContactDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "social_contact_num");
		GlobalIndexNumResultDO amilyEvaluationDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "amily_evaluation_num");
		GlobalIndexNumResultDO healthDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "health_num");

		return result.parallelStream().peek(e -> {
			e.setCurrentTotalNum(RiskCompute.normalizationCompute(currentMonth.getMaxNum(), currentMonth.getMinNum(), e.getCurrentTotalNum()));
			e.setTotalNum(RiskCompute.normalizationCompute(indexDO.getMaxNum(), indexDO.getMinNum(), e.getTotalNum()));
			e.setConductNum(RiskCompute.normalizationCompute(conductDO.getMaxNum(), conductDO.getMinNum(), e.getConductNum()));
			e.setHandlingCaseNum(RiskCompute.normalizationCompute(caseDO.getMaxNum(), caseDO.getMinNum(), e.getHandlingCaseNum()));
			e.setDutyNum(RiskCompute.normalizationCompute(dutyDO.getMaxNum(), dutyDO.getMinNum(), e.getDutyNum()));
			e.setTrainNum(RiskCompute.normalizationCompute(trainDO.getMaxNum(), trainDO.getMinNum(), e.getTrainNum()));
			e.setSocialContactNum(RiskCompute.normalizationCompute(socialContactDO.getMaxNum(), socialContactDO.getMinNum(), e.getSocialContactNum()));
			e.setAmilyEvaluationNum(RiskCompute.normalizationCompute(amilyEvaluationDO.getMaxNum(), amilyEvaluationDO.getMinNum(), e.getAmilyEvaluationNum()));
			e.setHealthNum(RiskCompute.normalizationCompute(healthDO.getMaxNum(), healthDO.getMinNum(), e.getHealthNum()));
			e.setLastTotalNum(RiskCompute.normalizationCompute(lastMonth.getMaxNum(), lastMonth.getMinNum(), e.getLastTotalNum()));
		}).collect(Collectors.toList());
	}

	// 警员风险列表总数
	@Override
	public int riskPageCount(String keyWords, Integer alarmType, String dateTime, String lastDateTime,
							 String lastMonthTime,Integer num,String orderName, Integer deptId) {
		return riskReportRecordMapper.riskPageCount(keyWords, alarmType, dateTime, lastDateTime, lastMonthTime,num,orderName, deptId);
	}

	// 警员预警类型查询
	@Override
	public List<RiskAlarmType> riskAlarmTypeList(Integer id) {
		return riskAlarmTypeMapper.riskAlarmTypeList(id);
	}

	// 警员风险雷达图
	@Override
	public List<ScreenDoubeChart> riskChartList(String policeId, String dateTime, String lastMonthTime,
												Integer timeType) {
		GlobalIndexNumResultDO conductDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "conduct_num");
		GlobalIndexNumResultDO caseDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "handling_case_num");
		GlobalIndexNumResultDO dutyDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "duty_num");
		GlobalIndexNumResultDO trainDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "train_num");
		GlobalIndexNumResultDO socialContactDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "social_contact_num");
		GlobalIndexNumResultDO amilyEvaluationDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "amily_evaluation_num");
//		GlobalIndexNumResultDO healthDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "health_num");

		List<ScreenDoubeChart> result = riskReportRecordMapper.riskChartList(policeId, dateTime, lastMonthTime, timeType);
		if (timeType == 1) {
			for (ScreenDoubeChart chart : result) {
				if ("行为风险".equals(chart.getName())) {
					chart.setValue(RiskCompute.normalizationCompute(conductDO.getMaxNum(), conductDO.getMinNum(), chart.getValue()));
				} else if ("执法风险".equals(chart.getName())) {
					chart.setValue(RiskCompute.normalizationCompute(caseDO.getMaxNum(), caseDO.getMinNum(), chart.getValue()));
				} else if ("接处警风险".equals(chart.getName())) {
					chart.setValue(RiskCompute.normalizationCompute(dutyDO.getMaxNum(), dutyDO.getMinNum(), chart.getValue()));
				} else if ("训练风险".equals(chart.getName())) {
					chart.setValue(RiskCompute.normalizationCompute(trainDO.getMaxNum(), trainDO.getMinNum(), chart.getValue()));
				} else if ("社交风险".equals(chart.getName())) {
					chart.setValue(RiskCompute.normalizationCompute(socialContactDO.getMaxNum(), socialContactDO.getMinNum(), chart.getValue()));
				} else if ("评价风险".equals(chart.getName())) {
					chart.setValue(RiskCompute.normalizationCompute(amilyEvaluationDO.getMaxNum(), amilyEvaluationDO.getMinNum(), chart.getValue()));
				}/* else if ("健康风险".equals(chart.getName())) {
					chart.setValue(RiskCompute.normalizationCompute(healthDO.getMaxNum(), healthDO.getMinNum(), chart.getValue()));
				}*/
			}
		}
		return result;
	}

	// 警员风险详情查询
	@Override
	public RiskReportRecord riskReportRecordItem(Integer id, String policeId, String dateTime, String lastDateTime,
												 String lastMonthTime, Integer timeType) {
		RiskReportRecord reportRecord = riskReportRecordMapper.riskReportRecordItem(id, policeId, dateTime, lastDateTime, lastMonthTime,
				timeType);
		if (reportRecord != null && timeType == 1) {
			GlobalIndexNumResultDO indexDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "total_num");
			GlobalIndexNumResultDO conductDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "conduct_num");
			GlobalIndexNumResultDO caseDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "handling_case_num");
			GlobalIndexNumResultDO dutyDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "duty_num");
			GlobalIndexNumResultDO trainDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "train_num");
			GlobalIndexNumResultDO socialContactDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "social_contact_num");
			GlobalIndexNumResultDO amilyEvaluationDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "amily_evaluation_num");
			GlobalIndexNumResultDO healthDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "health_num");
//			GlobalIndexNumResultDO drinkDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "drink_num");
//			GlobalIndexNumResultDO studyDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "study_num");
//			GlobalIndexNumResultDO workDO = riskReportRecordMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "work_num");

			reportRecord.setTotalNum(RiskCompute.normalizationCompute(indexDO.getMaxNum(), indexDO.getMinNum(), reportRecord.getTotalNum()));
			reportRecord.setConductNum(RiskCompute.normalizationCompute(conductDO.getMaxNum(), conductDO.getMinNum(), reportRecord.getConductNum()));
			reportRecord.setHandlingCaseNum(RiskCompute.normalizationCompute(caseDO.getMaxNum(), caseDO.getMinNum(), reportRecord.getHandlingCaseNum()));
			reportRecord.setDutyNum(RiskCompute.normalizationCompute(dutyDO.getMaxNum(), dutyDO.getMinNum(), reportRecord.getDutyNum()));
			reportRecord.setTrainNum(RiskCompute.normalizationCompute(trainDO.getMaxNum(), trainDO.getMinNum(), reportRecord.getTrainNum()));
			reportRecord.setSocialContactNum(RiskCompute.normalizationCompute(socialContactDO.getMaxNum(), socialContactDO.getMinNum(), reportRecord.getSocialContactNum()));
			reportRecord.setAmilyEvaluationNum(RiskCompute.normalizationCompute(amilyEvaluationDO.getMaxNum(), amilyEvaluationDO.getMinNum(), reportRecord.getAmilyEvaluationNum()));
			reportRecord.setHealthNum(RiskCompute.normalizationCompute(healthDO.getMaxNum(), healthDO.getMinNum(), reportRecord.getHealthNum()));
//			reportRecord.setDrinkNum(RiskCompute.normalizationCompute(drinkDO.getMaxNum(), drinkDO.getMinNum(), reportRecord.getDrinkNum()));
//			reportRecord.setStudyNum(RiskCompute.normalizationCompute(studyDO.getMaxNum(), studyDO.getMinNum(), reportRecord.getStudyNum()));
//			reportRecord.setWorkNum(RiskCompute.normalizationCompute(workDO.getMaxNum(), workDO.getMinNum(), reportRecord.getWorkNum()));
		}
		return reportRecord;
	}

	// 警员警务技能指数查询
	@Override
	public RiskTrain riskTrainIndexItem(String policeId, String dateTime, String lastMonthTime, Integer timeType) {
		RiskTrain riskTrain = riskTrainMapper.riskTrainIndexItem(policeId, dateTime, lastMonthTime, timeType);
		if (riskTrain != null && timeType == 1) {
			GlobalIndexNumResultDO resultDO = riskTrainMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "index_num");

			riskTrain.setIndexNum(RiskCompute.normalizationCompute(resultDO.getMaxNum(), resultDO.getMinNum(), riskTrain.getIndexNum()));
		}
		return riskTrain;
	}

	// 警员综合训练不合格趋势图
	@Override
	public List<ScreenChart> riskTrainFailChart(String policeId, String fieldName) {
		return riskTrainMapper.riskTrainFailChart(policeId, fieldName);
	}

	// 警员接警执勤指数查询
	@Override
	public RiskDuty riskDutyIndexItem(String policeId, String dateTime, String lastMonthTime, Integer timeType) {
		RiskDuty riskDuty = riskDutyMapper.riskDutyIndexItem(policeId, dateTime, lastMonthTime, timeType);
		if (riskDuty != null && timeType == 1) {
			GlobalIndexNumResultDO resultDO = riskDutyMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "index_num");

			riskDuty.setIndexNum(RiskCompute.normalizationCompute(resultDO.getMaxNum(), resultDO.getMinNum(), riskDuty.getIndexNum()));
		}
		return riskDuty;
	}

	// 半年内接警执勤风险指数
	@Override
	public List<ScreenDoubeChart> riskDutyIndexChart(String policeId) {
		return riskDutyMapper.riskDutyIndexChart(policeId);
	}

	// 警员接警执勤数据列表查询
	@Override
	public List<RiskDutyDealPoliceRecord> riskDutyRecordList(String policeId, String dateTime, String lastMonthTime,
															 Integer timeType) {
		return riskDutyDealPoliceRecordMapper.riskDutyRecordList(policeId, dateTime, lastMonthTime, timeType);
	}

	@Override
	public List<RiskConductTrafficViolationRecord> riskConductTrafficViolationRecordList(String policeId, String dateTime,
																						 String lastMonthTime, Integer timeType) {

		return riskConductTrafficViolationRecordMapper.findRiskConductTrafficViolationRecordList(policeId, dateTime,
				lastMonthTime, timeType);
	}

	// 警员执法办案风险指数查询
	@Override
	public RiskCase riskCaseIndexItem(String policeId, String dateTime, String lastMonthTime, Integer timeType) {
		RiskCase riskCase = riskCaseMapper.riskCaseIndexItem(policeId, dateTime, lastMonthTime, timeType);
		if (riskCase != null && timeType == 1) {
			GlobalIndexNumResultDO indexResultDO = riskCaseMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "index_num");
			GlobalIndexNumResultDO abilityResultDO = riskCaseMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "ability_num");
			GlobalIndexNumResultDO lawEnforcementResultDO = riskCaseMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "law_enforcement_num");
			GlobalIndexNumResultDO testResultDO = riskCaseMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "test_num");

			riskCase.setIndexNum(RiskCompute.normalizationCompute(indexResultDO.getMaxNum(), indexResultDO.getMinNum(), riskCase.getIndexNum()));
			riskCase.setAbilityNum(RiskCompute.normalizationCompute(abilityResultDO.getMaxNum(), abilityResultDO.getMinNum(), riskCase.getAbilityNum()));
			riskCase.setLawEnforcementNum(RiskCompute.normalizationCompute(lawEnforcementResultDO.getMaxNum(), lawEnforcementResultDO.getMinNum(), riskCase.getLawEnforcementNum()));
			riskCase.setTestNum(RiskCompute.normalizationCompute(testResultDO.getMaxNum(), testResultDO.getMinNum(), riskCase.getTestNum()));
		}
		return riskCase;
	}

	// 执法办案风险指数图例
	@Override
	public List<ScreenDoubeChart> riskCaseIndexChart(String policeId) {
		return riskCaseMapper.riskCaseIndexChart(policeId);
	}

	// 综合指数风险
	@Override
	public RiskIndexMonitorChild comprehensiveIndex(String dateTime,Integer num) {
		return riskReportRecordMapper.comprehensiveIndex(dateTime,num);
	}

	// 行为规范风险
	@Override
	public RiskIndexMonitorChild conductIndex(String dateTime,Integer num) {
		return riskReportRecordMapper.conductIndex(dateTime,num);
	}

	// 执法办案风险
	@Override
	public RiskIndexMonitorChild caseIndex(String dateTime,Integer num) {
		return riskReportRecordMapper.caseIndex(dateTime,num);
	}

	// 接警执勤风险
	@Override
	public RiskIndexMonitorChild dutyIndex(String dateTime,Integer num) {
		return riskReportRecordMapper.dutyIndex(dateTime,num);
	}

	// 警务技能风险
	@Override
	public RiskIndexMonitorChild trainIndex(String dateTime,Integer num) {
		return riskReportRecordMapper.trainIndex(dateTime,num);
	}

	// 社交风险
	@Override
	public RiskIndexMonitorChild socialContactIndex(String dateTime,Integer num) {
		return riskReportRecordMapper.socialContactIndex(dateTime,num);
	}

	// 家属评价风险
	@Override
	public RiskIndexMonitorChild familyEvaluationIndex(String dateTime,Integer num) {
		return riskReportRecordMapper.familyEvaluationIndex(dateTime,num);
	}

	// 健康风险
	@Override
	public RiskIndexMonitorChild healthIndex(String year, String dateTime,Integer num) {
		return riskReportRecordMapper.healthIndex(year, dateTime,num);
	}

	// 警员历史风险报告查询
	@Override
	public List<RiskHistoryReport> riskHistoryReportList(String policeId, String dateTime) {
		List<RiskHistoryReport> result = riskReportRecordMapper.riskHistoryReportList(policeId, dateTime);

		return result.parallelStream().peek(e -> {
			GlobalIndexNumResultDO currentMonth = riskReportRecordMapper.findRiskReportRecordGlobalTotalNum(e.getDateTime());

			e.setTotalNum(RiskCompute.normalizationCompute(currentMonth.getMaxNum(), currentMonth.getMinNum(), e.getTotalNum()));
		}).collect(Collectors.toList());
	}

	// 警员警务技能统计查询
	@Override
	public RiskTrain riskTrainStatisticsItem(String policeId, String dateTime) {
		return riskTrainMapper.riskTrainStatisticsItem(policeId, dateTime);
	}

	// 警员警务技能新增
	@Override
	public int riskTrainCreat(RiskTrain record) {
		return riskTrainMapper.riskTrainCreat(record);
	}

	// 警员警务技能修改
	@Override
	public int riskTrainUpdate(RiskTrain record) {
		return riskTrainMapper.riskTrainUpdate(record);
	}

	// 警员警员指标历史记录新增
	@Override
	public int riskReportRecordCreat(RiskReportRecord record) {
		return riskReportRecordMapper.riskReportRecordCreat(record);
	}

	// 警员社交风险查询
	@Override
	public RiskSocialContact riskSocialContactIndexItem(String policeId, String dateTime, String lastMonthTime, Integer timeType) {
		return riskSocialContactMapper.riskSocialContactIndexItem(policeId, dateTime,lastMonthTime,timeType);
	}

	// 社交风险指数图例
	@Override
	public List<ScreenDoubeChart> riskSocialContactIndexChart(String policeId, String tableName) {
		return riskSocialContactMapper.riskSocialContactIndexChart(policeId, tableName);
	}

	@Override
	public RiskHealth riskHealthIndexNewestItem(String policeId) {
		return riskHealthMapper.riskHealthIndexNewestItem(policeId);
	}

	// 社交详情记录
	@Override
	public List<RiskSocialContactRecord> riskSocialContactRecordList(Integer socialContactId) {
		return riskSocialContactRecordMapper.riskSocialContactRecordList(socialContactId);
	}

	// 警员执法管理风险查询
	@Override
	public RiskCaseLawEnforcement riskCaseLawEnforcementIndexItem(String policeId, String dateTime,
																  String lastMonthTime, Integer timeType) {
		RiskCaseLawEnforcement lawEnforcement = riskCaseLawEnforcementMapper.riskCaseLawEnforcementIndexItem(policeId, dateTime, lastMonthTime,
				timeType);
		if (lawEnforcement == null) {
			lawEnforcement = new RiskCaseLawEnforcement();
			lawEnforcement.setIsDisplay(0);
			lawEnforcement.setPoliceId(policeId);
//			lawEnforcement.setIndexNum();
			lawEnforcement.setTotalDeductionScore(0d);
			lawEnforcement.setTotalDeductionCount(0);
		}
		if (timeType == 1) {
			GlobalIndexNumResultDO resultDO = riskCaseMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "law_enforcement_num");

			lawEnforcement.setIndexNum(RiskCompute.normalizationCompute(resultDO.getMaxNum(), resultDO.getMinNum(), lawEnforcement.getIndexNum()));
		}
		return lawEnforcement;
	}

	// 警员执法管理数据列表查询
	@Override
	public List<RiskCaseLawEnforcementRecord> riskCaseLawEnforcementRecordList(String policeId, String dateTime,
																			   String lastMonthTime, Integer timeType) {
		return riskCaseLawEnforcementRecordMapper.riskCaseLawEnforcementRecordList(policeId, dateTime, lastMonthTime,
				timeType);
	}

	// 警员执法能力风险查询
	@Override
	public RiskCaseAbility riskCaseAbilityIndexItem(String policeId, String dateTime, String lastMonthTime,
													Integer timeType) {
		RiskCaseAbility ability = riskCaseAbilityMapper.riskCaseAbilityIndexItem(policeId, dateTime, lastMonthTime, timeType);
		if (ability != null && timeType == 1) {
			GlobalIndexNumResultDO resultDO = riskCaseMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "ability_num");

			ability.setIndexNum(RiskCompute.normalizationCompute(resultDO.getMaxNum(), resultDO.getMinNum(), ability.getIndexNum()));
		}
		return ability;
	}

	// 警员执法考试风险查询
	@Override
	public RiskCaseTest riskCaseTestIndexItem(String policeId, String dateTime, String lastMonthTime, Integer timeType) {
		RiskCaseTest caseTest = riskCaseTestMapper.riskCaseTestIndexItem(policeId, dateTime, lastMonthTime, timeType);
		if (caseTest != null && timeType == 1) {
			GlobalIndexNumResultDO resultDO = riskCaseMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "test_num");

			caseTest.setIndexNum(RiskCompute.normalizationCompute(resultDO.getMaxNum(), resultDO.getMinNum(), caseTest.getIndexNum()));
		}
		return caseTest;
	}

	// 警员执法考试数据列表查询
	@Override
	public List<RiskCaseTestRecord> riskCaseTestRecordList(String policeId, String dateTime, String lastMonthTime,
														   Integer timeType) {
		return riskCaseTestRecordMapper.riskCaseTestRecordList(policeId, dateTime, lastMonthTime, timeType);
	}

	// 警员局规计分风险查询
	@Override
	public RiskConductBureauRule riskConductBureauRuleIndexItem(String policeId, String dateTime, String lastMonthTime,
																Integer timeType) {
		RiskConductBureauRule bureauRule = riskConductBureauRuleMapper.riskConductBureauRuleIndexItem(policeId, dateTime, lastMonthTime, timeType);
		if (bureauRule == null) {
			bureauRule = new RiskConductBureauRule();
			bureauRule.setPoliceId(policeId);
			bureauRule.setIndexNum(0d);
			bureauRule.setDeductionScoreCount(0);
			bureauRule.setTotalDeductionScore(0d);
		}

		if (timeType == 1) {
			GlobalIndexNumResultDO bureauRuleResultDO = riskConductMapper.findGlobalIndexNumByYear(lastMonthTime, dateTime, "bureau_rule_score");

			bureauRule.setIndexNum(RiskCompute.normalizationCompute(bureauRuleResultDO.getMaxNum(), bureauRuleResultDO.getMinNum(), bureauRule.getIndexNum()));
		}
		return bureauRule;
	}

	@Override
	public RiskConductBureauRoleResultDTO riskConductBureauRole(String policeId, String dateTime, String lastDateTime) {
		RiskConductBureauRoleResultDTO resultDTO = new RiskConductBureauRoleResultDTO();
		RiskConductBureauRule bureauRule = riskConductBureauRuleMapper.findRiskConductBureauRole(policeId, dateTime);
		if(bureauRule != null){
			resultDTO.setIndexNum(bureauRule.getIndexNum());
			resultDTO.setDeductionScoreCount(bureauRule.getDeductionScoreCount());
			resultDTO.setTotalDeductionScore(bureauRule.getTotalDeductionScore());

			ScreenDoubeChart chart1 = new ScreenDoubeChart();
			RiskConductBureauRule bureauRuleUpMonth = riskConductBureauRuleMapper.findRiskConductBureauRole(policeId, lastDateTime);
			if (bureauRuleUpMonth != null) {
				chart1.setId(1);
				chart1.setName("上月");
				chart1.setValue(bureauRuleUpMonth.getIndexNum());
			}

			ScreenDoubeChart chart2 = new ScreenDoubeChart();
			chart2.setId(2);
			chart2.setName("本月");
			chart2.setValue(resultDTO.getIndexNum());
			resultDTO.setMonthList(Arrays.asList(chart1, chart2));
		}
		return resultDTO;
	}

	@Override
	public List<ScreenDoubeChart> riskConductBureauRoleChart(String policeId) {
		return riskConductBureauRuleMapper.findRiskConductBureauRoleChart(policeId);
	}

	@Override
	public List<RiskConductBureauRuleRecord> findRiskConductBureauRuleRecord(String policeId, String dateTime,String lastMonthTime,Integer timeType) {
		return riskConductBureauRuleRecordMapper.findByPoliceIdAndDate(policeId, dateTime,lastMonthTime,timeType);
	}

	@Override
	public int insertInpromt(RiskDrinkRecord riskDrinkRecord) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<RiskHealth> getByYear(String year) {
		// TODO Auto-generated method stub
		return riskHealthMapper.getByYear(year);
	}

	@Override
	public Double fraction(Integer id) {
		// TODO Auto-generated method stub
		return riskHealthMapper.fraction(id);
	}

	@Override
	public int updateRiskReportRecord(RiskReportRecord record) {
		// TODO Auto-generated method stub
		return riskReportRecordMapper.riskReportRecordUpdate(record);
	}

	@Override
	public List<RiskHistoryReportTime> riskHistoryReportTimeList(String policeId) {
		// TODO Auto-generated method stub
		return riskReportRecordMapper.riskHistoryReportTimeList(policeId);
	}
	
	@Override
	public Integer getByIdAndYear(String policeId, String year) {
		return riskHealthMapper.getByIdAndYear(policeId, year);
	}
	
	@Override
	public Double selectTotalNum(Integer id) {
		return riskHealthMapper.selectTotalNum(id);
	}
	
	@Override
	public Integer insertRiskReportRecord(RiskReportRecord record) {
		return riskReportRecordMapper.insertSelective(record);
	}
	
	@Override
	public List<String> getAllByYear(String year) {
		return riskHealthMapper.getAllByYear(year);
	}

	@Override
	public RiskCaseLawEnforcementReportDO lawEnforcementReportDOQuery(String policeId, String dateTime,
																	  String lastMonthTime, Integer timeType) {
		return riskCaseLawEnforcementMapper.lawEnforcementReportDOQuery(policeId, dateTime, lastMonthTime, timeType);
	}

	@Override
	public RiskConductVisitReportDO visitReportDOQuery(String policeId, String dateTime,
													   String lastMonthTime, Integer timeType) {
		return riskConductVisitMapper.visitReportDOQuery(policeId, dateTime, lastMonthTime, timeType);
	}

	@Override
	public RiskConductBureauRuleReportDO bureauRuleReportDOQuery(String policeId, String dateTime,
																 String lastMonthTime, Integer timeType) {
		return riskConductBureauRuleMapper.bureauRuleReportDOQuery(policeId, dateTime, lastMonthTime, timeType);
	}

	@Override
	public RiskConductTrafficViolationReportDO trafficViolationReportDOQuery(String policeId, String dateTime,
																			 String lastMonthTime, Integer timeType) {
		return riskConductTrafficViolationRecordMapper.trafficViolationReportDOQuery(policeId, dateTime,
				lastMonthTime, timeType);
	}

	@Override
	public RiskDutyReportDO dutyReportDOQuery(String policeId, String dateTime, String lastMonthTime, Integer timeType) {
		return riskDutyMapper.dutyReportDOQuery(policeId, dateTime, lastMonthTime, timeType);
	}

	@Override
	public RiskHealthReportDO healthReportDOQuery(String policeId) {
		return riskHealthMapper.healthReportDOQuery(policeId);
	}

	@Override
	public List<RiskHistoryYearListResultDO> riskHistoryYearList(String policeId) {
		List<RiskHistoryYearListResultDO> resultDOS = riskReportRecordMapper.riskHistoryYearList(policeId);
		return resultDOS.parallelStream().peek(e -> {
			GlobalIndexNumResultDO indexDO = riskReportRecordMapper.findGlobalIndexNumByYear(e.getDateTime(), e.getLastDateTime(), "total_num");

			e.setTotalNum(RiskCompute.normalizationCompute(indexDO.getMaxNum(), indexDO.getMinNum(), e.getTotalNum()));
		}).collect(Collectors.toList());
	}

	@Override
	public List<RiskReportTypeStatisticsDO> lawEnforcementReportTypeDOQuery(String policeId, String dateTime,
																			String lastMonthTime, Integer timeType) {
		return riskCaseLawEnforcementTypeMapper.lawEnforcementReportTypeDOQuery(policeId, dateTime, lastMonthTime, timeType);
	}

	@Override
	public List<RiskReportTypeStatisticsDO> conductVisitReportTypeDOQuery(String policeId, String dateTime,
																		  String lastMonthTime, Integer timeType) {
		return riskConductVisitMapper.conductVisitReportTypeDOQuery(policeId, dateTime, lastMonthTime, timeType);
	}

	@Override
	public List<RiskReportTypeStatisticsDO> conductBureauRuleReportTypeDOQuery(String policeId, String dateTime,
																			   String lastMonthTime, Integer timeType) {
		return riskConductBureauRuleRecordMapper.conductBureauRuleReportTypeDOQuery(policeId, dateTime, lastMonthTime, timeType);
	}

	@Override
	public List<RiskReportTypeStatisticsDO> dutyReportTypeDOQuery(String policeId, String dateTime,
																  String lastMonthTime, Integer timeType) {
		return riskDutyDealPoliceRecordMapper.dutyReportTypeDOQuery(policeId, dateTime, lastMonthTime, timeType);
	}

	@Override
	public GlobalIndexNumResultDO findRiskHealthGlobalIndexNum(String date) {
		return riskHealthMapper.findGlobalIndexNum(date);
	}

}

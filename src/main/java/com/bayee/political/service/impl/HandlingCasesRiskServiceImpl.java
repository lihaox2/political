package com.bayee.political.service.impl;

import com.bayee.political.domain.*;
import com.bayee.political.enums.AlarmTypeEnum;
import com.bayee.political.mapper.*;
import com.bayee.political.service.HandlingCasesRiskService;
import com.bayee.political.service.RiskAlarmService;
import com.bayee.political.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * @author xxl
 * @date 2021/3/30
 */
@Service
public class HandlingCasesRiskServiceImpl implements HandlingCasesRiskService {

	@Autowired
	RiskCaseAbilityRecordMapper riskCaseAbilityRecordMapper;

	@Autowired
	RiskCaseAbilityMapper riskCaseAbilityMapper;

	@Autowired
	RiskCaseLawEnforcementRecordMapper riskCaseLawEnforcementRecordMapper;

	@Autowired
	RiskCaseLawEnforcementMapper riskCaseLawEnforcementMapper;

	@Autowired
	RiskCaseTestRecordMapper riskCaseTestRecordMapper;

	@Autowired
	RiskCaseMapper riskCaseMapper;

	@Autowired
	RiskAlarmService riskAlarmService;

	@Autowired
	RiskCaseTestMapper riskCaseTestMapper;

	/**
	 * 最大指标值
	 */
	private double maxScore = 25d;
	/**
	 * 预警值
	 */
	private double alarmScore = 10d;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public RiskCase handlingCasesRiskDetails(User user, LocalDate localDate) {
		String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		RiskCase riskCase = new RiskCase();
		riskCase.setIndexNum(0d);
		riskCase.setAbilityNum(0d);
		riskCase.setLawEnforcementNum(0d);
		riskCase.setTestNum(0d);
		riskCase.setPoliceId(user.getPoliceId());

		// 执法能力处理
		RiskCaseAbility riskCaseAbility = handlingCasesAbilityRiskDetails(user, date);
		if (riskCaseAbility != null) {
			if (riskCaseAbility.getId() == null) {
				riskCaseAbilityMapper.insertRiskCaseAbility(riskCaseAbility);
			}
			riskCase.setAbilityNum(riskCaseAbility.getIndexNum());
		}

		// 执法管理处理
		RiskCaseLawEnforcement riskCaseLawEnforcement = handlingCasesManageRiskDetails(user, date);
		if (riskCaseLawEnforcement != null) {
			if (riskCaseLawEnforcement.getId() == null) {
				riskCaseLawEnforcementMapper.insert(riskCaseLawEnforcement);
			}
			riskCase.setLawEnforcementNum(riskCaseLawEnforcement.getIndexNum());
		}

		// 执法考试处理
		RiskCaseTest riskCaseTest = handlingCasesExamRiskDetails(user, date);
		if (riskCaseTest != null) {
			if (riskCaseTest.getId() == null) {
				riskCaseTestMapper.insert(riskCaseTest);
			}
			riskCase.setTestNum(riskCaseTest.getIndexNum());
		}
		riskCase.setIndexNum(Math.min((riskCase.getAbilityNum() + riskCase.getLawEnforcementNum()
				+ riskCase.getTestNum()), maxScore));

		RiskCase olsRiskCase = riskCaseMapper.findPoliceRiskCase(user.getPoliceId(), date);
		if (olsRiskCase != null && olsRiskCase.getId() != null) {
			riskCase.setId(olsRiskCase.getId());

			olsRiskCase.setIndexNum(riskCase.getIndexNum());
			olsRiskCase.setAbilityNum(riskCase.getAbilityNum());
			olsRiskCase.setLawEnforcementNum(riskCase.getLawEnforcementNum());
			olsRiskCase.setTestNum(riskCase.getTestNum());
			olsRiskCase.setUpdateDate(new Date());

			riskCaseMapper.updateByPrimaryKeySelective(olsRiskCase);
		}
		riskCase.setPoliceId(user.getPoliceId());
		riskCase.setCreationDate(
				DateUtils.parseDate(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), "yyyy-MM-dd"));

		// 预警处理
		if (riskCase.getIndexNum() >= alarmScore) {
			RiskAlarm riskAlarm = riskAlarmService.generateRiskAlarm(user.getPoliceId(), AlarmTypeEnum.HANDLING_CASES_RISK,
					date, riskCase.getIndexNum());

			if (riskAlarm != null) {
				riskAlarmService.insert(riskAlarm);
			}
		}

		return riskCase;
	}

	@Override
	public RiskCase handlingCasesRiskDetailsByCasesManageRisk(User user, LocalDate localDate, Double avgScore) {
		String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		Integer recordCount = riskCaseLawEnforcementRecordMapper.countPoliceCaseData(user.getPoliceId(), date);
		if (recordCount != null && recordCount > 0) {
			return null;
		}

		// 执法办案处理
		RiskCaseLawEnforcement riskCaseLawEnforcement = riskCaseLawEnforcementMapper
				.findRiskCaseLawEnforcementByPoliceIdAndDate(user.getPoliceId(), date);
		if (riskCaseLawEnforcement == null || riskCaseLawEnforcement.getId() == null) {
			return null;
		}
		riskCaseLawEnforcement.setIndexNum(avgScore);
		riskCaseLawEnforcement.setTotalDeductionScore(avgScore);
		riskCaseLawEnforcement.setTotalDeductionCount(0);
		riskCaseLawEnforcement.setUpdateDate(new Date());
		riskCaseLawEnforcementMapper.updateByPrimaryKeySelective(riskCaseLawEnforcement);

		// 办案报备总数据处理
		RiskCase olsRiskCase = riskCaseMapper.findPoliceRiskCase(user.getPoliceId(), date);
		olsRiskCase.setLawEnforcementNum(avgScore);
		olsRiskCase.setIndexNum(
				Math.min((olsRiskCase.getAbilityNum() + olsRiskCase.getLawEnforcementNum() + olsRiskCase.getTestNum()),
						maxScore));
		olsRiskCase.setUpdateDate(new Date());
		riskCaseMapper.updateByPrimaryKeySelective(olsRiskCase);

		// 预警处理
		if (olsRiskCase.getIndexNum() >= alarmScore) {
			RiskAlarm riskAlarm = riskAlarmService.generateRiskAlarm(user.getPoliceId(), AlarmTypeEnum.HANDLING_CASES_RISK,
					date, olsRiskCase.getIndexNum());

			if (riskAlarm != null) {
				riskAlarmService.insert(riskAlarm);
			}
		}
		return olsRiskCase;
	}

	@Override
	public void addRiskCaseList(List<RiskCase> riskCaseList) {
		riskCaseMapper.insertRiskCases(riskCaseList);
	}

	@Override
	public Double findPoliceAvgDeductionScoreByDate(String date) {
		return riskCaseLawEnforcementRecordMapper.findPoliceAvgDeductionScoreByDate(date);
	}

	/**
	 * 执法能力处理
	 * 
	 * @param user
	 * @param date
	 * @return
	 */
	private RiskCaseAbility handlingCasesAbilityRiskDetails(User user, String date) {
		// 校验规则

		// 扣分规则
		double reconsiderationLitigationScore = 5d;
		double letterVisitScore = 10d;
		double lawEnforcementFaultScore = 15d;
		double judicialSupervisionScore = 15d;

		RiskCaseAbility riskCaseAbility = new RiskCaseAbility();
		riskCaseAbility.setIndexNum(0d);
		riskCaseAbility.setReconsiderationLitigationScore(0d);
		riskCaseAbility.setLetterVisitScore(0d);
		riskCaseAbility.setLawEnforcementFaultScore(0d);
		riskCaseAbility.setJudicialSupervisionScore(0d);
		riskCaseAbility.setPoliceId(user.getPoliceId());
		riskCaseAbility.setCreationDate(DateUtils.parseDate(date, "yyyy-MM-dd"));

		List<RiskCaseAbilityRecord> riskCaseAbilityRecordList = riskCaseAbilityRecordMapper
				.findPoliceCaseData(user.getPoliceId(), date);
		RiskCaseAbility oldRiskCaseAbility = riskCaseAbilityMapper
				.findRiskCaseAbilityByPoliceIdAndDate(user.getPoliceId(), date);
		// 数据不处理操作
		if (riskCaseAbilityRecordList == null || riskCaseAbilityRecordList.size() <= 0) {
			// 校验这个月是否已产生过数据
			if (oldRiskCaseAbility == null || oldRiskCaseAbility.getId() == null) {
				return riskCaseAbility;
			}
			return null;
		}

		for (RiskCaseAbilityRecord record : riskCaseAbilityRecordList) {
			// 校验复议诉讼撤变
			if (record.getReconsiderationLitigationStatus() != null
					&& record.getReconsiderationLitigationStatus() == 1) {
				riskCaseAbility.setReconsiderationLitigationScore(
						riskCaseAbility.getReconsiderationLitigationScore() + reconsiderationLitigationScore);
			}

			// 校验有责涉案（警）投诉、信访
			if (record.getLetterVisitStatus() != null && record.getLetterVisitStatus() == 1) {
				riskCaseAbility.setLetterVisitScore(riskCaseAbility.getLetterVisitScore() + letterVisitScore);
			}

			// 校验重大执法过错
			if (record.getLawEnforcementFaultStatus() != null && record.getLawEnforcementFaultStatus() == 1) {
				riskCaseAbility.setLawEnforcementFaultScore(
						riskCaseAbility.getLawEnforcementFaultScore() + lawEnforcementFaultScore);
			}

			// 校验司法监督（检察院纠违）
			if (record.getJudicialSupervisionStatus() != null && record.getJudicialSupervisionStatus() == 1) {
				riskCaseAbility.setJudicialSupervisionScore(
						riskCaseAbility.getJudicialSupervisionScore() + judicialSupervisionScore);
			}
		}
		riskCaseAbility.setIndexNum(riskCaseAbility.getReconsiderationLitigationScore()
				+ riskCaseAbility.getLetterVisitScore() + riskCaseAbility.getLawEnforcementFaultScore()
				+ riskCaseAbility.getJudicialSupervisionScore());

		// 处理已产生过的报备数据
		if (oldRiskCaseAbility != null && oldRiskCaseAbility.getId() != null) {
			// 表示该riskCaseAbility不用新增
			riskCaseAbility.setId(oldRiskCaseAbility.getId());

			oldRiskCaseAbility.setIndexNum(riskCaseAbility.getIndexNum());
			oldRiskCaseAbility.setReconsiderationLitigationScore(riskCaseAbility.getReconsiderationLitigationScore());
			oldRiskCaseAbility.setLetterVisitScore(riskCaseAbility.getLetterVisitScore());
			oldRiskCaseAbility.setLawEnforcementFaultScore(riskCaseAbility.getLawEnforcementFaultScore());
			oldRiskCaseAbility.setJudicialSupervisionScore(riskCaseAbility.getJudicialSupervisionScore());
			oldRiskCaseAbility.setUpdateDate(new Date());

			riskCaseAbilityMapper.updateRiskCaseAbility(oldRiskCaseAbility);
		}

		return riskCaseAbility;
	}

	/**
	 * 执法管理处理
	 * 
	 * @param user
	 * @param date
	 * @return
	 */
	private RiskCaseLawEnforcement handlingCasesManageRiskDetails(User user, String date) {
		// 校验规则
		// 扣分规则

		RiskCaseLawEnforcement enforcement = new RiskCaseLawEnforcement();
		enforcement.setIndexNum(0d);
		enforcement.setTotalDeductionScore(0d);
		enforcement.setTotalDeductionCount(0);
		enforcement.setPoliceId(user.getPoliceId());
		enforcement.setCreationDate(DateUtils.parseDate(date, "yyyy-MM-dd"));

		List<RiskCaseLawEnforcementRecord> recordList = riskCaseLawEnforcementRecordMapper
				.findPoliceCaseData(user.getPoliceId(), date);
		RiskCaseLawEnforcement oldRiskCaseLawEnforcement = riskCaseLawEnforcementMapper
				.findRiskCaseLawEnforcementByPoliceIdAndDate(user.getPoliceId(), date);
		// 数据不处理操作
		if (recordList == null || recordList.size() <= 0) {
			// 校验这个月是否已产生过数据
			if (oldRiskCaseLawEnforcement == null || oldRiskCaseLawEnforcement.getId() == null) {
				return enforcement;
			}
			return null;
		}

		int count = 0;
		double score = 0d;
		for (RiskCaseLawEnforcementRecord record : recordList) {
			if (record.getDeductionScore() != null && record.getDeductionScore() > 0) {
				score += (record.getDeductionScore() * 10);
			}
			count++;
		}
		enforcement.setIndexNum(score);
		enforcement.setTotalDeductionScore(score);
		enforcement.setTotalDeductionCount(count);

		if (oldRiskCaseLawEnforcement != null && oldRiskCaseLawEnforcement.getId() != null) {
			// 表示该enforcement不用新增
			enforcement.setId(oldRiskCaseLawEnforcement.getId());

			oldRiskCaseLawEnforcement.setIndexNum(enforcement.getIndexNum());
			oldRiskCaseLawEnforcement.setTotalDeductionScore(enforcement.getTotalDeductionScore());
			oldRiskCaseLawEnforcement.setTotalDeductionCount(enforcement.getTotalDeductionCount());
			oldRiskCaseLawEnforcement.setUpdateDate(new Date());

			riskCaseLawEnforcementMapper.updateByPrimaryKeySelective(oldRiskCaseLawEnforcement);
		}
		return enforcement;
	}

	/**
	 * 执法考试处理
	 * 
	 * @param user
	 * @param date
	 * @return
	 */
	private RiskCaseTest handlingCasesExamRiskDetails(User user, String date) {
		String year = date.substring(0, 4);
		String month = date.substring(5, 7);
		int semester = Integer.valueOf(month) >= 1 && Integer.valueOf(month) <= 6 ? 1 : 2;
		// 校验规则
		double passScore = 60d;
		// 扣分规则
		double score = 0.5d;

		RiskCaseTest riskCaseTest = new RiskCaseTest();
		riskCaseTest.setPoliceId(user.getPoliceId());
		riskCaseTest.setIndexNum(0d);
		riskCaseTest.setYear(year);
		riskCaseTest.setSemester(semester);
		riskCaseTest.setDeductionScore(0d);
		riskCaseTest.setCreationDate(DateUtils.parseDate(date, "yyyy-MM-dd"));

		RiskCaseTestRecord record = riskCaseTestRecordMapper.findPoliceCaseData(user.getPoliceId(), date, semester);
		RiskCaseTest oldRiskCaseTest = riskCaseTestMapper.findPoliceRiskCaseTest(user.getPoliceId(), date);
		if (record == null || record.getId() == null) {
			if (oldRiskCaseTest == null || oldRiskCaseTest.getId() == null) {
				return riskCaseTest;
			}
			return null;
		}

		if (record.getScore() < passScore) {
			riskCaseTest.setIndexNum(score);
			riskCaseTest.setDeductionScore(score);
		}

		if (oldRiskCaseTest != null && oldRiskCaseTest.getId() != null) {
			riskCaseTest.setId(oldRiskCaseTest.getId());

			oldRiskCaseTest.setIndexNum(riskCaseTest.getIndexNum());
			oldRiskCaseTest.setDeductionScore(riskCaseTest.getDeductionScore());
			oldRiskCaseTest.setUpdateDate(new Date());

			riskCaseTestMapper.updateByPrimaryKeySelective(oldRiskCaseTest);
		}
		return riskCaseTest;
	}

}
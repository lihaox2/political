package com.bayee.political.service.impl;

import com.bayee.political.domain.RiskAlarm;
import com.bayee.political.domain.RiskDuty;
import com.bayee.political.domain.RiskDutyDealPoliceRecord;
import com.bayee.political.domain.User;
import com.bayee.political.enums.AlarmTypeEnum;
import com.bayee.political.mapper.RiskDutyDealPoliceRecordMapper;
import com.bayee.political.mapper.RiskDutyMapper;
import com.bayee.political.service.DutyRiskService;
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
public class DutyRiskServiceImpl implements DutyRiskService {

	@Autowired
	RiskDutyDealPoliceRecordMapper riskDutyDealPoliceRecordMapper;

	@Autowired
	RiskDutyMapper riskDutyMapper;

	@Autowired
	RiskAlarmService riskAlarmService;

	/**
	 * 预警分数值
	 */
	private double alarmScore = 8d;
	/**
	 * 最大扣分值
	 */
	private double maxScore = 15d;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public RiskDuty dutyRiskDetails(User user, String date) {
		RiskDuty riskDuty = new RiskDuty();
		riskDuty.setIndexNum(0d);
		riskDuty.setDeductionScoreCount(0);
		riskDuty.setTotalDeductionScore(0d);
		riskDuty.setPoliceId(user.getPoliceId());
		riskDuty.setCreationDate(DateUtils.parseDate(date, "yyyy-MM-dd"));

		List<RiskDutyDealPoliceRecord> riskDutyDealPoliceRecordList = riskDutyDealPoliceRecordMapper.
				findRiskDutyDealPoliceRecordList(user.getPoliceId(), date);
		if (riskDutyDealPoliceRecordList.size() > 0) {
			int count = 0;
			double totalScore = 0d;
			for (RiskDutyDealPoliceRecord record : riskDutyDealPoliceRecordList) {
				if (record != null && record.getDeductionScore() > 0) {
					count++;
					totalScore += (record.getDeductionScore() * 10);
				}
			}
			riskDuty.setIndexNum(Math.min(maxScore, totalScore));
			riskDuty.setDeductionScoreCount(count);
			riskDuty.setTotalDeductionScore(totalScore);
		}

		RiskDuty oldRiskDuty = riskDutyMapper.findPoliceRiskDuty(user.getPoliceId(), date);
		if (oldRiskDuty != null && oldRiskDuty.getId() != null) {
			// 表示该riskDuty不用新增
			riskDuty.setId(oldRiskDuty.getId());

			oldRiskDuty.setIndexNum(riskDuty.getIndexNum());
			oldRiskDuty.setDeductionScoreCount(riskDuty.getDeductionScoreCount());
			oldRiskDuty.setTotalDeductionScore(riskDuty.getTotalDeductionScore());
			oldRiskDuty.setUpdateDate(new Date());

			riskDutyMapper.updateByPrimaryKeySelective(oldRiskDuty);
		}

		// 产生预警数据
		if (riskDuty.getIndexNum() >= alarmScore) {
			RiskAlarm riskAlarm = riskAlarmService.generateRiskAlarm(user.getPoliceId(), AlarmTypeEnum.DUTY_RISK, date,
					riskDuty.getIndexNum());

			if (riskAlarm != null) {
				riskAlarmService.insert(riskAlarm);
			}
		}
		return riskDuty;
	}

	@Override
	public RiskDuty dutyRiskNoDeductionScoreCountDetails(User user, String date, Double avgScore) {
		Integer recordCount = riskDutyDealPoliceRecordMapper.countPoliceCaseData(user.getPoliceId(), date);
		if (recordCount != null && recordCount > 0) {
			return null;
		}
		RiskDuty oldRiskDuty = riskDutyMapper.findPoliceRiskDuty(user.getPoliceId(), date);
		oldRiskDuty.setIndexNum(Math.min(avgScore, maxScore));
		oldRiskDuty.setDeductionScoreCount(0);
		oldRiskDuty.setTotalDeductionScore(avgScore);
		oldRiskDuty.setUpdateDate(new Date());

		riskDutyMapper.updateByPrimaryKeySelective(oldRiskDuty);

		// 产生预警数据
		if (oldRiskDuty.getIndexNum() >= alarmScore) {
			RiskAlarm riskAlarm = riskAlarmService.generateRiskAlarm(user.getPoliceId(), AlarmTypeEnum.DUTY_RISK, date,
					oldRiskDuty.getIndexNum());

			if (riskAlarm != null) {
				riskAlarmService.insert(riskAlarm);
			}
		}

		return oldRiskDuty;
	}

	@Override
	public void addRiskDutyList(List<RiskDuty> riskDutyList) {
		riskDutyMapper.insertRiskDutys(riskDutyList);
	}

	@Override
	public Double findPoliceAvgDeductionScoreByDate(String date) {
		return riskDutyDealPoliceRecordMapper.findPoliceAvgDeductionScoreByDate(date);
	}
}
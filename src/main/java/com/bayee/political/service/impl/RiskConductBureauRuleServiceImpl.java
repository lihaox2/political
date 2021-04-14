package com.bayee.political.service.impl;

import com.bayee.political.domain.*;
import com.bayee.political.enums.AlarmTypeEnum;
import com.bayee.political.enums.VisitRecordType;
import com.bayee.political.mapper.*;
import com.bayee.political.service.RiskAlarmService;
import com.bayee.political.service.RiskConductBureauRuleService;
import com.bayee.political.service.RiskConductVisitRecordService;
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
 * @date 2021/4/6
 */
@Service
public class RiskConductBureauRuleServiceImpl implements RiskConductBureauRuleService {

    @Autowired
    RiskConductBureauRuleRecordMapper riskConductBureauRuleRecordMapper;

    @Autowired
    RiskConductBureauRuleMapper riskConductBureauRuleMapper;

    @Autowired
    RiskConductMapper riskConductMapper;

    @Autowired
    RiskAlarmService riskAlarmService;

    @Autowired
    RiskConductVisitRecordMapper riskConductVisitRecordMapper;

    @Autowired
    RiskConductVisitMapper riskConductVisitMapper;

    @Autowired
    RiskConductTrafficViolationRecordMapper riskConductTrafficViolationRecordMapper;

    @Autowired
    RiskConductTrafficViolationMapper riskConductTrafficViolationMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RiskConduct riskConductBureauRuleDetails(User user, String date) {
        RiskConduct riskConduct = new RiskConduct();

        double alarmScore = 12;
        double riskConductMaxScore = 25;

        //局规计分
        RiskConductBureauRule riskConductBureauRule = riskConductBureauRuleDetails(user.getPoliceId(), date);

        //信访投诉
        RiskConductVisit riskConductVisit = riskConductVisitDetails(user.getPoliceId(), date);

        //交通违章
        RiskConductTrafficViolation riskConductTrafficViolation = riskConductTrafficViolationDetails(user.getPoliceId(), date);

        //处理总行为规范数据
        riskConduct.setPoliceId(user.getPoliceId());
        riskConduct.setBureauRuleScore(riskConductBureauRule.getIndexNum());
        riskConduct.setVisitScore(riskConductVisit.getIndexNum());
        riskConduct.setTrafficViolationScore(riskConductTrafficViolation.getIndexNum());
        riskConduct.setIndexNum(Math.min(riskConduct.getVisitScore() + riskConduct.getTrafficViolationScore() +
                riskConduct.getBureauRuleScore(), riskConductMaxScore));
        riskConduct.setCreationDate(DateUtils.parseDate(date, "yyyy-MM-dd"));

        //处理旧的总行为规范数据
        RiskConduct oldRiskConduct = riskConductMapper.findRiskConductByPoliceIdAndDate(user.getPoliceId(), date);
        if (oldRiskConduct != null && oldRiskConduct.getId() != null) {
            //表示该riskConduct不用添加
            riskConduct.setId(oldRiskConduct.getId());

            oldRiskConduct.setBureauRuleScore(riskConduct.getBureauRuleScore());
            oldRiskConduct.setVisitScore(riskConduct.getVisitScore());
            oldRiskConduct.setTrafficViolationScore(riskConduct.getTrafficViolationScore());
            oldRiskConduct.setIndexNum(Math.min(riskConduct.getVisitScore() + riskConduct.getTrafficViolationScore() +
                    riskConduct.getBureauRuleScore(), riskConductMaxScore));
            oldRiskConduct.setUpdateDate(new Date());

            riskConductMapper.updateByPrimaryKey(oldRiskConduct);
        }

        //产生预警数据
        if (riskConduct.getIndexNum() >= alarmScore) {
            RiskAlarm riskAlarm = riskAlarmService.generateRiskAlarm(user.getPoliceId(), AlarmTypeEnum.BEHAVIOR_RISK, date,
                    riskConduct.getIndexNum());

            if (riskAlarm != null) {
                riskAlarmService.insert(riskAlarm);
            }
        }

        if (riskConductBureauRule.getId() == null) {
            riskConductBureauRuleMapper.insert(riskConductBureauRule);
        }
        if (riskConductVisit.getId() == null) {
            riskConductVisitMapper.insert(riskConductVisit);
        }
        if (riskConductTrafficViolation.getId() == null) {
            riskConductTrafficViolationMapper.insert(riskConductTrafficViolation);
        }

        return riskConduct;
    }

    @Override
    public void addRiskConductBureauRuleList(List<RiskConductBureauRule> riskConductBureauRuleList) {
        riskConductBureauRuleMapper.insertRiskConductBureauRuleList(riskConductBureauRuleList);
    }

    /**
     * 局规计分处理
     *
     * @param policeId
     * @param date
     * @return
     */
    private RiskConductBureauRule riskConductBureauRuleDetails(String policeId, String date) {
        List<RiskConductBureauRuleRecord> recordList = riskConductBureauRuleRecordMapper.findRiskConductBureauRuleRecord(policeId, date);
        RiskConductBureauRule riskConductBureauRule = new RiskConductBureauRule();
        riskConductBureauRule.setPoliceId(policeId);
        riskConductBureauRule.setIndexNum(0d);
        riskConductBureauRule.setDeductionScoreCount(0);
        riskConductBureauRule.setTotalDeductionScore(0d);
        riskConductBureauRule.setCreationDate(DateUtils.parseDate(date, "yyyy-MM-dd"));

        if (recordList.size() > 0) {
            int totalCount = 0;
            double totalScore = 0;

            for (RiskConductBureauRuleRecord record : recordList) {
                totalCount++;
                if (record.getDeductionScore() != null) {
                    totalScore += record.getDeductionScore();
                }
            }
            riskConductBureauRule.setIndexNum(totalScore);
            riskConductBureauRule.setDeductionScoreCount(totalCount);
            riskConductBureauRule.setTotalDeductionScore(totalScore);
        }

        //处理老数据
        RiskConductBureauRule oldRiskConductBureauRule = riskConductBureauRuleMapper.findRiskConductBureauRole(policeId, date);
        if (oldRiskConductBureauRule != null && oldRiskConductBureauRule.getId() != null) {
            //表示该riskConductBureauRule不用新增
            riskConductBureauRule.setId(oldRiskConductBureauRule.getId());

            oldRiskConductBureauRule.setIndexNum(riskConductBureauRule.getIndexNum());
            oldRiskConductBureauRule.setDeductionScoreCount(riskConductBureauRule.getDeductionScoreCount());
            oldRiskConductBureauRule.setTotalDeductionScore(riskConductBureauRule.getTotalDeductionScore());
            oldRiskConductBureauRule.setUpdateDate(new Date());

            riskConductBureauRuleMapper.updateByPrimaryKey(oldRiskConductBureauRule);
        }

        return riskConductBureauRule;
    }

    /**
     * 信访投诉处理
     *
     * @param policeId
     * @param date
     * @return
     */
    private RiskConductVisit riskConductVisitDetails(String policeId, String date) {
        List<RiskConductVisitRecord> visitRecordList = riskConductVisitRecordMapper.findRiskConductVisitRecordList(policeId, date);
        RiskConductVisit riskConductVisit = new RiskConductVisit();
        riskConductVisit.setPoliceId(policeId);
        riskConductVisit.setIndexNum(0d);
        riskConductVisit.setDeductionScoreCount(0);
        riskConductVisit.setTotalDeductionScore(0d);
        riskConductVisit.setCreationDate(DateUtils.parseDate(date, "yyyy-MM-dd"));

        if (visitRecordList.size() > 0) {
            int deductionCount = 0;
            double deductionScore = 0;
            for (RiskConductVisitRecord record : visitRecordList) {
                deductionCount++;
                if (VisitRecordType.ORDER_INSPECTION.getTypeId().equals(record.getType())) {
                    //责令检查
                    deductionScore += 1;
                } else if (VisitRecordType.CRITICAL_EDUCATION.getTypeId().equals(record.getType())) {
                    //批评教育
                    deductionScore += 2;
                } else if (VisitRecordType.REMAINDER_TALK.getTypeId().equals(record.getType())) {
                    //谈话提醒
                    deductionScore += 1;
                } else if (VisitRecordType.INQUIRY.getTypeId().equals(record.getType())) {
                    //函询
                    deductionCount += 1;
                } else if (VisitRecordType.PERSUADE_AND_ADMONISH.getTypeId().equals(record.getType())) {
                    //诫勉
                    deductionCount += 2;
                } else if (VisitRecordType.PARTY_WARNING.getTypeId().equals(record.getType())) {
                    //党纪处分-警告
                    deductionCount += 3;
                } else if (VisitRecordType.PARTY_SERIOUS_WARNING.getTypeId().equals(record.getType())) {
                    //党纪处分-严重警告
                    deductionCount += 4;
                } else if (VisitRecordType.PARTY_REMOVAL_OF_PARTY_POSTS.getTypeId().equals(record.getType())) {
                    //党纪处分-撤销党内职务
                    deductionCount += 10;
                } else if (VisitRecordType.PARTY_STAY_IN_THE_PARTY_FOR_INSPECTION.getTypeId().equals(record.getType())) {
                    //党纪处分-留党查看
                    deductionCount += 5;
                } else if (VisitRecordType.PARTY_EXPULSION_FORM_THE_PARTY.getTypeId().equals(record.getType())) {
                    //党纪处分-开除党籍
                    deductionCount += 10;
                } else if (VisitRecordType.GOVERNMENT_WARNING.getTypeId().equals(record.getType())) {
                    //政纪处分-警告
                    deductionCount += 1;
                } else if (VisitRecordType.GOVERNMENT_RECORD_A_DEMERIT.getTypeId().equals(record.getType())) {
                    //政纪处分-记过
                    deductionCount += 2;
                } else if (VisitRecordType.GOVERNMENT_RECORD_A_BIG_DEMERIT.getTypeId().equals(record.getType())) {
                    //政纪处分-记大过
                    deductionCount += 3;
                } else if (VisitRecordType.GOVERNMENT_DEMOTION_LEVEL.getTypeId().equals(record.getType())) {
                    //政纪处分-降级
                    deductionCount += 4;
                } else if (VisitRecordType.GOVERNMENT_DEMOTION_POSITION.getTypeId().equals(record.getType())) {
                    //政纪处分-撤职
                    deductionCount += 7;
                } else if (VisitRecordType.GOVERNMENT_EXPEL.getTypeId().equals(record.getType())) {
                    //政纪处分-开除
                    deductionCount += 10;
                } else if (VisitRecordType.GIVE_CRIMINAL_SANCTIONS.getTypeId().equals(record.getType())) {
                    //追究刑事责任
                    deductionCount += 10;
                } else if (VisitRecordType.INVESTIGATION_OF_CIVIL_LIABILITY.getTypeId().equals(record.getType())) {
                    //追究民事责任
                    deductionCount += 9;
                }
            }
            riskConductVisit.setIndexNum(deductionScore);
            riskConductVisit.setDeductionScoreCount(deductionCount);
            riskConductVisit.setTotalDeductionScore(deductionScore);
        }

        //处理老数据
        RiskConductVisit oldRiskConductVisit = riskConductVisitMapper.findRiskConductVisit(policeId, date);

        if (oldRiskConductVisit != null && oldRiskConductVisit.getId() != null) {
            //表示该riskConductVisit 不用新增
            riskConductVisit.setId(oldRiskConductVisit.getId());

            oldRiskConductVisit.setIndexNum(riskConductVisit.getIndexNum());
            oldRiskConductVisit.setDeductionScoreCount(riskConductVisit.getDeductionScoreCount());
            oldRiskConductVisit.setTotalDeductionScore(riskConductVisit.getTotalDeductionScore());
            oldRiskConductVisit.setUpdateDate(new Date());
        }

        return riskConductVisit;
    }

    /**
     * 交通违章
     *
     * @param policeId
     * @param date
     * @return
     */
    private RiskConductTrafficViolation riskConductTrafficViolationDetails(String policeId, String date) {
        List<RiskConductTrafficViolationRecord> trafficViolationRecordList =
                riskConductTrafficViolationRecordMapper.findPoliceRiskConductTrafficViolationRecordList(policeId, date);
        RiskConductTrafficViolation trafficViolation = new RiskConductTrafficViolation();
        trafficViolation.setPoliceId(policeId);
        trafficViolation.setIndexNum(0d);
        trafficViolation.setDeductionScoreCount(0);
        trafficViolation.setTotalDeductionScore(0d);
        trafficViolation.setCreationDate(DateUtils.parseDate(date, "yyyy-MM-dd"));

        double dscore = 2;

        if (trafficViolationRecordList.size() > 0) {
            int deductionCount = 0;
            double deductionScore = 0;
            for (RiskConductTrafficViolationRecord record : trafficViolationRecordList) {
                if (record != null && record.getId() != null) {
                    deductionCount++;
                    deductionScore += dscore;
                }
            }
            trafficViolation.setIndexNum(deductionScore);
            trafficViolation.setDeductionScoreCount(deductionCount);
            trafficViolation.setTotalDeductionScore(deductionScore);
        }

        RiskConductTrafficViolation oldRiskConductTrafficViolation =
                riskConductTrafficViolationMapper.findPoliceRiskConductTrafficViolation(policeId, date);
        if (oldRiskConductTrafficViolation != null && oldRiskConductTrafficViolation.getId() != null) {
            trafficViolation.setId(oldRiskConductTrafficViolation.getId());

            oldRiskConductTrafficViolation.setIndexNum(trafficViolation.getIndexNum());
            oldRiskConductTrafficViolation.setDeductionScoreCount(trafficViolation.getDeductionScoreCount());
            oldRiskConductTrafficViolation.setTotalDeductionScore(trafficViolation.getTotalDeductionScore());
            oldRiskConductTrafficViolation.setUpdateDate(new Date());

            riskConductTrafficViolationMapper.updateByPrimaryKey(oldRiskConductTrafficViolation);
        }
        return trafficViolation;
    }
}

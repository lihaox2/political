package com.bayee.political.service.impl;

import com.bayee.political.algorithm.RiskCompute;
import com.bayee.political.domain.RiskAlarm;
import com.bayee.political.domain.RiskTrain;
import com.bayee.political.domain.User;
import com.bayee.political.enums.AlarmTypeEnum;
import com.bayee.political.mapper.RiskAlarmMapper;
import com.bayee.political.mapper.RiskTrainMapper;
import com.bayee.political.mapper.TrainFirearmAchievementMapper;
import com.bayee.political.mapper.TrainPhysicalAchievementDetailsMapper;
import com.bayee.political.pojo.GlobalIndexNumResultDO;
import com.bayee.political.service.RiskAlarmService;
import com.bayee.political.service.RiskSkillService;
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
public class RiskSkillServiceImpl implements RiskSkillService {

    @Autowired
    RiskTrainMapper riskTrainMapper;

    @Autowired
    RiskAlarmService riskAlarmService;

    @Autowired
    TrainPhysicalAchievementDetailsMapper trainPhysicalAchievementDetailsMapper;

    @Autowired
    TrainFirearmAchievementMapper trainFirearmAchievementMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RiskTrain riskSkillDetails(User user, String date) {
        RiskTrain riskTrain = riskTrainMapper.findRiskTrainByPoliceIdAndDate(user.getPoliceId(), date);

        double comprehensiveScore = 5d;
        double shootingScore = 5d;

        double maxScore = 10d;
        double alarmScore = 5d;

        if (riskTrain != null) {
            Double physicalScore = trainPhysicalAchievementDetailsMapper.getPolicePhysicalDeductionScore(user.getPoliceId(), date);
            Double firearmScore = trainFirearmAchievementMapper.getPoliceFirearmDeductionScore(user.getPoliceId(), date);
            double totalScore = firearmScore + physicalScore;
//            double totalScore = 0d;
//            if (riskTrain.getPhysicalFailNum() != null && riskTrain.getPhysicalFailNum() > 0) {
//                totalScore += (riskTrain.getPhysicalFailNum() * comprehensiveScore);
//            }
//            if (riskTrain.getFirearmFailNum() != null && riskTrain.getFirearmFailNum() > 0) {
//                totalScore += (riskTrain.getFirearmFailNum() * shootingScore);
//            }
            riskTrain.setFirearmScore(firearmScore);
            riskTrain.setPhysicalScore(physicalScore);
            riskTrain.setIndexNum(Math.min(totalScore, maxScore));

            // 产生预警数据
            if (totalScore >= alarmScore) {
                RiskAlarm riskAlarm = riskAlarmService.generateRiskAlarm(user.getPoliceId(), AlarmTypeEnum.SKILL_RISK, date,
                        totalScore);

                if (riskAlarm != null) {
                    riskAlarmService.insert(riskAlarm);
                }
            }
            riskTrainMapper.updateByPrimaryKey(riskTrain);

            return riskTrain;
        } else {
            return null;
        }
    }

    @Override
    public Double getPolicePhysicalDeductionScore(String policeId, String date) {
        return trainPhysicalAchievementDetailsMapper.getPolicePhysicalDeductionScore(policeId, date);
    }

    @Override
    public Double getPoliceFirearmDeductionScore(String policeId, String date) {
        return trainFirearmAchievementMapper.getPoliceFirearmDeductionScore(policeId, date);
    }

    @Override
    public RiskTrain riskSkillDetailsV2(User user, String date) {
        String monthDate = DateUtils.formatDate(DateUtils.parseDate(date, "yyyy-MM-dd"), "yyyy-MM");
        double maxScore = 10d;
        double alarmScore = 5d;

        RiskTrain riskTrain = new RiskTrain();
        riskTrain.setTotalNum(0d);
        riskTrain.setPhysicalScore(0d);
        riskTrain.setFirearmScore(0d);
        riskTrain.setPoliceId(user.getPoliceId());
        riskTrain.setIndexNum(0d);
        riskTrain.setPhysicalNum(0);
        riskTrain.setPhysicalPassNum(0);
        riskTrain.setPhysicalFailNum(0);
        riskTrain.setPhysicalStandardStatus(0);
        riskTrain.setFirearmNum(0);
        riskTrain.setFirearmPassNum(0);
        riskTrain.setFirearmFailNum(0);
        riskTrain.setFirearmStandardStatus(0);

        RiskTrain item = riskTrainMapper.riskTrainStatisticsItem(user.getPoliceId(), monthDate);
        if (item != null) {
            riskTrain.setPhysicalNum(item.getPhysicalNum());
            riskTrain.setPhysicalPassNum(item.getPhysicalPassNum());
            riskTrain.setPhysicalFailNum(item.getPhysicalFailNum());
            riskTrain.setFirearmNum(item.getFirearmNum());
            riskTrain.setFirearmPassNum(item.getFirearmPassNum());
            riskTrain.setFirearmFailNum(item.getFirearmFailNum());
        }

        Double physicalScore = trainPhysicalAchievementDetailsMapper.getPolicePhysicalDeductionScore(user.getPoliceId(), date);
        Double firearmScore = trainFirearmAchievementMapper.getPoliceFirearmDeductionScore(user.getPoliceId(), date);
        double totalScore = firearmScore + physicalScore;

        riskTrain.setFirearmScore(firearmScore);
        riskTrain.setPhysicalScore(physicalScore);
        riskTrain.setTotalNum(totalScore);
        riskTrain.setIndexNum(Math.min(totalScore, maxScore));

        //数据归一化计算
        GlobalIndexNumResultDO resultDO = riskTrainMapper.findGlobalIndexNum(date);
        double globalScore = resultDO.getMaxNum() - resultDO.getMinNum();
        double indexNum = totalScore - resultDO.getMinNum();
        if (indexNum > globalScore) {
            globalScore = totalScore;
        }
        if (globalScore > 0) {
            double divValue = indexNum / globalScore;
            if (divValue > 1) {
                divValue = 1;
            }
            riskTrain.setIndexNum(RiskCompute.parserDecimal(divValue * 10));
        }

        // 产生预警数据
        if (totalScore >= alarmScore) {
            RiskAlarm riskAlarm = riskAlarmService.generateRiskAlarm(user.getPoliceId(), AlarmTypeEnum.SKILL_RISK, date,
                    totalScore);

            if (riskAlarm != null) {
                riskAlarmService.insert(riskAlarm);
            }
        }

        RiskTrain oldRiskTrain = riskTrainMapper.findRiskTrainByPoliceIdAndDate(user.getPoliceId(), date);
        if (oldRiskTrain != null && oldRiskTrain.getId() != null) {
            riskTrain.setId(oldRiskTrain.getId());

            oldRiskTrain.setTotalNum(riskTrain.getTotalNum());
            oldRiskTrain.setPhysicalScore(riskTrain.getPhysicalScore());
            oldRiskTrain.setFirearmScore(riskTrain.getFirearmScore());
            oldRiskTrain.setIndexNum(riskTrain.getIndexNum());
            oldRiskTrain.setPhysicalNum(riskTrain.getPhysicalNum());
            oldRiskTrain.setPhysicalPassNum(riskTrain.getPhysicalPassNum());
            oldRiskTrain.setPhysicalFailNum(riskTrain.getPhysicalFailNum());
            oldRiskTrain.setPhysicalStandardStatus(riskTrain.getPhysicalStandardStatus());
            oldRiskTrain.setFirearmNum(riskTrain.getFirearmNum());
            oldRiskTrain.setFirearmPassNum(riskTrain.getFirearmPassNum());
            oldRiskTrain.setFirearmFailNum(riskTrain.getFirearmFailNum());
            oldRiskTrain.setFirearmStandardStatus(riskTrain.getFirearmStandardStatus());
            oldRiskTrain.setUpdateDate(new Date());

            riskTrainMapper.updateByPrimaryKey(oldRiskTrain);
        } else {
            riskTrain.setCreationDate(DateUtils.parseDate(date, "yyyy-MM-dd"));
            riskTrainMapper.riskTrainCreat(riskTrain);
        }
        return riskTrain;
    }


}

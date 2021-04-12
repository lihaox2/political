package com.bayee.political.service.impl;

import com.bayee.political.domain.RiskAlarm;
import com.bayee.political.domain.RiskTrain;
import com.bayee.political.domain.User;
import com.bayee.political.mapper.RiskAlarmMapper;
import com.bayee.political.mapper.RiskTrainMapper;
import com.bayee.political.service.RiskSkillService;
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
    RiskAlarmMapper riskAlarmMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RiskTrain riskSkillDetails(User user) {
        LocalDate localDate = LocalDate.now();
        String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));
        RiskTrain riskTrain = riskTrainMapper.findRiskTrainByPoliceIdAndDate(user.getPoliceId(), date);

        double comprehensiveScore = 5d;
        double shootingScore = 5d;

        double maxScore = 10d;
        double alarmScore = 5d;

        if (riskTrain != null) {
            double totalScore = 0d;
            if (riskTrain.getPhysicalFailNum() != null && riskTrain.getPhysicalFailNum() > 0) {
                totalScore += (riskTrain.getPhysicalFailNum() * comprehensiveScore);
            }
            if (riskTrain.getFirearmFailNum() != null && riskTrain.getFirearmFailNum() > 0) {
                totalScore += (riskTrain.getFirearmFailNum() * shootingScore);
            }
            riskTrain.setIndexNum(Math.min(totalScore, maxScore));

            //生成预警数据
            if (totalScore >= alarmScore) {
                RiskAlarm riskAlarm = new RiskAlarm();
                riskAlarm.setPoliceId(user.getPoliceId());
                riskAlarm.setAlarmType(11005);
                riskAlarm.setAlarmScore(totalScore);
                riskAlarm.setCreationDate(new Date());
                riskAlarm.setIsTalk(0);
                riskAlarmMapper.insertSelective(riskAlarm);
            }
            riskTrainMapper.updateByPrimaryKey(riskTrain);

            return riskTrain;
        } else {
            return null;
        }
    }

}

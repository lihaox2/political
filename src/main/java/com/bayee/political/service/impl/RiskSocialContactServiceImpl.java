package com.bayee.political.service.impl;

import com.bayee.political.domain.RiskAlarm;
import com.bayee.political.domain.RiskSocialContact;
import com.bayee.political.domain.RiskSocialContactRecord;
import com.bayee.political.domain.User;
import com.bayee.political.enums.AlarmTypeEnum;
import com.bayee.political.mapper.RiskSocialContactMapper;
import com.bayee.political.mapper.RiskSocialContactRecordMapper;
import com.bayee.political.service.RiskAlarmService;
import com.bayee.political.service.RiskSocialContactService;
import com.bayee.political.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author xxl
 * @date 2021/4/14
 */
@Service
public class RiskSocialContactServiceImpl implements RiskSocialContactService {

    @Autowired
    RiskSocialContactMapper riskSocialContactMapper;

    @Autowired
    RiskSocialContactRecordMapper riskSocialContactRecordMapper;

    @Autowired
    RiskAlarmService riskAlarmService;

    @Override
    public RiskSocialContact riskSocialContactDetails(User user, String date) {
        List<RiskSocialContactRecord> socialContactRecordList =
                riskSocialContactRecordMapper.findPoliceRiskSocialContactRecordList(user.getPoliceId(), date);
        RiskSocialContact riskSocialContact = new RiskSocialContact();
        riskSocialContact.setPoliceId(user.getPoliceId());
        riskSocialContact.setIndexNum(0d);
        riskSocialContact.setNum(0);
        riskSocialContact.setCreationDate(DateUtils.parseDate(date, "yyyy-MM-dd"));

        double maxScore = 15;
        double alarmScore = 6;
        double score = 3;

        if (socialContactRecordList.size() > 0) {
            int count = 0;
            double deductionScore = 0;
            for (RiskSocialContactRecord record : socialContactRecordList) {
                count++;
                deductionScore += score;
            }
            riskSocialContact.setIndexNum(Math.min(deductionScore, maxScore));
            riskSocialContact.setNum(count);
        }

        RiskSocialContact oldRiskSocialContact = riskSocialContactMapper.findPoliceRiskSocialContact(user.getPoliceId(), date);
        if (oldRiskSocialContact != null && oldRiskSocialContact.getId() != null) {
            riskSocialContact.setId(oldRiskSocialContact.getId());

            oldRiskSocialContact.setIndexNum(riskSocialContact.getIndexNum());
            oldRiskSocialContact.setNum(riskSocialContact.getNum());
            oldRiskSocialContact.setUpdateDate(new Date());
        }

        //产生预警数据
        if (riskSocialContact.getIndexNum() >= alarmScore) {
            RiskAlarm riskAlarm = riskAlarmService.generateRiskAlarm(user.getPoliceId(), AlarmTypeEnum.SOCIAL_CONTACT, date,
                    riskSocialContact.getIndexNum());

            if (riskAlarm != null) {
                riskAlarmService.insert(riskAlarm);
            }
        }

        return riskSocialContact;
    }

    @Override
    public void addRiskSocialContactList(List<RiskSocialContact> riskSocialContactList) {
        riskSocialContactMapper.insertRiskSocialContactList(riskSocialContactList);
    }
}

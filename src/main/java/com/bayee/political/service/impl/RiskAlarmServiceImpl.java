package com.bayee.political.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.AlarmType;
import com.bayee.political.domain.RiskAlarm;
import com.bayee.political.mapper.RiskAlarmMapper;
import com.bayee.political.service.RiskAlarmService;

/**
 * @author xxl
 * @date 2021/3/30
 */
@Service
public class RiskAlarmServiceImpl implements RiskAlarmService {

    @Autowired
    RiskAlarmMapper riskAlarmMapper;

    @Override
    public int insert(RiskAlarm record) {
        return riskAlarmMapper.riskAlarmCreat(record);
    }

    @Override
    public int updateByPrimaryKeySelective(RiskAlarm record) {
        return riskAlarmMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public RiskAlarm findByPoliceIdAndTypeAndDate(String policeId, Integer type, String date) {
        return riskAlarmMapper.findByPoliceIdAndTypeAndDate(policeId, type, date);
    }

    @Override
    public void insertRiskAlarms(List<RiskAlarm> riskAlarmList) {
        riskAlarmMapper.insertRiskAlarms(riskAlarmList);
    }

    @Override
    public RiskAlarm generateRiskAlarm(String policeId, AlarmType alarmType, String date, Double score) {
        RiskAlarm riskAlarm = new RiskAlarm();
        riskAlarm.setPoliceId(policeId);
        riskAlarm.setAlarmType(alarmType.getId());
        riskAlarm.setAlarmScore(score);
        riskAlarm.setIsTalk(0);
        riskAlarm.setCreationDate(new Date());

        RiskAlarm oldRiskAlarm = riskAlarmMapper.findByPoliceIdAndTypeAndDate(policeId, alarmType.getId(), date);
        if (oldRiskAlarm != null && oldRiskAlarm.getId() != null) {
            oldRiskAlarm.setAlarmScore(riskAlarm.getAlarmScore());
            oldRiskAlarm.setUpdateDate(new Date());

            riskAlarmMapper.updateByPrimaryKeySelective(oldRiskAlarm);
            return null;
        } else {
            return riskAlarm;
        }
    }
}

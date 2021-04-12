package com.bayee.political.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.AlarmType;
import com.bayee.political.domain.RiskAlarm;

/**
 * @author xxl
 * @date 2021/3/30
 */
public interface RiskAlarmService {

    int insert(RiskAlarm record);

    int updateByPrimaryKeySelective(RiskAlarm record);

    /**
     * 查询本月已产生的预警数据
     * @param policeId 禁用id
     * @param type 预警类型
     * @param date 预警时间
     * @return
     */
    RiskAlarm findByPoliceIdAndTypeAndDate(@Param("policeId") String policeId,@Param("type") Integer type,
                                           @Param("date") String date);

    /**
     * 批量添加预警数据
     * @param riskAlarmList
     */
    void insertRiskAlarms(List<RiskAlarm> riskAlarmList);

    /**
     * 产生预警对象
     * @param policeId 警号
     * @param alarmType 预警类型
     * @param date 时间
     * @param score 预警值
     * @return
     */
    RiskAlarm generateRiskAlarm(String policeId, AlarmType alarmType, String date, Double score);

}

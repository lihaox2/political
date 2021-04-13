package com.bayee.political.service;

import com.bayee.political.domain.RiskDuty;
import com.bayee.political.domain.User;

import java.time.LocalDate;
import java.util.List;

/**
 * 接警执勤报备服务层
 *
 * @author xxl
 * @date 2021/3/30
 */
public interface DutyRiskService {

    /**
     * 接警执勤报备处理
     * @param user
     * @param localDate
     * @return
     */
    RiskDuty dutyRiskDetails(User user, LocalDate localDate);

    /**
     * 处理无接处警的警员扣分信息
     * @param user
     * @param localDate
     * @param avgScore
     * @return
     */
    RiskDuty dutyRiskNoDeductionScoreCountDetails(User user, LocalDate localDate, Double avgScore);

    /**
     * 批量添加执勤报备数据
     * @param riskDutyList
     */
    void addRiskDutyList(List<RiskDuty> riskDutyList);

    /**
     * 查询某个月警员的平均扣分
     *
     * @param date
     * @return
     */
    Double findPoliceAvgDeductionScoreByDate(String date);

}

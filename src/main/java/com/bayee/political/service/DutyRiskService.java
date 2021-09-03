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
     * @param date
     * @return
     */
    RiskDuty dutyRiskDetails(User user, String date);

    /**
     * 处理无接处警的警员扣分信息
     * @param user
     * @param date
     * @param avgScore
     * @return
     */
    RiskDuty dutyRiskNoDeductionScoreCountDetails(User user, String date, Double avgScore);

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

    /**
     * 接警执勤报备处理
     * @param user
     * @param date
     * @return
     */
    RiskDuty dutyRiskDetailsV2(User user, String date);
}

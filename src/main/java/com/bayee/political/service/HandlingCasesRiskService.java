package com.bayee.political.service;

import com.bayee.political.domain.RiskCase;
import com.bayee.political.domain.User;

import java.time.LocalDate;
import java.util.List;

/**
 * 执法办案报备服务层
 *
 * @author xxl
 * @date 2021/3/30
 */
public interface HandlingCasesRiskService {

    /**
     * 执法办案报备处理
     * @param user
     * @param localDate
     * @return
     */
    RiskCase handlingCasesRiskDetails(User user, LocalDate localDate);

    /**
     * 处理无办案的警员扣分信息
     * @param user
     * @param localDate
     * @param avgScore
     * @return
     */
    RiskCase handlingCasesRiskDetailsByCasesManageRisk(User user, LocalDate localDate, Double avgScore);

    /**
     * 批量添加执法报备数据
     * @param riskCaseList
     */
    void addRiskCaseList(List<RiskCase> riskCaseList);

    /**
     * 查询某个月警员的平均扣分
     *
     * @param date
     * @return
     */
    Double findPoliceAvgDeductionScoreByDate(String date);

}

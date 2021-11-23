package com.bayee.political.service;

import com.bayee.political.domain.RiskRelevant;
import com.bayee.political.domain.User;
import com.bayee.political.json.ChartResult;

import java.util.List;

/**
 * @author xxl
 * @date 2021/11/19
 */
public interface RiskRelevantService {

    /**
     * 警员-动态排摸风险计算
     * @param user
     * @param date
     * @return
     */
    RiskRelevant relevantRiskDetails(User user, String date);

    /**
     *
     * @param policeId
     * @param dateTime
     * @param timeType
     * @return
     */
    RiskRelevant riskRelevantItem(String policeId, String dateTime, Integer timeType);

    /**
     * 查询近六个月风险情况
     * @param policeId
     * @return
     */
    List<ChartResult> riskNearSixMonthChart(String policeId);

}

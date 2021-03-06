package com.bayee.political.service;

import com.bayee.political.domain.RiskConduct;
import com.bayee.political.domain.RiskConductBureauRule;
import com.bayee.political.domain.User;

import java.util.List;

/**
 * 局规计分服务层
 * @author xxl
 * @date 2021/4/6
 */
public interface RiskConductBureauRuleService {

    /**
     * 行为规范计算
     * @param user
     * @param date
     * @return
     */
    RiskConduct riskConductDetails(User user, String date);

    /**
     * 批量添加局规计分风险指数数据
     * @param riskConductBureauRuleList
     */
    void addRiskConductBureauRuleList(List<RiskConductBureauRule> riskConductBureauRuleList);

    /**
     * 行为规范计算
     * @param user
     * @param date
     * @return
     */
    RiskConduct riskConductDetailsV2(User user, String date);
}

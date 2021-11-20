package com.bayee.political.service;

import com.bayee.political.domain.RiskRelevant;
import com.bayee.political.domain.User;

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

}

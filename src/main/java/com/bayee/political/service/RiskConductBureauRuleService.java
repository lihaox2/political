package com.bayee.political.service;

import com.bayee.political.domain.RiskConductBureauRule;
import com.bayee.political.domain.User;

/**
 * 局规计分服务层
 * @author xxl
 * @date 2021/4/6
 */
public interface RiskConductBureauRuleService {

    /**
     * 局规计分计算
     * @param user
     * @return
     */
    RiskConductBureauRule riskConductBureauRuleDetails(User user);

}

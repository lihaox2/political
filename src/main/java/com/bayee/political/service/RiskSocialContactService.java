package com.bayee.political.service;

import com.bayee.political.domain.RiskSocialContact;
import com.bayee.political.domain.User;

import java.util.List;

/**
 * @author xxl
 * @date 2021/4/14
 */
public interface RiskSocialContactService {

    /**
     * 社交风险处理
     * @param user
     * @param date
     * @return
     */
    RiskSocialContact riskSocialContactDetails(User user, String date);

    /**
     * 批量添加
     * @param riskSocialContactList
     */
    void addRiskSocialContactList(List<RiskSocialContact> riskSocialContactList);

}

package com.bayee.political.service;

import com.bayee.political.domain.RiskTrain;
import com.bayee.political.domain.User;

import java.util.List;

/**
 * 警务技能计算服务层
 * @author xxl
 * @date 2021/4/6
 */
public interface RiskSkillService {

    /**
     * 警务技能计算
     * @param user
     * @param date
     * @return
     */
    RiskTrain riskSkillDetails(User user, String date);

    /**
     *
     * @param policeId
     * @param date
     * @return
     */
    Double getPolicePhysicalDeductionScore(String policeId, String date);

    /**
     *
     * @param policeId
     * @param date
     * @return
     */
    Double getPoliceFirearmDeductionScore(String policeId, String date);

    /**
     * 警务技能计算
     * @param user
     * @param date
     * @return
     */
    RiskTrain riskSkillDetailsV2(User user, String date);
}

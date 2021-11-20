package com.bayee.political.service;

import java.time.LocalDate;

/**
 * @author xxl
 * @date 2021/5/17
 */
public interface TotalRiskDetailsService {

    /**
     * 执法管理处理
     * @param policeId
     * @param localDate
     */
    void caseRiskDetails(LocalDate localDate);

    /**
     * 行为规范处理
     * @param policeId
     * @param localDate
     */
    void conductRiskDetails(LocalDate localDate);

    /**
     * 接警执勤处理
     * @param policeId
     * @param localDate
     */
    void dutyRiskDetails(LocalDate localDate);

    /**
     * 健康风险处理
     * @param localDate
     */
    void healthRiskDetails(LocalDate localDate);

    /**
     * 警务技能处理
     * @param localDate
     */
    void skillRiskDetails(LocalDate localDate);

}

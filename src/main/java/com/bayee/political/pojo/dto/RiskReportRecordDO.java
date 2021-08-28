package com.bayee.political.pojo.dto;

import com.bayee.political.domain.RiskReportRecord;

/**
 * @author xxl
 * @date 2021/6/13
 */
public class RiskReportRecordDO extends RiskReportRecord {

    /**
     * 上月分值
     */
    private Double lastMonthScore;

    /**
     * 本月分值
     */
    private Double thisMonthScore;

    /**
     * 环比
     */
    private Double qoq;

    /**
     * 健康描述
     */
    private String healthDesc;

    public Double getLastMonthScore() {
        return lastMonthScore;
    }

    public void setLastMonthScore(Double lastMonthScore) {
        this.lastMonthScore = lastMonthScore;
    }

    public Double getThisMonthScore() {
        return thisMonthScore;
    }

    public void setThisMonthScore(Double thisMonthScore) {
        this.thisMonthScore = thisMonthScore;
    }

    public Double getQoq() {
        return qoq;
    }

    public void setQoq(Double qoq) {
        this.qoq = qoq;
    }

    public String getHealthDesc() {
        return healthDesc;
    }

    public void setHealthDesc(String healthDesc) {
        this.healthDesc = healthDesc;
    }
}

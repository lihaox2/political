package com.bayee.political.pojo.dto;

/**
 * @author xxl
 * @date 2021/7/21
 */
public class RiskRecordVerifyStatisticsDO {

    /**
     * 总申诉数
     */
    private Integer totalCount;

    /**
     * 已复核数
     */
    private Integer isCheckCount;

    /**
     * 已复核率
     */
    private Double isCheckRatio;

    /**
     * 待复核数
     */
    private Integer unCheckCount;

    /**
     * 待复核率
     */
    private Double unCheckRatio;

    /**
     * 局规记分 数
     */
    private Integer conductBureauRuleCount;

    /**
     * 信访投诉 数
     */
    private Integer conductVisitCount;

    /**
     * 接警执勤 数
     */
    private Integer dutyDealCount;

    /**
     * 执法管理 数
     */
    private Integer lawEnforcementCount;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getIsCheckCount() {
        return isCheckCount;
    }

    public void setIsCheckCount(Integer isCheckCount) {
        this.isCheckCount = isCheckCount;
    }

    public Double getIsCheckRatio() {
        return isCheckRatio;
    }

    public void setIsCheckRatio(Double isCheckRatio) {
        this.isCheckRatio = isCheckRatio;
    }

    public Integer getUnCheckCount() {
        return unCheckCount;
    }

    public void setUnCheckCount(Integer unCheckCount) {
        this.unCheckCount = unCheckCount;
    }

    public Double getUnCheckRatio() {
        return unCheckRatio;
    }

    public void setUnCheckRatio(Double unCheckRatio) {
        this.unCheckRatio = unCheckRatio;
    }

    public Integer getConductBureauRuleCount() {
        return conductBureauRuleCount;
    }

    public void setConductBureauRuleCount(Integer conductBureauRuleCount) {
        this.conductBureauRuleCount = conductBureauRuleCount;
    }

    public Integer getConductVisitCount() {
        return conductVisitCount;
    }

    public void setConductVisitCount(Integer conductVisitCount) {
        this.conductVisitCount = conductVisitCount;
    }

    public Integer getDutyDealCount() {
        return dutyDealCount;
    }

    public void setDutyDealCount(Integer dutyDealCount) {
        this.dutyDealCount = dutyDealCount;
    }

    public Integer getLawEnforcementCount() {
        return lawEnforcementCount;
    }

    public void setLawEnforcementCount(Integer lawEnforcementCount) {
        this.lawEnforcementCount = lawEnforcementCount;
    }
}

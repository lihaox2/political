package com.bayee.political.json;

import java.util.Date;

/**
 * @author xxl
 * @date 2021/6/14
 */
public class RiskConductBureauRuleRecordResult {

    /**
     * 类型6-局规记分
     */
    private Integer type = 6;

    /**
     * 警员名称
     */
    private String policeName;

    /**
     * 计分原因
     */
    private String reason;

    /**
     * 扣除分数
     */
    private Double deductionScore;

    /**
     * sort
     */
    private Date date;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Double getDeductionScore() {
        return deductionScore;
    }

    public void setDeductionScore(Double deductionScore) {
        this.deductionScore = deductionScore;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

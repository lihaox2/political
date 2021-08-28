package com.bayee.political.json;

import java.util.Date;

/**
 * @author xxl
 * @date 2021/6/14
 */
public class RiskConductVisitRecordResult {

    /**
     * 类型7-信访投诉
     */
    private Integer type = 7;

    /**
     * 警员名称
     */
    private String policeName;

    /**
     * 问题情况与来源
     */
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

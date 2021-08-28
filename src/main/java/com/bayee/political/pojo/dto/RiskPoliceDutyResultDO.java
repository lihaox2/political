package com.bayee.political.pojo.dto;

import java.util.Date;

/**
 * @author xxl
 * @date 2021/8/10
 */
public class RiskPoliceDutyResultDO {

    /**
     * 类型1-接警执勤
     */
    private Integer type = 1;

    /**
     * 警员名称
     */
    private String policeName;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 错误类型
     */
    private String errorTypeName;

    /**
     * 扣除分数
     */
    private Double deductionScore;

    /**
     * sort
     */
    private Date date;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getErrorTypeName() {
        return errorTypeName;
    }

    public void setErrorTypeName(String errorTypeName) {
        this.errorTypeName = errorTypeName;
    }

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

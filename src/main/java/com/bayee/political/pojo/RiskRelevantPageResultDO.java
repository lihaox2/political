package com.bayee.political.pojo;

/**
 * @author xxl
 * @date 2021/9/5 11:47
 */
public class RiskRelevantPageResultDO {

    private Integer id;

    /**
     * 警号
     */
    private String policeId;

    /**
     * 警员名称
     */
    private String policeName;

    /**
     * 分类名称
     */
    private String typeName;

    /**
     * 问题类型名称
     */
    private String childTypeName;

    /**
     * 扣除分数
     */
    private Double deductionScore;

    /**
     * 记录时间
     */
    private String businessDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getChildTypeName() {
        return childTypeName;
    }

    public void setChildTypeName(String childTypeName) {
        this.childTypeName = childTypeName;
    }

    public Double getDeductionScore() {
        return deductionScore;
    }

    public void setDeductionScore(Double deductionScore) {
        this.deductionScore = deductionScore;
    }

    public String getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(String businessDate) {
        this.businessDate = businessDate;
    }
}

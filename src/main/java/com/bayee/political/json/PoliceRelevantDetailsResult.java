package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/9/4 20:21
 */
public class PoliceRelevantDetailsResult {

    /**
     * 警号
     */
    private String policeId;

    /**
     * 警员名称
     */
    private String policeName;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 分类code
     */
    private String typeCode;

    /**
     * 分类名称
     */
    private String typeCodeName;

    /**
     * 问题分类code
     */
    private String childTypeCode;

    /**
     * 问题分类名称
     */
    private String childTypeCodeName;

    /**
     * 扣除分数
     */
    private Double deductionScore;

    /**
     * 备注
     */
    private String remark;

    /**
     * 记录时间
     */
    private String businessDate;

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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeCodeName() {
        return typeCodeName;
    }

    public void setTypeCodeName(String typeCodeName) {
        this.typeCodeName = typeCodeName;
    }

    public String getChildTypeCode() {
        return childTypeCode;
    }

    public void setChildTypeCode(String childTypeCode) {
        this.childTypeCode = childTypeCode;
    }

    public String getChildTypeCodeName() {
        return childTypeCodeName;
    }

    public void setChildTypeCodeName(String childTypeCodeName) {
        this.childTypeCodeName = childTypeCodeName;
    }

    public Double getDeductionScore() {
        return deductionScore;
    }

    public void setDeductionScore(Double deductionScore) {
        this.deductionScore = deductionScore;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(String businessDate) {
        this.businessDate = businessDate;
    }
}

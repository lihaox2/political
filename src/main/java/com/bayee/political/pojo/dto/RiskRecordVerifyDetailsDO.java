package com.bayee.political.pojo.dto;

/**
 * @author xxl
 * @date 2021/7/20
 */
public class RiskRecordVerifyDetailsDO {

    /**
     * 申诉人名称
     */
    private String appealPoliceName;

    /**
     * 申诉人警号
     */
    private String appealPoliceId;

    /**
     * 申诉类型名称
     */
    private String appealTypeName;

    /**
     * 申诉内容
     */
    private String appealContent;

    /**
     * 申诉分数
     */
    private Double appealScore;

    /**
     * 申诉类型id
     */
    private Integer appealType;

    /**
     * 模型id
     */
    private Integer moduleId;

    /**
     * 申诉时间
     */
    private String appealDate;

    /**
     * 是否同意（1.已同意，2.不同意）
     */
    private Integer isAgree;

    /**
     * 复核内容
     */
    private String checkContent;

    /**
     * 扣除分数
     */
    private Double checkDeductionScore;

    /**
     * 扣除对象名称
     */
    private String checkDeductionPoliceName;

    /**
     * 复核人名称
     */
    private String checkPoliceName;

    /**
     * 复核时间
     */
    private String checkDate;

    public String getAppealPoliceName() {
        return appealPoliceName;
    }

    public void setAppealPoliceName(String appealPoliceName) {
        this.appealPoliceName = appealPoliceName;
    }

    public String getAppealPoliceId() {
        return appealPoliceId;
    }

    public void setAppealPoliceId(String appealPoliceId) {
        this.appealPoliceId = appealPoliceId;
    }

    public Integer getAppealType() {
        return appealType;
    }

    public void setAppealType(Integer appealType) {
        this.appealType = appealType;
    }

    public String getAppealTypeName() {
        return appealTypeName;
    }

    public void setAppealTypeName(String appealTypeName) {
        this.appealTypeName = appealTypeName;
    }

    public String getAppealContent() {
        return appealContent;
    }

    public void setAppealContent(String appealContent) {
        this.appealContent = appealContent;
    }

    public Double getAppealScore() {
        return appealScore;
    }

    public void setAppealScore(Double appealScore) {
        this.appealScore = appealScore;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getAppealDate() {
        return appealDate;
    }

    public void setAppealDate(String appealDate) {
        this.appealDate = appealDate;
    }

    public Integer getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(Integer isAgree) {
        this.isAgree = isAgree;
    }

    public String getCheckContent() {
        return checkContent;
    }

    public void setCheckContent(String checkContent) {
        this.checkContent = checkContent;
    }

    public Double getCheckDeductionScore() {
        return checkDeductionScore;
    }

    public void setCheckDeductionScore(Double checkDeductionScore) {
        this.checkDeductionScore = checkDeductionScore;
    }

    public String getCheckDeductionPoliceName() {
        return checkDeductionPoliceName;
    }

    public void setCheckDeductionPoliceName(String checkDeductionPoliceName) {
        this.checkDeductionPoliceName = checkDeductionPoliceName;
    }

    public String getCheckPoliceName() {
        return checkPoliceName;
    }

    public void setCheckPoliceName(String checkPoliceName) {
        this.checkPoliceName = checkPoliceName;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }
}

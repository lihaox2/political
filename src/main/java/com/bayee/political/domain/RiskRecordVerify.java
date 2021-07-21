package com.bayee.political.domain;

import java.util.Date;

/**
 * 数据项审核
 */
public class RiskRecordVerify {
    private Integer id;

    /**
     * 类型code
     */
    private Integer typeId;

    /**
     * 模块原数据id
     */
    private Integer moduleId;

    /**
     * 申诉人警号
     */
    private String appealPoliceId;

    /**
     * 申诉内容
     */
    private String appealContent;

    /**
     * 申诉分值
     */
    private Double appealScore;

    /**
     * 申诉时间
     */
    private Date appealDate;

    /**
     * 状态（1.申诉中，2.申诉通过，3.申诉拒绝）
     */
    private Integer state;

    /**
     * 复核内容
     */
    private String checkContent;

    /**
     * 是否同意（1.已同意，2.不同意）
     */
    private Integer isAgree;

    /**
     * 扣除分数
     */
    private Double checkDeductionScore;

    /**
     * 扣除对象
     */
    private String checkDeductionPoliceId;

    /**
     * 复核人
     */
    private String checkPoliceId;

    /**
     * 符合时间
     */
    private Date checkDate;

    /**
     * 创建时间
     */
    private Date creationDate;

    /**
     * 修改时间
     */
    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getAppealPoliceId() {
        return appealPoliceId;
    }

    public void setAppealPoliceId(String appealPoliceId) {
        this.appealPoliceId = appealPoliceId == null ? null : appealPoliceId.trim();
    }

    public String getAppealContent() {
        return appealContent;
    }

    public void setAppealContent(String appealContent) {
        this.appealContent = appealContent == null ? null : appealContent.trim();
    }

    public Double getAppealScore() {
        return appealScore;
    }

    public void setAppealScore(Double appealScore) {
        this.appealScore = appealScore;
    }

    public Date getAppealDate() {
        return appealDate;
    }

    public void setAppealDate(Date appealDate) {
        this.appealDate = appealDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCheckContent() {
        return checkContent;
    }

    public void setCheckContent(String checkContent) {
        this.checkContent = checkContent == null ? null : checkContent.trim();
    }

    public Integer getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(Integer isAgree) {
        this.isAgree = isAgree;
    }

    public Double getCheckDeductionScore() {
        return checkDeductionScore;
    }

    public void setCheckDeductionScore(Double checkDeductionScore) {
        this.checkDeductionScore = checkDeductionScore;
    }

    public String getCheckDeductionPoliceId() {
        return checkDeductionPoliceId;
    }

    public void setCheckDeductionPoliceId(String checkDeductionPoliceId) {
        this.checkDeductionPoliceId = checkDeductionPoliceId == null ? null : checkDeductionPoliceId.trim();
    }

    public String getCheckPoliceId() {
        return checkPoliceId;
    }

    public void setCheckPoliceId(String checkPoliceId) {
        this.checkPoliceId = checkPoliceId == null ? null : checkPoliceId.trim();
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
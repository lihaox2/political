package com.bayee.political.domain;

import java.util.Date;

/**
 * 表彰奖励
 */
public class RiskHonour {
    private Integer id;

    /**
     * 警号
     */
    private String policeId;

    /**
     * 奖励名称
     */
    private String honourName;

    /**
     * 奖励原因
     */
    private String honourReason;

    /**
     * 奖励类型
     */
    private String honourTypeCode;

    /**
     * 奖励机关
     */
    private String honourUnit;

    /**
     * 奖励机关级别
     */
    private String honourUnitLevel;

    /**
     * 备注
     */
    private String remark;

    /**
     * 批准时间
     */
    private Date businessTime;

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

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId == null ? null : policeId.trim();
    }

    public String getHonourName() {
        return honourName;
    }

    public void setHonourName(String honourName) {
        this.honourName = honourName == null ? null : honourName.trim();
    }

    public String getHonourReason() {
        return honourReason;
    }

    public void setHonourReason(String honourReason) {
        this.honourReason = honourReason == null ? null : honourReason.trim();
    }

    public String getHonourTypeCode() {
        return honourTypeCode;
    }

    public void setHonourTypeCode(String honourTypeCode) {
        this.honourTypeCode = honourTypeCode == null ? null : honourTypeCode.trim();
    }

    public String getHonourUnit() {
        return honourUnit;
    }

    public void setHonourUnit(String honourUnit) {
        this.honourUnit = honourUnit == null ? null : honourUnit.trim();
    }

    public String getHonourUnitLevel() {
        return honourUnitLevel;
    }

    public void setHonourUnitLevel(String honourUnitLevel) {
        this.honourUnitLevel = honourUnitLevel == null ? null : honourUnitLevel.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(Date businessTime) {
        this.businessTime = businessTime;
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
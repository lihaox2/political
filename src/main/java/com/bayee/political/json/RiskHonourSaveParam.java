package com.bayee.political.json;

import java.util.Date;

/**
 * @author xxl
 * @date 2021/8/28 16:03
 */
public class RiskHonourSaveParam {

    /**
     * id
     */
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
     * 批准时间 (yyyy-MM-dd)
     */
    private String businessTime;

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

    public String getHonourName() {
        return honourName;
    }

    public void setHonourName(String honourName) {
        this.honourName = honourName;
    }

    public String getHonourReason() {
        return honourReason;
    }

    public void setHonourReason(String honourReason) {
        this.honourReason = honourReason;
    }

    public String getHonourTypeCode() {
        return honourTypeCode;
    }

    public void setHonourTypeCode(String honourTypeCode) {
        this.honourTypeCode = honourTypeCode;
    }

    public String getHonourUnit() {
        return honourUnit;
    }

    public void setHonourUnit(String honourUnit) {
        this.honourUnit = honourUnit;
    }

    public String getHonourUnitLevel() {
        return honourUnitLevel;
    }

    public void setHonourUnitLevel(String honourUnitLevel) {
        this.honourUnitLevel = honourUnitLevel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }
}

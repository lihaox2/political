package com.bayee.political.pojo;

import java.util.Date;

/**
 * @author xxl
 * @date 2021/8/28 16:01
 */
public class RiskHonourPageResultDO {

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
    private String honourTypeName;

    /**
     * 奖励机关
     */
    private String honourUnit;

    /**
     * 奖励机关级别
     */
    private String honourUnitLevel;

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

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
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

    public String getHonourTypeName() {
        return honourTypeName;
    }

    public void setHonourTypeName(String honourTypeName) {
        this.honourTypeName = honourTypeName;
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

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }
}

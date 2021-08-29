package com.bayee.political.json;

import java.util.Date;

/**
 * @author xxl
 * @date 2021/8/29 14:16
 */
public class HonourLogListResult {

    /**
     * 类型14-表彰奖励
     */
    private Integer type = 14;

    private Integer id;

    /**
     * 奖励原因
     */
    private String honourReason;

    /**
     * 奖励名称
     */
    private String honourName;

    /**
     * 奖励机关
     */
    private String honourUnit;

    /**
     * 奖励机关级别
     */
    private String honourUnitLevel;

    /**
     * 业务时间
     */
    private Date date;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHonourReason() {
        return honourReason;
    }

    public void setHonourReason(String honourReason) {
        this.honourReason = honourReason;
    }

    public String getHonourName() {
        return honourName;
    }

    public void setHonourName(String honourName) {
        this.honourName = honourName;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

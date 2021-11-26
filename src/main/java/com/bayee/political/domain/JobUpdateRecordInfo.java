package com.bayee.political.domain;

import java.util.Date;

/**
 * 职务更新记录表
 */
public class JobUpdateRecordInfo {
    /**
     * 表序列
     */
    private Integer id;

    /**
     * 警号
     */
    private String policeId;

    /**
     * 职务id
     */
    private Integer positionId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date modifyTime;

    /**
     * 任职时间
     */
    private Date holdOfficeTime;

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

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getHoldOfficeTime() {
        return holdOfficeTime;
    }

    public void setHoldOfficeTime(Date holdOfficeTime) {
        this.holdOfficeTime = holdOfficeTime;
    }
}
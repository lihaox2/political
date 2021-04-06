package com.bayee.political.domain;

import java.util.Date;

public class CalculationPolicePost {
    private Integer id;

    private Integer policeType;

    private String policeStationIds;

    private Integer policePostId;

    private Integer policeNum;

    private Date creationDate;

    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPoliceType() {
        return policeType;
    }

    public void setPoliceType(Integer policeType) {
        this.policeType = policeType;
    }

    public String getPoliceStationIds() {
        return policeStationIds;
    }

    public void setPoliceStationIds(String policeStationIds) {
        this.policeStationIds = policeStationIds == null ? null : policeStationIds.trim();
    }

    public Integer getPolicePostId() {
        return policePostId;
    }

    public void setPolicePostId(Integer policePostId) {
        this.policePostId = policePostId;
    }

    public Integer getPoliceNum() {
        return policeNum;
    }

    public void setPoliceNum(Integer policeNum) {
        this.policeNum = policeNum;
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
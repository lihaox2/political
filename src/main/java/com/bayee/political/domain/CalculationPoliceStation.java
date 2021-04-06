package com.bayee.political.domain;

import java.util.Date;

public class CalculationPoliceStation {
    private Integer id;

    private Integer policeType;

    private Integer policeStationId;

    private String policeStationPostIds;

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

    public Integer getPoliceStationId() {
        return policeStationId;
    }

    public void setPoliceStationId(Integer policeStationId) {
        this.policeStationId = policeStationId;
    }

    public String getPoliceStationPostIds() {
        return policeStationPostIds;
    }

    public void setPoliceStationPostIds(String policeStationPostIds) {
        this.policeStationPostIds = policeStationPostIds == null ? null : policeStationPostIds.trim();
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
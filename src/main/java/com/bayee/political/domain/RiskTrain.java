package com.bayee.political.domain;

import java.util.Date;

public class RiskTrain {
    private Integer id;

    private String policeId;

    private Double indexNum;

    private Integer physicalNum;

    private Integer physicalPassNum;

    private Integer physicalFailNum;

    private Integer physicalStandardStatus;

    private Integer firearmNum;

    private Integer firearmPassNum;

    private Integer firearmFailNum;

    private Integer firearmStandardStatus;

    private Date creationDate;

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

    public Double getIndexNum() {
        return Math.min(indexNum, 10);
    }

    public void setIndexNum(Double indexNum) {
        this.indexNum = indexNum;
    }

    public Integer getPhysicalNum() {
        return physicalNum;
    }

    public void setPhysicalNum(Integer physicalNum) {
        this.physicalNum = physicalNum;
    }

    public Integer getPhysicalPassNum() {
        return physicalPassNum;
    }

    public void setPhysicalPassNum(Integer physicalPassNum) {
        this.physicalPassNum = physicalPassNum;
    }

    public Integer getPhysicalFailNum() {
        return physicalFailNum;
    }

    public void setPhysicalFailNum(Integer physicalFailNum) {
        this.physicalFailNum = physicalFailNum;
    }

    public Integer getPhysicalStandardStatus() {
        return physicalStandardStatus;
    }

    public void setPhysicalStandardStatus(Integer physicalStandardStatus) {
        this.physicalStandardStatus = physicalStandardStatus;
    }

    public Integer getFirearmNum() {
        return firearmNum;
    }

    public void setFirearmNum(Integer firearmNum) {
        this.firearmNum = firearmNum;
    }

    public Integer getFirearmPassNum() {
        return firearmPassNum;
    }

    public void setFirearmPassNum(Integer firearmPassNum) {
        this.firearmPassNum = firearmPassNum;
    }

    public Integer getFirearmFailNum() {
        return firearmFailNum;
    }

    public void setFirearmFailNum(Integer firearmFailNum) {
        this.firearmFailNum = firearmFailNum;
    }

    public Integer getFirearmStandardStatus() {
        return firearmStandardStatus;
    }

    public void setFirearmStandardStatus(Integer firearmStandardStatus) {
        this.firearmStandardStatus = firearmStandardStatus;
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
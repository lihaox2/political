package com.bayee.political.domain;

import java.util.Date;

public class RiskStutyUnitTrainRecord {
    private Integer id;

    private String policeId;

    private Integer isCityTrain;

    private Integer isPoliceSystem;

    private Integer isEducationRectification;

    private Integer isPartyStyleConstruction;

    private Integer totalCount;

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

    public Integer getIsCityTrain() {
        return isCityTrain;
    }

    public void setIsCityTrain(Integer isCityTrain) {
        this.isCityTrain = isCityTrain;
    }

    public Integer getIsPoliceSystem() {
        return isPoliceSystem;
    }

    public void setIsPoliceSystem(Integer isPoliceSystem) {
        this.isPoliceSystem = isPoliceSystem;
    }

    public Integer getIsEducationRectification() {
        return isEducationRectification;
    }

    public void setIsEducationRectification(Integer isEducationRectification) {
        this.isEducationRectification = isEducationRectification;
    }

    public Integer getIsPartyStyleConstruction() {
        return isPartyStyleConstruction;
    }

    public void setIsPartyStyleConstruction(Integer isPartyStyleConstruction) {
        this.isPartyStyleConstruction = isPartyStyleConstruction;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
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
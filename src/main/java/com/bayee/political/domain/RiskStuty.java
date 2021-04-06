package com.bayee.political.domain;

import java.util.Date;

public class RiskStuty {
    private Integer id;

    private String policeId;

    private Double indexNum;

    private Double activitiesPartyNum;

    private Double palmSchoolNum;

    private Double unitTrainNum;

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
        return indexNum;
    }

    public void setIndexNum(Double indexNum) {
        this.indexNum = indexNum;
    }

    public Double getActivitiesPartyNum() {
        return activitiesPartyNum;
    }

    public void setActivitiesPartyNum(Double activitiesPartyNum) {
        this.activitiesPartyNum = activitiesPartyNum;
    }

    public Double getPalmSchoolNum() {
        return palmSchoolNum;
    }

    public void setPalmSchoolNum(Double palmSchoolNum) {
        this.palmSchoolNum = palmSchoolNum;
    }

    public Double getUnitTrainNum() {
        return unitTrainNum;
    }

    public void setUnitTrainNum(Double unitTrainNum) {
        this.unitTrainNum = unitTrainNum;
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
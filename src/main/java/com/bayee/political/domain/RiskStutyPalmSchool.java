package com.bayee.political.domain;

import java.util.Date;

public class RiskStutyPalmSchool {
    private Integer id;

    private String policeId;

    private Double indexNum;

    private Integer needClass;

    private Integer overClass;

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

    public Integer getNeedClass() {
        return needClass;
    }

    public void setNeedClass(Integer needClass) {
        this.needClass = needClass;
    }

    public Integer getOverClass() {
        return overClass;
    }

    public void setOverClass(Integer overClass) {
        this.overClass = overClass;
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
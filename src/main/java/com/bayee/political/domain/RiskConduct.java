package com.bayee.political.domain;

import java.util.Date;

public class RiskConduct {
    private Integer id;

    private String policeId;

    private Integer conductType;

    private Integer seriousStatus;

    private Date inputTime;

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

    public Integer getConductType() {
        return conductType;
    }

    public void setConductType(Integer conductType) {
        this.conductType = conductType;
    }

    public Integer getSeriousStatus() {
        return seriousStatus;
    }

    public void setSeriousStatus(Integer seriousStatus) {
        this.seriousStatus = seriousStatus;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
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
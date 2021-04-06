package com.bayee.political.domain;

import java.util.Date;

public class CalculationFactor {
    private Integer id;

    private Integer caseId;

    private Integer policeStationId;

    private Integer num;

    private Double increment;

    private Date creationDate;

    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public Integer getPoliceStationId() {
        return policeStationId;
    }

    public void setPoliceStationId(Integer policeStationId) {
        this.policeStationId = policeStationId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getIncrement() {
        return increment;
    }

    public void setIncrement(Double increment) {
        this.increment = increment;
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
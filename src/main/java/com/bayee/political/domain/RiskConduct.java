package com.bayee.political.domain;

import java.util.Date;

public class RiskConduct {
    private Integer id;

    private String policeId;

    private Double indexNum;

    private Double bureauRuleScore;

    private Double visitScore;

    private Double trafficViolationScore;

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

    public Double getBureauRuleScore() {
        return bureauRuleScore;
    }

    public void setBureauRuleScore(Double bureauRuleScore) {
        this.bureauRuleScore = bureauRuleScore;
    }

    public Double getVisitScore() {
        return visitScore;
    }

    public void setVisitScore(Double visitScore) {
        this.visitScore = visitScore;
    }

    public Double getTrafficViolationScore() {
        return trafficViolationScore;
    }

    public void setTrafficViolationScore(Double trafficViolationScore) {
        this.trafficViolationScore = trafficViolationScore;
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
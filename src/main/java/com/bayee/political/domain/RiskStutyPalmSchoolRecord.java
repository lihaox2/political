package com.bayee.political.domain;

import java.util.Date;

public class RiskStutyPalmSchoolRecord {
    private Integer id;

    private String policeId;

    private Double textbookLawEnforcementRate;

    private Double policeInvolvedRate;

    private Double gun1HandoverRate;

    private Double forceRate;

    private Double gun2HandoverRate;

    private Double physicalRate;

    private Double drinkDealRate;

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

    public Double getTextbookLawEnforcementRate() {
        return textbookLawEnforcementRate;
    }

    public void setTextbookLawEnforcementRate(Double textbookLawEnforcementRate) {
        this.textbookLawEnforcementRate = textbookLawEnforcementRate;
    }

    public Double getPoliceInvolvedRate() {
        return policeInvolvedRate;
    }

    public void setPoliceInvolvedRate(Double policeInvolvedRate) {
        this.policeInvolvedRate = policeInvolvedRate;
    }

    public Double getGun1HandoverRate() {
        return gun1HandoverRate;
    }

    public void setGun1HandoverRate(Double gun1HandoverRate) {
        this.gun1HandoverRate = gun1HandoverRate;
    }

    public Double getForceRate() {
        return forceRate;
    }

    public void setForceRate(Double forceRate) {
        this.forceRate = forceRate;
    }

    public Double getGun2HandoverRate() {
        return gun2HandoverRate;
    }

    public void setGun2HandoverRate(Double gun2HandoverRate) {
        this.gun2HandoverRate = gun2HandoverRate;
    }

    public Double getPhysicalRate() {
        return physicalRate;
    }

    public void setPhysicalRate(Double physicalRate) {
        this.physicalRate = physicalRate;
    }

    public Double getDrinkDealRate() {
        return drinkDealRate;
    }

    public void setDrinkDealRate(Double drinkDealRate) {
        this.drinkDealRate = drinkDealRate;
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
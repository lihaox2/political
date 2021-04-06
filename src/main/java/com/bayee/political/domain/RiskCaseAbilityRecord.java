package com.bayee.political.domain;

import java.util.Date;

public class RiskCaseAbilityRecord {
    private Integer id;

    private String policeId;

    private Integer reconsiderationLitigationStatus;

    private Integer letterVisitStatus;

    private Integer lawEnforcementFaultStatus;

    private Integer judicialSupervisionStatus;

    private Integer caseExpertStatus;

    private Integer excellentLegalOfficerStatus;

    private Integer basicTestStatus;

    private Integer highTestStatus;

    private Integer judicialTestStatus;

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

    public Integer getReconsiderationLitigationStatus() {
        return reconsiderationLitigationStatus;
    }

    public void setReconsiderationLitigationStatus(Integer reconsiderationLitigationStatus) {
        this.reconsiderationLitigationStatus = reconsiderationLitigationStatus;
    }

    public Integer getLetterVisitStatus() {
        return letterVisitStatus;
    }

    public void setLetterVisitStatus(Integer letterVisitStatus) {
        this.letterVisitStatus = letterVisitStatus;
    }

    public Integer getLawEnforcementFaultStatus() {
        return lawEnforcementFaultStatus;
    }

    public void setLawEnforcementFaultStatus(Integer lawEnforcementFaultStatus) {
        this.lawEnforcementFaultStatus = lawEnforcementFaultStatus;
    }

    public Integer getJudicialSupervisionStatus() {
        return judicialSupervisionStatus;
    }

    public void setJudicialSupervisionStatus(Integer judicialSupervisionStatus) {
        this.judicialSupervisionStatus = judicialSupervisionStatus;
    }

    public Integer getCaseExpertStatus() {
        return caseExpertStatus;
    }

    public void setCaseExpertStatus(Integer caseExpertStatus) {
        this.caseExpertStatus = caseExpertStatus;
    }

    public Integer getExcellentLegalOfficerStatus() {
        return excellentLegalOfficerStatus;
    }

    public void setExcellentLegalOfficerStatus(Integer excellentLegalOfficerStatus) {
        this.excellentLegalOfficerStatus = excellentLegalOfficerStatus;
    }

    public Integer getBasicTestStatus() {
        return basicTestStatus;
    }

    public void setBasicTestStatus(Integer basicTestStatus) {
        this.basicTestStatus = basicTestStatus;
    }

    public Integer getHighTestStatus() {
        return highTestStatus;
    }

    public void setHighTestStatus(Integer highTestStatus) {
        this.highTestStatus = highTestStatus;
    }

    public Integer getJudicialTestStatus() {
        return judicialTestStatus;
    }

    public void setJudicialTestStatus(Integer judicialTestStatus) {
        this.judicialTestStatus = judicialTestStatus;
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
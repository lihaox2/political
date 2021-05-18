package com.bayee.political.domain;

import java.util.Date;

public class RiskCaseAbilityRecord {
    private Integer id;

    private String policeId;
    
    private String year;

    /**
     * 复议诉讼撤变
     */
    private Integer reconsiderationLitigationStatus;

    /**
     * 有责涉案（警）投诉、信访
     */
    private Integer letterVisitStatus;

    /**
     * 重大执法过错
     */
    private Integer lawEnforcementFaultStatus;

    /**
     * 司法监督（检察院纠违）
     */
    private Integer judicialSupervisionStatus;

    /**
     * 执法办案能手
     */
    private Integer caseExpertStatus;

    /**
     * 优秀法制员
     */
    private Integer excellentLegalOfficerStatus;

    /**
     * 基本级执法考试
     */
    private Integer basicTestStatus;

    /**
     * 高级执法考试
     */
    private Integer highTestStatus;

    /**
     * 司法考试
     */
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
}
package com.bayee.political.domain;

import java.util.Date;
import java.util.List;

public class RiskCaseAbility {
    private Integer id;

    private String policeId;

    private Double indexNum;

    private Double reconsiderationLitigationScore;

    private Double letterVisitScore;

    private Double lawEnforcementFaultScore;

    private Double judicialSupervisionScore;

    private Date creationDate;

    private Date updateDate;
    
    private List<ScreenDoubeChart> list;

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

    public Double getReconsiderationLitigationScore() {
        return reconsiderationLitigationScore;
    }

    public void setReconsiderationLitigationScore(Double reconsiderationLitigationScore) {
        this.reconsiderationLitigationScore = reconsiderationLitigationScore;
    }

    public Double getLetterVisitScore() {
        return letterVisitScore;
    }

    public void setLetterVisitScore(Double letterVisitScore) {
        this.letterVisitScore = letterVisitScore;
    }

    public Double getLawEnforcementFaultScore() {
        return lawEnforcementFaultScore;
    }

    public void setLawEnforcementFaultScore(Double lawEnforcementFaultScore) {
        this.lawEnforcementFaultScore = lawEnforcementFaultScore;
    }

    public Double getJudicialSupervisionScore() {
        return judicialSupervisionScore;
    }

    public void setJudicialSupervisionScore(Double judicialSupervisionScore) {
        this.judicialSupervisionScore = judicialSupervisionScore;
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

	public List<ScreenDoubeChart> getList() {
		return list;
	}

	public void setList(List<ScreenDoubeChart> list) {
		this.list = list;
	}
    
}
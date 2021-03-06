package com.bayee.political.domain;

import java.util.Date;
import java.util.List;

public class RiskConduct {
    private Integer id;

    private String policeId;

    private Double indexNum;

    private Double bureauRuleScore;

    private Double visitScore;

    /**
     * 动态排摸分值
     */
    private Double relevantScore;

    private Double trafficViolationScore;

    private Date creationDate;

    private Date updateDate;

    private Double totalNum;
    
    List<ScreenDoubeChart> list;

    public Double getRelevantScore() {
        return relevantScore;
    }

    public void setRelevantScore(Double relevantScore) {
        this.relevantScore = relevantScore;
    }

    public Double getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Double totalNum) {
        this.totalNum = totalNum;
    }

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
        if (null == indexNum) {
            return null;
        }
        return Math.min(indexNum, 25);
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

	public List<ScreenDoubeChart> getList() {
		return list;
	}

	public void setList(List<ScreenDoubeChart> list) {
		this.list = list;
	}
    
}
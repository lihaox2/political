package com.bayee.political.domain;

import java.util.Date;
import java.util.List;

public class RiskConductVisit {
    private Integer id;

    private String policeId;

    private Double indexNum;

    private Integer deductionScoreCount;

    private Double totalDeductionScore;

    private Date creationDate;

    private Date updateDate;
    
    private List<ScreenDoubeChart> list;
    
    private Integer isDisplay;

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

    public Integer getDeductionScoreCount() {
        return deductionScoreCount;
    }

    public void setDeductionScoreCount(Integer deductionScoreCount) {
        this.deductionScoreCount = deductionScoreCount;
    }

    public Double getTotalDeductionScore() {
        return totalDeductionScore;
    }

    public void setTotalDeductionScore(Double totalDeductionScore) {
        this.totalDeductionScore = totalDeductionScore;
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

	public Integer getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(Integer isDisplay) {
		this.isDisplay = isDisplay;
	}
}
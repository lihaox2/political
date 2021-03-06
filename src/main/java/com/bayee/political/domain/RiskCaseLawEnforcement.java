package com.bayee.political.domain;

import java.util.Date;
import java.util.List;

public class RiskCaseLawEnforcement {
    private Integer id;

    private String policeId;

    private Double indexNum;

    private Double totalDeductionScore;

    private Integer totalDeductionCount;

    private Date creationDate;

    private Date updateDate;

    /**
     * 是否显示详情（0否1是）
     */
    private Integer isDisplay;
    
    private List<ScreenDoubeChart> list;

    public Integer getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
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
        return indexNum;
    }

    public void setIndexNum(Double indexNum) {
        this.indexNum = indexNum;
    }

    public Double getTotalDeductionScore() {
        return totalDeductionScore;
    }

    public void setTotalDeductionScore(Double totalDeductionScore) {
        this.totalDeductionScore = totalDeductionScore;
    }

    public Integer getTotalDeductionCount() {
        return totalDeductionCount;
    }

    public void setTotalDeductionCount(Integer totalDeductionCount) {
        this.totalDeductionCount = totalDeductionCount;
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
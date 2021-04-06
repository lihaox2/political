package com.bayee.political.domain;

import java.util.Date;

public class LeaveIntegralManage {
    private Integer id;

    private String scoredPoliceId;

    private Integer policeMonthId;

    private Double integralValue;

    private String scoringPoliceId;

    private Date scoringDate;

    private Date creationDate;

    private Date updateDate;
    
    
    private String scoredPoliceName;
    
    private String policeMonth;
    
    private Integer departmentId;
    
    private String departmentName;
    
    private String scoringPoliceName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScoredPoliceId() {
        return scoredPoliceId;
    }

    public void setScoredPoliceId(String scoredPoliceId) {
        this.scoredPoliceId = scoredPoliceId == null ? null : scoredPoliceId.trim();
    }

    public Integer getPoliceMonthId() {
        return policeMonthId;
    }

    public void setPoliceMonthId(Integer policeMonthId) {
        this.policeMonthId = policeMonthId;
    }

    public Double getIntegralValue() {
        return integralValue;
    }

    public void setIntegralValue(Double integralValue) {
        this.integralValue = integralValue;
    }

    public String getScoringPoliceId() {
        return scoringPoliceId;
    }

    public void setScoringPoliceId(String scoringPoliceId) {
        this.scoringPoliceId = scoringPoliceId == null ? null : scoringPoliceId.trim();
    }

    public Date getScoringDate() {
        return scoringDate;
    }

    public void setScoringDate(Date scoringDate) {
        this.scoringDate = scoringDate;
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

	public String getScoredPoliceName() {
		return scoredPoliceName;
	}

	public void setScoredPoliceName(String scoredPoliceName) {
		this.scoredPoliceName = scoredPoliceName;
	}

	public String getPoliceMonth() {
		return policeMonth;
	}

	public void setPoliceMonth(String policeMonth) {
		this.policeMonth = policeMonth;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getScoringPoliceName() {
		return scoringPoliceName;
	}

	public void setScoringPoliceName(String scoringPoliceName) {
		this.scoringPoliceName = scoringPoliceName;
	}
    
}
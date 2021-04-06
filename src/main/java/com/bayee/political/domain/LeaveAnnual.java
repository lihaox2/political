package com.bayee.political.domain;

import java.util.Date;

public class LeaveAnnual {
    private Integer id;

    private String policeId;
    
    private String name;

    private Integer departmentId;

    private Integer positionId;

    private String year;

    private Double annualLeaveCount;

    private Double annualLeaveDays;

    private Integer annualLeaveNum;

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

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public Double getAnnualLeaveCount() {
        return annualLeaveCount;
    }

    public void setAnnualLeaveCount(Double annualLeaveCount) {
        this.annualLeaveCount = annualLeaveCount;
    }

    public Double getAnnualLeaveDays() {
        return annualLeaveDays;
    }

    public void setAnnualLeaveDays(Double annualLeaveDays) {
        this.annualLeaveDays = annualLeaveDays;
    }

    public Integer getAnnualLeaveNum() {
        return annualLeaveNum;
    }

    public void setAnnualLeaveNum(Integer annualLeaveNum) {
        this.annualLeaveNum = annualLeaveNum;
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

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
    
}
package com.bayee.political.domain;

import java.util.Date;

public class LeaveDuty {
    private Integer id;

    private String policeId;

    private String name;

    private Integer departmentId;

    private Integer positionId;

    private String year;
    
    private String month;

    private Double totalDutyTime;

    private Double residualDutyTime;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public Double getTotalDutyTime() {
        return totalDutyTime;
    }

    public void setTotalDutyTime(Double totalDutyTime) {
        this.totalDutyTime = totalDutyTime;
    }

    public Double getResidualDutyTime() {
        return residualDutyTime;
    }

    public void setResidualDutyTime(Double residualDutyTime) {
        this.residualDutyTime = residualDutyTime;
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
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}
    
}
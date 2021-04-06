package com.bayee.political.domain;

import java.util.Date;

public class ClockRecord {
    private Integer id;
    
    private String policeId;
    
    private String name;
    
    private String departmentName;
    
    private Double timeChange;

    private String userId;

    private String checkType;

    private Long groupId;

    private Long planId;

    private Long recordId;

    private Date workDate;

    private String timeResult;

    private String locationResult;

    private Long approveId;

    private String procinstId;

    private Date baseCheckTime;

    private Date userCheckTime;

    private String sourceType;
    
    private Integer identification;

    private Date creationDate;

    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType == null ? null : checkType.trim();
    }


    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public String getTimeResult() {
        return timeResult;
    }

    public void setTimeResult(String timeResult) {
        this.timeResult = timeResult == null ? null : timeResult.trim();
    }

    public String getLocationResult() {
        return locationResult;
    }

    public void setLocationResult(String locationResult) {
        this.locationResult = locationResult == null ? null : locationResult.trim();
    }


    /**
	 * @return the groupId
	 */
	public Long getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the planId
	 */
	public Long getPlanId() {
		return planId;
	}

	/**
	 * @param planId the planId to set
	 */
	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	/**
	 * @return the recordId
	 */
	public Long getRecordId() {
		return recordId;
	}

	/**
	 * @param recordId the recordId to set
	 */
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	/**
	 * @return the approveId
	 */
	public Long getApproveId() {
		return approveId;
	}

	/**
	 * @param approveId the approveId to set
	 */
	public void setApproveId(Long approveId) {
		this.approveId = approveId;
	}

	public String getProcinstId() {
        return procinstId;
    }

    public void setProcinstId(String procinstId) {
        this.procinstId = procinstId == null ? null : procinstId.trim();
    }

    public Date getBaseCheckTime() {
        return baseCheckTime;
    }

    public void setBaseCheckTime(Date baseCheckTime) {
        this.baseCheckTime = baseCheckTime;
    }

    public Date getUserCheckTime() {
        return userCheckTime;
    }

    public void setUserCheckTime(Date userCheckTime) {
        this.userCheckTime = userCheckTime;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.trim();
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
	 * @return the policeId
	 */
	public String getPoliceId() {
		return policeId;
	}

	/**
	 * @param policeId the policeId to set
	 */
	public void setPoliceId(String policeId) {
		this.policeId = policeId;
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

	/**
	 * @return the timeChange
	 */
	public Double getTimeChange() {
		return timeChange;
	}

	/**
	 * @param timeChange the timeChange to set
	 */
	public void setTimeChange(Double timeChange) {
		this.timeChange = timeChange;
	}

	/**
	 * @return the identification
	 */
	public Integer getIdentification() {
		return identification;
	}

	/**
	 * @param identification the identification to set
	 */
	public void setIdentification(Integer identification) {
		this.identification = identification;
	}

	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
    
}
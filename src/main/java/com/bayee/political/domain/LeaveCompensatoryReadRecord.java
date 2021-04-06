package com.bayee.political.domain;

import java.util.Date;

public class LeaveCompensatoryReadRecord {
    private Integer id;

    private Integer recordId;
    
    private String policeId;

    private String policeName;

	private String headImage;

	private Integer departmentId;

	private String departmentName;

	private Integer positionId;

	private String positionName;
	
	private Date startTime;

	private Date endTime;

    private String approvedId;
    
    private String approvedName;

	private String approvedHeadImage;

	private String approvedDepartmentName;

	private String approvedPositionName;
	
	private String approvedResult;

    private Integer approvedReadStatus;
    
    private Integer isRest;
    
    private Integer timeChange;

    private Date creationDate;
    
    private String creationDateStr;

    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
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

    public String getApprovedId() {
        return approvedId;
    }

    public void setApprovedId(String approvedId) {
        this.approvedId = approvedId == null ? null : approvedId.trim();
    }

    public Integer getApprovedReadStatus() {
        return approvedReadStatus;
    }

    public void setApprovedReadStatus(Integer approvedReadStatus) {
        this.approvedReadStatus = approvedReadStatus;
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
	 * @return the policeName
	 */
	public String getPoliceName() {
		return policeName;
	}

	/**
	 * @param policeName the policeName to set
	 */
	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}

	/**
	 * @return the headImage
	 */
	public String getHeadImage() {
		return headImage;
	}

	/**
	 * @param headImage the headImage to set
	 */
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
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

	/**
	 * @return the positionName
	 */
	public String getPositionName() {
		return positionName;
	}

	/**
	 * @param positionName the positionName to set
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	/**
	 * @return the approvedName
	 */
	public String getApprovedName() {
		return approvedName;
	}

	/**
	 * @param approvedName the approvedName to set
	 */
	public void setApprovedName(String approvedName) {
		this.approvedName = approvedName;
	}

	/**
	 * @return the approvedHeadImage
	 */
	public String getApprovedHeadImage() {
		return approvedHeadImage;
	}

	/**
	 * @param approvedHeadImage the approvedHeadImage to set
	 */
	public void setApprovedHeadImage(String approvedHeadImage) {
		this.approvedHeadImage = approvedHeadImage;
	}

	/**
	 * @return the approvedDepartmentName
	 */
	public String getApprovedDepartmentName() {
		return approvedDepartmentName;
	}

	/**
	 * @param approvedDepartmentName the approvedDepartmentName to set
	 */
	public void setApprovedDepartmentName(String approvedDepartmentName) {
		this.approvedDepartmentName = approvedDepartmentName;
	}

	/**
	 * @return the approvedPositionName
	 */
	public String getApprovedPositionName() {
		return approvedPositionName;
	}

	/**
	 * @param approvedPositionName the approvedPositionName to set
	 */
	public void setApprovedPositionName(String approvedPositionName) {
		this.approvedPositionName = approvedPositionName;
	}

	/**
	 * @return the approvedResult
	 */
	public String getApprovedResult() {
		return approvedResult;
	}

	/**
	 * @param approvedResult the approvedResult to set
	 */
	public void setApprovedResult(String approvedResult) {
		this.approvedResult = approvedResult;
	}

	/**
	 * @return the timeChange
	 */
	public Integer getTimeChange() {
		return timeChange;
	}

	/**
	 * @param timeChange the timeChange to set
	 */
	public void setTimeChange(Integer timeChange) {
		this.timeChange = timeChange;
	}

	/**
	 * @return the creationDateStr
	 */
	public String getCreationDateStr() {
		return creationDateStr;
	}

	/**
	 * @param creationDateStr the creationDateStr to set
	 */
	public void setCreationDateStr(String creationDateStr) {
		this.creationDateStr = creationDateStr;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the isRest
	 */
	public Integer getIsRest() {
		return isRest;
	}

	/**
	 * @param isRest the isRest to set
	 */
	public void setIsRest(Integer isRest) {
		this.isRest = isRest;
	}
    
}
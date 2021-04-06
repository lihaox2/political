package com.bayee.political.domain;

import java.util.Date;

public class LeaveRestManage {
    private Integer id;

    private String restArrangerPoliceId;

    private String restPoliceId;

    private Integer isRest;

    private Date startTime;

    private Date endTime;

    private String reason;

    private Date creationDate;

    private Date updateDate;
    
    
    private String restArrangerPoliceName;
    
    private String restPoliceName;
    
    private Integer positionId;
    
    private String positionName;
    
    private Integer departmentId;
    
    private String departmentName;
    
    private Integer gender;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRestArrangerPoliceId() {
        return restArrangerPoliceId;
    }

    public void setRestArrangerPoliceId(String restArrangerPoliceId) {
        this.restArrangerPoliceId = restArrangerPoliceId == null ? null : restArrangerPoliceId.trim();
    }

    public String getRestPoliceId() {
        return restPoliceId;
    }

    public void setRestPoliceId(String restPoliceId) {
        this.restPoliceId = restPoliceId == null ? null : restPoliceId.trim();
    }

    public Integer getIsRest() {
        return isRest;
    }

    public void setIsRest(Integer isRest) {
        this.isRest = isRest;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
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

	public String getRestArrangerPoliceName() {
		return restArrangerPoliceName;
	}

	public void setRestArrangerPoliceName(String restArrangerPoliceName) {
		this.restArrangerPoliceName = restArrangerPoliceName;
	}

	public String getRestPoliceName() {
		return restPoliceName;
	}

	public void setRestPoliceName(String restPoliceName) {
		this.restPoliceName = restPoliceName;
	}

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
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

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}
    
}
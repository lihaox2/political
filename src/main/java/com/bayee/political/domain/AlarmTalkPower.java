package com.bayee.political.domain;

import java.util.Date;

public class AlarmTalkPower {
    private Integer id;

    private Integer departmentId;

    private String policeId;

    private Integer condition;

    private Date creationDate;

    private Date updateDate;
    
    private Integer PoliceNumber;
    
    private String policeObjectIds;
    
    private String policeObjectNames;
    
    private String departmentName;
    
    private String policeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId == null ? null : policeId.trim();
    }


    /**
	 * @return the condition
	 */
	public Integer getCondition() {
		return condition;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setCondition(Integer condition) {
		this.condition = condition;
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

    public String getPoliceObjectIds() {
        return policeObjectIds;
    }

    public void setPoliceObjectIds(String policeObjectIds) {
        this.policeObjectIds = policeObjectIds == null ? null : policeObjectIds.trim();
    }

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getPoliceName() {
		return policeName;
	}

	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}

	public String getPoliceObjectNames() {
		return policeObjectNames;
	}

	public void setPoliceObjectNames(String policeObjectNames) {
		this.policeObjectNames = policeObjectNames;
	}

	public Integer getPoliceNumber() {
		return PoliceNumber;
	}

	public void setPoliceNumber(Integer policeNumber) {
		PoliceNumber = policeNumber;
	}
    
}
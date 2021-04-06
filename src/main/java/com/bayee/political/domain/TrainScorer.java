package com.bayee.political.domain;

import java.util.Date;

public class TrainScorer {
    private Integer id;

    private Integer groupId;

    private String scorerId;

    private Date creationDate;

    private Date updateDate;
    
    private String groupName;
    
    private String policeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getScorerId() {
        return scorerId;
    }

    public void setScorerId(String scorerId) {
        this.scorerId = scorerId == null ? null : scorerId.trim();
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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getPoliceName() {
		return policeName;
	}

	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}
    
}
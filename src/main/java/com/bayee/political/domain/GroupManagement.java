package com.bayee.political.domain;

import java.util.Date;

public class GroupManagement {
    private Integer id;

    private String groupName;

    private String description;

    private Integer isStop;

    private Date creationDate;
    
    private String creationDateStr;

    private Date updateDate;
    
    private Integer num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getIsStop() {
        return isStop;
    }

    public void setIsStop(Integer isStop) {
        this.isStop = isStop;
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

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getCreationDateStr() {
		return creationDateStr;
	}

	public void setCreationDateStr(String creationDateStr) {
		this.creationDateStr = creationDateStr;
	}
	
}
package com.bayee.political.domain;

import java.util.Date;

public class TrainPhysicalProjectRecord {
    private Integer id;

    private Integer trainPhysicalId;

    private Integer trainGroupId;

    private String trainProject;

    private Date creationDate;

    private Date updateDate;
    
    private String groupName;
    
    private String projectName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrainPhysicalId() {
        return trainPhysicalId;
    }

    public void setTrainPhysicalId(Integer trainPhysicalId) {
        this.trainPhysicalId = trainPhysicalId;
    }

    public Integer getTrainGroupId() {
        return trainGroupId;
    }

    public void setTrainGroupId(Integer trainGroupId) {
        this.trainGroupId = trainGroupId;
    }

    public String getTrainProject() {
        return trainProject;
    }

    public void setTrainProject(String trainProject) {
        this.trainProject = trainProject == null ? null : trainProject.trim();
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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
    
}
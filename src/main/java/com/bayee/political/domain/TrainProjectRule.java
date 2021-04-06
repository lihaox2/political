package com.bayee.political.domain;

import java.util.Date;

public class TrainProjectRule {
    private Integer id;

    private String name;

    private Integer projectId;
    
    /**
     * 运算符 1>= 2> 3<= 4< 5=
     */
    private Integer symbol;

    private Integer groupId;

    private Double qualifiedPhysical;

    private Double qualifiedFirearmA;

    private Double limitTime;

    private Date creationDate;

    private Date updateDate;
    
    private String projectName;
    
    private String groupName;
    
    private String projectIds;
    
    /**
     * 项目类型
     */
    private Integer projectType;
    
    /**
     * 项目是否U型射击
     */
    private Integer projectIsu;
    
    /**
     * 项目规则
     */
    private Integer sort;
    
   /**
    * 射击类型id
    */
   private Integer trainProjectType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Double getQualifiedPhysical() {
        return qualifiedPhysical;
    }

    public void setQualifiedPhysical(Double qualifiedPhysical) {
        this.qualifiedPhysical = qualifiedPhysical;
    }

    public Double getQualifiedFirearmA() {
        return qualifiedFirearmA;
    }

    public void setQualifiedFirearmA(Double qualifiedFirearmA) {
        this.qualifiedFirearmA = qualifiedFirearmA;
    }

    public Double getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Double limitTime) {
        this.limitTime = limitTime;
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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(String projectIds) {
		this.projectIds = projectIds;
	}

	public Integer getSymbol() {
		return symbol;
	}

	public void setSymbol(Integer symbol) {
		this.symbol = symbol;
	}

	public Integer getProjectType() {
		return projectType;
	}

	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}

	public Integer getProjectIsu() {
		return projectIsu;
	}

	public void setProjectIsu(Integer projectIsu) {
		this.projectIsu = projectIsu;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getTrainProjectType() {
		return trainProjectType;
	}

	public void setTrainProjectType(Integer trainProjectType) {
		this.trainProjectType = trainProjectType;
	}
	
}
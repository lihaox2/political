package com.bayee.political.domain;

/**
 * 训练成绩模板
 * @author 23520
 *
 */
public class TrainAchievementTemplate {

	private String name;//赛事或训练名称
	
	private String policeName;//姓名
	
	private String policeId;//警号
	
	private String projectTypeName;//项目类型名称
	
	private Integer isU;//是否U型靶
	
	private Double shootTime; // U型靶射击时间
	
	private String projectName;//项目名称
	
	private String achievementStr;//成绩字符串
	
	private String unit;//单位

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPoliceName() {
		return policeName;
	}

	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}

	public String getProjectTypeName() {
		return projectTypeName;
	}

	public void setProjectTypeName(String projectTypeName) {
		this.projectTypeName = projectTypeName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getAchievementStr() {
		return achievementStr;
	}

	public void setAchievementStr(String achievementStr) {
		this.achievementStr = achievementStr;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getIsU() {
		return isU;
	}

	public void setIsU(Integer isU) {
		this.isU = isU;
	}

	public String getPoliceId() {
		return policeId;
	}

	public void setPoliceId(String policeId) {
		this.policeId = policeId;
	}

	public Double getShootTime() {
		return shootTime;
	}

	public void setShootTime(Double shootTime) {
		this.shootTime = shootTime;
	}
	
}

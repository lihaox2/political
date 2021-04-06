package com.bayee.political.domain;

import java.util.Date;

public class TrainPhysicalAchievementDetails {
	private Integer id;

	private Integer trainPhysicalId;

	private Integer trainPhysicalAchievementId;

	private String policeId;

	private String departmentName;

	private Integer type;

	private Integer projectId;

	private String projectName;// 训练项目名称

	private Integer isEntry;// 是否参加（1未参加2已参加）

	private Double achievement;

	private Integer achievementFirst;

	private Double achievementSecond;

	private String unitName;// 单位名称

	private Integer achievementGrade;

	private String achievementStr;// 成绩（字符串）

	private Date achievementDate;

	private Date signDate;// 签到时间

	private Integer isSign;

	/**
	 * 排名
	 */
	private Integer rank;

	private Integer sortNum;// 排名方式1：升序2降序

	private Date creationDate;

	private Date updateDate;

	/**
	 * 姓名
	 */
	private String policeName;

	/**
	 * 身高
	 */
	private Double policeHeight;

	/**
	 * 体重
	 */
	private Double policeWeight;

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

	public Integer getTrainPhysicalAchievementId() {
		return trainPhysicalAchievementId;
	}

	public void setTrainPhysicalAchievementId(Integer trainPhysicalAchievementId) {
		this.trainPhysicalAchievementId = trainPhysicalAchievementId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Double getAchievement() {
		return achievement;
	}

	public void setAchievement(Double achievement) {
		this.achievement = achievement;
	}

	public Integer getAchievementGrade() {
		return achievementGrade;
	}

	public void setAchievementGrade(Integer achievementGrade) {
		this.achievementGrade = achievementGrade;
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
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the unitName
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * @param unitName the unitName to set
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
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
	 * @return the isEntry
	 */
	public Integer getIsEntry() {
		return isEntry;
	}

	/**
	 * @param isEntry the isEntry to set
	 */
	public void setIsEntry(Integer isEntry) {
		this.isEntry = isEntry;
	}

	public String getPoliceName() {
		return policeName;
	}

	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}

	public Double getPoliceHeight() {
		return policeHeight;
	}

	public void setPoliceHeight(Double policeHeight) {
		this.policeHeight = policeHeight;
	}

	public Double getPoliceWeight() {
		return policeWeight;
	}

	public void setPoliceWeight(Double policeWeight) {
		this.policeWeight = policeWeight;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the achievementStr
	 */
	public String getAchievementStr() {
		return achievementStr;
	}

	/**
	 * @param achievementStr the achievementStr to set
	 */
	public void setAchievementStr(String achievementStr) {
		this.achievementStr = achievementStr;
	}

	/**
	 * @return the signDate
	 */
	public Date getSignDate() {
		return signDate;
	}

	/**
	 * @param signDate the signDate to set
	 */
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	/**
	 * @return the isSign
	 */
	public Integer getIsSign() {
		return isSign;
	}

	/**
	 * @param isSign the isSign to set
	 */
	public void setIsSign(Integer isSign) {
		this.isSign = isSign;
	}

	/**
	 * @return the achievementDate
	 */
	public Date getAchievementDate() {
		return achievementDate;
	}

	/**
	 * @param achievementDate the achievementDate to set
	 */
	public void setAchievementDate(Date achievementDate) {
		this.achievementDate = achievementDate;
	}

	/**
	 * @return the achievementFirst
	 */
	public Integer getAchievementFirst() {
		return achievementFirst;
	}

	/**
	 * @param achievementFirst the achievementFirst to set
	 */
	public void setAchievementFirst(Integer achievementFirst) {
		this.achievementFirst = achievementFirst;
	}

	/**
	 * @return the achievementSecond
	 */
	public Double getAchievementSecond() {
		return achievementSecond;
	}

	/**
	 * @param achievementSecond the achievementSecond to set
	 */
	public void setAchievementSecond(Double achievementSecond) {
		this.achievementSecond = achievementSecond;
	}

	/**
	 * @return the sortNum
	 */
	public Integer getSortNum() {
		return sortNum;
	}

	/**
	 * @param sortNum the sortNum to set
	 */
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

}
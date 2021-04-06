package com.bayee.political.domain;

import java.util.Date;

public class TrainFirearmAchievement {
	private Integer id;

	private String headImage;

	private String policeId;

	private String policeName;

	private String departmentName;

	private String name;

	private Integer rank;

	private Date registrationDate;

	private Date achievementDate;

	private Date signDate;// 签到时间

	private Integer trainFirearmId;

	private String trainFirearmName;

	private Date trainStartDate;

	private Date trainEndDate;

	private Integer trainProjectType;

	private String projectName;

	private Double achievement;

	private Integer achievementFirst;

	private Double achievementSecond;

	private Double shootTime;// 射击时间

	private String unitName;

	private Integer achievementGrade;

	private String achievementStr;// 成绩（字符串）

	private String qrCode;

	private Integer isSign;

	private Integer isSubmit;

	private Date creationDate;

	private Date updateDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPoliceId() {
		return policeId;
	}

	public void setPoliceId(String policeId) {
		this.policeId = policeId == null ? null : policeId.trim();
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getAchievementDate() {
		return achievementDate;
	}

	public void setAchievementDate(Date achievementDate) {
		this.achievementDate = achievementDate;
	}

	public Integer getTrainFirearmId() {
		return trainFirearmId;
	}

	public void setTrainFirearmId(Integer trainFirearmId) {
		this.trainFirearmId = trainFirearmId;
	}

	public Integer getTrainProjectType() {
		return trainProjectType;
	}

	public void setTrainProjectType(Integer trainProjectType) {
		this.trainProjectType = trainProjectType;
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

	public Integer getIsSign() {
		return isSign;
	}

	public void setIsSign(Integer isSign) {
		this.isSign = isSign;
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
	 * @return the trainFirearmName
	 */
	public String getTrainFirearmName() {
		return trainFirearmName;
	}

	/**
	 * @param trainFirearmName the trainFirearmName to set
	 */
	public void setTrainFirearmName(String trainFirearmName) {
		this.trainFirearmName = trainFirearmName;
	}

	/**
	 * @return the trainEndDate
	 */
	public Date getTrainEndDate() {
		return trainEndDate;
	}

	/**
	 * @param trainEndDate the trainEndDate to set
	 */
	public void setTrainEndDate(Date trainEndDate) {
		this.trainEndDate = trainEndDate;
	}

	/**
	 * @return the shootTime
	 */
	public Double getShootTime() {
		return shootTime;
	}

	/**
	 * @param shootTime the shootTime to set
	 */
	public void setShootTime(Double shootTime) {
		this.shootTime = shootTime;
	}

	/**
	 * @return the isSubmit
	 */
	public Integer getIsSubmit() {
		return isSubmit;
	}

	/**
	 * @param isSubmit the isSubmit to set
	 */
	public void setIsSubmit(Integer isSubmit) {
		this.isSubmit = isSubmit;
	}

	/**
	 * @return the qrCode
	 */
	public String getQrCode() {
		return qrCode;
	}

	/**
	 * @param qrCode the qrCode to set
	 */
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
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

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getPoliceName() {
		return policeName;
	}

	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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
	 * @return the trainStartDate
	 */
	public Date getTrainStartDate() {
		return trainStartDate;
	}

	/**
	 * @param trainStartDate the trainStartDate to set
	 */
	public void setTrainStartDate(Date trainStartDate) {
		this.trainStartDate = trainStartDate;
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

}
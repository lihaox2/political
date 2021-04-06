package com.bayee.political.domain;

import java.util.Date;

public class TrainMatchAchievement {
	private Integer id;

	private Integer trainMatchId;
	
	private Integer nature;

	private String name;

	private String matchTypeName;// 比赛类型名称

	private String matchProjectName;// 比赛项目名称
	
	private String unitName;//成绩单位
	
	private String projectName;//项目名称

	private String place;

	private String headImage;

	private String policeId;

	private String policeName;

	private Integer departmentId;
	
	private Date matchEndDate;

	private Date registrationDate;

	private Date signDate;// 签到时间

	private Date achievementDate;

	private Double achievement;
	
	private Integer achievementFirst;

	private Double achievementSecond;

	private Integer achievementGrade;

	private String achievementStr;// 成绩（字符串）

	private String qrCode;

	private Integer status;

	private Integer isSign;

	private Date creationDate;

	private String creationDateStr;

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

	/**
	 * @return the achievement
	 */
	public Double getAchievement() {
		return achievement;
	}

	/**
	 * @param achievement the achievement to set
	 */
	public void setAchievement(Double achievement) {
		this.achievement = achievement;
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
	 * @return the achievementGrade
	 */
	public Integer getAchievementGrade() {
		return achievementGrade;
	}

	/**
	 * @param achievementGrade the achievementGrade to set
	 */
	public void setAchievementGrade(Integer achievementGrade) {
		this.achievementGrade = achievementGrade;
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
	 * @return the departmentId
	 */
	public Integer getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return the trainMatchId
	 */
	public Integer getTrainMatchId() {
		return trainMatchId;
	}

	/**
	 * @param trainMatchId the trainMatchId to set
	 */
	public void setTrainMatchId(Integer trainMatchId) {
		this.trainMatchId = trainMatchId;
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
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
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
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * @param place the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}

	/**
	 * @return the matchEndDate
	 */
	public Date getMatchEndDate() {
		return matchEndDate;
	}

	/**
	 * @param matchEndDate the matchEndDate to set
	 */
	public void setMatchEndDate(Date matchEndDate) {
		this.matchEndDate = matchEndDate;
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
	 * @return the matchTypeName
	 */
	public String getMatchTypeName() {
		return matchTypeName;
	}

	/**
	 * @param matchTypeName the matchTypeName to set
	 */
	public void setMatchTypeName(String matchTypeName) {
		this.matchTypeName = matchTypeName;
	}

	/**
	 * @return the matchProjectName
	 */
	public String getMatchProjectName() {
		return matchProjectName;
	}

	/**
	 * @param matchProjectName the matchProjectName to set
	 */
	public void setMatchProjectName(String matchProjectName) {
		this.matchProjectName = matchProjectName;
	}

	/**
	 * @return the nature
	 */
	public Integer getNature() {
		return nature;
	}

	/**
	 * @param nature the nature to set
	 */
	public void setNature(Integer nature) {
		this.nature = nature;
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

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
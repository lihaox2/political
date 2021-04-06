package com.bayee.political.domain;

import java.util.Date;
import java.util.List;

public class TrainMatch {
	private Integer id;

	private String name;

	private Integer type;// (赛事归属)1单位2分局

	private Integer objectId;// 1参赛者2领队;

	private Integer departmentId;

	private String departmentName;

	private Integer matchTypeId;

	private String matchTypeName;// 比赛类型名称

	private Integer matchProjectId;// 项目id

	private String matchProjectName;// 项目名称

	private Integer projectId;// 比赛项目id

	private String projectName;// 比赛项目名称

	private Integer isMoreUnit;

	private Integer policeNum;// 报名人数

	private Integer nature;

	private String place;

	private Date registrationStartDate;

	private Date registrationEndDate;

	private Date matchStartDate;

	private Date matchEndDate;

	private Integer TimeChange;

	private Integer signUpStatus;// 状态(1报名未开始2报名进行中3报名已结束)

	private Integer status;

	private Double achievement;

	private Integer achievementGrade;

	private String achievementStr;

	private String depAchievementStr;

	private Integer isSign;

	private String qrCode;

	private String headImageStr;

	private String scorerPoliceId;

	private String coverImg;

	private String matchContent;

	private String trainImg;

	private Integer limitPeopleNum;
	private String limitPeopleStr;
	private Integer maxPeopleNum;
	private Integer totalMinNum;
	private Integer totalMaxNum;
	private Integer manMinNum;
	private Integer manMaxNum;
	private Integer womanMinNum;
	private Integer womanMaxNum;

	private Integer isSubmit;// 是否提交（0否1是）

	private String policeId;

	private String policeName;

	private Integer sex;// 1男性2女性3男女不限

	private Integer sort;// 排序方式（1升序2降序）

	private Integer startTimeChange;

	private Date submitDate;

	private Date creationDate;

	private String reportDateStr;// 报名剩余时间

	private String creationDateStr;

	private Date updateDate;

	private List<CalculationChart> headImageList;

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

	/**
	 * @return the matchTypeId
	 */
	public Integer getMatchTypeId() {
		return matchTypeId;
	}

	/**
	 * @param matchTypeId the matchTypeId to set
	 */
	public void setMatchTypeId(Integer matchTypeId) {
		this.matchTypeId = matchTypeId;
	}

	public Integer getNature() {
		return nature;
	}

	public void setNature(Integer nature) {
		this.nature = nature;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place == null ? null : place.trim();
	}

	public Date getRegistrationStartDate() {
		return registrationStartDate;
	}

	public void setRegistrationStartDate(Date registrationStartDate) {
		this.registrationStartDate = registrationStartDate;
	}

	public Date getRegistrationEndDate() {
		return registrationEndDate;
	}

	public void setRegistrationEndDate(Date registrationEndDate) {
		this.registrationEndDate = registrationEndDate;
	}

	public Date getMatchStartDate() {
		return matchStartDate;
	}

	public void setMatchStartDate(Date matchStartDate) {
		this.matchStartDate = matchStartDate;
	}

	public Date getMatchEndDate() {
		return matchEndDate;
	}

	public void setMatchEndDate(Date matchEndDate) {
		this.matchEndDate = matchEndDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getScorerPoliceId() {
		return scorerPoliceId;
	}

	public void setScorerPoliceId(String scorerPoliceId) {
		this.scorerPoliceId = scorerPoliceId == null ? null : scorerPoliceId.trim();
	}

	public Integer getLimitPeopleNum() {
		return limitPeopleNum;
	}

	public void setLimitPeopleNum(Integer limitPeopleNum) {
		this.limitPeopleNum = limitPeopleNum;
	}

	public Integer getMaxPeopleNum() {
		return maxPeopleNum;
	}

	public void setMaxPeopleNum(Integer maxPeopleNum) {
		this.maxPeopleNum = maxPeopleNum;
	}

	public String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg == null ? null : coverImg.trim();
	}

	public String getTrainImg() {
		return trainImg;
	}

	public void setTrainImg(String trainImg) {
		this.trainImg = trainImg == null ? null : trainImg.trim();
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

	public String getMatchContent() {
		return matchContent;
	}

	public void setMatchContent(String matchContent) {
		this.matchContent = matchContent == null ? null : matchContent.trim();
	}

	/**
	 * @return the signUpStatus
	 */
	public Integer getSignUpStatus() {
		return signUpStatus;
	}

	/**
	 * @param signUpStatus the signUpStatus to set
	 */
	public void setSignUpStatus(Integer signUpStatus) {
		this.signUpStatus = signUpStatus;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
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
	 * @return the policeNum
	 */
	public Integer getPoliceNum() {
		return policeNum;
	}

	/**
	 * @param policeNum the policeNum to set
	 */
	public void setPoliceNum(Integer policeNum) {
		this.policeNum = policeNum;
	}

	public Integer getTotalMinNum() {
		return totalMinNum;
	}

	public void setTotalMinNum(Integer totalMinNum) {
		this.totalMinNum = totalMinNum;
	}

	public Integer getTotalMaxNum() {
		return totalMaxNum;
	}

	public void setTotalMaxNum(Integer totalMaxNum) {
		this.totalMaxNum = totalMaxNum;
	}

	public Integer getManMinNum() {
		return manMinNum;
	}

	public void setManMinNum(Integer manMinNum) {
		this.manMinNum = manMinNum;
	}

	public Integer getManMaxNum() {
		return manMaxNum;
	}

	public void setManMaxNum(Integer manMaxNum) {
		this.manMaxNum = manMaxNum;
	}

	public Integer getWomanMinNum() {
		return womanMinNum;
	}

	public void setWomanMinNum(Integer womanMinNum) {
		this.womanMinNum = womanMinNum;
	}

	public Integer getWomanMaxNum() {
		return womanMaxNum;
	}

	public void setWomanMaxNum(Integer womanMaxNum) {
		this.womanMaxNum = womanMaxNum;
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
	 * @return the timeChange
	 */
	public Integer getTimeChange() {
		return TimeChange;
	}

	/**
	 * @param timeChange the timeChange to set
	 */
	public void setTimeChange(Integer timeChange) {
		TimeChange = timeChange;
	}

	/**
	 * @return the projectId
	 */
	public Integer getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
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

	public String getPoliceName() {
		return policeName;
	}

	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}

	/**
	 * @return the sex
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
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

	public Integer getMatchProjectId() {
		return matchProjectId;
	}

	public void setMatchProjectId(Integer matchProjectId) {
		this.matchProjectId = matchProjectId;
	}

	public String getMatchProjectName() {
		return matchProjectName;
	}

	public void setMatchProjectName(String matchProjectName) {
		this.matchProjectName = matchProjectName;
	}

	/**
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
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
	 * @return the depAchievementStr
	 */
	public String getDepAchievementStr() {
		return depAchievementStr;
	}

	/**
	 * @param depAchievementStr the depAchievementStr to set
	 */
	public void setDepAchievementStr(String depAchievementStr) {
		this.depAchievementStr = depAchievementStr;
	}

	/**
	 * @return the headImageStr
	 */
	public String getHeadImageStr() {
		return headImageStr;
	}

	/**
	 * @param headImageStr the headImageStr to set
	 */
	public void setHeadImageStr(String headImageStr) {
		this.headImageStr = headImageStr;
	}

	/**
	 * @return the headImageList
	 */
	public List<CalculationChart> getHeadImageList() {
		return headImageList;
	}

	/**
	 * @param headImageList the headImageList to set
	 */
	public void setHeadImageList(List<CalculationChart> headImageList) {
		this.headImageList = headImageList;
	}

	/**
	 * @return the startTimeChange
	 */
	public Integer getStartTimeChange() {
		return startTimeChange;
	}

	/**
	 * @param startTimeChange the startTimeChange to set
	 */
	public void setStartTimeChange(Integer startTimeChange) {
		this.startTimeChange = startTimeChange;
	}

	/**
	 * @return the limitPeopleStr
	 */
	public String getLimitPeopleStr() {
		return limitPeopleStr;
	}

	/**
	 * @param limitPeopleStr the limitPeopleStr to set
	 */
	public void setLimitPeopleStr(String limitPeopleStr) {
		this.limitPeopleStr = limitPeopleStr;
	}

	/**
	 * @return the submitDate
	 */
	public Date getSubmitDate() {
		return submitDate;
	}

	/**
	 * @param submitDate the submitDate to set
	 */
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
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
	 * @return the isMoreUnit
	 */
	public Integer getIsMoreUnit() {
		return isMoreUnit;
	}

	/**
	 * @param isMoreUnit the isMoreUnit to set
	 */
	public void setIsMoreUnit(Integer isMoreUnit) {
		this.isMoreUnit = isMoreUnit;
	}

	/**
	 * @return the objectId
	 */
	public Integer getObjectId() {
		return objectId;
	}

	/**
	 * @param objectId the objectId to set
	 */
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	/**
	 * @return the reportDateStr
	 */
	public String getReportDateStr() {
		return reportDateStr;
	}

	/**
	 * @param reportDateStr the reportDateStr to set
	 */
	public void setReportDateStr(String reportDateStr) {
		this.reportDateStr = reportDateStr;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}
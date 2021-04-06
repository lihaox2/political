package com.bayee.political.domain;

/**
 * @author shentuqiwei
 * @version 2020年10月10日 下午3:10:18
 */
public class TrainRank {
	
	private Integer rankId;// 排名
	
	private Integer projectId;

	private String headImage;// 警员头像

	private String policeId;// 警员id

	private String name;// 警员姓名

	private String departmentName;// 部门名称

	private Double achievement;// 成绩
	
	private Integer achievementFirst;

	private Double achievementSecond;
	
	private String achievementStr;// 成绩

	private String unitName;// 成绩的单位

	private Integer isSubmit;

	private Integer isU;// 是否是U型射击(1否2是)

	/**
	 * @return the rankId
	 */
	public Integer getRankId() {
		return rankId;
	}

	/**
	 * @param rankId the rankId to set
	 */
	public void setRankId(Integer rankId) {
		this.rankId = rankId;
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
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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
	 * @return the isU
	 */
	public Integer getIsU() {
		return isU;
	}

	/**
	 * @param isU the isU to set
	 */
	public void setIsU(Integer isU) {
		this.isU = isU;
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

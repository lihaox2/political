package com.bayee.political.domain;

import java.util.Date;

/**
 * @author shentuqiwei
 * @version 2021年4月14日 下午12:15:21
 */
public class RiskTrainFirearmRecord {

	private Integer id;

	private String projectName;

	private Integer achievementGrade;

	private String achievementGradeStr;

	private String achievementStr;// 成绩（字符串）

	private Date creationDate;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @return the achievementGradeStr
	 */
	public String getAchievementGradeStr() {
		return achievementGradeStr;
	}

	/**
	 * @param achievementGradeStr the achievementGradeStr to set
	 */
	public void setAchievementGradeStr(String achievementGradeStr) {
		this.achievementGradeStr = achievementGradeStr;
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
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}

package com.bayee.political.domain;

import java.util.Date;
import java.util.List;

/** 
* @author  shentuqiwei 
* @version 2021年4月14日 下午12:17:59 
*/
public class RiskTrainPhysicalRecord {

	private Integer id;
	
	private Date creationDate;
	
	private Integer achievementGrade;

	private String achievementGradeStr;
	
	private List<RiskTrainPhysicalAchievementDetails> trainList;

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
	 * @return the trainList
	 */
	public List<RiskTrainPhysicalAchievementDetails> getTrainList() {
		return trainList;
	}

	/**
	 * @param trainList the trainList to set
	 */
	public void setTrainList(List<RiskTrainPhysicalAchievementDetails> trainList) {
		this.trainList = trainList;
	}
	
}

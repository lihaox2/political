package com.bayee.political.domain;

import java.util.Date;
import java.util.List;

/** 
* @author  shentuqiwei 
* @version 2020年10月30日 下午7:43:03 
*/
public class TrainPersonalAchievement {

	private Integer id;

	private Integer objectId;// 1综合体能2枪械

	private String objectType;

	private Integer type;// (训练项目归属)1单位2分局

	private String name;
	
	private String projectName;
	
	private Integer rankId;// 个人排名
	
	private Integer depRankId;// 单位排名

	private int passNum;// 合格数量
	
	private int failNum;// 不合格数量
	
	private Integer sort;
	
	private Integer nature;

	private Date trainStartDate;

	private Date trainEndDate;

	private String achievementName;
	
	private String strTime;

	private Date creationDate;
	
	private List<TrainPhysicalAchievementDetails> projectList;

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
	 * @return the objectType
	 */
	public String getObjectType() {
		return objectType;
	}

	/**
	 * @param objectType the objectType to set
	 */
	public void setObjectType(String objectType) {
		this.objectType = objectType;
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
	 * @return the achievementName
	 */
	public String getAchievementName() {
		return achievementName;
	}

	/**
	 * @param achievementName the achievementName to set
	 */
	public void setAchievementName(String achievementName) {
		this.achievementName = achievementName;
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
	 * @return the strTime
	 */
	public String getStrTime() {
		return strTime;
	}

	/**
	 * @param strTime the strTime to set
	 */
	public void setStrTime(String strTime) {
		this.strTime = strTime;
	}

	/**
	 * @return the projectList
	 */
	public List<TrainPhysicalAchievementDetails> getProjectList() {
		return projectList;
	}

	/**
	 * @param projectList the projectList to set
	 */
	public void setProjectList(List<TrainPhysicalAchievementDetails> projectList) {
		this.projectList = projectList;
	}

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
	 * @return the passNum
	 */
	public int getPassNum() {
		return passNum;
	}

	/**
	 * @param passNum the passNum to set
	 */
	public void setPassNum(int passNum) {
		this.passNum = passNum;
	}

	/**
	 * @return the failNum
	 */
	public int getFailNum() {
		return failNum;
	}

	/**
	 * @param failNum the failNum to set
	 */
	public void setFailNum(int failNum) {
		this.failNum = failNum;
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
	 * @return the depRankId
	 */
	public Integer getDepRankId() {
		return depRankId;
	}

	/**
	 * @param depRankId the depRankId to set
	 */
	public void setDepRankId(Integer depRankId) {
		this.depRankId = depRankId;
	}
	
}

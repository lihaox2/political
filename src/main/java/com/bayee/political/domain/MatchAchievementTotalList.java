package com.bayee.political.domain;

import java.util.Date;

/** 
* @author  shentuqiwei 
* @version 2021年1月13日 上午11:08:00 
*/
public class MatchAchievementTotalList {

	private Integer id;

	private Integer objectId;// 1综合体能2枪械

	private String objectType;

	private Integer type;// (训练项目归属)1单位2分局
	
	private Integer nature;
	
	private String natureStr;

	private Integer departmentId;

	private String name;

	private String policeId;
	
	private Integer rankId;
	
	private String rankStr;
	
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
	 * @return the rankStr
	 */
	public String getRankStr() {
		return rankStr;
	}

	/**
	 * @param rankStr the rankStr to set
	 */
	public void setRankStr(String rankStr) {
		this.rankStr = rankStr;
	}

	/**
	 * @return the natureStr
	 */
	public String getNatureStr() {
		return natureStr;
	}

	/**
	 * @param natureStr the natureStr to set
	 */
	public void setNatureStr(String natureStr) {
		this.natureStr = natureStr;
	}
	
}

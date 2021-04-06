package com.bayee.political.domain;

import java.util.Date;

/** 
* @author  shentuqiwei 
* @version 2020年10月26日 下午4:36:31 
*/
public class MatchRecordScore {

    private Integer id;
    
    private Integer type;
	
	private String name;
	
	private Double proportion;
	
	private String statusName;
	
	private Integer isMoreUnit;
	
	private Integer sort;
	
	private Date matchStartDate;

	private Date matchEndDate;
	
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
	 * @return the proportion
	 */
	public Double getProportion() {
		return proportion;
	}

	/**
	 * @param proportion the proportion to set
	 */
	public void setProportion(Double proportion) {
		this.proportion = proportion;
	}

	/**
	 * @return the statusName
	 */
	public String getStatusName() {
		return statusName;
	}

	/**
	 * @param statusName the statusName to set
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	/**
	 * @return the matchStartDate
	 */
	public Date getMatchStartDate() {
		return matchStartDate;
	}

	/**
	 * @param matchStartDate the matchStartDate to set
	 */
	public void setMatchStartDate(Date matchStartDate) {
		this.matchStartDate = matchStartDate;
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
	
}

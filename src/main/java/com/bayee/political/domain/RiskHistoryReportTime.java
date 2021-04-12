package com.bayee.political.domain;

import java.util.List;

/** 
* @author  shentuqiwei 
* @version 2021年4月8日 下午6:10:51 
*/
public class RiskHistoryReportTime {

	private Integer id;
	
	private String year;
	
	private Integer timeType;
	
	private List<RiskHistoryReport> monthList;

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
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the timeType
	 */
	public Integer getTimeType() {
		return timeType;
	}

	/**
	 * @param timeType the timeType to set
	 */
	public void setTimeType(Integer timeType) {
		this.timeType = timeType;
	}

	/**
	 * @return the monthList
	 */
	public List<RiskHistoryReport> getMonthList() {
		return monthList;
	}

	/**
	 * @param monthList the monthList to set
	 */
	public void setMonthList(List<RiskHistoryReport> monthList) {
		this.monthList = monthList;
	}
	
}

package com.bayee.political.domain;
/** 
* @author  shentuqiwei 
* @version 2020年8月5日 下午7:08:35 
*/

import java.util.List;

public class AlarmLastScoreList {

	private String monthName;
	
	private List<LeaveChart> scoreList;

	/**
	 * @return the monthName
	 */
	public String getMonthName() {
		return monthName;
	}

	/**
	 * @param monthName the monthName to set
	 */
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	/**
	 * @return the scoreList
	 */
	public List<LeaveChart> getScoreList() {
		return scoreList;
	}

	/**
	 * @param scoreList the scoreList to set
	 */
	public void setScoreList(List<LeaveChart> scoreList) {
		this.scoreList = scoreList;
	}
	
	
}

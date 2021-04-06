package com.bayee.political.domain;
/** 
* @author  shentuqiwei 
* @version 2021年1月25日 下午6:00:31 
*/

import java.util.List;

public class MatchLeaderApplicant {

	private int totalNum;
	
	private String departmentName;
	
	List<TrainMatchAchievement> list;

	/**
	 * @return the totalNum
	 */
	public int getTotalNum() {
		return totalNum;
	}

	/**
	 * @param totalNum the totalNum to set
	 */
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
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
	 * @return the list
	 */
	public List<TrainMatchAchievement> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<TrainMatchAchievement> list) {
		this.list = list;
	}
	
}

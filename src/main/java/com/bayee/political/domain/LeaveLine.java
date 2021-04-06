package com.bayee.political.domain;

import java.util.List;

/** 
* @author  shentuqiwei 
* @version 2020年7月17日 上午11:02:30 
*/
public class LeaveLine {

	private List<LeaveChart> overtimeList;// 加班

	private List<LeaveChart> dutyList;// 值班

	/**
	 * @return the overtimeList
	 */
	public List<LeaveChart> getOvertimeList() {
		return overtimeList;
	}

	/**
	 * @param overtimeList the overtimeList to set
	 */
	public void setOvertimeList(List<LeaveChart> overtimeList) {
		this.overtimeList = overtimeList;
	}

	/**
	 * @return the dutyList
	 */
	public List<LeaveChart> getDutyList() {
		return dutyList;
	}

	/**
	 * @param dutyList the dutyList to set
	 */
	public void setDutyList(List<LeaveChart> dutyList) {
		this.dutyList = dutyList;
	}

}

package com.bayee.political.domain;

import java.util.List;

/** 
* @author  shentuqiwei 
* @version 2021年3月19日 上午11:01:44 
*/
public class ScreenLeaveVacationChart {

	private List<ScreenChart> leavetotalNumList;// 每月请假总人数
	private List<ScreenChart> leaveAnnualNumList;// 每月休年假人数
	private List<ScreenChart> leaveOffNumList;// 每月病假人数
	private List<ScreenChart> leaveNumList;// 每月事假人数
	/**
	 * @return the leavetotalNumList
	 */
	public List<ScreenChart> getLeavetotalNumList() {
		return leavetotalNumList;
	}
	/**
	 * @param leavetotalNumList the leavetotalNumList to set
	 */
	public void setLeavetotalNumList(List<ScreenChart> leavetotalNumList) {
		this.leavetotalNumList = leavetotalNumList;
	}
	/**
	 * @return the leaveAnnualNumList
	 */
	public List<ScreenChart> getLeaveAnnualNumList() {
		return leaveAnnualNumList;
	}
	/**
	 * @param leaveAnnualNumList the leaveAnnualNumList to set
	 */
	public void setLeaveAnnualNumList(List<ScreenChart> leaveAnnualNumList) {
		this.leaveAnnualNumList = leaveAnnualNumList;
	}
	/**
	 * @return the leaveOffNumList
	 */
	public List<ScreenChart> getLeaveOffNumList() {
		return leaveOffNumList;
	}
	/**
	 * @param leaveOffNumList the leaveOffNumList to set
	 */
	public void setLeaveOffNumList(List<ScreenChart> leaveOffNumList) {
		this.leaveOffNumList = leaveOffNumList;
	}
	/**
	 * @return the leaveNumList
	 */
	public List<ScreenChart> getLeaveNumList() {
		return leaveNumList;
	}
	/**
	 * @param leaveNumList the leaveNumList to set
	 */
	public void setLeaveNumList(List<ScreenChart> leaveNumList) {
		this.leaveNumList = leaveNumList;
	}
	
	
}

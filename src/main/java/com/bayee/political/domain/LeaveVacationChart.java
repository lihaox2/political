package com.bayee.political.domain;

import java.util.List;

/**
 * @author shentuqiwei
 * @version 2020年7月17日 下午5:22:02
 */
public class LeaveVacationChart {

	private List<CalculationChart> leavetotalNumList;// 每月请假总人数
	private List<CalculationChart> leaveAnnualNumList;// 每月休年假人数
	private List<CalculationChart> leaveOffNumList;// 每月病假人数
	private List<CalculationChart> leaveNumList;// 每月事假人数

	/**
	 * @return the leavetotalNumList
	 */
	public List<CalculationChart> getLeavetotalNumList() {
		return leavetotalNumList;
	}

	/**
	 * @param leavetotalNumList the leavetotalNumList to set
	 */
	public void setLeavetotalNumList(List<CalculationChart> leavetotalNumList) {
		this.leavetotalNumList = leavetotalNumList;
	}

	/**
	 * @return the leaveAnnualNumList
	 */
	public List<CalculationChart> getLeaveAnnualNumList() {
		return leaveAnnualNumList;
	}

	/**
	 * @param leaveAnnualNumList the leaveAnnualNumList to set
	 */
	public void setLeaveAnnualNumList(List<CalculationChart> leaveAnnualNumList) {
		this.leaveAnnualNumList = leaveAnnualNumList;
	}

	/**
	 * @return the leaveOffNumList
	 */
	public List<CalculationChart> getLeaveOffNumList() {
		return leaveOffNumList;
	}

	/**
	 * @param leaveOffNumList the leaveOffNumList to set
	 */
	public void setLeaveOffNumList(List<CalculationChart> leaveOffNumList) {
		this.leaveOffNumList = leaveOffNumList;
	}

	/**
	 * @return the leaveNumList
	 */
	public List<CalculationChart> getLeaveNumList() {
		return leaveNumList;
	}

	/**
	 * @param leaveNumList the leaveNumList to set
	 */
	public void setLeaveNumList(List<CalculationChart> leaveNumList) {
		this.leaveNumList = leaveNumList;
	}

}

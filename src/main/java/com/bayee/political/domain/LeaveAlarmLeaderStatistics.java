package com.bayee.political.domain;

import java.util.List;

/**
 * @author shentuqiwei
 * @version 2020年9月21日 上午11:40:54
 */
public class LeaveAlarmLeaderStatistics {

	private int policeNum;// 民警总数

	private double annualLeaveRate;// 年假率

	private int annualLeaveNum;// 休年假人数

	private double notAnnualLeaveRate;// 未休年假率

	private int notAnnualLeaveNum;// 未休休年假人数
	
	private List<LeaveChart> depList;

	/**
	 * @return the policeNum
	 */
	public int getPoliceNum() {
		return policeNum;
	}

	/**
	 * @param policeNum the policeNum to set
	 */
	public void setPoliceNum(int policeNum) {
		this.policeNum = policeNum;
	}

	/**
	 * @return the annualLeaveRate
	 */
	public double getAnnualLeaveRate() {
		return annualLeaveRate;
	}

	/**
	 * @param annualLeaveRate the annualLeaveRate to set
	 */
	public void setAnnualLeaveRate(double annualLeaveRate) {
		this.annualLeaveRate = annualLeaveRate;
	}

	/**
	 * @return the annualLeaveNum
	 */
	public int getAnnualLeaveNum() {
		return annualLeaveNum;
	}

	/**
	 * @param annualLeaveNum the annualLeaveNum to set
	 */
	public void setAnnualLeaveNum(int annualLeaveNum) {
		this.annualLeaveNum = annualLeaveNum;
	}

	/**
	 * @return the notAnnualLeaveRate
	 */
	public double getNotAnnualLeaveRate() {
		return notAnnualLeaveRate;
	}

	/**
	 * @param notAnnualLeaveRate the notAnnualLeaveRate to set
	 */
	public void setNotAnnualLeaveRate(double notAnnualLeaveRate) {
		this.notAnnualLeaveRate = notAnnualLeaveRate;
	}

	/**
	 * @return the notAnnualLeaveNum
	 */
	public int getNotAnnualLeaveNum() {
		return notAnnualLeaveNum;
	}

	/**
	 * @param notAnnualLeaveNum the notAnnualLeaveNum to set
	 */
	public void setNotAnnualLeaveNum(int notAnnualLeaveNum) {
		this.notAnnualLeaveNum = notAnnualLeaveNum;
	}

	/**
	 * @return the depList
	 */
	public List<LeaveChart> getDepList() {
		return depList;
	}

	/**
	 * @param depList the depList to set
	 */
	public void setDepList(List<LeaveChart> depList) {
		this.depList = depList;
	}
	
}

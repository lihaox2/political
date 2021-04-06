package com.bayee.political.domain;

import java.util.List;

/** 
* @author  shentuqiwei 
* @version 2021年3月19日 上午11:27:53 
*/
public class SreenLeaveOverDutyStatistics {

private int policeNum;// 民警总数
	
	private double overTime;// 加班时长
	
	private double overTimeAve;// 平均每人加班时长
	
	private double dutyTime;// 值班时长
	
	private double dutyTimeAve;// 平均每人值班时长
	
	private List<ScreenDoubeChart> overtimeList;// 加班

	private List<ScreenDoubeChart> dutyList;// 值班

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
	 * @return the overTime
	 */
	public double getOverTime() {
		return overTime;
	}

	/**
	 * @param overTime the overTime to set
	 */
	public void setOverTime(double overTime) {
		this.overTime = overTime;
	}

	/**
	 * @return the overTimeAve
	 */
	public double getOverTimeAve() {
		return overTimeAve;
	}

	/**
	 * @param overTimeAve the overTimeAve to set
	 */
	public void setOverTimeAve(double overTimeAve) {
		this.overTimeAve = overTimeAve;
	}

	/**
	 * @return the dutyTime
	 */
	public double getDutyTime() {
		return dutyTime;
	}

	/**
	 * @param dutyTime the dutyTime to set
	 */
	public void setDutyTime(double dutyTime) {
		this.dutyTime = dutyTime;
	}

	/**
	 * @return the dutyTimeAve
	 */
	public double getDutyTimeAve() {
		return dutyTimeAve;
	}

	/**
	 * @param dutyTimeAve the dutyTimeAve to set
	 */
	public void setDutyTimeAve(double dutyTimeAve) {
		this.dutyTimeAve = dutyTimeAve;
	}

	/**
	 * @return the overtimeList
	 */
	public List<ScreenDoubeChart> getOvertimeList() {
		return overtimeList;
	}

	/**
	 * @param overtimeList the overtimeList to set
	 */
	public void setOvertimeList(List<ScreenDoubeChart> overtimeList) {
		this.overtimeList = overtimeList;
	}

	/**
	 * @return the dutyList
	 */
	public List<ScreenDoubeChart> getDutyList() {
		return dutyList;
	}

	/**
	 * @param dutyList the dutyList to set
	 */
	public void setDutyList(List<ScreenDoubeChart> dutyList) {
		this.dutyList = dutyList;
	}
	
}

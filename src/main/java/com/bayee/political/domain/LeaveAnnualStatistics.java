package com.bayee.political.domain;

/**
 * @author shentuqiwei
 * @version 2020年7月21日 上午10:01:02
 */
public class LeaveAnnualStatistics {

	private Integer policeNum;// 民警数量

	private double averageNum;// 年假平均值

	private Integer onHolidayNum;// 休假中人员数量

	private Integer askingForLeaveNum;// 请假中人员数量

	/**
	 * @return the policeNum
	 */
	public Integer getPoliceNum() {
		return policeNum;
	}

	/**
	 * @param policeNum the policeNum to set
	 */
	public void setPoliceNum(Integer policeNum) {
		this.policeNum = policeNum;
	}

	/**
	 * @return the averageNum
	 */
	public double getAverageNum() {
		return averageNum;
	}

	/**
	 * @param averageNum the averageNum to set
	 */
	public void setAverageNum(double averageNum) {
		this.averageNum = averageNum;
	}

	/**
	 * @return the onHolidayNum
	 */
	public Integer getOnHolidayNum() {
		return onHolidayNum;
	}

	/**
	 * @param onHolidayNum the onHolidayNum to set
	 */
	public void setOnHolidayNum(Integer onHolidayNum) {
		this.onHolidayNum = onHolidayNum;
	}

	/**
	 * @return the askingForLeaveNum
	 */
	public Integer getAskingForLeaveNum() {
		return askingForLeaveNum;
	}

	/**
	 * @param askingForLeaveNum the askingForLeaveNum to set
	 */
	public void setAskingForLeaveNum(Integer askingForLeaveNum) {
		this.askingForLeaveNum = askingForLeaveNum;
	}
	
	
}

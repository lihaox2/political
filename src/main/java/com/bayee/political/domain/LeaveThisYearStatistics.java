package com.bayee.political.domain;
/** 
* @author  shentuqiwei 
* @version 2020年11月24日 下午1:53:07 
*/
public class LeaveThisYearStatistics {

	private int overtimeNum;// 累计加班次数
	private double overtimeHours;// 累计加班时长
	private double aveOvertimeHours;// 平均加班时长
	private int businessTravelNum;// 累计出差次数
	private double businessTravelDays;// 累计出差天数
	private double aveBusinessTravelDays;// 平均出差天数
	private double totalPoints;// 累计总积分
	private double residualPoints;// 办案剩余积分
	private double pointsChangeDays;// 积分可调换天数
	private int leaveNum;// 累计请假次数
	private double leaveDays;// 累计请假天数
	/**
	 * @return the overtimeNum
	 */
	public int getOvertimeNum() {
		return overtimeNum;
	}
	/**
	 * @param overtimeNum the overtimeNum to set
	 */
	public void setOvertimeNum(int overtimeNum) {
		this.overtimeNum = overtimeNum;
	}
	/**
	 * @return the overtimeHours
	 */
	public double getOvertimeHours() {
		return overtimeHours;
	}
	/**
	 * @param overtimeHours the overtimeHours to set
	 */
	public void setOvertimeHours(double overtimeHours) {
		this.overtimeHours = overtimeHours;
	}
	/**
	 * @return the aveOvertimeHours
	 */
	public double getAveOvertimeHours() {
		return aveOvertimeHours;
	}
	/**
	 * @param aveOvertimeHours the aveOvertimeHours to set
	 */
	public void setAveOvertimeHours(double aveOvertimeHours) {
		this.aveOvertimeHours = aveOvertimeHours;
	}
	/**
	 * @return the businessTravelNum
	 */
	public int getBusinessTravelNum() {
		return businessTravelNum;
	}
	/**
	 * @param businessTravelNum the businessTravelNum to set
	 */
	public void setBusinessTravelNum(int businessTravelNum) {
		this.businessTravelNum = businessTravelNum;
	}
	/**
	 * @return the businessTravelDays
	 */
	public double getBusinessTravelDays() {
		return businessTravelDays;
	}
	/**
	 * @param businessTravelDays the businessTravelDays to set
	 */
	public void setBusinessTravelDays(double businessTravelDays) {
		this.businessTravelDays = businessTravelDays;
	}
	/**
	 * @return the aveBusinessTravelDays
	 */
	public double getAveBusinessTravelDays() {
		return aveBusinessTravelDays;
	}
	/**
	 * @param aveBusinessTravelDays the aveBusinessTravelDays to set
	 */
	public void setAveBusinessTravelDays(double aveBusinessTravelDays) {
		this.aveBusinessTravelDays = aveBusinessTravelDays;
	}
	/**
	 * @return the totalPoints
	 */
	public double getTotalPoints() {
		return totalPoints;
	}
	/**
	 * @param totalPoints the totalPoints to set
	 */
	public void setTotalPoints(double totalPoints) {
		this.totalPoints = totalPoints;
	}
	/**
	 * @return the residualPoints
	 */
	public double getResidualPoints() {
		return residualPoints;
	}
	/**
	 * @param residualPoints the residualPoints to set
	 */
	public void setResidualPoints(double residualPoints) {
		this.residualPoints = residualPoints;
	}
	/**
	 * @return the pointsChangeDays
	 */
	public double getPointsChangeDays() {
		return pointsChangeDays;
	}
	/**
	 * @param pointsChangeDays the pointsChangeDays to set
	 */
	public void setPointsChangeDays(double pointsChangeDays) {
		this.pointsChangeDays = pointsChangeDays;
	}
	/**
	 * @return the leaveNum
	 */
	public int getLeaveNum() {
		return leaveNum;
	}
	/**
	 * @param leaveNum the leaveNum to set
	 */
	public void setLeaveNum(int leaveNum) {
		this.leaveNum = leaveNum;
	}
	/**
	 * @return the leaveDays
	 */
	public double getLeaveDays() {
		return leaveDays;
	}
	/**
	 * @param leaveDays the leaveDays to set
	 */
	public void setLeaveDays(double leaveDays) {
		this.leaveDays = leaveDays;
	}
	
}

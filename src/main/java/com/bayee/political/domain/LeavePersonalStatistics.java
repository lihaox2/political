/**
 * 
 */
package com.bayee.political.domain;

/**
 * @author shentuqiwei
 *
 */
public class LeavePersonalStatistics {

	private double totalDays;// 总请假(天)
	private double annualLeaveDays;// 年假剩余(天)
	private int annualLeaveNum;// 年假剩余次数
	private double overtimeHours;// 加班时长
	private double residualOvertime;// 剩余加班时长
	private double points;// 办案积分
	private double residualPoints;// 办案剩余积分
	private double overtimeChangeDays;// 加班可调换天数
	private double pointsChangeDays;// 积分可调换天数

	/**
	 * @return the totalDays
	 */
	public double getTotalDays() {
		return totalDays;
	}

	/**
	 * @param totalDays the totalDays to set
	 */
	public void setTotalDays(double totalDays) {
		this.totalDays = totalDays;
	}

	/**
	 * @return the annualLeaveDays
	 */
	public double getAnnualLeaveDays() {
		return annualLeaveDays;
	}

	/**
	 * @param annualLeaveDays the annualLeaveDays to set
	 */
	public void setAnnualLeaveDays(double annualLeaveDays) {
		this.annualLeaveDays = annualLeaveDays;
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
	 * @return the points
	 */
	public double getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(double points) {
		this.points = points;
	}

	/**
	 * @return the residualOvertime
	 */
	public double getResidualOvertime() {
		return residualOvertime;
	}

	/**
	 * @param residualOvertime the residualOvertime to set
	 */
	public void setResidualOvertime(double residualOvertime) {
		this.residualOvertime = residualOvertime;
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
	 * @return the overtimeChangeDays
	 */
	public double getOvertimeChangeDays() {
		return overtimeChangeDays;
	}

	/**
	 * @param overtimeChangeDays the overtimeChangeDays to set
	 */
	public void setOvertimeChangeDays(double overtimeChangeDays) {
		this.overtimeChangeDays = overtimeChangeDays;
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

}

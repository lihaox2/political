package com.bayee.political.domain;

/**
 * @author shentuqiwei
 * @version 2020年11月21日 下午6:52:56 警力在线统计
 */
public class PoliceForceOnlineStatistics {

	private int totalPoliceNum;// 全局民警总数
	private int attendancePoliceNum;// 今日出勤民警总数
	private double attendanceRate;// 今日出勤民警率
	private int onDutyPoliceNum;// 在外执勤民警总数
	private double onDutyRate;// 在外执勤民警率
	private int noAttendancePoliceNum;// 未出勤民警总数
	private double noAttendanceRate;// 未出勤民警率
	private int leaveTodayPoliceNum;// 今日离杭人数
	private int goAbroadTodayPoliceNum;// 今日出境人数
	private int alarmTodayPoliceNum;// 今日预警人数
	private int talkTodayPoliceNum;// 今日谈话人数
	private int dayOffTodayPoliceNum;// 今日调休中人数
	private double dayOffTodayRate;// 今日调休中人数环比率
	private int askForLeaveTodayPoliceNum;// 今日请假中人数
	private double askForLeaveTodayRate;// 今日请假中人数环比率
	private int fallIllTodayPoliceNum;// 今日生病中人数
	private double fallIllTodayRate;// 今日生病中人数环比率

	/**
	 * @return the totalPoliceNum
	 */
	public int getTotalPoliceNum() {
		return totalPoliceNum;
	}

	/**
	 * @param totalPoliceNum the totalPoliceNum to set
	 */
	public void setTotalPoliceNum(int totalPoliceNum) {
		this.totalPoliceNum = totalPoliceNum;
	}

	/**
	 * @return the attendancePoliceNum
	 */
	public int getAttendancePoliceNum() {
		return attendancePoliceNum;
	}

	/**
	 * @param attendancePoliceNum the attendancePoliceNum to set
	 */
	public void setAttendancePoliceNum(int attendancePoliceNum) {
		this.attendancePoliceNum = attendancePoliceNum;
	}

	/**
	 * @return the attendanceRate
	 */
	public double getAttendanceRate() {
		return attendanceRate;
	}

	/**
	 * @param attendanceRate the attendanceRate to set
	 */
	public void setAttendanceRate(double attendanceRate) {
		this.attendanceRate = attendanceRate;
	}

	/**
	 * @return the onDutyPoliceNum
	 */
	public int getOnDutyPoliceNum() {
		return onDutyPoliceNum;
	}

	/**
	 * @param onDutyPoliceNum the onDutyPoliceNum to set
	 */
	public void setOnDutyPoliceNum(int onDutyPoliceNum) {
		this.onDutyPoliceNum = onDutyPoliceNum;
	}

	/**
	 * @return the onDutyRate
	 */
	public double getOnDutyRate() {
		return onDutyRate;
	}

	/**
	 * @param onDutyRate the onDutyRate to set
	 */
	public void setOnDutyRate(double onDutyRate) {
		this.onDutyRate = onDutyRate;
	}

	/**
	 * @return the noAttendancePoliceNum
	 */
	public int getNoAttendancePoliceNum() {
		return noAttendancePoliceNum;
	}

	/**
	 * @param noAttendancePoliceNum the noAttendancePoliceNum to set
	 */
	public void setNoAttendancePoliceNum(int noAttendancePoliceNum) {
		this.noAttendancePoliceNum = noAttendancePoliceNum;
	}

	/**
	 * @return the noAttendanceRate
	 */
	public double getNoAttendanceRate() {
		return noAttendanceRate;
	}

	/**
	 * @param noAttendanceRate the noAttendanceRate to set
	 */
	public void setNoAttendanceRate(double noAttendanceRate) {
		this.noAttendanceRate = noAttendanceRate;
	}

	/**
	 * @return the leaveTodayPoliceNum
	 */
	public int getLeaveTodayPoliceNum() {
		return leaveTodayPoliceNum;
	}

	/**
	 * @param leaveTodayPoliceNum the leaveTodayPoliceNum to set
	 */
	public void setLeaveTodayPoliceNum(int leaveTodayPoliceNum) {
		this.leaveTodayPoliceNum = leaveTodayPoliceNum;
	}

	/**
	 * @return the goAbroadTodayPoliceNum
	 */
	public int getGoAbroadTodayPoliceNum() {
		return goAbroadTodayPoliceNum;
	}

	/**
	 * @param goAbroadTodayPoliceNum the goAbroadTodayPoliceNum to set
	 */
	public void setGoAbroadTodayPoliceNum(int goAbroadTodayPoliceNum) {
		this.goAbroadTodayPoliceNum = goAbroadTodayPoliceNum;
	}

	/**
	 * @return the alarmTodayPoliceNum
	 */
	public int getAlarmTodayPoliceNum() {
		return alarmTodayPoliceNum;
	}

	/**
	 * @param alarmTodayPoliceNum the alarmTodayPoliceNum to set
	 */
	public void setAlarmTodayPoliceNum(int alarmTodayPoliceNum) {
		this.alarmTodayPoliceNum = alarmTodayPoliceNum;
	}

	/**
	 * @return the talkTodayPoliceNum
	 */
	public int getTalkTodayPoliceNum() {
		return talkTodayPoliceNum;
	}

	/**
	 * @param talkTodayPoliceNum the talkTodayPoliceNum to set
	 */
	public void setTalkTodayPoliceNum(int talkTodayPoliceNum) {
		this.talkTodayPoliceNum = talkTodayPoliceNum;
	}

	/**
	 * @return the dayOffTodayPoliceNum
	 */
	public int getDayOffTodayPoliceNum() {
		return dayOffTodayPoliceNum;
	}

	/**
	 * @param dayOffTodayPoliceNum the dayOffTodayPoliceNum to set
	 */
	public void setDayOffTodayPoliceNum(int dayOffTodayPoliceNum) {
		this.dayOffTodayPoliceNum = dayOffTodayPoliceNum;
	}

	/**
	 * @return the dayOffTodayRate
	 */
	public double getDayOffTodayRate() {
		return dayOffTodayRate;
	}

	/**
	 * @param dayOffTodayRate the dayOffTodayRate to set
	 */
	public void setDayOffTodayRate(double dayOffTodayRate) {
		this.dayOffTodayRate = dayOffTodayRate;
	}

	/**
	 * @return the askForLeaveTodayPoliceNum
	 */
	public int getAskForLeaveTodayPoliceNum() {
		return askForLeaveTodayPoliceNum;
	}

	/**
	 * @param askForLeaveTodayPoliceNum the askForLeaveTodayPoliceNum to set
	 */
	public void setAskForLeaveTodayPoliceNum(int askForLeaveTodayPoliceNum) {
		this.askForLeaveTodayPoliceNum = askForLeaveTodayPoliceNum;
	}

	/**
	 * @return the askForLeaveTodayRate
	 */
	public double getAskForLeaveTodayRate() {
		return askForLeaveTodayRate;
	}

	/**
	 * @param askForLeaveTodayRate the askForLeaveTodayRate to set
	 */
	public void setAskForLeaveTodayRate(double askForLeaveTodayRate) {
		this.askForLeaveTodayRate = askForLeaveTodayRate;
	}

	/**
	 * @return the fallIllTodayPoliceNum
	 */
	public int getFallIllTodayPoliceNum() {
		return fallIllTodayPoliceNum;
	}

	/**
	 * @param fallIllTodayPoliceNum the fallIllTodayPoliceNum to set
	 */
	public void setFallIllTodayPoliceNum(int fallIllTodayPoliceNum) {
		this.fallIllTodayPoliceNum = fallIllTodayPoliceNum;
	}

	/**
	 * @return the fallIllTodayRate
	 */
	public double getFallIllTodayRate() {
		return fallIllTodayRate;
	}

	/**
	 * @param fallIllTodayRate the fallIllTodayRate to set
	 */
	public void setFallIllTodayRate(double fallIllTodayRate) {
		this.fallIllTodayRate = fallIllTodayRate;
	}

}

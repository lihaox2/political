package com.bayee.political.domain;

import java.util.List;

/**
 * @author shentuqiwei
 * @version 2021年2月24日 下午9:13:27
 */
public class ScreenAlarmAnalysis {

	private int monthScorePersonNum;// 本月记分人数

	private double monthScorePersonRate;// 记分人数环比

	private int alarmScorePersonNum;// 本月预警人数

	private double alarmScorePersonRate;// 预警人数环比

	private List<ScreenChart> scoreList; // 记分人数list;
	
	private List<ScreenChart> alarmList; // 预警人数list;

	/**
	 * @return the monthScorePersonNum
	 */
	public int getMonthScorePersonNum() {
		return monthScorePersonNum;
	}

	/**
	 * @param monthScorePersonNum the monthScorePersonNum to set
	 */
	public void setMonthScorePersonNum(int monthScorePersonNum) {
		this.monthScorePersonNum = monthScorePersonNum;
	}

	/**
	 * @return the monthScorePersonRate
	 */
	public double getMonthScorePersonRate() {
		return monthScorePersonRate;
	}

	/**
	 * @param monthScorePersonRate the monthScorePersonRate to set
	 */
	public void setMonthScorePersonRate(double monthScorePersonRate) {
		this.monthScorePersonRate = monthScorePersonRate;
	}

	/**
	 * @return the alarmScorePersonNum
	 */
	public int getAlarmScorePersonNum() {
		return alarmScorePersonNum;
	}

	/**
	 * @param alarmScorePersonNum the alarmScorePersonNum to set
	 */
	public void setAlarmScorePersonNum(int alarmScorePersonNum) {
		this.alarmScorePersonNum = alarmScorePersonNum;
	}

	/**
	 * @return the alarmScorePersonRate
	 */
	public double getAlarmScorePersonRate() {
		return alarmScorePersonRate;
	}

	/**
	 * @param alarmScorePersonRate the alarmScorePersonRate to set
	 */
	public void setAlarmScorePersonRate(double alarmScorePersonRate) {
		this.alarmScorePersonRate = alarmScorePersonRate;
	}

	/**
	 * @return the scoreList
	 */
	public List<ScreenChart> getScoreList() {
		return scoreList;
	}

	/**
	 * @param scoreList the scoreList to set
	 */
	public void setScoreList(List<ScreenChart> scoreList) {
		this.scoreList = scoreList;
	}

	/**
	 * @return the alarmList
	 */
	public List<ScreenChart> getAlarmList() {
		return alarmList;
	}

	/**
	 * @param alarmList the alarmList to set
	 */
	public void setAlarmList(List<ScreenChart> alarmList) {
		this.alarmList = alarmList;
	}
	
}

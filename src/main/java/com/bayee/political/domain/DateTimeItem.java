package com.bayee.political.domain;

/**
 * @author shentuqiwei
 * @version 2020年8月11日 下午1:56:10
 */
public class DateTimeItem {

	private String timesMonthMorning;// 获取当月0点时间

	private String timesMonthNight;// 获取当月23点59分时间

	private String currentQuarterStartTime;// 获取当季0点时间

	private String currentQuarterEndTime;// 获取当季23点59分时间

	/**
	 * @return the timesMonthNight
	 */
	public String getTimesMonthNight() {
		return timesMonthNight;
	}

	/**
	 * @param timesMonthNight the timesMonthNight to set
	 */
	public void setTimesMonthNight(String timesMonthNight) {
		this.timesMonthNight = timesMonthNight;
	}

	/**
	 * @return the timesMonthMorning
	 */
	public String getTimesMonthMorning() {
		return timesMonthMorning;
	}

	/**
	 * @param timesMonthMorning the timesMonthMorning to set
	 */
	public void setTimesMonthMorning(String timesMonthMorning) {
		this.timesMonthMorning = timesMonthMorning;
	}

	/**
	 * @return the currentQuarterStartTime
	 */
	public String getCurrentQuarterStartTime() {
		return currentQuarterStartTime;
	}

	/**
	 * @param currentQuarterStartTime the currentQuarterStartTime to set
	 */
	public void setCurrentQuarterStartTime(String currentQuarterStartTime) {
		this.currentQuarterStartTime = currentQuarterStartTime;
	}

	/**
	 * @return the currentQuarterEndTime
	 */
	public String getCurrentQuarterEndTime() {
		return currentQuarterEndTime;
	}

	/**
	 * @param currentQuarterEndTime the currentQuarterEndTime to set
	 */
	public void setCurrentQuarterEndTime(String currentQuarterEndTime) {
		this.currentQuarterEndTime = currentQuarterEndTime;
	}

}

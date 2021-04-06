package com.bayee.political.domain;
/** 
* @author  shentuqiwei 
* @version 2020年8月26日 下午5:33:56 
*/

import java.util.List;

public class AlarmScoreAnalysis {

	private int alarmNum;// 总预警数量

	private String addMonth;// 加分最高月

	private String buckleMonth;// 扣分最高月
	
	private List<LeaveChart> chartlist;

	/**
	 * @return the alarmNum
	 */
	public int getAlarmNum() {
		return alarmNum;
	}

	/**
	 * @param alarmNum the alarmNum to set
	 */
	public void setAlarmNum(int alarmNum) {
		this.alarmNum = alarmNum;
	}

	/**
	 * @return the addMonth
	 */
	public String getAddMonth() {
		return addMonth;
	}

	/**
	 * @param addMonth the addMonth to set
	 */
	public void setAddMonth(String addMonth) {
		this.addMonth = addMonth;
	}

	/**
	 * @return the buckleMonth
	 */
	public String getBuckleMonth() {
		return buckleMonth;
	}

	/**
	 * @param buckleMonth the buckleMonth to set
	 */
	public void setBuckleMonth(String buckleMonth) {
		this.buckleMonth = buckleMonth;
	}

	/**
	 * @return the chartlist
	 */
	public List<LeaveChart> getChartlist() {
		return chartlist;
	}

	/**
	 * @param chartlist the chartlist to set
	 */
	public void setChartlist(List<LeaveChart> chartlist) {
		this.chartlist = chartlist;
	}
	
}

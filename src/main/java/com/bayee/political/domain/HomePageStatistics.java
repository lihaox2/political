package com.bayee.political.domain;

/**
 * @author shentuqiwei
 * @version 2020年8月20日 下午5:59:39
 */
public class HomePageStatistics {

	private int alarmNum;// AI预警数据统计

	private int evaluateNum;// 评价在线数据统计

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
	 * @return the evaluateNum
	 */
	public int getEvaluateNum() {
		return evaluateNum;
	}

	/**
	 * @param evaluateNum the evaluateNum to set
	 */
	public void setEvaluateNum(int evaluateNum) {
		this.evaluateNum = evaluateNum;
	}
	
	
}

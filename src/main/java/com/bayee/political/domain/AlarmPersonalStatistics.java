package com.bayee.political.domain;
/** 
* @author  shentuqiwei 
* @version 2020年7月23日 下午2:45:43 
*/
public class AlarmPersonalStatistics {
	
	private int buckleNum;// 扣分次数
	
	private int addNum;// 加分次数
	
	private int alarmNum;// 预警次数
	
	private int talkNum;// 约谈次数

	/**
	 * @return the buckleNum
	 */
	public int getBuckleNum() {
		return buckleNum;
	}

	/**
	 * @param buckleNum the buckleNum to set
	 */
	public void setBuckleNum(int buckleNum) {
		this.buckleNum = buckleNum;
	}

	/**
	 * @return the addNum
	 */
	public int getAddNum() {
		return addNum;
	}

	/**
	 * @param addNum the addNum to set
	 */
	public void setAddNum(int addNum) {
		this.addNum = addNum;
	}

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
	 * @return the talkNum
	 */
	public int getTalkNum() {
		return talkNum;
	}

	/**
	 * @param talkNum the talkNum to set
	 */
	public void setTalkNum(int talkNum) {
		this.talkNum = talkNum;
	}
	
}

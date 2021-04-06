package com.bayee.political.domain;
/** 
* @author  shentuqiwei 
* @version 2020年12月1日 下午12:58:32 
*/

import java.util.List;

public class AlarmPageStatisticsList {

	private int buckleNum;
	
	private int addNum;
	
	private List<AlarmRecordTimeName> timeList;

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
	 * @return the timeList
	 */
	public List<AlarmRecordTimeName> getTimeList() {
		return timeList;
	}

	/**
	 * @param timeList the timeList to set
	 */
	public void setTimeList(List<AlarmRecordTimeName> timeList) {
		this.timeList = timeList;
	}
	
}

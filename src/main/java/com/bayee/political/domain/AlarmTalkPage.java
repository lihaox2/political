package com.bayee.political.domain;

import java.util.List;

/**
 * @author shentuqiwei
 * @version 2020年8月12日 下午12:11:31
 */
public class AlarmTalkPage {

	private int totalNum;

	private List<TimeName> timeList;

	/**
	 * @return the totalNum
	 */
	public int getTotalNum() {
		return totalNum;
	}

	/**
	 * @param totalNum the totalNum to set
	 */
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	/**
	 * @return the timeList
	 */
	public List<TimeName> getTimeList() {
		return timeList;
	}

	/**
	 * @param timeList the timeList to set
	 */
	public void setTimeList(List<TimeName> timeList) {
		this.timeList = timeList;
	}
	
}

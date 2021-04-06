package com.bayee.political.domain;
/** 
* @author  shentuqiwei 
* @version 2020年8月5日 下午6:12:53 
*/

import java.util.List;

public class AlarmTalkList {

	private int totalNum;
	
	private List<AlarmTalk> talkList;

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
	 * @return the talkList
	 */
	public List<AlarmTalk> getTalkList() {
		return talkList;
	}

	/**
	 * @param talkList the talkList to set
	 */
	public void setTalkList(List<AlarmTalk> talkList) {
		this.talkList = talkList;
	}
	
}

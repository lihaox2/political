package com.bayee.political.domain;
/** 
* @author  shentuqiwei 
* @version 2020年8月12日 下午2:39:07 
*/

import java.util.List;

public class TimeName {

	private String name;
	
	private List<AlarmTalk> talkList;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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

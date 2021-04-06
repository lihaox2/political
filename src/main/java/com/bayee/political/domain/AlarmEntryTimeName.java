package com.bayee.political.domain;

import java.util.List;

/** 
* @author  shentuqiwei 
* @version 2020年12月1日 下午3:06:53 
*/
public class AlarmEntryTimeName {

	private String name;

	private List<AlarmEntryAndExitRecord> recordList;

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
	 * @return the recordList
	 */
	public List<AlarmEntryAndExitRecord> getRecordList() {
		return recordList;
	}

	/**
	 * @param recordList the recordList to set
	 */
	public void setRecordList(List<AlarmEntryAndExitRecord> recordList) {
		this.recordList = recordList;
	}
	
}

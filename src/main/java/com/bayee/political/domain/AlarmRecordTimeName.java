package com.bayee.political.domain;

import java.util.List;

/**
 * @author shentuqiwei
 * @version 2020年12月1日 下午12:38:49
 */
public class AlarmRecordTimeName {

	private String name;

	private List<AlarmRecord> recordList;

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
	public List<AlarmRecord> getRecordList() {
		return recordList;
	}

	/**
	 * @param recordList the recordList to set
	 */
	public void setRecordList(List<AlarmRecord> recordList) {
		this.recordList = recordList;
	}
	
}

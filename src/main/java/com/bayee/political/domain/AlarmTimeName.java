package com.bayee.political.domain;

import java.util.List;

/**
 * @author shentuqiwei
 * @version 2020年11月26日 下午4:06:05
 */
public class AlarmTimeName {

	private String name;

	private List<AlarmUrgeRecord> urgeList;

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
	 * @return the urgeList
	 */
	public List<AlarmUrgeRecord> getUrgeList() {
		return urgeList;
	}

	/**
	 * @param urgeList the urgeList to set
	 */
	public void setUrgeList(List<AlarmUrgeRecord> urgeList) {
		this.urgeList = urgeList;
	}

}

package com.bayee.political.domain;

import java.util.List;

/** 
* @author  shentuqiwei 
* @version 2020年11月26日 下午2:45:23 
*/
public class AlarmPassportNoticeStatistics {

	private int isRead;// 是否还有未读（0没有未读1有未读）

	List<AlarmUrgeRecord> list;

	/**
	 * @return the isRead
	 */
	public int getIsRead() {
		return isRead;
	}

	/**
	 * @param isRead the isRead to set
	 */
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	/**
	 * @return the list
	 */
	public List<AlarmUrgeRecord> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<AlarmUrgeRecord> list) {
		this.list = list;
	}
	
}

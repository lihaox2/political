package com.bayee.political.domain;

import java.util.List;

/**
 * @author shentuqiwei
 * @version 2020年11月24日 下午4:35:32 个人最新强制提醒
 */
public class LeaveCompensatoryRecordStatistics {

	private int isRead;// 强制提醒是否还有未读（0没有未读1有未读）

	List<LeaveCompensatoryRecord> list;
	
	List<LeaveCompensatoryReadRecord> leaderList;

	/**
	 * @return the isRead
	 */
	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	/**
	 * @return the list
	 */
	public List<LeaveCompensatoryRecord> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<LeaveCompensatoryRecord> list) {
		this.list = list;
	}

	/**
	 * @return the leaderList
	 */
	public List<LeaveCompensatoryReadRecord> getLeaderList() {
		return leaderList;
	}

	/**
	 * @param leaderList the leaderList to set
	 */
	public void setLeaderList(List<LeaveCompensatoryReadRecord> leaderList) {
		this.leaderList = leaderList;
	}
	
}

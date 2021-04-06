package com.bayee.political.domain;
/** 
* @author  shentuqiwei 
* @version 2020年7月27日 下午3:51:17 
*/

import java.util.List;

public class AlarmLine {

	private List<LeaveChart> addList;// 个人加分(近一年)
	private List<LeaveChart> buckleList;// 个人扣分(近一年)

	/**
	 * @return the addList
	 */
	public List<LeaveChart> getAddList() {
		return addList;
	}

	/**
	 * @param addList the addList to set
	 */
	public void setAddList(List<LeaveChart> addList) {
		this.addList = addList;
	}

	/**
	 * @return the buckleList
	 */
	public List<LeaveChart> getBuckleList() {
		return buckleList;
	}

	/**
	 * @param buckleList the buckleList to set
	 */
	public void setBuckleList(List<LeaveChart> buckleList) {
		this.buckleList = buckleList;
	}

}

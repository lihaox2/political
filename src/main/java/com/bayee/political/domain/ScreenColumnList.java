package com.bayee.political.domain;

import java.util.List;

/** 
* @author  shentuqiwei 
* @version 2021年3月19日 上午11:51:02 
*/
public class ScreenColumnList {

	private List<ScreenChart> actualList;// 实际警力

	private List<ScreenChart> getList;// 所需警力

	/**
	 * @return the actualList
	 */
	public List<ScreenChart> getActualList() {
		return actualList;
	}

	/**
	 * @param actualList the actualList to set
	 */
	public void setActualList(List<ScreenChart> actualList) {
		this.actualList = actualList;
	}

	/**
	 * @return the getList
	 */
	public List<ScreenChart> getGetList() {
		return getList;
	}

	/**
	 * @param getList the getList to set
	 */
	public void setGetList(List<ScreenChart> getList) {
		this.getList = getList;
	}
	
}

package com.bayee.political.domain;
/** 
* @author  shentuqiwei 
* @version 2021年3月26日 下午4:41:09 
*/

import java.util.List;

public class RiskTrainFailChart {

	private List<ScreenChart> physicalFailList;// 综合体能不合格list

	private List<ScreenChart> firearmFailList;// 枪械不合格list

	/**
	 * @return the physicalFailList
	 */
	public List<ScreenChart> getPhysicalFailList() {
		return physicalFailList;
	}

	/**
	 * @param physicalFailList the physicalFailList to set
	 */
	public void setPhysicalFailList(List<ScreenChart> physicalFailList) {
		this.physicalFailList = physicalFailList;
	}

	/**
	 * @return the firearmFailList
	 */
	public List<ScreenChart> getFirearmFailList() {
		return firearmFailList;
	}

	/**
	 * @param firearmFailList the firearmFailList to set
	 */
	public void setFirearmFailList(List<ScreenChart> firearmFailList) {
		this.firearmFailList = firearmFailList;
	}
	
}

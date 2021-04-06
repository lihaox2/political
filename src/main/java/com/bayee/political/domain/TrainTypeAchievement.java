package com.bayee.political.domain;
/** 
* @author  shentuqiwei 
* @version 2021年1月12日 下午5:24:58 
*/
public class TrainTypeAchievement {

	private TrainDepAchievementStatistics depItem;
	
	private TrainDepAchievementStatistics branchOfficeItem;

	/**
	 * @return the depItem
	 */
	public TrainDepAchievementStatistics getDepItem() {
		return depItem;
	}

	/**
	 * @param depItem the depItem to set
	 */
	public void setDepItem(TrainDepAchievementStatistics depItem) {
		this.depItem = depItem;
	}

	/**
	 * @return the branchOfficeItem
	 */
	public TrainDepAchievementStatistics getBranchOfficeItem() {
		return branchOfficeItem;
	}

	/**
	 * @param branchOfficeItem the branchOfficeItem to set
	 */
	public void setBranchOfficeItem(TrainDepAchievementStatistics branchOfficeItem) {
		this.branchOfficeItem = branchOfficeItem;
	}
}

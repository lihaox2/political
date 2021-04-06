package com.bayee.political.domain;

/**
 * @author shentuqiwei
 * @version 2021年1月12日 下午8:23:50
 */
public class MatchTypeAchievement {

	private MatchDepAchievementStatistics depItem;

	private MatchDepAchievementStatistics branchOfficeItem;

	/**
	 * @return the depItem
	 */
	public MatchDepAchievementStatistics getDepItem() {
		return depItem;
	}

	/**
	 * @param depItem the depItem to set
	 */
	public void setDepItem(MatchDepAchievementStatistics depItem) {
		this.depItem = depItem;
	}

	/**
	 * @return the branchOfficeItem
	 */
	public MatchDepAchievementStatistics getBranchOfficeItem() {
		return branchOfficeItem;
	}

	/**
	 * @param branchOfficeItem the branchOfficeItem to set
	 */
	public void setBranchOfficeItem(MatchDepAchievementStatistics branchOfficeItem) {
		this.branchOfficeItem = branchOfficeItem;
	}
	
}

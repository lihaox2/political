package com.bayee.political.domain;

import java.util.List;

/**
 * @author shentuqiwei 实时训练排名
 * @version 2020年10月10日 下午3:05:04
 */
public class TrainRankList {

	private CalculationChart projectItem;// 训练项目名

	private TrainRank rankItem;// 个人排名

	private List<TrainRank> rankList;// 组别排名

	/**
	 * @return the projectItem
	 */
	public CalculationChart getProjectItem() {
		return projectItem;
	}

	/**
	 * @param projectItem the projectItem to set
	 */
	public void setProjectItem(CalculationChart projectItem) {
		this.projectItem = projectItem;
	}

	/**
	 * @return the rankList
	 */
	public List<TrainRank> getRankList() {
		return rankList;
	}

	/**
	 * @param rankList the rankList to set
	 */
	public void setRankList(List<TrainRank> rankList) {
		this.rankList = rankList;
	}

	/**
	 * @return the rankItem
	 */
	public TrainRank getRankItem() {
		return rankItem;
	}

	/**
	 * @param rankItem the rankItem to set
	 */
	public void setRankItem(TrainRank rankItem) {
		this.rankItem = rankItem;
	}

}

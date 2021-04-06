package com.bayee.political.domain;

/**
 * @author shentuqiwei 录分类型数量统计
 * @version 2020年10月13日 下午3:49:12
 */
public class TrainRecordScoreType {

	private int trainNum;// 训练录分数量
	
	private int matchNum;// 比赛录分数量

	/**
	 * @return the trainNum
	 */
	public int getTrainNum() {
		return trainNum;
	}

	/**
	 * @param trainNum the trainNum to set
	 */
	public void setTrainNum(int trainNum) {
		this.trainNum = trainNum;
	}

	/**
	 * @return the matchNum
	 */
	public int getMatchNum() {
		return matchNum;
	}

	/**
	 * @param matchNum the matchNum to set
	 */
	public void setMatchNum(int matchNum) {
		this.matchNum = matchNum;
	}
	
}

package com.bayee.political.domain;

/**
 * @author shentuqiwei
 * @version 2020年6月12日 下午6:23:38
 */
public class ParticipantVote {
	
	private int participantId;//评价参与人id

	private int policeNum;// 评价参与人数量

	private int notNum;// 未投票数量
	
	private int validNum;// 有效票数量

	private int invalidNum;// 无效票
	
	private double score;// 分数

	/**
	 * @return the participantId
	 */
	public int getParticipantId() {
		return participantId;
	}

	/**
	 * @param participantId the participantId to set
	 */
	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}

	/**
	 * @return the policeNum
	 */
	public int getPoliceNum() {
		return policeNum;
	}

	/**
	 * @param policeNum the policeNum to set
	 */
	public void setPoliceNum(int policeNum) {
		this.policeNum = policeNum;
	}

	/**
	 * @return the notNum
	 */
	public int getNotNum() {
		return notNum;
	}

	/**
	 * @param notNum the notNum to set
	 */
	public void setNotNum(int notNum) {
		this.notNum = notNum;
	}

	/**
	 * @return the validNum
	 */
	public int getValidNum() {
		return validNum;
	}

	/**
	 * @param validNum the validNum to set
	 */
	public void setValidNum(int validNum) {
		this.validNum = validNum;
	}

	/**
	 * @return the invalidNum
	 */
	public int getInvalidNum() {
		return invalidNum;
	}

	/**
	 * @param invalidNum the invalidNum to set
	 */
	public void setInvalidNum(int invalidNum) {
		this.invalidNum = invalidNum;
	}

	/**
	 * @return the score
	 */
	public double getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(double score) {
		this.score = score;
	}


}

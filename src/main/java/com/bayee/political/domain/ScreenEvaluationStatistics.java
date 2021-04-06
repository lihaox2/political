package com.bayee.political.domain;

/**
 * @author shentuqiwei
 * @version 2021年2月24日 下午8:04:08
 */
public class ScreenEvaluationStatistics {

	private int evaluateTaskNum;// 评价总数

	private int evaluatePersonNum;// 评价人次总数

	/**
	 * @return the evaluateTaskNum
	 */
	public int getEvaluateTaskNum() {
		return evaluateTaskNum;
	}

	/**
	 * @param evaluateTaskNum the evaluateTaskNum to set
	 */
	public void setEvaluateTaskNum(int evaluateTaskNum) {
		this.evaluateTaskNum = evaluateTaskNum;
	}

	/**
	 * @return the evaluatePersonNum
	 */
	public int getEvaluatePersonNum() {
		return evaluatePersonNum;
	}

	/**
	 * @param evaluatePersonNum the evaluatePersonNum to set
	 */
	public void setEvaluatePersonNum(int evaluatePersonNum) {
		this.evaluatePersonNum = evaluatePersonNum;
	}
	
}

package com.bayee.political.domain;

/**
 * @author shentuqiwei
 * @version 2020年7月24日 下午3:03:07
 */
public class AlarmPersonalEvaluation {

	private int policeMonth; // 公安月份

	private double addScore;// 加分

	private double buckleScore;// 扣分

	/**
	 * @return the policeMonth
	 */
	public int getPoliceMonth() {
		return policeMonth;
	}

	/**
	 * @param policeMonth the policeMonth to set
	 */
	public void setPoliceMonth(int policeMonth) {
		this.policeMonth = policeMonth;
	}

	/**
	 * @return the addScore
	 */
	public double getAddScore() {
		return addScore;
	}

	/**
	 * @param addScore the addScore to set
	 */
	public void setAddScore(double addScore) {
		this.addScore = addScore;
	}

	/**
	 * @return the buckleScore
	 */
	public double getBuckleScore() {
		return buckleScore;
	}

	/**
	 * @param buckleScore the buckleScore to set
	 */
	public void setBuckleScore(double buckleScore) {
		this.buckleScore = buckleScore;
	}
	
}

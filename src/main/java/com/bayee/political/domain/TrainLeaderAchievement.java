package com.bayee.political.domain;

/**
 * @author shentuqiwei
 * @version 2020年10月22日 下午10:03:38
 */
public class TrainLeaderAchievement {

	private double signUpRate;// 签到率
	
	private double goodRate;// 优秀率
	
	private double justRate;// 良好率

	private double passRate;// 合格率

	private double failRate;// 不合格率

	/**
	 * @return the signUpRate
	 */
	public double getSignUpRate() {
		return signUpRate;
	}

	/**
	 * @param signUpRate the signUpRate to set
	 */
	public void setSignUpRate(double signUpRate) {
		this.signUpRate = signUpRate;
	}

	/**
	 * @return the passRate
	 */
	public double getPassRate() {
		return passRate;
	}

	/**
	 * @param passRate the passRate to set
	 */
	public void setPassRate(double passRate) {
		this.passRate = passRate;
	}

	/**
	 * @return the failRate
	 */
	public double getFailRate() {
		return failRate;
	}

	/**
	 * @param failRate the failRate to set
	 */
	public void setFailRate(double failRate) {
		this.failRate = failRate;
	}

	/**
	 * @return the goodRate
	 */
	public double getGoodRate() {
		return goodRate;
	}

	/**
	 * @param goodRate the goodRate to set
	 */
	public void setGoodRate(double goodRate) {
		this.goodRate = goodRate;
	}

	/**
	 * @return the justRate
	 */
	public double getJustRate() {
		return justRate;
	}

	/**
	 * @param justRate the justRate to set
	 */
	public void setJustRate(double justRate) {
		this.justRate = justRate;
	}
	
}

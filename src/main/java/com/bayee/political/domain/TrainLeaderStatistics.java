package com.bayee.political.domain;

import java.util.List;

/** 
* @author  shentuqiwei 
* @version 2020年10月21日 下午2:49:05 
*/
public class TrainLeaderStatistics {

	private int signUpNum;//报名中数量
	
	private int trainNotStartedNum;//训练未开始数量
	
	private int trainOngoingNum;//训练进行中数量
	
	private int trainOverNum;//训练已结束数量
	
	private List<TrainPhysical> trainList;

	/**
	 * @return the signUpNum
	 */
	public int getSignUpNum() {
		return signUpNum;
	}

	/**
	 * @param signUpNum the signUpNum to set
	 */
	public void setSignUpNum(int signUpNum) {
		this.signUpNum = signUpNum;
	}

	/**
	 * @return the trainNotStartedNum
	 */
	public int getTrainNotStartedNum() {
		return trainNotStartedNum;
	}

	/**
	 * @param trainNotStartedNum the trainNotStartedNum to set
	 */
	public void setTrainNotStartedNum(int trainNotStartedNum) {
		this.trainNotStartedNum = trainNotStartedNum;
	}

	/**
	 * @return the trainOngoingNum
	 */
	public int getTrainOngoingNum() {
		return trainOngoingNum;
	}

	/**
	 * @param trainOngoingNum the trainOngoingNum to set
	 */
	public void setTrainOngoingNum(int trainOngoingNum) {
		this.trainOngoingNum = trainOngoingNum;
	}

	/**
	 * @return the trainOverNum
	 */
	public int getTrainOverNum() {
		return trainOverNum;
	}

	/**
	 * @param trainOverNum the trainOverNum to set
	 */
	public void setTrainOverNum(int trainOverNum) {
		this.trainOverNum = trainOverNum;
	}

	/**
	 * @return the trainList
	 */
	public List<TrainPhysical> getTrainList() {
		return trainList;
	}

	/**
	 * @param trainList the trainList to set
	 */
	public void setTrainList(List<TrainPhysical> trainList) {
		this.trainList = trainList;
	}
	
}

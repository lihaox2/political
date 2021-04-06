package com.bayee.political.domain;

import java.util.List;

/**
 * @author shentuqiwei
 * @version 2020年9月28日 下午4:58:25
 */
public class TrainPhysicalStatistics {

	private int depNum;// 单位训练数量

	private int branchOfficeNum;// 分局训练数量
	
	private List<TrainPhysical> trainList;

	/**
	 * @return the depNum
	 */
	public int getDepNum() {
		return depNum;
	}

	/**
	 * @param depNum the depNum to set
	 */
	public void setDepNum(int depNum) {
		this.depNum = depNum;
	}

	/**
	 * @return the branchOfficeNum
	 */
	public int getBranchOfficeNum() {
		return branchOfficeNum;
	}

	/**
	 * @param branchOfficeNum the branchOfficeNum to set
	 */
	public void setBranchOfficeNum(int branchOfficeNum) {
		this.branchOfficeNum = branchOfficeNum;
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

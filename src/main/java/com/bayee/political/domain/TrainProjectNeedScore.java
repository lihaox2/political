package com.bayee.political.domain;

import java.util.List;

/**
 * @author shentuqiwei
 * @version 2020年10月20日 下午5:08:13
 */
public class TrainProjectNeedScore {

	private int enterNum;// 已录项目数

	private int totalEnterNum;// 总项目数
	
	private List<TrainProject> projectList;

	/**
	 * @return the enterNum
	 */
	public int getEnterNum() {
		return enterNum;
	}

	/**
	 * @param enterNum the enterNum to set
	 */
	public void setEnterNum(int enterNum) {
		this.enterNum = enterNum;
	}

	/**
	 * @return the totalEnterNum
	 */
	public int getTotalEnterNum() {
		return totalEnterNum;
	}

	/**
	 * @param totalEnterNum the totalEnterNum to set
	 */
	public void setTotalEnterNum(int totalEnterNum) {
		this.totalEnterNum = totalEnterNum;
	}

	/**
	 * @return the projectList
	 */
	public List<TrainProject> getProjectList() {
		return projectList;
	}

	/**
	 * @param projectList the projectList to set
	 */
	public void setProjectList(List<TrainProject> projectList) {
		this.projectList = projectList;
	}
	
}

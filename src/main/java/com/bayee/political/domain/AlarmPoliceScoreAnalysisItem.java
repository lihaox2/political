package com.bayee.political.domain;

import java.util.List;

/**
 * @author shentuqiwei
 * @version 2020年12月1日 下午4:59:37
 */
public class AlarmPoliceScoreAnalysisItem {

	private int buckleNum;// 扣分次数

	private int addNum;// 加分次数
	
	private List<AlarmPoliceScoreAnalysis> list;

	/**
	 * @return the buckleNum
	 */
	public int getBuckleNum() {
		return buckleNum;
	}

	/**
	 * @param buckleNum the buckleNum to set
	 */
	public void setBuckleNum(int buckleNum) {
		this.buckleNum = buckleNum;
	}

	/**
	 * @return the addNum
	 */
	public int getAddNum() {
		return addNum;
	}

	/**
	 * @param addNum the addNum to set
	 */
	public void setAddNum(int addNum) {
		this.addNum = addNum;
	}

	/**
	 * @return the list
	 */
	public List<AlarmPoliceScoreAnalysis> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<AlarmPoliceScoreAnalysis> list) {
		this.list = list;
	}
	
}

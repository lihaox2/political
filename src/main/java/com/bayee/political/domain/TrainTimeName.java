package com.bayee.political.domain;
/** 
* @author  shentuqiwei 
* @version 2020年10月30日 下午8:48:29 
*/

import java.util.List;

public class TrainTimeName {

	private String name;
	
	private int num;
	
	private List<TrainPersonalAchievement> achievementList;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * @return the achievementList
	 */
	public List<TrainPersonalAchievement> getAchievementList() {
		return achievementList;
	}

	/**
	 * @param achievementList the achievementList to set
	 */
	public void setAchievementList(List<TrainPersonalAchievement> achievementList) {
		this.achievementList = achievementList;
	}
	
}

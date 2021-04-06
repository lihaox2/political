package com.bayee.political.domain;

import java.util.List;

/** 
* @author  shentuqiwei 
* @version 2020年11月16日 下午4:25:04 
*/
public class TrainSignIn {
	
	private Integer objectId;// 1综合体能2枪械
	
	private Integer trainPhysicalId;
	
	private String trainPhysicalName;// 训练名称

	private String policeId;

	private String name;

	private String headImage;
	
	private String groupName;
	
	private List<TrainPhysicalAchievementDetails> projecList;

	/**
	 * @return the objectId
	 */
	public Integer getObjectId() {
		return objectId;
	}

	/**
	 * @param objectId the objectId to set
	 */
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	/**
	 * @return the trainPhysicalId
	 */
	public Integer getTrainPhysicalId() {
		return trainPhysicalId;
	}

	/**
	 * @param trainPhysicalId the trainPhysicalId to set
	 */
	public void setTrainPhysicalId(Integer trainPhysicalId) {
		this.trainPhysicalId = trainPhysicalId;
	}

	/**
	 * @return the trainPhysicalName
	 */
	public String getTrainPhysicalName() {
		return trainPhysicalName;
	}

	/**
	 * @param trainPhysicalName the trainPhysicalName to set
	 */
	public void setTrainPhysicalName(String trainPhysicalName) {
		this.trainPhysicalName = trainPhysicalName;
	}

	/**
	 * @return the policeId
	 */
	public String getPoliceId() {
		return policeId;
	}

	/**
	 * @param policeId the policeId to set
	 */
	public void setPoliceId(String policeId) {
		this.policeId = policeId;
	}

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
	 * @return the headImage
	 */
	public String getHeadImage() {
		return headImage;
	}

	/**
	 * @param headImage the headImage to set
	 */
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	/**
	 * @return the projecList
	 */
	public List<TrainPhysicalAchievementDetails> getProjecList() {
		return projecList;
	}

	/**
	 * @param projecList the projecList to set
	 */
	public void setProjecList(List<TrainPhysicalAchievementDetails> projecList) {
		this.projecList = projecList;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}

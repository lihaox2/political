/**
 * 
 */
package com.bayee.political.domain;

import java.util.Date;

/**
 * @author seanguo
 *
 */
public class UserReward {

	private long id;
	private long userId;
	private String ddUserId;
	private String policeId;
	private String rewardType;
	private String rewardTitle;
	private Date rewardDate;
	private String approvalGroup;
	private String approvalFileNum;
	private Date creationDate;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the ddUserId
	 */
	public String getDdUserId() {
		return ddUserId;
	}

	/**
	 * @param ddUserId the ddUserId to set
	 */
	public void setDdUserId(String ddUserId) {
		this.ddUserId = ddUserId;
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
	 * @return the rewardType
	 */
	public String getRewardType() {
		return rewardType;
	}

	/**
	 * @param rewardType the rewardType to set
	 */
	public void setRewardType(String rewardType) {
		this.rewardType = rewardType;
	}

	/**
	 * @return the rewardTitle
	 */
	public String getRewardTitle() {
		return rewardTitle;
	}

	/**
	 * @param rewardTitle the rewardTitle to set
	 */
	public void setRewardTitle(String rewardTitle) {
		this.rewardTitle = rewardTitle;
	}

	/**
	 * @return the rewardDate
	 */
	public Date getRewardDate() {
		return rewardDate;
	}

	/**
	 * @param rewardDate the rewardDate to set
	 */
	public void setRewardDate(Date rewardDate) {
		this.rewardDate = rewardDate;
	}

	/**
	 * @return the approvalGroup
	 */
	public String getApprovalGroup() {
		return approvalGroup;
	}

	/**
	 * @param approvalGroup the approvalGroup to set
	 */
	public void setApprovalGroup(String approvalGroup) {
		this.approvalGroup = approvalGroup;
	}

	/**
	 * @return the approvalFileNum
	 */
	public String getApprovalFileNum() {
		return approvalFileNum;
	}

	/**
	 * @param approvalFileNum the approvalFileNum to set
	 */
	public void setApprovalFileNum(String approvalFileNum) {
		this.approvalFileNum = approvalFileNum;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}

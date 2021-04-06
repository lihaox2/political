/**
 * 
 */
package com.bayee.political.domain;

import java.util.List;

/**
 * @author seanguo
 *
 */
public class UserLeaveStatisticsProfile {

	private String userId;
	private int totalLeave;  // 总计请假天数
	private int annualLeaveRemain;  // 剩余年假天数
	private int annualLeaveUsed;   // 年假使用次数
	private int redeemLeaveCount;   // 积分可兑换调休天数
	
	private Chart leaveTypePie;  // 请假类型占比
	
	private LeaveProcess latestLeave;   // 最近请假
	private List<LeaveProcess> leaves;   // 全部请假
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the totalLeave
	 */
	public int getTotalLeave() {
		return totalLeave;
	}
	/**
	 * @param totalLeave the totalLeave to set
	 */
	public void setTotalLeave(int totalLeave) {
		this.totalLeave = totalLeave;
	}
	/**
	 * @return the annualLeaveRemain
	 */
	public int getAnnualLeaveRemain() {
		return annualLeaveRemain;
	}
	/**
	 * @param annualLeaveRemain the annualLeaveRemain to set
	 */
	public void setAnnualLeaveRemain(int annualLeaveRemain) {
		this.annualLeaveRemain = annualLeaveRemain;
	}
	/**
	 * @return the annualLeaveUsed
	 */
	public int getAnnualLeaveUsed() {
		return annualLeaveUsed;
	}
	/**
	 * @param annualLeaveUsed the annualLeaveUsed to set
	 */
	public void setAnnualLeaveUsed(int annualLeaveUsed) {
		this.annualLeaveUsed = annualLeaveUsed;
	}
	 
	/**
	 * @return the redeemLeaveCount
	 */
	public int getRedeemLeaveCount() {
		return redeemLeaveCount;
	}
	/**
	 * @param redeemLeaveCount the redeemLeaveCount to set
	 */
	public void setRedeemLeaveCount(int redeemLeaveCount) {
		this.redeemLeaveCount = redeemLeaveCount;
	}
	/**
	 * @return the leaveTypePie
	 */
	public Chart getLeaveTypePie() {
		return leaveTypePie;
	}
	/**
	 * @param leaveTypePie the leaveTypePie to set
	 */
	public void setLeaveTypePie(Chart leaveTypePie) {
		this.leaveTypePie = leaveTypePie;
	}
	/**
	 * @return the latestLeave
	 */
	public LeaveProcess getLatestLeave() {
		return latestLeave;
	}
	/**
	 * @param latestLeave the latestLeave to set
	 */
	public void setLatestLeave(LeaveProcess latestLeave) {
		this.latestLeave = latestLeave;
	}
	/**
	 * @return the leaves
	 */
	public List<LeaveProcess> getLeaves() {
		return leaves;
	}
	/**
	 * @param leaves the leaves to set
	 */
	public void setLeaves(List<LeaveProcess> leaves) {
		this.leaves = leaves;
	}
	
	
}

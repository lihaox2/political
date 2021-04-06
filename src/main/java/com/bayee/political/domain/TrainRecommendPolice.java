package com.bayee.political.domain;

import java.util.Date;

/**
 * @author shentuqiwei
 * @version 2020年10月21日 下午11:08:01
 */
public class TrainRecommendPolice {

	private String policeId;

	private String name;

	private String headImage;

	private Integer gender;

	private Integer qualifiedNum;// 合格次数
	
	private String achievementStr;

	private Integer isRecommend;// 0否1是推荐

	private Date signDate;// 签到时间

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
	 * @return the qualifiedNum
	 */
	public Integer getQualifiedNum() {
		return qualifiedNum;
	}

	/**
	 * @param qualifiedNum the qualifiedNum to set
	 */
	public void setQualifiedNum(Integer qualifiedNum) {
		this.qualifiedNum = qualifiedNum;
	}

	/**
	 * @return the isRecommend
	 */
	public Integer getIsRecommend() {
		return isRecommend;
	}

	/**
	 * @param isRecommend the isRecommend to set
	 */
	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	/**
	 * @return the gender
	 */
	public Integer getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	/**
	 * @return the signDate
	 */
	public Date getSignDate() {
		return signDate;
	}

	/**
	 * @param signDate the signDate to set
	 */
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	/**
	 * @return the achievementStr
	 */
	public String getAchievementStr() {
		return achievementStr;
	}

	/**
	 * @param achievementStr the achievementStr to set
	 */
	public void setAchievementStr(String achievementStr) {
		this.achievementStr = achievementStr;
	}

}

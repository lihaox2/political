package com.bayee.political.domain;

import java.util.Date;

/**
 * @author shentuqiwei
 * @version 2020年7月31日 下午2:40:24 最新预警实体类
 */
public class AlarmNewest {

	private Integer id;

	private String headImage;// 头像

	private String scoredPoliceId;

	private String scoredPoliceName;

	private String departmentName;

	private Double num;

	private Date scoringDate;

	private Long scoringDateNum;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the scoredPoliceId
	 */
	public String getScoredPoliceId() {
		return scoredPoliceId;
	}

	/**
	 * @param scoredPoliceId the scoredPoliceId to set
	 */
	public void setScoredPoliceId(String scoredPoliceId) {
		this.scoredPoliceId = scoredPoliceId;
	}

	/**
	 * @return the scoredPoliceName
	 */
	public String getScoredPoliceName() {
		return scoredPoliceName;
	}

	/**
	 * @param scoredPoliceName the scoredPoliceName to set
	 */
	public void setScoredPoliceName(String scoredPoliceName) {
		this.scoredPoliceName = scoredPoliceName;
	}

	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * @return the num
	 */
	public Double getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(Double num) {
		this.num = num;
	}

	/**
	 * @return the scoringDate
	 */
	public Date getScoringDate() {
		return scoringDate;
	}

	/**
	 * @param scoringDate the scoringDate to set
	 */
	public void setScoringDate(Date scoringDate) {
		this.scoringDate = scoringDate;
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
	 * @return the scoringDateNum
	 */
	public Long getScoringDateNum() {
		return scoringDateNum;
	}

	/**
	 * @param scoringDateNum the scoringDateNum to set
	 */
	public void setScoringDateNum(Long scoringDateNum) {
		this.scoringDateNum = scoringDateNum;
	}

}

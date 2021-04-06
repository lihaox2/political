package com.bayee.political.domain;

/** 
* @author  shentuqiwei 
* @version 2020年8月26日 下午3:58:02 
*/
public class AlarmPoliceScoreAnalysis {

	private Integer id;

	private String headImage;// 头像

	private String scoredPoliceId;

	private String scoredPoliceName;

	private String departmentName;

	private Double score;

	private Double proportion;// 环比

	private Integer identifier;// 标识符(1显示具体数据2显示“--”)

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
	 * @return the score
	 */
	public Double getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(Double score) {
		this.score = score;
	}

	/**
	 * @return the proportion
	 */
	public Double getProportion() {
		return proportion;
	}

	/**
	 * @param proportion the proportion to set
	 */
	public void setProportion(Double proportion) {
		this.proportion = proportion;
	}

	/**
	 * @return the identifier
	 */
	public Integer getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}
	
}

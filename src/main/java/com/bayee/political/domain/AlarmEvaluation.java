package com.bayee.political.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class AlarmEvaluation {

	private Integer id;

	private Integer type;

	private String typeName;

	private Integer scoreItems;
	
	private String itemName;

	private String scoreItemsName;

	private String scoredPoliceId;

	private Integer policeMonthId;

	private Integer scoringBreakdownId;

	private Integer scoringSonBreakdownId;

	private String scoringSonBreakdownName;

	private String clause;

	private Double scoreValue;

	private String scoringPoliceId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date scoringDate;

	private String content;

	private Integer status;

	private Integer isTrue;

	private String reason;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date creationDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updateDate;

	private String scoredName;
	private String policeId;
	private String policeMonthStr;
	private String scoringBreakdown;
	private String scoringSonBreakdown;
	private String scoreingName;
	private String evaluationTarget;

	private String departmentName;
	private Integer departmentId;

	private String creationDateStr;
	private String updateDateStr;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getScoreItems() {
		return scoreItems;
	}

	public void setScoreItems(Integer scoreItems) {
		this.scoreItems = scoreItems;
	}

	public String getScoredPoliceId() {
		return scoredPoliceId;
	}

	public void setScoredPoliceId(String scoredPoliceId) {
		this.scoredPoliceId = scoredPoliceId == null ? null : scoredPoliceId.trim();
	}

	public Integer getPoliceMonthId() {
		return policeMonthId;
	}

	public void setPoliceMonthId(Integer policeMonthId) {
		this.policeMonthId = policeMonthId;
	}

	public Integer getScoringBreakdownId() {
		return scoringBreakdownId;
	}

	public void setScoringBreakdownId(Integer scoringBreakdownId) {
		this.scoringBreakdownId = scoringBreakdownId;
	}

	public Integer getScoringSonBreakdownId() {
		return scoringSonBreakdownId;
	}

	public void setScoringSonBreakdownId(Integer scoringSonBreakdownId) {
		this.scoringSonBreakdownId = scoringSonBreakdownId;
	}

	public Double getScoreValue() {
		return scoreValue;
	}

	public void setScoreValue(Double scoreValue) {
		this.scoreValue = scoreValue;
	}

	public String getScoringPoliceId() {
		return scoringPoliceId;
	}

	public void setScoringPoliceId(String scoringPoliceId) {
		this.scoringPoliceId = scoringPoliceId == null ? null : scoringPoliceId.trim();
	}

	public Date getScoringDate() {
		return scoringDate;
	}

	public void setScoringDate(Date scoringDate) {
		this.scoringDate = scoringDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the scoringSonBreakdownName
	 */
	public String getScoringSonBreakdownName() {
		return scoringSonBreakdownName;
	}

	/**
	 * @param scoringSonBreakdownName the scoringSonBreakdownName to set
	 */
	public void setScoringSonBreakdownName(String scoringSonBreakdownName) {
		this.scoringSonBreakdownName = scoringSonBreakdownName;
	}

	/**
	 * @return the isTrue
	 */
	public Integer getIsTrue() {
		return isTrue;
	}

	/**
	 * @param isTrue the isTrue to set
	 */
	public void setIsTrue(Integer isTrue) {
		this.isTrue = isTrue;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the clause
	 */
	public String getClause() {
		return clause;
	}

	/**
	 * @param clause the clause to set
	 */
	public void setClause(String clause) {
		this.clause = clause;
	}

	public String getScoredName() {
		return scoredName;
	}

	public void setScoredName(String scoredName) {
		this.scoredName = scoredName;
	}

	public String getPoliceId() {
		return policeId;
	}

	public void setPoliceId(String policeId) {
		this.policeId = policeId;
	}

	public String getPoliceMonthStr() {
		return policeMonthStr;
	}

	public void setPoliceMonthStr(String policeMonthStr) {
		this.policeMonthStr = policeMonthStr;
	}

	public String getScoringBreakdown() {
		return scoringBreakdown;
	}

	public void setScoringBreakdown(String scoringBreakdown) {
		this.scoringBreakdown = scoringBreakdown;
	}

	public String getScoringSonBreakdown() {
		return scoringSonBreakdown;
	}

	public void setScoringSonBreakdown(String scoringSonBreakdown) {
		this.scoringSonBreakdown = scoringSonBreakdown;
	}

	public String getScoreingName() {
		return scoreingName;
	}

	public void setScoreingName(String scoreingName) {
		this.scoreingName = scoreingName;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return the scoreItemsName
	 */
	public String getScoreItemsName() {
		return scoreItemsName;
	}

	/**
	 * @param scoreItemsName the scoreItemsName to set
	 */
	public void setScoreItemsName(String scoreItemsName) {
		this.scoreItemsName = scoreItemsName;
	}

	public String getEvaluationTarget() {
		return evaluationTarget;
	}

	public void setEvaluationTarget(String evaluationTarget) {
		this.evaluationTarget = evaluationTarget;
	}

	public String getCreationDateStr() {
		return creationDateStr;
	}

	public void setCreationDateStr(String creationDateStr) {
		this.creationDateStr = creationDateStr;
	}

	public String getUpdateDateStr() {
		return updateDateStr;
	}

	public void setUpdateDateStr(String updateDateStr) {
		this.updateDateStr = updateDateStr;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
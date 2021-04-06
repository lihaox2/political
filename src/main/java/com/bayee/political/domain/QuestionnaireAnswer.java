package com.bayee.political.domain;

import java.util.Date;

public class QuestionnaireAnswer {
	private Integer id;

	private Integer questionnaireId;

	private Integer optionId;

	private String policeId;

	private String optionLabel;// 标签

	private Date creationDate;

	private Date updateDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(Integer questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public Integer getOptionId() {
		return optionId;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}

	public String getPoliceId() {
		return policeId;
	}

	public void setPoliceId(String policeId) {
		this.policeId = policeId == null ? null : policeId.trim();
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
	 * @return the optionLabel
	 */
	public String getOptionLabel() {
		return optionLabel;
	}

	/**
	 * @param optionLabel the optionLabel to set
	 */
	public void setOptionLabel(String optionLabel) {
		this.optionLabel = optionLabel;
	}

}
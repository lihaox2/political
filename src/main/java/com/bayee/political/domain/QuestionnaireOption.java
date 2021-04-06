package com.bayee.political.domain;

import java.util.Date;

public class QuestionnaireOption {
    private Integer id;

    private Integer questionnaireId;

    private String optionName;
    
    private String optionLabel;

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

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName == null ? null : optionName.trim();
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
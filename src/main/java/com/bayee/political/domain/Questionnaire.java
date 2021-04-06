package com.bayee.political.domain;

import java.util.Date;
import java.util.List;

public class Questionnaire {
    private Integer id;

    private String name;

    private Integer isChoose;
    
    private List<QuestionnaireOption> questionnaireOptionList;

    private Date creationDate;

    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }


    /**
	 * @return the isChoose
	 */
	public Integer getIsChoose() {
		return isChoose;
	}

	/**
	 * @param isChoose the isChoose to set
	 */
	public void setIsChoose(Integer isChoose) {
		this.isChoose = isChoose;
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
	 * @return the questionnaireOptionList
	 */
	public List<QuestionnaireOption> getQuestionnaireOptionList() {
		return questionnaireOptionList;
	}

	/**
	 * @param questionnaireOptionList the questionnaireOptionList to set
	 */
	public void setQuestionnaireOptionList(List<QuestionnaireOption> questionnaireOptionList) {
		this.questionnaireOptionList = questionnaireOptionList;
	}
    
}
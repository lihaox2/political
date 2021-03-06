package com.bayee.political.domain;

import java.util.Date;

public class RiskCaseLawEnforcementRecord {
    private Integer id;

    private String policeId;

    private Integer type;

    private String content;

    private Date inputTime;

    private Double deductionScore;

    /**
     * 责任单位
     */
    private String deptName;

    /**
     * 案件编号
     */
    private String caseCode;

    private Date creationDate;

    private Date updateDate;

    /**
     * 文件集合
     */
    private String imgArr;
    
    private String typeName;

    /**
     * 状态 0.可申诉，1.申诉中，2.申诉通过，3.申诉不通过，4.已撤销申诉
     */
    private Integer appealState;

    /**
     * 是否有效（1.有效，2.无效）
     */
    private Integer isEffective;

    public Integer getAppealState() {
        return appealState;
    }

    public void setAppealState(Integer appealState) {
        this.appealState = appealState;
    }

    public Integer getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(Integer isEffective) {
        this.isEffective = isEffective;
    }

    public String getImgArr() {
        return imgArr;
    }

    public void setImgArr(String imgArr) {
        this.imgArr = imgArr;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCaseCode() {
        return caseCode;
    }

    public void setCaseCode(String caseCode) {
        this.caseCode = caseCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId == null ? null : policeId.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public Double getDeductionScore() {
        return deductionScore;
    }

    public void setDeductionScore(Double deductionScore) {
        this.deductionScore = deductionScore;
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
    
}
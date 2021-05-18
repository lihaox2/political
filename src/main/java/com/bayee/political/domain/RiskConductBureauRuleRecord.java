package com.bayee.political.domain;

import java.util.Date;

public class RiskConductBureauRuleRecord {
    private Integer id;

    private String policeId;

    private Integer type;

    private Date inputTime;

    /**
     * 计分层级
     */
    private Integer scoringLevel;

    /**
     * 计分单位
     */
    private Integer scoringDept;

    /**
     * 采取措施
     */
    private Integer measures;

    private String content;

    private Double deductionScore;

    private String remarks;

    private Date creationDate;

    private Date updateDate;

    private String typeName;

    private Integer parentTypeId;

    private String rootName;

    public String getRootName() {
        return rootName;
    }

    public void setRootName(String rootName) {
        this.rootName = rootName;
    }

    public Integer getScoringLevel() {
        return scoringLevel;
    }

    public void setScoringLevel(Integer scoringLevel) {
        this.scoringLevel = scoringLevel;
    }

    public Integer getScoringDept() {
        return scoringDept;
    }

    public void setScoringDept(Integer scoringDept) {
        this.scoringDept = scoringDept;
    }

    public Integer getMeasures() {
        return measures;
    }

    public void setMeasures(Integer measures) {
        this.measures = measures;
    }

    public Integer getParentTypeId() {
        return parentTypeId;
    }

    public void setParentTypeId(Integer parentTypeId) {
        this.parentTypeId = parentTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Double getDeductionScore() {
        return deductionScore;
    }

    public void setDeductionScore(Double deductionScore) {
        this.deductionScore = deductionScore;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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
}
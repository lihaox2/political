package com.bayee.political.pojo.dto;

/**
 * @author xxl
 * @date 2021/5/7
 */
public class ConductBureauRuleDetailsDO {

    /**
     * 警员编号
     */
    private String policeId;

    /**
     * 警员名称
     */
    private String policeName;

    /**
     * 计分层级id
     */
    private Integer scoringLevel;

    /**
     * 计分层级名称
     */
    private String scoringLevelName;

    /**
     *
     */
    private Integer scoringRootType;

    /**
     * 记分类型大类名称
     */
    private String scoringRootTypeName;

    /**
     * 计分单位id
     */
    private Integer scoringDept;

    /**
     * 计分单位名称
     */
    private String scoringDeptName;

    /**
     * 计分类型id
     */
    private Integer scoringType;

    /**
     * 计分类型名称
     */
    private String scoringTypeName;

    /**
     * 计分项id
     */
    private Integer scoringOption;

    /**
     * 计分项名称
     */
    private String scoringOptionName;

    /**
     * 采取措施id
     */
    private Integer measures;

    /**
     * 采取措施名称
     */
    private String measuresName;


    /**
     * 扣除分数
     */
    private Double deductScore;

    /**
     * 时间
     */
    private String date;

    /**
     * 备注
     */
    private String remarks;

    public Integer getScoringRootType() {
        return scoringRootType;
    }

    public void setScoringRootType(Integer scoringRootType) {
        this.scoringRootType = scoringRootType;
    }

    public String getScoringRootTypeName() {
        return scoringRootTypeName;
    }

    public void setScoringRootTypeName(String scoringRootTypeName) {
        this.scoringRootTypeName = scoringRootTypeName;
    }

    public Integer getScoringLevel() {
        return scoringLevel;
    }

    public void setScoringLevel(Integer scoringLevel) {
        this.scoringLevel = scoringLevel;
    }

    public String getScoringLevelName() {
        return scoringLevelName;
    }

    public void setScoringLevelName(String scoringLevelName) {
        this.scoringLevelName = scoringLevelName;
    }

    public Integer getScoringDept() {
        return scoringDept;
    }

    public void setScoringDept(Integer scoringDept) {
        this.scoringDept = scoringDept;
    }

    public String getScoringDeptName() {
        return scoringDeptName;
    }

    public void setScoringDeptName(String scoringDeptName) {
        this.scoringDeptName = scoringDeptName;
    }

    public Integer getScoringType() {
        return scoringType;
    }

    public void setScoringType(Integer scoringType) {
        this.scoringType = scoringType;
    }

    public String getScoringTypeName() {
        return scoringTypeName;
    }

    public void setScoringTypeName(String scoringTypeName) {
        this.scoringTypeName = scoringTypeName;
    }

    public Integer getScoringOption() {
        return scoringOption;
    }

    public void setScoringOption(Integer scoringOption) {
        this.scoringOption = scoringOption;
    }

    public String getScoringOptionName() {
        return scoringOptionName;
    }

    public void setScoringOptionName(String scoringOptionName) {
        this.scoringOptionName = scoringOptionName;
    }

    public Integer getMeasures() {
        return measures;
    }

    public void setMeasures(Integer measures) {
        this.measures = measures;
    }

    public String getMeasuresName() {
        return measuresName;
    }

    public void setMeasuresName(String measuresName) {
        this.measuresName = measuresName;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    public Double getDeductScore() {
        return deductScore;
    }

    public void setDeductScore(Double deductScore) {
        this.deductScore = deductScore;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}

package com.bayee.political.json;

/**
 * 查询id查询人才库响应参数详情
 */
public class TalentsParticularsResult {


    /**
     * 头像
     */
    private String headImage;

    /**
     * 警员类型（1民警，2辅警）
     */
    private Integer policeType;

    /**
     * 职位
     */
    private String position;

    /**
     * 警员姓名
     */
    private String name;

    /**
     * 警号
     */
    private String policeId;

    /**
     * 警员部门名称
     */
    private String policeDeptName;

    /**
     * 学历（1、中专 2、大专 3、本科 4、研究生）
     */
    private Integer degree;

    /**
     * 公安工作年限
     */
    private Integer workEmploymentYear;

    /**
     * 政治面貌
     */
    private String politicalStatus;

    /**
     * 综合积分
     */
    private Double synthesizeScore;

    /**
     * 行为规范
     */
    private Double conductScore;

    /**
     * 接警执勤
     */
    private Double dutyScore;

    /**
     * 社交风险
     */
    private Double socialContactScore;

    /**
     * 家属评价
     */
    private Double familyEvaluationNum;

    /**
     * 执法办案
     */
    private Double enforceScore;

    /**
     * 接警执勤
     */
    private Double performScore;

    /**
     * 警务技能
     */
    private Double skillScore;

    /**
     * 学习培训
     */
    private Double cultivateScore;

    /**
     * 绩效考核
     */
    private Double assessScore;

    /**
     * 警体健康
     */
    private Double healthScore;

    /**
     * 主观评价
     */
    private Double evaluateScore;

    /*@ApiModelProperty(value = "行政处罚")
    private Double administrativeScore;

    @ApiModelProperty(value = "执法办案量")
    private Double enforcementScore;

    @ApiModelProperty(value = "110接处警次数")
    private Double callPoliceScore;

    @ApiModelProperty(value = "刑事打击")
    private Double criminalScore;

    @ApiModelProperty(value = "交通违法处理数")
    private Double trafficViolationScore;

    @ApiModelProperty(value = "交通事故处理数")
    private Double trafficAccidentScore;

    @ApiModelProperty(value = "防范宣传")
    private Double precautionPublicityScore;

    @ApiModelProperty(value = "治安管理")
    private Double securityScore;

    @ApiModelProperty(value = "实有人口管理")
    private Double practicalPopulationScore;*/

    public Double getDutyScore() {
        return dutyScore;
    }

    public void setDutyScore(Double dutyScore) {
        this.dutyScore = dutyScore;
    }

    public Double getSocialContactScore() {
        return socialContactScore;
    }

    public void setSocialContactScore(Double socialContactScore) {
        this.socialContactScore = socialContactScore;
    }

    public Double getFamilyEvaluationNum() {
        return familyEvaluationNum;
    }

    public void setFamilyEvaluationNum(Double familyEvaluationNum) {
        this.familyEvaluationNum = familyEvaluationNum;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public Integer getPoliceType() {
        return policeType;
    }

    public void setPoliceType(Integer policeType) {
        this.policeType = policeType;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public String getPoliceDeptName() {
        return policeDeptName;
    }

    public void setPoliceDeptName(String policeDeptName) {
        this.policeDeptName = policeDeptName;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public Integer getWorkEmploymentYear() {
        return workEmploymentYear;
    }

    public void setWorkEmploymentYear(Integer workEmploymentYear) {
        this.workEmploymentYear = workEmploymentYear;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public Double getSynthesizeScore() {
        return synthesizeScore;
    }

    public void setSynthesizeScore(Double synthesizeScore) {
        this.synthesizeScore = synthesizeScore;
    }

    public Double getConductScore() {
        return conductScore;
    }

    public void setConductScore(Double conductScore) {
        this.conductScore = conductScore;
    }

    public Double getEnforceScore() {
        return enforceScore;
    }

    public void setEnforceScore(Double enforceScore) {
        this.enforceScore = enforceScore;
    }

    public Double getPerformScore() {
        return performScore;
    }

    public void setPerformScore(Double performScore) {
        this.performScore = performScore;
    }

    public Double getSkillScore() {
        return skillScore;
    }

    public void setSkillScore(Double skillScore) {
        this.skillScore = skillScore;
    }

    public Double getCultivateScore() {
        return cultivateScore;
    }

    public void setCultivateScore(Double cultivateScore) {
        this.cultivateScore = cultivateScore;
    }

    public Double getAssessScore() {
        return assessScore;
    }

    public void setAssessScore(Double assessScore) {
        this.assessScore = assessScore;
    }

    public Double getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(Double healthScore) {
        this.healthScore = healthScore;
    }

    public Double getEvaluateScore() {
        return evaluateScore;
    }

    public void setEvaluateScore(Double evaluateScore) {
        this.evaluateScore = evaluateScore;
    }

}

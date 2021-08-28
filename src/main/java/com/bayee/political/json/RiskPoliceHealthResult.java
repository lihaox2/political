package com.bayee.political.json;

import java.util.Date;

/**
 * @author xxl
 * @date 2021/8/14
 */
public class RiskPoliceHealthResult {

    private Integer type = 10;

    /**
     * 身高
     */
    private Double height;

    /**
     * 体重
     */
    private Double weight;

    /**
     * BMI值
     */
    private Double bmiValue;

    /**
     * bmi名称
     */
    private String bmiName;

    /**
     * 体重超标
     */
    private Double overWeightScore;

    /**
     * 高血脂
     */
    private Double hyperlipidemiaScore;

    /**
     * 高血压
     */
    private Double hypertensionScore;

    /**
     * 高血糖
     */
    private Double hyperglycemiaScore;

    /**
     * 高血尿酸
     */
    private Double hyperuricemiaScore;

    /**
     * 前列腺指标
     */
    private Double prostateScore;

    /**
     * 术后重大病史
     */
    private Double majorDiseasesScore;

    /**
     * 心脏指标
     */
    private Double heartScore;

    /**
     * 肿瘤抗原指标
     */
    private Double tumorAntigenScore;

    /**
     * 骨科指标
     */
    private Double orthopaedicsScore;

    /**
     * sort
     */
    private Date date;

    public String getBmiName() {
        return bmiName;
    }

    public void setBmiName(String bmiName) {
        this.bmiName = bmiName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getBmiValue() {
        return bmiValue;
    }

    public void setBmiValue(Double bmiValue) {
        this.bmiValue = bmiValue;
    }

    public Double getOverWeightScore() {
        return overWeightScore;
    }

    public void setOverWeightScore(Double overWeightScore) {
        this.overWeightScore = overWeightScore;
    }

    public Double getHyperlipidemiaScore() {
        return hyperlipidemiaScore;
    }

    public void setHyperlipidemiaScore(Double hyperlipidemiaScore) {
        this.hyperlipidemiaScore = hyperlipidemiaScore;
    }

    public Double getHypertensionScore() {
        return hypertensionScore;
    }

    public void setHypertensionScore(Double hypertensionScore) {
        this.hypertensionScore = hypertensionScore;
    }

    public Double getHyperglycemiaScore() {
        return hyperglycemiaScore;
    }

    public void setHyperglycemiaScore(Double hyperglycemiaScore) {
        this.hyperglycemiaScore = hyperglycemiaScore;
    }

    public Double getHyperuricemiaScore() {
        return hyperuricemiaScore;
    }

    public void setHyperuricemiaScore(Double hyperuricemiaScore) {
        this.hyperuricemiaScore = hyperuricemiaScore;
    }

    public Double getProstateScore() {
        return prostateScore;
    }

    public void setProstateScore(Double prostateScore) {
        this.prostateScore = prostateScore;
    }

    public Double getMajorDiseasesScore() {
        return majorDiseasesScore;
    }

    public void setMajorDiseasesScore(Double majorDiseasesScore) {
        this.majorDiseasesScore = majorDiseasesScore;
    }

    public Double getHeartScore() {
        return heartScore;
    }

    public void setHeartScore(Double heartScore) {
        this.heartScore = heartScore;
    }

    public Double getTumorAntigenScore() {
        return tumorAntigenScore;
    }

    public void setTumorAntigenScore(Double tumorAntigenScore) {
        this.tumorAntigenScore = tumorAntigenScore;
    }

    public Double getOrthopaedicsScore() {
        return orthopaedicsScore;
    }

    public void setOrthopaedicsScore(Double orthopaedicsScore) {
        this.orthopaedicsScore = orthopaedicsScore;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

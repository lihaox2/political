package com.bayee.political.domain;

import java.util.Date;

/**
 * @author xxl
 * @date 2021/5/8
 */
public class RiskHealthRecordInfo {

    private Integer id;

    /**
     * risk_health_record 表 id
     */
    private Integer recordId;

    /**
     * 警员id
     */
    private String policeId;

    /**
     * 身高
     */
    private Double height;

    /**
     * 体重
     */
    private Double weight;

    /**
     * 高密度脂蛋白
     */
    private Double highDensityLipoprotein;

    /**
     * 低密度脂蛋白
     */
    private Double lowDensityLipoprotein;

    /**
     * 甘油三酯
     */
    private Double triglyceride;

    /**
     * 总胆固醇
     */
    private Double cholesterol;

    /**
     * 收压缩
     */
    private Double receiveCompression;

    /**
     * 舒张压
     */
    private Double diastolicPressure;

    /**
     * 血糖
     */
    private Double bloodSugar;

    /**
     * 血尿酸
     */
    private Double serumUricAcid;

    /**
     * 前列腺异常（0否，1是）
     */
    private Integer isProstate;

    /**
     * 前列腺异常描述（0否，1是）
     */
    private String prostateDesc;

    /**
     * 术后或重大病史（0否，1是）
     */
    private Integer isMajorDiseases;

    /**
     * 心脏指标异常（0否，1是）
     */
    private Integer isHeart;

    /**
     * 肿瘤抗原指标增高0否1是）
     */
    private Integer isTumorAntigen;

    /**
     * 骨科指标异常（0否1是
     */
    private Integer isOrthopaedics;

    /**
     * 创建时间
     */
    private Date creationDate;

    /**
     * 修改时间
     */
    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
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

    public Double getHighDensityLipoprotein() {
        return highDensityLipoprotein;
    }

    public void setHighDensityLipoprotein(Double highDensityLipoprotein) {
        this.highDensityLipoprotein = highDensityLipoprotein;
    }

    public Double getLowDensityLipoprotein() {
        return lowDensityLipoprotein;
    }

    public void setLowDensityLipoprotein(Double lowDensityLipoprotein) {
        this.lowDensityLipoprotein = lowDensityLipoprotein;
    }

    public Double getTriglyceride() {
        return triglyceride;
    }

    public void setTriglyceride(Double triglyceride) {
        this.triglyceride = triglyceride;
    }

    public Double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(Double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public Double getReceiveCompression() {
        return receiveCompression;
    }

    public void setReceiveCompression(Double receiveCompression) {
        this.receiveCompression = receiveCompression;
    }

    public Double getDiastolicPressure() {
        return diastolicPressure;
    }

    public void setDiastolicPressure(Double diastolicPressure) {
        this.diastolicPressure = diastolicPressure;
    }

    public Double getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(Double bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public Double getSerumUricAcid() {
        return serumUricAcid;
    }

    public void setSerumUricAcid(Double serumUricAcid) {
        this.serumUricAcid = serumUricAcid;
    }

    public Integer getIsProstate() {
        return isProstate;
    }

    public void setIsProstate(Integer isProstate) {
        this.isProstate = isProstate;
    }

    public String getProstateDesc() {
        return prostateDesc;
    }

    public void setProstateDesc(String prostateDesc) {
        this.prostateDesc = prostateDesc;
    }

    public Integer getIsMajorDiseases() {
        return isMajorDiseases;
    }

    public void setIsMajorDiseases(Integer isMajorDiseases) {
        this.isMajorDiseases = isMajorDiseases;
    }

    public Integer getIsHeart() {
        return isHeart;
    }

    public void setIsHeart(Integer isHeart) {
        this.isHeart = isHeart;
    }

    public Integer getIsTumorAntigen() {
        return isTumorAntigen;
    }

    public void setIsTumorAntigen(Integer isTumorAntigen) {
        this.isTumorAntigen = isTumorAntigen;
    }

    public Integer getIsOrthopaedics() {
        return isOrthopaedics;
    }

    public void setIsOrthopaedics(Integer isOrthopaedics) {
        this.isOrthopaedics = isOrthopaedics;
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

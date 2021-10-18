package com.bayee.political.domain;

import java.util.Date;

/**
 * 警员体检记录
 */
public class RiskHealthRecordInfo {
    /**
     * 警员体检id
     */
    private Integer id;

    /**
     * risk_health_record表id
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
     * 术后或重大病史描述
     */
    private String majorDiseasesDesc;

    /**
     * 心脏指标异常（0否，1是）
     */
    private Integer isHeart;

    /**
     * 心脏指标异常描述
     */
    private String heartDesc;

    /**
     * 肿瘤抗原指标增高0否1是）
     */
    private Integer isTumorAntigen;

    /**
     * 肿瘤指标描述
     */
    private String tumorAntigenDesc;

    /**
     * 骨科指标异常（0否1是）
     */
    private Integer isOrthopaedics;

    /**
     * 骨科指标异常
     */
    private String orthopaedicsDesc;

    /**
     * 其他健康描述
     */
    private String otherHealthDesc;

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
        this.policeId = policeId == null ? null : policeId.trim();
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
        this.prostateDesc = prostateDesc == null ? null : prostateDesc.trim();
    }

    public Integer getIsMajorDiseases() {
        return isMajorDiseases;
    }

    public void setIsMajorDiseases(Integer isMajorDiseases) {
        this.isMajorDiseases = isMajorDiseases;
    }

    public String getMajorDiseasesDesc() {
        return majorDiseasesDesc;
    }

    public void setMajorDiseasesDesc(String majorDiseasesDesc) {
        this.majorDiseasesDesc = majorDiseasesDesc == null ? null : majorDiseasesDesc.trim();
    }

    public Integer getIsHeart() {
        return isHeart;
    }

    public void setIsHeart(Integer isHeart) {
        this.isHeart = isHeart;
    }

    public String getHeartDesc() {
        return heartDesc;
    }

    public void setHeartDesc(String heartDesc) {
        this.heartDesc = heartDesc == null ? null : heartDesc.trim();
    }

    public Integer getIsTumorAntigen() {
        return isTumorAntigen;
    }

    public void setIsTumorAntigen(Integer isTumorAntigen) {
        this.isTumorAntigen = isTumorAntigen;
    }

    public String getTumorAntigenDesc() {
        return tumorAntigenDesc;
    }

    public void setTumorAntigenDesc(String tumorAntigenDesc) {
        this.tumorAntigenDesc = tumorAntigenDesc == null ? null : tumorAntigenDesc.trim();
    }

    public Integer getIsOrthopaedics() {
        return isOrthopaedics;
    }

    public void setIsOrthopaedics(Integer isOrthopaedics) {
        this.isOrthopaedics = isOrthopaedics;
    }

    public String getOrthopaedicsDesc() {
        return orthopaedicsDesc;
    }

    public void setOrthopaedicsDesc(String orthopaedicsDesc) {
        this.orthopaedicsDesc = orthopaedicsDesc == null ? null : orthopaedicsDesc.trim();
    }

    public String getOtherHealthDesc() {
        return otherHealthDesc;
    }

    public void setOtherHealthDesc(String otherHealthDesc) {
        this.otherHealthDesc = otherHealthDesc == null ? null : otherHealthDesc.trim();
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
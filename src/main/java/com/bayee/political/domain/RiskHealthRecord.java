package com.bayee.political.domain;

import java.util.Date;

public class RiskHealthRecord {
    private Integer id;

    private String policeId;

    private String year;

    private Double height;

    private Double weight;

    private Double bmi;

    private Integer bmiId;

    private String blood;

    private Integer isOverweight;

    private Integer isHyperlipidemia;

    private Integer isHypertension;

    private Integer isHyperglycemia;

    private Integer isHyperuricemia;

    private Integer isProstate;

    private Integer isMajorDiseases;

    private String majorDiseasesDescribe;

    private Integer isHeart;

    private String heartDescribe;

    private Integer isTumorAntigen;

    private String tumorAntigenDescribe;

    private Integer isOrthopaedics;

    private String orthopaedicsDescribe;

    private Date creationDate;

    private Date updateDate;

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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
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

    public Double getBmi() {
        return bmi;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    public Integer getBmiId() {
        return bmiId;
    }

    public void setBmiId(Integer bmiId) {
        this.bmiId = bmiId;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood == null ? null : blood.trim();
    }

    public Integer getIsOverweight() {
        return isOverweight;
    }

    public void setIsOverweight(Integer isOverweight) {
        this.isOverweight = isOverweight;
    }

    public Integer getIsHyperlipidemia() {
        return isHyperlipidemia;
    }

    public void setIsHyperlipidemia(Integer isHyperlipidemia) {
        this.isHyperlipidemia = isHyperlipidemia;
    }

    public Integer getIsHypertension() {
        return isHypertension;
    }

    public void setIsHypertension(Integer isHypertension) {
        this.isHypertension = isHypertension;
    }

    public Integer getIsHyperglycemia() {
        return isHyperglycemia;
    }

    public void setIsHyperglycemia(Integer isHyperglycemia) {
        this.isHyperglycemia = isHyperglycemia;
    }

    public Integer getIsHyperuricemia() {
        return isHyperuricemia;
    }

    public void setIsHyperuricemia(Integer isHyperuricemia) {
        this.isHyperuricemia = isHyperuricemia;
    }

    public Integer getIsProstate() {
        return isProstate;
    }

    public void setIsProstate(Integer isProstate) {
        this.isProstate = isProstate;
    }

    public Integer getIsMajorDiseases() {
        return isMajorDiseases;
    }

    public void setIsMajorDiseases(Integer isMajorDiseases) {
        this.isMajorDiseases = isMajorDiseases;
    }

    public String getMajorDiseasesDescribe() {
        return majorDiseasesDescribe;
    }

    public void setMajorDiseasesDescribe(String majorDiseasesDescribe) {
        this.majorDiseasesDescribe = majorDiseasesDescribe == null ? null : majorDiseasesDescribe.trim();
    }

    public Integer getIsHeart() {
        return isHeart;
    }

    public void setIsHeart(Integer isHeart) {
        this.isHeart = isHeart;
    }

    public String getHeartDescribe() {
        return heartDescribe;
    }

    public void setHeartDescribe(String heartDescribe) {
        this.heartDescribe = heartDescribe == null ? null : heartDescribe.trim();
    }

    public Integer getIsTumorAntigen() {
        return isTumorAntigen;
    }

    public void setIsTumorAntigen(Integer isTumorAntigen) {
        this.isTumorAntigen = isTumorAntigen;
    }

    public String getTumorAntigenDescribe() {
        return tumorAntigenDescribe;
    }

    public void setTumorAntigenDescribe(String tumorAntigenDescribe) {
        this.tumorAntigenDescribe = tumorAntigenDescribe == null ? null : tumorAntigenDescribe.trim();
    }

    public Integer getIsOrthopaedics() {
        return isOrthopaedics;
    }

    public void setIsOrthopaedics(Integer isOrthopaedics) {
        this.isOrthopaedics = isOrthopaedics;
    }

    public String getOrthopaedicsDescribe() {
        return orthopaedicsDescribe;
    }

    public void setOrthopaedicsDescribe(String orthopaedicsDescribe) {
        this.orthopaedicsDescribe = orthopaedicsDescribe == null ? null : orthopaedicsDescribe.trim();
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
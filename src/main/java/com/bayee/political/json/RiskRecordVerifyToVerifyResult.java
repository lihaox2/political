package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/7/20
 */
public class RiskRecordVerifyToVerifyResult {

    /**
     * 申诉人名称
     */
    private String name;

    /**
     * 申诉人警号
     */
    private String policeId;

    /**
     * 申诉类型id
     */
    private Integer  appealType;

    /**
     * 申诉类型名称
     */
    private String appealTypeName;

    /**
     * 申诉内容
     */
    private String appealContent;

    /**
     * 申诉分数
     */
    private Double appealScore;

    /**
     * 模型id
     */
    private Integer moduleId;

    /**
     * 申诉时间
     */
    private String appealDate;

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

    public Integer getAppealType() {
        return appealType;
    }

    public void setAppealType(Integer appealType) {
        this.appealType = appealType;
    }

    public String getAppealTypeName() {
        return appealTypeName;
    }

    public void setAppealTypeName(String appealTypeName) {
        this.appealTypeName = appealTypeName;
    }

    public String getAppealContent() {
        return appealContent;
    }

    public void setAppealContent(String appealContent) {
        this.appealContent = appealContent;
    }

    public Double getAppealScore() {
        return appealScore;
    }

    public void setAppealScore(Double appealScore) {
        this.appealScore = appealScore;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getAppealDate() {
        return appealDate;
    }

    public void setAppealDate(String appealDate) {
        this.appealDate = appealDate;
    }
}

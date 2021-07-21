package com.bayee.political.pojo.dto;

/**
 * @author xxl
 * @date 2021/7/20
 */
public class RiskRecordVerifyPageResultDO {

    private Integer id;

    /**
     * 申诉人警号
     */
    private String appealPoliceId;

    /**
     * 申诉人
     */
    private String appealName;

    /**
     * 申诉类型名称
     */
    private String appealTypeName;

    /**
     * 申诉分值
     */
    private Double appealScore;

    /**
     * 状态名称
     */
    private String stateName;

    /**
     * 审核状态
     */
    private Integer state;

    /**
     * 申诉时间
     */
    private String appealDate;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppealPoliceId() {
        return appealPoliceId;
    }

    public void setAppealPoliceId(String appealPoliceId) {
        this.appealPoliceId = appealPoliceId;
    }

    public String getAppealName() {
        return appealName;
    }

    public void setAppealName(String appealName) {
        this.appealName = appealName;
    }

    public String getAppealTypeName() {
        return appealTypeName;
    }

    public void setAppealTypeName(String appealTypeName) {
        this.appealTypeName = appealTypeName;
    }

    public Double getAppealScore() {
        return appealScore;
    }

    public void setAppealScore(Double appealScore) {
        this.appealScore = appealScore;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getAppealDate() {
        return appealDate;
    }

    public void setAppealDate(String appealDate) {
        this.appealDate = appealDate;
    }
}

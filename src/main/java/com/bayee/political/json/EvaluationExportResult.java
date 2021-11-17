package com.bayee.political.json;

/**
 * @author tlt
 * @date 2021/11/16
 */
public class EvaluationExportResult {

    /**
     * 评价人姓名
     */
    private String appraiserName;

    /**
     *评价人账号
     */
    private String appraiserPhone;

    /**
     *评价对象
     */
    private String objectName;

    /**
     *总得分
     */
    private Integer totalScore;

    /**
     *评价时间
     */
    private String businessTime;

    /**
     *序号
     */
    private Integer serialNum;

    /**
     *警号
     */
    private String policeId;

    /**
     *所属年月
     */
    private String belongMonth;

    public String getAppraiserName() {
        return appraiserName;
    }

    public void setAppraiserName(String appraiserName) {
        this.appraiserName = appraiserName;
    }

    public String getAppraiserPhone() {
        return appraiserPhone;
    }

    public void setAppraiserPhone(String appraiserPhone) {
        this.appraiserPhone = appraiserPhone;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    public Integer getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(Integer serialNum) {
        this.serialNum = serialNum;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public String getBelongMonth() {
        return belongMonth;
    }

    public void setBelongMonth(String belongMonth) {
        this.belongMonth = belongMonth;
    }
}

package com.bayee.political.pojo.json;

/**
 * @author xxl
 * @date 2021/5/7
 */
public class DutyDetailsResult {

    /**
     * 警员编号
     */
    private String policeId;

    /**
     * 警员名称
     */
    private String policeName;

    /**
     * 警单编号
     */
    private String policeListCode;

    /**
     * 辖区
     */
    private String jurisdiction;

    /**
     * 警情类别
     */
    private Integer informationId;

    /**
     * 警情类别名称
     */
    private String informationName;

    /**
     * 警单详情
     */
    private String policeListInfo;

    /**
     * 错误类别
     */
    private Integer errorId;

    /**
     * 错误类别名称
     */
    private String errorName;

    /**
     * 是否核实（1 已核实，0 未核实）
     */
    private Integer isVerified;

    /**
     * 问题描述
     */
    private String desc;

    /**
     * 扣除分数
     */
    private Double deductScore;

    /**
     * 时间
     */
    private String date;

    public String getPoliceListCode() {
        return policeListCode;
    }

    public void setPoliceListCode(String policeListCode) {
        this.policeListCode = policeListCode;
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public Integer getInformationId() {
        return informationId;
    }

    public void setInformationId(Integer informationId) {
        this.informationId = informationId;
    }

    public String getInformationName() {
        return informationName;
    }

    public void setInformationName(String informationName) {
        this.informationName = informationName;
    }

    public String getPoliceListInfo() {
        return policeListInfo;
    }

    public void setPoliceListInfo(String policeListInfo) {
        this.policeListInfo = policeListInfo;
    }

    public Integer getErrorId() {
        return errorId;
    }

    public void setErrorId(Integer errorId) {
        this.errorId = errorId;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public Integer getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Integer isVerified) {
        this.isVerified = isVerified;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
}

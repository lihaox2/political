package com.bayee.political.pojo.json;

/**
 * @author xxl
 * @date 2021/5/7
 */
public class DutySaveParam {

    /**
     * 警员id
     */
    private String policeId;

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
     * 警单详情
     */
    private String policeListInfo;

    /**
     * 错误类别
     */
    private Integer errorId;

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

    /**
     * 文件集合
     */
    private String fileList;

    public String getFileList() {
        return fileList;
    }

    public void setFileList(String fileList) {
        this.fileList = fileList;
    }

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

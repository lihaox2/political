package com.bayee.political.pojo.dto;

/**
 * @author xxl
 * @date 2021/5/19
 */
public class DutyPageDO {

    private Integer id;

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
     * 警情类别名称
     */
    private String informationName;

    /**
     * 错误类别名称
     */
    private String errorName;

    /**
     * 扣除分数
     */
    private Double deductScore;

    /**
     * 警情时间
     */
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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
        this.policeId = policeId;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
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

    public String getInformationName() {
        return informationName;
    }

    public void setInformationName(String informationName) {
        this.informationName = informationName;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public Double getDeductScore() {
        return deductScore;
    }

    public void setDeductScore(Double deductScore) {
        this.deductScore = deductScore;
    }
}

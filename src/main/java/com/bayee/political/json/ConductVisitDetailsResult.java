package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/5/7
 */
public class ConductVisitDetailsResult {

    private String policeId;

    private String policeName;

    private Integer bigTypeId;

    /**
     * 大类名称
     */
    private String bigType;

    private Integer smallTypeId;

    /**
     * 小类名称
     */
    private String smallType;

    /**
     * 问题情况和来源
     */
    private String content;

    /**
     * 扣除分数
     */
    private Double deductScore;

    /**
     * 时间
     */
    private String date;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 是否属实（1.属实，2.不属实）
     */
    private Integer idReally;

    /**
     * 信访来源id
     */
    private Integer originId;

    /**
     * 来源名称
     */
    private String originName;

    public Integer getIdReally() {
        return idReally;
    }

    public void setIdReally(Integer idReally) {
        this.idReally = idReally;
    }

    public Integer getOriginId() {
        return originId;
    }

    public void setOriginId(Integer originId) {
        this.originId = originId;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public Integer getBigTypeId() {
        return bigTypeId;
    }

    public void setBigTypeId(Integer bigTypeId) {
        this.bigTypeId = bigTypeId;
    }

    public Integer getSmallTypeId() {
        return smallTypeId;
    }

    public void setSmallTypeId(Integer smallTypeId) {
        this.smallTypeId = smallTypeId;
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

    public String getBigType() {
        return bigType;
    }

    public void setBigType(String bigType) {
        this.bigType = bigType;
    }

    public String getSmallType() {
        return smallType;
    }

    public void setSmallType(String smallType) {
        this.smallType = smallType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}

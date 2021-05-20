package com.bayee.political.pojo.dto;

/**
 * @author xxl
 * @date 2021/5/19
 */
public class CaseLawEnforcementPageDO {

    private Integer id;

    /**
     * 警号
     */
    private String policeId;

    /**
     * 警员名称
     */
    private String policeName;

    /**
     * 责任部门
     */
    private String deptName;

    /**
     * 案件编号
     */
    private String caseCode;

    /**
     * 问题类型名称
     */
    private String typeName;

    /**
     * 扣分标准
     */
    private String content;

    /**
     * 问题描述
     */
    private String desc;

    /**
     * 扣除分数
     */
    private Double deductScore;

    /**
     * 扣分时间
     */
    private String date;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCaseCode() {
        return caseCode;
    }

    public void setCaseCode(String caseCode) {
        this.caseCode = caseCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

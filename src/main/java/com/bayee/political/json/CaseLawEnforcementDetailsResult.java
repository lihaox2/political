package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/5/7
 */
public class CaseLawEnforcementDetailsResult {

    /**
     * 警员编号
     */
    private String policeId;

    /**
     * 警员名称
     */
    private String policeName;

    /**
     * 责任单位
     */
    private String deptName;

    /**
     * 案件编号
     */
    private String caseCode;

    /**
     * 问题分类id
     */
    private Integer parentId;

    /**
     * 问题分类名称
     */
    private String parentName;

    /**
     * 扣分标准id
     */
    private Integer typeId;

    /**
     * 扣分标准
     */
    private String typeName;

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
     * 同样错误次数
     */
    private Integer replaceErrorCount;

    /**
     * 文件集合
     */
    private String[] fileList;

    public String[] getFileList() {
        return fileList;
    }

    public void setFileList(String[] fileList) {
        this.fileList = fileList;
    }

    public Integer getReplaceErrorCount() {
        return replaceErrorCount;
    }

    public void setReplaceErrorCount(Integer replaceErrorCount) {
        this.replaceErrorCount = replaceErrorCount;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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

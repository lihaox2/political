package com.bayee.political.json;

/**
 * 人才库请求参数
 */
public class TalentsUserParam {

    /**
     * 性别（1: 男，2：女）
     */
    private Integer gender;

    /**
     * 学历（1、中专 2、大专 3、本科 4、研究生）
     */
    private Integer degree;

    /**
     * 小年龄
     */
    private Integer minAge;

    /**
     * 大年龄
     */
    private Integer maxAge;

    /**
     * 警种id
     */
    private Integer kindId;

    /**
     * 参加工作年限（1、1-3年 2、3-5年 3、5-10年 4、10年以上）
     */
    private Integer workIngYear;

    /**
     * 参数公安工作年限（1、1-3年 2、3-5年 3、5-10年 4、10年以上）
     */
    private Integer workEmploymentYear;

    /**
     * 民警部门表id
     */
    private Integer deptId;

    /**
     * 模糊查询字段
     */
    private String likeName;

    /**
     * 标签id
     */
    private Integer labelId;

    /**
     * 政治面貌
     */
    private String politicalStatus;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 婚姻状况（1、未婚 2、已婚）
     */
    private Integer marriageStatus;

    /**
     * 页码数
     */
    private Integer pageIndex;

    /**
     * 数据条数
     */
    private Integer pageSize;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public Integer getKindId() {
        return kindId;
    }

    public void setKindId(Integer kindId) {
        this.kindId = kindId;
    }

    public Integer getWorkIngYear() {
        return workIngYear;
    }

    public void setWorkIngYear(Integer workIngYear) {
        this.workIngYear = workIngYear;
    }

    public Integer getWorkEmploymentYear() {
        return workEmploymentYear;
    }

    public void setWorkEmploymentYear(Integer workEmploymentYear) {
        this.workEmploymentYear = workEmploymentYear;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getLikeName() {
        return likeName;
    }

    public void setLikeName(String likeName) {
        this.likeName = likeName;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public Integer getMarriageStatus() {
        return marriageStatus;
    }

    public void setMarriageStatus(Integer marriageStatus) {
        this.marriageStatus = marriageStatus;
    }
}

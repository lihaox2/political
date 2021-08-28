package com.bayee.political.json;

import java.util.List;

/**
 * @author xxl
 * @date 2021/6/5
 */
public class HolographicPoliceDetailsResult {

    /**
     * 警号
     */
    private String policeId;

    /**
     * 警员名称
     */
    private String policeName;

    /**
     * 警员头像
     */
    private String headImg;

    /**
     * 警员类型
     */
    private String policeType;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 政治面貌
     */
    private String politicalOutlook;

    /**
     * 工作年份
     */
    private Integer workYearCount;

    /**
     * 参加公安时间
     */
    private String publicSecurityDate;

    /**
     * 参加组织时间
     */
    private String organizeDate;

    /**
     * 警员标签
     */
    private String policeLabel;

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

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getPoliceType() {
        return policeType;
    }

    public void setPoliceType(String policeType) {
        this.policeType = policeType;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getPoliticalOutlook() {
        return politicalOutlook;
    }

    public void setPoliticalOutlook(String politicalOutlook) {
        this.politicalOutlook = politicalOutlook;
    }

    public Integer getWorkYearCount() {
        return workYearCount;
    }

    public void setWorkYearCount(Integer workYearCount) {
        this.workYearCount = workYearCount;
    }

    public String getPublicSecurityDate() {
        return publicSecurityDate;
    }

    public void setPublicSecurityDate(String publicSecurityDate) {
        this.publicSecurityDate = publicSecurityDate;
    }

    public String getOrganizeDate() {
        return organizeDate;
    }

    public void setOrganizeDate(String organizeDate) {
        this.organizeDate = organizeDate;
    }

    public String getPoliceLabel() {
        return policeLabel;
    }

    public void setPoliceLabel(String policeLabel) {
        this.policeLabel = policeLabel;
    }
}

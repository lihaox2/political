package com.bayee.political.json;

import java.util.Date;

/**
 * @author xxl
 * @date 2021/6/7
 */
public class PoliceEducationLogResult {

    /**
     * 类型3-学习经历
     */
    private Integer type = 3;

    /**
     * id
     */
    private Integer id;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 学校图标
     */
    private String schoolIcon;

    /**
     * 学位信息
     */
    private String academicDegree;

    /**
     * 专业信息
     */
    private String major;

    /**
     * 时间信息
     */
    private String dateInfo;

    /**
     * sort
     */
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolIcon() {
        return schoolIcon;
    }

    public void setSchoolIcon(String schoolIcon) {
        this.schoolIcon = schoolIcon;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDateInfo() {
        return dateInfo;
    }

    public void setDateInfo(String dateInfo) {
        this.dateInfo = dateInfo;
    }
}

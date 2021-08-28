package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/5/20
 */
public class TrainFirearmDetailsPageResult {

    private Integer id;

    /**
     * 警员名称
     */
    private String policeName;

    /**
     * 警号
     */
    private String policeId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 成绩
     */
    private String eligibleFlag;

    /**
     * 成绩录入时间
     */
    private String date;

    /**
     * 成绩
     */
    private Integer achievement;

    public Integer getAchievement() {
        return achievement;
    }

    public void setAchievement(Integer achievement) {
        this.achievement = achievement;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getEligibleFlag() {
        return eligibleFlag;
    }

    public void setEligibleFlag(String eligibleFlag) {
        this.eligibleFlag = eligibleFlag;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

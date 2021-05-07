package com.bayee.political.pojo.json;

/**
 * @author xxl
 * @date 2021/5/7
 */
public class CaseTestDetailsResult {

    /**
     * 警员id
     */
    private String policeId;

    /**
     * 警员名称
     */
    private String policeName;

    /**
     * 考试名称
     */
    private String testName;

    /**
     * 分数
     */
    private Double score;

    /**
     * 考试年份
     */
    private String year;

    /**
     * 期数
     */
    private Integer semester;

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

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }
}

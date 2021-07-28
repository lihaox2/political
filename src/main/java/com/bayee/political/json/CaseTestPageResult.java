package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/5/7
 */
public class CaseTestPageResult {

    private Integer id;

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
    private String date;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

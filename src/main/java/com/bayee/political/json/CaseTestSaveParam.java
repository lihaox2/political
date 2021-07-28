package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/5/7
 */
public class CaseTestSaveParam {

    /**
     * 警员id
     */
    private String policeId;

    /**
     * 考试名称
     */
    private String testName;

    /**
     * 得分
     */
    private Double score;

    /**
     * 考试时间
     */
    private String date;

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
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

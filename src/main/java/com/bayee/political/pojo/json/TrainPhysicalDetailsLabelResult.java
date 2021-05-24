package com.bayee.political.pojo.json;

/**
 * @author xxl
 * @date 2021/5/20
 */
public class TrainPhysicalDetailsLabelResult {

    /**
     * 参加数
     */
    private Integer attendCount;

    /**
     * 未参加数
     */
    private Integer notAttendCount;

    /**
     * 及格数
     */
    private Integer eligibleCount;

    /**
     * 及格率
     */
    private Double eligibleRatio;

    /**
     * 未及格数
     */
    private Integer notEligibleCount;

    /**
     * 未及格率
     */
    private Double notEligibleRatio;

    /**
     * 1到2项不合格
     */
    private Integer ontToTwoNotOverCount;

    /**
     * 3到5项不合格
     */
    private Integer threeToFiveNotOverCount;

    /**
     * 大于5项不合格
     */
    private Integer moreThanFiveNotOverCount;

    public Integer getAttendCount() {
        return attendCount;
    }

    public void setAttendCount(Integer attendCount) {
        this.attendCount = attendCount;
    }

    public Integer getNotAttendCount() {
        return notAttendCount;
    }

    public void setNotAttendCount(Integer notAttendCount) {
        this.notAttendCount = notAttendCount;
    }

    public Integer getEligibleCount() {
        return eligibleCount;
    }

    public void setEligibleCount(Integer eligibleCount) {
        this.eligibleCount = eligibleCount;
    }

    public Double getEligibleRatio() {
        return eligibleRatio;
    }

    public void setEligibleRatio(Double eligibleRatio) {
        this.eligibleRatio = eligibleRatio;
    }

    public Integer getNotEligibleCount() {
        return notEligibleCount;
    }

    public void setNotEligibleCount(Integer notEligibleCount) {
        this.notEligibleCount = notEligibleCount;
    }

    public Double getNotEligibleRatio() {
        return notEligibleRatio;
    }

    public void setNotEligibleRatio(Double notEligibleRatio) {
        this.notEligibleRatio = notEligibleRatio;
    }

    public Integer getOntToTwoNotOverCount() {
        return ontToTwoNotOverCount;
    }

    public void setOntToTwoNotOverCount(Integer ontToTwoNotOverCount) {
        this.ontToTwoNotOverCount = ontToTwoNotOverCount;
    }

    public Integer getThreeToFiveNotOverCount() {
        return threeToFiveNotOverCount;
    }

    public void setThreeToFiveNotOverCount(Integer threeToFiveNotOverCount) {
        this.threeToFiveNotOverCount = threeToFiveNotOverCount;
    }

    public Integer getMoreThanFiveNotOverCount() {
        return moreThanFiveNotOverCount;
    }

    public void setMoreThanFiveNotOverCount(Integer moreThanFiveNotOverCount) {
        this.moreThanFiveNotOverCount = moreThanFiveNotOverCount;
    }
}

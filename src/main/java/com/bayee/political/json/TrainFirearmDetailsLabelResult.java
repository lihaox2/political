package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/5/20
 */
public class TrainFirearmDetailsLabelResult {

    /**
     * 完成人数
     */
    private Integer attendCount;

    /**
     * 未完成数
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

}

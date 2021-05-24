package com.bayee.political.pojo.json;

/**
 * @author xxl
 * @date 2021/5/20
 */
public class TrainFirearmPageResult {

    private Integer id;

    /**
     * 比赛名称
     */
    private String trainName;

    /**
     * 比赛时间
     */
    private String date;

    /**
     * 射击类型
     */
    private String firingType;

    /**
     * 及格数
     */
    private Integer eligibleCount;

    /**
     * 未及格数
     */
    private Integer notEligibleCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFiringType() {
        return firingType;
    }

    public void setFiringType(String firingType) {
        this.firingType = firingType;
    }

    public Integer getEligibleCount() {
        return eligibleCount;
    }

    public void setEligibleCount(Integer eligibleCount) {
        this.eligibleCount = eligibleCount;
    }

    public Integer getNotEligibleCount() {
        return notEligibleCount;
    }

    public void setNotEligibleCount(Integer notEligibleCount) {
        this.notEligibleCount = notEligibleCount;
    }
}

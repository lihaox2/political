package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/5/20
 */
public class TrainPhysicalPageResult {

    private Integer id;

    /**
     * 训练名称
     */
    private String trainName;

    /**
     * 训练时间
     */
    private String trainDate;

    /**
     * 及格数
     */
    private Integer eligibleCount;

    /**
     * 未及格数
     */
    private Integer notEligibleCount;

    /**
     * 训练类型（1.全局，2.抽测）
     */
    private Integer trainType;

    public Integer getTrainType() {
        return trainType;
    }

    public void setTrainType(Integer trainType) {
        this.trainType = trainType;
    }

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

    public String getTrainDate() {
        return trainDate;
    }

    public void setTrainDate(String trainDate) {
        this.trainDate = trainDate;
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

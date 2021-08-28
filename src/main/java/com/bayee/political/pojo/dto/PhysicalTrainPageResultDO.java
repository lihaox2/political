package com.bayee.political.pojo.dto;

/**
 * @author xxl
 * @date 2021/8/3
 */
public class PhysicalTrainPageResultDO {

    /**
     * id
     */
    private Integer id;

    /**
     * 训练名称
     */
    private String trainName;

    /**
     * 训练类型（1.全局，2.抽测）
     */
    private Integer trainType;

    /**
     * 训练时间
     */
    private String trainDate;

    /**
     * 合格人数
     */
    private Integer qualifiedCount;

    /**
     * 不合格人数
     */
    private Integer unQualifiedCount;

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

    public Integer getTrainType() {
        return trainType;
    }

    public void setTrainType(Integer trainType) {
        this.trainType = trainType;
    }

    public String getTrainDate() {
        return trainDate;
    }

    public void setTrainDate(String trainDate) {
        this.trainDate = trainDate;
    }

    public Integer getQualifiedCount() {
        return qualifiedCount;
    }

    public void setQualifiedCount(Integer qualifiedCount) {
        this.qualifiedCount = qualifiedCount;
    }

    public Integer getUnQualifiedCount() {
        return unQualifiedCount;
    }

    public void setUnQualifiedCount(Integer unQualifiedCount) {
        this.unQualifiedCount = unQualifiedCount;
    }
}

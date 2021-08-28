package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/8/3
 */
public class PhysicalTrainPageQueryParam {

    /**
     * 训练开始时间(yyyy-MM-dd HH:mm:ss)
     */
    private String trainBeginDate;

    /**
     * 训练结束时间(yyyy-MM-dd HH:mm:ss)
     */
    private String trainEndDate;

    /**
     * 训练名称
     */
    private String trainName;

    /**
     * 当前页码
     */
    private Integer pageIndex;

    /**
     * 数据条数
     */
    private Integer pageSize;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getTrainBeginDate() {
        return trainBeginDate;
    }

    public void setTrainBeginDate(String trainBeginDate) {
        this.trainBeginDate = trainBeginDate;
    }

    public String getTrainEndDate() {
        return trainEndDate;
    }

    public void setTrainEndDate(String trainEndDate) {
        this.trainEndDate = trainEndDate;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }
}

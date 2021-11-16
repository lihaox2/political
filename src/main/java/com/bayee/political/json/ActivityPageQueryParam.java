package com.bayee.political.json;

/**
 * @author tlt
 * @date 2021/10/25
 */
public class ActivityPageQueryParam {

    /**
     * 所属月份
     */
    private String belongMonth;

    /**
     * 活动状态
     */
    private Integer activityStatus;

    /**
     * 模糊查询字段(活动名称)
     */
    private String key;

    /**
     * 当前页码
     */
    private Integer pageIndex;

    /**
     * 数据条数
     */
    private Integer pageSize;

    public String getBelongMonth() {
        return belongMonth;
    }

    public void setBelongMonth(String belongMonth) {
        this.belongMonth = belongMonth;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

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
}

package com.bayee.political.json;

/**
 * @author tlt
 * @date 2021/11/9
 */
public class MajorAuditPageQueryParam {

    /**
     * 报告人
     */
    private String name;

    /**
     * 部门id
     */
    private Integer deptId;

    /**
     * 职务id
     */
    private Integer positionId;

    /**
     * 警号
     */
    private String policeId;

    /**
     * 用户职务id
     */
    private Integer userPositionId;

    /**
     * 报告状态
     */
    private Integer reportStatus;

    /**
     * 开始时间
     */
    private String startDate;

    /**
     * 结束时间
     */
    private String endDate;

    /**
     * 当前页码
     */
    private Integer pageIndex;

    /**
     * 数据条数
     */
    private Integer pageSize;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public Integer getUserPositionId() {
        return userPositionId;
    }

    public void setUserPositionId(Integer userPositionId) {
        this.userPositionId = userPositionId;
    }

    public Integer getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

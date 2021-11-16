package com.bayee.political.pojo;

/**
 * @author tlt
 * @date 2021/11/5
 */
public class MajorReportPageResultDO {

    private Integer id;

    /**
     * 警号
     */
    private String policeId;

    /**
     * 警员名称
     */
    private String policeName;

    /**
     * 单位
     */
    private String departmentName;

    /**
     * 职务
     */
    private String positionName;

    /**
     * 报告时间
     */
    private String businessTime;

    /**
     * 报告描述
     */
    private String reportDescription;

    /**
     * 报告状态（0为待上报，1为审核中，2为已完成）
     */
    private Integer reportStatus;

    /**
     * 部门审核状态（0为待审核，1为已通过，2为已驳回）
     */
    private Integer deptStatus;

    /**
     * 分管局审核状态（0为待审核，1为已通过，2为已驳回）
     */
    private Integer bureauStatus;

    /**
     * 是否为再次上报状态
     */
    private Integer isAgain;

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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    public Integer getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }

    public Integer getDeptStatus() {
        return deptStatus;
    }

    public void setDeptStatus(Integer deptStatus) {
        this.deptStatus = deptStatus;
    }

    public Integer getBureauStatus() {
        return bureauStatus;
    }

    public void setBureauStatus(Integer bureauStatus) {
        this.bureauStatus = bureauStatus;
    }

    public Integer getIsAgain() {
        return isAgain;
    }

    public void setIsAgain(Integer isAgain) {
        this.isAgain = isAgain;
    }
}

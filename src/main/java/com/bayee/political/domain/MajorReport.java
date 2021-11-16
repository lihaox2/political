package com.bayee.political.domain;

import java.util.Date;

public class MajorReport {
    /**
     * id
     */
    private Integer id;

    /**
     * 报告人警号
     */
    private String policeId;

    /**
     * 报告描述
     */
    private String reportDescription;

    /**
     * 报告内容
     */
    private String reportContent;

    /**
     * 是否存在风险（1为是，2为否）
     */
    private Integer isRisk;

    /**
     * 报告状态（0为待上报，1为审核中，2为已完成，3为再次上报）
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
     * 报告承诺（1为是，2为否）
     */
    private Integer reportCommitment;

    /**
     * 是否为再次上报状态（1为再次上报状态）
     */
    private Integer isAgain;

    /**
     * 报告时间
     */
    private Date businessTime;

    /**
     * 创建时间
     */
    private Date creationDate;

    /**
     * 修改时间
     */
    private Date updateDate;

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
        this.policeId = policeId == null ? null : policeId.trim();
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription == null ? null : reportDescription.trim();
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent == null ? null : reportContent.trim();
    }

    public Integer getIsRisk() {
        return isRisk;
    }

    public void setIsRisk(Integer isRisk) {
        this.isRisk = isRisk;
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

    public Integer getReportCommitment() {
        return reportCommitment;
    }

    public void setReportCommitment(Integer reportCommitment) {
        this.reportCommitment = reportCommitment;
    }

    public Integer getIsAgain() {
        return isAgain;
    }

    public void setIsAgain(Integer isAgain) {
        this.isAgain = isAgain;
    }

    public Date getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(Date businessTime) {
        this.businessTime = businessTime;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
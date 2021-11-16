package com.bayee.political.json;

import com.bayee.political.domain.MajorAccessory;

import java.util.List;

/**
 * @author tlt
 * @date 2021/11/7
 */
public class MajorReportDetailsResult {

    private Integer id;

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
     * 报告内容
     */
    private String reportContent;

    /**
     * 是否存在风险（1为是，2为否）
     */
    private Integer isRisk;

    /**
     * 报告承诺（1为是，2为否）
     */
    private Integer reportCommitment;

    /**
     * 附件
     */
    private List<MajorAccessory> accessoryList;

    /**
     * 审核流程
     */
    private List<MajorReportAndAuditRecord> majorReportAndAuditRecords;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public Integer getIsRisk() {
        return isRisk;
    }

    public void setIsRisk(Integer isRisk) {
        this.isRisk = isRisk;
    }

    public Integer getReportCommitment() {
        return reportCommitment;
    }

    public void setReportCommitment(Integer reportCommitment) {
        this.reportCommitment = reportCommitment;
    }

    public List<MajorAccessory> getAccessoryList() {
        return accessoryList;
    }

    public void setAccessoryList(List<MajorAccessory> accessoryList) {
        this.accessoryList = accessoryList;
    }

    public List<MajorReportAndAuditRecord> getMajorReportAndAuditRecords() {
        return majorReportAndAuditRecords;
    }

    public void setMajorReportAndAuditRecords(List<MajorReportAndAuditRecord> majorReportAndAuditRecords) {
        this.majorReportAndAuditRecords = majorReportAndAuditRecords;
    }
}

package com.bayee.political.json;

import com.bayee.political.domain.MajorAccessory;

import java.util.List;

/**
 * @author tlt
 * @date 2021/11/5
 */
public class MajorReportSaveParam {

    /**
     * id
     */
    private Integer id;

    /**
     * 警号
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
     * 报告承诺（1为是，2为否）
     */
    private Integer reportCommitment;

    /**
     * 报告时间
     */
    private String businessTime;

    /**
     * 附件上传
     */
    private List<MajorAccessory> accessoryList;

    /**
     * 报告状态
     */
    private Integer reportStatus;

    /**
     * 再次上报状态
     */
    private Integer isAgain;

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
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

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    public List<MajorAccessory> getAccessoryList() {
        return accessoryList;
    }

    public void setAccessoryList(List<MajorAccessory> accessoryList) {
        this.accessoryList = accessoryList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }

    public Integer getIsAgain() {
        return isAgain;
    }

    public void setIsAgain(Integer isAgain) {
        this.isAgain = isAgain;
    }
}

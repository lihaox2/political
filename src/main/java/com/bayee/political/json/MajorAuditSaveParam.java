package com.bayee.political.json;

/**
 * @author tlt
 * @date 2021/11/8
 */
public class MajorAuditSaveParam {

    /**
     * id
     */
    private Integer id;

    /**
     * 审核人警号
     */
    private String policeId;

    /**
     * 报告id
     */
    private Integer reportId;

    /**
     * 审核意见
     */
    private String opinion;

    /**
     * 审核状态
     */
    private Integer auditStatus;

    /**
     * 审核结果（1为通过，2为驳回）
     */
    private Integer auditResult;

    /**
     * 审核时间
     */
    private String businessTime;

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

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(Integer auditResult) {
        this.auditResult = auditResult;
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }
}

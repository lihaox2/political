package com.bayee.political.json;

/**
 * @author tlt
 * @date 2021/11/8
 */
public class MajorAuditDetailsResult {

    /**
     * 审核人姓名
     */
    private String name;

    /**
     * 审核状态
     */
    private Integer auditStatus;

    /**
     * 审核结果（1为通过，2为驳回）
     */
    private Integer auditResult;

    /**
     * 审核意见
     */
    private String opinion;

    /**
     * 审核时间
     */
    private String businessTime;

    /**
     * 上报id
     */
    private Integer recordId;

    /**
     * 是否为待审核
     */
    private Integer isAudit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(Integer isAudit) {
        this.isAudit = isAudit;
    }
}

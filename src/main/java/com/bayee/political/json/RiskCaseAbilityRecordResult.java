package com.bayee.political.json;

import java.util.Date;

/**
 * @author xxl
 * @date 2021/6/14
 */
public class RiskCaseAbilityRecordResult {

    /**
     * 类型2-执法能力
     */
    private Integer type = 2;

    /**
     * 警员名称
     */
    private String policeName;

    /**
     * 复议诉讼撤变(否 是)
     */
    private String reconsiderationLitigationStatus;

    /**
     * 有责涉案（警）投诉、信访(否 是)
     */
    private String letterVisitStatus;

    /**
     * 重大执法过错(否 是)
     */
    private String lawEnforcementFaultStatus;

    /**
     * 司法监督(否 是)
     */
    private String judicialSupervisionStatus;

    /**
     * 执法办案能手(否 是)
     */
    private String caseExpertStatus;

    /**
     * 优秀法制员(否 是)
     */
    private String excellentLegalOfficerStatus;

    /**
     * 基本级执法考试(否 是)
     */
    private String basicTestStatus;

    /**
     * 高级执法考试(否 是)
     */
    private String highTestStatus;

    /**
     * 司法考试(否 是)
     */
    private String judicialTestStatus;

    /**
     * sort
     */
    private Date date;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    public String getReconsiderationLitigationStatus() {
        return reconsiderationLitigationStatus;
    }

    public void setReconsiderationLitigationStatus(String reconsiderationLitigationStatus) {
        this.reconsiderationLitigationStatus = reconsiderationLitigationStatus;
    }

    public String getLetterVisitStatus() {
        return letterVisitStatus;
    }

    public void setLetterVisitStatus(String letterVisitStatus) {
        this.letterVisitStatus = letterVisitStatus;
    }

    public String getLawEnforcementFaultStatus() {
        return lawEnforcementFaultStatus;
    }

    public void setLawEnforcementFaultStatus(String lawEnforcementFaultStatus) {
        this.lawEnforcementFaultStatus = lawEnforcementFaultStatus;
    }

    public String getJudicialSupervisionStatus() {
        return judicialSupervisionStatus;
    }

    public void setJudicialSupervisionStatus(String judicialSupervisionStatus) {
        this.judicialSupervisionStatus = judicialSupervisionStatus;
    }

    public String getCaseExpertStatus() {
        return caseExpertStatus;
    }

    public void setCaseExpertStatus(String caseExpertStatus) {
        this.caseExpertStatus = caseExpertStatus;
    }

    public String getExcellentLegalOfficerStatus() {
        return excellentLegalOfficerStatus;
    }

    public void setExcellentLegalOfficerStatus(String excellentLegalOfficerStatus) {
        this.excellentLegalOfficerStatus = excellentLegalOfficerStatus;
    }

    public String getBasicTestStatus() {
        return basicTestStatus;
    }

    public void setBasicTestStatus(String basicTestStatus) {
        this.basicTestStatus = basicTestStatus;
    }

    public String getHighTestStatus() {
        return highTestStatus;
    }

    public void setHighTestStatus(String highTestStatus) {
        this.highTestStatus = highTestStatus;
    }

    public String getJudicialTestStatus() {
        return judicialTestStatus;
    }

    public void setJudicialTestStatus(String judicialTestStatus) {
        this.judicialTestStatus = judicialTestStatus;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

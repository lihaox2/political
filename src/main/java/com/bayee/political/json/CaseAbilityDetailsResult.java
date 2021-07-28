package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/5/7
 */
public class CaseAbilityDetailsResult {

    /**
     * 警员编号
     */
    private String policeId;

    /**
     * 警员姓名
     */
    private String policeName;

    /**
     * 复议诉讼撤变（必填）（0否1是）
     */
    private Integer reconsiderationLitigationStatus;

    /**
     * 有责涉案（警）投诉、信访（必填）(0否1是)
     */
    private Integer letterVisitStatus;

    /**
     * 重大执法过错（必填）(0否1是)
     */
    private Integer lawEnforcementFaultStatus;

    /**
     * 司法监督（检察院纠违）（必填）(0否1是)
     */
    private Integer judicialSupervisionStatus;

    /**
     * 执法办案能手（必填）(0否1是)
     */
    private Integer caseExpertStatus;

    /**
     * 优秀法制员（必填）(0否1是)
     */
    private Integer excellentLegalOfficerStatus;

    /**
     * 基本级执法考试（必填）(0否1是)
     */
    private Integer basicTestStatus;

    /**
     * 高级执法考试（必填）(0否1是)
     */
    private Integer highTestStatus;

    /**
     * 司法考试（必填）(0否1是)
     */
    private Integer judicialTestStatus;

    /**
     * 时间
     */
    private String date;

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public Integer getReconsiderationLitigationStatus() {
        return reconsiderationLitigationStatus;
    }

    public void setReconsiderationLitigationStatus(Integer reconsiderationLitigationStatus) {
        this.reconsiderationLitigationStatus = reconsiderationLitigationStatus;
    }

    public Integer getLetterVisitStatus() {
        return letterVisitStatus;
    }

    public void setLetterVisitStatus(Integer letterVisitStatus) {
        this.letterVisitStatus = letterVisitStatus;
    }

    public Integer getLawEnforcementFaultStatus() {
        return lawEnforcementFaultStatus;
    }

    public void setLawEnforcementFaultStatus(Integer lawEnforcementFaultStatus) {
        this.lawEnforcementFaultStatus = lawEnforcementFaultStatus;
    }

    public Integer getJudicialSupervisionStatus() {
        return judicialSupervisionStatus;
    }

    public void setJudicialSupervisionStatus(Integer judicialSupervisionStatus) {
        this.judicialSupervisionStatus = judicialSupervisionStatus;
    }

    public Integer getCaseExpertStatus() {
        return caseExpertStatus;
    }

    public void setCaseExpertStatus(Integer caseExpertStatus) {
        this.caseExpertStatus = caseExpertStatus;
    }

    public Integer getExcellentLegalOfficerStatus() {
        return excellentLegalOfficerStatus;
    }

    public void setExcellentLegalOfficerStatus(Integer excellentLegalOfficerStatus) {
        this.excellentLegalOfficerStatus = excellentLegalOfficerStatus;
    }

    public Integer getBasicTestStatus() {
        return basicTestStatus;
    }

    public void setBasicTestStatus(Integer basicTestStatus) {
        this.basicTestStatus = basicTestStatus;
    }

    public Integer getHighTestStatus() {
        return highTestStatus;
    }

    public void setHighTestStatus(Integer highTestStatus) {
        this.highTestStatus = highTestStatus;
    }

    public Integer getJudicialTestStatus() {
        return judicialTestStatus;
    }

    public void setJudicialTestStatus(Integer judicialTestStatus) {
        this.judicialTestStatus = judicialTestStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

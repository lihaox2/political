package com.bayee.political.json;

import java.util.List;

/**
 * @author tlt
 * @date 2021/11/10
 */
public class MajorReportAndAuditRecord {

    List<MajorAuditDetailsResult> resultList;

    ReportRecordResult record;

    public ReportRecordResult getRecord() {
        return record;
    }

    public void setRecord(ReportRecordResult record) {
        this.record = record;
    }

    public List<MajorAuditDetailsResult> getResultList() {
        return resultList;
    }

    public void setResultList(List<MajorAuditDetailsResult> resultList) {
        this.resultList = resultList;
    }
}

package com.bayee.political.service;

import com.bayee.political.domain.MajorReportRecord;
import com.bayee.political.json.ReportRecordResult;

import java.util.List;

/**
 * @author tlt
 * @date 2021/11/10
 */
public interface MajorReportRecordService {

    /**
     * 新增上报记录
     */
    void insert(MajorReportRecord record);

    /**
     * 查询最新上报记录
     * @return
     */
    MajorReportRecord findByMaxCreationDate(Integer reportId);

    /**
     * 查询所有上报记录
     * @param reportId
     * @return
     */
    List<ReportRecordResult> findAll(Integer reportId);
}

package com.bayee.political.service.impl;

import com.bayee.political.domain.MajorReportRecord;
import com.bayee.political.json.ReportRecordResult;
import com.bayee.political.mapper.MajorReportRecordMapper;
import com.bayee.political.service.MajorReportRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tlt
 * @date 2021/11/10
 */
@Service
public class MajorReportRecordServiceImpl implements MajorReportRecordService {

    @Autowired
    private MajorReportRecordMapper reportRecordMapper;

    @Override
    public void insert(MajorReportRecord record) {
        reportRecordMapper.insert(record);
    }

    @Override
    public MajorReportRecord findByMaxCreationDate(Integer reportId) {
        return reportRecordMapper.selectByMaxCreationDate(reportId);
    }

    @Override
    public List<ReportRecordResult> findAll(Integer reportId) {
        return reportRecordMapper.selectAll(reportId);
    }
}

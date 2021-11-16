package com.bayee.political.service.impl;

import com.bayee.political.domain.MajorReport;
import com.bayee.political.json.MajorReportPageQueryParam;
import com.bayee.political.mapper.MajorReportMapper;
import com.bayee.political.pojo.MajorReportPageResultDO;
import com.bayee.political.service.MajorReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tlt
 * @date 2021/11/4
 */
@Service
public class MajorReportServiceImpl implements MajorReportService {

    @Autowired
    private MajorReportMapper reportMapper;

    @Override
    public void addReport(MajorReport report) {
        reportMapper.insert(report);
    }

    @Override
    public void updateReport(MajorReport report) {
        reportMapper.updateByPrimaryKey(report);
    }

    @Override
    public MajorReport findById(Integer id) {
        return reportMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteReport(Integer id) {
        reportMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<MajorReportPageResultDO> majorReportPage(MajorReportPageQueryParam queryParam) {
        if (queryParam.getPageIndex() == null || queryParam.getPageIndex() < 1) {
            queryParam.setPageIndex(1);
        }
        if (queryParam.getPageSize() == null) {
            queryParam.setPageSize(10);
        }
        queryParam.setPageIndex((queryParam.getPageIndex() - 1) * queryParam.getPageSize());

        return reportMapper.majorReportPage(queryParam);
    }

    @Override
    public Integer majorReportPageCount(MajorReportPageQueryParam queryParam) {
        return reportMapper.majorReportPageCount(queryParam);
    }
}

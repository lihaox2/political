package com.bayee.political.service.impl;

import com.bayee.political.domain.MajorAudit;
import com.bayee.political.json.HistoryPageQueryParam;
import com.bayee.political.json.MajorAuditDetailsResult;
import com.bayee.political.json.MajorAuditPageQueryParam;
import com.bayee.political.json.MajorReportPageQueryParam;
import com.bayee.political.mapper.MajorAuditMapper;
import com.bayee.political.pojo.MajorReportPageResultDO;
import com.bayee.political.service.MajorAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tlt
 * @date 2021/11/4
 */
@Service
public class MajorAuditServiceImpl implements MajorAuditService {

    @Autowired
    private MajorAuditMapper auditMapper;

    @Override
    public void addMajorAudit(MajorAudit audit) {
        auditMapper.insert(audit);
    }

    @Override
    public List<MajorReportPageResultDO> majorReportPage(MajorAuditPageQueryParam queryParam) {
        if (queryParam.getPageIndex() == null || queryParam.getPageIndex() < 1) {
            queryParam.setPageIndex(1);
        }
        if (queryParam.getPageSize() == null) {
            queryParam.setPageSize(10);
        }
        queryParam.setPageIndex((queryParam.getPageIndex() - 1) * queryParam.getPageSize());

        return auditMapper.majorAuditPage(queryParam);
    }

    @Override
    public Integer majorReportPageCount(MajorAuditPageQueryParam queryParam) {
        return auditMapper.majorAuditPageCount(queryParam);
    }

    @Override
    public List<MajorReportPageResultDO> historyPage(HistoryPageQueryParam queryParam) {
        if (queryParam.getPageIndex() == null || queryParam.getPageIndex() < 1) {
            queryParam.setPageIndex(1);
        }
        if (queryParam.getPageSize() == null) {
            queryParam.setPageSize(10);
        }
        queryParam.setPageIndex((queryParam.getPageIndex() - 1) * queryParam.getPageSize());
        return auditMapper.historyPage(queryParam);
    }

    @Override
    public Integer historyPageCount(HistoryPageQueryParam queryParam) {
        return auditMapper.historyPageCount(queryParam);
    }

    @Override
    public List<MajorAuditDetailsResult> findByReportIdAndStatus(Integer reportId) {
        return auditMapper.selectByReportIdAndStatus(reportId);
    }

    @Override
    public void deleteByReportId(Integer reportId) {
        auditMapper.deleteByReportId(reportId);
    }
}

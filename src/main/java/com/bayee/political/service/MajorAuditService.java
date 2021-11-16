package com.bayee.political.service;

import com.bayee.political.domain.MajorAudit;
import com.bayee.political.json.HistoryPageQueryParam;
import com.bayee.political.json.MajorAuditDetailsResult;
import com.bayee.political.json.MajorAuditPageQueryParam;
import com.bayee.political.json.MajorReportPageQueryParam;
import com.bayee.political.pojo.MajorReportPageResultDO;

import java.util.List;

/**
 * @author tlt
 * @date 2021/11/4
 */
public interface MajorAuditService {

    /**
     * 重大审核-新增数据
     * @param audit
     */
    void addMajorAudit(MajorAudit audit);

    /**
     * 分页查询重大报告
     * @return
     */
    List<MajorReportPageResultDO> majorReportPage(MajorAuditPageQueryParam queryParam);

    /**
     * 分页统计
     * @param queryParam
     * @return
     */
    Integer majorReportPageCount(MajorAuditPageQueryParam queryParam);

    /**
     * 分页查询历史记录
     * @return
     */
    List<MajorReportPageResultDO> historyPage(HistoryPageQueryParam queryParam);

    /**
     * 分页统计历史记录
     * @param queryParam
     * @return
     */
    Integer historyPageCount(HistoryPageQueryParam queryParam);

    /**
     * 查询已审核数据
     * @param reportId
     * @return
     */
    List<MajorAuditDetailsResult> findByReportIdAndStatus(Integer reportId);

    /**
     * 以报告id删除审核
     * @param reportId
     */
    void deleteByReportId(Integer reportId);

}

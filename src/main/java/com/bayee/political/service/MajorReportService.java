package com.bayee.political.service;

import com.bayee.political.domain.MajorReport;
import com.bayee.political.json.MajorReportPageQueryParam;
import com.bayee.political.pojo.MajorReportPageResultDO;

import java.util.List;

/**
 * @author tlt
 * @date 2021/11/4
 */
public interface MajorReportService {

    /**
     *重大报告-添加数据
     * @param report
     */
    void addReport(MajorReport report);

    /**
     *重大报告-修改数据
     * @param report
     */
    void updateReport(MajorReport report);

    /**
     * 重大报告-查询详情
     * @param id
     * @return
     */
    MajorReport findById(Integer id);

    /**
     * 重大报告-删除数据
     * @param id
     */
    void deleteReport(Integer id);

    /**
     * 分页查询重大报告
     * @return
     */
    List<MajorReportPageResultDO> majorReportPage(MajorReportPageQueryParam queryParam);

    /**
     * 分页统计
     * @param queryParam
     * @return
     */
    Integer majorReportPageCount(MajorReportPageQueryParam queryParam);
}

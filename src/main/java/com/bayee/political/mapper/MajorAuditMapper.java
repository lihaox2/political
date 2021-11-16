package com.bayee.political.mapper;

import com.bayee.political.domain.MajorAudit;
import com.bayee.political.json.HistoryPageQueryParam;
import com.bayee.political.json.MajorAuditDetailsResult;
import com.bayee.political.json.MajorAuditPageQueryParam;
import com.bayee.political.pojo.MajorReportPageResultDO;

import java.util.List;

public interface MajorAuditMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MajorAudit record);

    int insertSelective(MajorAudit record);

    MajorAudit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MajorAudit record);

    int updateByPrimaryKey(MajorAudit record);

    /**
     * 查询已审核数据
     * @param reportId
     * @return
     */
    List<MajorAuditDetailsResult> selectByReportIdAndStatus(Integer reportId);

    /**
     * 以报告id删除审核
     * @param reportId
     */
    void deleteByReportId(Integer reportId);

    /**
     * 分页查询重大报告
     * @return
     */
    List<MajorReportPageResultDO> majorAuditPage(MajorAuditPageQueryParam queryParam);

    /**
     * 分页统计
     * @param queryParam
     * @return
     */
    Integer majorAuditPageCount(MajorAuditPageQueryParam queryParam);

    /**
     * 分页查询审核历史
     * @return
     */
    List<MajorReportPageResultDO> historyPage(HistoryPageQueryParam queryParam);

    /**
     * 分页统计审核历史
     * @param queryParam
     * @return
     */
    Integer historyPageCount(HistoryPageQueryParam queryParam);
}
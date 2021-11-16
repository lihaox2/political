package com.bayee.political.mapper;

import com.bayee.political.domain.MajorReport;
import com.bayee.political.json.MajorReportPageQueryParam;
import com.bayee.political.pojo.MajorReportPageResultDO;

import java.util.List;

public interface MajorReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MajorReport record);

    int insertSelective(MajorReport record);

    MajorReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MajorReport record);

    int updateByPrimaryKey(MajorReport record);

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
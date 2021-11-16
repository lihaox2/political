package com.bayee.political.mapper;

import com.bayee.political.domain.MajorReportRecord;
import com.bayee.political.json.ReportRecordResult;

import java.util.List;

public interface MajorReportRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MajorReportRecord record);

    int insertSelective(MajorReportRecord record);

    MajorReportRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MajorReportRecord record);

    int updateByPrimaryKey(MajorReportRecord record);

    /**
     * 查询最新上报记录
     * @return
     */
    MajorReportRecord selectByMaxCreationDate(Integer reportId);

    /**
     * 查询所有上报记录
     * @param reportId
     * @return
     */
    List<ReportRecordResult> selectAll(Integer reportId);
}
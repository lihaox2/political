package com.bayee.political.service;

import com.bayee.political.domain.RiskReportRecord;
import org.apache.ibatis.annotations.Param;

/**
 * @author xxl
 * @date 2021/4/6
 */
public interface RiskReportRecordService {

    int updateByPrimaryKey(RiskReportRecord record);

    int insert(RiskReportRecord record);

    /**
     * 查询警员指标历史记录
     * @param policeId
     * @param year
     * @param month
     * @return
     */
    RiskReportRecord findRiskReportRecord(@Param("policeId") String policeId, @Param("year") String year, @Param("month") String month);

}

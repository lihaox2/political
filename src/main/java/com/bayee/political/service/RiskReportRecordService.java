package com.bayee.political.service;

import com.bayee.political.domain.RiskReportRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xxl
 * @date 2021/4/6
 */
public interface RiskReportRecordService {

    int updateByPrimaryKey(RiskReportRecord record);

    int insert(RiskReportRecord record);

    /**
     * 批量添加
     * @param reportRecords
     */
    void insertRiskReportRecordList(List<RiskReportRecord> reportRecords);

    /**
     * 查询警员指标历史记录
     * @param policeId
     * @param year
     * @param month
     * @return
     */
    RiskReportRecord findRiskReportRecord(@Param("policeId") String policeId, @Param("year") String year, @Param("month") String month);

    /**
     * 查询警员报备数据
     * @param year
     * @param month
     * @param policeId
     * @return
     */
    RiskReportRecord getByPoliceIdMonth(String year,String month, String policeId);
    
    
    void health();
    
    void family();

}

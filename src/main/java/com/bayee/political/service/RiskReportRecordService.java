package com.bayee.political.service;

import com.bayee.political.domain.RiskReportRecord;
import com.bayee.political.domain.User;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * @author xxl
 * @date 2021/4/6
 */
public interface RiskReportRecordService {

    /**
     * 警员风险指数处理
     * @param userList
     * @param localDate
     */
    void policeRiskDetails(List<User> userList, LocalDate localDate);

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
    RiskReportRecord findRiskReportRecord(String policeId, String year, String month);

    /**
     * 查询警员报备数据
     * @param year
     * @param month
     * @param policeId
     * @return
     */
    RiskReportRecord getByPoliceIdMonth(String year,String month, String policeId);
    
    
    void health(LocalDate localDate);
    
    void family(LocalDate localDate);

    /**
     * 修改指数数据
     *
     * @param riskReportRecord
     */
    void updateRiskReportRecord(RiskReportRecord riskReportRecord);

}

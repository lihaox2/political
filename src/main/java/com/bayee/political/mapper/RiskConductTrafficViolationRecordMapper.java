package com.bayee.political.mapper;

import java.util.List;

import com.bayee.political.pojo.dto.RiskConductTrafficViolationReportDO;
import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskConductTrafficViolationRecord;

public interface RiskConductTrafficViolationRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskConductTrafficViolationRecord record);

    int insertSelective(RiskConductTrafficViolationRecord record);

    RiskConductTrafficViolationRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConductTrafficViolationRecord record);

    int updateByPrimaryKey(RiskConductTrafficViolationRecord record);
    
    List<RiskConductTrafficViolationRecord> riskConductTrafficViolationRecordList(@Param("policeId")String policeId,@Param("dateTime")String dateTime);

    List<RiskConductTrafficViolationRecord> findRiskConductTrafficViolationRecordList(@Param("policeId") String policeId,
                                                                                      @Param("dateTime") String dateTime,
                                                                                      @Param("lastMonthTime") String lastMonthTime,
                                                                                      @Param("timeType") Integer timeType);

    /**
     * 查询交通违章详情
     * @param policeId
     * @param date
     * @return
     */
    List<RiskConductTrafficViolationRecord> findPoliceRiskConductTrafficViolationRecordList(@Param("policeId")String policeId,
                                                                                      @Param("date")String date);

    /**
     * 交通违章-报表查询
     * @param policeId
     * @param dateTime
     * @param lastMonthTime
     * @param timeType
     * @return
     */
    RiskConductTrafficViolationReportDO trafficViolationReportDOQuery(@Param("policeId") String policeId,
                                                                      @Param("dateTime") String dateTime,
                                                                      @Param("lastMonthTime") String lastMonthTime,
                                                                      @Param("timeType") Integer timeType);

}
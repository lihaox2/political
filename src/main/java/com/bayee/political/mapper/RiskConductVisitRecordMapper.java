package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskConductVisitRecord;

public interface RiskConductVisitRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskConductVisitRecord record);

    int insertSelective(RiskConductVisitRecord record);

    RiskConductVisitRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConductVisitRecord record);

    int updateByPrimaryKey(RiskConductVisitRecord record);
    
    List<RiskConductVisitRecord> riskConductVisitRecordList(@Param("policeId")String policeId,@Param("dateTime")String dateTime,
			@Param("lastMonthTime") String lastMonthTime, @Param("timeType") Integer timeType);

    /**
     * 查询扣分详情
     * @param policeId
     * @param date
     * @return
     */
    List<RiskConductVisitRecord> findRiskConductVisitRecordList(@Param("policeId") String policeId,@Param("date") String date);

}
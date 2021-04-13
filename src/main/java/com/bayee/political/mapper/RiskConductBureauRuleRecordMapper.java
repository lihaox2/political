package com.bayee.political.mapper;

import com.bayee.political.domain.RiskConductBureauRuleRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RiskConductBureauRuleRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskConductBureauRuleRecord record);

    int insertSelective(RiskConductBureauRuleRecord record);

    RiskConductBureauRuleRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConductBureauRuleRecord record);

    int updateByPrimaryKey(RiskConductBureauRuleRecord record);

    /**
     * 查询扣分详情
     * @param policeId
     * @param dateTime
     * @return
     */
    List<RiskConductBureauRuleRecord> findByPoliceIdAndDate(@Param("policeId") String policeId, @Param("dateTime") String dateTime,
			 @Param("lastMonthTime") String lastMonthTime, @Param("timeType") Integer timeType);

    /**
     * 查询扣分详情
     * @param policeId
     * @param date
     * @return
     */
    List<RiskConductBureauRuleRecord> findRiskConductBureauRuleRecord(@Param("policeId") String policeId, @Param("date") String date);
}
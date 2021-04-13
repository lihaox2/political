package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.bayee.political.domain.RiskConductVisit;
import com.bayee.political.domain.ScreenDoubeChart;

public interface RiskConductVisitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskConductVisit record);

    int insertSelective(RiskConductVisit record);

    RiskConductVisit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConductVisit record);

    int updateByPrimaryKey(RiskConductVisit record);
    
    //警员信访投诉风险指数查询
    RiskConductVisit riskConductVisitItem(@Param("policeId") String policeId, @Param("dateTime") String dateTime,
			@Param("lastMonthTime") String lastMonthTime, @Param("timeType") Integer timeType);
    
    // 信访投诉风险指数图例
 	List<ScreenDoubeChart> riskConductVisitChart(@Param("policeId") String policeId);

    /**
     * 查询警员风险指数
     * @param policeId
     * @param date
     * @return
     */
    RiskConductVisit findRiskConductVisit(@Param("policeId") String policeId, @Param("date") String date);

}
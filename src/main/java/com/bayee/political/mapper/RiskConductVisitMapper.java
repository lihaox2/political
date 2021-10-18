package com.bayee.political.mapper;

import java.util.List;

import com.bayee.political.pojo.GlobalIndexNumResultDO;
import com.bayee.political.pojo.RiskReportTypeStatisticsDO;
import com.bayee.political.pojo.dto.RiskConductVisitReportDO;
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

    /**
     * 信访投诉-报表查询
     * @param policeId
     * @param dateTime
     * @param lastMonthTime
     * @param timeType
     * @return
     */
    RiskConductVisitReportDO visitReportDOQuery(@Param("policeId") String policeId,
                                                @Param("dateTime") String dateTime,
                                                @Param("lastMonthTime") String lastMonthTime,
                                                @Param("timeType") Integer timeType);

    /**
     * 信访投诉-扣分类型统计
     * @param policeId
     * @param dateTime
     * @param lastMonthTime
     * @param timeType
     * @return
     */
    List<RiskReportTypeStatisticsDO> conductVisitReportTypeDOQuery(@Param("policeId") String policeId,
                                                                   @Param("dateTime") String dateTime,
                                                                   @Param("lastMonthTime") String lastMonthTime,
                                                                   @Param("timeType") Integer timeType);

    /**
     * 取得全局扣分 的最高分 - 最低分分值
     * @param date
     * @return
     */
    GlobalIndexNumResultDO findGlobalIndexNum(@Param("date") String date);
}
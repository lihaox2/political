package com.bayee.political.mapper;

import java.util.List;

import com.bayee.political.pojo.GlobalIndexNumResultDO;
import com.bayee.political.pojo.dto.RiskCaseLawEnforcementReportDO;
import org.apache.ibatis.annotations.Param;
import com.bayee.political.domain.RiskCaseLawEnforcement;
import com.bayee.political.domain.ScreenDoubeChart;

public interface RiskCaseLawEnforcementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskCaseLawEnforcement record);

    int insertSelective(RiskCaseLawEnforcement record);

    RiskCaseLawEnforcement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskCaseLawEnforcement record);

    int updateByPrimaryKey(RiskCaseLawEnforcement record);
    
    // 警员执法办案风险指数查询
    RiskCaseLawEnforcement riskCaseLawEnforcementItem(@Param("policeId") String policeId, @Param("dateTime") String dateTime);
    
    // 执法办案风险指数图例
 	List<ScreenDoubeChart> riskCaseLawEnforcementChart(@Param("policeId") String policeId);

    // 警员执法管理风险查询
    RiskCaseLawEnforcement riskCaseLawEnforcementIndexItem(@Param("policeId") String policeId,
                                                           @Param("dateTime") String dateTime,
                                                           @Param("lastMonthTime") String lastMonthTime,
                                                           @Param("timeType") Integer timeType);

    /**
     * 查询已产生的警员执法报备数据
     *
     * @param policeId
     * @param date
     * @return
     */
    RiskCaseLawEnforcement findRiskCaseLawEnforcementByPoliceIdAndDate(@Param("policeId") String policeId,
                                                                       @Param("date") String date);

    /**
     * 执法管理-报表生成查询
     * @param policeId
     * @param dateTime
     * @param lastMonthTime
     * @param timeType
     * @return
     */
    RiskCaseLawEnforcementReportDO lawEnforcementReportDOQuery(@Param("policeId") String policeId,
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
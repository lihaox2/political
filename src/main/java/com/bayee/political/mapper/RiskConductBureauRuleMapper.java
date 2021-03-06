package com.bayee.political.mapper;

import com.bayee.political.domain.RiskConductBureauRule;
import com.bayee.political.domain.ScreenDoubeChart;
import com.bayee.political.pojo.GlobalIndexNumResultDO;
import com.bayee.political.pojo.dto.RiskConductBureauRuleReportDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RiskConductBureauRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskConductBureauRule record);

    int insertSelective(RiskConductBureauRule record);

    RiskConductBureauRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConductBureauRule record);

    int updateByPrimaryKey(RiskConductBureauRule record);

    /**
     * 查询局规计分
     * @param policeId
     * @param dateTime
     * @return
     */
    RiskConductBureauRule findRiskConductBureauRole(@Param("policeId") String policeId, @Param("dateTime") String dateTime);

    /**
     * 查询局规计分图形
     * @param policeId
     * @return
     */
    List<ScreenDoubeChart> findRiskConductBureauRoleChart(String policeId);

    /**
     * 批量添加局规计分风险指数数据
     * @param riskConductBureauRuleList
     */
    void insertRiskConductBureauRuleList(List<RiskConductBureauRule> riskConductBureauRuleList);

    // 警员局规计分风险查询
    RiskConductBureauRule riskConductBureauRuleIndexItem(@Param("policeId") String policeId,
                                                         @Param("dateTime") String dateTime, @Param("lastMonthTime") String lastMonthTime,
                                                         @Param("timeType") Integer timeType);

    /**
     * 局规记分-报表查询
     * @param policeId
     * @param dateTime
     * @param lastMonthTime
     * @param timeType
     * @return
     */
    RiskConductBureauRuleReportDO bureauRuleReportDOQuery(@Param("policeId") String policeId,
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
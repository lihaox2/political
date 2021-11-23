package com.bayee.political.mapper;

import com.bayee.political.domain.RiskRelevant;
import com.bayee.political.json.ChartResult;
import com.bayee.political.pojo.GlobalIndexNumResultDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RiskRelevantMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskRelevant record);

    int insertSelective(RiskRelevant record);

    RiskRelevant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskRelevant record);

    int updateByPrimaryKey(RiskRelevant record);

    /**
     * 查询警员动态排摸扣分情况
     * @param policeId
     * @param date
     * @return
     */
    RiskRelevant findByPoliceIdAndDate(@Param("policeId") String policeId, @Param("date") String date);

    /**
     * 取得全局扣分 的最高分 - 最低分分值
     * @param date yyyy-MM-dd
     * @param column
     * @param timeType
     * @return
     */
    GlobalIndexNumResultDO findGlobalIndexNum(@Param("date") String date, @Param("column") String column,
                                              @Param("timeType") Integer timeType);

    /**
     *
     * @param policeId
     * @param dateTime
     * @param timeType
     * @return
     */
    RiskRelevant riskRelevantItem(@Param("policeId") String policeId, @Param("dateTime") String dateTime,
                                  @Param("timeType") Integer timeType);

    /**
     * 查询近六个月风险情况
     * @param policeId
     * @return
     */
    List<ChartResult> riskNearSixMonthChart(String policeId);

}
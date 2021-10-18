package com.bayee.political.mapper;

import java.util.List;

import com.bayee.political.pojo.GlobalIndexNumResultDO;
import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskConduct;
import com.bayee.political.domain.ScreenDoubeChart;

public interface RiskConductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskConduct record);

    int insertSelective(RiskConduct record);

    RiskConduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConduct record);

    int updateByPrimaryKey(RiskConduct record);

    /**
     * 查询警员行为规范数据
     * @param policeId 警员
     * @param dateTime ${yyyy-mm}
     * @return
     */
    RiskConduct findRiskConductByPoliceIdAndDate(@Param("policeId") String policeId, @Param("dateTime") String dateTime);
    
    //警员交通违章风险指数查询
    RiskConduct riskConductItem(@Param("policeId") String policeId, @Param("dateTime") String dateTime, @Param("lastMonthTime") String lastMonthTime,
			@Param("timeType") Integer timeType);
    
    // 交通违章风险指数图例
 	List<ScreenDoubeChart> riskConductChart(@Param("policeId") String policeId);

    /**
     * 批量添加
     * @param riskConductList
     */
 	void insertRiskConductList(List<RiskConduct> riskConductList);

    /**
     * 取得全局扣分 的最高分 - 最低分分值
     * @param date
     * @return
     */
    GlobalIndexNumResultDO findGlobalIndexNum(@Param("date") String date);

    /**
     * 取得全局扣分 的最高分 & 最低分
     * @param lastDateTime
     * @param date
     * @param column
     * @return
     */
    GlobalIndexNumResultDO findGlobalIndexNumByYear(@Param("lastDateTime") String lastDateTime, @Param("date") String date,
                                                    @Param("column") String column);
}
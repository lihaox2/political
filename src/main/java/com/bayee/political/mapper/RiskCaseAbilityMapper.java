package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskCaseAbility;
import com.bayee.political.domain.ScreenDoubeChart;

public interface RiskCaseAbilityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskCaseAbility record);

    int insertSelective(RiskCaseAbility record);

    RiskCaseAbility selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskCaseAbility record);

    int updateByPrimaryKey(RiskCaseAbility record);
    
    // 警员执法办案风险指数查询
    RiskCaseAbility riskCaseAbilityItem(@Param("policeId") String policeId, @Param("dateTime") String dateTime);
    
    // 执法办案风险指数图例
 	List<ScreenDoubeChart> riskCaseAbilityChart(@Param("policeId") String policeId);

    // 警员执法能力风险查询
    RiskCaseAbility riskCaseAbilityIndexItem(@Param("policeId") String policeId, @Param("dateTime") String dateTime,
                                             @Param("lastMonthTime") String lastMonthTime, @Param("timeType") Integer timeType);

    /**
     * 添加执法报备数据
     *
     * @param riskCaseAbility
     */
    void insertRiskCaseAbility(RiskCaseAbility riskCaseAbility);

    /**
     * 通过警号和时间查询警员执法报备数据
     *
     * @param policeId
     * @param date
     * @return
     */
    RiskCaseAbility findRiskCaseAbilityByPoliceIdAndDate(@Param("policeId") String policeId, @Param("date") String date);

    /**
     * 修改执法报备数据
     *
     * @param riskCaseAbility
     */
    void updateRiskCaseAbility(RiskCaseAbility riskCaseAbility);

}
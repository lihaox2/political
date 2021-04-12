package com.bayee.political.mapper;

import java.util.List;

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
                                                           @Param("dateTime") String dateTime, @Param("lastMonthTime") String lastMonthTime,
                                                           @Param("timeType") Integer timeType);
}
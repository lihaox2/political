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
}
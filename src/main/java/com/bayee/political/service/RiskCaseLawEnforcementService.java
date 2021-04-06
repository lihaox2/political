package com.bayee.political.service;

import java.util.List;

import com.bayee.political.domain.RiskCaseLawEnforcement;
import com.bayee.political.domain.ScreenDoubeChart;

public interface RiskCaseLawEnforcementService {

	int deleteByPrimaryKey(Integer id);

    int insert(RiskCaseLawEnforcement record);

    int insertSelective(RiskCaseLawEnforcement record);

    RiskCaseLawEnforcement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskCaseLawEnforcement record);

    int updateByPrimaryKey(RiskCaseLawEnforcement record);// 警员执法办案风险指数查询
    
    RiskCaseLawEnforcement riskCaseLawEnforcementItem(String policeId,String dateTime);
    
    // 执法办案风险指数图例
 	List<ScreenDoubeChart> riskCaseLawEnforcementChart(String policeId);
}

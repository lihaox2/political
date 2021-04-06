package com.bayee.political.service;

import java.util.List;

import com.bayee.political.domain.RiskFamilyEvaluation;
import com.bayee.political.domain.ScreenDoubeChart;

public interface RiskFamilyEvaluationService {

	int deleteByPrimaryKey(Integer id);

    int insert(RiskFamilyEvaluation record);

    int insertSelective(RiskFamilyEvaluation record);

    RiskFamilyEvaluation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskFamilyEvaluation record);

    int updateByPrimaryKey(RiskFamilyEvaluation record);
    
    // 家属评价风险指数查询
    RiskFamilyEvaluation riskFamilyEvaluationItem(String policeId,String dateTime);
    
    // 家属评价风险指数图例
 	List<ScreenDoubeChart> riskFamilyEvaluationChart(String policeId);
}

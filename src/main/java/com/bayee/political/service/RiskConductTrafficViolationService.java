package com.bayee.political.service;

import java.util.List;

import com.bayee.political.domain.RiskConductTrafficViolation;
import com.bayee.political.domain.ScreenDoubeChart;

public interface RiskConductTrafficViolationService {
	
	int deleteByPrimaryKey(Integer id);

    int insert(RiskConductTrafficViolation record);

    int insertSelective(RiskConductTrafficViolation record);

    RiskConductTrafficViolation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConductTrafficViolation record);

    int updateByPrimaryKey(RiskConductTrafficViolation record);
    
    //警员交通违章风险指数查询
    RiskConductTrafficViolation riskConductTrafficViolationItem(String policeId,String dateTime);
    
    // 交通违章风险指数图例
 	List<ScreenDoubeChart> riskConductTrafficViolationChart(String policeId);

}

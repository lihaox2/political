package com.bayee.political.service;

import java.util.List;

import com.bayee.political.domain.RiskConduct;
import com.bayee.political.domain.ScreenDoubeChart;

public interface RiskConductService {
	
	int deleteByPrimaryKey(Integer id);

    int insert(RiskConduct record);

    int insertSelective(RiskConduct record);

    RiskConduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConduct record);

    int updateByPrimaryKey(RiskConduct record);
    
    //警员交通违章风险指数查询
    RiskConduct riskConductItem(String policeId,String dateTime, String lastMonthTime, Integer timeType);
    
    // 交通违章风险指数图例
 	List<ScreenDoubeChart> riskConductChart(String policeId);

    /**
     * 批量添加
     * @param riskConductList
     */
 	void insertRiskConductList(List<RiskConduct> riskConductList);

}

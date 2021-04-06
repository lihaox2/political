package com.bayee.political.service;

import java.util.List;
import com.bayee.political.domain.RiskConductVisit;
import com.bayee.political.domain.ScreenDoubeChart;

public interface RiskConductVisitService {
	
	 int deleteByPrimaryKey(Integer id);

    int insert(RiskConductVisit record);

    int insertSelective(RiskConductVisit record);

    RiskConductVisit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConductVisit record);

    int updateByPrimaryKey(RiskConductVisit record);
    
    //警员信访投诉风险指数查询
    RiskConductVisit riskConductVisitItem(String policeId, String dateTime);
    
    // 信访投诉风险指数图例
 	List<ScreenDoubeChart> riskConductVisitChart(String policeId);

}

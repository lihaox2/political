package com.bayee.political.service;

import java.util.List;

import com.bayee.political.domain.RiskCaseTestRecord;
import com.bayee.political.domain.ScreenDoubeChart;

public interface RiskCaseTestRecordService {
	
	 int deleteByPrimaryKey(Integer id);

    int insert(RiskCaseTestRecord record);

    int insertSelective(RiskCaseTestRecord record);

    RiskCaseTestRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskCaseTestRecord record);

    int updateByPrimaryKey(RiskCaseTestRecord record);
    
    // 警员执法考试风险指数查询
    RiskCaseTestRecord riskCaseTestItem(String policeId,String dateTime);
    
    // 执法考试风险指数图例
 	List<ScreenDoubeChart> riskCaseTestChart(String policeId);

}

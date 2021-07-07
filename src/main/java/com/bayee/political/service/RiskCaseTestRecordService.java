package com.bayee.political.service;

import java.util.List;

import com.bayee.political.domain.RiskCaseTestRecord;
import com.bayee.political.domain.ScreenDoubeChart;

public interface RiskCaseTestRecordService {
	
	 int deleteByPrimaryKey(Integer id);

    int insertSelective(RiskCaseTestRecord record);

    RiskCaseTestRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskCaseTestRecord record);

    int updateByPrimaryKey(RiskCaseTestRecord record);
    
    // 警员执法考试风险指数查询
    RiskCaseTestRecord riskCaseTestItem(String policeId,String dateTime);
    
    // 执法考试风险指数图例
 	List<ScreenDoubeChart> riskCaseTestChart(String policeId);
 	
 	int insertTest(RiskCaseTestRecord record);
	
	Integer isExistence( String policeId,String year, Integer semester, Integer id);

    /**
     * 分页查询执法考试数据
     * @param pageIndex
     * @param pageSize
     * @param date
     * @param passFlag
     * @param key
     * @return
     */
	List<RiskCaseTestRecord> riskCaseTestRecordPage(Integer pageIndex, Integer pageSize,String date,
                                                    Integer passFlag,String key, Integer deptId);

    /**
     * 统计数据条数
     * @param date
     * @param passFlag
     * @param key
     * @return
     */
	Integer riskCaseTestRecordPageCount(String date,Integer passFlag,String key, Integer deptId);

}

package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.bayee.political.domain.RiskCaseTestRecord;
import com.bayee.political.domain.ScreenDoubeChart;

public interface RiskCaseTestRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insertTest(RiskCaseTestRecord record);

    int insertSelective(RiskCaseTestRecord record);

    RiskCaseTestRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskCaseTestRecord record);

    int updateByPrimaryKey(RiskCaseTestRecord record);
    
    // 警员执法考试风险指数查询
    RiskCaseTestRecord riskCaseTestItem(@Param("policeId") String policeId, @Param("dateTime") String dateTime);
    
    // 执法考试风险指数图例
 	List<ScreenDoubeChart> riskCaseTestChart(@Param("policeId") String policeId);
 	
 	Integer isExistence(@Param("policeId") String policeId, @Param("year") String year,
			@Param("semester") Integer semester);
 	
 	/**
	 * 查询警员执法数据
	 * 
	 * @param policeId 警号
	 * @param date     时间
	 * @param semester 学期
	 * @return
	 */
	RiskCaseTestRecord findPoliceCaseData(@Param("policeId") String policeId, @Param("date") String date,
												@Param("semester") Integer semester);

	// 警员执法考试数据列表查询
	List<RiskCaseTestRecord> riskCaseTestRecordList(@Param("policeId") String policeId,
													@Param("dateTime") String dateTime, @Param("lastMonthTime") String lastMonthTime,
													@Param("timeType") Integer timeType);
}
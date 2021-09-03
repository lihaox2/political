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
			@Param("semester") Integer semester,@Param("id") Integer id);
 	
 	/**
	 * 查询警员执法数据
	 * 
	 * @param policeId 警号
	 * @param date     时间
	 * @param semester 学期
	 * @return
	 */
	List<RiskCaseTestRecord> findPoliceCaseData(@Param("policeId") String policeId, @Param("date") String date);

	// 警员执法考试数据列表查询
	List<RiskCaseTestRecord> riskCaseTestRecordList(@Param("policeId") String policeId,
													@Param("dateTime") String dateTime, @Param("lastMonthTime") String lastMonthTime,
													@Param("timeType") Integer timeType);

	/**
	 * 分页查询执法考试数据
	 * @param pageIndex
	 * @param pageSize
	 * @param date
	 * @param passFlag
	 * @param key
	 * @return
	 */
	List<RiskCaseTestRecord> riskCaseTestRecordPage(@Param("pageIndex") Integer pageIndex,
													@Param("pageSize") Integer pageSize, @Param("date") String date,
													@Param("passFlag") Integer passFlag, @Param("key") String key,
													@Param("deptId") Integer deptId);

	/**
	 * 统计数据条数
	 * @param date
	 * @param passFlag
	 * @param key
	 * @return
	 */
	Integer riskCaseTestRecordPageCount(@Param("date") String date, @Param("passFlag") Integer passFlag,
										@Param("key") String key, @Param("deptId") Integer deptId);

	/**
	 * 根据年份和月份进行查询
	 * @param caseTestRecordYear
	 * @param caseTestRecordMonth
	 * @param policeId
	 * @return
	 */
	List<RiskCaseTestRecord> findCaseTestRecordYearAndMonth(
			@Param("caseTestRecordYear") String caseTestRecordYear,
			@Param("caseTestRecordMonth") String caseTestRecordMonth,
			@Param("policeId") String policeId
	);

	/**
	 * 统计警员平均考试成绩
	 * @param policeId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	Integer riskCaseTestScoreAvg(@Param("policeId") String policeId, @Param("beginDate") String beginDate,
								 @Param("endDate") String endDate);
}
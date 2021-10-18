package com.bayee.political.mapper;

import java.util.List;
import java.util.Map;

import com.bayee.political.json.ChartResult;
import com.bayee.political.pojo.GlobalIndexNumResultDO;
import com.bayee.political.pojo.RiskHistoryYearListResultDO;
import com.bayee.political.pojo.dto.RiskReportRecordDO;
import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskHistoryReport;
import com.bayee.political.domain.RiskHistoryReportTime;
import com.bayee.political.domain.RiskIndexMonitorChild;
import com.bayee.political.domain.RiskReportRecord;
import com.bayee.political.domain.ScreenDoubeChart;

public interface RiskReportRecordMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(RiskReportRecord record);

	int insertSelective(RiskReportRecord record);

	RiskReportRecord selectByPrimaryKey(Integer id);

	int updateByPrimaryKey(RiskReportRecord record);

	/**
	 * 查询警员指标历史记录
	 * @param policeId
	 * @param year
	 * @param month
	 * @return
	 */
	RiskReportRecord findRiskReportRecord(@Param("policeId") String policeId,@Param("year") String year,@Param("month") String month);

	// 警员风险分页查询
	List<RiskReportRecord> riskPageList(@Param("keyWords") String keyWords, @Param("alarmType") Integer alarmType,
			@Param("sortName") String sortName, @Param("dateTime") String dateTime,
			@Param("lastDateTime") String lastDateTime, @Param("lastMonthTime") String lastMonthTime,
			@Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum,
										@Param("num")Integer num, @Param("orderName") String orderName,
										@Param("deptId") Integer deptId);

	// 警员风险列表总数
	int riskPageCount(@Param("keyWords") String keyWords, @Param("alarmType") Integer alarmType,
			@Param("dateTime") String dateTime, @Param("lastDateTime") String lastDateTime,
			@Param("lastMonthTime") String lastMonthTime,@Param("num")Integer num,@Param("orderName") String orderName,
					  @Param("deptId") Integer deptId);

	// 警员风险雷达图
	List<ScreenDoubeChart> riskChartList(@Param("policeId") String policeId, @Param("dateTime") String dateTime,
										 @Param("lastMonthTime") String lastMonthTime, @Param("timeType") Integer timeType);

	// 警员风险详情查询
	RiskReportRecord riskReportRecordItem(@Param("id") Integer id, @Param("policeId") String policeId,
										  @Param("dateTime") String dateTime, @Param("lastDateTime") String lastDateTime,
										  @Param("lastMonthTime") String lastMonthTime, @Param("timeType") Integer timeType);

	// 综合指数风险
	RiskIndexMonitorChild comprehensiveIndex(@Param("dateTime") String dateTime,@Param("num")Integer num);

	// 行为规范风险
	RiskIndexMonitorChild conductIndex(@Param("dateTime") String dateTime,@Param("num")Integer num);

	// 执法办案风险
	RiskIndexMonitorChild caseIndex(@Param("dateTime") String dateTime,@Param("num")Integer num);

	// 接警执勤风险
	RiskIndexMonitorChild dutyIndex(@Param("dateTime") String dateTime,@Param("num")Integer num);

	// 警务技能风险
	RiskIndexMonitorChild trainIndex(@Param("dateTime") String dateTime,@Param("num")Integer num);

	// 社交风险
	RiskIndexMonitorChild socialContactIndex(@Param("dateTime") String dateTime,@Param("num")Integer num);

	// 家属评价风险
	RiskIndexMonitorChild familyEvaluationIndex(@Param("dateTime") String dateTime,@Param("num")Integer num);

	// 健康风险
	RiskIndexMonitorChild healthIndex(@Param("year") String year, @Param("dateTime") String dateTime,@Param("num")Integer num);

	// 警员历史风险报告查询
	List<RiskHistoryReport> riskHistoryReportList(@Param("policeId") String policeId,
			@Param("dateTime") String dateTime);

	// 警员警员指标历史记录新增
	int riskReportRecordCreat(RiskReportRecord record);
	
	RiskReportRecord getByPoliceIdMonth(@Param("year") String year, @Param("month") String month,
			@Param("policeId") String policeId);
	
	/**
	 * 批量添加报备数据
	 * 
	 * @param riskReportRecordList
	 */
	void insertRiskReportRecords(List<RiskReportRecord> riskReportRecordList);

	// 警员警员指标历史记录修改
	int riskReportRecordUpdate(RiskReportRecord record);
	
	List<RiskHistoryReportTime> riskHistoryReportTimeList(@Param("policeId") String policeId);
	
	Double findPoliceHealthScoreByYear(@Param("policeId") String policeId,@Param("year") String year);
	
	Double findNotFamilyTotalNum(@Param("id")Integer id);

	/**
	 * 根据警号和时间
	 * @param policeId
	 * @param date
	 * @return
	 */
	RiskReportRecordDO findRiskReportByPoliceIdToMonth(@Param("policeId") String policeId, @Param("date") String date);

	/**
	 * 查询警员所有月份
	 * @param police
	 * @param year
	 * @return
	 */
	List<ChartResult> findPoliceAllRiskMonth(@Param("police") String police, @Param("year") String year);

	/**
	 * 查询警员近12个月详情信息
	 * @param policeId
	 * @param date
	 * @param lastDate
	 * @return
	 */
	RiskReportRecordDO findRiskReportByPoliceIdToYear(@Param("policeId") String policeId, @Param("date") String date,
													  @Param("lastDate") String lastDate);

	/**
	 * 警员风险报告-年度查询
	 * @param policeId
	 * @return
	 */
	List<RiskHistoryYearListResultDO> riskHistoryYearList(String policeId);

	/**
	 * 取得全局扣分 的最高分 - 最低分分值
	 * @param date
	 * @return
	 */
	GlobalIndexNumResultDO findRiskReportRecordGlobalIndexNum(@Param("date") String date);

	/**
	 * 取得全局扣分 的最高分 - 最低分分值
	 * @param date
	 * @return
	 */
	GlobalIndexNumResultDO findRiskReportRecordGlobalTotalNum(@Param("date") String date);
	
	/**
	 * 取得全局扣分 的最高分 & 最低分分值
	 * @param date
	 * @return
	 */
	GlobalIndexNumResultDO findGlobalIndexNumByYear(@Param("lastDateTime") String lastDateTime, @Param("date") String date,
											  		@Param("column") String column);
}
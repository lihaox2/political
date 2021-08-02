package com.bayee.political.service;

import java.util.List;

import com.bayee.political.domain.*;
import com.bayee.political.pojo.dto.*;
import org.springframework.stereotype.Service;

/**
 * @author shentuqiwei
 * @version 2021年3月26日 上午10:07:30
 */
@Service
public interface RiskService {
	
	int insertSelective(RiskHealth record);

	// 警员健康风险指数新增
	int riskHealthCreat(RiskHealth record);

	// 警员健康风险指数修改
	int riskHealthUpdate(RiskHealth record);

	// 警员健康风险指数查询
	RiskHealth riskHealthIndexItem(String policeId, String dateTime);

	// 警员预警分页查询
	List<RiskAlarm> riskAlarmPageList(String startTime, String endTime, Integer pageSize, Integer pageNum,String dateTime);

	// 警员预警列表总数
	int riskAlarmPageCount(String startTime, String endTime,String dateTime);

	// 警员风险分页查询
	List<RiskReportRecord> riskPageList(String keyWords, Integer alarmType, String sortName, String dateTime,
			String lastDateTime, String lastMonthTime, Integer pageSize, Integer pageNum,Integer num,
										String orderName, Integer deptId);

	// 警员风险列表总数
	int riskPageCount(String keyWords, Integer alarmType, String dateTime, String lastDateTime, String lastMonthTime,
					  Integer num,String orderName, Integer deptId);

	// 警员预警类型查询
	List<RiskAlarmType> riskAlarmTypeList(Integer id);

	// 警员风险雷达图
	List<ScreenDoubeChart> riskChartList(String policeId, String dateTime, String lastMonthTime, Integer timeType);

	// 警员风险详情查询
	RiskReportRecord riskReportRecordItem(Integer id, String policeId, String dateTime, String lastDateTime,
										  String lastMonthTime, Integer timeType);

	// 警员警务技能指数查询
	RiskTrain riskTrainIndexItem(String policeId, String dateTime, String lastMonthTime, Integer timeType);

	// 警员综合训练不合格趋势图
	List<ScreenChart> riskTrainFailChart(String policeId, String fieldName);

	// 警员接警执勤指数查询
	RiskDuty riskDutyIndexItem(String policeId, String dateTime, String lastMonthTime, Integer timeType);

	// 半年内接警执勤风险指数
	List<ScreenDoubeChart> riskDutyIndexChart(String policeId);

	// 警员接警执勤数据列表查询
	List<RiskDutyDealPoliceRecord> riskDutyRecordList(String policeId, String dateTime, String lastMonthTime,
													  Integer timeType);


	List<RiskConductTrafficViolationRecord> riskConductTrafficViolationRecordList(String policeId, String dateTime,
																				  String lastMonthTime, Integer timeType);

	// 警员执法办案风险指数查询
	RiskCase riskCaseIndexItem(String policeId, String dateTime, String lastMonthTime, Integer timeType);

	// 执法办案风险指数图例
	List<ScreenDoubeChart> riskCaseIndexChart(String policeId);

	// 综合指数风险
	RiskIndexMonitorChild comprehensiveIndex(String dateTime,Integer num);

	// 行为规范风险
	RiskIndexMonitorChild conductIndex(String dateTime,Integer num);

	// 执法办案风险
	RiskIndexMonitorChild caseIndex(String dateTime,Integer num);

	// 接警执勤风险
	RiskIndexMonitorChild dutyIndex(String dateTime,Integer num);

	// 警务技能风险
	RiskIndexMonitorChild trainIndex(String dateTime,Integer num);

	// 社交风险
	RiskIndexMonitorChild socialContactIndex(String dateTime,Integer num);

	// 家属评价风险
	RiskIndexMonitorChild familyEvaluationIndex(String dateTime,Integer num);

	// 健康风险
	RiskIndexMonitorChild healthIndex(String year, String dateTime,Integer num);

	// 警员历史风险报告查询
	List<RiskHistoryReport> riskHistoryReportList(String policeId, String dateTime);

	// 警员警务技能统计查询
	RiskTrain riskTrainStatisticsItem(String policeId, String dateTime);

	// 警员警务技能新增
	int riskTrainCreat(RiskTrain record);

	// 警员警务技能修改
	int riskTrainUpdate(RiskTrain record);

	// 警员警员指标历史记录新增
	int riskReportRecordCreat(RiskReportRecord record);

	// 警员社交风险查询
	RiskSocialContact riskSocialContactIndexItem(String policeId, String dateTime, String lastMonthTime, Integer timeType);

	// 社交风险指数图例
	List<ScreenDoubeChart> riskSocialContactIndexChart(String policeId, String tableName);

	// 警员最新一条健康风险指数查询
	RiskHealth riskHealthIndexNewestItem(String policeId);

	// 社交详情记录
	List<RiskSocialContactRecord> riskSocialContactRecordList(Integer socialContactId);

	// 警员执法管理风险查询
	RiskCaseLawEnforcement riskCaseLawEnforcementIndexItem(String policeId, String dateTime, String lastMonthTime,
														   Integer timeType);

	// 警员执法管理数据列表查询
	List<RiskCaseLawEnforcementRecord> riskCaseLawEnforcementRecordList(String policeId, String dateTime,
																		String lastMonthTime, Integer timeType);

	// 警员执法能力风险查询
	RiskCaseAbility riskCaseAbilityIndexItem(String policeId, String dateTime, String lastMonthTime, Integer timeType);

	// 警员执法考试风险查询
	RiskCaseTest riskCaseTestIndexItem(String policeId, String dateTime, String lastMonthTime, Integer timeType);

	// 警员执法考试数据列表查询
	List<RiskCaseTestRecord> riskCaseTestRecordList(String policeId, String dateTime, String lastMonthTime,
													Integer timeType);

	// 警员局规计分风险查询
	RiskConductBureauRule riskConductBureauRuleIndexItem(String policeId, String dateTime, String lastMonthTime,
														 Integer timeType);

	/**
	 * 查询局规计分
	 * @param policeId
	 * @param dateTime
	 * @param lastDateTime
	 * @return
	 */
	RiskConductBureauRoleResultDTO riskConductBureauRole(String policeId, String dateTime, String lastDateTime);

	/**
	 * 查询局规计分图形
	 * @param policeId
	 * @return
	 */
	List<ScreenDoubeChart> riskConductBureauRoleChart(String policeId);

	/**
	 * 查询具体扣分详情
	 * @param policeId
	 * @param dateTime
	 * @return
	 */
	List<RiskConductBureauRuleRecord> findRiskConductBureauRuleRecord(String policeId, String dateTime,String lastMonthTime,Integer timeType);
	
	int insertInpromt(RiskDrinkRecord riskDrinkRecord);
	
	List<RiskHealth> getByYear(String year);
	
	Double fraction(Integer id);
	
	int updateRiskReportRecord(RiskReportRecord record);
	
	List<RiskHistoryReportTime> riskHistoryReportTimeList(String policeId);
	
	Integer getByIdAndYear(String policeId, String year);
	
	Double selectTotalNum(Integer id);
	
	Integer insertRiskReportRecord(RiskReportRecord record);
	
	List<String> getAllByYear(String year);

	/**
	 * 执法能力-报表查询
	 * @param policeId
	 * @param dateTime
	 * @param lastMonthTime
	 * @param timeType
	 * @return
	 */
	RiskCaseLawEnforcementReportDO lawEnforcementReportDOQuery(String policeId, String dateTime, String lastMonthTime,
															   Integer timeType);

	/**
	 * 信访投诉-报表查询
	 * @param policeId
	 * @param dateTime
	 * @param lastMonthTime
	 * @param timeType
	 * @return
	 */
	RiskConductVisitReportDO visitReportDOQuery(String policeId, String dateTime, String lastMonthTime,
												Integer timeType);

	/**
	 * 局规记分-报表查询
	 * @param policeId
	 * @param dateTime
	 * @param lastMonthTime
	 * @param timeType
	 * @return
	 */
	RiskConductBureauRuleReportDO bureauRuleReportDOQuery(String policeId, String dateTime, String lastMonthTime,
														  Integer timeType);

	/**
	 * 交通违章-报表查询
	 * @param policeId
	 * @param dateTime
	 * @param lastMonthTime
	 * @param timeType
	 * @return
	 */
	RiskConductTrafficViolationReportDO trafficViolationReportDOQuery(String policeId, String dateTime,
																	  String lastMonthTime, Integer timeType);

	/**
	 * 接警执勤-报表查询
	 * @param policeId
	 * @param dateTime
	 * @param lastMonthTime
	 * @param timeType
	 * @return
	 */
	RiskDutyReportDO dutyReportDOQuery(String policeId, String dateTime, String lastMonthTime, Integer timeType);

	/**
	 * 健康风险-报表查询
	 * @param policeId
	 * @return
	 */
	RiskHealthReportDO healthReportDOQuery(String policeId);

}

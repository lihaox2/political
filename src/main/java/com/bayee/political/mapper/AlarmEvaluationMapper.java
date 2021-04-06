package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.bayee.political.domain.AlarmConfig;
import com.bayee.political.domain.AlarmEvaluation;
import com.bayee.political.domain.AlarmLeaderStatistics;
import com.bayee.political.domain.AlarmNewest;
import com.bayee.political.domain.AlarmPersonalEvaluation;
import com.bayee.political.domain.AlarmPersonalStatistics;
import com.bayee.political.domain.AlarmPoliceScoreAnalysis;
import com.bayee.political.domain.AlarmRecordChart;
import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.DateTimeItem;
import com.bayee.political.domain.LeaveChart;
import com.bayee.political.domain.ReportDetails;

public interface AlarmEvaluationMapper {

	/**
	 * 根据id删除
	 * 
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 添加考评
	 * 
	 * @param record
	 * @return
	 */
	int insert(AlarmEvaluation record);

	/**
	 * 批量添加考评
	 * 
	 * @param record
	 * @return
	 */
	int insertMore(@Param("alarmEvaluationList") List<AlarmEvaluation> alarmEvaluationList);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	AlarmEvaluation selectByPrimaryKey(Integer id);

	/**
	 * 根据id修改(只修改属性不为null的)
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(AlarmEvaluation record);

	/**
	 * 根据id修改(null属性也修改)
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(AlarmEvaluation record);

	/**
	 * (scoreItems必填 为记分项目分类)查询所有或根据记分细目id、 记分子细目id、记分日期、警号、被记分人、记分查询
	 * 
	 * @return
	 */
	List<AlarmEvaluation> selectAllOrByCondition(@Param("scoreItems") Integer scoreItems,
			@Param("scoringBreakdownId") Integer scoringBreakdownId,
			@Param("scoringSonBreakdownId") Integer scoringSonBreakdownId, @Param("scoringDate") String scoringDate,
			@Param("keywords") String keywords, @Param("departmentId") Integer departmentId,
			@Param("searchDepId") Integer searchDepId, @Param("pageSize") Integer pageSize);

	/**
	 * (scoreItems必填 为记分项目分类)查询所有条数或根据记分细目id、 记分子细目id、记分日期、警号、被记分人、记分查询的来的条数
	 * 
	 * @return
	 */
	Integer selectAllOrByConditionCount(@Param("scoreItems") Integer scoreItems,
			@Param("scoringBreakdownId") Integer scoringBreakdownId,
			@Param("scoringSonBreakdownId") Integer scoringSonBreakdownId, @Param("scoringDate") String scoringDate,
			@Param("keywords") String keywords, @Param("departmentId") Integer departmentId,
			@Param("searchDepId") Integer searchDepId);

	/**
	 * 本月参与总人数
	 * 
	 * @return
	 */
	Integer theMonthTotal(@Param("departmentId") Integer departmentId);

	/**
	 * 本月总人数新增
	 * 
	 * @return
	 */
	Integer theMonthNewAddTotal(@Param("departmentId") Integer departmentId);

	/**
	 * 本月扣分人数
	 */
	Integer theMonthPointsDeductedNum(@Param("departmentId") Integer departmentId);

	/**
	 * 本月新增扣分人数
	 */
	Integer theMonthNewAddPointsDeductedNum(@Param("departmentId") Integer departmentId);

	/**
	 * 本月加分人数
	 */
	Integer theMonthBonusPointsNum(@Param("departmentId") Integer departmentId);

	/**
	 * 本月新增加分人数
	 */
	Integer theMonthNewAddBonusPointsNum(@Param("departmentId") Integer departmentId);

	/**
	 * 本月加分预警人数
	 * 
	 * @return
	 */
	Integer theMonthBonusPointsAlarmNum(@Param("departmentId") Integer departmentId, @Param("type") Integer type);

	/**
	 * 本月新增加分预警人数
	 */
	Integer theMonthNewAddBonusPointsAlarmNum(@Param("departmentId") Integer departmentId, @Param("type") Integer type);

	/**
	 * 本月扣分预警人数
	 * 
	 * @return
	 */
	Integer theMonthPointsDeductedAlarmNum(@Param("departmentId") Integer departmentId, @Param("type") Integer type);

	/**
	 * 本月新增扣分预警人数
	 */
	Integer theMonthNewAddPointsDeductedAlarmNum(@Param("departmentId") Integer departmentId,
			@Param("type") Integer type);

	/**
	 * 本月未上传指标
	 * 
	 * @return
	 */
	List<String> theMonthNotUpload(@Param("scoringPoliceId") String scoringPoliceId);

	/**
	 * 记分总指标数量
	 * 
	 * @return
	 */
	Integer totalScoreTargetNum();

	/**
	 * 1扣分指标数量/2加分指标数量/3中性指标数量
	 * 
	 * @return
	 */
	Integer scoreTargetNum(@Param("number") Integer number);

	/**
	 * 根据scoringPoliceId查询记分审核
	 * 
	 * @return
	 */
	List<AlarmEvaluation> review(@Param("scoringPoliceId") Integer scoringPoliceId);

	/**
	 * 根据thresholdType阈值类型修改预警分配置
	 * 
	 * @return
	 */
	Integer updateAlarmConfig(@Param("thresholdType") Integer thresholdType, @Param("score") Double score,
			@Param("type") Integer type);

	/**
	 * 月度预警报表
	 * 
	 * @param departmentName
	 * @return
	 */
	List<AlarmEvaluation> getMonthAlarmReport(@Param("departmentName") String departmentName,
			@Param("keywords") String keywords, @Param("ids") String ids, @Param("pageSize") Integer pageSize);

	/**
	 * 月度预警报表数量
	 * 
	 * @return
	 */
	Integer getMonthAlarmReportCount(@Param("departmentName") String departmentName, @Param("ids") String ids,
			@Param("keywords") String keywords);

	/**
	 * 季度预警报表
	 * 
	 * @param departmentName
	 * @return
	 */
	List<AlarmEvaluation> getQuarterAlarmReport(@Param("departmentName") String departmentName,
			@Param("keywords") String keywords, @Param("ids") String ids, @Param("pageSize") Integer pageSize);

	/**
	 * 季度预警报表数量
	 * 
	 * @return
	 */
	Integer getQuarterAlarmReportCount(@Param("departmentName") String departmentName, @Param("ids") String ids,
			@Param("keywords") String keywords);

	/**
	 * 全部预警报表
	 * 
	 * @param departmentName
	 * @return
	 */
	List<AlarmEvaluation> getAllAlarmReport(@Param("departmentName") String departmentName,
			@Param("keywords") String keywords, @Param("ids") String ids, @Param("pageSize") Integer pageSize);

	/**
	 * 全部预警报表数量
	 * 
	 * @return
	 */
	Integer getaAllAlarmReportCount(@Param("departmentName") String departmentName, @Param("keywords") String keywords,
			@Param("ids") String ids);

	/**
	 * 预警报表中的单位类型
	 * 
	 * @return
	 */
	List<AlarmEvaluation> getUnitType();

	/**
	 * 待办事项(当前登录者)
	 * 
	 * @param scoredPoliceId
	 * @return
	 */
	List<AlarmEvaluation> waitMatter(@Param("scoringPoliceId") String scoringPoliceId,
			@Param("pageSize") Integer pageSize);

	/**
	 * 已办事项(当前登录者)
	 * 
	 * @param scoredPoliceId
	 * @return
	 */
	List<AlarmEvaluation> doneMatter(@Param("scoringPoliceId") String scoringPoliceId,
			@Param("pageSize") Integer pageSize);

	/**
	 * 待办事项数量(当前登录者)
	 * 
	 * @param scoredPoliceId
	 * @return
	 */
	Integer waitMatterCount(@Param("scoringPoliceId") String scoringPoliceId);

	/**
	 * 已办事项数量(当前登录者)
	 * 
	 * @param scoredPoliceId
	 * @return
	 */
	Integer doneMatterCount(@Param("scoringPoliceId") String scoringPoliceId);

	/**
	 * 待办事项 同意或拒绝
	 * 
	 * @param mark 1同意 2拒绝
	 * @return
	 */
	Integer waitMatterMark(@Param("mark") Integer mark, @Param("id") Integer id);

	/**
	 * 查询预警配置
	 * 
	 * @return
	 */
	List<AlarmConfig> getAlarmConfig();

	/**
	 * 记分提醒
	 * 
	 * @return
	 */
	List<String> scoreTip(@Param("scoringPoliceId") String scoringPoliceId);

	/**
	 * 月年度正负+中面考评分数
	 * 
	 * @param scoredPoliceId 被记分人
	 * @param type           1负面考评2正面考评
	 * @param configTimeType 1本月分数 2本年分数
	 * @return
	 */
	Double evaluationScoreByCondition(@Param("scoredPoliceId") String scoredPoliceId, @Param("type") Integer type,
			@Param("configTimeType") Integer configTimeType);

	/**
	 * 月年度正负面加中性的考评预警分数
	 * 
	 * @param configTimeType 1本月分数 2本年分数
	 * @param threshold      1负面考评分数+中性考评 2:正面考评分数+中性考评
	 * @return
	 */
	Double alarmScoreByCondition(@Param("configTimeType") Integer configTimeType,
			@Param("threshold") Integer threshold);

	/**
	 * 最新两条负面记分
	 * 
	 * @return
	 */
	List<AlarmEvaluation> newestScoring(@Param("scoringPoliceId") String scoringPoliceId);

	/**
	 * 根据月度或季度获得报表详情
	 * 
	 * @param conditionType  条件类型 1为月度 2为季度
	 * @param condition      月度或季度的参数值
	 * @param departmentName
	 * @return
	 */
	List<ReportDetails> getReportDetails(@Param("conditionType") Integer conditionType,
			@Param("condition") Integer condition, @Param("year") Integer year,
			@Param("departmentId") Integer departmentId, @Param("pageSize") Integer pageSize);

	/**
	 * 根据月度或季度获得报表详情数量
	 * 
	 * @param conditionType  条件类型 1为月度 2为季度
	 * @param condition      月度或季度的参数值
	 * @param departmentName
	 * @return
	 */
	Integer getReportDetailsCount(@Param("conditionType") Integer conditionType, @Param("condition") Integer condition,
			@Param("year") Integer year, @Param("departmentId") Integer departmentId);

	/**
	 * 获取的导出的Excel标题
	 * 
	 * @param scoreItem
	 * @return
	 */
	String getExportExcelTitle(@Param("scoreItem") Integer scoreItem);

	/**
	 * 本周考核预警事件数量(所有预警)
	 * 
	 * @return
	 */
	Integer alarmNumWeek();

	/**
	 * 本周出入境预警数
	 * 
	 * @return
	 */
	@Select("select count(*) as count from alarm_entry_and_exit_record where YEARWEEK(date_format(departure_time,'%Y-%m-%d')) = YEARWEEK(now())")
	Integer alarmEntryExitNumWeek();

	/**
	 * 本周约谈完成数
	 * 
	 * @return
	 */
	@Select("select count(*) as count from alarm_talk where `status` = 5 and YEARWEEK(date_format(end_time,'%Y-%m-%d')) = YEARWEEK(now())")
	Integer alarmTalkComplateNumWeek();

	// 修改预约评价记录
	int alarmEvaluationupdate(AlarmEvaluation alarmEvaluation);

	// 个人预警统计
	AlarmPersonalStatistics alarmPersonalStatisticsItem(@Param("policeId") String policeId);

	// 个人最新记分项数量统计（最近一周）
	int alarmPersonalNotConfirmNum(@Param("policeId") String policeId);

	// 个人最新记分项查询（最近一周）
	List<AlarmEvaluation> alarmPersonalNotConfirmList(@Param("policeId") String policeId);

	// 预警评价记录详情查询
	AlarmEvaluation alarmEvaluationItem(@Param("id") Integer id);

	// 个人考评表数量查询
	int alarmPersonalEvaluationNum(@Param("policeId") String policeId);

	// 个人考评报表列表查询
	List<AlarmPersonalEvaluation> alarmPersonalEvaluationList(@Param("policeId") String policeId,
			@Param("dateTime") String dateTime);

	// 个人考评报表历史扣分记录查询
	List<AlarmEvaluation> alarmPersonalEvaluationHistoryBuckleList(@Param("policeId") String policeId,
			@Param("dateTime") String dateTime, @Param("policeMonthId") Integer policeMonthId,
			@Param("type") Integer type);

	// 个人考评报表历史加分记录查询
	List<AlarmEvaluation> alarmPersonalEvaluationHistoryAddList(@Param("policeId") String policeId,
			@Param("dateTime") String dateTime, @Param("policeMonthId") Integer policeMonthId,
			@Param("type") Integer type);

	// 个人最近加扣分项查询(当前月)
	List<LeaveChart> alarmEvaluationLastScoreList(@Param("policeId") String policeId);

	// 个人加分趋势查询(近一年)
	List<LeaveChart> alarmAddScoreList(@Param("policeId") String policeId);

	// 个人扣分趋势查询(近一年)
	List<LeaveChart> alarmBuckleScoreList(@Param("policeId") String policeId);

	// 个人考评维度加扣分占比
	List<LeaveChart> alarmPersonalEvaluationPieList(@Param("policeId") String policeId);

	// 个人正面扣分项目占比
	List<LeaveChart> alarmAddProjectList(@Param("policeId") String policeId, @Param("type") Integer type);

	// 个人负面扣分项目占比
	List<LeaveChart> alarmBuckleProjectList(@Param("policeId") String policeId, @Param("type") Integer type);

	// 个人考评年份查询
	List<CalculationChart> alarmYearsList(@Param("policeId") String policeId);

	// 局领导当前年预警人数统计
	AlarmLeaderStatistics alarmLeaderStatistics(@Param("policeId") String policeId);

	// 局领导上个月预警人数统计
	AlarmLeaderStatistics alarmLastLeaderStatistics(@Param("policeId") String policeId);

	// 局领导约预警加分人数趋势图
	List<CalculationChart> alarmLeaderLineAddChart(@Param("policeId") String policeId);

	// 局领导考核预警趋势图
	List<CalculationChart> alarmLeaderLineBuckleChart(@Param("policeId") String policeId);

	// 局领导扣分人数趋势图
	List<CalculationChart> alarmLeaderTypeBuckleChart(@Param("policeId") String policeId);

	// 局领导加分人数趋势图
	List<CalculationChart> alarmLeaderTypeAddChart(@Param("policeId") String policeId);

	// 局领导最新预警统计
	AlarmLeaderStatistics alarmLeaderNewestItem(@Param("policeId") String policeId);

	// 查询民警预警最新时间
	AlarmEvaluation alarmNewestTime(@Param("scoredPoliceId") String scoredPoliceId);

	// 局领导约谈对象民警查询
	List<AlarmNewest> alarmLeaderTalkObjectList(@Param("scoredPoliceId") String scoredPoliceId,
			@Param("scoringPoliceId") String scoringPoliceId);

	// 局领导预警列表查询
	List<AlarmNewest> alarmList(@Param("policeId") String policeId, @Param("type") Integer type,
			@Param("dateTime") String dateTime);

	// 局领导扣分预警详情
	List<AlarmRecordChart> alarmGetScoreBuckleItem(@Param("policeId") String policeId,
			@Param("alarmConfigType") Integer alarmConfigType, @Param("dateTime") String dateTime);

	// 局领导加分预警详情
	List<AlarmRecordChart> alarmGetScoreAddItem(@Param("policeId") String policeId,
			@Param("alarmConfigType") Integer alarmConfigType, @Param("dateTime") String dateTime);

	// 局领导扣分最大值查询
	List<LeaveChart> alarmBuckleMaxList(@Param("policeId") String policeId);

	// 局领导加分最大值查询
	List<LeaveChart> alarmAddMaxList(@Param("policeId") String policeId);

	// 查询当前季度月度时间
	DateTimeItem dateItems();

	// 扣分型排行榜
	List<AlarmNewest> alarmBuckleRankList(@Param("policeId") String policeId, @Param("startTime") String startTime,
			@Param("endTime") String endTime);

	// 加分型排行榜
	List<AlarmNewest> alarmAddRankList(@Param("policeId") String policeId, @Param("startTime") String startTime,
			@Param("endTime") String endTime);

	// 局领导约谈对象预警民警查询
	List<AlarmNewest> alarmLeaderTalkConditionList(@Param("scoredPoliceId") String scoredPoliceId,
			@Param("scoringPoliceId") String scoringPoliceId);

	// 局领导当前月预警提醒人数统计
	AlarmLeaderStatistics alarmLeaderRemindStatistics(@Param("policeId") String policeId);

	// 警员个人分值扣分查询
	List<LeaveChart> personalBuckleAnalysisList(@Param("policeId") String policeId,
			@Param("scoredPoliceId") String scoredPoliceId, @Param("name") String name, @Param("sort") String sort);

	// 警员个人分值加分查询
	List<LeaveChart> personalAddAnalysisList(@Param("policeId") String policeId,
			@Param("scoredPoliceId") String scoredPoliceId, @Param("name") String name, @Param("sort") String sort);

	// 扣分型警员分值分析列表
	List<AlarmPoliceScoreAnalysis> alarmBuckleScoreLimitList(@Param("policeId") String policeId,
			@Param("dateTime") String dateTime);

	// 加分型警员分值分析列表
	List<AlarmPoliceScoreAnalysis> alarmAddScoreLimitList(@Param("policeId") String policeId,
			@Param("dateTime") String dateTime);

	// 扣分型警员分值分析更多列表查询
	List<AlarmPoliceScoreAnalysis> alarmBuckleScoreMoreList(@Param("policeId") String policeId,
			@Param("departmentId") Integer departmentId, @Param("dateTime") String dateTime);

	// 加分型警员分值分析更多列表查询
	List<AlarmPoliceScoreAnalysis> alarmAddScoreMoreList(@Param("policeId") String policeId,
			@Param("departmentId") Integer departmentId, @Param("dateTime") String dateTime);

	// 个人扣分最新预警详情查询
	AlarmEvaluation alarmPersonalRecordBuckleItem(@Param("policeId") String policeId,
			@Param("dateTime") String dateTime);

	// 个人加分最新预警详情查询
	AlarmEvaluation alarmPersonalRecordAddItem(@Param("policeId") String policeId, @Param("dateTime") String dateTime);

	// 计分行为占比统计
	List<CalculationChart> screenAlarmTypeStatisticsList();

	// 每月最高负分
	List<LeaveChart> screenAlarmMaxNegativeScoreList();

	// 近12个月记分人数查询
	List<CalculationChart> screenScoreList();

	// 近12个月预警人数查询
	List<CalculationChart> screenAlarmList();

}
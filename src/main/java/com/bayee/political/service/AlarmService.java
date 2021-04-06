package com.bayee.political.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.AlarmConfig;
import com.bayee.political.domain.AlarmEntryAndExitRecord;
import com.bayee.political.domain.AlarmEntryTimeName;
import com.bayee.political.domain.AlarmEvaluation;
import com.bayee.political.domain.AlarmLeaderStatistics;
import com.bayee.political.domain.AlarmNewest;
import com.bayee.political.domain.AlarmPersonalEvaluation;
import com.bayee.political.domain.AlarmPersonalStatistics;
import com.bayee.political.domain.AlarmPoliceMonth;
import com.bayee.political.domain.AlarmPoliceScoreAnalysis;
import com.bayee.political.domain.AlarmRecord;
import com.bayee.political.domain.AlarmRecordChart;
import com.bayee.political.domain.AlarmRecordTimeName;
import com.bayee.political.domain.AlarmScoreItem;
import com.bayee.political.domain.AlarmScoringBreakdown;
import com.bayee.political.domain.AlarmScoringSonBreakdown;
import com.bayee.political.domain.AlarmTalk;
import com.bayee.political.domain.AlarmTalkPoliceNum;
import com.bayee.political.domain.AlarmTalkPower;
import com.bayee.political.domain.AlarmTalkStatistics;
import com.bayee.political.domain.AlarmTimeName;
import com.bayee.political.domain.AlarmUrgeRecord;
import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.DateTimeItem;
import com.bayee.political.domain.LeaveChart;
import com.bayee.political.domain.LeavePower;
import com.bayee.political.domain.ReportDetails;
import com.bayee.political.domain.TimeName;
import com.bayee.political.domain.User;

/**
 * @author shentuqiwei
 * @version 2020年7月22日 下午1:55:12
 */

@Service
public interface AlarmService {

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
	 * (scoreItems必填 为记分项目分类)查询所有或根据记分细目id、记分子细目id、 记分日期、警号、被记分人、记分查询
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
	 * 获得公安月
	 */
	List<AlarmPoliceMonth> getAllMonth();

	/**
	 * 查询全部的记分细目
	 * 
	 * @return
	 */
	List<AlarmScoringBreakdown> getAllScoringBreakdown();

	/**
	 * 根据记分项目获得对应的记分细目
	 * 
	 * @return scoreItems 记分项目id
	 */
	List<AlarmScoringBreakdown> getBreakDownByProgram(@Param("scoreItems") Integer scoreItems);

	/**
	 * 根据id获取记分项目对应的记分细目
	 * 
	 * @param id
	 * @return
	 */
	List<AlarmScoringBreakdown> getBreakDownById(@Param("id") String id);

	/**
	 * 查询所有记分子细目
	 * 
	 * @return
	 */
	@Deprecated
	List<AlarmScoringSonBreakdown> getAllScoringSonBreakdowns();

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
	 * 根据PoliceId查询本月未上传的记分项目
	 * 
	 * @return
	 */
	List<AlarmScoreItem> notUploaded(String scoringPoliceId);

	/**
	 * 根据记分人获得本月未上传指标
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
	 * 获得约谈列表/根据条件查询
	 */
	List<AlarmTalk> getAlarmTalk(@Param("alrmTalk") AlarmTalk alrmTalk, @Param("keywords") String keywords,
			@Param("departmentIds") String departmentIds, @Param("pageSize") Integer pageSize);

	/**
	 * 获得约谈列表数量
	 */
	Integer getAlarmTalkCount(@Param("alrmTalk") AlarmTalk alrmTalk, @Param("departmentIds") String departmentIds,
			@Param("keywords") String keywords);

	/**
	 * 月度考评预警趋势图
	 * 
	 * @param target 1代表加分数量 2代表扣分数量
	 * @return
	 */
	List<AlarmPoliceMonth> getMonthEvaluationTrendChart(@Param("target") Integer target,
			@Param("departmentId") Integer departmentId);

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
	 * 根据记分细目id查询记分子细目
	 * 
	 * @return
	 */
	List<AlarmScoringSonBreakdown> getScoringSonBreakdownsByParentId(
			@Param("scoringBreakdownId") Integer scoringBreakdownId);

	/**
	 * 根据名字模糊查
	 * 
	 * @param name
	 * @return
	 */
	List<User> likeName(@Param("name") String name);

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
	 * 根据policeId查询本月是否存在预警记录
	 * 
	 * @return
	 */
	AlarmRecord getAlarmRecord(@Param("policeId") String policeId, @Param("type") Integer type,
			@Param("configTimeType") Integer configTimeType);

	/**
	 * 最新两条负面记分
	 * 
	 * @return
	 */
	List<AlarmEvaluation> newestScoring(@Param("scoringPoliceId") String scoringPoliceId);

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
	 * 根据计分项目名查询计分项目
	 * 
	 * @return
	 */
	AlarmScoreItem selectByName(@Param("dimension") Integer dimension, @Param("name") String name);

	/**
	 * 根据计分细目名获取计分细目id
	 * 
	 * @param name
	 * @return
	 */
	Integer getIdByBreakdownName(String name);

	/**
	 * 根据计分细目名获取计分细目id
	 * 
	 * @param name
	 * @return
	 */
	Integer getIdBySonBreakdownName(@Param("scoringBreakdownId") Integer scoringBreakdownId,
			@Param("name") String name);

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
	 * 新增约谈权限
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(AlarmTalkPower record);

	/**
	 * 查询约谈详情
	 * 
	 * @param id
	 * @return
	 */
	AlarmTalkPower getAlarmTalkPowerDetails(Integer id);

	/**
	 * 修改约谈权限
	 * 
	 * @return
	 */
	Integer alarmTalkPowerUpdate(@Param("alarmTalkPower") AlarmTalkPower alarmTalkPower);

	/**
	 * 获取的导出的Excel标题
	 * 
	 * @param scoreItem
	 * @return
	 */
	String getExportExcelTitle(@Param("scoreItem") Integer scoreItem);

	/**
	 * 获取约谈权限列表
	 * 
	 * @return
	 */
	List<AlarmTalkPower> getAlarmTalkPower(@Param("departmentId") Integer departmentId,
			@Param("keywords") String keywords, @Param("pageSize") Integer pageSize);

	/**
	 * 获取约谈权限列表数量
	 * 
	 * @return
	 */
	Integer getAlarmTalkPowerCount(@Param("departmentId") Integer departmentId, @Param("keywords") String keywords);

	/**
	 * 查询指定部门对应下的约谈人数量
	 * 
	 * @param departmentId
	 * @param policeId
	 * @return
	 */
	Integer isHave(@Param("departmentId") Integer departmentId, @Param("policeId") String policeId);

	/**
	 * 删除约谈权限根据id
	 * 
	 * @param id
	 * @return
	 */
	Integer alarmTalkPowerDelete(@Param("id") Integer id);

	/**
	 * 检查约谈权限是否被引用
	 * 
	 * @param id
	 * @return
	 */
	Integer checkAlarmTalkPower(@Param("policeId") String policeId, @Param("id") Integer id);

	/**
	 * 获得所有被记分人id及约谈对象id
	 * 
	 * @return
	 */
	List<String> findEvaltionTalk();

	/**
	 * 查询所有记分项目名
	 * 
	 * @return
	 */
	List<AlarmScoreItem> getAllItemName();
	
	/**
	 * 本周考核预警事件数量(所有预警)
	 * @return
	 */
	Integer alarmNumWeek();
	
	/**
	 * 本周出入境预警数
	 * @return
	 */
	Integer alarmEntryExitNumWeek();
	
	/**
	 * 本周约谈完成数
	 * @return
	 */
	Integer alarmTalkComplateNumWeek();
	
	/**
	 * 最新3条已完成或已发起的约谈
	 * @return
	 */
	List<AlarmEvaluation> newThreeTalk();

	// 约谈记录新增
	int alarmTalkCreat(AlarmTalk alarmTalk);

	// 约谈记录修改
	int alarmTalkUpdate(AlarmTalk alarmTalk);

	// 约谈记录详情查询(后台)
	AlarmTalk alarmTalkItem(Integer id);

	// 约谈近期列表查询(api)
	List<AlarmTalk> alarmTalkLastList(String hostId, String dateTime);

	// 约谈消息列表查询(api)
	List<AlarmTalk> alarmTalkNewsList(String hostId);

	// 约谈完成率，未完成率查询
	AlarmTalkStatistics alarmTalkRateItem(String hostId, String dateTime);

	// 约谈完成人数查询
	AlarmTalkPoliceNum talkPoliceNumItem(String hostId, String dateTime);

	// 约谈未完成人数查询
	AlarmTalkPoliceNum noTalkPoliceNumItem(String hostId, String dateTime);

	// 个人预警统计
	AlarmPersonalStatistics alarmPersonalStatisticsItem(String policeId);

	// 个人谈话预约列表查询
	List<AlarmTalk> alarmTalkPersonalList(String policeId, Integer isReceive);

	// 个人最新记分项数量统计（最近一周）
	int alarmPersonalNotConfirmNum(String policeId);

	// 个人最新记分项查询（最近一周）
	List<AlarmEvaluation> alarmPersonalNotConfirmList(String policeId);

	// 修改预约评价记录
	int alarmEvaluationupdate(AlarmEvaluation alarmEvaluation);

	// 预警评价记录详情查询
	AlarmEvaluation alarmEvaluationItem(Integer id);

	// 个人考评表数量查询
	int alarmPersonalEvaluationNum(String policeId);

	// 个人考评报表列表查询
	List<AlarmPersonalEvaluation> alarmPersonalEvaluationList(String policeId, String dateTime);

	// 个人考评报表历史记录扣分查询
	List<AlarmEvaluation> alarmPersonalEvaluationHistoryBuckleList(String policeId, String dateTime,
			Integer policeMonthId, Integer type);

	// 个人考评报表历史记录加分查询
	List<AlarmEvaluation> alarmPersonalEvaluationHistoryAddList(String policeId, String dateTime, Integer policeMonthId,
			Integer type);

	// 个人最近加扣分项查询(当前月)
	List<LeaveChart> alarmEvaluationLastScoreList(String policeId);

	// 个人加分趋势查询(近一年)
	List<LeaveChart> alarmAddScoreList(String policeId);

	// 个人扣分趋势查询(近一年)
	List<LeaveChart> alarmBuckleScoreList(String policeId);

	// 个人考评维度加扣分占比
	List<LeaveChart> alarmPersonalEvaluationPieList(String policeId);

	// 个人正面扣分项目占比
	List<LeaveChart> alarmAddProjectList(String policeId, Integer type);

	// 个人负面扣分项目占比
	List<LeaveChart> alarmBuckleProjectList(String policeId, Integer type);

	// 个人考评年份查询
	List<CalculationChart> alarmYearsList(String policeId);

	// 局领导当前年预警人数统计
	AlarmLeaderStatistics alarmLeaderStatistics(String policeId);

	// 局领导约谈人数统计
	AlarmLeaderStatistics alarmLeaderTalkStatistics(String policeId);

	// 局领导上个月预警人数统计
	AlarmLeaderStatistics alarmLastLeaderStatistics(String policeId);

	// 局领导约预警加分人数趋势图
	List<CalculationChart> alarmLeaderLineAddChart(String policeId);

	// 局领导考核预警趋势图
	List<CalculationChart> alarmLeaderLineBuckleChart(String policeId);

	// 局领导扣分人数趋势图
	List<CalculationChart> alarmLeaderTypeBuckleChart(String policeId);

	// 局领导加分人数趋势图
	List<CalculationChart> alarmLeaderTypeAddChart(String policeId);

	// 局领导最新预警查询
	List<AlarmRecord> alarmLeaderNewestList(String policeId);

	// 局领导最新预警统计
	AlarmLeaderStatistics alarmLeaderNewestItem(String policeId);

	// 查询民警预警最新时间
	AlarmEvaluation alarmNewestTime(String scoredPoliceId);

	// 局领导约谈事项查询
	List<AlarmTalk> alarmLeaderTalkList(String policeId);

	// 局领导约谈数量累计统计
	AlarmLeaderStatistics alarmLeaderTalkItem(String policeId);

	// 局领导约谈对象民警查询
	List<AlarmNewest> alarmLeaderTalkObjectList(String scoredPoliceId, String scoringPoliceId);

	// 局领导约谈对象预警民警查询
	List<AlarmNewest> alarmLeaderTalkConditionList(String scoredPoliceId, String scoringPoliceId);

	// 局领导预警列表查询
	List<AlarmRecord> alarmBuckleList(String policeId, Integer type, String dateTime, Integer pageSize,
			Integer pageNum);

	// 局领导扣分预警列表数量统计
	int alarmBuckleListCount(String policeId, Integer type, String dateTime);

	// 局领导加分预警列表查询
	List<AlarmRecord> alarmAddList(String policeId, String dateTime);

	// 局领导扣分预警详情
	List<AlarmRecordChart> alarmGetScoreBuckleItem(String policeId, Integer alarmConfigType, String dateTime);

	// 局领导加分预警详情
	List<AlarmRecordChart> alarmGetScoreAddItem(String policeId, Integer alarmConfigType, String dateTime);

	// 各月已约谈人数查询
	List<CalculationChart> alarmTalkMonthNumChart(String hostId);

	// 每月最高扣分值图
	List<LeaveChart> alarmMonthScoreBuckleList(String policeId);

	// 每月最高加分值图
	List<LeaveChart> alarmMonthScoreAddList(String policeId);

	// 预警记录新增
	int alarmRecordCreat(AlarmRecord alarmRecord);

	// 预警记录修改
	int alarmRecordUpdate(AlarmRecord alarmRecord);

	// 约谈列表查询(定时任务修改约谈状态进程)
	List<AlarmTalk> alarmTalkStatusList();

	// 个人谈话完成数量统计
	int alarmTalkOverNum(String policeId);

	// 局领导扣分最大值查询
	List<LeaveChart> alarmBuckleMaxList(String policeId);

	// 局领导加分最大值查询
	List<LeaveChart> alarmAddMaxList(String policeId);

	// 查询当前季度月度时间
	DateTimeItem dateItems();

	// 扣分型排行榜
	List<AlarmNewest> alarmBuckleRankList(String policeId, String startTime, String endTime);

	// 加分型排行榜
	List<AlarmNewest> alarmAddRankList(String policeId, String startTime, String endTime);

	// 统计需填写约谈数量
	int alarmTalkFillCount(String hostId);

	// 局领导约谈列表查询
	List<AlarmTalk> alarmLeaderTalkPageList(String hostId, String dateTime, Integer viewType, Integer pageSize,
			Integer pageNum);

	// 局领导约谈列表时间查询
	List<TimeName> alarmDatePageList(String hostId, Integer viewType, Integer pageSize, Integer pageNum);

	// 局领导约谈列表总数查询
	int alarmLeaderTalkPageCount(String hostId, String dateTime, Integer viewType);

	// 查询当前人最新一条约谈记录
	List<AlarmTalk> newAlarmTalkList(String hostId);

	// 查询约谈人约谈条件
	LeavePower powerItem(String scoringPoliceId, Integer departmentId);

	// 统计被约谈人需填写反馈数量
	int alarmObjectTalkFillCount(String policeId);

	// 被约谈人约谈列表时间查询
	List<TimeName> alarmObjectDatePageList(String policeId, Integer viewType, Integer pageSize, Integer pageNum);

	// 被约谈人约谈列表查询
	List<AlarmTalk> alarmObjectTalkPageList(String policeId, Integer viewType, Integer pageSize, Integer pageNum);

	// 被约谈人约谈列表总数查询
	int alarmObjectTalkPageCount(String policeId, Integer viewType);

	// 局领导当前月预警提醒人数统计
	AlarmLeaderStatistics alarmLeaderRemindStatistics(String policeId);

	// 警员个人分值扣分查询
	List<LeaveChart> personalBuckleAnalysisList(String policeId, String scoredPoliceId, String name, String sort);

	// 警员个人分值加分查询
	List<LeaveChart> personalAddAnalysisList(String policeId, String scoredPoliceId, String name, String sort);

	// 最近一年总预警数统计
	int personalAlarmTotalNum(String scoredPoliceId);

	// 扣分型警员分值分析列表
	List<AlarmPoliceScoreAnalysis> alarmBuckleScoreLimitList(String policeId, String dateTime);

	// 加分型警员分值分析列表
	List<AlarmPoliceScoreAnalysis> alarmAddScoreLimitList(String policeId, String dateTime);

	// 扣分型警员分值分析更多列表查询
	List<AlarmPoliceScoreAnalysis> alarmBuckleScoreMoreList(String policeId, Integer departmentId, String dateTime);

	// 加分型警员分值分析更多列表查询
	List<AlarmPoliceScoreAnalysis> alarmAddScoreMoreList(String policeId, Integer departmentId, String dateTime);

	// 局领导出入境预警统计
	AlarmLeaderStatistics alarmEntryAndExitStatistics(String policeId);

	// 局领导出入境预警累计统计
	AlarmLeaderStatistics alarmLeaderEntryRecordItem(String policeId);

	// 局领导最新出入境预警查询
	List<AlarmEntryAndExitRecord> alarmLeaderEntryNewestList(String policeId);

	// 局领导出入境预警数量统计
	int alarmLeaderEntryRecordPageCount(String policeId);

	// 局领导出入境预警分页查询
	List<AlarmEntryAndExitRecord> alarmLeaderEntryRecordPageList(String policeId, Integer pageSize, Integer pageNum);

	// 局领导出入境预警详情查询
	AlarmEntryAndExitRecord alarmLeaderEntryItem(Integer id);

	// 出入境预警新增
	int alarmEntryAndExitRecordCreat(AlarmEntryAndExitRecord record);

	// 出入境预警修改
	int alarmEntryAndExitRecordUpdate(AlarmEntryAndExitRecord record);

	// 局领导催还护照记录新增
	int alarmUrgeRecordCreat(AlarmUrgeRecord record);

	// 局领导催还护照记录修改
	int alarmUrgeRecordUpdate(AlarmUrgeRecord record);

	// 局领导催还护照记录详情查询
	AlarmUrgeRecord alarmUrgeRecordItem(Integer id, Integer entryId, String policeId, String urgePoliceId);

	// 个人护照催还通知最新查询
	List<AlarmUrgeRecord> alarmPersonalPassportReturnNewestList(String policeId, Integer readStatus);

	// 个人护照催还通知总数统计
	int alarmPersonalPassportReturnCount(String policeId, Integer readStatus);

	// 个人护照催还通知时间查询
	List<AlarmTimeName> alarmPassportDateList(String policeId, Integer readStatus, Integer pageSize, Integer pageNum);

	// 个人护照催还通知分页查询
	List<AlarmUrgeRecord> alarmPersonalPassportReturnList(String policeId, Integer readStatus, Integer pageSize,
			Integer pageNum);

	// 局领导出入境预警趋势图
	List<CalculationChart> alarmLeaderEntryExitChart(String policeId);

	// 局领导约谈人数趋势图
	List<CalculationChart> alarmLeaderTalkLineChart(String policeId);

	// 局领导预警列表时间查询
	List<AlarmRecordTimeName> alarmRecordDatePageList(String policeId, Integer type, String dateTime, Integer pageSize,
			Integer pageNum);

	// 预警详情查询
	AlarmRecord alarmPoliceRecordItem(Integer id, String policeId, Integer alarmConfigType, Integer type);

	// 查询最新约谈详情
	AlarmTalk talkNewItem(Integer alarmRecordId, String policeId, Integer alarmType, Integer alarmConfigType,
			String dateTime);

	// 局领导出入境时间查询
	List<AlarmEntryTimeName> alarmEntryDatePageList(String policeId, Integer pageSize, Integer pageNum);

	// 个人扣分最新预警详情查询
	AlarmEvaluation alarmPersonalRecordBuckleItem(String policeId, String dateTime);

	// 个人加分最新预警详情查询
	AlarmEvaluation alarmPersonalRecordAddItem(String policeId, String dateTime);

	// 查询进行中的约谈
	List<AlarmTalk> talkExistList(String policeId, Integer alarmRecordId, Integer alarmType);

}

package com.bayee.political.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.bayee.political.mapper.AlarmEntryAndExitRecordMapper;
import com.bayee.political.mapper.AlarmEvaluationMapper;
import com.bayee.political.mapper.AlarmPoliceMonthMapper;
import com.bayee.political.mapper.AlarmRecordMapper;
import com.bayee.political.mapper.AlarmScoreItemMapper;
import com.bayee.political.mapper.AlarmScoringBreakdownMapper;
import com.bayee.political.mapper.AlarmScoringSonBreakdownMapper;
import com.bayee.political.mapper.AlarmTalkMapper;
import com.bayee.political.mapper.AlarmTalkPowerMapper;
import com.bayee.political.mapper.AlarmUrgeRecordMapper;
import com.bayee.political.mapper.UserMapper;
import com.bayee.political.service.AlarmService;

/**
 * @author shentuqiwei
 * @version 2020年7月22日 下午1:55:56
 */
@Service
public class AlarmServiceImpl implements AlarmService {

	@Autowired
	private AlarmTalkMapper alarmTalkMapper;// AI预警约谈记录

	@Autowired
	private AlarmEvaluationMapper alarmEvaluationMapper;// 预警评价表

	@Autowired
	private AlarmPoliceMonthMapper alarmPoliceMonthMapper;// 公安月

	@Autowired
	private AlarmScoringBreakdownMapper alarmScoringBreakdownMapper;// 记分细目

	@Autowired
	private AlarmScoringSonBreakdownMapper alarmScoringSonBreakdownMapper;// 记分子细目

	@Autowired
	private AlarmScoreItemMapper alarmScoreItemMapper;// 记分项目

	@Autowired
	private UserMapper userMapper;// 用户表

	@Autowired
	private AlarmRecordMapper alarmRecordMapper;// 预警记录

	@Autowired
	private AlarmTalkPowerMapper alarmTalkPowerMapper;// 约谈权限

	@Autowired
	private AlarmEntryAndExitRecordMapper alarmEntryAndExitRecordMapper;// 警员出入境记录

	@Autowired
	private AlarmUrgeRecordMapper alarmUrgeRecordMapper;// 局领导催还护照记录

	// 约谈记录详情查询(后台)
	@Override
	public AlarmTalk alarmTalkItem(Integer id) {
		return alarmTalkMapper.alarmTalkItem(id);
	}

	// 约谈记录新增
	@Override
	public int alarmTalkCreat(AlarmTalk alarmTalk) {
		return alarmTalkMapper.alarmTalkCreat(alarmTalk);
	}

	// 约谈记录修改
	@Override
	public int alarmTalkUpdate(AlarmTalk alarmTalk) {
		return alarmTalkMapper.alarmTalkUpdate(alarmTalk);
	}

	// 约谈近期列表查询(api)
	@Override
	public List<AlarmTalk> alarmTalkLastList(String hostId, String dateTime) {
		return alarmTalkMapper.alarmTalkLastList(hostId, dateTime);
	}

	// 约谈消息列表查询(api)
	@Override
	public List<AlarmTalk> alarmTalkNewsList(String hostId) {
		return alarmTalkMapper.alarmTalkNewsList(hostId);
	}

	// 约谈完成率，未完成率查询
	@Override
	public AlarmTalkStatistics alarmTalkRateItem(String hostId, String dateTime) {
		return alarmTalkMapper.alarmTalkRateItem(hostId, dateTime);
	}

	// 约谈完成人数查询
	@Override
	public AlarmTalkPoliceNum talkPoliceNumItem(String hostId, String dateTime) {
		return alarmTalkMapper.talkPoliceNumItem(hostId, dateTime);
	}

	// 约谈未完成人数查询
	@Override
	public AlarmTalkPoliceNum noTalkPoliceNumItem(String hostId, String dateTime) {
		return alarmTalkMapper.noTalkPoliceNumItem(hostId, dateTime);
	}

	// 个人预警统计
	@Override
	public AlarmPersonalStatistics alarmPersonalStatisticsItem(String policeId) {
		return alarmEvaluationMapper.alarmPersonalStatisticsItem(policeId);
	}

	// 个人谈话预约列表查询
	@Override
	public List<AlarmTalk> alarmTalkPersonalList(String policeId, Integer isReceive) {
		return alarmTalkMapper.alarmTalkPersonalList(policeId, isReceive);
	}

	// 个人最新记分项数量统计（最近一周）
	@Override
	public int alarmPersonalNotConfirmNum(String policeId) {
		return alarmEvaluationMapper.alarmPersonalNotConfirmNum(policeId);
	}

	// 个人最新记分项查询（最近一周）
	@Override
	public List<AlarmEvaluation> alarmPersonalNotConfirmList(String policeId) {
		return alarmEvaluationMapper.alarmPersonalNotConfirmList(policeId);
	}

	// 修改预约评价记录
	@Override
	public int alarmEvaluationupdate(AlarmEvaluation alarmEvaluation) {
		return alarmEvaluationMapper.alarmEvaluationupdate(alarmEvaluation);
	}

	// 预警评价记录详情查询
	@Override
	public AlarmEvaluation alarmEvaluationItem(Integer id) {
		return alarmEvaluationMapper.alarmEvaluationItem(id);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return alarmEvaluationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AlarmEvaluation record) {
		return alarmEvaluationMapper.insert(record);
	}

	@Override
	public AlarmEvaluation selectByPrimaryKey(Integer id) {
		return alarmEvaluationMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AlarmEvaluation record) {
		return alarmEvaluationMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AlarmEvaluation record) {
		return alarmEvaluationMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<AlarmEvaluation> selectAllOrByCondition(Integer scoreItems, Integer scoringBreakdownId,
			Integer scoringSonBreakdownId, String scoringDate, String keywords, Integer departmentId,
			Integer searchDepId, Integer pageSize) {
		return alarmEvaluationMapper.selectAllOrByCondition(scoreItems, scoringBreakdownId, scoringSonBreakdownId,
				scoringDate, keywords, departmentId, searchDepId, pageSize);
	}

	@Override
	public Integer selectAllOrByConditionCount(Integer scoreItems, Integer scoringBreakdownId,
			Integer scoringSonBreakdownId, String scoringDate, String keywords, Integer departmentId,
			Integer searchDepId) {
		return alarmEvaluationMapper.selectAllOrByConditionCount(scoreItems, scoringBreakdownId, scoringSonBreakdownId,
				scoringDate, keywords, departmentId, searchDepId);
	}

	@Override
	public List<AlarmPoliceMonth> getAllMonth() {
		return alarmPoliceMonthMapper.getAllMonth();
	}

	@Override
	public List<AlarmScoringBreakdown> getAllScoringBreakdown() {
		return alarmScoringBreakdownMapper.getAllScoringBreakdown();
	}

	@Override
	public List<AlarmScoringSonBreakdown> getAllScoringSonBreakdowns() {
		return alarmScoringSonBreakdownMapper.getAllScoringSonBreakdowns();
	}

	@Override
	public Integer theMonthTotal(Integer departmentId) {
		return alarmEvaluationMapper.theMonthTotal(departmentId);
	}

	@Override
	public Integer theMonthNewAddTotal(Integer departmentId) {
		return alarmEvaluationMapper.theMonthNewAddTotal(departmentId);
	}

	@Override
	public Integer theMonthPointsDeductedNum(Integer departmentId) {
		return alarmEvaluationMapper.theMonthPointsDeductedNum(departmentId);
	}

	@Override
	public Integer theMonthNewAddPointsDeductedNum(Integer departmentId) {
		return alarmEvaluationMapper.theMonthNewAddPointsDeductedNum(departmentId);
	}

	@Override
	public Integer theMonthBonusPointsNum(Integer departmentId) {
		return alarmEvaluationMapper.theMonthBonusPointsNum(departmentId);
	}

	@Override
	public Integer theMonthNewAddBonusPointsNum(Integer departmentId) {
		return alarmEvaluationMapper.theMonthNewAddBonusPointsNum(departmentId);
	}

	@Override
	public Integer theMonthBonusPointsAlarmNum(Integer departmentId, Integer type) {
		return alarmEvaluationMapper.theMonthBonusPointsAlarmNum(departmentId, type);
	}

	@Override
	public Integer theMonthNewAddBonusPointsAlarmNum(Integer departmentId, Integer type) {
		return alarmEvaluationMapper.theMonthNewAddBonusPointsAlarmNum(departmentId, type);
	}

	@Override
	public Integer theMonthPointsDeductedAlarmNum(Integer departmentId, Integer type) {
		return alarmEvaluationMapper.theMonthPointsDeductedAlarmNum(departmentId, type);
	}

	@Override
	public Integer theMonthNewAddPointsDeductedAlarmNum(Integer departmentId, Integer type) {
		return alarmEvaluationMapper.theMonthNewAddPointsDeductedAlarmNum(departmentId, type);
	}

	// 个人考评表数量查询
	@Override
	public int alarmPersonalEvaluationNum(String policeId) {
		return alarmEvaluationMapper.alarmPersonalEvaluationNum(policeId);
	}

	// 个人考评报表列表查询
	@Override
	public List<AlarmPersonalEvaluation> alarmPersonalEvaluationList(String policeId, String dateTime) {
		return alarmEvaluationMapper.alarmPersonalEvaluationList(policeId, dateTime);
	}

	// 个人考评报表历史记录扣分查询
	@Override
	public List<AlarmEvaluation> alarmPersonalEvaluationHistoryBuckleList(String policeId, String dateTime,
			Integer policeMonthId, Integer type) {
		return alarmEvaluationMapper.alarmPersonalEvaluationHistoryBuckleList(policeId, dateTime, policeMonthId, type);
	}

	// 个人考评报表历史记录加分查询
	@Override
	public List<AlarmEvaluation> alarmPersonalEvaluationHistoryAddList(String policeId, String dateTime,
			Integer policeMonthId, Integer type) {
		return alarmEvaluationMapper.alarmPersonalEvaluationHistoryAddList(policeId, dateTime, policeMonthId, type);
	}

	// 个人最近加扣分项查询(当前月)
	@Override
	public List<LeaveChart> alarmEvaluationLastScoreList(String policeId) {
		return alarmEvaluationMapper.alarmEvaluationLastScoreList(policeId);
	}

	// 个人加分趋势查询(近一年)
	@Override
	public List<LeaveChart> alarmAddScoreList(String policeId) {
		return alarmEvaluationMapper.alarmAddScoreList(policeId);
	}

	// 个人扣分趋势查询(近一年)
	@Override
	public List<LeaveChart> alarmBuckleScoreList(String policeId) {
		return alarmEvaluationMapper.alarmBuckleScoreList(policeId);
	}

	// 个人考评维度加扣分占比
	@Override
	public List<LeaveChart> alarmPersonalEvaluationPieList(String policeId) {
		return alarmEvaluationMapper.alarmPersonalEvaluationPieList(policeId);
	}

	// 个人正面扣分项目占比
	@Override
	public List<LeaveChart> alarmAddProjectList(String policeId, Integer type) {
		return alarmEvaluationMapper.alarmAddProjectList(policeId, type);
	}

	// 个人负面扣分项目占比
	@Override
	public List<LeaveChart> alarmBuckleProjectList(String policeId, Integer type) {
		return alarmEvaluationMapper.alarmBuckleProjectList(policeId, type);
	}

	@Override
	public List<AlarmScoreItem> notUploaded(String scoringPoliceId) {
		return alarmScoreItemMapper.notUploaded(scoringPoliceId);
	}

	@Override
	public List<String> theMonthNotUpload(String scoringPoliceId) {
		return alarmEvaluationMapper.theMonthNotUpload(scoringPoliceId);
	}

	@Override
	public Integer totalScoreTargetNum() {
		return alarmEvaluationMapper.totalScoreTargetNum();
	}

	@Override
	public Integer scoreTargetNum(Integer number) {
		return alarmEvaluationMapper.scoreTargetNum(number);
	}

	@Override
	public List<AlarmEvaluation> review(Integer scoringPoliceId) {
		return alarmEvaluationMapper.review(scoringPoliceId);
	}

	@Override
	public Integer updateAlarmConfig(Integer thresholdType, Double score, Integer type) {
		return alarmEvaluationMapper.updateAlarmConfig(thresholdType, score, type);
	}

	@Override
	public List<AlarmTalk> getAlarmTalk(AlarmTalk alrmTalk, String keywords, String departmentIds, Integer pageSize) {
		return alarmTalkMapper.getAlarmTalk(alrmTalk, keywords, departmentIds, pageSize);
	}

	@Override
	public List<AlarmPoliceMonth> getMonthEvaluationTrendChart(Integer target, Integer departmentId) {
		return alarmPoliceMonthMapper.getMonthEvaluationTrendChart(target, departmentId);
	}

	// 个人考评年份查询
	@Override
	public List<CalculationChart> alarmYearsList(String policeId) {
		return alarmEvaluationMapper.alarmYearsList(policeId);
	}

	// 局领导当前年预警人数统计
	@Override
	public AlarmLeaderStatistics alarmLeaderStatistics(String policeId) {
		return alarmEvaluationMapper.alarmLeaderStatistics(policeId);
	}

	// 局领导上个月预警人数统计
	@Override
	public AlarmLeaderStatistics alarmLastLeaderStatistics(String policeId) {
		return alarmEvaluationMapper.alarmLastLeaderStatistics(policeId);
	}

	// 局领导约谈人数统计
	@Override
	public AlarmLeaderStatistics alarmLeaderTalkStatistics(String policeId) {
		return alarmTalkMapper.alarmLeaderTalkStatistics(policeId);
	}

	@Override
	public List<AlarmEvaluation> getMonthAlarmReport(String departmentName, String keywords, String ids,
			Integer pageSize) {
		return alarmEvaluationMapper.getMonthAlarmReport(departmentName, keywords, ids, pageSize);
	}

	@Override
	public List<AlarmEvaluation> getUnitType() {
		return alarmEvaluationMapper.getUnitType();
	}

	@Override
	public List<AlarmEvaluation> waitMatter(String scoredPoliceId, Integer pageSize) {
		return alarmEvaluationMapper.waitMatter(scoredPoliceId, pageSize);
	}

	@Override
	public List<AlarmEvaluation> doneMatter(String scoringPoliceId, Integer pageSize) {
		return alarmEvaluationMapper.doneMatter(scoringPoliceId, pageSize);
	}

	@Override
	public Integer waitMatterMark(Integer mark, Integer id) {
		return alarmEvaluationMapper.waitMatterMark(mark, id);
	}

	@Override
	public List<AlarmScoringSonBreakdown> getScoringSonBreakdownsByParentId(Integer scoringBreakdownId) {
		return alarmScoringSonBreakdownMapper.getScoringSonBreakdownsByParentId(scoringBreakdownId);
	}

	@Override
	public List<User> likeName(String name) {
		return userMapper.likeName(name);
	}

	@Override
	public List<AlarmScoringBreakdown> getBreakDownByProgram(Integer scoreItems) {
		return alarmScoringBreakdownMapper.getBreakDownByProgram(scoreItems);
	}

	@Override
	public List<AlarmScoringBreakdown> getBreakDownById(String id) {
		return alarmScoringBreakdownMapper.getBreakDownById(id);
	}

	// 局领导约预警加分人数趋势图
	@Override
	public List<CalculationChart> alarmLeaderLineAddChart(String policeId) {
		return alarmEvaluationMapper.alarmLeaderLineAddChart(policeId);
	}

	// 局领导考核预警趋势图
	@Override
	public List<CalculationChart> alarmLeaderLineBuckleChart(String policeId) {
		return alarmEvaluationMapper.alarmLeaderLineBuckleChart(policeId);
	}

	// 局领导扣分人数趋势图
	@Override
	public List<CalculationChart> alarmLeaderTypeBuckleChart(String policeId) {
		return alarmEvaluationMapper.alarmLeaderTypeBuckleChart(policeId);
	}

	// 局领导加分人数趋势图
	@Override
	public List<CalculationChart> alarmLeaderTypeAddChart(String policeId) {
		return alarmEvaluationMapper.alarmLeaderTypeAddChart(policeId);
	}

	@Override
	public List<AlarmConfig> getAlarmConfig() {
		return alarmEvaluationMapper.getAlarmConfig();
	}

	@Override
	public Integer getAlarmTalkCount(AlarmTalk alrmTalk, String departmentIds, String keywords) {
		return alarmTalkMapper.getAlarmTalkCount(alrmTalk, departmentIds, keywords);
	}

	// 局领导最新预警查询
	@Override
	public List<AlarmRecord> alarmLeaderNewestList(String policeId) {
		return alarmRecordMapper.alarmLeaderNewestList(policeId);
	}

	// 局领导最新预警统计
	@Override
	public AlarmLeaderStatistics alarmLeaderNewestItem(String policeId) {
		return alarmEvaluationMapper.alarmLeaderNewestItem(policeId);
	}

	// 查询民警预警最新时间
	@Override
	public AlarmEvaluation alarmNewestTime(String scoredPoliceId) {
		return alarmEvaluationMapper.alarmNewestTime(scoredPoliceId);
	}

	// 局领导约谈事项查询
	@Override
	public List<AlarmTalk> alarmLeaderTalkList(String policeId) {
		return alarmTalkMapper.alarmLeaderTalkList(policeId);
	}

	// 局领导约谈数量累计统计
	@Override
	public AlarmLeaderStatistics alarmLeaderTalkItem(String policeId) {
		return alarmTalkMapper.alarmLeaderTalkItem(policeId);
	}

	// 局领导约谈对象民警查询
	@Override
	public List<AlarmNewest> alarmLeaderTalkObjectList(String scoredPoliceId, String scoringPoliceId) {
		return alarmEvaluationMapper.alarmLeaderTalkObjectList(scoredPoliceId, scoringPoliceId);
	}

	// 局领导预警列表查询
	@Override
	public List<AlarmRecord> alarmBuckleList(String policeId, Integer type, String dateTime, Integer pageSize,
			Integer pageNum) {
		return alarmRecordMapper.alarmBuckleList(policeId, type, dateTime, pageSize, pageNum);
	}

	// 局领导预警列表数量统计
	@Override
	public int alarmBuckleListCount(String policeId, Integer type, String dateTime) {
		return alarmRecordMapper.alarmBuckleListCount(policeId, type, dateTime);
	}

	// 局领导预警列表时间查询
	@Override
	public List<AlarmRecordTimeName> alarmRecordDatePageList(String policeId, Integer type, String dateTime,
			Integer pageSize, Integer pageNum) {
		return alarmRecordMapper.alarmRecordDatePageList(policeId, type, dateTime, pageSize, pageNum);
	}

	// 局领导加分预警列表查询
	@Override
	public List<AlarmRecord> alarmAddList(String policeId, String dateTime) {
		return alarmRecordMapper.alarmAddList(policeId, dateTime);
	}

	// 局领导扣分预警详情
	@Override
	public List<AlarmRecordChart> alarmGetScoreBuckleItem(String policeId, Integer alarmConfigType, String dateTime) {
		return alarmEvaluationMapper.alarmGetScoreBuckleItem(policeId, alarmConfigType, dateTime);
	}

	// 局领导加分预警详情
	@Override
	public List<AlarmRecordChart> alarmGetScoreAddItem(String policeId, Integer alarmConfigType, String dateTime) {
		return alarmEvaluationMapper.alarmGetScoreAddItem(policeId, alarmConfigType, dateTime);
	}

	@Override
	public Integer getMonthAlarmReportCount(String departmentName, String ids, String keywords) {
		return alarmEvaluationMapper.getMonthAlarmReportCount(departmentName, ids, keywords);
	}

	// 各月已约谈人数查询
	@Override
	public List<CalculationChart> alarmTalkMonthNumChart(String hostId) {
		return alarmTalkMapper.alarmTalkMonthNumChart(hostId);
	}

	@Override
	public List<LeaveChart> alarmMonthScoreBuckleList(String policeId) {
		return null;
	}

	@Override
	public List<LeaveChart> alarmMonthScoreAddList(String policeId) {
		return null;
	}

	// 预警记录新增
	@Override
	public int alarmRecordCreat(AlarmRecord alarmRecord) {
		return alarmRecordMapper.alarmRecordCreat(alarmRecord);
	}

	// 预警记录修改
	@Override
	public int alarmRecordUpdate(AlarmRecord alarmRecord) {
		return alarmRecordMapper.alarmRecordUpdate(alarmRecord);
	}

	@Override
	public Integer waitMatterCount(String scoringPoliceId) {
		return alarmEvaluationMapper.waitMatterCount(scoringPoliceId);
	}

	@Override
	public Integer doneMatterCount(String scoringPoliceId) {
		return alarmEvaluationMapper.doneMatterCount(scoringPoliceId);
	}

	@Override
	public List<String> scoreTip(String scoringPoliceId) {
		return alarmEvaluationMapper.scoreTip(scoringPoliceId);
	}

	@Override
	public List<AlarmEvaluation> getQuarterAlarmReport(String departmentName, String keywords, String ids,
			Integer pageSize) {
		return alarmEvaluationMapper.getQuarterAlarmReport(departmentName, keywords, ids, pageSize);
	}

	@Override
	public Integer getQuarterAlarmReportCount(String departmentName, String ids, String keywords) {
		return alarmEvaluationMapper.getQuarterAlarmReportCount(departmentName, ids, keywords);
	}

	@Override
	public AlarmRecord getAlarmRecord(String policeId, Integer type, Integer configTimeType) {
		return alarmRecordMapper.getAlarmRecord(policeId, type, configTimeType);
	}

	@Override
	public List<AlarmEvaluation> getAllAlarmReport(String departmentName, String keywords, String ids,
			Integer pageSize) {
		return alarmEvaluationMapper.getAllAlarmReport(departmentName, keywords, ids, pageSize);
	}

	@Override
	public Integer getaAllAlarmReportCount(String departmentName, String keywords, String ids) {
		return alarmEvaluationMapper.getaAllAlarmReportCount(departmentName, keywords, ids);
	}

	@Override
	public List<AlarmEvaluation> newestScoring(String scoringPoliceId) {
		return alarmEvaluationMapper.newestScoring(scoringPoliceId);
	}

	// 约谈列表查询(定时任务修改约谈状态进程)
	@Override
	public List<AlarmTalk> alarmTalkStatusList() {
		return alarmTalkMapper.alarmTalkStatusList();
	}

	// 个人谈话完成数量统计
	@Override
	public int alarmTalkOverNum(String policeId) {
		return alarmTalkMapper.alarmTalkOverNum(policeId);
	}

	@Override
	public AlarmScoreItem selectByName(Integer dimension, String name) {
		return alarmScoreItemMapper.selectByName(dimension, name);
	}

	@Override
	public Integer getIdByBreakdownName(String name) {
		return alarmScoringBreakdownMapper.getIdByBreakdownName(name);
	}

	@Override
	public Integer getIdBySonBreakdownName(Integer scoringBreakdownId, String name) {
		return alarmScoringSonBreakdownMapper.getIdBySonBreakdownName(scoringBreakdownId, name);
	}

	@Override
	public List<ReportDetails> getReportDetails(Integer conditionType, Integer condition, Integer year,
			Integer departmentId, Integer pageSize) {
		return alarmEvaluationMapper.getReportDetails(conditionType, condition, year, departmentId, pageSize);
	}

	@Override
	public Integer getReportDetailsCount(Integer conditionType, Integer condition, Integer year, Integer departmentId) {
		return alarmEvaluationMapper.getReportDetailsCount(conditionType, condition, year, departmentId);
	}

	@Override
	public List<AlarmTalkPower> getAlarmTalkPower(Integer departmentId, String keywords, Integer pageSize) {
		return alarmTalkPowerMapper.getAlarmTalkPower(departmentId, keywords, pageSize);
	}

	@Override
	public Integer getAlarmTalkPowerCount(Integer departmentId, String keywords) {
		return alarmTalkPowerMapper.getAlarmTalkPowerCount(departmentId, keywords);
	}

	// 局领导扣分最大值查询
	@Override
	public List<LeaveChart> alarmBuckleMaxList(String policeId) {
		return alarmEvaluationMapper.alarmBuckleMaxList(policeId);
	}

	// 局领导加分最大值查询
	@Override
	public List<LeaveChart> alarmAddMaxList(String policeId) {
		return alarmEvaluationMapper.alarmAddMaxList(policeId);
	}

	@Override
	public int insertSelective(AlarmTalkPower record) {
		return alarmTalkPowerMapper.insertSelective(record);
	}

	@Override
	public AlarmTalkPower getAlarmTalkPowerDetails(Integer id) {
		return alarmTalkPowerMapper.getAlarmTalkPowerDetails(id);
	}

	// 查询当前季度月度时间
	@Override
	public DateTimeItem dateItems() {
		return alarmEvaluationMapper.dateItems();
	}

	// 扣分型排行榜
	@Override
	public List<AlarmNewest> alarmBuckleRankList(String policeId, String startTime, String endTime) {
		return alarmEvaluationMapper.alarmBuckleRankList(policeId, startTime, endTime);
	}

	// 加分型排行榜
	@Override
	public List<AlarmNewest> alarmAddRankList(String policeId, String startTime, String endTime) {
		return alarmEvaluationMapper.alarmAddRankList(policeId, startTime, endTime);
	}

	@Override
	public Integer alarmTalkPowerUpdate(AlarmTalkPower alarmTalkPower) {
		return alarmTalkPowerMapper.alarmTalkPowerUpdate(alarmTalkPower);
	}

	// 统计需填写约谈数量
	@Override
	public int alarmTalkFillCount(String hostId) {
		return alarmTalkMapper.alarmTalkFillCount(hostId);
	}

	// 局领导约谈列表查询
	@Override
	public List<AlarmTalk> alarmLeaderTalkPageList(String hostId, String dateTime, Integer viewType, Integer pageSize,
			Integer pageNum) {
		return alarmTalkMapper.alarmLeaderTalkPageList(hostId, dateTime, viewType, pageSize, pageNum);
	}

	// 局领导约谈列表时间查询
	@Override
	public List<TimeName> alarmDatePageList(String hostId, Integer viewType, Integer pageSize, Integer pageNum) {
		return alarmTalkMapper.alarmDatePageList(hostId, viewType, pageSize, pageNum);
	}

	@Override
	public String getExportExcelTitle(Integer scoreItem) {
		return alarmEvaluationMapper.getExportExcelTitle(scoreItem);
	}

	// 局领导约谈列表总数查询
	@Override
	public int alarmLeaderTalkPageCount(String hostId, String dateTime, Integer viewType) {
		return alarmTalkMapper.alarmLeaderTalkPageCount(hostId, dateTime, viewType);
	}

	// 查询当前人最新一条约谈记录
	@Override
	public List<AlarmTalk> newAlarmTalkList(String hostId) {
		return alarmTalkMapper.newAlarmTalkList(hostId);
	}

	@Override
	public Integer isHave(Integer departmentId, String policeId) {
		return alarmTalkPowerMapper.isHave(departmentId, policeId);
	}

	@Override
	public Integer alarmTalkPowerDelete(Integer id) {
		return alarmTalkPowerMapper.alarmTalkPowerDelete(id);
	}

	@Override
	public Integer checkAlarmTalkPower(String policeId, Integer id) {
		return alarmTalkPowerMapper.checkAlarmTalkPower(policeId, id);
	}

	@Override
	public List<String> findEvaltionTalk() {
		return alarmTalkPowerMapper.findEvaltionTalk();
	}

	// 查询约谈人约谈条件
	@Override
	public LeavePower powerItem(String scoringPoliceId, Integer departmentId) {
		return alarmTalkPowerMapper.powerItem(scoringPoliceId, departmentId);
	}

	// 局领导约谈对象预警民警查询
	@Override
	public List<AlarmNewest> alarmLeaderTalkConditionList(String scoredPoliceId, String scoringPoliceId) {
		return alarmEvaluationMapper.alarmLeaderTalkConditionList(scoredPoliceId, scoringPoliceId);
	}

	// 统计被约谈人需填写反馈数量
	@Override
	public int alarmObjectTalkFillCount(String policeId) {
		return alarmTalkMapper.alarmObjectTalkFillCount(policeId);
	}

	// 被约谈人约谈列表时间查询
	@Override
	public List<TimeName> alarmObjectDatePageList(String policeId, Integer viewType, Integer pageSize,
			Integer pageNum) {
		return alarmTalkMapper.alarmObjectDatePageList(policeId, viewType, pageSize, pageNum);
	}

	// 被约谈人约谈列表查询
	@Override
	public List<AlarmTalk> alarmObjectTalkPageList(String policeId, Integer viewType, Integer pageSize,
			Integer pageNum) {
		return alarmTalkMapper.alarmObjectTalkPageList(policeId, viewType, pageSize, pageNum);
	}

	// 被约谈人约谈列表总数查询
	@Override
	public int alarmObjectTalkPageCount(String policeId, Integer viewType) {
		return alarmTalkMapper.alarmObjectTalkPageCount(policeId, viewType);
	}

	@Override
	public int insertMore(List<AlarmEvaluation> alarmEvaluationList) {
		return alarmEvaluationMapper.insertMore(alarmEvaluationList);
	}

	// 局领导当前月预警提醒人数统计
	@Override
	public AlarmLeaderStatistics alarmLeaderRemindStatistics(String policeId) {
		return alarmEvaluationMapper.alarmLeaderRemindStatistics(policeId);
	}

	// 警员个人分值扣分查询
	@Override
	public List<LeaveChart> personalBuckleAnalysisList(String policeId, String scoredPoliceId, String name,
			String sort) {
		return alarmEvaluationMapper.personalBuckleAnalysisList(policeId, scoredPoliceId, name, sort);
	}

	// 警员个人分值加分查询
	@Override
	public List<LeaveChart> personalAddAnalysisList(String policeId, String scoredPoliceId, String name, String sort) {
		return alarmEvaluationMapper.personalAddAnalysisList(policeId, scoredPoliceId, name, sort);
	}

	@Override
	public List<AlarmScoreItem> getAllItemName() {
		return alarmScoreItemMapper.getAllItemName();
	}

	// 最近一年总预警数统计
	@Override
	public int personalAlarmTotalNum(String scoredPoliceId) {
		return alarmRecordMapper.personalAlarmTotalNum(scoredPoliceId);
	}

	// 扣分型警员分值分析列表
	@Override
	public List<AlarmPoliceScoreAnalysis> alarmBuckleScoreLimitList(String policeId, String dateTime) {
		return alarmEvaluationMapper.alarmBuckleScoreLimitList(policeId, dateTime);
	}

	// 加分型警员分值分析列表
	@Override
	public List<AlarmPoliceScoreAnalysis> alarmAddScoreLimitList(String policeId, String dateTime) {
		return alarmEvaluationMapper.alarmAddScoreLimitList(policeId, dateTime);
	}

	// 扣分型警员分值分析更多列表查询
	@Override
	public List<AlarmPoliceScoreAnalysis> alarmBuckleScoreMoreList(String policeId, Integer departmentId,
			String dateTime) {
		return alarmEvaluationMapper.alarmBuckleScoreMoreList(policeId, departmentId, dateTime);
	}

	// 加分型警员分值分析更多列表查询
	@Override
	public List<AlarmPoliceScoreAnalysis> alarmAddScoreMoreList(String policeId, Integer departmentId,
			String dateTime) {
		return alarmEvaluationMapper.alarmAddScoreMoreList(policeId, departmentId, dateTime);
	}

	// 局领导出入境预警统计
	@Override
	public AlarmLeaderStatistics alarmEntryAndExitStatistics(String policeId) {
		return alarmEntryAndExitRecordMapper.alarmEntryAndExitStatistics(policeId);
	}

	// 局领导出入境预警累计统计
	@Override
	public AlarmLeaderStatistics alarmLeaderEntryRecordItem(String policeId) {
		return alarmEntryAndExitRecordMapper.alarmLeaderEntryRecordItem(policeId);
	}

	// 局领导最新出入境预警查询
	@Override
	public List<AlarmEntryAndExitRecord> alarmLeaderEntryNewestList(String policeId) {
		return alarmEntryAndExitRecordMapper.alarmLeaderEntryNewestList(policeId);
	}

	// 局领导出入境预警数量统计
	@Override
	public int alarmLeaderEntryRecordPageCount(String policeId) {
		return alarmEntryAndExitRecordMapper.alarmLeaderEntryRecordPageCount(policeId);
	}

	// 局领导出入境预警分页查询
	@Override
	public List<AlarmEntryAndExitRecord> alarmLeaderEntryRecordPageList(String policeId, Integer pageSize,
			Integer pageNum) {
		return alarmEntryAndExitRecordMapper.alarmLeaderEntryRecordPageList(policeId, pageSize, pageNum);
	}

	// 局领导出入境预警详情查询
	@Override
	public AlarmEntryAndExitRecord alarmLeaderEntryItem(Integer id) {
		return alarmEntryAndExitRecordMapper.alarmLeaderEntryItem(id);
	}

	// 出入境预警新增
	@Override
	public int alarmEntryAndExitRecordCreat(AlarmEntryAndExitRecord record) {
		return alarmEntryAndExitRecordMapper.alarmEntryAndExitRecordCreat(record);
	}

	// 出入境预警修改
	@Override
	public int alarmEntryAndExitRecordUpdate(AlarmEntryAndExitRecord record) {
		return alarmEntryAndExitRecordMapper.alarmEntryAndExitRecordUpdate(record);
	}

	// 局领导催还护照记录新增
	@Override
	public int alarmUrgeRecordCreat(AlarmUrgeRecord record) {
		return alarmUrgeRecordMapper.alarmUrgeRecordCreat(record);
	}

	// 局领导催还护照记录修改
	@Override
	public int alarmUrgeRecordUpdate(AlarmUrgeRecord record) {
		return alarmUrgeRecordMapper.alarmUrgeRecordUpdate(record);
	}

	// 局领导催还护照记录详情查询
	@Override
	public AlarmUrgeRecord alarmUrgeRecordItem(Integer id, Integer entryId, String policeId, String urgePoliceId) {
		return alarmUrgeRecordMapper.alarmUrgeRecordItem(id, entryId, policeId, urgePoliceId);
	}

	// 个人护照催还通知最新查询
	@Override
	public List<AlarmUrgeRecord> alarmPersonalPassportReturnNewestList(String policeId, Integer readStatus) {
		return alarmUrgeRecordMapper.alarmPersonalPassportReturnNewestList(policeId, readStatus);
	}

	// 个人护照催还通知总数统计
	@Override
	public int alarmPersonalPassportReturnCount(String policeId, Integer readStatus) {
		return alarmUrgeRecordMapper.alarmPersonalPassportReturnCount(policeId, readStatus);
	}

	// 个人护照催还通知时间查询
	@Override
	public List<AlarmTimeName> alarmPassportDateList(String policeId, Integer readStatus, Integer pageSize,
			Integer pageNum) {
		return alarmUrgeRecordMapper.alarmPassportDateList(policeId, readStatus, pageSize, pageNum);
	}

	// 个人护照催还通知分页查询
	@Override
	public List<AlarmUrgeRecord> alarmPersonalPassportReturnList(String policeId, Integer readStatus, Integer pageSize,
			Integer pageNum) {
		return alarmUrgeRecordMapper.alarmPersonalPassportReturnList(policeId, readStatus, pageSize, pageNum);
	}

	// 局领导出入境预警趋势图
	@Override
	public List<CalculationChart> alarmLeaderEntryExitChart(String policeId) {
		return alarmEntryAndExitRecordMapper.alarmLeaderEntryExitChart(policeId);
	}

	// 局领导约谈人数趋势图
	@Override
	public List<CalculationChart> alarmLeaderTalkLineChart(String policeId) {
		return alarmTalkMapper.alarmLeaderTalkLineChart(policeId);
	}

	// 预警详情查询
	@Override
	public AlarmRecord alarmPoliceRecordItem(Integer id, String policeId, Integer alarmConfigType, Integer type) {
		return alarmRecordMapper.alarmPoliceRecordItem(id, policeId, alarmConfigType, type);
	}

	// 查询最新约谈详情
	@Override
	public AlarmTalk talkNewItem(Integer alarmRecordId, String policeId, Integer alarmType, Integer alarmConfigType,
			String dateTime) {
		return alarmTalkMapper.talkNewItem(alarmRecordId, policeId, alarmType, alarmConfigType, dateTime);
	}

	// 局领导出入境时间查询
	@Override
	public List<AlarmEntryTimeName> alarmEntryDatePageList(String policeId, Integer pageSize, Integer pageNum) {
		return alarmEntryAndExitRecordMapper.alarmEntryDatePageList(policeId, pageSize, pageNum);
	}

	// 个人扣分最新预警详情查询
	@Override
	public AlarmEvaluation alarmPersonalRecordBuckleItem(String policeId, String dateTime) {
		return alarmEvaluationMapper.alarmPersonalRecordBuckleItem(policeId, dateTime);
	}

	// 个人加分最新预警详情查询
	@Override
	public AlarmEvaluation alarmPersonalRecordAddItem(String policeId, String dateTime) {
		return alarmEvaluationMapper.alarmPersonalRecordAddItem(policeId, dateTime);
	}

	// 查询进行中的约谈
	@Override
	public List<AlarmTalk> talkExistList(String policeId, Integer alarmRecordId, Integer alarmType) {
		return alarmTalkMapper.talkExistList(policeId, alarmRecordId, alarmType);
	}

	@Override
	public Double alarmScoreByCondition(Integer configTimeType, Integer threshold) {
		// TODO Auto-generated method stub
		return alarmEvaluationMapper.alarmScoreByCondition(configTimeType, threshold);
	}

	@Override
	public Double evaluationScoreByCondition(String scoredPoliceId, Integer type, Integer configTimeType) {
		// TODO Auto-generated method stub
		return alarmEvaluationMapper.evaluationScoreByCondition(scoredPoliceId, type, configTimeType);
	}
	
	@Override
	public Integer alarmEntryExitNumWeek() {
		// TODO Auto-generated method stub
		return alarmEvaluationMapper.alarmEntryExitNumWeek();
	}
	
	@Override
	public Integer alarmNumWeek() {
		// TODO Auto-generated method stub
		return alarmEvaluationMapper.alarmNumWeek();
	}
	
	@Override
	public Integer alarmTalkComplateNumWeek() {
		// TODO Auto-generated method stub
		return alarmEvaluationMapper.alarmTalkComplateNumWeek();
	}
	
	@Override
	public List<AlarmEvaluation> newThreeTalk() {
		// TODO Auto-generated method stub
		return alarmTalkMapper.newThreeTalk();
	}

	// 警员局规计分数据列表查询
	@Override
	public List<AlarmEvaluation> riskConductBureauRuleRecordList(String policeId, String dateTime, String lastMonthTime,
																 Integer timeType) {
		return alarmEvaluationMapper.riskConductBureauRuleRecordList(policeId, dateTime, lastMonthTime, timeType);
	}

}

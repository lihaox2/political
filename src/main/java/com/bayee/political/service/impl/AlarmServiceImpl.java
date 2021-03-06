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
 * @version 2020???7???22??? ??????1:55:56
 */
@Service
public class AlarmServiceImpl implements AlarmService {

	@Autowired
	private AlarmTalkMapper alarmTalkMapper;// AI??????????????????

	@Autowired
	private AlarmEvaluationMapper alarmEvaluationMapper;// ???????????????

	@Autowired
	private AlarmPoliceMonthMapper alarmPoliceMonthMapper;// ?????????

	@Autowired
	private AlarmScoringBreakdownMapper alarmScoringBreakdownMapper;// ????????????

	@Autowired
	private AlarmScoringSonBreakdownMapper alarmScoringSonBreakdownMapper;// ???????????????

	@Autowired
	private AlarmScoreItemMapper alarmScoreItemMapper;// ????????????

	@Autowired
	private UserMapper userMapper;// ?????????

	@Autowired
	private AlarmRecordMapper alarmRecordMapper;// ????????????

	@Autowired
	private AlarmTalkPowerMapper alarmTalkPowerMapper;// ????????????

	@Autowired
	private AlarmEntryAndExitRecordMapper alarmEntryAndExitRecordMapper;// ?????????????????????

	@Autowired
	private AlarmUrgeRecordMapper alarmUrgeRecordMapper;// ???????????????????????????

	// ????????????????????????(??????)
	@Override
	public AlarmTalk alarmTalkItem(Integer id) {
		return alarmTalkMapper.alarmTalkItem(id);
	}

	// ??????????????????
	@Override
	public int alarmTalkCreat(AlarmTalk alarmTalk) {
		return alarmTalkMapper.alarmTalkCreat(alarmTalk);
	}

	// ??????????????????
	@Override
	public int alarmTalkUpdate(AlarmTalk alarmTalk) {
		return alarmTalkMapper.alarmTalkUpdate(alarmTalk);
	}

	// ????????????????????????(api)
	@Override
	public List<AlarmTalk> alarmTalkLastList(String hostId, String dateTime) {
		return alarmTalkMapper.alarmTalkLastList(hostId, dateTime);
	}

	// ????????????????????????(api)
	@Override
	public List<AlarmTalk> alarmTalkNewsList(String hostId) {
		return alarmTalkMapper.alarmTalkNewsList(hostId);
	}

	// ????????????????????????????????????
	@Override
	public AlarmTalkStatistics alarmTalkRateItem(String hostId, String dateTime) {
		return alarmTalkMapper.alarmTalkRateItem(hostId, dateTime);
	}

	// ????????????????????????
	@Override
	public AlarmTalkPoliceNum talkPoliceNumItem(String hostId, String dateTime) {
		return alarmTalkMapper.talkPoliceNumItem(hostId, dateTime);
	}

	// ???????????????????????????
	@Override
	public AlarmTalkPoliceNum noTalkPoliceNumItem(String hostId, String dateTime) {
		return alarmTalkMapper.noTalkPoliceNumItem(hostId, dateTime);
	}

	// ??????????????????
	@Override
	public AlarmPersonalStatistics alarmPersonalStatisticsItem(String policeId) {
		return alarmEvaluationMapper.alarmPersonalStatisticsItem(policeId);
	}

	// ??????????????????????????????
	@Override
	public List<AlarmTalk> alarmTalkPersonalList(String policeId, Integer isReceive) {
		return alarmTalkMapper.alarmTalkPersonalList(policeId, isReceive);
	}

	// ???????????????????????????????????????????????????
	@Override
	public int alarmPersonalNotConfirmNum(String policeId) {
		return alarmEvaluationMapper.alarmPersonalNotConfirmNum(policeId);
	}

	// ?????????????????????????????????????????????
	@Override
	public List<AlarmEvaluation> alarmPersonalNotConfirmList(String policeId) {
		return alarmEvaluationMapper.alarmPersonalNotConfirmList(policeId);
	}

	// ????????????????????????
	@Override
	public int alarmEvaluationupdate(AlarmEvaluation alarmEvaluation) {
		return alarmEvaluationMapper.alarmEvaluationupdate(alarmEvaluation);
	}

	// ??????????????????????????????
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

	// ???????????????????????????
	@Override
	public int alarmPersonalEvaluationNum(String policeId) {
		return alarmEvaluationMapper.alarmPersonalEvaluationNum(policeId);
	}

	// ??????????????????????????????
	@Override
	public List<AlarmPersonalEvaluation> alarmPersonalEvaluationList(String policeId, String dateTime) {
		return alarmEvaluationMapper.alarmPersonalEvaluationList(policeId, dateTime);
	}

	// ??????????????????????????????????????????
	@Override
	public List<AlarmEvaluation> alarmPersonalEvaluationHistoryBuckleList(String policeId, String dateTime,
			Integer policeMonthId, Integer type) {
		return alarmEvaluationMapper.alarmPersonalEvaluationHistoryBuckleList(policeId, dateTime, policeMonthId, type);
	}

	// ??????????????????????????????????????????
	@Override
	public List<AlarmEvaluation> alarmPersonalEvaluationHistoryAddList(String policeId, String dateTime,
			Integer policeMonthId, Integer type) {
		return alarmEvaluationMapper.alarmPersonalEvaluationHistoryAddList(policeId, dateTime, policeMonthId, type);
	}

	// ??????????????????????????????(?????????)
	@Override
	public List<LeaveChart> alarmEvaluationLastScoreList(String policeId) {
		return alarmEvaluationMapper.alarmEvaluationLastScoreList(policeId);
	}

	// ????????????????????????(?????????)
	@Override
	public List<LeaveChart> alarmAddScoreList(String policeId) {
		return alarmEvaluationMapper.alarmAddScoreList(policeId);
	}

	// ????????????????????????(?????????)
	@Override
	public List<LeaveChart> alarmBuckleScoreList(String policeId) {
		return alarmEvaluationMapper.alarmBuckleScoreList(policeId);
	}

	// ?????????????????????????????????
	@Override
	public List<LeaveChart> alarmPersonalEvaluationPieList(String policeId) {
		return alarmEvaluationMapper.alarmPersonalEvaluationPieList(policeId);
	}

	// ??????????????????????????????
	@Override
	public List<LeaveChart> alarmAddProjectList(String policeId, Integer type) {
		return alarmEvaluationMapper.alarmAddProjectList(policeId, type);
	}

	// ??????????????????????????????
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

	// ????????????????????????
	@Override
	public List<CalculationChart> alarmYearsList(String policeId) {
		return alarmEvaluationMapper.alarmYearsList(policeId);
	}

	// ????????????????????????????????????
	@Override
	public AlarmLeaderStatistics alarmLeaderStatistics(String policeId) {
		return alarmEvaluationMapper.alarmLeaderStatistics(policeId);
	}

	// ????????????????????????????????????
	@Override
	public AlarmLeaderStatistics alarmLastLeaderStatistics(String policeId) {
		return alarmEvaluationMapper.alarmLastLeaderStatistics(policeId);
	}

	// ???????????????????????????
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

	// ???????????????????????????????????????
	@Override
	public List<CalculationChart> alarmLeaderLineAddChart(String policeId) {
		return alarmEvaluationMapper.alarmLeaderLineAddChart(policeId);
	}

	// ??????????????????????????????
	@Override
	public List<CalculationChart> alarmLeaderLineBuckleChart(String policeId) {
		return alarmEvaluationMapper.alarmLeaderLineBuckleChart(policeId);
	}

	// ??????????????????????????????
	@Override
	public List<CalculationChart> alarmLeaderTypeBuckleChart(String policeId) {
		return alarmEvaluationMapper.alarmLeaderTypeBuckleChart(policeId);
	}

	// ??????????????????????????????
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

	// ???????????????????????????
	@Override
	public List<AlarmRecord> alarmLeaderNewestList(String policeId) {
		return alarmRecordMapper.alarmLeaderNewestList(policeId);
	}

	// ???????????????????????????
	@Override
	public AlarmLeaderStatistics alarmLeaderNewestItem(String policeId) {
		return alarmEvaluationMapper.alarmLeaderNewestItem(policeId);
	}

	// ??????????????????????????????
	@Override
	public AlarmEvaluation alarmNewestTime(String scoredPoliceId) {
		return alarmEvaluationMapper.alarmNewestTime(scoredPoliceId);
	}

	// ???????????????????????????
	@Override
	public List<AlarmTalk> alarmLeaderTalkList(String policeId) {
		return alarmTalkMapper.alarmLeaderTalkList(policeId);
	}

	// ?????????????????????????????????
	@Override
	public AlarmLeaderStatistics alarmLeaderTalkItem(String policeId) {
		return alarmTalkMapper.alarmLeaderTalkItem(policeId);
	}

	// ?????????????????????????????????
	@Override
	public List<AlarmNewest> alarmLeaderTalkObjectList(String scoredPoliceId, String scoringPoliceId) {
		return alarmEvaluationMapper.alarmLeaderTalkObjectList(scoredPoliceId, scoringPoliceId);
	}

	// ???????????????????????????
	@Override
	public List<AlarmRecord> alarmBuckleList(String policeId, Integer type, String dateTime, Integer pageSize,
			Integer pageNum) {
		return alarmRecordMapper.alarmBuckleList(policeId, type, dateTime, pageSize, pageNum);
	}

	// ?????????????????????????????????
	@Override
	public int alarmBuckleListCount(String policeId, Integer type, String dateTime) {
		return alarmRecordMapper.alarmBuckleListCount(policeId, type, dateTime);
	}

	// ?????????????????????????????????
	@Override
	public List<AlarmRecordTimeName> alarmRecordDatePageList(String policeId, Integer type, String dateTime,
			Integer pageSize, Integer pageNum) {
		return alarmRecordMapper.alarmRecordDatePageList(policeId, type, dateTime, pageSize, pageNum);
	}

	// ?????????????????????????????????
	@Override
	public List<AlarmRecord> alarmAddList(String policeId, String dateTime) {
		return alarmRecordMapper.alarmAddList(policeId, dateTime);
	}

	// ???????????????????????????
	@Override
	public List<AlarmRecordChart> alarmGetScoreBuckleItem(String policeId, Integer alarmConfigType, String dateTime) {
		return alarmEvaluationMapper.alarmGetScoreBuckleItem(policeId, alarmConfigType, dateTime);
	}

	// ???????????????????????????
	@Override
	public List<AlarmRecordChart> alarmGetScoreAddItem(String policeId, Integer alarmConfigType, String dateTime) {
		return alarmEvaluationMapper.alarmGetScoreAddItem(policeId, alarmConfigType, dateTime);
	}

	@Override
	public Integer getMonthAlarmReportCount(String departmentName, String ids, String keywords) {
		return alarmEvaluationMapper.getMonthAlarmReportCount(departmentName, ids, keywords);
	}

	// ???????????????????????????
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

	// ??????????????????
	@Override
	public int alarmRecordCreat(AlarmRecord alarmRecord) {
		return alarmRecordMapper.alarmRecordCreat(alarmRecord);
	}

	// ??????????????????
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

	// ??????????????????(????????????????????????????????????)
	@Override
	public List<AlarmTalk> alarmTalkStatusList() {
		return alarmTalkMapper.alarmTalkStatusList();
	}

	// ??????????????????????????????
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

	// ??????????????????????????????
	@Override
	public List<LeaveChart> alarmBuckleMaxList(String policeId) {
		return alarmEvaluationMapper.alarmBuckleMaxList(policeId);
	}

	// ??????????????????????????????
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

	// ??????????????????????????????
	@Override
	public DateTimeItem dateItems() {
		return alarmEvaluationMapper.dateItems();
	}

	// ??????????????????
	@Override
	public List<AlarmNewest> alarmBuckleRankList(String policeId, String startTime, String endTime) {
		return alarmEvaluationMapper.alarmBuckleRankList(policeId, startTime, endTime);
	}

	// ??????????????????
	@Override
	public List<AlarmNewest> alarmAddRankList(String policeId, String startTime, String endTime) {
		return alarmEvaluationMapper.alarmAddRankList(policeId, startTime, endTime);
	}

	@Override
	public Integer alarmTalkPowerUpdate(AlarmTalkPower alarmTalkPower) {
		return alarmTalkPowerMapper.alarmTalkPowerUpdate(alarmTalkPower);
	}

	// ???????????????????????????
	@Override
	public int alarmTalkFillCount(String hostId) {
		return alarmTalkMapper.alarmTalkFillCount(hostId);
	}

	// ???????????????????????????
	@Override
	public List<AlarmTalk> alarmLeaderTalkPageList(String hostId, String dateTime, Integer viewType, Integer pageSize,
			Integer pageNum) {
		return alarmTalkMapper.alarmLeaderTalkPageList(hostId, dateTime, viewType, pageSize, pageNum);
	}

	// ?????????????????????????????????
	@Override
	public List<TimeName> alarmDatePageList(String hostId, Integer viewType, Integer pageSize, Integer pageNum) {
		return alarmTalkMapper.alarmDatePageList(hostId, viewType, pageSize, pageNum);
	}

	@Override
	public String getExportExcelTitle(Integer scoreItem) {
		return alarmEvaluationMapper.getExportExcelTitle(scoreItem);
	}

	// ?????????????????????????????????
	@Override
	public int alarmLeaderTalkPageCount(String hostId, String dateTime, Integer viewType) {
		return alarmTalkMapper.alarmLeaderTalkPageCount(hostId, dateTime, viewType);
	}

	// ???????????????????????????????????????
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

	// ???????????????????????????
	@Override
	public LeavePower powerItem(String scoringPoliceId, Integer departmentId) {
		return alarmTalkPowerMapper.powerItem(scoringPoliceId, departmentId);
	}

	// ???????????????????????????????????????
	@Override
	public List<AlarmNewest> alarmLeaderTalkConditionList(String scoredPoliceId, String scoringPoliceId) {
		return alarmEvaluationMapper.alarmLeaderTalkConditionList(scoredPoliceId, scoringPoliceId);
	}

	// ???????????????????????????????????????
	@Override
	public int alarmObjectTalkFillCount(String policeId) {
		return alarmTalkMapper.alarmObjectTalkFillCount(policeId);
	}

	// ????????????????????????????????????
	@Override
	public List<TimeName> alarmObjectDatePageList(String policeId, Integer viewType, Integer pageSize,
			Integer pageNum) {
		return alarmTalkMapper.alarmObjectDatePageList(policeId, viewType, pageSize, pageNum);
	}

	// ??????????????????????????????
	@Override
	public List<AlarmTalk> alarmObjectTalkPageList(String policeId, Integer viewType, Integer pageSize,
			Integer pageNum) {
		return alarmTalkMapper.alarmObjectTalkPageList(policeId, viewType, pageSize, pageNum);
	}

	// ????????????????????????????????????
	@Override
	public int alarmObjectTalkPageCount(String policeId, Integer viewType) {
		return alarmTalkMapper.alarmObjectTalkPageCount(policeId, viewType);
	}

	@Override
	public int insertMore(List<AlarmEvaluation> alarmEvaluationList) {
		return alarmEvaluationMapper.insertMore(alarmEvaluationList);
	}

	// ??????????????????????????????????????????
	@Override
	public AlarmLeaderStatistics alarmLeaderRemindStatistics(String policeId) {
		return alarmEvaluationMapper.alarmLeaderRemindStatistics(policeId);
	}

	// ??????????????????????????????
	@Override
	public List<LeaveChart> personalBuckleAnalysisList(String policeId, String scoredPoliceId, String name,
			String sort) {
		return alarmEvaluationMapper.personalBuckleAnalysisList(policeId, scoredPoliceId, name, sort);
	}

	// ??????????????????????????????
	@Override
	public List<LeaveChart> personalAddAnalysisList(String policeId, String scoredPoliceId, String name, String sort) {
		return alarmEvaluationMapper.personalAddAnalysisList(policeId, scoredPoliceId, name, sort);
	}

	@Override
	public List<AlarmScoreItem> getAllItemName() {
		return alarmScoreItemMapper.getAllItemName();
	}

	// ??????????????????????????????
	@Override
	public int personalAlarmTotalNum(String scoredPoliceId) {
		return alarmRecordMapper.personalAlarmTotalNum(scoredPoliceId);
	}

	// ?????????????????????????????????
	@Override
	public List<AlarmPoliceScoreAnalysis> alarmBuckleScoreLimitList(String policeId, String dateTime) {
		return alarmEvaluationMapper.alarmBuckleScoreLimitList(policeId, dateTime);
	}

	// ?????????????????????????????????
	@Override
	public List<AlarmPoliceScoreAnalysis> alarmAddScoreLimitList(String policeId, String dateTime) {
		return alarmEvaluationMapper.alarmAddScoreLimitList(policeId, dateTime);
	}

	// ?????????????????????????????????????????????
	@Override
	public List<AlarmPoliceScoreAnalysis> alarmBuckleScoreMoreList(String policeId, Integer departmentId,
			String dateTime) {
		return alarmEvaluationMapper.alarmBuckleScoreMoreList(policeId, departmentId, dateTime);
	}

	// ?????????????????????????????????????????????
	@Override
	public List<AlarmPoliceScoreAnalysis> alarmAddScoreMoreList(String policeId, Integer departmentId,
			String dateTime) {
		return alarmEvaluationMapper.alarmAddScoreMoreList(policeId, departmentId, dateTime);
	}

	// ??????????????????????????????
	@Override
	public AlarmLeaderStatistics alarmEntryAndExitStatistics(String policeId) {
		return alarmEntryAndExitRecordMapper.alarmEntryAndExitStatistics(policeId);
	}

	// ????????????????????????????????????
	@Override
	public AlarmLeaderStatistics alarmLeaderEntryRecordItem(String policeId) {
		return alarmEntryAndExitRecordMapper.alarmLeaderEntryRecordItem(policeId);
	}

	// ????????????????????????????????????
	@Override
	public List<AlarmEntryAndExitRecord> alarmLeaderEntryNewestList(String policeId) {
		return alarmEntryAndExitRecordMapper.alarmLeaderEntryNewestList(policeId);
	}

	// ????????????????????????????????????
	@Override
	public int alarmLeaderEntryRecordPageCount(String policeId) {
		return alarmEntryAndExitRecordMapper.alarmLeaderEntryRecordPageCount(policeId);
	}

	// ????????????????????????????????????
	@Override
	public List<AlarmEntryAndExitRecord> alarmLeaderEntryRecordPageList(String policeId, Integer pageSize,
			Integer pageNum) {
		return alarmEntryAndExitRecordMapper.alarmLeaderEntryRecordPageList(policeId, pageSize, pageNum);
	}

	// ????????????????????????????????????
	@Override
	public AlarmEntryAndExitRecord alarmLeaderEntryItem(Integer id) {
		return alarmEntryAndExitRecordMapper.alarmLeaderEntryItem(id);
	}

	// ?????????????????????
	@Override
	public int alarmEntryAndExitRecordCreat(AlarmEntryAndExitRecord record) {
		return alarmEntryAndExitRecordMapper.alarmEntryAndExitRecordCreat(record);
	}

	// ?????????????????????
	@Override
	public int alarmEntryAndExitRecordUpdate(AlarmEntryAndExitRecord record) {
		return alarmEntryAndExitRecordMapper.alarmEntryAndExitRecordUpdate(record);
	}

	// ?????????????????????????????????
	@Override
	public int alarmUrgeRecordCreat(AlarmUrgeRecord record) {
		return alarmUrgeRecordMapper.alarmUrgeRecordCreat(record);
	}

	// ?????????????????????????????????
	@Override
	public int alarmUrgeRecordUpdate(AlarmUrgeRecord record) {
		return alarmUrgeRecordMapper.alarmUrgeRecordUpdate(record);
	}

	// ???????????????????????????????????????
	@Override
	public AlarmUrgeRecord alarmUrgeRecordItem(Integer id, Integer entryId, String policeId, String urgePoliceId) {
		return alarmUrgeRecordMapper.alarmUrgeRecordItem(id, entryId, policeId, urgePoliceId);
	}

	// ????????????????????????????????????
	@Override
	public List<AlarmUrgeRecord> alarmPersonalPassportReturnNewestList(String policeId, Integer readStatus) {
		return alarmUrgeRecordMapper.alarmPersonalPassportReturnNewestList(policeId, readStatus);
	}

	// ????????????????????????????????????
	@Override
	public int alarmPersonalPassportReturnCount(String policeId, Integer readStatus) {
		return alarmUrgeRecordMapper.alarmPersonalPassportReturnCount(policeId, readStatus);
	}

	// ????????????????????????????????????
	@Override
	public List<AlarmTimeName> alarmPassportDateList(String policeId, Integer readStatus, Integer pageSize,
			Integer pageNum) {
		return alarmUrgeRecordMapper.alarmPassportDateList(policeId, readStatus, pageSize, pageNum);
	}

	// ????????????????????????????????????
	@Override
	public List<AlarmUrgeRecord> alarmPersonalPassportReturnList(String policeId, Integer readStatus, Integer pageSize,
			Integer pageNum) {
		return alarmUrgeRecordMapper.alarmPersonalPassportReturnList(policeId, readStatus, pageSize, pageNum);
	}

	// ?????????????????????????????????
	@Override
	public List<CalculationChart> alarmLeaderEntryExitChart(String policeId) {
		return alarmEntryAndExitRecordMapper.alarmLeaderEntryExitChart(policeId);
	}

	// ??????????????????????????????
	@Override
	public List<CalculationChart> alarmLeaderTalkLineChart(String policeId) {
		return alarmTalkMapper.alarmLeaderTalkLineChart(policeId);
	}

	// ??????????????????
	@Override
	public AlarmRecord alarmPoliceRecordItem(Integer id, String policeId, Integer alarmConfigType, Integer type) {
		return alarmRecordMapper.alarmPoliceRecordItem(id, policeId, alarmConfigType, type);
	}

	// ????????????????????????
	@Override
	public AlarmTalk talkNewItem(Integer alarmRecordId, String policeId, Integer alarmType, Integer alarmConfigType,
			String dateTime) {
		return alarmTalkMapper.talkNewItem(alarmRecordId, policeId, alarmType, alarmConfigType, dateTime);
	}

	// ??????????????????????????????
	@Override
	public List<AlarmEntryTimeName> alarmEntryDatePageList(String policeId, Integer pageSize, Integer pageNum) {
		return alarmEntryAndExitRecordMapper.alarmEntryDatePageList(policeId, pageSize, pageNum);
	}

	// ????????????????????????????????????
	@Override
	public AlarmEvaluation alarmPersonalRecordBuckleItem(String policeId, String dateTime) {
		return alarmEvaluationMapper.alarmPersonalRecordBuckleItem(policeId, dateTime);
	}

	// ????????????????????????????????????
	@Override
	public AlarmEvaluation alarmPersonalRecordAddItem(String policeId, String dateTime) {
		return alarmEvaluationMapper.alarmPersonalRecordAddItem(policeId, dateTime);
	}

	// ????????????????????????
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

	// ????????????????????????????????????
	@Override
	public List<AlarmEvaluation> riskConductBureauRuleRecordList(String policeId, String dateTime, String lastMonthTime,
																 Integer timeType) {
		return alarmEvaluationMapper.riskConductBureauRuleRecordList(policeId, dateTime, lastMonthTime, timeType);
	}

}

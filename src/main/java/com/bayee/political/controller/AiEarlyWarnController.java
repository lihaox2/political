package com.bayee.political.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bayee.political.domain.AlarmConfig;
import com.bayee.political.domain.AlarmEntryAndExitRecord;
import com.bayee.political.domain.AlarmEntryTimeName;
import com.bayee.political.domain.AlarmEvaluation;
import com.bayee.political.domain.AlarmHistory;
import com.bayee.political.domain.AlarmLastScoreList;
import com.bayee.political.domain.AlarmLeaderLine;
import com.bayee.political.domain.AlarmLeaderStatistics;
import com.bayee.political.domain.AlarmLeaderType;
import com.bayee.political.domain.AlarmLine;
import com.bayee.political.domain.AlarmNewest;
import com.bayee.political.domain.AlarmNewestList;
import com.bayee.political.domain.AlarmPageStatisticsList;
import com.bayee.political.domain.AlarmPassportNoticeStatistics;
import com.bayee.political.domain.AlarmPersonalEvaluation;
import com.bayee.political.domain.AlarmPersonalStatistics;
import com.bayee.political.domain.AlarmPoliceMonth;
import com.bayee.political.domain.AlarmPoliceScoreAnalysis;
import com.bayee.political.domain.AlarmPoliceScoreAnalysisItem;
import com.bayee.political.domain.AlarmRecord;
import com.bayee.political.domain.AlarmRecordChart;
import com.bayee.political.domain.AlarmRecordItem;
import com.bayee.political.domain.AlarmRecordTimeName;
import com.bayee.political.domain.AlarmScoreAnalysis;
import com.bayee.political.domain.AlarmScoreItem;
import com.bayee.political.domain.AlarmScoringBreakdown;
import com.bayee.political.domain.AlarmScoringSonBreakdown;
import com.bayee.political.domain.AlarmTalk;
import com.bayee.political.domain.AlarmTalkItem;
import com.bayee.political.domain.AlarmTalkList;
import com.bayee.political.domain.AlarmTalkPage;
import com.bayee.political.domain.AlarmTalkPoliceNum;
import com.bayee.political.domain.AlarmTalkPower;
import com.bayee.political.domain.AlarmTalkStatistics;
import com.bayee.political.domain.AlarmTimeName;
import com.bayee.political.domain.AlarmUrgeRecord;
import com.bayee.political.domain.AlarmYear;
import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.DateTimeItem;
import com.bayee.political.domain.Department;
import com.bayee.political.domain.LeaveChart;
import com.bayee.political.domain.LeavePower;
import com.bayee.political.domain.ReportDetails;
import com.bayee.political.domain.TimeName;
import com.bayee.political.domain.User;
import com.bayee.political.service.AlarmService;
import com.bayee.political.service.DepartmentService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListPage;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DoExcel;
import com.bayee.political.utils.ExportExcelUtils;
import com.bayee.political.utils.GetExcel;
import com.bayee.political.utils.SelectExcel;
import com.bayee.political.utils.StatusCode;
import com.taobao.api.ApiException;

import cn.hutool.core.date.DateUtil;

@Controller
public class AiEarlyWarnController<E, V> extends BaseController {

	@Autowired
	private AlarmService alarmService;

	@Autowired
	private UserService userService;

	@Autowired
	private DepartmentService departmentService;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	String currentYearStr = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));

	DecimalFormat df = new DecimalFormat("#.00");

	SimpleDateFormat sdfs = new SimpleDateFormat("yyyy年MM月dd日");

	SimpleDateFormat currf = new SimpleDateFormat("yyyy-MM");

	SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * (scoreItems必填 为记分项目分类)查询所有或根据记分细目id、记分子细目id、 记分日期、警号、被记分人、记分查询
	 * 
	 * @param scoreItems
	 * @param scoringBreakdownId
	 * @param scoringSonBreakdownId
	 * @param scoringDate
	 * @param policeId
	 * @param policeName
	 * @return
	 */
	@RequestMapping(value = "/alarm/get_alarm_evaluation", method = RequestMethod.POST)
	public ResponseEntity<?> getAlarmEvaluationOrByCondition(@Param("scoreItems") Integer scoreItems,
			@Param("scoringBreakdownId") Integer scoringBreakdownId,
			@Param("scoringSonBreakdownId") Integer scoringSonBreakdownId, @Param("scoringDate") String scoringDate,
			@Param("keywords") String keywords, @Param("departmentId") Integer departmentId,
			@Param("searchDepId") Integer searchDepId, @Param("currentPage") Integer currentPage) {

		if (null == currentPage) {
			currentPage = 1;
		}

		List<AlarmEvaluation> selectAllOrByCondition = alarmService.selectAllOrByCondition(scoreItems,
				scoringBreakdownId, scoringSonBreakdownId, scoringDate, keywords, departmentId, searchDepId,
				(currentPage - 1) * 10);

		// 查询总数
		Integer count = alarmService.selectAllOrByConditionCount(scoreItems, scoringBreakdownId, scoringSonBreakdownId,
				scoringDate, keywords, departmentId, searchDepId);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("count", count);
		map.put("data", selectAllOrByCondition);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(map);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 查询所有的警员数据
	 *
	 * @return
	 */
	@RequestMapping(value = "/alarm/like_user", method = RequestMethod.POST)
	public ResponseEntity<?> likeName(@Param("departmentId") Integer departmentId) {

		List<User> userAllList = userService.allUser(departmentId);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(userAllList);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 添加预警评价
	 * 
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/alarm/add_alarm_evaluation", method = RequestMethod.POST)
	public ResponseEntity<?> addAlarmEvaluation(@Param("alarmEvaluation") AlarmEvaluation alarmEvaluation)
			throws ParseException {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		int policeMonth = alarmEvaluation.getPoliceMonthId();
		if (month == policeMonth) {
			alarmEvaluation.setScoringDate(new Date());
		} else {
			String dateTime = null;
			if (policeMonth < 10) {
				String monthString = "0" + policeMonth;
				dateTime = year + "-" + monthString + "-15 00:00:00";
			} else {
				dateTime = year + "-" + policeMonth + "-15 00:00:00";
			}
			alarmEvaluation.setScoringDate(sdf.parse(dateTime));
		}
		// 添加预警评价
		int insert = alarmService.insert(alarmEvaluation);

		// 检测预警评价记录是否达到年预警分数或月预警分数的处理方法
		isRecord(alarmEvaluation);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(insert);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 检测满足预警提示分数则记录预警记录表中
	 */
	private void isRecord(AlarmEvaluation alarmEvaluation) {

		// 月度预警检查
		yearOrMonAlarm(alarmEvaluation, 1);
		// 年度预警检查
		yearOrMonAlarm(alarmEvaluation, 2);

	}

	/**
	 * 年月度的预警
	 * 
	 * @param alarmEvaluation
	 * @param configTimeType  1月预警2年预警
	 */
	private void yearOrMonAlarm(AlarmEvaluation alarmEvaluation, Integer configTimeType) {

		// 检测本月是否达到预警
		// 预警类型
		int alarmType = alarmEvaluation.getType();

		// 月年度正负面+中性的分数
		Double monScore = alarmService.evaluationScoreByCondition(alarmEvaluation.getScoredPoliceId(), alarmType,
				configTimeType);

		// 月年度正面+中性分数的预警值
		Double alarmMonScore = alarmService.alarmScoreByCondition(configTimeType, alarmType);

		// 负面
		if (alarmType == 1) {
			if (monScore <= alarmMonScore) {
				// 插入预警
				queryInRecord(alarmEvaluation, monScore, configTimeType);
			}
		} else if (alarmType == 2) {
			// 正面
			if (monScore >= alarmMonScore) {
				// 插入预警
				queryInRecord(alarmEvaluation, monScore, configTimeType);
			}
		}

	}

	/**
	 * 查询添加的预警评价在预警评价记录表中是否存在
	 * 
	 * @param alarmEvaluation
	 * @param score           预警分数
	 * @param configType      1月预警2年预警
	 */
	private void queryInRecord(AlarmEvaluation alarmEvaluation, Double score, Integer configType) {

		// 查询本年月是否存在预警记录
		AlarmRecord record = alarmService.getAlarmRecord(alarmEvaluation.getScoredPoliceId(), alarmEvaluation.getType(),
				configType);
		// 存在即修改
		// 不存在即新增
		if (null == record) {
			// 新增
			AlarmRecord alarmRecord = new AlarmRecord();
			alarmRecord.setAlarmConfigType(configType);
			alarmRecord.setType(alarmEvaluation.getType());
			alarmRecord.setPoliceId(alarmEvaluation.getScoredPoliceId());
			// 根据policeId查询部门
			User user = userService.policeItem(alarmEvaluation.getScoredPoliceId(), null, null);
			alarmRecord.setDepartmentId(user.getDepartmentId());
			alarmRecord.setPoliceMonthId(alarmEvaluation.getPoliceMonthId());
			alarmRecord.setScore(score);
			alarmRecord.setFrequency(1);
			// 首次预警时间
			alarmRecord.setFirstAlarmTime(new Date());
			alarmRecord.setCreationDate(new Date());

			alarmService.alarmRecordCreat(alarmRecord);
		} else {
			// 修改
			AlarmRecord alarmRecord = new AlarmRecord();
			alarmRecord.setId(record.getId());
			alarmRecord.setScore(score);
			// 最终预警时间
			alarmRecord.setFinishAlarmTime(new Date());
			alarmRecord.setFrequency(record.getFrequency() + 1);
			alarmRecord.setUpdateDate(new Date());

			alarmService.alarmRecordUpdate(alarmRecord);
		}
	}

	/**
	 * 根据用户名获得用户
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/alarm/get_user", method = RequestMethod.POST)
	public ResponseEntity<?> getUserByName(@Param("name") String name) {

		List<User> findUserByName = userService.findUserByName(name);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(findUserByName);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 编辑预警评价
	 * 
	 * @param alarmEvaluation
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/alarm/edit_evaluation", method = RequestMethod.POST)
	public ResponseEntity<?> editEvaluation(@Param("alarmEvaluation") AlarmEvaluation alarmEvaluation)
			throws ParseException {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		int policeMonth = alarmEvaluation.getPoliceMonthId();
		if (month == policeMonth) {
			alarmEvaluation.setScoringDate(new Date());
		} else {
			String dateTime = null;
			if (policeMonth < 10) {
				String monthString = "0" + policeMonth;
				dateTime = year + "-" + monthString + "-15 00:00:00";
			} else {
				dateTime = year + "-" + policeMonth + "-15 00:00:00";
			}
			alarmEvaluation.setScoringDate(sdf.parse(dateTime));
		}
		int update = alarmService.updateByPrimaryKeySelective(alarmEvaluation);

		// 检测预警评价记录是否达到预警分数的处理方法
		isRecord(alarmEvaluation);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(update);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 根据id删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/alarm/delete_evaluation", method = RequestMethod.POST)
	public ResponseEntity<?> deleteEvaluation(@Param("id") Integer id) {

		int delete = alarmService.deleteByPrimaryKey(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(delete);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/alarm/select_evaluation", method = RequestMethod.POST)
	public ResponseEntity<?> selectEvaluation(@Param("id") Integer id) {

		AlarmEvaluation selectByPrimaryKey = alarmService.selectByPrimaryKey(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(selectByPrimaryKey);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 查询所有公安月
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alarm/get_police_month", method = RequestMethod.POST)
	public ResponseEntity<?> getAllPoliceMonth() {

		List<AlarmPoliceMonth> allMonth = alarmService.getAllMonth();

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(allMonth);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 获得所有记分细目
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alarm/get_all_breakdown", method = RequestMethod.POST)
	public ResponseEntity<?> getAllBreakDown() {

		List<AlarmScoringBreakdown> allScoringBreakdown = alarmService.getAllScoringBreakdown();

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(allScoringBreakdown);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 根据记分项目获得对应的记分细目
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alarm/get_breakdown_byprogram", method = RequestMethod.POST)
	public ResponseEntity<?> getBreakDownByProgram(@Param("scoreItems") Integer scoreItems) {

		List<AlarmScoringBreakdown> scoringBreakdown = alarmService.getBreakDownByProgram(scoreItems);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(scoringBreakdown);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 根据id获取记分项目对应的记分细目
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alarm/get_breakdown_byid", method = RequestMethod.POST)
	public ResponseEntity<?> getBreakDownById(@Param("id") String id) {

		List<AlarmScoringBreakdown> scoringBreakdown = alarmService.getBreakDownById(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(scoringBreakdown);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 获得所有记分子细目
	 * 
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = "/alarm/get_all_sonbreakdown", method = RequestMethod.POST)
	public ResponseEntity<?> getAllSonBreakDown() {

		List<AlarmScoringSonBreakdown> allScoringSonBreakdowns = alarmService.getAllScoringSonBreakdowns();

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(allScoringSonBreakdowns);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 根据记分细目id获得记分子细目
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alarm/get_sonbreakdown_parent", method = RequestMethod.POST)
	public ResponseEntity<?> getScoringSonBreakdownsByParentId(
			@Param("scoringBreakdownId") Integer scoringBreakdownId) {

		List<AlarmScoringSonBreakdown> ScoringSonBreakdowns = alarmService
				.getScoringSonBreakdownsByParentId(scoringBreakdownId);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(ScoringSonBreakdowns);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 总体概览
	 * 
	 * @param departmentId
	 * @param type         1月预警2年预警
	 * @return
	 */
	@RequestMapping(value = "/alarm/totality_overview", method = RequestMethod.POST)
	public ResponseEntity<?> totalityOverview(@Param("departmentId") Integer departmentId,
			@Param("type") Integer type) {

		// 本月参与总人数
		Integer theMonthTotal = alarmService.theMonthTotal(departmentId);
		// 本月总人数新增
		Integer theMonthNewAddTotal = alarmService.theMonthNewAddTotal(departmentId);
		// 本月扣分人数
		Integer theMonthPointsDeductedNum = alarmService.theMonthPointsDeductedNum(departmentId);
		// 本月新增扣分人数
		Integer theMonthNewAddPointsDeductedNum = alarmService.theMonthNewAddPointsDeductedNum(departmentId);
		// 本月加分人数
		Integer theMonthBonusPointsNum = alarmService.theMonthBonusPointsNum(departmentId);
		// 本月新增加分人数
		Integer theMonthNewAddBonusPointsNum = alarmService.theMonthNewAddBonusPointsNum(departmentId);
		// 本月加分预警人数
		Integer theMonthBonusPointsAlarmNum = alarmService.theMonthBonusPointsAlarmNum(departmentId, type);
		// 本月新增加分预警人数
		Integer theMonthNewAddBonusPointsAlarmNum = alarmService.theMonthNewAddBonusPointsAlarmNum(departmentId, type);
		// 本月扣分预警人数
		Integer theMonthPointsDeductedAlarmNum = alarmService.theMonthPointsDeductedAlarmNum(departmentId, type);
		// 本月新增扣分预警人数
		Integer theMonthNewAddPointsDeductedAlarmNum = alarmService.theMonthNewAddPointsDeductedAlarmNum(departmentId,
				type);

		LinkedHashMap<String, java.lang.Integer> map = new LinkedHashMap<String, Integer>();
		map.put("theMonthTotal", theMonthTotal);
		map.put("theMonthNewAddTotal", theMonthNewAddTotal);
		map.put("theMonthPointsDeductedNum", theMonthPointsDeductedNum);
		map.put("theMonthNewAddPointsDeductedNum", theMonthNewAddPointsDeductedNum);
		map.put("theMonthBonusPointsNum", theMonthBonusPointsNum);
		map.put("theMonthNewAddBonusPointsNum", theMonthNewAddBonusPointsNum);
		map.put("theMonthBonusPointsAlarmNum", theMonthBonusPointsAlarmNum);
		map.put("theMonthNewAddBonusPointsAlarmNum", theMonthNewAddBonusPointsAlarmNum);
		map.put("theMonthPointsDeductedAlarmNum", theMonthPointsDeductedAlarmNum);
		map.put("theMonthNewAddPointsDeductedAlarmNum", theMonthNewAddPointsDeductedAlarmNum);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(map);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 根据policeId查询所有未上传的记分项目
	 *
	 * @return
	 */
	@RequestMapping(value = "/alarm/get_notuploaded_project", method = RequestMethod.POST)
	public ResponseEntity<?> getNotUploadedProject(@Param("scoringPoliceId") String scoringPoliceId) {

		List<AlarmScoreItem> notUploaded = alarmService.notUploaded(scoringPoliceId);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(notUploaded);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 根据policeId查询所有未上传的记分指标
	 * 
	 * @param scoringPoliceId
	 * @return
	 */
	@RequestMapping(value = "/alarm/get_notuploaded_target", method = RequestMethod.POST)
	public ResponseEntity<?> getNotUploadedTarget(@Param("scoringPoliceId") String scoringPoliceId) {

		List<String> theMonthNotUpload = alarmService.theMonthNotUpload(scoringPoliceId);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(theMonthNotUpload);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 记分指标总览
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alarm/targetOverview", method = RequestMethod.POST)
	public ResponseEntity<?> targetOverview() {

		// 总指标
		Integer totalScoreTargetNum = alarmService.totalScoreTargetNum();
		// 扣分指标
		Integer deductedTargetNum = alarmService.scoreTargetNum(1);
		// 加分指标
		Integer bonusTargetNum = alarmService.scoreTargetNum(2);
		// 中性指标
		Integer neutralTargetNum = alarmService.scoreTargetNum(3);

		LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("totalScoreTargetNum", totalScoreTargetNum);
		map.put("deductedTargetNum", deductedTargetNum);
		map.put("bonusTargetNum", bonusTargetNum);
		map.put("neutralTargetNum", neutralTargetNum);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(map);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 记分提醒
	 * 
	 * @param scoringPoliceId
	 * @return
	 */
	@RequestMapping(value = "/alarm/score_tip", method = RequestMethod.POST)
	public ResponseEntity<?> scoreTip(@Param("scoringPoliceId") String scoringPoliceId) {

		List<String> scoreTip = alarmService.scoreTip(scoringPoliceId);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(scoreTip);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 根据scoringPoliceId获得记分审核
	 * 
	 * @param scoringPoliceId
	 * @return
	 */
	@RequestMapping(value = "/alarm/score_review", method = RequestMethod.POST)
	public ResponseEntity<?> reviewScore(@Param("scoringPoliceId") Integer scoringPoliceId) {

		List<AlarmEvaluation> scoreReview = alarmService.review(scoringPoliceId);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(scoreReview);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 根据thresholdType阈值类型修改预警分配置
	 * 
	 * @param thresholdType
	 * @param score
	 * @return
	 */
	@RequestMapping(value = "/alarm/update_alarm_config", method = RequestMethod.POST)
	public ResponseEntity<?> updateAlarmConfig(@Param("thresholdType") Integer thresholdType,
			@Param("score") Double score, @Param("type") Integer type) {

		Integer updateAlarmConfig = alarmService.updateAlarmConfig(thresholdType, score, type);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(updateAlarmConfig);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 获得预警配置
	 *
	 * @return
	 */
	@RequestMapping(value = "/alarm/get_alarm_config", method = RequestMethod.POST)
	public ResponseEntity<?> getAlarmConfig() {

		List<AlarmConfig> alarmConfig = alarmService.getAlarmConfig();

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(alarmConfig);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 获得约谈列表/根据条件查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alarm/get_alarm_talk", method = RequestMethod.POST)
	public ResponseEntity<?> getAlarmTalk(@Param("alrmTalk") AlarmTalk alrmTalk, @Param("keywords") String keywords,
			@Param("departmentIds") String departmentIds, @Param("pageSize") Integer pageSize) {

		if (null == pageSize) {
			pageSize = 1;
		}

		List<AlarmTalk> alarmTalk = alarmService.getAlarmTalk(alrmTalk, keywords, departmentIds, (pageSize - 1) * 10);

		Integer alarmTalkCount = alarmService.getAlarmTalkCount(alrmTalk, departmentIds, keywords);

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("alarmTalk", alarmTalk);
		map.put("count", alarmTalkCount);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(map);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 根据约谈id获得约谈
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alarm/get_alarm_talk_byid", method = RequestMethod.POST)
	public ResponseEntity<?> getAlarmTalkById(@Param("id") Integer id) {

		AlarmTalk alarmTalkItem = alarmService.alarmTalkItem(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(alarmTalkItem);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 查询所有单位
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alarm/get_department_list", method = RequestMethod.POST)
	public ResponseEntity<?> getDepartmentList(@Param("ids") String ids) {

		List<Department> departments = departmentService.getDepartmentByIds(ids);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(departments);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 月度考评预警趋势图
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alarm/month_evaluation_trendchart", method = RequestMethod.POST)
	public ResponseEntity<?> monthEvaluationTrendChart(@Param("departmentId") Integer departmentId) {

		// 加分
		List<AlarmPoliceMonth> monthBonus = alarmService.getMonthEvaluationTrendChart(1, departmentId);
		// 减分
		List<AlarmPoliceMonth> monthDeducted = alarmService.getMonthEvaluationTrendChart(2, departmentId);

		LinkedHashMap<String, List<AlarmPoliceMonth>> map = new LinkedHashMap<String, List<AlarmPoliceMonth>>();
		map.put("monthBonus", monthBonus);
		map.put("monthDeducted", monthDeducted);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(map);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 月度预警报表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alarm/month_alarm_report", method = RequestMethod.POST)
	public ResponseEntity<?> getMonthAlarmReport(@Param("departmentName") String departmentName,
			@Param("keywords") String keywords, @Param("ids") String ids, @Param("pageSize") Integer pageSize) {

		List<AlarmEvaluation> monthAlarmReport = alarmService.getMonthAlarmReport(departmentName, keywords, ids,
				(pageSize - 1) * 10);

		Integer monthAlarmReportCount = alarmService.getMonthAlarmReportCount(departmentName, ids, keywords);

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("list", monthAlarmReport);
		map.put("count", monthAlarmReportCount);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(map);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 季度预警报表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alarm/quarter_alarm_report", method = RequestMethod.POST)
	public ResponseEntity<?> getQuarterAlarmReport(@Param("departmentName") String departmentName,
			@Param("keywords") String keywords, @Param("ids") String ids, @Param("pageSize") Integer pageSize) {

		List<AlarmEvaluation> quarterAlarmReport = alarmService.getQuarterAlarmReport(departmentName, keywords, ids,
				(pageSize - 1) * 10);

		Integer quarterAlarmReportCount = alarmService.getQuarterAlarmReportCount(departmentName, ids, keywords);

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("list", quarterAlarmReport);
		map.put("count", quarterAlarmReportCount);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(map);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 全部预警报表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alarm/all_alarm_report", method = RequestMethod.POST)
	public ResponseEntity<?> getAllAlarmReport(@Param("departmentName") String departmentName,
			@Param("keywords") String keywords, @Param("ids") String ids, @Param("pageSize") Integer pageSize) {

		List<AlarmEvaluation> allAlarmReport = alarmService.getAllAlarmReport(departmentName, keywords, ids,
				(pageSize - 1) * 10);

		Integer allAlarmReportCount = alarmService.getaAllAlarmReportCount(departmentName, keywords, ids);

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("list", allAlarmReport);
		map.put("count", allAlarmReportCount);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(map);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 预警报表详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alarm/report_details", method = RequestMethod.POST)
	public ResponseEntity<?> reportDetails(@Param("conditionType") Integer conditionType,
			@Param("condition") Integer condition, @Param("year") Integer year,
			@Param("departmentId") Integer departmentId, @Param("pageSize") Integer pageSize) {

		List<ReportDetails> reportDetailsList = alarmService.getReportDetails(conditionType, condition, year,
				departmentId, (pageSize - 1) * 10);

		Integer count = alarmService.getReportDetailsCount(conditionType, condition, year, departmentId);

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("reportDetailsList", reportDetailsList);
		map.put("count", count);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(map);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 预警预警报表单位类型
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alarm/alarm_report_unit_type", method = RequestMethod.POST)
	public ResponseEntity<?> getAlarmReportUnitType() {

		List<AlarmEvaluation> unitType = alarmService.getUnitType();

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(unitType);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 待办事项
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alarm/wait_matter", method = RequestMethod.POST)
	public ResponseEntity<?> getToDoList(@Param("scoringPoliceId") String scoringPoliceId,
			@Param("pageSize") Integer pageSize) {

		if (pageSize == null) {
			pageSize = 1;
		}

		List<AlarmEvaluation> toDoList = alarmService.waitMatter(scoringPoliceId, (pageSize - 1) * 10);
		Integer waitMatterCount = alarmService.waitMatterCount(scoringPoliceId);

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("list", toDoList);
		map.put("count", waitMatterCount);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(map);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 已办事项
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alarm/done_matter", method = RequestMethod.POST)
	public ResponseEntity<?> getDoneList(@Param("scoringPoliceId") String scoringPoliceId,
			@Param("pageSize") Integer pageSize) {

		if (pageSize == null) {
			pageSize = 1;
		}

		List<AlarmEvaluation> doneList = alarmService.doneMatter(scoringPoliceId, (pageSize - 1) * 10);
		Integer doneMatterCount = alarmService.doneMatterCount(scoringPoliceId);

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("list", doneList);
		map.put("count", doneMatterCount);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(map);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 根据id操作待办事项
	 * 
	 * @mark 1是拒绝2是同意
	 */
	@RequestMapping(value = "/alarm/mark_matter", method = RequestMethod.POST)
	public ResponseEntity<?> markMatter(@Param("mark") Integer mark, @Param("id") Integer id) {

		Integer waitMatterMark = alarmService.waitMatterMark(mark, id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(waitMatterMark);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 最新两条负面记分
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alarm/newest_scoring", method = RequestMethod.POST)
	public ResponseEntity<?> newestScoring(@Param("scoringPoliceId") String scoringPoliceId) {

		List<AlarmEvaluation> newestScoring = alarmService.newestScoring(scoringPoliceId);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(newestScoring);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 导入Excel
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/alarm/import_excel", method = RequestMethod.POST)
	public ResponseEntity<?> importExcel(@Param("file") MultipartFile file) throws Exception {
		List<List<String>> readExcel = GetExcel.ReadExcel(file);

		List<AlarmEvaluation> list = new ArrayList<AlarmEvaluation>();

		// 记录错误行
		int index = 0;

		try {

			for (List<String> excel : readExcel) {
				// 累计错误行
				index++;
				// 插入数据
				AlarmEvaluation evaluation = new AlarmEvaluation();
				// 考评类型
				int type = "负面考评".equals(excel.get(0).toString()) ? 1 : "正面考评".equals(excel.get(0).toString()) ? 2 : 3;
				// 根据计分项目查询计分项目及计分类型
				AlarmScoreItem alarmScoreItem = alarmService.selectByName(type, excel.get(1).toString());
				evaluation.setType(alarmScoreItem.getDimension());
				evaluation.setScoreItems(alarmScoreItem.getId());
				evaluation.setScoredName(excel.get(2).toString());
				evaluation.setScoredPoliceId(excel.get(3).toString().trim().substring(0, 6));

				String mon = excel.get(4).toString();
				mon = mon.substring(0, mon.indexOf("月"));
				evaluation.setPoliceMonthId(Integer.parseInt(mon));

				Calendar cal = Calendar.getInstance();
				int month = cal.get(Calendar.MONTH) + 1;
				int year = cal.get(Calendar.YEAR);
				int policeMonth = Integer.parseInt(mon);
				if (month == policeMonth) {
					evaluation.setScoringDate(new Date());
				} else {
					String dateTime = null;
					if (policeMonth < 10) {
						String monthString = "0" + policeMonth;
						dateTime = year + "-" + monthString + "-15 00:00:00";
					} else {
						dateTime = year + "-" + policeMonth + "-15 00:00:00";
					}
					evaluation.setScoringDate(sdf.parse(dateTime));
				}

				if (null != excel.get(5) && "" != excel.get(5)) {
					// 根据计分细目名查询计分细目id
					Integer BreakdownId = alarmService.getIdByBreakdownName(excel.get(5).toString());
					evaluation.setScoringBreakdownId(BreakdownId);
					// 根据计分子细目名查询计分子细目id
					if (null != excel.get(6) && "" != excel.get(6)) {
						Integer sonBreakdownId = alarmService.getIdBySonBreakdownName(BreakdownId,
								excel.get(6).toString());
						evaluation.setScoringSonBreakdownId(sonBreakdownId);
					}
				}
				evaluation.setClause(excel.get(7).toString());
				evaluation.setScoringPoliceId(excel.get(9).toString().trim().substring(0, 6));
				evaluation.setScoreValue(Double.valueOf(excel.get(10).toString()));
				evaluation.setContent(excel.get(11).toString());
				/*
				 * try { evaluation.setScoringDate(DateUtils.toDate(excel.get(12).toString()));
				 * } catch (IndexOutOfBoundsException e) {
				 * 
				 * }
				 */
				// 读取成功的添加到集合
				list.add(evaluation);

			}

		} catch (Exception e) {

			e.printStackTrace();
			// StringWriter stringWriter = new StringWriter();
			// e.printStackTrace(new PrintWriter(stringWriter, true));
			// String str = stringWriter.toString();
			// String error = str.substring(str.indexOf(":"), str.indexOf(":")+30);

			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getFailcode());
			dataListReturn.setMessage("faile");
			dataListReturn.setResult("第" + index + "条数据错误,导入失败!");
			dataListReturn.setStatus(true);
			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

		}

		// 添加到数据库
		alarmService.insertMore(list);

		// 去重所有导入的人
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).getScoredPoliceId().equals(list.get(i).getScoredPoliceId())) {
					list.remove(j);
				}
			}
		}

		// 检测预警评价记录是否达到预警分数的处理方法
		for (AlarmEvaluation evaluation : list) {
			isRecord(evaluation);
		}

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(readExcel.get(0).get(0).toString() + "-" + readExcel.get(0).get(1).toString());
		dataListReturn.setStatus(true);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 考评维度导出Excel
	 */
	@RequestMapping(value = "/alarm/export_excel", method = RequestMethod.GET)
	public void importExcel(@Param("scoreItems") Integer scoreItems,
			@Param("scoringBreakdownId") Integer scoringBreakdownId,
			@Param("scoringSonBreakdownId") Integer scoringSonBreakdownId, @Param("scoringDate") String scoringDate,
			@Param("keywords") String keywords, @Param("departmentId") Integer departmentId,
			@Param("searchDepId") Integer searchDepId, HttpServletResponse response) {

		try {
			ExportExcelUtils.exportExcel(response, doExcel(scoreItems, scoringBreakdownId, scoringSonBreakdownId,
					scoringDate, keywords, departmentId, searchDepId));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 考评维度的excel生成
	 * 
	 * @param scoreItem
	 * @return
	 */
	private List<Map<String, Object>> doExcel(Integer scoreItems, Integer scoringBreakdownId,
			Integer scoringSonBreakdownId, String scoringDate, String keywords, Integer departmentId,
			Integer searchDepId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();

		String fileName = alarmService.getExportExcelTitle(scoreItems);
		String excelHeader[] = { "考评类型", "记分项目", "被记分人", "警号", "公安月", "记分细目", "记分子细目", "计分条款", "记分人", "警号 ", "记分分值",
				"记分依据", "记分日期" };
		String sheetName = fileName.substring(fileName.indexOf("-") + 1, fileName.indexOf("-") + 1 + 4);

		// 文件名
		map.put("fileName", fileName);
		// 表头
		map.put("excelHeader", excelHeader);
		// 数据
		map.put("keys", excelHeader);
		// sheet名
		map.put("sheetName", sheetName);

		list.add(map);

		// 查询所有负面考评
		List<AlarmEvaluation> alarmEvaluation = alarmService.selectAllOrByCondition(scoreItems, scoringBreakdownId,
				scoringSonBreakdownId, scoringDate, keywords, departmentId, searchDepId, null);

		for (int i = 0; i < alarmEvaluation.size(); i++) {
			LinkedHashMap<String, Object> mapBody = new LinkedHashMap<String, Object>();
			AlarmEvaluation alarm = alarmEvaluation.get(i);
			mapBody.put("考评类型", alarm.getTypeName());
			mapBody.put("记分项目", alarm.getItemName());
			mapBody.put("被记分人", alarm.getScoredName());
			mapBody.put("警号", alarm.getScoredPoliceId());
			mapBody.put("公安月", alarm.getPoliceMonthStr());
			mapBody.put("记分细目", alarm.getScoringBreakdown());
			mapBody.put("记分子细目", alarm.getScoringSonBreakdown());
			mapBody.put("计分条款", alarm.getClause());
			mapBody.put("记分人", alarm.getScoreingName());
			mapBody.put("警号 ", alarm.getScoringPoliceId());
			mapBody.put("记分分值", alarm.getScoreValue());
			mapBody.put("记分依据", alarm.getContent());
			mapBody.put("记分日期", DateUtil.format(alarm.getScoringDate(), "yyyy-MM-dd"));
			list.add(mapBody);
		}

		return list;

	}

	/**
	 * 约谈列表导出Excel
	 */
	@RequestMapping(value = "/alarm/interview_export_excel", method = RequestMethod.GET)
	public void exportInterviewExcel(
			@Param("alrmTalk") AlarmTalk alrmTalk, 
			@Param("keywords") String keywords,
			HttpServletResponse response) {

		try {
			ExportExcelUtils.exportExcel(response, doInterviewExcel(alrmTalk, keywords));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 约谈列表的excel生成
	 * 
	 * @return
	 */
	private List<Map<String, Object>> doInterviewExcel(AlarmTalk alrmTalk, String keywords) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		String fileName = "约谈列表" + DateUtil.format(new Date(), "yyyyMMddHHmmss");
		String excelHeader[] = { "约谈对象", "警号", "所属单位", "主题","约谈内容", "约谈意见", "约谈时间", "约谈者", "警号 ", "状态" };
		String sheetName = "约谈列表";
		// 文件名
		map.put("fileName", fileName);
		// 表头
		map.put("excelHeader", excelHeader);
		// 数据
		map.put("keys", excelHeader);
		// sheet名
		map.put("sheetName", sheetName);

		list.add(map);

		// 查询所有约谈列表
		List<AlarmTalk> alarmTalk = alarmService.getAlarmTalk(alrmTalk, keywords, null, null);

		for (int i = 0; i < alarmTalk.size(); i++) {

			LinkedHashMap<String, Object> mapBody = new LinkedHashMap<String, Object>();
			mapBody.put("约谈对象", alarmTalk.get(i).getPoliceName());
			mapBody.put("警号", alarmTalk.get(i).getPoliceId());
			mapBody.put("所属单位", alarmTalk.get(i).getDepartmentName());
			mapBody.put("主题", alarmTalk.get(i).getTitle());
			mapBody.put("约谈内容", alarmTalk.get(i).getContent());
			mapBody.put("约谈意见", alarmTalk.get(i).getFeedback());
			mapBody.put("约谈时间", DateUtil.format(alarmTalk.get(i).getStartTime(), "yyyy/MM/dd HH:mm") + " - "
					+ DateUtil.format(alarmTalk.get(i).getEndTime(), "yyyy/MM/dd HH:mm"));
			mapBody.put("约谈者", alarmTalk.get(i).getHostName());
			mapBody.put("警号 ", alarmTalk.get(i).getHostId());
			mapBody.put("状态", "已完成");
			list.add(mapBody);
		}

		/*
		 * alarmTalk.get(i).getStatus() == 1 ? "邀请中" : alarmTalk.get(i).getStatus() == 2
		 * ? "已同意" : alarmTalk.get(i).getStatus() == 3 ? "约谈中" :
		 * alarmTalk.get(i).getStatus() == 4 ? "已拒绝" : "已完成"
		 */

		return list;

	}

	/**
	 * 获得所有约谈权限
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alarm/talk_power", method = RequestMethod.POST)
	public ResponseEntity<?> getTalkPower(@Param("departmentId") Integer departmentId,
			@Param("keywords") String keywords, @Param("pageSize") Integer pageSize) {
		if (null == pageSize) {
			pageSize = 1;
		}
		List<AlarmTalkPower> alarmTalkPowerList = alarmService.getAlarmTalkPower(departmentId, keywords,
				(pageSize - 1) * 10);
		// 根据约谈对象警号查询约谈对象名称
		for (AlarmTalkPower power : alarmTalkPowerList) {
			List<User> users = userService.getUsersByPoliceId(power.getPoliceObjectIds().split(","));
			String names = "";
			for (User user : users) {
				names += user.getName() + ",";
			}
			if ("" != names) {
				names = names.substring(0, names.length() - 1);
			}
			power.setPoliceObjectNames(names);
			power.setPoliceNumber(users.size());
		}
		Integer alarmTalkPowerCount = alarmService.getAlarmTalkPowerCount(departmentId, keywords);
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("alarmTalkPowerList", alarmTalkPowerList);
		map.put("count", alarmTalkPowerCount);
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(map);
		dataListReturn.setStatus(true);
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 新增约谈权限
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alarm/add_talkpower", method = RequestMethod.POST)
	public ResponseEntity<?> addAlarmTalkPower(@Param("alarmTalkPower") AlarmTalkPower alarmTalkPower) {

		// 根据部门判断该部门下的某一约谈人是否已分配权限
		Integer have = alarmService.isHave(alarmTalkPower.getDepartmentId(), alarmTalkPower.getPoliceId());

		if (have > 0) {

			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getFailcode());
			dataListReturn.setMessage("faile");
			dataListReturn.setResult("该约谈人已分配约谈对象<br />仅可以编辑该约谈人");
			dataListReturn.setStatus(true);
			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

		} else {

			int insertSelective = alarmService.insertSelective(alarmTalkPower);

			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getSuccesscode());
			dataListReturn.setMessage("success");
			dataListReturn.setResult(insertSelective);
			dataListReturn.setStatus(true);
			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

		}

	}

	/**
	 * 根据部门id获得约谈人及约谈对象
	 *
	 * @return
	 */
	@RequestMapping(value = "/alarm/get_interview_obj", method = RequestMethod.POST)
	public ResponseEntity<?> getInterViewPoliceAndObj(@Param("departmentId") Integer departmentId,
			@Param("policeId") String policeId) {

		if (departmentId == 0 || null == departmentId) {
			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getSuccesscode());
			dataListReturn.setMessage("message");
			dataListReturn.setResult(null);
			dataListReturn.setStatus(true);

			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
		}

		// 根据单位获得约谈人
		List<User> police = userService.getUsersByDepUnitCard(departmentId);
		// 根据单位获得约谈对象
		// 根据用户表获得管理该部门的人员
		List<User> police2 = userService.getUsersByDepartmentId(departmentId);

		// 合并两个集合
		police.addAll(police2);

		// 去除重复
		for (int i = 0; i < police.size() - 1; i++) {
			for (int j = police.size() - 1; j > i; j--) {
				if (police.get(j).getPoliceId().equals(police.get(i).getPoliceId())) {
					police.remove(j);
				}
			}
		}

		// 根据单位获得约谈对象
		List<User> policeObj = null;
		if (null == policeId || "".equals(policeId)) {
			policeObj = userService.getUsersByNotDepUnitCard(departmentId, police.get(0).getPoliceId());
		} else {
			policeObj = userService.getUsersByNotDepUnitCard(departmentId, policeId);
		}

		LinkedHashMap<String, List<User>> map = new LinkedHashMap<String, List<User>>();
		map.put("police", police);
		map.put("policeObj", policeObj);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(map);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 获得所有局领导
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alarm/all_leaders", method = RequestMethod.POST)
	public ResponseEntity<?> getAllLeaders() {

		// 获得所有局领导
		List<User> allLeaders = userService.getAllLeaders();

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(allLeaders);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 根据局领导警号获得该局领导分管部门
	 * 
	 * @param policeId
	 * @return
	 */
	@RequestMapping(value = "/alarm/leaders_responsible_department", method = RequestMethod.POST)
	public ResponseEntity<?> getLeadersResponsibleDepartment(@Param("policeId") String policeId) {

		/*
		 * // 根据局领导警号获得该局领导分管部门 String responsibleDepartment =
		 * userService.getResponsibleDepartment(policeId); List<Department> departments
		 * = null; if (null != responsibleDepartment &&
		 * !"".equals(responsibleDepartment)) { //根据多个部门ids获得多个部门 departments =
		 * departmentService.getDepartmentByIds(responsibleDepartment); }
		 */

		// 查询机关单位和派出所
		// 机关单位
		List<Department> departments = departmentService.getDepartmentByType(2);
		// 派出所
		List<Department> policeStaion = departmentService.getDepartmentByType(3);

		departments.addAll(policeStaion);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(departments);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 查询多个部门分别对应的部门下的警员并除指定的policeId以外的
	 * 
	 * @param departmentId
	 * @param policeId
	 * @return
	 */
	@RequestMapping(value = "/alarm/get_more_department_police", method = RequestMethod.POST)
	public ResponseEntity<?> getMoreDepartmentPolice(@Param("departmentId") String departmentIds,
			@Param("policeId") String policeId) {

		ArrayList<List<User>> list = new ArrayList<List<User>>();

		String[] departmentId = departmentIds.split(",");

		for (int i = 0; i < departmentId.length; i++) {
			// 根据单位获得约谈对象
			List<User> policeObj = userService.getUsersByNotDepUnitCard(Integer.parseInt(departmentId[i]), policeId);
			list.add(policeObj);
		}

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(list);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 根据单位查询该单除了选中的约谈人以外的警员
	 * 
	 * @param departmentId
	 * @param policeId
	 * @return
	 */
	@RequestMapping(value = "/alarm/all_interview_obj", method = RequestMethod.POST)
	public ResponseEntity<?> getInterviewObj(@Param("departmentId") Integer departmentId,
			@Param("policeId") String policeId) {

		// 根据单位获得约谈对象
		List<User> policeObj = userService.getUsersByNotDepUnitCard(departmentId, policeId);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(policeObj);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 根据约谈权限id获得约谈详情
	 *
	 * @return
	 */
	@RequestMapping(value = "/alarm/get_alarmtalk_details", method = RequestMethod.POST)
	public ResponseEntity<?> getAlarmTalkPowerDetails(@Param("id") Integer id) {

		AlarmTalkPower alarmTalkPower = alarmService.getAlarmTalkPowerDetails(id);

		// 根据约谈对象警号查询约谈对象名称

		List<User> users = userService.getUsersByPoliceId(alarmTalkPower.getPoliceObjectIds().split(","));

		String names = "";

		for (User user : users) {

			names += user.getName() + ",";

		}

		if ("" != names) {
			names = names.substring(0, names.length() - 1);
		}

		alarmTalkPower.setPoliceObjectNames(names);
		alarmTalkPower.setPoliceNumber(users.size());

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(alarmTalkPower);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 修改约谈权限
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alarm/alarm_talk_update", method = RequestMethod.POST)
	public ResponseEntity<?> alarmTalkPowerUpdate(@Param("alarmTalkPower") AlarmTalkPower alarmTalkPower) {

		Integer updateCount = alarmService.alarmTalkPowerUpdate(alarmTalkPower);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(updateCount);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 删除约谈权限
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alarm/alarm_talk_delete", method = RequestMethod.POST)
	public ResponseEntity<?> alarmTalkPowerDelete(@Param("id") Integer id) {

		// 检验结果
		Boolean success = false;

		// 获得所有被记分人id及约谈对象id
		List<String> findEvaltionTalk = alarmService.findEvaltionTalk();
		// 遍历查询约谈权限表 是否引用
		for (String policeId : findEvaltionTalk) {
			Integer checkAlarmTalkPower = alarmService.checkAlarmTalkPower(policeId, id);
			if (checkAlarmTalkPower > 0) {
				success = true;
				break;
			}
		}

		DataListReturn dataListReturn = new DataListReturn();

		if (success) {
			dataListReturn.setCode(StatusCode.getFailcode());
			dataListReturn.setMessage("fail");
			dataListReturn.setResult("删除失败,该约谈权限已被引用!");
			dataListReturn.setStatus(true);
		} else {
			// 删除约谈权限
			alarmService.alarmTalkPowerDelete(id);

			dataListReturn.setCode(StatusCode.getSuccesscode());
			dataListReturn.setMessage("success");
			dataListReturn.setResult(null);
			dataListReturn.setStatus(true);
		}

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 制作Excel
	 *
	 * @return
	 */
	@RequestMapping(value = "/alarm/alarm_do_excel", method = RequestMethod.GET)
	public void alarmDoExcel(@Param("conditionType") Integer conditionType, @Param("condition") Integer condition,
			@Param("year") Integer year, @Param("departmentId") Integer departmentId, @Param("title") String title,
			HttpServletResponse response) {

		try {
			DoExcel.exportExcel(response, doExcel(conditionType, condition, year, departmentId, title));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private List<Map<String, Object>> doExcel(Integer conditionType, Integer condition, Integer year,
			Integer departmentId, String title) {

		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();

		String fileName = title;
		String excelHeader[] = { "被记分人", "警号", "分局记分", "自主记分", "督查提醒", "法制点评", "其他业务", "生活纪律", "工作纪律", "其他管理 ", "质能管理",
				"效能管理", " 其他管理", "群众反应", "媒体监督", "信访投诉", "立功嘉奖", "通报表扬", "其他表扬", "优质案件", "最佳案件", "工作效能", "其他管理", "家庭变动",
				"岗位调动", "其他情况", "总分(负面+中性)", "总分(正面+中性)" };
		String sheetName = title.substring(0, title.indexOf("考"));

		// 文件名
		map.put("fileName", fileName);
		// 表头
		map.put("excelHeader", excelHeader);
		// 数据
		map.put("keys", excelHeader);
		// sheet名
		map.put("sheetName", sheetName);

		list.add(map);

		List<ReportDetails> reportDetails = alarmService.getReportDetails(conditionType, condition, year, departmentId,
				null);

		for (int i = 0; i < reportDetails.size(); i++) {
			LinkedHashMap<String, Object> mapBody = new LinkedHashMap<String, Object>();
			mapBody.put("被记分人", reportDetails.get(i).getName());
			mapBody.put("警号", reportDetails.get(i).getScoredPoliceId());
			mapBody.put("分局记分", reportDetails.get(i).getBnum());
			mapBody.put("自主记分", reportDetails.get(i).getCnum());
			mapBody.put("督查提醒", reportDetails.get(i).getDnum());
			mapBody.put("法制点评", reportDetails.get(i).getEenum());
			mapBody.put("其他业务", reportDetails.get(i).getFnum());
			mapBody.put("生活纪律", reportDetails.get(i).getGnum());
			mapBody.put("工作纪律", reportDetails.get(i).getHnum());
			mapBody.put("其他管理 ", reportDetails.get(i).getInum());
			mapBody.put("质能管理", reportDetails.get(i).getJnum());
			mapBody.put("效能管理", reportDetails.get(i).getKnum());
			mapBody.put(" 其他管理", reportDetails.get(i).getLnum());
			mapBody.put("群众反应", reportDetails.get(i).getMnum());
			mapBody.put("媒体监督", reportDetails.get(i).getNnum());
			mapBody.put("信访投诉", reportDetails.get(i).getOnum());
			mapBody.put("立功嘉奖", reportDetails.get(i).getPnum());
			mapBody.put("通报表扬", reportDetails.get(i).getQnum());
			mapBody.put("其他表扬", reportDetails.get(i).getRnum());
			mapBody.put("优质案件", reportDetails.get(i).getSnum());
			mapBody.put("最佳案件", reportDetails.get(i).getTnum());
			mapBody.put("工作效能", reportDetails.get(i).getUnum());
			mapBody.put("其他管理", reportDetails.get(i).getVnum());
			mapBody.put("家庭变动", reportDetails.get(i).getWnum());
			mapBody.put("岗位调动", reportDetails.get(i).getXnum());
			mapBody.put("其他情况", reportDetails.get(i).getYnum());
			mapBody.put("总分(负面+中性)", String.format("%.2f",
					reportDetails.get(i).getZ1num() == null ? 0 : reportDetails.get(i).getZ1num()));
			mapBody.put("总分(正面+中性)", String.format("%.2f",
					reportDetails.get(i).getZ2num() == null ? 0 : reportDetails.get(i).getZ2num()));
			list.add(mapBody);
		}

		return list;

	}

	/**
	 * 获得模板
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/alarm/alarm_get_excel", method = RequestMethod.GET)
	public void alarmGetExcel(HttpServletResponse response) {

		String[] itemNames;
		String[] months;
		String[] breakDownNames;
		String[] sonBreakDownNames;

		// 初始化下拉选项
		List<AlarmScoreItem> allItemName = alarmService.getAllItemName();
		itemNames = new String[allItemName.size()];
		for (int i = 0; i < allItemName.size(); i++)
			itemNames[i] = allItemName.get(i).getItemName();

		List<AlarmPoliceMonth> allMonth = alarmService.getAllMonth();
		months = new String[allMonth.size()];
		for (int i = 0; i < allMonth.size(); i++)
			months[i] = allMonth.get(i).getPoliceMonth();

		List<AlarmScoringBreakdown> allScoringBreakdown = alarmService.getAllScoringBreakdown();
		breakDownNames = new String[allScoringBreakdown.size() + 1];
		breakDownNames[0] = "";
		for (int i = 0, j = 1; i < allScoringBreakdown.size(); i++, j++)
			breakDownNames[j] = allScoringBreakdown.get(i).getScoringBreakdown();

		List<AlarmScoringSonBreakdown> allScoringSonBreakdowns = alarmService.getAllScoringSonBreakdowns();
		sonBreakDownNames = new String[allScoringSonBreakdowns.size() + 1];
		sonBreakDownNames[0] = "";
		for (int i = 0, j = 1; i < allScoringSonBreakdowns.size(); i++, j++)
			sonBreakDownNames[j] = allScoringSonBreakdowns.get(i).getScoringSonBreakdown();

		try {
			new SelectExcel(itemNames, months, breakDownNames, sonBreakDownNames).exportExcel(response, getExcel());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private List<Map<String, Object>> getExcel() {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		String fileName = "模板导入";
		String excelHeader[] = { "考评类型", "记分项目", "被记分人", "警号", "公安月", "记分细目", "记分子细目", "计分条款", "记分人", "警号", "记分分值",
				"记分依据", "记分日期" };

		String sheetName = "sheet";
		// 文件名
		map.put("fileName", fileName);
		// 表头
		map.put("excelHeader", excelHeader);
		// 数据
		map.put("keys", excelHeader);
		// sheet名
		map.put("sheetName", sheetName);

		list.add(map);

		return list;

	}

	// 约谈记录详情查询
	@RequestMapping(value = "/alarm/talk/item", method = RequestMethod.GET)
	public ResponseEntity<?> alarmTalkItem(@RequestParam(value = "id", required = false) Integer id)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 约谈记录详情查询(后台)
		AlarmTalk alarmTalkItem = alarmService.alarmTalkItem(id);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(alarmTalkItem);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 局领导约谈记录详情查询
	@RequestMapping(value = "/alarm/leader/talk/item", method = RequestMethod.GET)
	public ResponseEntity<?> alarmLeaderTalkItem(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "alarmType", required = false) Integer alarmType) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		AlarmTalkItem aItem = new AlarmTalkItem();
		if (alarmType == 0) {
			// 约谈记录详情查询(后台)
			AlarmTalk alarmTalkItem = alarmService.alarmTalkItem(id);
			aItem.setTalkItem(alarmTalkItem);
		} else if (alarmType == 1) {
			// 约谈记录详情查询(后台)
			AlarmTalk alarmTalkItem = alarmService.alarmTalkItem(id);
			if (alarmTalkItem.getAlarmRecordId() != null) {
				AlarmRecord recordItem = alarmService.alarmPoliceRecordItem(alarmTalkItem.getAlarmRecordId(), null,
						null, null);
				if (recordItem != null) {
					aItem.setAlarmItem(recordItem);
				}
			}
			aItem.setTalkItem(alarmTalkItem);
		} else if (alarmType == 2) {
			// 约谈记录详情查询(后台)
			AlarmTalk alarmTalkItem = alarmService.alarmTalkItem(id);
			if (alarmTalkItem != null && alarmTalkItem.getAlarmRecordId() != null) {
				// 局领导出入境预警详情查询
				AlarmEntryAndExitRecord item = alarmService.alarmLeaderEntryItem(alarmTalkItem.getAlarmRecordId());
				if (item != null) {
					if (item.getTimeChange() > 0) {
						double currentScore = item.getTimeChange() / 1440.0;// 时长(天);
						String number = String.valueOf(currentScore);
						int index = number.indexOf(".");
						double douNumber1 = Double.valueOf(number.substring(0, index)) + 0.5;
						double douNumber2 = Double.valueOf(number.substring(0, index));
						if (douNumber2 == currentScore) {
							currentScore = douNumber2;
						} else {
							if (douNumber1 < currentScore) {
								currentScore = douNumber1;
							} else if (douNumber1 > currentScore) {
								currentScore = douNumber2;
							}
						}
						item.setOverdueDays(currentScore);
					}
					// 查询最新约谈详情
//				AlarmTalk talkItem = alarmService.talkNewItem(id, item.getPoliceId(), 2, null, null);
					aItem.setTalkItem(alarmTalkItem);
				}
				if (item.getOverdueDays() == null) {
					item.setOverdueDays(0.0);
				}
				aItem.setEntryItem(item);
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(aItem);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);

	}

	// **********AI预警******************调皮的分割线***********************api接口

	// 局领导考核预警,出入境预警,约谈人数统计
	@RequestMapping(value = "/alarm/leader/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> alarmLeaderStatistics(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 局领导当前年考核预警统计
		AlarmLeaderStatistics alarmLeader = alarmService.alarmLeaderStatistics(policeId);
		// 局领导上个月考核预警统计
//		AlarmLeaderStatistics alarmLastLeader = alarmService.alarmLastLeaderStatistics(policeId);
//		if (alarmLastLeader.getTotalNum() > 0) {
//			double difference = alarmLeader.getTotalNum() - alarmLastLeader.getTotalNum();
//			double proportion = difference / alarmLastLeader.getTotalNum() * 100;
//			DecimalFormat df = new DecimalFormat(".00");
//			alarmLeader.setProportion(Double.valueOf(df.format(proportion)));
//			alarmLeader.setIdentifier(1);
//		} else {
//			alarmLeader.setIdentifier(2);
//			alarmLeader.setProportion(null);
//		}
		// 局领导约谈人数统计
		AlarmLeaderStatistics alarmTalkItem = alarmService.alarmLeaderTalkStatistics(policeId);
		// 局领导出入境预警统计
		AlarmLeaderStatistics alarmEntryItem = alarmService.alarmEntryAndExitStatistics(policeId);
//		if (alarmEntryItem.getTotalNum() > 0) {
//			alarmEntryItem.setIdentifier(1);
//		} else {
//			alarmEntryItem.setIdentifier(2);
//			alarmEntryItem.setProportion(null);
//		}
		alarmLeader.setTotalNum(alarmLeader.getTotalNum() + alarmEntryItem.getTotalNum());
		AlarmLeaderType alarmLeaderType = new AlarmLeaderType();
		alarmLeaderType.setAlarmLeaderItem(alarmLeader);
		alarmLeaderType.setAlarmLeaderTalkItem(alarmTalkItem);
//		alarmLeaderType.setAlarmEntryAndExitItem(alarmEntryItem);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(alarmLeaderType);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 局领导预警人数趋势图
	@RequestMapping(value = "/alarm/leader/line/chart", method = RequestMethod.GET)
	public ResponseEntity<?> alarmLeaderLineChart(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "type", required = false) Integer type) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		List<CalculationChart> list = new ArrayList<CalculationChart>();
		AlarmLeaderLine aLine = new AlarmLeaderLine();
		if (type == null || type == 1) {// 考核预警
			// 局领导考核预警趋势图
			list = alarmService.alarmLeaderLineBuckleChart(policeId);
			aLine.setTotalNum(list.get(list.size() - 1).getNum());
			aLine.setChartList(list);
			if (list.size() < 2) {
				aLine.setIdentifier(2);
				aLine.setProportion(null);
			} else {
				if (list.get(list.size() - 2).getNum() > 0) {
					double difference = list.get(list.size() - 1).getNum() - list.get(list.size() - 2).getNum();
					double proportion = difference / list.get(list.size() - 2).getNum() * 100;
					DecimalFormat df = new DecimalFormat(".00");
					aLine.setProportion(Double.valueOf(df.format(proportion)));
					aLine.setIdentifier(1);
				} else {
					aLine.setIdentifier(2);
					aLine.setProportion(null);
				}
			}
		} else if (type == 2) {// 出入境预警
			// 局领导出入境预警趋势图
//			list = alarmService.alarmLeaderLineAddChart(policeId);
			list = alarmService.alarmLeaderEntryExitChart(policeId);
			aLine.setTotalNum(list.get(list.size() - 1).getNum());
			aLine.setChartList(list);
			if (list.size() < 2) {
				aLine.setIdentifier(2);
				aLine.setProportion(null);
			} else {
				if (list.get(list.size() - 2).getNum() > 0) {
					double difference = list.get(list.size() - 1).getNum() - list.get(list.size() - 2).getNum();
					double proportion = difference / list.get(list.size() - 2).getNum() * 100;
					DecimalFormat df = new DecimalFormat(".00");
					aLine.setProportion(Double.valueOf(df.format(proportion)));
					aLine.setIdentifier(1);
				} else {
					aLine.setIdentifier(2);
					aLine.setProportion(null);
				}
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(aLine);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 局领导约谈人数趋势图
	@RequestMapping(value = "/alarm/leader/talk/line/chart", method = RequestMethod.GET)
	public ResponseEntity<?> alarmLeaderTalkLineChart(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		AlarmLeaderLine aLine = new AlarmLeaderLine();
		// 局领导约谈人数趋势图
		List<CalculationChart> list = alarmService.alarmLeaderTalkLineChart(policeId);
		aLine.setTotalNum(list.get(list.size() - 1).getNum());
		aLine.setChartList(list);
		if (list.size() < 2) {
			aLine.setIdentifier(2);
			aLine.setProportion(null);
		} else {
			if (list.get(list.size() - 2).getNum() > 0) {
				double difference = list.get(list.size() - 1).getNum() - list.get(list.size() - 2).getNum();
				double proportion = difference / list.get(list.size() - 2).getNum() * 100;
				DecimalFormat df = new DecimalFormat(".00");
				aLine.setProportion(Double.valueOf(df.format(proportion)));
				aLine.setIdentifier(1);
			} else {
				aLine.setIdentifier(2);
				aLine.setProportion(null);
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(aLine);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 局领导加扣分人数趋势图
	@RequestMapping(value = "/alarm/leader/type/chart", method = RequestMethod.GET)
	public ResponseEntity<?> alarmLeaderTypeChart(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "type", required = false) Integer type) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		List<CalculationChart> list = new ArrayList<CalculationChart>();
		AlarmLeaderLine aLine = new AlarmLeaderLine();
		int addNum = 0;
		if (type == null || type == 1) {
			// 局领导扣分人数趋势图
			list = alarmService.alarmLeaderTypeBuckleChart(policeId);
//			for (int i = 0; i < list.size(); i++) {
//				addNum = list.get(i).getNum() + addNum;
//			}
			aLine.setTotalNum(list.get(list.size() - 1).getNum());
			aLine.setChartList(list);
			if (list.size() < 2) {
				aLine.setIdentifier(2);
				aLine.setProportion(null);
			} else {
				if (list.get(list.size() - 2).getNum() > 0) {
					double difference = list.get(list.size() - 1).getNum() - list.get(list.size() - 2).getNum();
					double proportion = difference / list.get(list.size() - 2).getNum() * 100;
					DecimalFormat df = new DecimalFormat(".00");
					aLine.setProportion(Double.valueOf(df.format(proportion)));
					aLine.setIdentifier(1);
				} else {
					aLine.setIdentifier(2);
					aLine.setProportion(null);
				}
			}
		} else if (type == 2) {
			// 局领导加分人数趋势图
			list = alarmService.alarmLeaderTypeAddChart(policeId);
//			for (int i = 0; i < list.size(); i++) {
//				addNum = list.get(i).getNum() + addNum;
//			}
			aLine.setTotalNum(list.get(list.size() - 1).getNum());
			aLine.setChartList(list);
			if (list.size() < 2) {
				aLine.setIdentifier(2);
				aLine.setProportion(null);
			} else {
				if (list.get(list.size() - 2).getNum() > 0) {
					double difference = list.get(list.size() - 1).getNum() - list.get(list.size() - 2).getNum();
					double proportion = difference / list.get(list.size() - 2).getNum() * 100;
					DecimalFormat df = new DecimalFormat(".00");
					aLine.setProportion(Double.valueOf(df.format(proportion)));
					aLine.setIdentifier(1);
				} else {
					aLine.setIdentifier(2);
					aLine.setProportion(null);
				}
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(aLine);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 局领导考核预警查询
	@RequestMapping(value = "/alarm/leader/newest/list", method = RequestMethod.GET)
	public ResponseEntity<?> alarmLeaderNewestList(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		AlarmNewestList list = new AlarmNewestList();
		// 局领导最新预警查询
		List<AlarmRecord> evaluationList = alarmService.alarmLeaderNewestList(policeId);
		evaluationList.removeAll(Collections.singleton(null));
		list.setAlarmNewestList(evaluationList);
		// 局领导最新预警统计
		AlarmLeaderStatistics statisticsItem = alarmService.alarmLeaderNewestItem(policeId);
		// 局领导当前月预警人数统计
		AlarmLeaderStatistics alarmLeader = alarmService.alarmLeaderStatistics(policeId);
		statisticsItem.setOverNum(alarmLeader.getTotalNum());
		list.setAlarmStatistics(statisticsItem);
		list.setAlarmTalkList(null);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 局领导出入境预警查询(2条)
	@RequestMapping(value = "/alarm/leader/entry/record/list", method = RequestMethod.GET)
	public ResponseEntity<?> alarmLeaderEntryRecordList(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		AlarmNewestList list = new AlarmNewestList();
		// 局领导最新出入境预警查询
		List<AlarmEntryAndExitRecord> list2 = alarmService.alarmLeaderEntryNewestList(policeId);
		list2.removeAll(Collections.singleton(null));
		if (list2.size() > 0) {
			for (int i = 0; i < list2.size(); i++) {
				int timeItem = Math.abs(list2.get(i).getTimeChange());
				if (timeItem == 0) {
					list2.get(i).setCreationDateStr("1分钟前");
				} else if (timeItem < 60 && timeItem > 0) {
					list2.get(i).setCreationDateStr(timeItem + "分钟前");
				} else if (timeItem >= 60 && timeItem < 1440) {
					int timeInt = (int) Math.floor(timeItem / 60);
					list2.get(i).setCreationDateStr(timeInt + "小时前");
				} else if (timeItem >= 1440 && timeItem <= 4320) {
					int timeInt = (int) Math.floor(timeItem / 60 / 24);
					list2.get(i).setCreationDateStr(timeInt + "天前");
				} else if (timeItem > 4320) {
					int yearInt = Integer.valueOf(currentYearStr);
					int createYear = Integer.valueOf(sdf.format(list2.get(i).getCreationDate()).substring(0, 4));
					if (yearInt == createYear) {
						list2.get(i).setCreationDateStr(sdfs.format(list2.get(i).getCreationDate()).substring(5, 11));
					} else {
						String timeString = sdfs.format(list2.get(i).getCreationDate());
						list2.get(i).setCreationDateStr(timeString);
					}
				}
			}
		}
		list.setAlarmEntryList(list2);
		// 局领导出入境预警统计
		AlarmLeaderStatistics statisticsItem = alarmService.alarmLeaderEntryRecordItem(policeId);
		list.setAlarmStatistics(statisticsItem);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 局领导出入境预警分页查询
	@RequestMapping(value = "/alarm/leader/entry/record/page/list", method = RequestMethod.GET)
	public ResponseEntity<?> alarmLeaderEntryRecordPageList(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageNum", required = false) Integer pageNum) throws ApiException {
		DataListPage dlr = new DataListPage();
		Integer pageNums = pageNum;
		if (pageSize == null) {
			pageSize = 10;
		}
		pageNum = ((pageNum) - 1) * pageSize;
		// 局领导出入境时间查询
		List<AlarmEntryTimeName> dateList = alarmService.alarmEntryDatePageList(policeId, pageSize, pageNum);
		// 局领导出入境预警数量统计
		int total = alarmService.alarmLeaderEntryRecordPageCount(policeId);
		// 局领导出入境预警分页查询
		List<AlarmEntryAndExitRecord> list2 = alarmService.alarmLeaderEntryRecordPageList(policeId, pageSize, pageNum);
		if (dateList.size() > 0) {
			for (int i = 0; i < dateList.size(); i++) {
				List<AlarmEntryAndExitRecord> aList = new ArrayList<AlarmEntryAndExitRecord>();
				for (int j = 0; j < list2.size(); j++) {
					if (dateList.get(i).getName().equals(list2.get(j).getStrTime())) {
						aList.add(list2.get(j));
					}
					int timeItem = Math.abs(list2.get(j).getTimeChange());
					if (timeItem == 0) {
						list2.get(j).setCreationDateStr("1分钟前");
					} else if (timeItem < 60 && timeItem > 0) {
						list2.get(j).setCreationDateStr(timeItem + "分钟前");
					} else if (timeItem >= 60 && timeItem < 1440) {
						int timeInt = (int) Math.floor(timeItem / 60);
						list2.get(j).setCreationDateStr(timeInt + "小时前");
					} else if (timeItem >= 1440 && timeItem <= 4320) {
						int timeInt = (int) Math.floor(timeItem / 60 / 24);
						list2.get(j).setCreationDateStr(timeInt + "天前");
					} else if (timeItem > 4320) {
						int yearInt = Integer.valueOf(currentYearStr);
						int createYear = Integer.valueOf(sdf.format(list2.get(j).getCreationDate()).substring(0, 4));
						if (yearInt == createYear) {
							list2.get(j)
									.setCreationDateStr(sdfs.format(list2.get(j).getCreationDate()).substring(5, 11));
						} else {
							String timeString = sdfs.format(list2.get(j).getCreationDate());
							list2.get(j).setCreationDateStr(timeString);
						}
					}
					// 查询最新约谈详情
					AlarmTalk talkItem = alarmService.talkNewItem(list2.get(j).getId(), list2.get(j).getPoliceId(), 2,
							null, null);
					if (talkItem != null) {
						list2.get(j).setIsTalk(talkItem.getStatus());
					} else {
						list2.get(j).setIsTalk(0);
					}
				}
				dateList.get(i).setRecordList(aList);
			}
		}
		List<AlarmEntryTimeName> totalList = new ArrayList<AlarmEntryTimeName>();
		if (dateList.size() > 0) {
			for (int i = 0; i < dateList.size(); i++) {
				if (dateList.get(i).getName() != null) {
					if (dateList.get(i).getName().length() == 7) {
						String currentTime = currf.format(new Date());
						String yearStr = dateList.get(i).getName().substring(0, 4);
						if (yearStr.equals(currentYearStr)) {
							if (dateList.get(i).getName().equals(currentTime)) {
								dateList.get(i).setName("本月");
							} else {
								dateList.get(i).setName(dateList.get(i).getName().substring(5, 7) + "月");
							}
						} else {
							dateList.get(i).setName(yearStr + "年" + dateList.get(i).getName().substring(5, 7) + "月");
						}
					} else if (dateList.get(i).getName().length() == 10) {
						dateList.get(i).setName("今日");
					}
				}
				if (dateList.get(i).getRecordList().size() > 0) {
					totalList.add(dateList.get(i));
				}
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(totalList);
		dlr.setCode(StatusCode.getSuccesscode());
		dlr.setTotal(total);
		dlr.setPageNum(pageNums);
		dlr.setPageSize(pageSize);
		dlr.setTotalPage(((int) Math.ceil((double) total / (double) pageSize)));
		int count = ((int) Math.ceil((double) total / (double) pageSize));
		if (count - pageNums > 0) {
			dlr.setPageNext(1);
		} else {
			dlr.setPageNext(0);
		}
		return new ResponseEntity<DataListPage>(dlr, HttpStatus.OK);
	}

	// 局领导出入境预警详情查询
	@RequestMapping(value = "/alarm/leader/entry/record/item", method = RequestMethod.GET)
	public ResponseEntity<?> alarmLeaderEntryItem(@RequestParam(value = "id", required = false) Integer id)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 局领导出入境预警详情查询
		AlarmEntryAndExitRecord item = alarmService.alarmLeaderEntryItem(id);
		if (item != null) {
			if (item.getTimeChange() > 0) {
				double currentScore = item.getTimeChange() / 1440.0;// 时长(天);
				String number = String.valueOf(currentScore);
				int index = number.indexOf(".");
				double douNumber1 = Double.valueOf(number.substring(0, index)) + 0.5;
				double douNumber2 = Double.valueOf(number.substring(0, index));
				if (douNumber2 == currentScore) {
					currentScore = douNumber2;
				} else {
					if (douNumber1 < currentScore) {
						currentScore = douNumber1;
					} else if (douNumber1 > currentScore) {
						currentScore = douNumber2;
					}
				}
				item.setOverdueDays(currentScore);
			}
			// 查询最新约谈详情
			AlarmTalk talkItem = alarmService.talkNewItem(id, item.getPoliceId(), 2, null, null);
			item.setTalkItem(talkItem);
			if (item.getOverdueDays() == null) {
				item.setOverdueDays(0.0);
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 局领导出入境催还护照
	@ResponseBody
	@RequestMapping(value = "/alarm/leader/entry/record/urge", method = RequestMethod.POST)
	public DataListReturn alarmLeaderEntryUrge(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "policeId", required = false) String policeId) throws IOException, ParseException {
		DataListReturn dlr = new DataListReturn();
		// 局领导出入境预警详情查询
		AlarmEntryAndExitRecord aitem = alarmService.alarmLeaderEntryItem(id);
		AlarmEntryAndExitRecord item = new AlarmEntryAndExitRecord();
		item.setId(id);
		item.setUrgeNum(aitem.getUrgeNum() + 1);
		item.setUpdateDate(new Date());
		int count = alarmService.alarmEntryAndExitRecordUpdate(item);
		if (count > 0) {
			AlarmUrgeRecord uitem = new AlarmUrgeRecord();
			uitem.setEntryId(id);
			uitem.setPoliceId(aitem.getPoliceId());
			uitem.setUrgeNotice("发来了催还护照通知");
			uitem.setUrgeContent("通知您尽快归还护照");
			uitem.setReadStatus(0);
			uitem.setUrgeReadStatus(0);
			uitem.setUrgePoliceId(policeId);
			uitem.setCreationDate(new Date());
			alarmService.alarmUrgeRecordCreat(uitem);
			dlr.setStatus(true);
			dlr.setMessage("催还成功");
			dlr.setResult(count);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("催还失败");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
		}
		return dlr;
	}

	// 局领导出入境护照归还确认
	@ResponseBody
	@RequestMapping(value = "/alarm/leader/passport/return/confirmation", method = RequestMethod.POST)
	public DataListReturn alarmLeaderPassport(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "policeId", required = false) String policeId) throws IOException, ParseException {
		DataListReturn dlr = new DataListReturn();
		// 局领导出入境预警详情查询
		AlarmEntryAndExitRecord item = alarmService.alarmLeaderEntryItem(id);
		item.setId(id);
		item.setIsReturn(1);
		item.setUrgePoliceId(policeId);
		item.setPassportReturnTime(new Date());
		item.setUpdateDate(new Date());
		int count = alarmService.alarmEntryAndExitRecordUpdate(item);
		if (count > 0) {
			dlr.setStatus(true);
			dlr.setMessage("确认成功");
			dlr.setResult(item);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("确认失败");
			dlr.setResult(null);
			dlr.setCode(StatusCode.getFailcode());
		}
		return dlr;
	}

	// 局领导约谈事项查询
	@RequestMapping(value = "/alarm/leader/talk/list", method = RequestMethod.GET)
	public ResponseEntity<?> alarmLeaderTalkList(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		AlarmNewestList list = new AlarmNewestList();
		// 局领导约谈事项查询
		List<AlarmTalk> talkList = alarmService.alarmLeaderTalkList(policeId);
		if (talkList.size() > 0) {
			for (int i = 0; i < talkList.size(); i++) {
				if (talkList.get(i).getStatus() == 1 && talkList.get(i).getIsReceive() == 0) {// 待约谈
					talkList.get(i).setViewType(1);
				} else if (talkList.get(i).getStatus() == 2 && talkList.get(i).getIsReceive() == 2) {// 已接受
					talkList.get(i).setViewType(2);
				} else if (talkList.get(i).getStatus() == 4 && talkList.get(i).getIsReceive() == 1) {// 已拒绝
					talkList.get(i).setViewType(3);
				} else if (talkList.get(i).getStatus() == 3 && talkList.get(i).getIsReceive() == 2) {// 需填写
					talkList.get(i).setViewType(4);
				} else if (talkList.get(i).getStatus() == 6 && talkList.get(i).getIsReceive() == 0) {// 已超时
					talkList.get(i).setViewType(5);
				} else if (talkList.get(i).getStatus() == 5 && talkList.get(i).getIsReceive() == 2) {// 已完成
					talkList.get(i).setViewType(8);
				} else if (talkList.get(i).getStatus() == 7 && talkList.get(i).getIsReceive() == 0) {// 已取消
					talkList.get(i).setViewType(10);
				} else {
					talkList.get(i).setViewType(0);
				}
			}
		}
		list.setAlarmTalkList(talkList);
		list.setAlarmNewestList(null);
		// 局领导约谈数量累计统计
		AlarmLeaderStatistics talkItem = alarmService.alarmLeaderTalkItem(policeId);
		list.setAlarmStatistics(talkItem);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 局领导约谈对象民警查询
	@RequestMapping(value = "/alarm/leader/talk/object/list", method = RequestMethod.GET)
	public ResponseEntity<?> alarmLeaderTalkObjectList(
			@RequestParam(value = "scoredPoliceId", required = false) String scoredPoliceId,
			@RequestParam(value = "scoringPoliceId", required = false) String scoringPoliceId,
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 查询约谈人约谈条件
		LeavePower powerItem = alarmService.powerItem(scoringPoliceId, departmentId);
		List<AlarmNewest> talkList = new ArrayList<AlarmNewest>();
		if (powerItem != null) {
			if (powerItem.getConditions() == 1) {
				// 局领导约谈对象预警民警查询
				talkList = alarmService.alarmLeaderTalkConditionList(scoredPoliceId, scoringPoliceId);
			} else if (powerItem.getConditions() == 2) {
				// 局领导约谈对象民警查询
				talkList = alarmService.alarmLeaderTalkObjectList(scoredPoliceId, scoringPoliceId);
			}
		}
		talkList.removeAll(Collections.singleton(null));
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(talkList);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 新增约谈记录
	@ResponseBody
	@RequestMapping(value = "/alarm/talk/creat", method = RequestMethod.POST)
	public DataListReturn taskCreatExecutor(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "alarmRecordId", required = false) Integer alarmRecordId,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "alarmType", required = false) Integer alarmType,
			@RequestParam(value = "talkType", required = false) Integer talkType,
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "message", required = false) String message,
			@RequestParam(value = "feedback", required = false) String feedback,
			@RequestParam(value = "reason", required = false) String reason,
			@RequestParam(value = "hostId", required = false) String hostId,
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "isReceive", required = false) Integer isReceive) throws IOException, ParseException {
		DataListReturn dlr = new DataListReturn();
		AlarmTalk item = new AlarmTalk();
		item.setAlarmRecordId(alarmRecordId);
		item.setTitle(title);
		item.setAlarmType(alarmType);
		item.setTalkType(talkType);
		item.setPoliceId(policeId);
		if (StringUtils.isEmpty(policeId)) {
			policeId = "1";
		}
		User userItemUser = userService.policeItem(policeId, null, null);
		if (userItemUser != null) {
			item.setDepartmentId(userItemUser.getDepartmentId());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("所填信息不全");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
		if (startTime != null) {
			startTime = startTime + ":00";
			item.setStartTime(sdf.parse(startTime));
		}
		if (endTime != null) {
			endTime = endTime + ":00";
			item.setEndTime(sdf.parse(endTime));
		}
		if (!StringUtils.isEmpty(content)) {
			item.setContent(content);
		}
		if (!StringUtils.isEmpty(feedback)) {
			item.setFeedback(feedback);
		}
		if (!StringUtils.isEmpty(reason)) {
			item.setReason(reason);
		}
		item.setMessage(message);
		item.setHostId(hostId);
		item.setStatus(1);
		item.setIsReceive(0);
		item.setCreationDate(new Date());
		item.setUpdateDate(new Date());
		long currentTimeLong = new Date().getTime();
		long startTimeLong = sdf.parse(startTime).getTime();
		long endTimeLong = sdf.parse(endTime).getTime();
		if (startTimeLong < currentTimeLong) {
			dlr.setStatus(false);
			dlr.setMessage("开始时间不能小于当前时间");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
		if (startTimeLong > endTimeLong) {
			dlr.setStatus(false);
			dlr.setMessage("开始时间不能大于结束时间");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
		if (endTimeLong - startTimeLong <= 300000) {
			dlr.setStatus(false);
			dlr.setMessage("时间间隔必须大于5分钟");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
		// 查询当前约谈人约谈时间记录
//		List<AlarmTalk> newAlarmTalkList = alarmService.newAlarmTalkList(hostId);
//		for (int i = 0; i < newAlarmTalkList.size(); i++) {
//			long startTimeNew = newAlarmTalkList.get(i).getStartTime().getTime();
//			long endTimeNew = newAlarmTalkList.get(i).getEndTime().getTime();
//			if (startTimeLong > endTimeNew || endTimeLong < startTimeNew) {
//
//			} else {
//				dlr.setStatus(false);
//				dlr.setMessage("当前时间段跟其他时间段冲突");
//				dlr.setResult(0);
//				dlr.setCode(StatusCode.getFailcode());
//				return dlr;
//			}
//		}
		// 查询进行中的约谈
		List<AlarmTalk> talkList = alarmService.talkExistList(policeId, alarmRecordId, alarmType);
		if (talkList.size() > 0) {
			dlr.setStatus(false);
			dlr.setMessage("还有未完成的约谈");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
		int count = alarmService.alarmTalkCreat(item);
		if (count > 0) {
			dlr.setStatus(true);
			dlr.setMessage("新增成功");
			dlr.setResult(count);
			dlr.setCode(StatusCode.getSuccesscode());
			return dlr;
		} else {
			dlr.setStatus(false);
			dlr.setMessage("新增失败");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
	}

	// 修改约谈记录
	@ResponseBody
	@RequestMapping(value = "/alarm/talk/update", method = RequestMethod.POST)
	public DataListReturn alarmTalkUpdate(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "alarmType", required = false) Integer alarmType,
			@RequestParam(value = "talkType", required = false) Integer talkType,
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "message", required = false) String message,
			@RequestParam(value = "feedback", required = false) String feedback,
			@RequestParam(value = "reason", required = false) String reason,
			@RequestParam(value = "hostId", required = false) String hostId,
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "isReceive", required = false) Integer isReceive) throws IOException, ParseException {
		DataListReturn dlr = new DataListReturn();
		AlarmTalk item = new AlarmTalk();
		// 约谈记录详情查询(后台)
		AlarmTalk alarmTalkItem = alarmService.alarmTalkItem(id);
		item.setId(id);
		item.setTitle(title);
		item.setAlarmType(alarmType);
		item.setTalkType(talkType);
		item.setPoliceId(policeId);
		User userItemUser = userService.policeItem(policeId, null, null);
		if (userItemUser != null) {
			item.setDepartmentId(userItemUser.getDepartmentId());
		}
		if (startTime != null) {
			item.setStartTime(sdf.parse(startTime));
		}
		if (endTime != null) {
			item.setEndTime(sdf.parse(endTime));
		}
		if (!StringUtils.isEmpty(content)) {
			item.setContent(content);
		}
		if (!StringUtils.isEmpty(feedback)) {
			item.setFeedback(feedback);
		}
		if (!StringUtils.isEmpty(reason)) {
			item.setReason(reason);
		}
		item.setMessage(message);
		item.setHostId(hostId);
		if (alarmTalkItem != null && endTime != null && startTime != null) {
			if (alarmTalkItem.getStatus() == 4) {
				item.setStatus(1);
				item.setIsReceive(0);
			}
		} else {
			item.setStatus(status);
			item.setIsReceive(2);
		}
		item.setUpdateDate(new Date());
		if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
			long currentTimeLong = new Date().getTime();
			long startTimeLong = sdf.parse(startTime).getTime();
			long endTimeLong = sdf.parse(endTime).getTime();
			if (startTimeLong < currentTimeLong) {
				dlr.setStatus(false);
				dlr.setMessage("开始时间不能小于当前时间");
				dlr.setResult(0);
				dlr.setCode(StatusCode.getFailcode());
				return dlr;
			}
			if (startTimeLong > endTimeLong) {
				dlr.setStatus(false);
				dlr.setMessage("开始时间不能大于结束时间");
				dlr.setResult(0);
				dlr.setCode(StatusCode.getFailcode());
				return dlr;
			}
			if (endTimeLong - startTimeLong <= 300000) {
				dlr.setStatus(false);
				dlr.setMessage("时间间隔必须大于5分钟");
				dlr.setResult(0);
				dlr.setCode(StatusCode.getFailcode());
				return dlr;
			}
			// 查询当前约谈人约谈时间记录
//			List<AlarmTalk> newAlarmTalkList = alarmService.newAlarmTalkList(hostId);
//			for (int i = 0; i < newAlarmTalkList.size(); i++) {
//				long startTimeNew = newAlarmTalkList.get(i).getStartTime().getTime();
//				long endTimeNew = newAlarmTalkList.get(i).getEndTime().getTime();
//				if (startTimeLong > endTimeNew || endTimeLong < startTimeNew) {
//
//				} else {
//					dlr.setStatus(false);
//					dlr.setMessage("当前时间段跟其他时间段冲突");
//					dlr.setResult(0);
//					dlr.setCode(StatusCode.getFailcode());
//					return dlr;
//				}
//			}
		}
		int count = alarmService.alarmTalkUpdate(item);
		if (count > 0) {
			dlr.setStatus(true);
			dlr.setMessage("修改成功");
			dlr.setResult(count);
			dlr.setCode(StatusCode.getSuccesscode());
			return dlr;
		} else {
			dlr.setStatus(false);
			dlr.setMessage("修改失败");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
	}

	// 约谈对象反馈修改
	@ResponseBody
	@RequestMapping(value = "/alarm/talk/message/update", method = RequestMethod.POST)
	public DataListReturn alarmTalkMessageUpdate(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "message", required = false) String message,
			@RequestParam(value = "feedback", required = false) String feedback,
			@RequestParam(value = "reason", required = false) String reason,
			@RequestParam(value = "isReceive", required = false) Integer isReceive) throws IOException, ParseException {
		DataListReturn dlr = new DataListReturn();
		AlarmTalk item = new AlarmTalk();
		item.setId(id);
		item.setIsReceive(isReceive);
		if (!StringUtils.isEmpty(feedback)) {
			item.setFeedback(feedback);
			item.setStatus(5);
		}
		item.setUpdateDate(new Date());
		int count = alarmService.alarmTalkUpdate(item);
		if (count > 0) {
			dlr.setStatus(true);
			dlr.setMessage("修改成功");
			dlr.setResult(count);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("修改失败");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
		}
		return dlr;
	}

	// 约谈对象修改状态
	@ResponseBody
	@RequestMapping(value = "/alarm/talk/status/update", method = RequestMethod.POST)
	public DataListReturn alarmTalkStatusUpdate(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "reason", required = false) String reason,
			@RequestParam(value = "isReceive", required = false) Integer isReceive) throws IOException, ParseException {
		DataListReturn dlr = new DataListReturn();
		AlarmTalk item = new AlarmTalk();
		item.setId(id);
		item.setIsReceive(isReceive);
		if (isReceive == 1) {
			item.setStatus(4);
			item.setReason(reason);
		} else if (isReceive == 2) {
			AlarmTalk itemTalks = alarmService.alarmTalkItem(id);
			long startTime = itemTalks.getStartTime().getTime();
			long endTime = itemTalks.getEndTime().getTime();
			long currentTime = new Date().getTime();
			if (startTime < currentTime && endTime < currentTime) {
				item.setStatus(3);
			} else {
				item.setStatus(2);
			}
		}

		item.setUpdateDate(new Date());
		int count = alarmService.alarmTalkUpdate(item);
		if (count > 0) {
			dlr.setStatus(true);
			dlr.setMessage("修改成功");
			dlr.setResult(count);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("修改失败");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
		}
		return dlr;
	}

	// 局领导预警列表查询
	@RequestMapping(value = "/alarm/list", method = RequestMethod.GET)
	public ResponseEntity<?> alarmList(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "dateTime", required = false) String dateTime,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageNum", required = false) Integer pageNum) throws ApiException {
		DataListPage dlr = new DataListPage();
		AlarmPageStatisticsList sItem = new AlarmPageStatisticsList();
		Integer pageNums = pageNum;
		if (pageSize == null) {
			pageSize = 10;
		}
		pageNum = ((pageNum) - 1) * pageSize;
		// 局领导预警列表时间查询
		List<AlarmRecordTimeName> dateList = alarmService.alarmRecordDatePageList(policeId, type, dateTime, pageSize,
				pageNum);
		// 局领导预警列表查询
		List<AlarmRecord> pageList = alarmService.alarmBuckleList(policeId, type, dateTime, pageSize, pageNum);
		if (pageList.size() > 0) {
			for (int i = 0; i < pageList.size(); i++) {
				// 查询最新约谈详情
				AlarmTalk talkItem = alarmService.talkNewItem(pageList.get(i).getId(), pageList.get(i).getPoliceId(), 1,
						null, null);
				if (talkItem != null) {
					pageList.get(i).setStatus(talkItem.getStatus());
				}
			}
		}
		// 局领导预警列表总数查询
		int total = alarmService.alarmBuckleListCount(policeId, type, dateTime);
		if (dateList.size() > 0) {
			for (int i = 0; i < dateList.size(); i++) {
				List<AlarmRecord> aList = new ArrayList<AlarmRecord>();
				for (int j = 0; j < pageList.size(); j++) {
					if (dateList.get(i).getName().equals(pageList.get(j).getStrTime())) {
						aList.add(pageList.get(j));
					}
				}
				dateList.get(i).setRecordList(aList);
			}
		}
		int total1 = alarmService.alarmBuckleListCount(policeId, 1, dateTime);
		int total2 = alarmService.alarmBuckleListCount(policeId, 2, dateTime);
		List<AlarmRecordTimeName> totalList = new ArrayList<AlarmRecordTimeName>();
		if (dateList.size() > 0) {
			for (int i = 0; i < dateList.size(); i++) {
				if (dateList.get(i).getName() != null) {
					if (dateList.get(i).getName().length() == 7) {
						String currentTime = currf.format(new Date());
						String yearStr = dateList.get(i).getName().substring(0, 4);
						if (yearStr.equals(currentYearStr)) {
							if (dateList.get(i).getName().equals(currentTime)) {
								dateList.get(i).setName("本月");
							} else {
								dateList.get(i).setName(dateList.get(i).getName().substring(5, 7) + "月");
							}
						} else {
							dateList.get(i).setName(yearStr + "年" + dateList.get(i).getName().substring(5, 7) + "月");
						}
					} else if (dateList.get(i).getName().length() == 10) {
						dateList.get(i).setName("今日");
					}
				}
				if (dateList.get(i).getRecordList().size() > 0) {
					totalList.add(dateList.get(i));
				}
			}
		}
		sItem.setTimeList(totalList);
		sItem.setBuckleNum(total1);
		sItem.setAddNum(total2);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(sItem);
		dlr.setCode(StatusCode.getSuccesscode());
		dlr.setTotal(total);
		dlr.setPageNum(pageNums);
		dlr.setPageSize(pageSize);
		dlr.setTotalPage(((int) Math.ceil((double) total / (double) pageSize)));
		int count = ((int) Math.ceil((double) total / (double) pageSize));
		if (count - pageNums > 0) {
			dlr.setPageNext(1);
		} else {
			dlr.setPageNext(0);
		}
		return new ResponseEntity<DataListPage>(dlr, HttpStatus.OK);
	}

	// 预警得分详情查询
	@RequestMapping(value = "/alarm/get/score/item", method = RequestMethod.GET)
	public ResponseEntity<?> alarmGetScoreItem(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "alarmConfigType", required = false) Integer alarmConfigType,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		if (StringUtils.isEmpty(dateTime)) {
			dateTime = sdf.format(new Date());
		}
		if (alarmConfigType == 1) {
			dateTime = dateTime.substring(0, 7);
		} else if (alarmConfigType == 2) {
			dateTime = dateTime.substring(0, 4);
		}
		// 预警详情查询
		AlarmRecord reItem = alarmService.alarmPoliceRecordItem(id, null, null, null);
		AlarmRecordItem item = new AlarmRecordItem();
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		item.setPoliceId(policeId);
		item.setName(user.getName());
		item.setDepartmentName(user.getDepartmentName());
		item.setPositionName(user.getPositionName());
		if (reItem != null) {
			item.setCreationDate(reItem.getFinishAlarmTime());
		}
		List<AlarmRecordChart> itemList = new ArrayList<AlarmRecordChart>();
		if (type == 1) {// 扣分型
			itemList = alarmService.alarmGetScoreBuckleItem(policeId, alarmConfigType, dateTime);
		} else if (type == 2) {// 加分型
			itemList = alarmService.alarmGetScoreAddItem(policeId, alarmConfigType, dateTime);
		}
//		item.setList(itemList.removeAll(Collections.singleton(null)));
		// 查询最新约谈详情
		AlarmTalk talkItem = alarmService.talkNewItem(id, policeId, 1, alarmConfigType, dateTime);
		item.setList(itemList);
		if (talkItem != null) {
			item.setTalkItem(talkItem);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 数据概况(本月)
	@RequestMapping(value = "/alarm/data/survey", method = RequestMethod.GET)
	public ResponseEntity<?> alarmData(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		if (StringUtils.isEmpty(dateTime)) {
			dateTime = sdf.format(new Date());
		}
		dateTime = dateTime.substring(0, 7);
		List<AlarmLeaderStatistics> list = new ArrayList<AlarmLeaderStatistics>();

		// 局领导当前月预警人数统计
		AlarmLeaderStatistics alarmLeader = alarmService.alarmLeaderStatistics(policeId);
		alarmLeader.setOverNum(1);
		// 局领导上个月预警人数统计
		AlarmLeaderStatistics alarmLastLeader = alarmService.alarmLastLeaderStatistics(policeId);
		if (alarmLastLeader.getTotalNum() > 0) {
			double difference = alarmLeader.getTotalNum() - alarmLastLeader.getTotalNum();
			double proportion = difference / alarmLastLeader.getTotalNum() * 100;
			DecimalFormat df = new DecimalFormat(".00");
			alarmLeader.setProportion(Double.valueOf(df.format(proportion)));
			alarmLeader.setIdentifier(1);
		} else {
			alarmLeader.setIdentifier(2);
			alarmLeader.setProportion(null);
		}
		list.add(alarmLeader);
		AlarmLeaderStatistics BuckleItem = new AlarmLeaderStatistics();
		// 局领导扣分人数趋势图
		List<CalculationChart> BuckleList = alarmService.alarmLeaderTypeBuckleChart(policeId);
		BuckleItem.setTotalNum(BuckleList.get(BuckleList.size() - 1).getNum());
		BuckleItem.setOverNum(2);
		if (BuckleList.size() < 2) {
			BuckleItem.setIdentifier(2);
			BuckleItem.setProportion(null);
		} else {
			if (BuckleList.get(BuckleList.size() - 2).getNum() > 0) {
				double difference = BuckleList.get(BuckleList.size() - 1).getNum()
						- BuckleList.get(BuckleList.size() - 2).getNum();
				double proportion = difference / BuckleList.get(BuckleList.size() - 2).getNum() * 100;
				DecimalFormat df = new DecimalFormat(".00");
				BuckleItem.setProportion(Double.valueOf(df.format(proportion)));
				BuckleItem.setIdentifier(1);
			} else {
				BuckleItem.setIdentifier(2);
				BuckleItem.setProportion(null);
			}
		}
		list.add(BuckleItem);
		AlarmLeaderStatistics addItem = new AlarmLeaderStatistics();
		// 局领导加分人数趋势图
		List<CalculationChart> addList = alarmService.alarmLeaderTypeAddChart(policeId);
		addItem.setTotalNum(addList.get(addList.size() - 1).getNum());
		addItem.setOverNum(3);
		if (addList.size() < 2) {
			addItem.setIdentifier(2);
			addItem.setProportion(null);
		} else {
			if (addList.get(addList.size() - 2).getNum() > 0) {
				double difference = addList.get(addList.size() - 1).getNum() - addList.get(addList.size() - 2).getNum();
				double lastNum = addList.get(addList.size() - 2).getNum();
				double proportion = difference / lastNum * 100;
				DecimalFormat df = new DecimalFormat(".00");
				addItem.setProportion(Double.valueOf(df.format(proportion)));
				addItem.setIdentifier(1);
			} else {
				addItem.setIdentifier(2);
				addItem.setProportion(null);
			}
		}
		list.add(addItem);
		// 局领导扣分最大值查询
		AlarmLeaderStatistics buckleMaxItem = new AlarmLeaderStatistics();
		List<LeaveChart> buckleMaxList = alarmService.alarmBuckleMaxList(policeId);
		buckleMaxItem.setTotalScore(buckleMaxList.get(buckleMaxList.size() - 1).getNum());
		buckleMaxItem.setOverNum(4);
		if (buckleMaxList.size() < 2) {
			buckleMaxItem.setIdentifier(2);
			buckleMaxItem.setProportion(null);
		} else {
			if (buckleMaxList.get(buckleMaxList.size() - 2).getNum() < 0) {
				double difference = buckleMaxList.get(buckleMaxList.size() - 1).getNum()
						- buckleMaxList.get(buckleMaxList.size() - 2).getNum();
				double lastNum = buckleMaxList.get(buckleMaxList.size() - 2).getNum();
				double proportion = difference / lastNum * 100;
				DecimalFormat df = new DecimalFormat(".00");
				buckleMaxItem.setProportion(Double.valueOf(df.format(proportion)));
				buckleMaxItem.setIdentifier(1);
			} else {
				buckleMaxItem.setIdentifier(2);
				buckleMaxItem.setProportion(null);
			}
		}

		list.add(buckleMaxItem);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 排行榜查询
	@RequestMapping(value = "/alarm/rank/list", method = RequestMethod.GET)
	public ResponseEntity<?> alarmRankList(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "dateType", required = false) Integer dateType) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		DateTimeItem dateItem = alarmService.dateItems();// 查询当前季度月度时间
		String startTime = null;
		String endTime = null;
		if (dateType == null || dateType == 1) {// 月度
			startTime = dateItem.getTimesMonthMorning();// 获取当月0点时间
			endTime = dateItem.getTimesMonthNight();// 获取当月24点时间
		} else if (dateType == 2) {// 季度
			startTime = dateItem.getCurrentQuarterStartTime();// 获取当季0点时间
			endTime = dateItem.getCurrentQuarterEndTime();// 获取当季24点时间
		}
		List<AlarmNewest> itemList = new ArrayList<AlarmNewest>();
		if (type == null || type == 1) {// 扣分型排行榜
			itemList = alarmService.alarmBuckleRankList(policeId, startTime, endTime);
		} else if (type == 2) {// 加分型排行榜
			itemList = alarmService.alarmAddRankList(policeId, startTime, endTime);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(itemList);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 每月最高加扣分值图
	@RequestMapping(value = "/alarm/leader/month/score/list", method = RequestMethod.GET)
	public ResponseEntity<?> alarmLeaderMonthScoreList(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 局领导扣分最大值查询
		List<LeaveChart> buckleMaxList = alarmService.alarmBuckleMaxList(policeId);
		// 局领导加分最大值查询
		List<LeaveChart> addMaxList = alarmService.alarmAddMaxList(policeId);
		AlarmLine line = new AlarmLine();
		line.setAddList(addMaxList);
		line.setBuckleList(buckleMaxList);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(line);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 局领导加扣分预警人数所占比
	@RequestMapping(value = "/alarm/add/buckle/proportion", method = RequestMethod.GET)
	public ResponseEntity<?> alarmAddBuckleProportion(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 扣分型
		List<CalculationChart> buckleList = alarmService.alarmLeaderTypeBuckleChart(policeId);
		// 加分型
		List<CalculationChart> addList = alarmService.alarmLeaderTypeAddChart(policeId);
		AlarmPersonalStatistics item = new AlarmPersonalStatistics();
		item.setAddNum(addList.get(addList.size() - 1).getNum());
		item.setBuckleNum(buckleList.get(buckleList.size() - 1).getNum());
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 约谈近期列表查询
	@RequestMapping(value = "/alarm/talk/last/list", method = RequestMethod.GET)
	public ResponseEntity<?> alarmTalkLastList(@RequestParam(value = "hostId", required = false) String hostId,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 约谈近期列表查询(api)
		if (StringUtils.isEmpty(dateTime)) {
			dateTime = sdf.format(new Date());
		}
		dateTime = dateTime.substring(0, 10);
		List<AlarmTalk> alarmTalkList = alarmService.alarmTalkLastList(hostId, dateTime);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(alarmTalkList);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 约谈消息列表查询
	@RequestMapping(value = "/alarm/talk/news/list", method = RequestMethod.GET)
	public ResponseEntity<?> alarmTalkNewsList(@RequestParam(value = "hostId", required = false) String hostId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 约谈近期列表查询(api)
		List<AlarmTalk> alarmTalkNewsList = alarmService.alarmTalkNewsList(hostId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(alarmTalkNewsList);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 约谈统计
	@RequestMapping(value = "/alarm/talk/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> alarmTalkStatistics(@RequestParam(value = "hostId", required = false) String hostId,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		if (StringUtils.isEmpty(dateTime)) {
			dateTime = sdf.format(new Date());
		}
		dateTime = dateTime.substring(0, 7);
		// 约谈完成率，未完成率查询
		AlarmTalkStatistics alarmTalkRateItem = alarmService.alarmTalkRateItem(hostId, null);
		// 约谈完成人数查询
		AlarmTalkPoliceNum talkPoliceNumItem = alarmService.talkPoliceNumItem(hostId, null);
		if (talkPoliceNumItem.getTotalNum() > 0) {
			alarmTalkRateItem.setCompletionPoliceNum(talkPoliceNumItem);
		}
		// 约谈未完成人数查询
		AlarmTalkPoliceNum noTalkPoliceNumItem = alarmService.noTalkPoliceNumItem(hostId, null);
		if (noTalkPoliceNumItem.getTotalNum() > 0) {
			alarmTalkRateItem.setUnfinishedNum(noTalkPoliceNumItem);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		if (alarmTalkRateItem != null) {
			AlarmTalkPoliceNum policeItem = new AlarmTalkPoliceNum();
			if (alarmTalkRateItem.getCompletionPoliceNum() == null) {
				policeItem.setAddNum(0);
				policeItem.setBuckleNum(0);
				policeItem.setTotalNum(0);
				alarmTalkRateItem.setCompletionPoliceNum(policeItem);
			}
			if (alarmTalkRateItem.getUnfinishedNum() == null) {
				policeItem.setAddNum(0);
				policeItem.setBuckleNum(0);
				policeItem.setTotalNum(0);
				alarmTalkRateItem.setUnfinishedNum(policeItem);
			}
			dlr.setResult(alarmTalkRateItem);
		} else {
			AlarmTalkStatistics item = new AlarmTalkStatistics();
			item.setCompletionRate(0);
			item.setUnfinishedRate(0);
			AlarmTalkPoliceNum policeItem = new AlarmTalkPoliceNum();
			policeItem.setAddNum(0);
			policeItem.setBuckleNum(0);
			policeItem.setTotalNum(0);
			item.setCompletionPoliceNum(policeItem);
			item.setUnfinishedNum(policeItem);
			dlr.setResult(item);
		}
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 各月已约谈人数趋势图
	@RequestMapping(value = "/alarm/talk/month/num/chart", method = RequestMethod.GET)
	public ResponseEntity<?> alarmTalkMonthNumChart(@RequestParam(value = "hostId", required = false) String hostId,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		if (StringUtils.isEmpty(dateTime)) {
			dateTime = sdf.format(new Date());
		}
		dateTime = dateTime.substring(0, 7);
		AlarmLeaderLine aLine = new AlarmLeaderLine();
		// 各月已约谈人数查询
		List<CalculationChart> list = alarmService.alarmTalkMonthNumChart(hostId);
		aLine.setChartList(list);
		// 本月约谈完成人数查询
		AlarmTalkPoliceNum talkPoliceNumItem = alarmService.talkPoliceNumItem(hostId, dateTime);
		aLine.setTotalNum(talkPoliceNumItem.getTotalNum());
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(aLine);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 局领导约谈列表查询以及统计需填写约谈数量
	@RequestMapping(value = "/alarm/leader/talk/page/list", method = RequestMethod.GET)
	public ResponseEntity<?> alarmLeaderTalkPageList(@RequestParam(value = "hostId", required = false) String hostId,
			@RequestParam(value = "viewType", required = false) Integer viewType,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageNum", required = false) Integer pageNum) throws ApiException {
		DataListPage dlr = new DataListPage();
		String dateTime = null;
		Integer pageNums = pageNum;
		if (pageSize == null) {
			pageSize = 10;
		}
		pageNum = ((pageNum) - 1) * pageSize;
		AlarmTalkPage list = new AlarmTalkPage();
		// 统计需填写约谈数量
		int num = alarmService.alarmTalkFillCount(hostId);
		list.setTotalNum(num);
		// 局领导约谈列表时间查询
		List<TimeName> dateList = alarmService.alarmDatePageList(hostId, viewType, pageSize, pageNum);
		list.setTimeList(dateList);
		// 局领导约谈列表查询
		List<AlarmTalk> pageList = alarmService.alarmLeaderTalkPageList(hostId, dateTime, viewType, pageSize, pageNum);
		if (pageList.size() > 0) {
			for (int i = 0; i < pageList.size(); i++) {
				if (pageList.get(i).getStatus() == 1 && pageList.get(i).getIsReceive() == 0) {// 待约谈
					pageList.get(i).setViewType(1);
				} else if (pageList.get(i).getStatus() == 2 && pageList.get(i).getIsReceive() == 2) {// 已接受
					pageList.get(i).setViewType(2);
				} else if (pageList.get(i).getStatus() == 4 && pageList.get(i).getIsReceive() == 1) {// 已拒绝
					pageList.get(i).setViewType(3);
				} else if (pageList.get(i).getStatus() == 3 && pageList.get(i).getIsReceive() == 2) {// 需填写
					pageList.get(i).setViewType(4);
				} else if (pageList.get(i).getStatus() == 6 && pageList.get(i).getIsReceive() == 0) {// 已超时
					pageList.get(i).setViewType(5);
				} else {
					pageList.get(i).setViewType(0);
				}
			}
		}
		// 局领导约谈列表总数查询
		int total = alarmService.alarmLeaderTalkPageCount(hostId, dateTime, viewType);
		if (dateList.size() > 0) {
			for (int i = 0; i < dateList.size(); i++) {
				List<AlarmTalk> aList = new ArrayList<AlarmTalk>();
				for (int j = 0; j < pageList.size(); j++) {
					if (dateList.get(i).getName().equals(pageList.get(j).getStrStartTime())) {
						aList.add(pageList.get(j));
					}
				}
				dateList.get(i).setTalkList(aList);
			}
		}
		List<TimeName> totalList = new ArrayList<TimeName>();
		if (dateList.size() > 0) {
			for (int i = 0; i < dateList.size(); i++) {
				if (dateList.get(i).getName() != null) {
					String yearStr = dateList.get(i).getName().substring(0, 4);
					String currentTime = dayFormat.format(new Date());
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -1);
					Date yestime = cal.getTime();
					String yesterdayStr = dayFormat.format(yestime);
					if (yearStr.equals(currentYearStr)) {
						if (dateList.get(i).getName().equals(currentTime)) {
							dateList.get(i).setName("今天");
						} else if (dateList.get(i).getName().equals(yesterdayStr)) {
							dateList.get(i).setName("昨天");
						} else {
							dateList.get(i).setName(dateList.get(i).getName().substring(5, 7) + "月"
									+ dateList.get(i).getName().substring(8, 10) + '日');
						}
					} else {
						dateList.get(i).setName(yearStr + "年" + dateList.get(i).getName().substring(5, 7) + "月"
								+ dateList.get(i).getName().substring(8, 10) + '日');
					}
				}
				if (dateList.get(i).getTalkList().size() > 0) {
					totalList.add(dateList.get(i));
				}
			}
		}
		list.setTimeList(totalList);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		dlr.setTotal(total);
		dlr.setPageNum(pageNums);
		dlr.setPageSize(pageSize);
		dlr.setTotalPage(((int) Math.ceil((double) total / (double) pageSize)));
		int count = ((int) Math.ceil((double) total / (double) pageSize));
		if (count - pageNums > 0) {
			dlr.setPageNext(1);
		} else {
			dlr.setPageNext(0);
		}
		return new ResponseEntity<DataListPage>(dlr, HttpStatus.OK);
	}

	// 个人预警统计
	@RequestMapping(value = "/alarm/personal/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> personalStatistics(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 个人预警统计
		AlarmPersonalStatistics alarmItem = alarmService.alarmPersonalStatisticsItem(policeId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(alarmItem);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人最新记分项数量统计（最近一周）
	@RequestMapping(value = "/alarm/personal/not/confirm", method = RequestMethod.GET)
	public ResponseEntity<?> alarmPersonalNotConfirm(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 个人最新记分项数量统计（最近一周）
		int count = alarmService.alarmPersonalNotConfirmNum(policeId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(count);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人最新记分项查询（最近一周）
	@RequestMapping(value = "/alarm/personal/not/confirm/list", method = RequestMethod.GET)
	public ResponseEntity<?> alarmPersonalNotConfirmList(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 个人最新记分项查询（最近一周）
		List<AlarmEvaluation> alarmPersonalNotConfirmList = alarmService.alarmPersonalNotConfirmList(policeId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(alarmPersonalNotConfirmList);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人谈话预约列表查询
	@RequestMapping(value = "/alarm/talk/personal/list", method = RequestMethod.GET)
	public ResponseEntity<?> alarmTalkPersonalList(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		AlarmTalkList list = new AlarmTalkList();
		// 个人谈话完成数量统计
		int count = alarmService.alarmTalkOverNum(policeId);
		list.setTotalNum(count);
		// 个人谈话预约列表查询
		List<AlarmTalk> alarmList = alarmService.alarmTalkPersonalList(policeId, null);
		for (int i = 0; i < alarmList.size(); i++) {
			if (alarmList.get(i).getStatus() == 1 && alarmList.get(i).getIsReceive() == 0) {// 待约谈
				alarmList.get(i).setViewType(1);
			} else if (alarmList.get(i).getStatus() == 2 && alarmList.get(i).getIsReceive() == 2) {// 已接受
				alarmList.get(i).setViewType(2);
			} else if (alarmList.get(i).getStatus() == 4 && alarmList.get(i).getIsReceive() == 1) {// 已拒绝
				alarmList.get(i).setViewType(3);
			} else if (alarmList.get(i).getStatus() == 3 && alarmList.get(i).getIsReceive() == 2) {// 需填写
				alarmList.get(i).setViewType(4);
			} else if (alarmList.get(i).getStatus() == 6 && alarmList.get(i).getIsReceive() == 0) {// 已超时
				alarmList.get(i).setViewType(5);
			} else if (alarmList.get(i).getStatus() == 5 && alarmList.get(i).getIsReceive() == 2) {// 已完成
				alarmList.get(i).setViewType(8);
			} else if (alarmList.get(i).getStatus() == 7 && alarmList.get(i).getIsReceive() == 0) {// 已取消
				alarmList.get(i).setViewType(10);
			} else {
				alarmList.get(i).setViewType(0);
			}
		}
		if (alarmList.size() > 1) {
			if (alarmList.get(0).getViewType() == 1 && alarmList.get(1).getViewType() == 1) {//
				alarmList.get(1).setViewType(6);
			}
		}
		list.setTalkList(alarmList);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 修改警预约评价记录
	@ResponseBody
	@RequestMapping(value = "/alarm/evaluation/update", method = RequestMethod.POST)
	public DataListReturn alarmEvaluationUpdate(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "isTrue", required = false) Integer isTrue,
			@RequestParam(value = "reason", required = false) String reason) throws IOException, ParseException {
		DataListReturn dlr = new DataListReturn();
		AlarmEvaluation item = new AlarmEvaluation();
		item.setId(id);
		item.setStatus(status);
		item.setIsTrue(isTrue);
		item.setReason(reason);
		item.setUpdateDate(new Date());
		int count = alarmService.alarmEvaluationupdate(item);
		if (count > 0) {
			dlr.setStatus(true);
			dlr.setMessage("修改成功");
			dlr.setResult(count);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("修改失败");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
		}
		return dlr;
	}

	// 预警评价记录详情查询
	@RequestMapping(value = "/alarm/evaluation/item", method = RequestMethod.GET)
	public ResponseEntity<?> alarmEvaluationItem(@RequestParam(value = "id", required = false) Integer id)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 预警评价记录详情查询
		AlarmEvaluation item = alarmService.alarmEvaluationItem(id);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人考评表数量以及年份查询
	@RequestMapping(value = "/alarm/personal/evaluation/num", method = RequestMethod.GET)
	public ResponseEntity<?> alarmPersonalEvaluationNum(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 个人考评表数量查询
		int count = alarmService.alarmPersonalEvaluationNum(policeId);
		// 个人考评年份查询
		List<CalculationChart> calculationCharts = alarmService.alarmYearsList(policeId);
		AlarmYear al = new AlarmYear();
		al.setNum(count);
		al.setYearList(calculationCharts);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(al);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人考评报表列表查询
	@RequestMapping(value = "/alarm/personal/evaluation/list", method = RequestMethod.GET)
	public ResponseEntity<?> alarmPersonalEvaluationList(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		if (StringUtils.isEmpty(dateTime)) {
			dateTime = sdf.format(new Date());
		}
		dateTime = dateTime.substring(0, 4);
		// 个人考评报表列表查询
		List<AlarmPersonalEvaluation> list = alarmService.alarmPersonalEvaluationList(policeId, dateTime);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人考评报表历史记录查询
	@RequestMapping(value = "/alarm/personal/evaluation/history/list", method = RequestMethod.GET)
	public ResponseEntity<?> alarmPersonalEvaluationHistoryList(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "dateTime", required = false) String dateTime,
			@RequestParam(value = "policeMonthId", required = false) Integer policeMonthId,
			@RequestParam(value = "type", required = false) Integer type) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		if (StringUtils.isEmpty(dateTime)) {
			dateTime = sdf.format(new Date());
		}
		dateTime = dateTime.substring(0, 4);
		if (policeMonthId == null) {
			Calendar calendar = Calendar.getInstance();
			policeMonthId = calendar.get(Calendar.MONTH) + 1;
		}
		List<AlarmEvaluation> list = new ArrayList<AlarmEvaluation>();
		List<AlarmEvaluation> buckleList = alarmService.alarmPersonalEvaluationHistoryBuckleList(policeId, dateTime,
				policeMonthId, null);
		List<AlarmEvaluation> addList = alarmService.alarmPersonalEvaluationHistoryAddList(policeId, dateTime,
				policeMonthId, null);
		if (type == 1) {// 扣分
			// 个人考评报表历史记录扣分查询
			list = alarmService.alarmPersonalEvaluationHistoryBuckleList(policeId, dateTime, policeMonthId, null);
		} else if (type == 2) {// 加分
			// 个人考评报表历史记录加分查询
			list = alarmService.alarmPersonalEvaluationHistoryAddList(policeId, dateTime, policeMonthId, null);
		}
		AlarmHistory item = new AlarmHistory();
		item.setAlarmEvaluationList(list);
		item.setAddNum(addList.size());
		item.setBuckleNum(buckleList.size());
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 最近加扣分项查询(当前月)
	@RequestMapping(value = "/alarm/evaluation/last/score/list", method = RequestMethod.GET)
	public ResponseEntity<?> alarmEvaluationLastScoreList(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		AlarmLastScoreList list = new AlarmLastScoreList();
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH) + 1;
		String string = month + "月";
		list.setMonthName(string);
		// 个人最近加扣分项查询(当前月)
		List<LeaveChart> chartList = alarmService.alarmEvaluationLastScoreList(policeId);
		list.setScoreList(chartList);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人加扣分趋势
	@RequestMapping(value = "/alarm/evaluation/score/trend/list", method = RequestMethod.GET)
	public ResponseEntity<?> alarmEvaluationScoreTrendList(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 个人加分趋势查询(近一年)
		List<LeaveChart> alarmAddScoreList = alarmService.alarmAddScoreList(policeId);
		// 个人扣分趋势查询(近一年)
		List<LeaveChart> alarmBuckleScoreList = alarmService.alarmBuckleScoreList(policeId);
		AlarmLine alarmLine = new AlarmLine();
		alarmLine.setAddList(alarmAddScoreList);
		alarmLine.setBuckleList(alarmBuckleScoreList);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(alarmLine);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人考评维度加扣分占比
	@RequestMapping(value = "/alarm/personal/evaluation/pie/list", method = RequestMethod.GET)
	public ResponseEntity<?> alarmPersonalEvaluationPieList(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 个人考评维度加扣分占比
		List<LeaveChart> list = alarmService.alarmPersonalEvaluationPieList(policeId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 正负面扣分项目占比
	@RequestMapping(value = "/alarm/personal/evaluation/project/list", method = RequestMethod.GET)
	public ResponseEntity<?> alarmPersonalEvaluationProjectList(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "type", required = false) Integer type) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		List<LeaveChart> list = new ArrayList<LeaveChart>();
		// 个人正负面扣分项目占比
		if (type == 1) {// 负面
			list = alarmService.alarmBuckleProjectList(policeId, 1);
		} else if (type == 2) {// 正面
			list = alarmService.alarmAddProjectList(policeId, 2);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 被约谈对象列表查询以及统计需填写约谈数量
	@RequestMapping(value = "/alarm/object/talk/page/list", method = RequestMethod.GET)
	public ResponseEntity<?> alarmObjectTalkPageList(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "viewType", required = false) Integer viewType,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageNum", required = false) Integer pageNum) throws ApiException {
		DataListPage dlr = new DataListPage();
		Integer pageNums = pageNum;
		if (pageSize == null) {
			pageSize = 10;
		}
		pageNum = ((pageNum) - 1) * pageSize;
		AlarmTalkPage list = new AlarmTalkPage();
		// 统计被约谈人需填写反馈数量
		int num = alarmService.alarmObjectTalkFillCount(policeId);
		list.setTotalNum(num);
		// 被约谈人约谈列表时间查询
		List<TimeName> dateList = alarmService.alarmObjectDatePageList(policeId, viewType, pageSize, pageNum);
		list.setTimeList(dateList);
		// 被约谈人约谈列表查询
		List<AlarmTalk> pageList = alarmService.alarmObjectTalkPageList(policeId, viewType, pageSize, pageNum);
		if (pageList.size() > 0) {
			for (int i = 0; i < pageList.size(); i++) {
				if (pageList.get(i).getStatus() == 1 && pageList.get(i).getIsReceive() == 0) {// 待约谈
					pageList.get(i).setViewType(1);
				} else if (pageList.get(i).getStatus() == 2 && pageList.get(i).getIsReceive() == 2) {// 已接受
					pageList.get(i).setViewType(2);
				} else if (pageList.get(i).getStatus() == 4 && pageList.get(i).getIsReceive() == 1) {// 已拒绝
					pageList.get(i).setViewType(3);
				} else if (pageList.get(i).getStatus() == 3 && pageList.get(i).getIsReceive() == 2) {// 需填写
					pageList.get(i).setViewType(4);
				} else if (pageList.get(i).getStatus() == 6 && pageList.get(i).getIsReceive() == 0) {// 已超时
					pageList.get(i).setViewType(5);
				} else if (pageList.get(i).getStatus() == 5 && pageList.get(i).getIsReceive() == 2) {// 已完成
					pageList.get(i).setViewType(7);
				} else if (pageList.get(i).getStatus() == 7 && pageList.get(i).getIsReceive() == 0) {// 已取消
					pageList.get(i).setViewType(10);
				} else {
					pageList.get(i).setViewType(0);
				}
			}
		}
		// 被约谈人约谈列表总数查询
		int total = alarmService.alarmObjectTalkPageCount(policeId, viewType);
		if (dateList.size() > 0) {
			for (int i = 0; i < dateList.size(); i++) {
				List<AlarmTalk> aList = new ArrayList<AlarmTalk>();
				for (int j = 0; j < pageList.size(); j++) {
					if (dateList.get(i).getName().equals(pageList.get(j).getStrStartTime())) {
						aList.add(pageList.get(j));
					}
				}
				dateList.get(i).setTalkList(aList);
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		dlr.setTotal(total);
		dlr.setPageNum(pageNums);
		dlr.setPageSize(pageSize);
		dlr.setTotalPage(((int) Math.ceil((double) total / (double) pageSize)));
		int count = ((int) Math.ceil((double) total / (double) pageSize));
		if (count - pageNums > 0) {
			dlr.setPageNext(1);
		} else {
			dlr.setPageNext(0);
		}
		return new ResponseEntity<DataListPage>(dlr, HttpStatus.OK);
	}

	// 局领导取消状态修改
	@ResponseBody
	@RequestMapping(value = "/alarm/talk/cancel/update", method = RequestMethod.POST)
	public DataListReturn alarmTalkCancelUpdate(@RequestParam(value = "id", required = false) Integer id)
			throws IOException, ParseException {
		DataListReturn dlr = new DataListReturn();
		AlarmTalk item = new AlarmTalk();
		item.setId(id);
		item.setStatus(7);
		item.setIsReceive(0);
		item.setUpdateDate(new Date());
		int count = alarmService.alarmTalkUpdate(item);
		if (count > 0) {
			dlr.setStatus(true);
			dlr.setMessage("取消成功");
			dlr.setResult(count);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("取消失败");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
		}
		return dlr;
	}

	// 局领导编辑约谈记录
	@ResponseBody
	@RequestMapping(value = "/alarm/talk/submit", method = RequestMethod.POST)
	public DataListReturn alarmTalkSubmit(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "message", required = false) String message,
			@RequestParam(value = "feedback", required = false) String feedback,
			@RequestParam(value = "reason", required = false) String reason,
			@RequestParam(value = "hostId", required = false) String hostId,
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "isReceive", required = false) Integer isReceive) throws IOException, ParseException {
		DataListReturn dlr = new DataListReturn();
		AlarmTalk item = new AlarmTalk();
		item.setId(id);
		item.setTitle(title);
		item.setPoliceId(policeId);
		if (startTime != null) {
			item.setStartTime(sdf.parse(startTime));
		}
		if (endTime != null) {
			item.setEndTime(sdf.parse(endTime));
		}
		if (!StringUtils.isEmpty(content)) {
			item.setContent(content);
		}
		if (!StringUtils.isEmpty(feedback)) {
			item.setFeedback(feedback);
		}
		if (!StringUtils.isEmpty(reason)) {
			item.setReason(reason);
		}
		item.setMessage(message);
		item.setHostId(hostId);
		item.setUpdateDate(new Date());
		if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
			long currentTimeLong = new Date().getTime();
			long startTimeLong = sdf.parse(startTime).getTime();
			long endTimeLong = sdf.parse(endTime).getTime();
			// 约谈记录详情查询(后台)
			AlarmTalk alarmTalkItem = alarmService.alarmTalkItem(id);
			long startTimeItem = alarmTalkItem.getStartTime().getTime();
			long endTimeItem = alarmTalkItem.getEndTime().getTime();
			if (startTimeItem != startTimeLong || endTimeItem != endTimeLong) {
				if (startTimeLong < currentTimeLong) {
					dlr.setStatus(false);
					dlr.setMessage("开始时间不能小于当前时间");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
				if (startTimeLong > endTimeLong) {
					dlr.setStatus(false);
					dlr.setMessage("开始时间不能大于结束时间");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
				if (endTimeLong - startTimeLong <= 300000) {
					dlr.setStatus(false);
					dlr.setMessage("时间间隔必须大于5分钟");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
				// 查询当前约谈人约谈时间记录
//				List<AlarmTalk> newAlarmTalkList = alarmService.newAlarmTalkList(hostId);
//				for (int i = 0; i < newAlarmTalkList.size(); i++) {
//					long startTimeNew = newAlarmTalkList.get(i).getStartTime().getTime();
//					long endTimeNew = newAlarmTalkList.get(i).getEndTime().getTime();
//					if (startTimeLong > endTimeNew || endTimeLong < startTimeNew) {
//
//					} else {
//						dlr.setStatus(false);
//						dlr.setMessage("当前时间段跟其他时间段冲突");
//						dlr.setResult(0);
//						dlr.setCode(StatusCode.getFailcode());
//						return dlr;
//					}
//				}
			}
		}
		int count = alarmService.alarmTalkUpdate(item);
		if (count > 0) {
			dlr.setStatus(true);
			dlr.setMessage("修改成功");
			dlr.setResult(count);
			dlr.setCode(StatusCode.getSuccesscode());
			return dlr;
		} else {
			dlr.setStatus(false);
			dlr.setMessage("修改失败");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
	}

	// 警员分值分析首页列表（3条）
	@RequestMapping(value = "/police/score/analysis/limit/list", method = RequestMethod.GET)
	public ResponseEntity<?> policeScoreLimitList(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		if (StringUtils.isEmpty(dateTime)) {
			dateTime = sdf.format(new Date());
		}
		dateTime = dateTime.substring(0, 7);
		List<AlarmPoliceScoreAnalysis> itemList = new ArrayList<AlarmPoliceScoreAnalysis>();
		if (type == null || type == 1) {// 扣分型警员分值分析列表
			itemList = alarmService.alarmBuckleScoreLimitList(policeId, dateTime);
			if (itemList.size() > 0) {
				for (int i = 0; i < itemList.size(); i++) {
					// 警员个人分值扣分查询
					List<LeaveChart> chartList = alarmService.personalBuckleAnalysisList(policeId,
							itemList.get(i).getScoredPoliceId(), "a.year_month", "asc");
					if (chartList.get(chartList.size() - 2).getNum() < 0) {
						double difference = chartList.get(chartList.size() - 1).getNum()
								- chartList.get(chartList.size() - 2).getNum();
						double proportion = difference / chartList.get(chartList.size() - 2).getNum() * 100;
						DecimalFormat df = new DecimalFormat(".00");
						itemList.get(i).setProportion(Double.valueOf(df.format(proportion)));
						itemList.get(i).setIdentifier(1);
					} else {
						itemList.get(i).setProportion(null);
						itemList.get(i).setIdentifier(2);
					}
				}
			} else {
				type = 2;
			}
		} else if (type == 2) {// 加分型警员分值分析列表
			itemList = alarmService.alarmAddScoreLimitList(policeId, dateTime);
			if (itemList.size() > 0) {
				for (int i = 0; i < itemList.size(); i++) {
					// 警员个人分值加分查询
					List<LeaveChart> chartList = alarmService.personalAddAnalysisList(policeId,
							itemList.get(i).getScoredPoliceId(), "a.year_month", "asc");
					if (chartList.get(chartList.size() - 2).getNum() > 0) {
						double difference = chartList.get(chartList.size() - 1).getNum()
								- chartList.get(chartList.size() - 2).getNum();
						double proportion = difference / chartList.get(chartList.size() - 2).getNum() * 100;
						DecimalFormat df = new DecimalFormat(".00");
						itemList.get(i).setProportion(Double.valueOf(df.format(proportion)));
						itemList.get(i).setIdentifier(1);
					} else {
						itemList.get(i).setProportion(null);
						itemList.get(i).setIdentifier(2);
					}
				}
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(itemList);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警员分值分析更多列表查询
	@RequestMapping(value = "/police/score/analysis/more/list", method = RequestMethod.GET)
	public ResponseEntity<?> policeScoreMoreList(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		AlarmPoliceScoreAnalysisItem item = new AlarmPoliceScoreAnalysisItem();
		if (departmentId == null || departmentId == 0) {
			departmentId = null;
		}
		if (StringUtils.isEmpty(dateTime)) {
			dateTime = sdf.format(new Date());
		}
		dateTime = dateTime.substring(0, 7);
		List<AlarmPoliceScoreAnalysis> itemList = new ArrayList<AlarmPoliceScoreAnalysis>();
		List<AlarmPoliceScoreAnalysis> itemList1 = alarmService.alarmBuckleScoreMoreList(policeId, departmentId,
				dateTime);
		List<AlarmPoliceScoreAnalysis> itemList2 = alarmService.alarmAddScoreMoreList(policeId, departmentId, dateTime);
		item.setBuckleNum(itemList1.size());
		item.setAddNum(itemList2.size());
		if (type == null || type == 1) {// 扣分型警员分值分析更多列表查询
			itemList = alarmService.alarmBuckleScoreMoreList(policeId, departmentId, dateTime);
			if (itemList.size() > 0) {
				for (int i = 0; i < itemList.size(); i++) {
					// 警员个人分值扣分查询
					List<LeaveChart> chartList = alarmService.personalBuckleAnalysisList(policeId,
							itemList.get(i).getScoredPoliceId(), "a.year_month", "asc");
					if (chartList.get(chartList.size() - 2).getNum() < 0) {
						double difference = chartList.get(chartList.size() - 1).getNum()
								- chartList.get(chartList.size() - 2).getNum();
						double proportion = difference / chartList.get(chartList.size() - 2).getNum() * 100;
						DecimalFormat df = new DecimalFormat(".00");
						itemList.get(i).setProportion(Double.valueOf(df.format(proportion)));
						itemList.get(i).setIdentifier(1);
					} else {
						itemList.get(i).setProportion(null);
						itemList.get(i).setIdentifier(2);
					}
				}
			}
		} else if (type == 2) {// 加分型警员分值分析更多列表查询
			itemList = alarmService.alarmAddScoreMoreList(policeId, departmentId, dateTime);
			if (itemList.size() > 0) {
				for (int i = 0; i < itemList.size(); i++) {
					// 警员个人分值加分查询
					List<LeaveChart> chartList = alarmService.personalAddAnalysisList(policeId,
							itemList.get(i).getScoredPoliceId(), "a.year_month", "asc");
					if (chartList.get(chartList.size() - 2).getNum() > 0) {
						double difference = chartList.get(chartList.size() - 1).getNum()
								- chartList.get(chartList.size() - 2).getNum();
						double proportion = difference / chartList.get(chartList.size() - 2).getNum() * 100;
						DecimalFormat df = new DecimalFormat(".00");
						itemList.get(i).setProportion(Double.valueOf(df.format(proportion)));
						itemList.get(i).setIdentifier(1);
					} else {
						itemList.get(i).setProportion(null);
						itemList.get(i).setIdentifier(2);
					}
				}
			}
		}
		itemList.removeAll(Collections.singleton(null));
		item.setList(itemList);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 统计分析分值详情查询
	@RequestMapping(value = "/police/analysis/get/score/item", method = RequestMethod.GET)
	public ResponseEntity<?> alarmAnalysisGetScoreItem(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "alarmConfigType", required = false) Integer alarmConfigType,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		if (StringUtils.isEmpty(dateTime)) {
			dateTime = sdf.format(new Date());
		}
		if (alarmConfigType == null || alarmConfigType == 1) {
			dateTime = dateTime.substring(0, 7);
		} else if (alarmConfigType == 2) {
			dateTime = dateTime.substring(0, 4);
		}
		AlarmRecordItem item = new AlarmRecordItem();
		// 个人预警详情查询
		AlarmEvaluation reItem = new AlarmEvaluation();
		List<AlarmRecordChart> itemList = new ArrayList<AlarmRecordChart>();
		if (type == 1) {// 扣分型
			itemList = alarmService.alarmGetScoreBuckleItem(policeId, 1, dateTime);
			// 个人扣分最新预警详情查询
			reItem = alarmService.alarmPersonalRecordBuckleItem(policeId, dateTime);
		} else if (type == 2) {// 加分型
			itemList = alarmService.alarmGetScoreAddItem(policeId, 1, dateTime);
			// 个人加分最新预警详情查询
			reItem = alarmService.alarmPersonalRecordAddItem(policeId, dateTime);
		}
		if (reItem != null) {
			item.setCreationDate(reItem.getCreationDate());
		}
		itemList.removeAll(Collections.singleton(null));
		item.setList(itemList);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 统计分析部门查询
	@RequestMapping(value = "/police/analysis/dep/list", method = RequestMethod.GET)
	public ResponseEntity<?> alarmAnalysisDepList(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "dateTime", required = false) String dateTime) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		if (StringUtils.isEmpty(dateTime)) {
			dateTime = sdf.format(new Date());
		}
		dateTime = dateTime.substring(0, 7);
		List<Department> itemList = new ArrayList<Department>();
		Department department = new Department();
		department.setId(0);
		department.setName("全部");
		itemList.add(department);
		List<Department> dList = new ArrayList<Department>();
		if (type == 1) {// 扣分型统计分析部门查询
			dList = departmentService.alarmDepBuckleItem(policeId, dateTime);
		} else if (type == 2) {// 加分型统计分析部门查询
			dList = departmentService.alarmDepAddItem(policeId, dateTime);
		}
		dList.removeAll(Collections.singleton(null));
		itemList.addAll(dList);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(itemList);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警员个人分值分析
	@RequestMapping(value = "/alarm/personal/score/analysis/list", method = RequestMethod.GET)
	public ResponseEntity<?> alarmPersonalScoreAnalysisList(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "scoredPoliceId", required = false) String scoredPoliceId,
			@RequestParam(value = "type", required = false) Integer type) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		AlarmScoreAnalysis item = new AlarmScoreAnalysis();
		// 最近一年总预警数统计
		int count = alarmService.personalAlarmTotalNum(scoredPoliceId);
		item.setAlarmNum(count);
		List<LeaveChart> chartList = new ArrayList<LeaveChart>();
		String name = null;
		String sort = null;
		List<LeaveChart> buckleList = alarmService.personalBuckleAnalysisList(policeId, scoredPoliceId,
				"ifnull(b.num,0)", "asc");
		if (buckleList.size() > 0) {
			if (buckleList.get(0).getNum() < 0) {
				int nameStr = Integer.valueOf(buckleList.get(0).getName().substring(5, 7));
				item.setBuckleMonth(String.valueOf(nameStr));
			} else {
				item.setBuckleMonth("--");
			}
		}
		List<LeaveChart> addList = alarmService.personalAddAnalysisList(policeId, scoredPoliceId, "ifnull(b.num,0)",
				"desc");
		if (addList.size() > 0) {
			if (addList.get(0).getNum() > 0) {
				int nameStr = Integer.valueOf(addList.get(0).getName().substring(5, 7));
				item.setAddMonth(String.valueOf(nameStr));
			} else {
				item.setAddMonth("--");
			}
		}
		if (type == null || type == 1) {
			// 警员个人分值扣分查询
			name = "a.year_month";
			sort = "asc";
			chartList = alarmService.personalBuckleAnalysisList(policeId, scoredPoliceId, name, sort);
		} else if (type == 2) {
			// 警员个人分值加分查询
			name = "a.year_month";
			sort = "asc";
			chartList = alarmService.personalAddAnalysisList(policeId, scoredPoliceId, name, sort);
		}
		item.setChartlist(chartList);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人护照催还通知(1条)
	@RequestMapping(value = "/alarm/personal/passport/return/newest/list", method = RequestMethod.GET)
	public ResponseEntity<?> alarmPersonalPassportReturnNewestList(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		AlarmPassportNoticeStatistics recordItme = new AlarmPassportNoticeStatistics();
		// 个人护照催还通知最新查询
		List<AlarmUrgeRecord> list = alarmService.alarmPersonalPassportReturnNewestList(policeId, 0);
		// 个人护照催还通知总数统计
		int total = alarmService.alarmPersonalPassportReturnCount(policeId, 0);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int timeItem = Math.abs(list.get(i).getTimeChange());
				if (timeItem == 0) {
					list.get(i).setCreationDateStr("1分钟前");
				} else if (timeItem < 60 && timeItem > 0) {
					list.get(i).setCreationDateStr(timeItem + "分钟前");
				} else if (timeItem >= 60 && timeItem < 1440) {
					int timeInt = (int) Math.floor(timeItem / 60);
					list.get(i).setCreationDateStr(timeInt + "小时前");
				} else if (timeItem >= 1440 && timeItem <= 4320) {
					int timeInt = (int) Math.floor(timeItem / 60 / 24);
					list.get(i).setCreationDateStr(timeInt + "天前");
				} else if (timeItem > 4320) {
					int yearInt = Integer.valueOf(currentYearStr);
					int createYear = Integer.valueOf(sdf.format(list.get(i).getCreationDate()).substring(0, 4));
					if (yearInt == createYear) {
						list.get(i).setCreationDateStr(sdfs.format(list.get(i).getCreationDate()).substring(5, 11));
					} else {
						String timeString = sdfs.format(list.get(i).getCreationDate());
						list.get(i).setCreationDateStr(timeString);
					}
				}
				AlarmUrgeRecord rItem = new AlarmUrgeRecord();
				rItem.setId(list.get(i).getId());
				rItem.setReadStatus(1);
				rItem.setUpdateDate(new Date());
				alarmService.alarmUrgeRecordUpdate(rItem);
				list.get(i).setReadStatus(rItem.getReadStatus());
				recordItme.setList(list);
			}
		}
		if (total > 0) {
			recordItme.setIsRead(1);
		} else {
			recordItme.setIsRead(0);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(recordItme);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人护照催还通知分页查询
	@RequestMapping(value = "/alarm/personal/passport/return/list", method = RequestMethod.GET)
	public ResponseEntity<?> alarmPersonalPassportReturnList(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageNum", required = false) Integer pageNum) throws ApiException {
		DataListPage dlr = new DataListPage();
//		if (StringUtils.isEmpty(dateTime)) {
//		dateTime = sdf.format(new Date());
//	}
//	dateTime = dateTime.substring(0, 7);
		Integer pageNums = pageNum;
		if (pageSize == null) {
			pageSize = 10;
		}
		pageNum = ((pageNum) - 1) * pageSize;
		// 个人护照催还通知时间查询
		List<AlarmTimeName> dateList = alarmService.alarmPassportDateList(policeId, null, pageSize, pageNum);
		// 个人护照催还通知分页查询
		List<AlarmUrgeRecord> pageList = alarmService.alarmPersonalPassportReturnList(policeId, null, pageSize,
				pageNum);
		pageList.removeAll(Collections.singleton(null));
		if (pageList.size() > 0) {
			for (int i = 0; i < pageList.size(); i++) {
				int timeItem = Math.abs(pageList.get(i).getTimeChange());
				if (timeItem == 0) {
					pageList.get(i).setCreationDateStr("1分钟前");
				} else if (timeItem < 60 && timeItem > 0) {
					pageList.get(i).setCreationDateStr(timeItem + "分钟前");
				} else if (timeItem >= 60 && timeItem < 1440) {
					int timeInt = (int) Math.floor(timeItem / 60);
					pageList.get(i).setCreationDateStr(timeInt + "小时前");
				} else if (timeItem >= 1440 && timeItem <= 4320) {
					int timeInt = (int) Math.floor(timeItem / 60 / 24);
					pageList.get(i).setCreationDateStr(timeInt + "天前");
				} else if (timeItem > 4320) {
					int yearInt = Integer.valueOf(currentYearStr);
					int createYear = Integer.valueOf(sdf.format(pageList.get(i).getCreationDate()).substring(0, 4));
					if (yearInt == createYear) {
						pageList.get(i)
								.setCreationDateStr(sdfs.format(pageList.get(i).getCreationDate()).substring(5, 11));
					} else {
						String timeString = sdfs.format(pageList.get(i).getCreationDate());
						pageList.get(i).setCreationDateStr(timeString);
					}
				}
				AlarmUrgeRecord rItem = new AlarmUrgeRecord();
				rItem.setId(pageList.get(i).getId());
				rItem.setReadStatus(1);
				rItem.setUpdateDate(new Date());
				alarmService.alarmUrgeRecordUpdate(rItem);
			}
		}
		// 个人护照催还通知总数统计
		int total = alarmService.alarmPersonalPassportReturnCount(policeId, null);
		if (dateList.size() > 0) {
			for (int i = 0; i < dateList.size(); i++) {
				List<AlarmUrgeRecord> aList = new ArrayList<AlarmUrgeRecord>();
				for (int j = 0; j < pageList.size(); j++) {
					if (dateList.get(i).getName().equals(pageList.get(j).getStrStartTime())) {
						aList.add(pageList.get(j));
					}
				}
				dateList.get(i).setUrgeList(aList);
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(dateList);
		dlr.setCode(StatusCode.getSuccesscode());
		dlr.setTotal(total);
		dlr.setPageNum(pageNums);
		dlr.setPageSize(pageSize);
		dlr.setTotalPage(((int) Math.ceil((double) total / (double) pageSize)));
		int count = ((int) Math.ceil((double) total / (double) pageSize));
		if (count - pageNums > 0) {
			dlr.setPageNext(1);
		} else {
			dlr.setPageNext(0);
		}
		return new ResponseEntity<DataListPage>(dlr, HttpStatus.OK);
	}

	// 个人护照催还通知详情查询
	@RequestMapping(value = "/alarm/personal/passport/return/item", method = RequestMethod.GET)
	public ResponseEntity<?> alarmPersonalPassportReturnItem(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 个人护照催还通知最新查询
		AlarmUrgeRecord uitem = alarmService.alarmUrgeRecordItem(id, null, policeId, null);
		if (uitem != null) {
			// 局领导出入境预警详情查询
			AlarmEntryAndExitRecord item = alarmService.alarmLeaderEntryItem(uitem.getEntryId());
			if (item != null) {
				uitem.setDestination(item.getDestination());
				uitem.setDepartureTime(item.getDepartureTime());
				uitem.setReturnTime(item.getReturnTime());
				uitem.setIsReturn(item.getIsReturn());
				if (item.getTimeChange() > 0) {
					double currentScore = item.getTimeChange() / 1440.0;// 时长(天);
					String number = String.valueOf(currentScore);
					int index = number.indexOf(".");
					double douNumber1 = Double.valueOf(number.substring(0, index)) + 0.5;
					double douNumber2 = Double.valueOf(number.substring(0, index));
					if (douNumber2 == currentScore) {
						currentScore = douNumber2;
					} else {
						if (douNumber1 < currentScore) {
							currentScore = douNumber1;
						} else if (douNumber1 > currentScore) {
							currentScore = douNumber2;
						}
					}
					uitem.setOverdueDays(currentScore);
				}
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(uitem);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	/**
	 * 最新三条已完成或已发起的约谈
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alarm/index/new-three-talk", method = RequestMethod.POST)
	public ResponseEntity<?> getNewThreeTalk() {

		List<AlarmEvaluation> newThreeTalk = alarmService.newThreeTalk();

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(newThreeTalk);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

}

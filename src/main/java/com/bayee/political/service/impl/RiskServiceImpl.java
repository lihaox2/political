package com.bayee.political.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskAlarm;
import com.bayee.political.domain.RiskAlarmType;
import com.bayee.political.domain.RiskCase;
import com.bayee.political.domain.RiskDuty;
import com.bayee.political.domain.RiskDutyDealPoliceRecord;
import com.bayee.political.domain.RiskHealth;
import com.bayee.political.domain.RiskIndexMonitorChild;
import com.bayee.political.domain.RiskReportRecord;
import com.bayee.political.domain.RiskTrain;
import com.bayee.political.domain.ScreenChart;
import com.bayee.political.domain.ScreenDoubeChart;
import com.bayee.political.mapper.RiskAlarmMapper;
import com.bayee.political.mapper.RiskAlarmTypeMapper;
import com.bayee.political.mapper.RiskCaseAbilityRecordMapper;
import com.bayee.political.mapper.RiskCaseLawEnforcementMapper;
import com.bayee.political.mapper.RiskCaseLawEnforcementRecordMapper;
import com.bayee.political.mapper.RiskCaseLawEnforcementTypeMapper;
import com.bayee.political.mapper.RiskCaseMapper;
import com.bayee.political.mapper.RiskCaseTestRecordMapper;
import com.bayee.political.mapper.RiskDutyDealPoliceRecordMapper;
import com.bayee.political.mapper.RiskDutyMapper;
import com.bayee.political.mapper.RiskHealthBmiStandardMapper;
import com.bayee.political.mapper.RiskHealthMapper;
import com.bayee.political.mapper.RiskHealthRecordMapper;
import com.bayee.political.mapper.RiskReportRecordMapper;
import com.bayee.political.mapper.RiskTrainMapper;
import com.bayee.political.service.RiskService;

/**
 * @author shentuqiwei
 * @version 2021年3月26日 上午10:07:52
 */
@Service
public class RiskServiceImpl implements RiskService {

	@Autowired
	RiskHealthMapper riskHealthMapper;// 警员风险指标记录

	@Autowired
	RiskHealthBmiStandardMapper riskHealthBmiStandardMapper;// 警员BMI值

	@Autowired
	RiskHealthRecordMapper riskHealthRecordMapper;// 警员体检数据记录

	@Autowired
	RiskAlarmMapper riskAlarmMapper;// 警员预警记录

	@Autowired
	RiskAlarmTypeMapper riskAlarmTypeMapper;// 警员预警类型记录

	@Autowired
	RiskReportRecordMapper riskReportRecordMapper;// 警员风险记录

	@Autowired
	RiskTrainMapper riskTrainMapper;// 警员训练风险

	@Autowired
	RiskDutyMapper riskDutyMapper;// 警员接警执勤风险

	@Autowired
	RiskDutyDealPoliceRecordMapper riskDutyDealPoliceRecordMapper;// 警员接警执勤记录

	@Autowired
	RiskCaseMapper riskCaseMapper;// 警员执法办案风险

	@Autowired
	RiskCaseAbilityRecordMapper riskCaseAbilityRecordMapper;// 警员执法能力记录

	@Autowired
	RiskCaseLawEnforcementRecordMapper riskCaseLawEnforcementRecordMapper;// 警员执法管理记录

	@Autowired
	RiskCaseLawEnforcementMapper riskCaseLawEnforcementMapper;// 警员执法管理风险

	@Autowired
	RiskCaseLawEnforcementTypeMapper riskCaseLawEnforcementTypeMapper;// 警员执法管理类型

	@Autowired
	RiskCaseTestRecordMapper riskCaseTestRecordMapper;// 警员考试记录

	// 警员健康风险指数查询
	@Override
	public RiskHealth riskHealthIndexItem(String policeId, String dateTime) {
		return riskHealthMapper.riskHealthIndexItem(policeId, dateTime);
	}

	// 警员健康风险指数新增
	@Override
	public int riskHealthCreat(RiskHealth record) {
		return riskHealthMapper.riskHealthCreat(record);
	}

	// 警员健康风险指数修改
	@Override
	public int riskHealthUpdate(RiskHealth record) {
		return riskHealthMapper.riskHealthUpdate(record);
	}

	// 警员预警分页查询
	@Override
	public List<RiskAlarm> riskAlarmPageList(String startTime, String endTime, Integer pageSize, Integer pageNum) {
		return riskAlarmMapper.riskAlarmPageList(startTime, endTime, pageSize, pageNum);
	}

	// 警员预警列表总数
	@Override
	public int riskAlarmPageCount(String startTime, String endTime) {
		return riskAlarmMapper.riskAlarmPageCount(startTime, endTime);
	}

	// 警员风险分页查询
	@Override
	public List<RiskReportRecord> riskPageList(String keyWords, Integer alarmType, String sortName, String dateTime,
			String lastDateTime, Integer pageSize, Integer pageNum) {
		return riskReportRecordMapper.riskPageList(keyWords, alarmType, sortName, dateTime, lastDateTime, pageSize,
				pageNum);
	}

	// 警员风险列表总数
	@Override
	public int riskPageCount(String keyWords, Integer alarmType, String dateTime, String lastDateTime) {
		return riskReportRecordMapper.riskPageCount(keyWords, alarmType, dateTime, lastDateTime);
	}

	// 警员预警类型查询
	@Override
	public List<RiskAlarmType> riskAlarmTypeList(Integer id) {
		return riskAlarmTypeMapper.riskAlarmTypeList(id);
	}

	// 警员风险雷达图
	@Override
	public List<ScreenDoubeChart> riskChartList(String policeId, String dateTime) {
		return riskReportRecordMapper.riskChartList(policeId, dateTime);
	}

	// 警员风险详情查询
	@Override
	public RiskReportRecord riskReportRecordItem(Integer id, String policeId, String dateTime, String lastDateTime) {
		return riskReportRecordMapper.riskReportRecordItem(id, policeId, dateTime, lastDateTime);
	}

	// 警员警务技能指数查询
	@Override
	public RiskTrain riskTrainIndexItem(String policeId, String dateTime) {
		return riskTrainMapper.riskTrainIndexItem(policeId, dateTime);
	}

	// 警员综合训练不合格趋势图
	@Override
	public List<ScreenChart> riskTrainFailChart(String policeId, String fieldName) {
		return riskTrainMapper.riskTrainFailChart(policeId, fieldName);
	}

	// 警员接警执勤指数查询
	@Override
	public RiskDuty riskDutyIndexItem(String policeId, String dateTime) {
		return riskDutyMapper.riskDutyIndexItem(policeId, dateTime);
	}

	// 半年内接警执勤风险指数
	@Override
	public List<ScreenDoubeChart> riskDutyIndexChart(String policeId) {
		return riskDutyMapper.riskDutyIndexChart(policeId);
	}

	// 警员接警执勤数据列表查询
	@Override
	public List<RiskDutyDealPoliceRecord> riskDutyRecordList(String policeId, String dateTime) {
		return riskDutyDealPoliceRecordMapper.riskDutyRecordList(policeId, dateTime);
	}

	// 警员执法办案风险指数查询
	@Override
	public RiskCase riskCaseIndexItem(String policeId, String dateTime) {
		return riskCaseMapper.riskCaseIndexItem(policeId, dateTime);
	}

	// 执法办案风险指数图例
	@Override
	public List<ScreenDoubeChart> riskCaseIndexChart(String policeId) {
		return riskCaseMapper.riskCaseIndexChart(policeId);
	}

	// 综合指数风险
	@Override
	public RiskIndexMonitorChild comprehensiveIndex(String dateTime) {
		return riskReportRecordMapper.comprehensiveIndex(dateTime);
	}

	// 行为规范风险
	@Override
	public RiskIndexMonitorChild conductIndex(String dateTime) {
		return riskReportRecordMapper.conductIndex(dateTime);
	}

	// 执法办案风险
	@Override
	public RiskIndexMonitorChild caseIndex(String dateTime) {
		return riskReportRecordMapper.caseIndex(dateTime);
	}

	// 接警执勤风险
	@Override
	public RiskIndexMonitorChild dutyIndex(String dateTime) {
		return riskReportRecordMapper.dutyIndex(dateTime);
	}

	// 警务技能风险
	@Override
	public RiskIndexMonitorChild trainIndex(String dateTime) {
		return riskReportRecordMapper.trainIndex(dateTime);
	}

	// 社交风险
	@Override
	public RiskIndexMonitorChild socialContactIndex(String dateTime) {
		return riskReportRecordMapper.socialContactIndex(dateTime);
	}

	// 家属评价风险
	@Override
	public RiskIndexMonitorChild familyEvaluationIndex(String dateTime) {
		return riskReportRecordMapper.familyEvaluationIndex(dateTime);
	}

	// 健康风险
	@Override
	public RiskIndexMonitorChild healthIndex(String year, String dateTime) {
		return riskReportRecordMapper.healthIndex(year, dateTime);
	}
}

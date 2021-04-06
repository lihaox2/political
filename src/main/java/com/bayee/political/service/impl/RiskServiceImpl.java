package com.bayee.political.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.bayee.political.domain.*;
import com.bayee.political.mapper.*;
import com.bayee.political.pojo.dto.RiskConductResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Autowired
	RiskSocialContactMapper riskSocialContactMapper;// 警员社交风险

	@Autowired
	RiskSocialContactRecordMapper riskSocialContactRecordMapper;// 警员社交记录

	/**
	 * 警员行为规范
	 */
	@Autowired
	RiskConductMapper riskConductMapper;

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

	@Override
	public RiskConductResultDTO riskConduct(String policeId, String date, String lastDateTime) {
		RiskConductResultDTO resultDTO = new RiskConductResultDTO();

		Map<String, Object> totalCountAndStatue = riskConductMapper.findMostSeriousStatusAndTotalCount(policeId, date);
		if (totalCountAndStatue != null) {
			Object v = totalCountAndStatue.get("totalCount");
			resultDTO.setTotalCount(Integer.valueOf(v.toString()));
			resultDTO.setStatus(Integer.valueOf(totalCountAndStatue.get("status").toString()));
			riskConductMapper.countByConductType(policeId, date).forEach(map -> {
				if (map.get("type") != null && map.get("type").equals(1)) {
					resultDTO.setBureauCount(Integer.valueOf(map.get("count").toString()));
				} else if (map.get("type") != null && map.get("type").equals(2)) {
					resultDTO.setLettersCount(Integer.valueOf(map.get("count").toString()));
				} else if (map.get("type") != null && map.get("type").equals(3)) {
					resultDTO.setTrafficCount(Integer.valueOf(map.get("count").toString()));
				}
			});

			Map<String, Object> totalCountAndStatueMonth1 = riskConductMapper
					.findMostSeriousStatusAndTotalCount(policeId, lastDateTime);
			ScreenDoubeChart screenDoubleChart2 = new ScreenDoubeChart();
			screenDoubleChart2.setId(1);
			screenDoubleChart2.setName("上月");
			screenDoubleChart2.setValue(Integer.valueOf(totalCountAndStatueMonth1.get("totalCount").toString()));

			ScreenDoubeChart screenDoubleChart1 = new ScreenDoubeChart();
			screenDoubleChart1.setId(2);
			screenDoubleChart1.setName("本月");
			screenDoubleChart1.setValue(resultDTO.getTotalCount());

			resultDTO.setMonthList(Arrays.asList(screenDoubleChart2, screenDoubleChart1));
		}
		return resultDTO;
	}

	@Override
	public List<ScreenDoubeChart> riskConductChart(String policeId) {
		return riskConductMapper.findRiskConductChart(policeId);
	}

	// 警员历史风险报告查询
	@Override
	public List<RiskHistoryReport> riskHistoryReportList(String policeId, String dateTime) {
		return riskReportRecordMapper.riskHistoryReportList(policeId, dateTime);
	}

	// 警员警务技能统计查询
	@Override
	public RiskTrain riskTrainStatisticsItem(String policeId, String dateTime) {
		return riskTrainMapper.riskTrainStatisticsItem(policeId, dateTime);
	}

	// 警员警务技能新增
	@Override
	public int riskTrainCreat(RiskTrain record) {
		return riskTrainMapper.riskTrainCreat(record);
	}

	// 警员警务技能修改
	@Override
	public int riskTrainUpdate(RiskTrain record) {
		return riskTrainMapper.riskTrainUpdate(record);
	}

	// 警员警员指标历史记录新增
	@Override
	public int riskReportRecordCreat(RiskReportRecord record) {
		return riskReportRecordMapper.riskReportRecordCreat(record);
	}

	// 警员社交风险查询
	@Override
	public RiskSocialContact riskSocialContactIndexItem(String policeId, String dateTime) {
		return riskSocialContactMapper.riskSocialContactIndexItem(policeId, dateTime);
	}

	// 社交风险指数图例
	@Override
	public List<ScreenDoubeChart> riskSocialContactIndexChart(String policeId, String tableName) {
		return riskSocialContactMapper.riskSocialContactIndexChart(policeId, tableName);
	}

	// 社交详情记录
	@Override
	public List<RiskSocialContactRecord> riskSocialContactRecordList(Integer socialContactId) {
		return riskSocialContactRecordMapper.riskSocialContactRecordList(socialContactId);
	}
}

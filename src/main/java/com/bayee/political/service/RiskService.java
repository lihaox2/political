package com.bayee.political.service;

import java.util.List;

import com.bayee.political.domain.*;
import com.bayee.political.pojo.dto.RiskConductResultDTO;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskAlarm;
import com.bayee.political.domain.RiskAlarmType;
import com.bayee.political.domain.RiskCase;
import com.bayee.political.domain.RiskDuty;
import com.bayee.political.domain.RiskDutyDealPoliceRecord;
import com.bayee.political.domain.RiskHealth;
import com.bayee.political.domain.RiskReportRecord;
import com.bayee.political.domain.RiskTrain;
import com.bayee.political.domain.ScreenChart;
import com.bayee.political.domain.ScreenDoubeChart;

/**
 * @author shentuqiwei
 * @version 2021年3月26日 上午10:07:30
 */
@Service
public interface RiskService {

	// 警员健康风险指数新增
	int riskHealthCreat(RiskHealth record);

	// 警员健康风险指数修改
	int riskHealthUpdate(RiskHealth record);

	// 警员健康风险指数查询
	RiskHealth riskHealthIndexItem(String policeId, String dateTime);

	// 警员预警分页查询
	List<RiskAlarm> riskAlarmPageList(String startTime, String endTime, Integer pageSize, Integer pageNum);

	// 警员预警列表总数
	int riskAlarmPageCount(String startTime, String endTime);

	// 警员风险分页查询
	List<RiskReportRecord> riskPageList(String keyWords, String sortName, String dateTime, String lastDateTime,
			Integer pageSize, Integer pageNum);

	// 警员风险列表总数
	int riskPageCount(String keyWords, String dateTime, String lastDateTime);

	// 警员预警类型查询
	List<RiskAlarmType> riskAlarmTypeList(Integer id);

	// 警员风险雷达图
	List<ScreenDoubeChart> riskChartList(String policeId, String dateTime);

	// 警员风险详情查询
	RiskReportRecord riskReportRecordItem(Integer id, String policeId, String dateTime, String lastDateTime);

	// 警员警务技能指数查询
	RiskTrain riskTrainIndexItem(String policeId, String dateTime);

	// 警员综合训练不合格趋势图
	List<ScreenChart> riskTrainFailChart(String policeId, String fieldName);

	// 警员接警执勤指数查询
	RiskDuty riskDutyIndexItem(String policeId, String dateTime);

	// 半年内接警执勤风险指数
	List<ScreenDoubeChart> riskDutyIndexChart(String policeId);

	// 警员接警执勤数据列表查询
	List<RiskDutyDealPoliceRecord> riskDutyRecordList(String policeId, String dateTime);

	// 警员执法办案风险指数查询
	RiskCase riskCaseIndexItem(String policeId, String dateTime);

	// 执法办案风险指数图例
	List<ScreenDoubeChart> riskCaseIndexChart(String policeId);

	/**
	 * 警员行为规范风险指数查询
	 * @param policeId
	 * @param date
	 * @param lastDateTime
	 * @return
	 */
	RiskConductResultDTO riskConduct(String policeId, String date,String lastDateTime);

	/**
	 * 警员行为规范风险指数图例
	 * @param policeId
	 * @return
	 */
	List<ScreenDoubeChart> riskConductChart(String policeId);

}

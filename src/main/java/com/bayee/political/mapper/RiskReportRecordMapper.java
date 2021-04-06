package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskIndexMonitorChild;
import com.bayee.political.domain.RiskReportRecord;
import com.bayee.political.domain.ScreenDoubeChart;

public interface RiskReportRecordMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(RiskReportRecord record);

	int insertSelective(RiskReportRecord record);

	RiskReportRecord selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(RiskReportRecord record);

	int updateByPrimaryKey(RiskReportRecord record);

	// 警员风险分页查询
	List<RiskReportRecord> riskPageList(@Param("keyWords") String keyWords, @Param("alarmType") Integer alarmType,
			@Param("sortName") String sortName, @Param("dateTime") String dateTime,
			@Param("lastDateTime") String lastDateTime, @Param("pageSize") Integer pageSize,
			@Param("pageNum") Integer pageNum);

	// 警员风险列表总数
	int riskPageCount(@Param("keyWords") String keyWords, @Param("alarmType") Integer alarmType,
			@Param("dateTime") String dateTime, @Param("lastDateTime") String lastDateTime);

	// 警员风险雷达图
	List<ScreenDoubeChart> riskChartList(@Param("policeId") String policeId, @Param("dateTime") String dateTime);

	// 警员风险详情查询
	RiskReportRecord riskReportRecordItem(@Param("id") Integer id, @Param("policeId") String policeId,
			@Param("dateTime") String dateTime, @Param("lastDateTime") String lastDateTime);

	// 综合指数风险
	RiskIndexMonitorChild comprehensiveIndex(@Param("dateTime") String dateTime);

	// 行为规范风险
	RiskIndexMonitorChild conductIndex(@Param("dateTime") String dateTime);

	// 执法办案风险
	RiskIndexMonitorChild caseIndex(@Param("dateTime") String dateTime);

	// 接警执勤风险
	RiskIndexMonitorChild dutyIndex(@Param("dateTime") String dateTime);

	// 警务技能风险
	RiskIndexMonitorChild trainIndex(@Param("dateTime") String dateTime);

	// 社交风险
	RiskIndexMonitorChild socialContactIndex(@Param("dateTime") String dateTime);

	// 家属评价风险
	RiskIndexMonitorChild familyEvaluationIndex(@Param("dateTime") String dateTime);

	// 健康风险
	RiskIndexMonitorChild healthIndex(@Param("year") String year, @Param("dateTime") String dateTime);
}
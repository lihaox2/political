package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.AlarmEntryAndExitRecord;
import com.bayee.political.domain.AlarmEntryTimeName;
import com.bayee.political.domain.AlarmLeaderStatistics;
import com.bayee.political.domain.CalculationChart;

public interface AlarmEntryAndExitRecordMapper {

	// 出入境预警新增
	int alarmEntryAndExitRecordCreat(AlarmEntryAndExitRecord record);

	// 出入境预警修改
	int alarmEntryAndExitRecordUpdate(AlarmEntryAndExitRecord record);

	// 局领导出入境预警统计
	AlarmLeaderStatistics alarmEntryAndExitStatistics(@Param("policeId") String policeId);

	// 局领导出入境预警累计统计
	AlarmLeaderStatistics alarmLeaderEntryRecordItem(@Param("policeId") String policeId);

	// 局领导最新出入境预警查询
	List<AlarmEntryAndExitRecord> alarmLeaderEntryNewestList(@Param("policeId") String policeId);

	// 局领导出入境预警数量统计
	int alarmLeaderEntryRecordPageCount(@Param("policeId") String policeId);

	// 局领导出入境预警分页查询
	List<AlarmEntryAndExitRecord> alarmLeaderEntryRecordPageList(@Param("policeId") String policeId,
			@Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum);

	// 局领导出入境预警详情查询
	AlarmEntryAndExitRecord alarmLeaderEntryItem(@Param("id") Integer id);

	// 局领导出入境预警趋势图
	List<CalculationChart> alarmLeaderEntryExitChart(@Param("policeId") String policeId);

	// 局领导出入境时间查询
	List<AlarmEntryTimeName> alarmEntryDatePageList(@Param("policeId") String policeId,
			@Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum);
}
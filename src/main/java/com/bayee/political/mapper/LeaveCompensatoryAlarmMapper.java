package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.LeaveCompensatoryAlarm;

public interface LeaveCompensatoryAlarmMapper {

	// 警员调休预警新增
	int leaveCompensatoryAlarmCreat(LeaveCompensatoryAlarm record);

	// 警员调休预警修改
	int leaveCompensatoryAlarmUpdate(LeaveCompensatoryAlarm record);

	// 警员调休预警(2条)查询
	List<LeaveCompensatoryAlarm> leaveLeaderCompensatoryAlarmNewestList(@Param("policeId") String policeId);

	// 警员调休预警总数统计
	int leaveLeaderCompensatoryAlarmNewestCount(@Param("policeId") String policeId);

	// 警员调休预警分页查询
	List<LeaveCompensatoryAlarm> leaveLeaderCompensatoryAlarmList(@Param("policeId") String policeId,
			@Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum);

	// 根据季度查询民警累计出差天数
	List<LeaveCompensatoryAlarm> quarterPoliceList(@Param("startTime") String startTime,
			@Param("endTime") String endTime);

	// 根据季度,创建时间,警号查询季度预警
	LeaveCompensatoryAlarm quarterPoliceItem(@Param("type") Integer type, @Param("ruleId") Integer ruleId,
			@Param("policeId") String policeId, @Param("startTime") String startTime, @Param("endTime") String endTime);

	// 根据每月查询民警累计出差天数
	List<LeaveCompensatoryAlarm> monthPoliceList(@Param("startTime") String startTime,
			@Param("endTime") String endTime);

	// 查询单次民警出差天数
	List<LeaveCompensatoryAlarm> singlePoliceList();

	// 查询连续工作预警
	LeaveCompensatoryAlarm continuePoliceItem(@Param("type") Integer type, @Param("policeId") String policeId);

	// 查询个人连续未打卡天数
	LeaveCompensatoryAlarm notRecordPoliceItem(@Param("policeId") String policeId);

	// 警员调休预警连续加班修改
	int leaveCompensatoryAlarmTimeUpdate(LeaveCompensatoryAlarm record);

	// 警员调休预警不打卡修改
	int leaveCompensatoryAlarmAllUpdate(LeaveCompensatoryAlarm record);

	// 查询连续未打卡天数
	List<LeaveCompensatoryAlarm> notRecordList();

	// 查询预警连续加班人员
	List<LeaveCompensatoryAlarm> alarmComRecordList();

	// 局领导需强制调休人员详情查询
	LeaveCompensatoryAlarm leaveLeaderCompensatoryAlarmItem(@Param("id") Integer id);

}
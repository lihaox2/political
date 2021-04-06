package com.bayee.political.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.ClockRecord;
import com.bayee.political.domain.DepOvertimeDutypoliceNum;
import com.bayee.political.domain.LeaveChart;
import com.bayee.political.domain.PoliceForceOnlineStatistics;

public interface ClockRecordMapper {
	int deleteByPrimaryKey(Integer id);

	// 钉钉打卡记录保存
	int clockRecordCreate(ClockRecord clock);

	ClockRecord selectByPrimaryKey(Integer id);

	// 修改打卡记录
	int clockRecordUpdate(ClockRecord clock);

	int updateByPrimaryKey(ClockRecord record);

	// 个人加班排名Top10（api）
	List<ClockRecord> leavePersonalOvertimeRankList(@Param("departmentId") Integer departmentId,
			@Param("startTime") String startTime, @Param("endTime") String endTime);

	// 部门加班情况排名（api）
	List<LeaveChart> leaveDepOvertimeRankList(@Param("startTime") String startTime, @Param("endTime") String endTime);

	// 部门值班情况排名（api）
	List<LeaveChart> leaveDepDutyRankList();

	// 加班折线图查询（api）
	List<LeaveChart> leaveOvertimeList(@Param("departmentId") Integer departmentId);

	// 值班折线图查询（api）
	List<LeaveChart> leaveDutyList(@Param("departmentId") Integer departmentId);

	// 警员打卡加班数据查询
	List<ClockRecord> overTimeClockRecordList();

	// 警员打卡值班数据查询
	List<ClockRecord> dutyClockRecordList();

	// 查询当天下班打卡记录详情
	ClockRecord offDutyClockRecordItem(@Param("userId") String userId, @Param("dateTime") String dateTime);

	// 修改打卡记录查询状态
	int identificationUpdate(ClockRecord clock);

	// 查询打卡人员
	List<ClockRecord> clockRecordUserIdList();

	// 警力在线统计
	PoliceForceOnlineStatistics policeForceOnlineStatisticsItem();

	// 今日各部门加值班警员人数查询
	List<DepOvertimeDutypoliceNum> processDepOvertimeDutypoliceNumList(@Param("departmentId") Integer departmentId,
			@Param("typeStr") String typeStr, @Param("sortStr") String sortStr);

	// 打卡记录详情查询
	ClockRecord clockRecordItem(@Param("userId") String userId, @Param("checkType") String checkType,
			@Param("baseCheckTime") Date baseCheckTime, @Param("userCheckTime") Date userCheckTime);

	// 每月加班时长趋势
	List<LeaveChart> screenOverTimeList();

	// 每月值班时长趋势
	List<LeaveChart> screenDutyLineChartList();

}
/**
 * 
 */
package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.DepartmentAnnualLeaveRatio;
import com.bayee.political.domain.LeaveChart;
import com.bayee.political.domain.LeavePersonalStatistics;
import com.bayee.political.domain.LeaveProcess;
import com.bayee.political.domain.LeaveThisYearStatistics;
import com.bayee.political.domain.LeaveTypeCount;
import com.bayee.political.domain.MonthlyLeaveTotal;

/**
 * @author seanguo
 *
 */
public interface LeaveProcessMapper {

	LeaveProcess findByBusinessId(@Param("businessId") String businessId);

	void save(LeaveProcess leaveProcess);

	void update(LeaveProcess leaveProcess);

	// 请假记录修改
	int leaveProcessUpdate(LeaveProcess leaveProcess);

	LeaveProcess findById(@Param("id") long id);

	List<LeaveProcess> findByUserId(@Param("userId") String userId);

	List<LeaveProcess> findAll();

	int countAnnualLeveByUser(@Param("year") String year);

	List<DepartmentAnnualLeaveRatio> countAnnualLeaveRatioByDepartment(@Param("year") String year);

	List<MonthlyLeaveTotal> countMonthlyLeaveByYear(@Param("year") String year);

	List<LeaveTypeCount> countLeaveByTypes(@Param("year") String year);

	int countUserTotalLeaveCount(@Param("userId") String userId, @Param("year") String year);

	int countAnnualLeaveLeftCount(@Param("userId") String userId, @Param("year") String year);

	int countAnnualLeaveUsedTimes(@Param("userId") String userId, @Param("year") String year);

	List<LeaveTypeCount> countUserLeaveByTypes(@Param("userId") String userId, @Param("year") String year);

	int countTotalLeave(@Param("year") String year);

	// 每月请假类型占比查询（api）
	List<CalculationChart> leaveTypeList(@Param("departmentId") Integer departmentId);

	// 个人休假情况统计（api）
	LeavePersonalStatistics leavePersonalStatisticsItem(@Param("policeId") String policeId, @Param("year") String year,
			@Param("halfYear") Integer halfYear);

	// 个人请假原因占比情况（api）
	List<CalculationChart> leaveReasonAnalysisList(@Param("policeId") String policeId);

	// 个人最近请假查询列表（api）
	List<LeaveProcess> leavePersonalList(@Param("policeId") String policeId, @Param("leaveType") String leaveType,
			@Param("identification") Integer identification);

	// 每月请假总人数查询（api）
	List<CalculationChart> leavetotalNumList(@Param("departmentId") Integer departmentId);

	// 每月休年假人数查询（api）
	List<CalculationChart> leaveAnnualNumList(@Param("departmentId") Integer departmentId);

	// 每月调休人数查询（api）
	List<CalculationChart> leaveOffNumList(@Param("departmentId") Integer departmentId);

	// 每月请假人数查询（api）
	List<CalculationChart> leaveNumList(@Param("departmentId") Integer departmentId);

	// 各部门年假使用情况查询（api）
	List<LeaveChart> leaveDepAnnualChartList();

	// 各部门平均年假使用率查询（api）
	int leaveDepAverageItem(@Param("departmentId") Integer departmentId);

	// 休假中民警数量（api）
	int onHolidayNumItem(@Param("departmentId") Integer departmentId);

	// 请假中民警数量（api）
	int askingForLeaveNumItem(@Param("departmentId") Integer departmentId);

	// 个人加班时长查询
	LeavePersonalStatistics leavePersonalOvertimeItem(@Param("policeId") String policeId);

	// 个人积分查询
	LeavePersonalStatistics leavePersonalPointsItems(@Param("policeId") String policeId, @Param("year") String year,
			@Param("halfYear") Integer halfYear);

	// 各单位需调休人数查询
	List<CalculationChart> leaveDepCompensatoryChart();

	// 修改请假表状态
	int leaveProcessAlarmItemUpdate(LeaveProcess lProcess);

	// 个人加班可调休天数查询
	LeavePersonalStatistics leavePersonalOverTimeChangeDays(@Param("policeId") String policeId,
			@Param("currentYearStr") String currentYearStr);

	// 个人积分可调休天数查询
	LeavePersonalStatistics leavePersonalpointsChangeDays(@Param("policeId") String policeId,
			@Param("currentYearStr") String currentYearStr, @Param("halfYear") Integer halfYear);

	// 各单位年休假率查询
	List<LeaveChart> leaveDepAnnualNotStandardStatistics();

	// 加班调休列表查询
	List<LeaveProcess> leaveLeaderOvertimeTaskList();

	// 积分调休列表查询
	List<LeaveProcess> leaveLeaderPointTaskList();

	// 个人当前年情况统计
	LeaveThisYearStatistics leaveThisYearStatisticsItem(@Param("policeId") String policeId);

	// 请假类型占比
	List<CalculationChart> screenLeaveTypeList();

}

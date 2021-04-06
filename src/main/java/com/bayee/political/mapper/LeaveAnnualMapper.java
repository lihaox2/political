package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.bayee.political.domain.LeaveAnnual;
import com.bayee.political.domain.LeaveChart;
import com.bayee.political.domain.ScreenChart;
import com.bayee.political.domain.ScreenDoubeChart;

public interface LeaveAnnualMapper {

	// 警员年假新增
	int leaveAnnualCreat(LeaveAnnual leaveAnnual);

	// 警员年假修改
	int leaveAnnualUpdate(LeaveAnnual leaveAnnual);

	// 警员年假详情查询
	LeaveAnnual leaveAnnualItem(@Param("id") Integer id, @Param("policeId") String policeId,
			@Param("year") String year);

	// 警员年假列表查询
	List<LeaveAnnual> leaveAnnualList(@Param("policeId") String policeId, @Param("year") String year);

	// 警员年假天数列表查询
	List<LeaveAnnual> policeLeaveAnnualExistList();

	// 年休假人数统计
	int annualLeaveNum(@Param("departmentId") Integer departmentId);

	// 未休年假人数统计
	int notAnnualLeaveNum(@Param("departmentId") Integer departmentId);

	// 单位年休假达标数量统计
	int depAnnualLeaveNum();

	// 有年休假的单位数量统计
	int totalDepAnnualLeaveNum();

	/**
	 * 本周休假人数
	 * 
	 * @return
	 */
	@Select("select count(DISTINCT police_id) as count from leave_alarm where YEARWEEK(date_format(creation_date,'%Y-%m-%d')) = YEARWEEK(now())")
	Integer leaveNum();

	/**
	 * 本周休年假人数
	 * 
	 * @return
	 */
	@Select("select count(DISTINCT police_id) as count from leave_annual where YEARWEEK(date_format(creation_date,'%Y-%m-%d')) = YEARWEEK(now())")
	Integer leaveYearNum();

	/**
	 * 本周强制调休人数
	 * 
	 * @return
	 */
	@Select("select count(DISTINCT police_id) as count from leave_compensatory_alarm where YEARWEEK(date_format(creation_date,'%Y-%m-%d')) = YEARWEEK(now())")
	Integer leaveAdjustNum();

	/**
	 * 年假已使用占比
	 * 
	 * @return
	 */
	@Select("select TRUNCATE((select count(*) as count from leave_annual where annual_leave_count != annual_leave_days and year = DATE_FORMAT(now(),'%Y')) / (select count(*) as count from leave_annual where not ISNULL(annual_leave_count) and year = DATE_FORMAT(now(),'%Y')) * 100, 2) as rator")
	Double leaveYearUsedRator();

	// 全局年休假统计
	List<ScreenDoubeChart> screenLeaveAnnualStatisticsList();

	// 各所年休假使用率
	List<ScreenDoubeChart> screenLStationeaveAnnualStatisticsList();

	// 每月请假总人数查询（api）
	List<ScreenChart> screenLeavetotalNumList();

	// 每月休年假人数查询（api）
	List<ScreenChart> screenLAnnualNumList();

	// 每月病假人数查询（api）
	List<ScreenChart> screenLOffNumList();

	// 每月事假人数查询（api）
	List<ScreenChart> screenLNumList();

}
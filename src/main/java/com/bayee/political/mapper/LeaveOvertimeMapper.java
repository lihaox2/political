package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.LeaveLeaderAlarm;
import com.bayee.political.domain.LeaveOvertime;

public interface LeaveOvertimeMapper {

	// 警员加班时长新增
	int leaveOvertimeCreat(LeaveOvertime record);

	// 警员加班时长修改
	int leaveOvertimeUpdate(LeaveOvertime record);

	// 根据年份和警员号修改
	int leaveOvertimeYearUpdate(LeaveOvertime record);

	// 警员加班时长详情
	LeaveOvertime leaveOvertimeItem(@Param("id") Integer id, @Param("policeId") String policeId,
			@Param("year") String year);

	// 加班预警(1条)查询
	List<LeaveLeaderAlarm> leaveLeaderOvertimeAlarmNewestList(@Param("policeId") String policeId);

	// 加班预警总数统计
	int leaveLeaderOvertimeAlarmNewestCount(@Param("policeId") String policeId);

	// 加班预警分页查询
	List<LeaveLeaderAlarm> leaveLeaderOvertimeAlarmList(@Param("policeId") String policeId,
			@Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum);
	
	/**
	 * 本周个人加班前5
	 * @return
	 */
	List<LeaveOvertime> leaveOverTimeTopFiveWeek();
	
}
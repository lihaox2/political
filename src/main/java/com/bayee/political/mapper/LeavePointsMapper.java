package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.LeaveLeaderAlarm;
import com.bayee.political.domain.LeavePoints;

public interface LeavePointsMapper {

	// 警员积分新增
	int leavePointsCreat(LeavePoints record);

	// 警员积分修改
	int leavePointsUpdate(LeavePoints record);

	// 警员根据policeId,year,halfYear修改积分
	int leavePointsResidualUpdate(LeavePoints record);

	// 警员积分详情查询
	LeavePoints leavePointsItem(@Param("id") Integer id, @Param("policeId") String policeId, @Param("year") String year,
			@Param("halfYear") Integer halfYear);

	/**
	 * 批量新增记分
	 * 
	 * @param leavePointsList
	 * @return
	 */
	int addMoreleavePoints(@Param("leavePointsList") List<LeavePoints> leavePointsList);

	// 各单位积分未兑换人数查询
	List<CalculationChart> leaveDepPointsExchangeChart(@Param("year") String year, @Param("halfYear") Integer halfYear);

	// 办案积分预警(2条)查询
	List<LeaveLeaderAlarm> leaveLeaderPointsAlarmNewestList(@Param("policeId") String policeId,
			@Param("year") String year, @Param("halfYear") Integer halfYear);

	// 办案积分预警总数统计
	int leaveLeaderPointsAlarmNewestCount(@Param("policeId") String policeId, @Param("year") String year,
			@Param("halfYear") Integer halfYear);

	// 办案积分预警分页查询
	List<LeaveLeaderAlarm> leaveLeaderPointsAlarmList(@Param("policeId") String policeId, @Param("year") String year,
			@Param("halfYear") Integer halfYear, @Param("pageSize") Integer pageSize,
			@Param("pageNum") Integer pageNum);

}
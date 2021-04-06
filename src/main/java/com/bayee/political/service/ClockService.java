package com.bayee.political.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bayee.political.domain.ClockRecord;
import com.bayee.political.domain.DepOvertimeDutypoliceNum;
import com.bayee.political.domain.DrinkRecord;
import com.bayee.political.domain.LeaveChart;
import com.bayee.political.domain.PoliceForceOnlineStatistics;

/**
 * @author shentuqiwei
 * @version 2020年7月9日 下午4:01:55
 */
@Service
public interface ClockService {

	// 钉钉打卡记录保存
	int clockRecordCreate(ClockRecord clock);

	// 修改打卡记录
	int clockRecordUpdate(ClockRecord clock);

	// 修改打卡记录查询状态
	int identificationUpdate(ClockRecord clock);

	// 个人加班排名Top10（api）
	List<ClockRecord> leavePersonalOvertimeRankList(Integer departmentId, String startTime, String endTime);

	// 部门加班情况排名（api）
	List<LeaveChart> leaveDepOvertimeRankList(String startTime, String endTime);

	// 部门值班情况排名（api）
	List<LeaveChart> leaveDepDutyRankList();

	// 加班折线图查询（api）
	List<LeaveChart> leaveOvertimeList(Integer departmentId);

	// 值班折线图查询（api）
	List<LeaveChart> leaveDutyList(Integer departmentId);

	// 警员打卡加班数据查询
	List<ClockRecord> overTimeClockRecordList();

	// 警员打卡值班数据查询
	List<ClockRecord> dutyClockRecordList();

	// 查询当天下班打卡记录详情
	ClockRecord offDutyClockRecordItem(String userId, String dateTime);

	// 查询打卡人员
	List<ClockRecord> clockRecordUserIdList();

	// 警力在线统计
	PoliceForceOnlineStatistics policeForceOnlineStatisticsItem();

	// 警员喝酒记录查询
	List<DrinkRecord> drinkList();

	// 今日各部门加值班警员人数查询
	List<DepOvertimeDutypoliceNum> processDepOvertimeDutypoliceNumList(Integer departmentId, String typeStr,
			String sortStr);

	// 打卡记录详情查询
	ClockRecord clockRecordItem(String userId, String checkType, Date baseCheckTime, Date userCheckTime);
}

package com.bayee.political.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.ClockRecord;
import com.bayee.political.domain.DepOvertimeDutypoliceNum;
import com.bayee.political.domain.DrinkRecord;
import com.bayee.political.domain.LeaveChart;
import com.bayee.political.domain.PoliceForceOnlineStatistics;
import com.bayee.political.mapper.ClockRecordMapper;
import com.bayee.political.mapper.DrinkRecordMapper;
import com.bayee.political.service.ClockService;

/**
 * @author shentuqiwei
 * @version 2020年7月9日 下午4:03:03
 */
@Service
public class ClockServiceImpl implements ClockService {

	@Autowired
	ClockRecordMapper clockRecordMapper;

	@Autowired
	DrinkRecordMapper drinkRecordMapper;// 警员喝酒记录

	// 钉钉打卡记录保存
	@Override
	public int clockRecordCreate(ClockRecord clock) {
		return clockRecordMapper.clockRecordCreate(clock);
	}

	// 个人加班排名Top10（api）
	@Override
	public List<ClockRecord> leavePersonalOvertimeRankList(Integer departmentId, String startTime, String endTime) {
		return clockRecordMapper.leavePersonalOvertimeRankList(departmentId, startTime, endTime);
	}

	// 部门加班情况排名（api）
	@Override
	public List<LeaveChart> leaveDepOvertimeRankList(String startTime, String endTime) {
		return clockRecordMapper.leaveDepOvertimeRankList(startTime, endTime);
	}

	// 部门值班情况排名（api）
	@Override
	public List<LeaveChart> leaveDepDutyRankList() {
		return clockRecordMapper.leaveDepDutyRankList();
	}

	// 加班折线图查询（api）
	@Override
	public List<LeaveChart> leaveOvertimeList(Integer departmentId) {
		return clockRecordMapper.leaveOvertimeList(departmentId);
	}

	// 值班折线图查询（api）
	@Override
	public List<LeaveChart> leaveDutyList(Integer departmentId) {
		return clockRecordMapper.leaveDutyList(departmentId);
	}

	// 警员打卡加班数据查询
	@Override
	public List<ClockRecord> overTimeClockRecordList() {
		return clockRecordMapper.overTimeClockRecordList();
	}

	// 修改打卡记录
	@Override
	public int clockRecordUpdate(ClockRecord clock) {
		return clockRecordMapper.clockRecordUpdate(clock);
	}

	// 警员打卡值班数据查询
	@Override
	public List<ClockRecord> dutyClockRecordList() {
		return clockRecordMapper.dutyClockRecordList();
	}

	// 查询当天下班打卡记录详情
	@Override
	public ClockRecord offDutyClockRecordItem(String userId, String dateTime) {
		return clockRecordMapper.offDutyClockRecordItem(userId, dateTime);
	}

	// 修改打卡记录查询状态
	@Override
	public int identificationUpdate(ClockRecord clock) {
		return clockRecordMapper.identificationUpdate(clock);
	}

	// 查询打卡人员
	@Override
	public List<ClockRecord> clockRecordUserIdList() {
		return clockRecordMapper.clockRecordUserIdList();
	}

	// 警力在线统计
	@Override
	public PoliceForceOnlineStatistics policeForceOnlineStatisticsItem() {
		return clockRecordMapper.policeForceOnlineStatisticsItem();
	}

	// 警员喝酒记录查询
	@Override
	public List<DrinkRecord> drinkList() {
		return drinkRecordMapper.drinkList();
	}

	// 今日各部门加值班警员人数查询
	@Override
	public List<DepOvertimeDutypoliceNum> processDepOvertimeDutypoliceNumList(Integer departmentId, String typeStr,
			String sortStr) {
		return clockRecordMapper.processDepOvertimeDutypoliceNumList(departmentId, typeStr, sortStr);
	}

	// 打卡记录详情查询
	@Override
	public ClockRecord clockRecordItem(String userId, String checkType, Date baseCheckTime, Date userCheckTime) {
		return clockRecordMapper.clockRecordItem(userId, checkType, baseCheckTime, userCheckTime);
	}

}

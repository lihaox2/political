/**
 * 
 */
package com.bayee.political.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.DepartmentAnnualLeaveRatio;
import com.bayee.political.domain.LeaveAnnual;
import com.bayee.political.domain.LeaveChart;
import com.bayee.political.domain.LeaveCompensatoryAlarm;
import com.bayee.political.domain.LeaveCompensatoryReadRecord;
import com.bayee.political.domain.LeaveCompensatoryRecord;
import com.bayee.political.domain.LeaveDuty;
import com.bayee.political.domain.LeavePower;
import com.bayee.political.domain.LeavePowerObject;
import com.bayee.political.domain.LeaveIntegralExchangeRule;
import com.bayee.political.domain.LeaveIntegralManage;
import com.bayee.political.domain.LeaveLeaderAlarm;
import com.bayee.political.domain.LeaveOvertime;
import com.bayee.political.domain.LeaveOvertimeRule;
import com.bayee.political.domain.LeavePersonalStatistics;
import com.bayee.political.domain.LeavePoints;
import com.bayee.political.domain.LeavePointsExchangeRecord;
import com.bayee.political.domain.LeaveProcess;
import com.bayee.political.domain.LeaveRestAlarmRule;
import com.bayee.political.domain.LeaveRestManage;
import com.bayee.political.domain.LeaveThisYearStatistics;
import com.bayee.political.domain.LeaveTrain;
import com.bayee.political.domain.LeaveTypeCount;
import com.bayee.political.domain.MonthlyLeaveTotal;
import com.bayee.political.mapper.LeaveAnnualMapper;
import com.bayee.political.mapper.LeaveCompensatoryAlarmMapper;
import com.bayee.political.mapper.LeaveCompensatoryReadRecordMapper;
import com.bayee.political.mapper.LeaveCompensatoryRecordMapper;
import com.bayee.political.mapper.LeaveDutyMapper;
import com.bayee.political.mapper.LeavePowerMapper;
import com.bayee.political.mapper.LeavePowerObjectMapper;
import com.bayee.political.mapper.LeaveIntegralExchangeRuleMapper;
import com.bayee.political.mapper.LeaveIntegralManageMapper;
import com.bayee.political.mapper.LeaveOvertimeMapper;
import com.bayee.political.mapper.LeaveOvertimeRuleMapper;
import com.bayee.political.mapper.LeavePointsExchangeRecordMapper;
import com.bayee.political.mapper.LeavePointsMapper;
import com.bayee.political.mapper.LeaveProcessMapper;
import com.bayee.political.mapper.LeaveRestAlarmRuleMapper;
import com.bayee.political.mapper.LeaveRestManageMapper;
import com.bayee.political.mapper.LeaveTrainMapper;
import com.bayee.political.service.LeaveProcessService;

/**
 * @author seanguo
 *
 */
@Service
public class LeaveProcessServiceImpl implements LeaveProcessService {

	@Autowired
	LeaveProcessMapper leaveProcessMapper;

	@Autowired
	LeaveRestManageMapper leaveRestManageMapper;

	@Autowired
	LeaveIntegralManageMapper leaveIntegralManageMapper;

	@Autowired
	LeavePowerMapper leavePowerMapper;// AI休假审核权限

	@Autowired
	LeavePowerObjectMapper leavePowerObjectMapper;// 审核对象人员

	@Autowired
	LeaveTrainMapper leaveTrainMapper;

	@Autowired
	LeaveAnnualMapper leaveAnnualMapper;// 警员年假

	@Autowired
	LeaveOvertimeRuleMapper leaveOvertimeRuleMapper;

	@Autowired
	LeaveIntegralExchangeRuleMapper leaveIntegralExchangeRuleMapper;

	@Autowired
	LeaveRestAlarmRuleMapper leaveRestAlarmRuleMapper;

	@Autowired
	LeaveCompensatoryRecordMapper leaveCompensatoryRecordMapper;// 调休记录

	@Autowired
	LeavePointsExchangeRecordMapper leavePointsExchangeRecordMapper;// 积分兑换记录

	@Autowired
	LeavePointsMapper leavePointsMapper;// 警员积分

	@Autowired
	LeaveOvertimeMapper leaveOvertimeMapper;// 警员加班

	@Autowired
	LeaveDutyMapper leaveDutyMapper;// 警员值班

	@Autowired
	LeaveCompensatoryAlarmMapper leaveCompensatoryAlarmMapper;// 警员调休预警

	@Autowired
	LeaveCompensatoryReadRecordMapper leaveCompensatoryReadRecordMapper;// 局领导调休提醒已读记录

	@Override
	public void saveOrUpdate(LeaveProcess leaveProcess) {
		LeaveProcess result = leaveProcessMapper.findByBusinessId(leaveProcess.getBusinessId());
		if (result == null) {
			leaveProcessMapper.save(leaveProcess);
		} else {
			leaveProcessMapper.update(leaveProcess);
		}
	}

	@Override
	public void save(LeaveProcess leaveProcess) {
		leaveProcessMapper.save(leaveProcess);
	}

	@Override
	public LeaveProcess findById(long id) {
		return leaveProcessMapper.findById(id);
	}

	@Override
	public LeaveProcess findByBusinessId(String businessId) {
		return leaveProcessMapper.findByBusinessId(businessId);
	}

	@Override
	public List<LeaveProcess> findByUserId(String userId) {
		return leaveProcessMapper.findByUserId(userId);
	}

	@Override
	public List<LeaveProcess> findAll() {
		return leaveProcessMapper.findAll();
	}

	@Override
	public int countAnnualLeveByUser(String year) {
		return leaveProcessMapper.countAnnualLeveByUser(year);
	}

	@Override
	public List<DepartmentAnnualLeaveRatio> countAnnualLeaveRatioByDepartment(String year) {
		return leaveProcessMapper.countAnnualLeaveRatioByDepartment(year);
	}

	@Override
	public List<MonthlyLeaveTotal> countMonthlyLeaveByYear(String year) {
		return leaveProcessMapper.countMonthlyLeaveByYear(year);
	}

	@Override
	public List<LeaveTypeCount> countLeaveByTypes(String year) {
		return leaveProcessMapper.countLeaveByTypes(year);
	}

	@Override
	public int countUserTotalLeaveCount(String userId, String year) {
		return leaveProcessMapper.countUserTotalLeaveCount(userId, year);
	}

	@Override
	public int countAnnualLeaveLeftCount(String userId, String year) {
		return leaveProcessMapper.countAnnualLeaveLeftCount(userId, year);
	}

	@Override
	public int countAnnualLeaveUsedTimes(String userId, String year) {
		return leaveProcessMapper.countAnnualLeaveUsedTimes(userId, year);
	}

	@Override
	public List<LeaveTypeCount> countUserLeaveByTypes(String userId, String year) {
		return leaveProcessMapper.countUserLeaveByTypes(userId, year);
	}

	@Override
	public int countTotalLeave(String year) {
		return leaveProcessMapper.countTotalLeave(year);
	}

	// 每月请假类型占比查询（api）
	@Override
	public List<CalculationChart> leaveTypeList(Integer departmentId) {
		return leaveProcessMapper.leaveTypeList(departmentId);
	}

	// 个人休假情况统计（api）
	@Override
	public LeavePersonalStatistics leavePersonalStatisticsItem(String policeId, String year, Integer halfYear) {
		return leaveProcessMapper.leavePersonalStatisticsItem(policeId, year, halfYear);
	}

	// 个人请假原因占比情况（api）
	@Override
	public List<CalculationChart> leaveReasonAnalysisList(String policeId) {
		return leaveProcessMapper.leaveReasonAnalysisList(policeId);
	}

	// 个人最近请假查询列表（api）
	@Override
	public List<LeaveProcess> leavePersonalList(String policeId, String leaveType, Integer identification) {
		return leaveProcessMapper.leavePersonalList(policeId, leaveType, identification);
	}

	// 每月请假总人数查询（api）
	@Override
	public List<CalculationChart> leavetotalNumList(Integer departmentId) {
		return leaveProcessMapper.leavetotalNumList(departmentId);
	}

	// 每月休年假人数查询（api）
	@Override
	public List<CalculationChart> leaveAnnualNumList(Integer departmentId) {
		return leaveProcessMapper.leaveAnnualNumList(departmentId);
	}

	// 每月调休人数查询（api）
	@Override
	public List<CalculationChart> leaveOffNumList(Integer departmentId) {
		return leaveProcessMapper.leaveOffNumList(departmentId);
	}

	// 每月请假人数查询（api）
	@Override
	public List<CalculationChart> leaveNumList(Integer departmentId) {
		return leaveProcessMapper.leaveNumList(departmentId);
	}

	// 各部门年假使用情况查询（api）
	@Override
	public List<LeaveChart> leaveDepAnnualChartList() {
		return leaveProcessMapper.leaveDepAnnualChartList();
	}

	// 各部门平均年假使用率查询（api）
	@Override
	public int leaveDepAverageItem(Integer departmentId) {
		return leaveProcessMapper.leaveDepAverageItem(departmentId);
	}

	// 休假中民警数量（api）
	@Override
	public int onHolidayNumItem(Integer departmentId) {
		return leaveProcessMapper.onHolidayNumItem(departmentId);
	}

	// 请假中民警数量（api）
	@Override
	public int askingForLeaveNumItem(Integer departmentId) {
		return leaveProcessMapper.askingForLeaveNumItem(departmentId);
	}

	@Override
	public List<LeaveRestManage> getLeaveRestManageList(Integer departmentId, Integer positionId, String keyword,
			Integer pageSize) {
		return leaveRestManageMapper.getLeaveRestManageList(departmentId, positionId, keyword, pageSize);
	}

	@Override
	public List<LeaveIntegralManage> getLeaveIntegralManageList(Integer policeMonth, Integer departmentId,
			String keyword, Integer pageSize) {
		return leaveIntegralManageMapper.getLeaveIntegralManageList(policeMonth, departmentId, keyword, pageSize);
	}

	@Override
	public Integer addLeaveIntegralManage(LeaveIntegralManage leaveIntegralManage) {
		return leaveIntegralManageMapper.addLeaveIntegralManage(leaveIntegralManage);
	}

	@Override
	public List<LeavePower> getLeaveIntegralAuditPowerList(Integer departmentId, String keyword, Integer pageSize) {
		return leavePowerMapper.getLeaveIntegralAuditPowerList(departmentId, keyword, pageSize);
	}

	@Override
	public int addLeaveIntegralAuditPower(LeavePower leaveIntegralAuditPower) {
		return leavePowerMapper.addLeaveIntegralAuditPower(leaveIntegralAuditPower);
	}

	@Override
	public int updateLeaveIntegralAuditPower(LeavePower record) {
		return leavePowerMapper.updateLeaveIntegralAuditPower(record);
	}

	@Override
	public LeavePower getLeaveIntegralAuditPowerById(Integer id) {
		return leavePowerMapper.getLeaveIntegralAuditPowerById(id);
	}

	// 警员年假新增
	@Override
	public int leaveAnnualCreat(LeaveAnnual leaveAnnual) {
		return leaveAnnualMapper.leaveAnnualCreat(leaveAnnual);
	}

	// 警员年假修改
	@Override
	public int leaveAnnualUpdate(LeaveAnnual leaveAnnual) {
		return leaveAnnualMapper.leaveAnnualUpdate(leaveAnnual);
	}

	// 警员年假详情查询
	@Override
	public LeaveAnnual leaveAnnualItem(Integer id, String policeId, String year) {
		return leaveAnnualMapper.leaveAnnualItem(id, policeId, year);
	}

	// 警员年假列表查询
	@Override
	public List<LeaveAnnual> leaveAnnualList(String policeId, String year) {
		return leaveAnnualMapper.leaveAnnualList(policeId, year);
	}

	// 警员年假天数列表查询
	@Override
	public List<LeaveAnnual> policeLeaveAnnualExistList() {
		return leaveAnnualMapper.policeLeaveAnnualExistList();
	}

	// 请假记录修改
	@Override
	public int leaveProcessUpdate(LeaveProcess leaveProcess) {
		return leaveProcessMapper.leaveProcessUpdate(leaveProcess);
	}

	@Override
	public int getLeaveRestManageListCount(Integer departmentId, Integer positionId, String keyword) {
		return leaveRestManageMapper.getLeaveRestManageListCount(departmentId, positionId, keyword);
	}

	@Override
	public LeaveRestManage getLeaveRestManageOne(Integer id) {
		return leaveRestManageMapper.getLeaveRestManageOne(id);
	}

	@Override
	public List<LeaveTrain> getLeaveTrainList(Integer type, Integer departmentId, String keyWord, Integer pageSize) {
		return leaveTrainMapper.getLeaveTrainList(type, departmentId, keyWord, pageSize);
	}

	@Override
	public int getLeaveTrainListCount(Integer type, Integer departmentId, String keyWord) {
		return leaveTrainMapper.getLeaveTrainListCount(type, departmentId, keyWord);
	}

	@Override
	public int insertLeaveTrainList(LeaveTrain leaveTrain) {
		return leaveTrainMapper.insertLeaveTrainList(leaveTrain);
	}

	@Override
	public int getLeaveIntegralManageCount(Integer policeMonth, Integer departmentId, String keyword) {
		return leaveIntegralManageMapper.getLeaveIntegralManageCount(policeMonth, departmentId, keyword);
	}

	@Override
	public int getLeaveIntegralAuditPowerCount(Integer departmentId, String keyword) {
		return leavePowerMapper.getLeaveIntegralAuditPowerCount(departmentId, keyword);
	}

	@Override
	public List<LeaveOvertimeRule> getLeaveOvertimeRuleList() {
		return leaveOvertimeRuleMapper.getLeaveOvertimeRuleList();
	}

	@Override
	public int updateByPrimaryKeySelective(LeaveOvertimeRule leaveOvertimeRule) {
		return leaveOvertimeRuleMapper.updateByPrimaryKeySelective(leaveOvertimeRule);
	}

	@Override
	public List<LeaveIntegralExchangeRule> getLeaveIntegralExchangeRuleList() {
		return leaveIntegralExchangeRuleMapper.getLeaveIntegralExchangeRuleList();
	}

	@Override
	public int updateByPrimaryKeySelective(LeaveIntegralExchangeRule leaveIntegralExchangeRule) {
		return leaveIntegralExchangeRuleMapper.updateByPrimaryKeySelective(leaveIntegralExchangeRule);
	}

	@Override
	public List<LeaveRestAlarmRule> getLeaveRestAlarmRuleList() {
		return leaveRestAlarmRuleMapper.getLeaveRestAlarmRuleList();
	}

	@Override
	public int updateByPrimaryKeySelective(LeaveRestAlarmRule leaveRestAlarmRule) {
		return leaveRestAlarmRuleMapper.updateByPrimaryKeySelective(leaveRestAlarmRule);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return leaveTrainMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(LeaveTrain leaveTrain) {
		return leaveTrainMapper.updateByPrimaryKeySelective(leaveTrain);
	}

	@Override
	public int deleteLeaveIntegralManageById(Integer id) {
		return leaveIntegralManageMapper.deleteLeaveIntegralManageById(id);
	}

	@Override
	public int updateLeaveIntegralManageById(LeaveIntegralManage leaveIntegralManage) {
		return leaveIntegralManageMapper.updateLeaveIntegralManageById(leaveIntegralManage);
	}

	@Override
	public int deleteLeaveIntegralAuditPower(Integer id) {
		return leavePowerMapper.deleteLeaveIntegralAuditPower(id);
	}

	@Override
	public List<String> getScoredPoliceIds() {
		return leavePowerMapper.getScoredPoliceIds();
	}

	@Override
	public int checkIsExist(String policeId, Integer id) {
		return leavePowerMapper.checkIsExist(policeId, id);
	}

	@Override
	public int isExist(Integer departmentId, String policeId, Integer moduleId) {
		return leavePowerMapper.isExist(departmentId, policeId, moduleId);
	}

	// 个人最新调休记录查询
	@Override
	public List<LeaveCompensatoryRecord> leaveCompensatoryRecordNewestList(String policeId, Integer readStatus) {
		return leaveCompensatoryRecordMapper.leaveCompensatoryRecordNewestList(policeId, readStatus);
	}

	// 调休记录新增
	@Override
	public int leaveCompensatoryRecordCreat(LeaveCompensatoryRecord leaveCompensatoryRecord) {
		return leaveCompensatoryRecordMapper.leaveCompensatoryRecordCreat(leaveCompensatoryRecord);
	}

	// 调休记录修改
	@Override
	public int leaveCompensatoryRecordUpdate(LeaveCompensatoryRecord leaveCompensatoryRecord) {
		return leaveCompensatoryRecordMapper.leaveCompensatoryRecordUpdate(leaveCompensatoryRecord);
	}

	// 个人调休记录详情查询
	@Override
	public LeaveCompensatoryRecord leaveCompensatoryRecordItem(Integer id) {
		return leaveCompensatoryRecordMapper.leaveCompensatoryRecordItem(id);
	}

	// 个人调休记录查询
	@Override
	public List<LeaveCompensatoryRecord> leaveCompensatoryRecordList(String policeId, Integer type, String dateTime,
			Integer readStatus, Integer pageSize, Integer pageNum) {
		return leaveCompensatoryRecordMapper.leaveCompensatoryRecordList(policeId, type, dateTime, readStatus, pageSize,
				pageNum);
	}

	// 个人调休记录总数统计
	@Override
	public int leaveCompensatoryRecordCount(String policeId, Integer type, String dateTime, Integer readStatus) {
		return leaveCompensatoryRecordMapper.leaveCompensatoryRecordCount(policeId, type, dateTime, readStatus);
	}

	@Override
	public LeaveTrain getLeaveTrainOne(Integer id) {
		return leaveTrainMapper.getLeaveTrainOne(id);
	}

	@Override
	public LeaveIntegralManage getLeaveIntegralManageOne(Integer id) {
		return leaveIntegralManageMapper.getLeaveIntegralManageOne(id);
	}

	// 积分兑换记录新增
	@Override
	public int leavePointsExchangeRecordCreat(LeavePointsExchangeRecord record) {
		return leavePointsExchangeRecordMapper.leavePointsExchangeRecordCreat(record);
	}

	// 积分兑换记录修改
	@Override
	public int leavePointsExchangeRecordUpdate(LeavePointsExchangeRecord record) {
		return leavePointsExchangeRecordMapper.leavePointsExchangeRecordUpdate(record);
	}

	// 个人最新积分兑换记录查询
	@Override
	public List<LeavePointsExchangeRecord> leavePointsExchangeRecordNewestList(String policeId) {
		return leavePointsExchangeRecordMapper.leavePointsExchangeRecordNewestList(policeId);
	}

	// 个人积分兑换记录查询
	@Override
	public List<LeavePointsExchangeRecord> leavePointsExchangeRecordList(String policeId, String dateTime,
			Integer pageSize, Integer pageNum) {
		return leavePointsExchangeRecordMapper.leavePointsExchangeRecordList(policeId, dateTime, pageSize, pageNum);
	}

	// 个人积分兑换记录总数统计
	@Override
	public int leavePointsExchangeRecordCount(String policeId, String dateTime) {
		return leavePointsExchangeRecordMapper.leavePointsExchangeRecordCount(policeId, dateTime);
	}

	// 个人积分兑换记录详情查询
	@Override
	public LeavePointsExchangeRecord leavePointsExchangeRecordItem(Integer id) {
		return leavePointsExchangeRecordMapper.leavePointsExchangeRecordItem(id);
	}

	// 警员积分新增
	@Override
	public int leavePointsCreat(LeavePoints record) {
		return leavePointsMapper.leavePointsCreat(record);
	}

	// 警员积分修改
	@Override
	public int leavePointsUpdate(LeavePoints record) {
		return leavePointsMapper.leavePointsUpdate(record);
	}

	// 警员积分详情查询
	@Override
	public LeavePoints leavePointsItem(Integer id, String policeId, String year, Integer halfYear) {
		return leavePointsMapper.leavePointsItem(id, policeId, year, halfYear);
	}

	// 警员加班时长新增
	@Override
	public int leaveOvertimeCreat(LeaveOvertime record) {
		return leaveOvertimeMapper.leaveOvertimeCreat(record);
	}

	// 警员加班时长修改
	@Override
	public int leaveOvertimeUpdate(LeaveOvertime record) {
		return leaveOvertimeMapper.leaveOvertimeUpdate(record);
	}

	// 警员加班时长详情
	@Override
	public LeaveOvertime leaveOvertimeItem(Integer id, String policeId, String year) {
		return leaveOvertimeMapper.leaveOvertimeItem(id, policeId, year);
	}

	@Override
	public int addMoreLeaveTrain(List<LeaveTrain> leaveTrain) {
		return leaveTrainMapper.addMoreLeaveTrain(leaveTrain);
	}

	// 警员值班时长新增
	@Override
	public int leaveDutyCreat(LeaveDuty record) {
		return leaveDutyMapper.leaveDutyCreat(record);
	}

	// 警员值班时长修改
	@Override
	public int leaveDutyUpdate(LeaveDuty record) {
		return leaveDutyMapper.leaveDutyUpdate(record);
	}

	// 警员值班时长详情
	@Override
	public LeaveDuty leaveDutyItem(Integer id, String policeId, String year, String month) {
		return leaveDutyMapper.leaveDutyItem(id, policeId, year, month);
	}

	@Override
	public int addMoreLeaveIntegralManage(List<LeaveIntegralManage> leaveIntegralManageList) {
		return leaveIntegralManageMapper.addMoreLeaveIntegralManage(leaveIntegralManageList);
	}

	@Override
	public int addMoreleavePoints(List<LeavePoints> leavePointsList) {
		return leavePointsMapper.addMoreleavePoints(leavePointsList);
	}

	// 年休假人数统计
	@Override
	public int annualLeaveNum(Integer departmentId) {
		return leaveAnnualMapper.annualLeaveNum(departmentId);
	}

	// 未休年假人数统计
	@Override
	public int notAnnualLeaveNum(Integer departmentId) {
		return leaveAnnualMapper.notAnnualLeaveNum(departmentId);
	}

	// 单位年休假达标数量统计
	@Override
	public int depAnnualLeaveNum() {
		return leaveAnnualMapper.depAnnualLeaveNum();
	}

	// 有年休假的单位数量统计
	@Override
	public int totalDepAnnualLeaveNum() {
		return leaveAnnualMapper.totalDepAnnualLeaveNum();
	}

	// 各单位疗休养人员数量统计
	@Override
	public int leaveDepTrainStatisticsNum(Integer type, Integer departmentId) {
		return leaveTrainMapper.leaveDepTrainStatisticsNum(type, departmentId);
	}

	// 各单位积分未兑换人数查询
	@Override
	public List<CalculationChart> leaveDepPointsExchangeChart(String year, Integer halfYear) {
		return leavePointsMapper.leaveDepPointsExchangeChart(year, halfYear);
	}

	// 各单位需调休人数查询
	@Override
	public List<CalculationChart> leaveDepCompensatoryChart() {
		return leaveProcessMapper.leaveDepCompensatoryChart();
	}

	// 办案积分预警(2条)查询
	@Override
	public List<LeaveLeaderAlarm> leaveLeaderPointsAlarmNewestList(String policeId, String year, Integer halfYear) {
		return leavePointsMapper.leaveLeaderPointsAlarmNewestList(policeId, year, halfYear);
	}

	// 办案积分预警总数统计
	@Override
	public int leaveLeaderPointsAlarmNewestCount(String policeId, String year, Integer halfYear) {
		return leavePointsMapper.leaveLeaderPointsAlarmNewestCount(policeId, year, halfYear);
	}

	// 办案积分预警分页查询
	@Override
	public List<LeaveLeaderAlarm> leaveLeaderPointsAlarmList(String policeId, String year, Integer halfYear,
			Integer pageSize, Integer pageNum) {
		return leavePointsMapper.leaveLeaderPointsAlarmList(policeId, year, halfYear, pageSize, pageNum);
	}

	// 加班预警(1条)查询
	@Override
	public List<LeaveLeaderAlarm> leaveLeaderOvertimeAlarmNewestList(String policeId) {
		return leaveOvertimeMapper.leaveLeaderOvertimeAlarmNewestList(policeId);
	}

	// 加班预警总数统计
	@Override
	public int leaveLeaderOvertimeAlarmNewestCount(String policeId) {
		return leaveOvertimeMapper.leaveLeaderOvertimeAlarmNewestCount(policeId);
	}

	// 加班预警分页查询
	@Override
	public List<LeaveLeaderAlarm> leaveLeaderOvertimeAlarmList(String policeId, Integer pageSize, Integer pageNum) {
		return leaveOvertimeMapper.leaveLeaderOvertimeAlarmList(policeId, pageSize, pageNum);
	}

	// 警员调休预警(2条)查询
	@Override
	public List<LeaveCompensatoryAlarm> leaveLeaderCompensatoryAlarmNewestList(String policeId) {
		return leaveCompensatoryAlarmMapper.leaveLeaderCompensatoryAlarmNewestList(policeId);
	}

	// 警员调休预警总数统计
	@Override
	public int leaveLeaderCompensatoryAlarmNewestCount(String policeId) {
		return leaveCompensatoryAlarmMapper.leaveLeaderCompensatoryAlarmNewestCount(policeId);
	}

	// 警员调休预警分页查询
	@Override
	public List<LeaveCompensatoryAlarm> leaveLeaderCompensatoryAlarmList(String policeId, Integer pageSize,
			Integer pageNum) {
		return leaveCompensatoryAlarmMapper.leaveLeaderCompensatoryAlarmList(policeId, pageSize, pageNum);
	}

	// 待办事项查询(2条)
	@Override
	public List<LeaveCompensatoryRecord> leaveLeaderNeedDealtNewestList(String policeId) {
		return leaveCompensatoryRecordMapper.leaveLeaderNeedDealtNewestList(policeId);
	}

	// 完成事项总数统计
	@Override
	public int leaveLeaderNeedDealtNewestCount(String policeId) {
		return leaveCompensatoryRecordMapper.leaveLeaderNeedDealtNewestCount(policeId);
	}

	// 待办事项分页查询
	@Override
	public List<LeaveCompensatoryRecord> leaveLeaderNeedDealtList(String policeId, Integer type, String dateTime,
			Integer pageSize, Integer pageNum) {
		return leaveCompensatoryRecordMapper.leaveLeaderNeedDealtList(policeId, type, dateTime, pageSize, pageNum);
	}

	// 待办事项总数统计
	@Override
	public int leaveLeaderNeedDealtCount(String policeId, Integer type, String dateTime) {
		return leaveCompensatoryRecordMapper.leaveLeaderNeedDealtCount(policeId, type, dateTime);
	}

	// 已办事项分页查询
	@Override
	public List<LeaveCompensatoryRecord> leaveLeaderOverDealtList(String policeId, Integer type, String dateTime,
			Integer pageSize, Integer pageNum) {
		return leaveCompensatoryRecordMapper.leaveLeaderOverDealtList(policeId, type, dateTime, pageSize, pageNum);
	}

	// 已办事项总数统计
	@Override
	public int leaveLeaderOverDealtCount(String policeId, Integer type, String dateTime) {
		return leaveCompensatoryRecordMapper.leaveLeaderOverDealtCount(policeId, type, dateTime);
	}

	// 调休记录列表查询(定时任务修改调休记录状态进程)
	@Override
	public List<LeaveCompensatoryRecord> leaveCompensatoryRecordStatusList() {
		return leaveCompensatoryRecordMapper.leaveCompensatoryRecordStatusList();
	}

	// 根据当前用户查询审核人
	@Override
	public LeavePower leavePowerItem(String policeId) {
		return leavePowerMapper.leavePowerItem(policeId);
	}

	// 个人加班时长查询
	@Override
	public LeavePersonalStatistics leavePersonalOvertimeItem(String policeId) {
		return leaveProcessMapper.leavePersonalOvertimeItem(policeId);
	}

	// 个人积分查询
	@Override
	public LeavePersonalStatistics leavePersonalPointsItems(String policeId, String year, Integer halfYear) {
		return leaveProcessMapper.leavePersonalPointsItems(policeId, year, halfYear);
	}

	// 警员根据policeId,year,halfYear修改积分
	@Override
	public int leavePointsResidualUpdate(LeavePoints record) {
		return leavePointsMapper.leavePointsResidualUpdate(record);
	}

	// 根据年份和警员号修改
	@Override
	public int leaveOvertimeYearUpdate(LeaveOvertime record) {
		return leaveOvertimeMapper.leaveOvertimeYearUpdate(record);
	}

	// 根据季度查询民警累计出差天数
	@Override
	public List<LeaveCompensatoryAlarm> quarterPoliceList(String startTime, String endTime) {
		return leaveCompensatoryAlarmMapper.quarterPoliceList(startTime, endTime);
	}

	// 警员调休预警新增
	@Override
	public int leaveCompensatoryAlarmCreat(LeaveCompensatoryAlarm record) {
		return leaveCompensatoryAlarmMapper.leaveCompensatoryAlarmCreat(record);
	}

	// 警员调休预警修改
	@Override
	public int leaveCompensatoryAlarmUpdate(LeaveCompensatoryAlarm record) {
		return leaveCompensatoryAlarmMapper.leaveCompensatoryAlarmUpdate(record);
	}

	// 根据季度,创建时间,警号查询季度预警
	@Override
	public LeaveCompensatoryAlarm quarterPoliceItem(Integer type, Integer ruleId, String policeId, String startTime,
			String endTime) {
		return leaveCompensatoryAlarmMapper.quarterPoliceItem(type, ruleId, policeId, startTime, endTime);
	}

	// 中层领导查看自己部门疗休养人员数据
	@Override
	public List<LeaveTrain> leaveDepTrainStatisticsList(Integer type, String policeId) {
		return leaveTrainMapper.leaveDepTrainStatisticsList(type, policeId);
	}

	// 根据每月查询民警累计出差天数
	@Override
	public List<LeaveCompensatoryAlarm> monthPoliceList(String startTime, String endTime) {
		return leaveCompensatoryAlarmMapper.monthPoliceList(startTime, endTime);
	}

	// 查询单次民警出差天数
	@Override
	public List<LeaveCompensatoryAlarm> singlePoliceList() {
		return leaveCompensatoryAlarmMapper.singlePoliceList();
	}

	// 修改请假表状态
	@Override
	public int leaveProcessAlarmItemUpdate(LeaveProcess lProcess) {
		return leaveProcessMapper.leaveProcessAlarmItemUpdate(lProcess);
	}

	@Override
	public Integer nowMonthHave(String policeId, Integer policeMonth) {
		return leaveIntegralManageMapper.nowMonthHave(policeId, policeMonth);
	}

	// 查询连续未打卡天数
	@Override
	public List<LeaveCompensatoryAlarm> notRecordList() {
		return leaveCompensatoryAlarmMapper.notRecordList();
	}

	// 获得调修预警规则
	@Override
	public LeaveRestAlarmRule getLeaveRestAlarmRuleItem(Integer id) {
		return leaveRestAlarmRuleMapper.getLeaveRestAlarmRuleItem(id);
	}

	// 查询连续工作预警
	@Override
	public LeaveCompensatoryAlarm continuePoliceItem(Integer type, String policeId) {
		return leaveCompensatoryAlarmMapper.continuePoliceItem(type, policeId);
	}

	// 查询个人连续未打卡天数
	@Override
	public LeaveCompensatoryAlarm notRecordPoliceItem(String policeId) {
		return leaveCompensatoryAlarmMapper.notRecordPoliceItem(policeId);
	}

	// 警员调休预警连续加班修改
	@Override
	public int leaveCompensatoryAlarmTimeUpdate(LeaveCompensatoryAlarm record) {
		return leaveCompensatoryAlarmMapper.leaveCompensatoryAlarmTimeUpdate(record);
	}

	// 警员调休预警不打卡修改
	@Override
	public int leaveCompensatoryAlarmAllUpdate(LeaveCompensatoryAlarm record) {
		return leaveCompensatoryAlarmMapper.leaveCompensatoryAlarmAllUpdate(record);
	}

	// 查询预警连续加班人员
	@Override
	public List<LeaveCompensatoryAlarm> alarmComRecordList() {
		return leaveCompensatoryAlarmMapper.alarmComRecordList();
	}

	// 个人加班可调休天数查询
	@Override
	public LeavePersonalStatistics leavePersonalOverTimeChangeDays(String policeId, String currentYearStr) {
		return leaveProcessMapper.leavePersonalOverTimeChangeDays(policeId, currentYearStr);
	}

	// 个人积分可调休天数查询
	@Override
	public LeavePersonalStatistics leavePersonalpointsChangeDays(String policeId, String currentYearStr,
			Integer halfYear) {
		return leaveProcessMapper.leavePersonalpointsChangeDays(policeId, currentYearStr, halfYear);
	}

	// 各单位年休假率查询
	@Override
	public List<LeaveChart> leaveDepAnnualNotStandardStatistics() {
		return leaveProcessMapper.leaveDepAnnualNotStandardStatistics();
	}

	// 加班调休列表查询
	@Override
	public List<LeaveProcess> leaveLeaderOvertimeTaskList() {
		return leaveProcessMapper.leaveLeaderOvertimeTaskList();
	}

	// 积分调休列表查询
	@Override
	public List<LeaveProcess> leaveLeaderPointTaskList() {
		return leaveProcessMapper.leaveLeaderPointTaskList();
	}

	// 审核对象人员删除
	@Override
	public int leavePowerObjectDelete(Integer id) {
		return leavePowerObjectMapper.leavePowerObjectDelete(id);
	}

	// 审核对象新增
	@Override
	public int leavePowerObjectCreat(LeavePowerObject record) {
		return leavePowerObjectMapper.leavePowerObjectCreat(record);
	}

	// 审核对象人员修改
	@Override
	public int leavePowerObjectUpdate(LeavePowerObject record) {
		return leavePowerObjectMapper.leavePowerObjectUpdate(record);
	}

	// 局领导最新调休提醒查询
	@Override
	public List<LeaveCompensatoryReadRecord> leaveLeaderCompensatoryRecordNewestList(String policeId,
			Integer approvedReadStatus) {
		return leaveCompensatoryReadRecordMapper.leaveLeaderCompensatoryRecordNewestList(policeId, approvedReadStatus);
	}

	// 局领导调休提醒分页查询
	@Override
	public List<LeaveCompensatoryReadRecord> leaveLeaderCompensatoryRecordList(String policeId, Integer type,
			String dateTime, Integer approvedReadStatus, Integer pageSize, Integer pageNum) {
		return leaveCompensatoryReadRecordMapper.leaveLeaderCompensatoryRecordList(policeId, type, dateTime,
				approvedReadStatus, pageSize, pageNum);
	}

	// 局领导调休提醒记录总数统计
	@Override
	public int leaveLeaderCompensatoryRecordCount(String policeId, Integer type, String dateTime,
			Integer approvedReadStatus) {
		return leaveCompensatoryReadRecordMapper.leaveLeaderCompensatoryRecordCount(policeId, type, dateTime,
				approvedReadStatus);
	}

	@Override
	public int leavePowerObjectDeleteByPowerId(Integer powerId) {
		return leavePowerObjectMapper.leavePowerObjectDeleteByPowerId(powerId);
	}

	@Override
	public String getPoliceObjectIdsByPowerId(Integer powerId) {
		return leavePowerObjectMapper.getPoliceObjectIdsByPowerId(powerId);
	}

	// 个人当前年情况统计
	@Override
	public LeaveThisYearStatistics leaveThisYearStatisticsItem(String policeId) {
		return leaveProcessMapper.leaveThisYearStatisticsItem(policeId);
	}

	// 调休人读取状态修改
	@Override
	public int leaveReadUpdate(LeaveCompensatoryRecord record) {
		return leaveCompensatoryRecordMapper.leaveReadUpdate(record);
	}

	// 审批人读取状态修改
	@Override
	public int leaveApprovedReadUpdate(LeaveCompensatoryRecord record) {
		return leaveCompensatoryRecordMapper.leaveApprovedReadUpdate(record);
	}

	// 局领导需强制调休人员详情查询
	@Override
	public LeaveCompensatoryAlarm leaveLeaderCompensatoryAlarmItem(Integer id) {
		return leaveCompensatoryAlarmMapper.leaveLeaderCompensatoryAlarmItem(id);
	}

	// 局领导调休提醒记录新增
	@Override
	public int leaveCompensatoryReadRecordCreat(LeaveCompensatoryReadRecord record) {
		return leaveCompensatoryReadRecordMapper.leaveCompensatoryReadRecordCreat(record);
	}

	// 局领导调休提醒记录修改
	@Override
	public int leaveCompensatoryReadRecordUpdate(LeaveCompensatoryReadRecord record) {
		return leaveCompensatoryReadRecordMapper.leaveCompensatoryReadRecordUpdate(record);
	}

	// 查询当前民警所属领导
	@Override
	public List<LeavePower> LeavePowerPoliceList(String policeId) {
		return leavePowerMapper.LeavePowerPoliceList(policeId);
	}
	
	@Override
	public Integer leaveAdjustNum() {
		// TODO Auto-generated method stub
		return leaveAnnualMapper.leaveAdjustNum();
	}
	
	@Override
	public Integer leaveNum() {
		// TODO Auto-generated method stub
		return leaveAnnualMapper.leaveNum();
	}
	
	@Override
	public Integer leaveYearNum() {
		// TODO Auto-generated method stub
		return leaveAnnualMapper.leaveYearNum();
	}
	
	@Override
	public Double leaveYearUsedRator() {
		// TODO Auto-generated method stub
		return leaveAnnualMapper.leaveYearUsedRator();
	}
	
	@Override
	public List<LeaveOvertime> leaveOverTimeTopFiveWeek() {
		// TODO Auto-generated method stub
		return leaveOvertimeMapper.leaveOverTimeTopFiveWeek();
	}
	
	@Override
	public List<LeaveCompensatoryRecord> getLeaveCompensatoryRecord(Integer departmentId,
			Integer positionId, String keyword,
			Integer pageSize) {
		// TODO Auto-generated method stub
		return leaveCompensatoryRecordMapper.getLeaveCompensatoryRecord(departmentId, positionId, keyword, pageSize);
	}
	
	@Override
	public Integer getLeaveCompensatoryRecordCount(Integer departmentId,
			Integer positionId, String keyword) {
		// TODO Auto-generated method stub
		return leaveCompensatoryRecordMapper.getLeaveCompensatoryRecordCount(departmentId, positionId, keyword);
	}
	
	@Override
	public LeaveCompensatoryRecord getLeaveCompensatoryRecordOne(Integer id) {
		// TODO Auto-generated method stub
		return leaveCompensatoryRecordMapper.getLeaveCompensatoryRecordOne(id);
	}

}

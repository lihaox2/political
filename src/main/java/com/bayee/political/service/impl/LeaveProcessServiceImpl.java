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
	LeavePowerMapper leavePowerMapper;// AI??????????????????

	@Autowired
	LeavePowerObjectMapper leavePowerObjectMapper;// ??????????????????

	@Autowired
	LeaveTrainMapper leaveTrainMapper;

	@Autowired
	LeaveAnnualMapper leaveAnnualMapper;// ????????????

	@Autowired
	LeaveOvertimeRuleMapper leaveOvertimeRuleMapper;

	@Autowired
	LeaveIntegralExchangeRuleMapper leaveIntegralExchangeRuleMapper;

	@Autowired
	LeaveRestAlarmRuleMapper leaveRestAlarmRuleMapper;

	@Autowired
	LeaveCompensatoryRecordMapper leaveCompensatoryRecordMapper;// ????????????

	@Autowired
	LeavePointsExchangeRecordMapper leavePointsExchangeRecordMapper;// ??????????????????

	@Autowired
	LeavePointsMapper leavePointsMapper;// ????????????

	@Autowired
	LeaveOvertimeMapper leaveOvertimeMapper;// ????????????

	@Autowired
	LeaveDutyMapper leaveDutyMapper;// ????????????

	@Autowired
	LeaveCompensatoryAlarmMapper leaveCompensatoryAlarmMapper;// ??????????????????

	@Autowired
	LeaveCompensatoryReadRecordMapper leaveCompensatoryReadRecordMapper;// ?????????????????????????????????

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

	// ?????????????????????????????????api???
	@Override
	public List<CalculationChart> leaveTypeList(Integer departmentId) {
		return leaveProcessMapper.leaveTypeList(departmentId);
	}

	// ???????????????????????????api???
	@Override
	public LeavePersonalStatistics leavePersonalStatisticsItem(String policeId, String year, Integer halfYear) {
		return leaveProcessMapper.leavePersonalStatisticsItem(policeId, year, halfYear);
	}

	// ?????????????????????????????????api???
	@Override
	public List<CalculationChart> leaveReasonAnalysisList(String policeId) {
		return leaveProcessMapper.leaveReasonAnalysisList(policeId);
	}

	// ?????????????????????????????????api???
	@Override
	public List<LeaveProcess> leavePersonalList(String policeId, String leaveType, Integer identification) {
		return leaveProcessMapper.leavePersonalList(policeId, leaveType, identification);
	}

	// ??????????????????????????????api???
	@Override
	public List<CalculationChart> leavetotalNumList(Integer departmentId) {
		return leaveProcessMapper.leavetotalNumList(departmentId);
	}

	// ??????????????????????????????api???
	@Override
	public List<CalculationChart> leaveAnnualNumList(Integer departmentId) {
		return leaveProcessMapper.leaveAnnualNumList(departmentId);
	}

	// ???????????????????????????api???
	@Override
	public List<CalculationChart> leaveOffNumList(Integer departmentId) {
		return leaveProcessMapper.leaveOffNumList(departmentId);
	}

	// ???????????????????????????api???
	@Override
	public List<CalculationChart> leaveNumList(Integer departmentId) {
		return leaveProcessMapper.leaveNumList(departmentId);
	}

	// ????????????????????????????????????api???
	@Override
	public List<LeaveChart> leaveDepAnnualChartList() {
		return leaveProcessMapper.leaveDepAnnualChartList();
	}

	// ???????????????????????????????????????api???
	@Override
	public int leaveDepAverageItem(Integer departmentId) {
		return leaveProcessMapper.leaveDepAverageItem(departmentId);
	}

	// ????????????????????????api???
	@Override
	public int onHolidayNumItem(Integer departmentId) {
		return leaveProcessMapper.onHolidayNumItem(departmentId);
	}

	// ????????????????????????api???
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

	// ??????????????????
	@Override
	public int leaveAnnualCreat(LeaveAnnual leaveAnnual) {
		return leaveAnnualMapper.leaveAnnualCreat(leaveAnnual);
	}

	// ??????????????????
	@Override
	public int leaveAnnualUpdate(LeaveAnnual leaveAnnual) {
		return leaveAnnualMapper.leaveAnnualUpdate(leaveAnnual);
	}

	// ????????????????????????
	@Override
	public LeaveAnnual leaveAnnualItem(Integer id, String policeId, String year) {
		return leaveAnnualMapper.leaveAnnualItem(id, policeId, year);
	}

	// ????????????????????????
	@Override
	public List<LeaveAnnual> leaveAnnualList(String policeId, String year) {
		return leaveAnnualMapper.leaveAnnualList(policeId, year);
	}

	// ??????????????????????????????
	@Override
	public List<LeaveAnnual> policeLeaveAnnualExistList() {
		return leaveAnnualMapper.policeLeaveAnnualExistList();
	}

	// ??????????????????
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

	// ??????????????????????????????
	@Override
	public List<LeaveCompensatoryRecord> leaveCompensatoryRecordNewestList(String policeId, Integer readStatus) {
		return leaveCompensatoryRecordMapper.leaveCompensatoryRecordNewestList(policeId, readStatus);
	}

	// ??????????????????
	@Override
	public int leaveCompensatoryRecordCreat(LeaveCompensatoryRecord leaveCompensatoryRecord) {
		return leaveCompensatoryRecordMapper.leaveCompensatoryRecordCreat(leaveCompensatoryRecord);
	}

	// ??????????????????
	@Override
	public int leaveCompensatoryRecordUpdate(LeaveCompensatoryRecord leaveCompensatoryRecord) {
		return leaveCompensatoryRecordMapper.leaveCompensatoryRecordUpdate(leaveCompensatoryRecord);
	}

	// ??????????????????????????????
	@Override
	public LeaveCompensatoryRecord leaveCompensatoryRecordItem(Integer id) {
		return leaveCompensatoryRecordMapper.leaveCompensatoryRecordItem(id);
	}

	// ????????????????????????
	@Override
	public List<LeaveCompensatoryRecord> leaveCompensatoryRecordList(String policeId, Integer type, String dateTime,
			Integer readStatus, Integer pageSize, Integer pageNum) {
		return leaveCompensatoryRecordMapper.leaveCompensatoryRecordList(policeId, type, dateTime, readStatus, pageSize,
				pageNum);
	}

	// ??????????????????????????????
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

	// ????????????????????????
	@Override
	public int leavePointsExchangeRecordCreat(LeavePointsExchangeRecord record) {
		return leavePointsExchangeRecordMapper.leavePointsExchangeRecordCreat(record);
	}

	// ????????????????????????
	@Override
	public int leavePointsExchangeRecordUpdate(LeavePointsExchangeRecord record) {
		return leavePointsExchangeRecordMapper.leavePointsExchangeRecordUpdate(record);
	}

	// ????????????????????????????????????
	@Override
	public List<LeavePointsExchangeRecord> leavePointsExchangeRecordNewestList(String policeId) {
		return leavePointsExchangeRecordMapper.leavePointsExchangeRecordNewestList(policeId);
	}

	// ??????????????????????????????
	@Override
	public List<LeavePointsExchangeRecord> leavePointsExchangeRecordList(String policeId, String dateTime,
			Integer pageSize, Integer pageNum) {
		return leavePointsExchangeRecordMapper.leavePointsExchangeRecordList(policeId, dateTime, pageSize, pageNum);
	}

	// ????????????????????????????????????
	@Override
	public int leavePointsExchangeRecordCount(String policeId, String dateTime) {
		return leavePointsExchangeRecordMapper.leavePointsExchangeRecordCount(policeId, dateTime);
	}

	// ????????????????????????????????????
	@Override
	public LeavePointsExchangeRecord leavePointsExchangeRecordItem(Integer id) {
		return leavePointsExchangeRecordMapper.leavePointsExchangeRecordItem(id);
	}

	// ??????????????????
	@Override
	public int leavePointsCreat(LeavePoints record) {
		return leavePointsMapper.leavePointsCreat(record);
	}

	// ??????????????????
	@Override
	public int leavePointsUpdate(LeavePoints record) {
		return leavePointsMapper.leavePointsUpdate(record);
	}

	// ????????????????????????
	@Override
	public LeavePoints leavePointsItem(Integer id, String policeId, String year, Integer halfYear) {
		return leavePointsMapper.leavePointsItem(id, policeId, year, halfYear);
	}

	// ????????????????????????
	@Override
	public int leaveOvertimeCreat(LeaveOvertime record) {
		return leaveOvertimeMapper.leaveOvertimeCreat(record);
	}

	// ????????????????????????
	@Override
	public int leaveOvertimeUpdate(LeaveOvertime record) {
		return leaveOvertimeMapper.leaveOvertimeUpdate(record);
	}

	// ????????????????????????
	@Override
	public LeaveOvertime leaveOvertimeItem(Integer id, String policeId, String year) {
		return leaveOvertimeMapper.leaveOvertimeItem(id, policeId, year);
	}

	@Override
	public int addMoreLeaveTrain(List<LeaveTrain> leaveTrain) {
		return leaveTrainMapper.addMoreLeaveTrain(leaveTrain);
	}

	// ????????????????????????
	@Override
	public int leaveDutyCreat(LeaveDuty record) {
		return leaveDutyMapper.leaveDutyCreat(record);
	}

	// ????????????????????????
	@Override
	public int leaveDutyUpdate(LeaveDuty record) {
		return leaveDutyMapper.leaveDutyUpdate(record);
	}

	// ????????????????????????
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

	// ?????????????????????
	@Override
	public int annualLeaveNum(Integer departmentId) {
		return leaveAnnualMapper.annualLeaveNum(departmentId);
	}

	// ????????????????????????
	@Override
	public int notAnnualLeaveNum(Integer departmentId) {
		return leaveAnnualMapper.notAnnualLeaveNum(departmentId);
	}

	// ?????????????????????????????????
	@Override
	public int depAnnualLeaveNum() {
		return leaveAnnualMapper.depAnnualLeaveNum();
	}

	// ?????????????????????????????????
	@Override
	public int totalDepAnnualLeaveNum() {
		return leaveAnnualMapper.totalDepAnnualLeaveNum();
	}

	// ????????????????????????????????????
	@Override
	public int leaveDepTrainStatisticsNum(Integer type, Integer departmentId) {
		return leaveTrainMapper.leaveDepTrainStatisticsNum(type, departmentId);
	}

	// ????????????????????????????????????
	@Override
	public List<CalculationChart> leaveDepPointsExchangeChart(String year, Integer halfYear) {
		return leavePointsMapper.leaveDepPointsExchangeChart(year, halfYear);
	}

	// ??????????????????????????????
	@Override
	public List<CalculationChart> leaveDepCompensatoryChart() {
		return leaveProcessMapper.leaveDepCompensatoryChart();
	}

	// ??????????????????(2???)??????
	@Override
	public List<LeaveLeaderAlarm> leaveLeaderPointsAlarmNewestList(String policeId, String year, Integer halfYear) {
		return leavePointsMapper.leaveLeaderPointsAlarmNewestList(policeId, year, halfYear);
	}

	// ??????????????????????????????
	@Override
	public int leaveLeaderPointsAlarmNewestCount(String policeId, String year, Integer halfYear) {
		return leavePointsMapper.leaveLeaderPointsAlarmNewestCount(policeId, year, halfYear);
	}

	// ??????????????????????????????
	@Override
	public List<LeaveLeaderAlarm> leaveLeaderPointsAlarmList(String policeId, String year, Integer halfYear,
			Integer pageSize, Integer pageNum) {
		return leavePointsMapper.leaveLeaderPointsAlarmList(policeId, year, halfYear, pageSize, pageNum);
	}

	// ????????????(1???)??????
	@Override
	public List<LeaveLeaderAlarm> leaveLeaderOvertimeAlarmNewestList(String policeId) {
		return leaveOvertimeMapper.leaveLeaderOvertimeAlarmNewestList(policeId);
	}

	// ????????????????????????
	@Override
	public int leaveLeaderOvertimeAlarmNewestCount(String policeId) {
		return leaveOvertimeMapper.leaveLeaderOvertimeAlarmNewestCount(policeId);
	}

	// ????????????????????????
	@Override
	public List<LeaveLeaderAlarm> leaveLeaderOvertimeAlarmList(String policeId, Integer pageSize, Integer pageNum) {
		return leaveOvertimeMapper.leaveLeaderOvertimeAlarmList(policeId, pageSize, pageNum);
	}

	// ??????????????????(2???)??????
	@Override
	public List<LeaveCompensatoryAlarm> leaveLeaderCompensatoryAlarmNewestList(String policeId) {
		return leaveCompensatoryAlarmMapper.leaveLeaderCompensatoryAlarmNewestList(policeId);
	}

	// ??????????????????????????????
	@Override
	public int leaveLeaderCompensatoryAlarmNewestCount(String policeId) {
		return leaveCompensatoryAlarmMapper.leaveLeaderCompensatoryAlarmNewestCount(policeId);
	}

	// ??????????????????????????????
	@Override
	public List<LeaveCompensatoryAlarm> leaveLeaderCompensatoryAlarmList(String policeId, Integer pageSize,
			Integer pageNum) {
		return leaveCompensatoryAlarmMapper.leaveLeaderCompensatoryAlarmList(policeId, pageSize, pageNum);
	}

	// ??????????????????(2???)
	@Override
	public List<LeaveCompensatoryRecord> leaveLeaderNeedDealtNewestList(String policeId) {
		return leaveCompensatoryRecordMapper.leaveLeaderNeedDealtNewestList(policeId);
	}

	// ????????????????????????
	@Override
	public int leaveLeaderNeedDealtNewestCount(String policeId) {
		return leaveCompensatoryRecordMapper.leaveLeaderNeedDealtNewestCount(policeId);
	}

	// ????????????????????????
	@Override
	public List<LeaveCompensatoryRecord> leaveLeaderNeedDealtList(String policeId, Integer type, String dateTime,
			Integer pageSize, Integer pageNum) {
		return leaveCompensatoryRecordMapper.leaveLeaderNeedDealtList(policeId, type, dateTime, pageSize, pageNum);
	}

	// ????????????????????????
	@Override
	public int leaveLeaderNeedDealtCount(String policeId, Integer type, String dateTime) {
		return leaveCompensatoryRecordMapper.leaveLeaderNeedDealtCount(policeId, type, dateTime);
	}

	// ????????????????????????
	@Override
	public List<LeaveCompensatoryRecord> leaveLeaderOverDealtList(String policeId, Integer type, String dateTime,
			Integer pageSize, Integer pageNum) {
		return leaveCompensatoryRecordMapper.leaveLeaderOverDealtList(policeId, type, dateTime, pageSize, pageNum);
	}

	// ????????????????????????
	@Override
	public int leaveLeaderOverDealtCount(String policeId, Integer type, String dateTime) {
		return leaveCompensatoryRecordMapper.leaveLeaderOverDealtCount(policeId, type, dateTime);
	}

	// ????????????????????????(??????????????????????????????????????????)
	@Override
	public List<LeaveCompensatoryRecord> leaveCompensatoryRecordStatusList() {
		return leaveCompensatoryRecordMapper.leaveCompensatoryRecordStatusList();
	}

	// ?????????????????????????????????
	@Override
	public LeavePower leavePowerItem(String policeId) {
		return leavePowerMapper.leavePowerItem(policeId);
	}

	// ????????????????????????
	@Override
	public LeavePersonalStatistics leavePersonalOvertimeItem(String policeId) {
		return leaveProcessMapper.leavePersonalOvertimeItem(policeId);
	}

	// ??????????????????
	@Override
	public LeavePersonalStatistics leavePersonalPointsItems(String policeId, String year, Integer halfYear) {
		return leaveProcessMapper.leavePersonalPointsItems(policeId, year, halfYear);
	}

	// ????????????policeId,year,halfYear????????????
	@Override
	public int leavePointsResidualUpdate(LeavePoints record) {
		return leavePointsMapper.leavePointsResidualUpdate(record);
	}

	// ??????????????????????????????
	@Override
	public int leaveOvertimeYearUpdate(LeaveOvertime record) {
		return leaveOvertimeMapper.leaveOvertimeYearUpdate(record);
	}

	// ??????????????????????????????????????????
	@Override
	public List<LeaveCompensatoryAlarm> quarterPoliceList(String startTime, String endTime) {
		return leaveCompensatoryAlarmMapper.quarterPoliceList(startTime, endTime);
	}

	// ????????????????????????
	@Override
	public int leaveCompensatoryAlarmCreat(LeaveCompensatoryAlarm record) {
		return leaveCompensatoryAlarmMapper.leaveCompensatoryAlarmCreat(record);
	}

	// ????????????????????????
	@Override
	public int leaveCompensatoryAlarmUpdate(LeaveCompensatoryAlarm record) {
		return leaveCompensatoryAlarmMapper.leaveCompensatoryAlarmUpdate(record);
	}

	// ????????????,????????????,????????????????????????
	@Override
	public LeaveCompensatoryAlarm quarterPoliceItem(Integer type, Integer ruleId, String policeId, String startTime,
			String endTime) {
		return leaveCompensatoryAlarmMapper.quarterPoliceItem(type, ruleId, policeId, startTime, endTime);
	}

	// ???????????????????????????????????????????????????
	@Override
	public List<LeaveTrain> leaveDepTrainStatisticsList(Integer type, String policeId) {
		return leaveTrainMapper.leaveDepTrainStatisticsList(type, policeId);
	}

	// ??????????????????????????????????????????
	@Override
	public List<LeaveCompensatoryAlarm> monthPoliceList(String startTime, String endTime) {
		return leaveCompensatoryAlarmMapper.monthPoliceList(startTime, endTime);
	}

	// ??????????????????????????????
	@Override
	public List<LeaveCompensatoryAlarm> singlePoliceList() {
		return leaveCompensatoryAlarmMapper.singlePoliceList();
	}

	// ?????????????????????
	@Override
	public int leaveProcessAlarmItemUpdate(LeaveProcess lProcess) {
		return leaveProcessMapper.leaveProcessAlarmItemUpdate(lProcess);
	}

	@Override
	public Integer nowMonthHave(String policeId, Integer policeMonth) {
		return leaveIntegralManageMapper.nowMonthHave(policeId, policeMonth);
	}

	// ???????????????????????????
	@Override
	public List<LeaveCompensatoryAlarm> notRecordList() {
		return leaveCompensatoryAlarmMapper.notRecordList();
	}

	// ????????????????????????
	@Override
	public LeaveRestAlarmRule getLeaveRestAlarmRuleItem(Integer id) {
		return leaveRestAlarmRuleMapper.getLeaveRestAlarmRuleItem(id);
	}

	// ????????????????????????
	@Override
	public LeaveCompensatoryAlarm continuePoliceItem(Integer type, String policeId) {
		return leaveCompensatoryAlarmMapper.continuePoliceItem(type, policeId);
	}

	// ?????????????????????????????????
	@Override
	public LeaveCompensatoryAlarm notRecordPoliceItem(String policeId) {
		return leaveCompensatoryAlarmMapper.notRecordPoliceItem(policeId);
	}

	// ????????????????????????????????????
	@Override
	public int leaveCompensatoryAlarmTimeUpdate(LeaveCompensatoryAlarm record) {
		return leaveCompensatoryAlarmMapper.leaveCompensatoryAlarmTimeUpdate(record);
	}

	// ?????????????????????????????????
	@Override
	public int leaveCompensatoryAlarmAllUpdate(LeaveCompensatoryAlarm record) {
		return leaveCompensatoryAlarmMapper.leaveCompensatoryAlarmAllUpdate(record);
	}

	// ??????????????????????????????
	@Override
	public List<LeaveCompensatoryAlarm> alarmComRecordList() {
		return leaveCompensatoryAlarmMapper.alarmComRecordList();
	}

	// ?????????????????????????????????
	@Override
	public LeavePersonalStatistics leavePersonalOverTimeChangeDays(String policeId, String currentYearStr) {
		return leaveProcessMapper.leavePersonalOverTimeChangeDays(policeId, currentYearStr);
	}

	// ?????????????????????????????????
	@Override
	public LeavePersonalStatistics leavePersonalpointsChangeDays(String policeId, String currentYearStr,
			Integer halfYear) {
		return leaveProcessMapper.leavePersonalpointsChangeDays(policeId, currentYearStr, halfYear);
	}

	// ???????????????????????????
	@Override
	public List<LeaveChart> leaveDepAnnualNotStandardStatistics() {
		return leaveProcessMapper.leaveDepAnnualNotStandardStatistics();
	}

	// ????????????????????????
	@Override
	public List<LeaveProcess> leaveLeaderOvertimeTaskList() {
		return leaveProcessMapper.leaveLeaderOvertimeTaskList();
	}

	// ????????????????????????
	@Override
	public List<LeaveProcess> leaveLeaderPointTaskList() {
		return leaveProcessMapper.leaveLeaderPointTaskList();
	}

	// ????????????????????????
	@Override
	public int leavePowerObjectDelete(Integer id) {
		return leavePowerObjectMapper.leavePowerObjectDelete(id);
	}

	// ??????????????????
	@Override
	public int leavePowerObjectCreat(LeavePowerObject record) {
		return leavePowerObjectMapper.leavePowerObjectCreat(record);
	}

	// ????????????????????????
	@Override
	public int leavePowerObjectUpdate(LeavePowerObject record) {
		return leavePowerObjectMapper.leavePowerObjectUpdate(record);
	}

	// ?????????????????????????????????
	@Override
	public List<LeaveCompensatoryReadRecord> leaveLeaderCompensatoryRecordNewestList(String policeId,
			Integer approvedReadStatus) {
		return leaveCompensatoryReadRecordMapper.leaveLeaderCompensatoryRecordNewestList(policeId, approvedReadStatus);
	}

	// ?????????????????????????????????
	@Override
	public List<LeaveCompensatoryReadRecord> leaveLeaderCompensatoryRecordList(String policeId, Integer type,
			String dateTime, Integer approvedReadStatus, Integer pageSize, Integer pageNum) {
		return leaveCompensatoryReadRecordMapper.leaveLeaderCompensatoryRecordList(policeId, type, dateTime,
				approvedReadStatus, pageSize, pageNum);
	}

	// ???????????????????????????????????????
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

	// ???????????????????????????
	@Override
	public LeaveThisYearStatistics leaveThisYearStatisticsItem(String policeId) {
		return leaveProcessMapper.leaveThisYearStatisticsItem(policeId);
	}

	// ???????????????????????????
	@Override
	public int leaveReadUpdate(LeaveCompensatoryRecord record) {
		return leaveCompensatoryRecordMapper.leaveReadUpdate(record);
	}

	// ???????????????????????????
	@Override
	public int leaveApprovedReadUpdate(LeaveCompensatoryRecord record) {
		return leaveCompensatoryRecordMapper.leaveApprovedReadUpdate(record);
	}

	// ??????????????????????????????????????????
	@Override
	public LeaveCompensatoryAlarm leaveLeaderCompensatoryAlarmItem(Integer id) {
		return leaveCompensatoryAlarmMapper.leaveLeaderCompensatoryAlarmItem(id);
	}

	// ?????????????????????????????????
	@Override
	public int leaveCompensatoryReadRecordCreat(LeaveCompensatoryReadRecord record) {
		return leaveCompensatoryReadRecordMapper.leaveCompensatoryReadRecordCreat(record);
	}

	// ?????????????????????????????????
	@Override
	public int leaveCompensatoryReadRecordUpdate(LeaveCompensatoryReadRecord record) {
		return leaveCompensatoryReadRecordMapper.leaveCompensatoryReadRecordUpdate(record);
	}

	// ??????????????????????????????
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

/**
 * 
 */
package com.bayee.political.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
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

/**
 * @author seanguo
 *
 */
@Service
public interface LeaveProcessService {

	void saveOrUpdate(LeaveProcess leaveProcess);

	void save(LeaveProcess leaveProcess);

	// 请假记录修改
	int leaveProcessUpdate(LeaveProcess leaveProcess);

	LeaveProcess findById(long id);

	LeaveProcess findByBusinessId(String businessId);

	List<LeaveProcess> findByUserId(String userId);

	List<LeaveProcess> findAll();

	int countAnnualLeveByUser(String year);

	List<DepartmentAnnualLeaveRatio> countAnnualLeaveRatioByDepartment(String year);

	List<MonthlyLeaveTotal> countMonthlyLeaveByYear(String year);

	List<LeaveTypeCount> countLeaveByTypes(String year);

	int countUserTotalLeaveCount(String userId, String year);

	int countAnnualLeaveLeftCount(String userId, String year);

	int countAnnualLeaveUsedTimes(String userId, String year);

	List<LeaveTypeCount> countUserLeaveByTypes(String userId, String year);

	int countTotalLeave(String year);

	// 每月请假类型占比查询（api）
	List<CalculationChart> leaveTypeList(Integer departmentId);

	// 个人休假情况统计（api）
	LeavePersonalStatistics leavePersonalStatisticsItem(String policeId, String year, Integer halfYear);

	// 个人请假原因占比情况（api）
	List<CalculationChart> leaveReasonAnalysisList(String policeId);

	// 个人最近请假查询列表（api）
	List<LeaveProcess> leavePersonalList(String policeId, String leaveType, Integer identification);

	// 每月请假总人数查询（api）
	List<CalculationChart> leavetotalNumList(Integer departmentId);

	// 每月休年假人数查询（api）
	List<CalculationChart> leaveAnnualNumList(Integer departmentId);

	// 每月调休人数查询（api）
	List<CalculationChart> leaveOffNumList(Integer departmentId);

	// 每月请假人数查询（api）
	List<CalculationChart> leaveNumList(Integer departmentId);

	// 各部门年假使用情况查询（api）
	List<LeaveChart> leaveDepAnnualChartList();

	// 各部门平均年假使用率查询（api）
	int leaveDepAverageItem(Integer departmentId);

	// 休假中民警数量（api）
	int onHolidayNumItem(Integer departmentId);

	// 请假中民警数量（api）
	int askingForLeaveNumItem(Integer departmentId);

	// 警员年假新增
	int leaveAnnualCreat(LeaveAnnual leaveAnnual);

	// 警员年假修改
	int leaveAnnualUpdate(LeaveAnnual leaveAnnual);

	// 警员年假详情查询
	LeaveAnnual leaveAnnualItem(Integer id, String policeId, String year);

	// 警员年假列表查询
	List<LeaveAnnual> leaveAnnualList(String policeId, String year);

	// 警员年假天数列表查询
	List<LeaveAnnual> policeLeaveAnnualExistList();

	/**
	 * 查询全部或按条件查询调休管理
	 * 
	 * @return
	 */
	List<LeaveRestManage> getLeaveRestManageList(@Param("departmentId") Integer departmentId,
			@Param("positionId") Integer positionId, @Param("keyword") String keyword,
			@Param("pageSize") Integer pageSize);

	/**
	 * 查询全部或按条件查询调休管理总数量
	 * 
	 * @return
	 */
	int getLeaveRestManageListCount(@Param("departmentId") Integer departmentId,
			@Param("positionId") Integer positionId, @Param("keyword") String keyword);

	/**
	 * 根据id查询一条或按条件查询调休管理
	 * 
	 * @return
	 */
	LeaveRestManage getLeaveRestManageOne(@Param("id") Integer id);

	/**
	 * 查询全部的积分管理数据或根据条件查询积分管理数据
	 * 
	 * @return
	 */
	List<LeaveIntegralManage> getLeaveIntegralManageList(@Param("policeMonth") Integer policeMonth,
			@Param("departmentId") Integer departmentId, @Param("keyword") String keyword,
			@Param("pageSize") Integer pageSize);

	/**
	 * 查询全部的积分管理数量或根据条件查询积分管理数量
	 * 
	 * @return
	 */
	int getLeaveIntegralManageCount(@Param("policeMonth") Integer policeMonth,
			@Param("departmentId") Integer departmentId, @Param("keyword") String keyword);

	/**
	 * 新增积分
	 * 
	 * @return
	 */
	Integer addLeaveIntegralManage(@Param("leaveIntegralManage") LeaveIntegralManage leaveIntegralManage);

	/**
	 * 查询积分审核权限或根据条件查询
	 * 
	 * @return
	 */
	List<LeavePower> getLeaveIntegralAuditPowerList(@Param("departmentId") Integer departmentId,
			@Param("keyword") String keyword, @Param("pageSize") Integer pageSize);

	/**
	 * 查询积分审核权限数量或根据条件查询数量
	 * 
	 * @return
	 */
	int getLeaveIntegralAuditPowerCount(@Param("departmentId") Integer departmentId, @Param("keyword") String keyword);

	/**
	 * 根据id删除积分管理
	 * 
	 * @param id
	 * @return
	 */
	int deleteLeaveIntegralManageById(Integer id);

	/**
	 * 根据id获得积分管理
	 * 
	 * @param id
	 * @return
	 */
	LeaveIntegralManage getLeaveIntegralManageOne(@Param("id") Integer id);

	/**
	 * 根据id修改积分管理
	 * 
	 * @param record
	 * @return
	 */
	int updateLeaveIntegralManageById(LeaveIntegralManage leaveIntegralManage);

	/**
	 * 添加积分审核权限
	 * 
	 * @return
	 */
	int addLeaveIntegralAuditPower(@Param("leaveIntegralAuditPower") LeavePower leaveIntegralAuditPower);

	/**
	 * 修改积分审核权限
	 * 
	 * @param record
	 * @return
	 */
	int updateLeaveIntegralAuditPower(LeavePower record);

	/**
	 * 根据id删除积分审核权限
	 * 
	 * @param id
	 * @return
	 */
	int deleteLeaveIntegralAuditPower(Integer id);

	/**
	 * 根据id查询积分审核权限
	 * 
	 * @param id
	 * @return
	 */
	LeavePower getLeaveIntegralAuditPowerById(@Param("id") Integer id);

	/**
	 * 查询所有疗休养数据或根据条件
	 * 
	 * @return
	 */
	List<LeaveTrain> getLeaveTrainList(@Param("type") Integer type, @Param("departmentId") Integer departmentId,
			@Param("keyWord") String keyWord, @Param("pageSize") Integer pageSize);

	/**
	 * 查询所有疗休养数据或根据条件
	 * 
	 * @return
	 */
	int getLeaveTrainListCount(@Param("type") Integer type, @Param("departmentId") Integer departmentId,
			@Param("keyWord") String keyWord);

	/**
	 * 添加疗休养数据
	 * 
	 * @param record
	 * @return
	 */
	int insertLeaveTrainList(LeaveTrain leaveTrain);

	/**
	 * 根据id删除辽修养
	 * 
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 根据id获得疗修养数据
	 * 
	 * @param id
	 * @return
	 */
	LeaveTrain getLeaveTrainOne(@Param("id") Integer id);

	/**
	 * 根据id修改辽修养
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(LeaveTrain leaveTrain);

	/**
	 * 获得加班规则数据
	 * 
	 * @return
	 */
	List<LeaveOvertimeRule> getLeaveOvertimeRuleList();

	/**
	 * 根据id修改加班规则
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(LeaveOvertimeRule leaveOvertimeRule);

	/**
	 * 获得积分兑换规则数据
	 * 
	 * @return
	 */
	List<LeaveIntegralExchangeRule> getLeaveIntegralExchangeRuleList();

	/**
	 * 修改积分兑换规则数据
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(LeaveIntegralExchangeRule leaveIntegralExchangeRule);

	/**
	 * 获得调修预警规则
	 * 
	 * @return
	 */
	List<LeaveRestAlarmRule> getLeaveRestAlarmRuleList();

	// 获得调修预警规则
	LeaveRestAlarmRule getLeaveRestAlarmRuleItem(Integer id);

	/**
	 * 根据id修改调休预警规则
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(LeaveRestAlarmRule leaveRestAlarmRule);

	/**
	 * 查询积分管理中所有的被记分人id
	 * 
	 * @return
	 */
	List<String> getScoredPoliceIds();

	/**
	 * 根据审核权限id及审核对象id查询在审核权限中是否存在
	 * 
	 * @return
	 */
	int checkIsExist(@Param("policeId") String policeId, @Param("id") Integer id);

	/**
	 * 根据兑换单位id及审核人id查询是否已创建审核权限
	 * 
	 * @return
	 */
	int isExist(@Param("departmentId") Integer departmentId, @Param("policeId") String policeId,
			@Param("moduleId") Integer moduleId);

	/**
	 * 批量插入疗休养数据
	 * 
	 * @return
	 */
	int addMoreLeaveTrain(@Param("leaveTrain") List<LeaveTrain> leaveTrain);

	/**
	 * 批量新增积分
	 * 
	 * @return
	 */
	int addMoreLeaveIntegralManage(@Param("leaveIntegralManageList") List<LeaveIntegralManage> leaveIntegralManageList);

	/**
	 * 批量新增记分
	 * 
	 * @param leavePointsList
	 * @return
	 */
	int addMoreleavePoints(@Param("leavePointsList") List<LeavePoints> leavePointsList);

	/**
	 * 根据powerId删除
	 * 
	 * @param powerId
	 * @return
	 */
	int leavePowerObjectDeleteByPowerId(Integer powerId);

	/**
	 * 根据power_id获得police_object_ids 以"-"号隔开
	 * 
	 * @param powerId
	 * @return
	 */
	String getPoliceObjectIdsByPowerId(Integer powerId);

	// 个人最新调休记录查询
	List<LeaveCompensatoryRecord> leaveCompensatoryRecordNewestList(String policeId, Integer readStatus);

	// 调休记录新增
	int leaveCompensatoryRecordCreat(LeaveCompensatoryRecord leaveCompensatoryRecord);

	// 调休记录修改
	int leaveCompensatoryRecordUpdate(LeaveCompensatoryRecord leaveCompensatoryRecord);

	// 个人调休记录详情查询
	LeaveCompensatoryRecord leaveCompensatoryRecordItem(Integer id);

	// 个人调休记录查询
	List<LeaveCompensatoryRecord> leaveCompensatoryRecordList(String policeId, Integer type, String dateTime,
			Integer readStatus, Integer pageSize, Integer pageNum);

	// 个人调休记录总数统计
	int leaveCompensatoryRecordCount(String policeId, Integer type, String dateTime, Integer readStatus);

	// 积分兑换记录新增
	int leavePointsExchangeRecordCreat(LeavePointsExchangeRecord record);

	// 积分兑换记录修改
	int leavePointsExchangeRecordUpdate(LeavePointsExchangeRecord record);

	// 个人最新积分兑换记录查询
	List<LeavePointsExchangeRecord> leavePointsExchangeRecordNewestList(String policeId);

	// 个人积分兑换记录查询
	List<LeavePointsExchangeRecord> leavePointsExchangeRecordList(String policeId, String dateTime, Integer pageSize,
			Integer pageNum);

	// 个人积分兑换记录总数统计
	int leavePointsExchangeRecordCount(String policeId, String dateTime);

	// 个人积分兑换记录详情查询
	LeavePointsExchangeRecord leavePointsExchangeRecordItem(Integer id);

	// 警员积分新增
	int leavePointsCreat(LeavePoints record);

	// 警员积分修改
	int leavePointsUpdate(LeavePoints record);

	// 警员积分详情查询
	LeavePoints leavePointsItem(Integer id, String policeId, String year, Integer halfYear);

	// 警员加班时长新增
	int leaveOvertimeCreat(LeaveOvertime record);

	// 警员加班时长修改
	int leaveOvertimeUpdate(LeaveOvertime record);

	// 警员加班时长详情
	LeaveOvertime leaveOvertimeItem(Integer id, String policeId, String year);

	// 警员值班时长新增
	int leaveDutyCreat(LeaveDuty record);

	// 警员值班时长修改
	int leaveDutyUpdate(LeaveDuty record);

	// 警员值班时长详情
	LeaveDuty leaveDutyItem(Integer id, String policeId, String year, String month);

	// 年休假人数统计
	int annualLeaveNum(Integer departmentId);

	// 未休年假人数统计
	int notAnnualLeaveNum(Integer departmentId);

	// 单位年休假达标数量统计
	int depAnnualLeaveNum();

	// 有年休假的单位数量统计
	int totalDepAnnualLeaveNum();

	// 各单位疗休养人员数量统计
	int leaveDepTrainStatisticsNum(Integer type, Integer departmentId);

	// 各单位积分未兑换人数查询
	List<CalculationChart> leaveDepPointsExchangeChart(String year, Integer halfYear);

	// 各单位需调休人数查询
	List<CalculationChart> leaveDepCompensatoryChart();

	// 办案积分预警(2条)查询
	List<LeaveLeaderAlarm> leaveLeaderPointsAlarmNewestList(String policeId, String year, Integer halfYear);

	// 办案积分预警总数统计
	int leaveLeaderPointsAlarmNewestCount(String policeId, String year, Integer halfYear);

	// 办案积分预警分页查询
	List<LeaveLeaderAlarm> leaveLeaderPointsAlarmList(String policeId, String year, Integer halfYear, Integer pageSize,
			Integer pageNum);

	// 加班预警(1条)查询
	List<LeaveLeaderAlarm> leaveLeaderOvertimeAlarmNewestList(String policeId);

	// 加班总数统计
	int leaveLeaderOvertimeAlarmNewestCount(String policeId);

	// 加班预警分页查询
	List<LeaveLeaderAlarm> leaveLeaderOvertimeAlarmList(String policeId, Integer pageSize, Integer pageNum);

	// 警员调休预警(2条)查询
	List<LeaveCompensatoryAlarm> leaveLeaderCompensatoryAlarmNewestList(String policeId);

	// 警员调休预警总数统计
	int leaveLeaderCompensatoryAlarmNewestCount(String policeId);

	// 警员调休预警分页查询
	List<LeaveCompensatoryAlarm> leaveLeaderCompensatoryAlarmList(String policeId, Integer pageSize, Integer pageNum);

	// 待办事项查询(2条)
	List<LeaveCompensatoryRecord> leaveLeaderNeedDealtNewestList(String policeId);

	// 完成事项总数统计
	int leaveLeaderNeedDealtNewestCount(String policeId);

	// 待办事项分页查询
	List<LeaveCompensatoryRecord> leaveLeaderNeedDealtList(String policeId, Integer type, String dateTime,
			Integer pageSize, Integer pageNum);

	// 待办事项总数统计
	int leaveLeaderNeedDealtCount(String policeId, Integer type, String dateTime);

	// 已办事项分页查询
	List<LeaveCompensatoryRecord> leaveLeaderOverDealtList(String policeId, Integer type, String dateTime,
			Integer pageSize, Integer pageNum);

	// 已办事项总数统计
	int leaveLeaderOverDealtCount(String policeId, Integer type, String dateTime);

	// 调休记录列表查询(定时任务修改调休记录状态进程)
	List<LeaveCompensatoryRecord> leaveCompensatoryRecordStatusList();

	// 根据当前用户查询审核人
	LeavePower leavePowerItem(String policeId);

	// 个人加班时长查询
	LeavePersonalStatistics leavePersonalOvertimeItem(String policeId);

	// 个人积分查询
	LeavePersonalStatistics leavePersonalPointsItems(String policeId, String year, Integer halfYear);

	// 警员根据policeId,year,halfYear修改积分
	int leavePointsResidualUpdate(LeavePoints record);

	// 根据年份和警员号修改
	int leaveOvertimeYearUpdate(LeaveOvertime record);

	// 根据季度查询民警累计出差天数
	List<LeaveCompensatoryAlarm> quarterPoliceList(String startTime, String endTime);

	// 警员调休预警新增
	int leaveCompensatoryAlarmCreat(LeaveCompensatoryAlarm record);

	// 警员调休预警修改
	int leaveCompensatoryAlarmUpdate(LeaveCompensatoryAlarm record);

	// 警员调休预警连续加班修改
	int leaveCompensatoryAlarmTimeUpdate(LeaveCompensatoryAlarm record);

	// 警员调休预警不打卡修改
	int leaveCompensatoryAlarmAllUpdate(LeaveCompensatoryAlarm record);

	// 根据季度,创建时间,警号查询季度预警
	LeaveCompensatoryAlarm quarterPoliceItem(Integer type, Integer ruleId, String policeId, String startTime,
			String endTime);

	// 中层领导查看自己部门疗休养人员数据
	List<LeaveTrain> leaveDepTrainStatisticsList(Integer type, String policeId);

	// 根据每月查询民警累计出差天数
	List<LeaveCompensatoryAlarm> monthPoliceList(String startTime, String endTime);

	// 查询单次民警出差天数
	List<LeaveCompensatoryAlarm> singlePoliceList();

	// 修改请假表状态
	int leaveProcessAlarmItemUpdate(LeaveProcess lProcess);

	/**
	 * 判断当前月是否已添加过数据
	 * 
	 * @return
	 */
	Integer nowMonthHave(@Param("policeId") String policeId, @Param("policeMonth") Integer policeMonth);

	// 查询连续未打卡天数
	List<LeaveCompensatoryAlarm> notRecordList();

	// 查询连续工作预警
	LeaveCompensatoryAlarm continuePoliceItem(Integer type, String policeId);

	// 查询个人连续未打卡天数
	LeaveCompensatoryAlarm notRecordPoliceItem(String policeId);

	// 查询预警连续加班人员
	List<LeaveCompensatoryAlarm> alarmComRecordList();

	// 个人加班可调休天数查询
	LeavePersonalStatistics leavePersonalOverTimeChangeDays(String policeId, String currentYearStr);

	// 个人积分可调休天数查询
	LeavePersonalStatistics leavePersonalpointsChangeDays(String policeId, String currentYearStr, Integer halfYear);

	// 各单位年休假率查询
	List<LeaveChart> leaveDepAnnualNotStandardStatistics();

	// 加班调休列表查询
	List<LeaveProcess> leaveLeaderOvertimeTaskList();

	// 积分调休列表查询
	List<LeaveProcess> leaveLeaderPointTaskList();

	// 审核对象人员删除
	int leavePowerObjectDelete(Integer id);

	// 审核对象新增
	int leavePowerObjectCreat(LeavePowerObject record);

	// 审核对象人员修改
	int leavePowerObjectUpdate(LeavePowerObject record);

	// 局领导最新调休提醒查询
	List<LeaveCompensatoryReadRecord> leaveLeaderCompensatoryRecordNewestList(String policeId,
			Integer approvedReadStatus);

	// 局领导调休提醒分页查询
	List<LeaveCompensatoryReadRecord> leaveLeaderCompensatoryRecordList(String policeId, Integer type, String dateTime,
			Integer approvedReadStatus, Integer pageSize, Integer pageNum);

	// 局领导调休提醒记录总数统计
	int leaveLeaderCompensatoryRecordCount(String policeId, Integer type, String dateTime, Integer approvedReadStatus);

	// 个人当前年情况统计
	LeaveThisYearStatistics leaveThisYearStatisticsItem(String policeId);

	// 调休人读取状态修改
	int leaveReadUpdate(LeaveCompensatoryRecord record);

	// 审批人读取状态修改
	int leaveApprovedReadUpdate(LeaveCompensatoryRecord record);

	// 局领导需强制调休人员详情查询
	LeaveCompensatoryAlarm leaveLeaderCompensatoryAlarmItem(Integer id);

	// 局领导调休提醒记录新增
	int leaveCompensatoryReadRecordCreat(LeaveCompensatoryReadRecord record);

	// 局领导调休提醒记录修改
	int leaveCompensatoryReadRecordUpdate(LeaveCompensatoryReadRecord record);

	// 查询当前民警所属领导
	List<LeavePower> LeavePowerPoliceList(String policeId);
	
	/**
	 * 本周休假人数
	 * @return
	 */
	Integer leaveNum();
	
	/**
	 * 本周休年假人数
	 * @return
	 */
	Integer leaveYearNum();
	
	/**
	 * 本周强制调休人数
	 * @return
	 */
	Integer leaveAdjustNum();
	
	/**
	 * 年假已使用占比
	 * @return
	 */
	Double leaveYearUsedRator();
	
	/**
	 * 本周个人加班前5
	 * @return
	 */
	List<LeaveOvertime> leaveOverTimeTopFiveWeek();
	
	/**
	 * 获得调休记录
	 * @return
	 */
	List<LeaveCompensatoryRecord> getLeaveCompensatoryRecord(@Param("departmentId") Integer departmentId,
			@Param("positionId") Integer positionId, @Param("keyword") String keyword,
			@Param("pageSize") Integer pageSize);
	
	/**
	 * 获得调休记录数量
	 * @return
	 */
	Integer getLeaveCompensatoryRecordCount(@Param("departmentId") Integer departmentId,
			@Param("positionId") Integer positionId, @Param("keyword") String keyword);
	
	/**
	 * 根据id查询调休详情
	 * @param id
	 * @return
	 */
	LeaveCompensatoryRecord getLeaveCompensatoryRecordOne(@Param("id") Integer id);
	
}

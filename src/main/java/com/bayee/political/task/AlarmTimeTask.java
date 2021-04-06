package com.bayee.political.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bayee.political.controller.BaseController;
import com.bayee.political.domain.AlarmTalk;
import com.bayee.political.domain.ClockRecord;
import com.bayee.political.domain.LeaveAnnual;
import com.bayee.political.domain.LeaveCompensatoryAlarm;
import com.bayee.political.domain.LeaveCompensatoryRecord;
import com.bayee.political.domain.LeaveDuty;
import com.bayee.political.domain.LeaveIntegralExchangeRule;
import com.bayee.political.domain.LeaveOvertime;
import com.bayee.political.domain.LeaveOvertimeRule;
import com.bayee.political.domain.LeavePersonalStatistics;
import com.bayee.political.domain.LeavePoints;
import com.bayee.political.domain.LeaveProcess;
import com.bayee.political.domain.LeaveRestAlarmRule;
import com.bayee.political.domain.ReportDataFillTime;
import com.bayee.political.domain.User;
import com.bayee.political.service.AlarmService;
import com.bayee.political.service.ClockService;
import com.bayee.political.service.EvaluationService;
import com.bayee.political.service.LeaveProcessService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
import com.bayee.political.utils.StatusCode;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiAttendanceListRecordRequest;
import com.dingtalk.api.response.OapiAttendanceListRecordResponse;
import com.dingtalk.api.response.OapiAttendanceListRecordResponse.Recordresult;
import com.taobao.api.ApiException;

/**
 * @author shentuqiwei
 * @version 2020年4月10日 下午4:07:18
 */
@Component
@EnableScheduling
@Controller
public class AlarmTimeTask extends BaseController {

	@Autowired
	EvaluationService evaluationService;

	@Autowired
	AlarmService alarmService;

	@Autowired
	private LeaveProcessService leaveProcessService;

	@Autowired
	private ClockService clockService;

	@Autowired
	private UserService userService;

	@Autowired
	private TaskScheduler scheduler;

	public static final Map<Object, ScheduledFuture<?>> scheduledTasks = new IdentityHashMap<>();
	public static final String STARTJOB = "taskJob";

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
	Date date = new Date();
	String dateTime = sdf.format(date);

	String currentYearStr = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));

	// 约谈状态修改任务
	@Scheduled(cron = "0 0/1 * * * ?") // 每分钟
	public void alarmTalkStatusTask() throws ParseException {
//		System.out.println("预警开始: " + DateUtils.formatDate(new Date(), "HH:mm:ss"));
		// 约谈列表查询（定时任务修改约谈状态进程）
		List<AlarmTalk> statusList = alarmService.alarmTalkStatusList();
		for (int i = 0; i < statusList.size(); i++) {
			long startTime = statusList.get(i).getStartTime().getTime();
			long endTime = statusList.get(i).getEndTime().getTime();
			long currentTime = new Date().getTime();
			AlarmTalk item = new AlarmTalk();
			item.setId(statusList.get(i).getId());
			if (statusList.get(i).getIsReceive() == 0) {
				if (currentTime > endTime) {
					item.setStatus(6);
					item.setUpdateDate(new Date());
					alarmService.alarmTalkUpdate(item);
				}
			} else if (statusList.get(i).getIsReceive() == 2) {
				if (startTime < currentTime && currentTime < endTime) {
					item.setStatus(3);
					item.setUpdateDate(new Date());
					alarmService.alarmTalkUpdate(item);
				}
			}
		}
//		System.out.println("预警结束: " + DateUtils.formatDate(new Date(), "HH:mm:ss"));
	}

	// 钉钉打卡记录保存(自动定时抓取钉钉请假数据，每天6点10分30秒触发任务)
//	@Scheduled(cron = "30 10 6 * * ?") // 每天6点10分30秒触发任务
	@Scheduled(cron = "0 0/20 * * * ?") // 每分钟
	public void clockRecordSave() throws ApiException {
		// 查询全部警员数据
		List<User> userList = userService.userAllList();
		List<String> srtUserList = new ArrayList<String>();
		for (int i = 0; i < userList.size(); i++) {
			srtUserList.add(userList.get(i).getDdUserId());
		}
		long count = 0L;
		String accessToken = getAccessToken();
		for (int i = 0; i < srtUserList.size(); i++) {
			DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/attendance/listRecord");
			OapiAttendanceListRecordRequest request = new OapiAttendanceListRecordRequest();
			Date dNow = new Date(); // 当前时间
			Date dBefore = new Date();
			Calendar calendar = Calendar.getInstance(); // 得到日历
			calendar.setTime(dNow);// 把当前时间赋给日历
			calendar.add(Calendar.DAY_OF_MONTH, -1); // 设置为前一天
			dBefore = calendar.getTime(); // 得到前一天的时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
//			String defaultStartDate = sdf.format(dBefore); // 格式化前一天
			String defaultEndDate = sdf.format(dNow); // 格式化当前时间
			request.setCheckDateFrom(defaultEndDate + " 00:00:00");
			request.setCheckDateTo(defaultEndDate + " 23:59:59");
			request.setUserIds(Arrays.asList(srtUserList.get(i)));
			OapiAttendanceListRecordResponse response = client.execute(request, accessToken);
//			System.out.println("请求结果:" + response.getRecordresult());
			List<Recordresult> recordresultList = response.getRecordresult();
			if (recordresultList != null) {
//				System.out.println("成功进入");
				for (Recordresult recordresult : recordresultList) {
					ClockRecord clock = new ClockRecord();
					clock.setUserId(recordresult.getUserId());
					clock.setCheckType(recordresult.getCheckType());
					clock.setGroupId(recordresult.getGroupId());
					clock.setPlanId(recordresult.getPlanId());
					clock.setWorkDate(recordresult.getWorkDate());
					clock.setTimeResult(recordresult.getTimeResult());
					clock.setLocationResult(recordresult.getLocationResult());
					clock.setApproveId(recordresult.getApproveId());
					clock.setProcinstId(recordresult.getProcInstId());
					clock.setBaseCheckTime(recordresult.getBaseCheckTime());
					clock.setIdentification(1);
					clock.setUserCheckTime(recordresult.getUserCheckTime());
					clock.setCreationDate(new Date());
					ClockRecord rItem = clockService.clockRecordItem(recordresult.getUserId(),
							recordresult.getCheckType(), recordresult.getBaseCheckTime(),
							recordresult.getUserCheckTime());
					if (rItem == null) {
						if (recordresult.getCheckType() != null) {
							clockService.clockRecordCreate(clock);
						}
					}
					count = count + 1;
				}
			}

		}
//		System.out.println("打卡结束: " + DateUtils.formatDate(new Date(), "HH:mm:ss"));
	}

	// 加班时长保存
	@Scheduled(cron = "0 0/1 * * * ?") // 每分钟
	public void overTimeSave() throws ApiException {
		// 警员打卡加班数据查询
		List<ClockRecord> list = clockService.overTimeClockRecordList();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				User user = userService.policeItem(null, null, list.get(i).getUserId());
				if (user != null) {
					LeaveOvertime item = new LeaveOvertime();
					LeaveOvertime timeItem = leaveProcessService.leaveOvertimeItem(null, user.getPoliceId(),
							currentYearStr);
					if (timeItem == null) {
						item.setPoliceId(user.getPoliceId());
						item.setName(user.getName());
						item.setDepartmentId(user.getDepartmentId());
						item.setPositionId(user.getPositionId());
						item.setYear(currentYearStr);
						item.setTotalOvertime(list.get(i).getTimeChange());
						item.setResidualOvertime(list.get(i).getTimeChange());
						item.setCreationDate(new Date());
						leaveProcessService.leaveOvertimeCreat(item);
					} else if (timeItem != null) {
						item.setPoliceId(user.getPoliceId());
						item.setYear(currentYearStr);
						item.setTotalOvertime(timeItem.getTotalOvertime() + list.get(i).getTimeChange());
						item.setResidualOvertime(timeItem.getResidualOvertime() + list.get(i).getTimeChange());
						item.setUpdateDate(new Date());
						leaveProcessService.leaveOvertimeUpdate(item);
					}
					ClockRecord clock = new ClockRecord();
					clock.setId(list.get(i).getId());
					clock.setIdentification(2);
					clock.setUpdateDate(new Date());
					clockService.clockRecordUpdate(clock);
				}
			}
		}
//		System.out.println("成功保存：");
	}

	// 值班时长保存
	@Scheduled(cron = "0 0/1 * * * ?") // 每分钟
	public void dutySave() throws ApiException {
		// 警员打卡值班数据查询
		List<ClockRecord> list = clockService.dutyClockRecordList();
		int count = 0;
		if (list.size() > 0) {
			count = 1 + count;
			for (int i = 0; i < list.size(); i++) {
				User user = userService.policeItem(null, null, list.get(i).getUserId());
				if (user != null) {
					String workTime = null;
					String monthStr = null;
					if (list.get(i).getWorkDate() != null) {
						workTime = ft.format(list.get(i).getWorkDate());
						monthStr = ft.format(list.get(i).getWorkDate()).substring(5, 7);
					}
					ClockRecord record = clockService.offDutyClockRecordItem(list.get(i).getUserId(), workTime);
					if (record == null) {
						LeaveDuty item = new LeaveDuty();
						LeaveDuty timeItem = leaveProcessService.leaveDutyItem(null, user.getPoliceId(), currentYearStr,
								monthStr);
						if (timeItem == null) {
							item.setPoliceId(user.getPoliceId());
							item.setName(user.getName());
							item.setDepartmentId(user.getDepartmentId());
							item.setPositionId(user.getPositionId());
							item.setYear(currentYearStr);
							item.setMonth(monthStr);
							item.setTotalDutyTime(15.0);
							item.setResidualDutyTime(15.0);
							item.setCreationDate(new Date());
							leaveProcessService.leaveDutyCreat(item);
						} else if (timeItem != null) {
							item.setPoliceId(user.getPoliceId());
							item.setYear(currentYearStr);
							item.setMonth(monthStr);
							item.setTotalDutyTime(timeItem.getTotalDutyTime() + 15.0);
							item.setResidualDutyTime(timeItem.getResidualDutyTime() + 15.0);
							item.setUpdateDate(new Date());
							leaveProcessService.leaveDutyUpdate(item);
						}
						ClockRecord clock = new ClockRecord();
						clock.setId(list.get(i).getId());
						clock.setIdentification(2);
						clock.setUpdateDate(new Date());
						clockService.clockRecordUpdate(clock);
					}
				}
			}
			ClockRecord clockItems = new ClockRecord();
			clockItems.setIdentification(2);
			clockItems.setUpdateDate(new Date());
			clockService.identificationUpdate(clockItems);
		}
	}

	// 警员每年年假任务统计
//	@Scheduled(cron = "0 0 15 * * ?") // 每天15点执行一次
	@Scheduled(cron = "0 0/1 * * * ?") // 每分钟
	public void policeLeaveAnnualTask() throws ParseException {
		// 警员年假天数列表查询
		List<LeaveAnnual> list = leaveProcessService.policeLeaveAnnualExistList();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				LeaveAnnual leaveAnnual = leaveProcessService.leaveAnnualItem(null, list.get(i).getPoliceId(),
						list.get(i).getYear());
				if (leaveAnnual == null) {
					LeaveAnnual item = new LeaveAnnual();
					item.setPoliceId(list.get(i).getPoliceId());
					item.setName(list.get(i).getName());
					item.setDepartmentId(list.get(i).getDepartmentId());
					item.setPositionId(list.get(i).getPositionId());
					item.setYear(list.get(i).getYear());
					item.setAnnualLeaveCount(list.get(i).getAnnualLeaveCount());
					item.setAnnualLeaveDays(list.get(i).getAnnualLeaveCount());
					item.setAnnualLeaveNum(3);
					item.setCreationDate(new Date());
					leaveProcessService.leaveAnnualCreat(item);
				}
			}
		}
//		System.out.println("年假任务结束: " + DateUtils.formatDate(new Date(), "HH:mm:ss"));
		policeLeaveAnnualDataTask();
	}

	// 警员年假数据修改
	public void policeLeaveAnnualDataTask() throws ParseException {
		// 警员年假列表查询
		List<LeaveAnnual> list = leaveProcessService.leaveAnnualList(null, currentYearStr);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				// 个人最近请假查询列表（api）
				List<LeaveProcess> leavePersonalList = leaveProcessService.leavePersonalList(list.get(i).getPoliceId(),
						"年休假", 1);
				double days = 0;
				if (leavePersonalList.size() > 0) {
					for (int j = 0; j < leavePersonalList.size(); j++) {
						days = days + leavePersonalList.get(j).getLeaveDuarationDays();
					}
				}
				// 查询个人年休假详情
				LeaveAnnual leaveAnnual = leaveProcessService.leaveAnnualItem(null, list.get(i).getPoliceId(),
						list.get(i).getYear());
				if (leaveAnnual != null) {
					LeaveAnnual item = new LeaveAnnual();
					item.setId(list.get(i).getId());
					double diffNum = list.get(i).getAnnualLeaveDays() - days;
					if (diffNum >= 0) {
						item.setAnnualLeaveDays(diffNum);
					} else {
						item.setAnnualLeaveDays(0.0);
					}
					int nums = list.get(i).getAnnualLeaveNum() - leavePersonalList.size();
					if (nums >= 0) {
						item.setAnnualLeaveNum(nums);
					} else {
						item.setAnnualLeaveNum(0);
					}
					item.setUpdateDate(new Date());
					leaveProcessService.leaveAnnualUpdate(item);
				}
				if (leavePersonalList.size() > 0) {
					LeaveProcess process = new LeaveProcess();
					process.setUserId(leavePersonalList.get(0).getUserId());
					process.setIdentification(2);
					process.setUpdateDate(new Date());
					leaveProcessService.leaveProcessUpdate(process);
				}
			}
		}
//		System.out.println("年假数据修改: " + DateUtils.formatDate(new Date(), "HH:mm:ss"));
	}

	// 局领导修改调休记录任务
	@Scheduled(cron = "0 0/1 * * * ?") // 每分钟
	public void leaveLeaderNeedDealtUpdateTask() throws ParseException {
		// 调休记录列表查询(定时任务修改调休记录状态进程)
		List<LeaveCompensatoryRecord> statusList = leaveProcessService.leaveCompensatoryRecordStatusList();
		if (statusList.size() > 0) {
			for (int i = 0; i < statusList.size(); i++) {
				LeaveCompensatoryRecord item = new LeaveCompensatoryRecord();
				item.setId(statusList.get(i).getId());
				item.setStartTime(statusList.get(i).getStartTime());
				item.setEndTime(statusList.get(i).getEndTime());
				item.setStatus(4);
				item.setUpdateDate(new Date());
				leaveProcessService.leaveCompensatoryRecordUpdate(item);
			}
		}
	}

	// 新增加班调休任务
	@Scheduled(cron = "0 0/1 * * * ?") // 每分钟
	public void leaveLeaderOvertimeUpdateTask() throws ParseException {
		// 加班调休列表查询
		List<LeaveProcess> list = leaveProcessService.leaveLeaderOvertimeTaskList();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				// 根据policeId查询部门
				User user = userService.policeItem(null, null, list.get(i).getUserId());
				if (user != null) {
					LeaveOvertime oItem = leaveProcessService.leaveOvertimeItem(null, user.getPoliceId(),
							currentYearStr);
					LeaveCompensatoryRecord item = new LeaveCompensatoryRecord();
					item.setType(2);
					item.setItemId(Integer.valueOf(String.valueOf(list.get(i).getId())));
					item.setPoliceId(user.getPoliceId());
					item.setDepartmentId(user.getDepartmentId());
					item.setPositionId(user.getPositionId());
					item.setRemarks(list.get(i).getLeaveReason());
					if (list.get(i).getLeaveStartDate() != null) {
						item.setStartTime(list.get(i).getLeaveStartDate());
					}
					if (list.get(i).getLeaveEndDate() != null) {
						item.setEndTime(list.get(i).getLeaveEndDate());
					}
					item.setCreationDate(new Date());
					if (oItem == null) {
						item.setResult("拒绝了您的调休");
						item.setApprovedResult("申请天数超出加班可调休天数");
						item.setIsRest(0);
						item.setStatus(3);
					} else {
						// 个人加班可调休天数查询
						LeavePersonalStatistics personalItem = leaveProcessService
								.leavePersonalOverTimeChangeDays(user.getPoliceId(), currentYearStr);
						if (personalItem != null) {
							if (personalItem.getOvertimeChangeDays() >= list.get(i).getLeaveDuarationDays()) {
								// 获得加班兑换规则数据
								List<LeaveOvertimeRule> ruleList = leaveProcessService.getLeaveOvertimeRuleList();
								double ruleHour = ruleList.get(0).getHour();// 累计加班时长6
								double ruleComDay = ruleList.get(0).getDay1();// 可调休天数0.5
								item.setResult("同意了您的调休");
								item.setApprovedResult("申请了调休");
								item.setIsRest(1);
								item.setStatus(2);
								double diffNum = oItem.getResidualOvertime()
										- list.get(i).getLeaveDuarationDays() / ruleComDay * ruleHour;
								oItem.setResidualOvertime(diffNum);
								oItem.setUpdateDate(new Date());
								leaveProcessService.leaveOvertimeYearUpdate(oItem);
							} else {
								item.setResult("拒绝了您的调休");
								item.setApprovedResult("申请天数超出加班可调休天数");
								item.setIsRest(0);
								item.setStatus(3);
							}
						}

					}
					leaveProcessService.leaveCompensatoryRecordCreat(item);
				}
				LeaveProcess process = new LeaveProcess();
				process.setId(list.get(i).getId());
				process.setOvertimeItem(2);
				process.setUpdateDate(new Date());
				leaveProcessService.leaveProcessAlarmItemUpdate(process);
			}
		}
//		System.out.println("加班调休修改: " + DateUtils.formatDate(new Date(), "HH:mm:ss"));
	}

	// 新增积分调休任务
	@Scheduled(cron = "0 0/1 * * * ?") // 每分钟
	public void leaveLeaderPointUpdateTask() throws ParseException {
		// 积分调休列表查询
		List<LeaveProcess> list = leaveProcessService.leaveLeaderPointTaskList();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				// 根据policeId查询部门
				User user = userService.policeItem(null, null, list.get(i).getUserId());
				if (user != null) {
					Calendar cal = Calendar.getInstance();
					int month = cal.get(Calendar.MONTH) + 1;
					int halfYear = 0;
					if (month <= 6) {
						halfYear = 2;
						int yearInt = Integer.valueOf(currentYearStr) - 1;
						currentYearStr = String.valueOf(yearInt);
					} else if (month > 6) {
						halfYear = 1;
					}
					// 警员积分详情查询
					LeavePoints oItem = leaveProcessService.leavePointsItem(null, user.getPoliceId(), currentYearStr,
							halfYear);
					LeaveCompensatoryRecord item = new LeaveCompensatoryRecord();
					item.setType(3);
					item.setItemId(Integer.valueOf(String.valueOf(list.get(i).getId())));
					item.setPoliceId(user.getPoliceId());
					item.setDepartmentId(user.getDepartmentId());
					item.setPositionId(user.getPositionId());
					item.setRemarks(list.get(i).getLeaveReason());
					if (list.get(i).getLeaveStartDate() != null) {
						item.setStartTime(list.get(i).getLeaveStartDate());
					}
					if (list.get(i).getLeaveEndDate() != null) {
						item.setEndTime(list.get(i).getLeaveEndDate());
					}
					item.setCreationDate(new Date());
					if (oItem == null) {
						item.setResult("拒绝了您的积分调休");
						item.setApprovedResult("申请天数超出积分可调休天数");
						item.setIsRest(0);
						item.setStatus(3);
					} else {
						// 个人积分可调休天数查询
						LeavePersonalStatistics personalItem = leaveProcessService
								.leavePersonalpointsChangeDays(user.getPoliceId(), currentYearStr, halfYear);
						if (personalItem != null) {
							// 获得积分兑换规则数据
							List<LeaveIntegralExchangeRule> ruleList = leaveProcessService
									.getLeaveIntegralExchangeRuleList();
							double rulePoints = ruleList.get(0).getIntegralValue();// 规则积分
							double ruleDay = ruleList.get(0).getDay();// 积分兑换天数
							if (personalItem.getPointsChangeDays() >= list.get(i).getLeaveDuarationDays()) {
								item.setResult("同意了您的积分调休");
								item.setApprovedResult("申请了积分调休");
								item.setIsRest(1);
								item.setStatus(2);
								double diffNum = oItem.getResidualPoints()
										- list.get(i).getLeaveDuarationDays() / ruleDay * rulePoints;
								oItem.setResidualPoints(diffNum);
								oItem.setUpdateDate(new Date());
								leaveProcessService.leavePointsResidualUpdate(oItem);
							} else {
								item.setResult("拒绝了您的积分调休");
								item.setApprovedResult("申请天数超出积分可调休天数");
								item.setIsRest(0);
								item.setStatus(3);
							}
						}

					}
					leaveProcessService.leaveCompensatoryRecordCreat(item);
				}
				LeaveProcess process = new LeaveProcess();
				process.setId(list.get(i).getId());
				process.setPointItem(2);
				process.setUpdateDate(new Date());
				leaveProcessService.leaveProcessAlarmItemUpdate(process);
			}
		}
//		System.out.println("积分调休修改: " + DateUtils.formatDate(new Date(), "HH:mm:ss"));
	}

	// 局领导调休预警新增修改任务
	@Scheduled(cron = "0 0/1 * * * ?") // 每分钟
//	@RequestMapping(value = "/submit", method = RequestMethod.GET)
	public void leaveLeaderAlarmSubmitTask() throws ParseException {
		List<LeaveRestAlarmRule> ruleList = leaveProcessService.getLeaveRestAlarmRuleList();
		LeaveRestAlarmRule ruleItem1 = leaveProcessService.getLeaveRestAlarmRuleItem(4);
		LeaveRestAlarmRule ruleItem2 = leaveProcessService.getLeaveRestAlarmRuleItem(5);
		for (int i = 0; i < ruleList.size(); i++) {
			if (ruleList.get(i).getId() == 1) {// 每季度民警累计出差天数
				ReportDataFillTime fillTime = fillDate(null, 3);
				String startTime = fillTime.getStartTime();
				String endTime = fillTime.getEndTime();
				// 根据季度查询民警累计出差天数
				List<LeaveCompensatoryAlarm> list = leaveProcessService.quarterPoliceList(startTime, endTime);
				for (int j = 0; j < list.size(); j++) {
					// 根据季度,创建时间,警号查询季度预警
					LeaveCompensatoryAlarm quarterItem = leaveProcessService.quarterPoliceItem(1, 1,
							list.get(j).getPoliceId(), startTime, endTime);
					LeaveCompensatoryAlarm item = new LeaveCompensatoryAlarm();
					if (quarterItem == null) {
						item.setType(1);
						item.setRuleId(1);
						item.setPoliceId(list.get(j).getPoliceId());
						// 根据policeId查询部门
						User user = userService.policeItem(list.get(j).getPoliceId(), null, null);
						if (user != null) {
							item.setName(user.getName());
							item.setDepartmentId(user.getDepartmentId());
							item.setPositionId(user.getPositionId());
						}
						item.setYear(currentYearStr);
						item.setWorkDays(list.get(j).getWorkDays());
						item.setIsRest(0);
						item.setIsDeal(0);
						item.setCreationDate(new Date());
						leaveProcessService.leaveCompensatoryAlarmCreat(item);
					} else {
						item.setId(quarterItem.getId());
						item.setWorkDays(list.get(j).getWorkDays());
						item.setUpdateDate(new Date());
						leaveProcessService.leaveCompensatoryAlarmUpdate(item);
					}
				}
			} else if (ruleList.get(i).getId() == 2) {// 每月民警累计出差天数
				ReportDataFillTime fillTime = fillDate(null, 2);
				String startTime = fillTime.getStartTime();
				String endTime = fillTime.getEndTime();
				// 根据每月查询民警累计出差天数
				List<LeaveCompensatoryAlarm> list = leaveProcessService.monthPoliceList(startTime, endTime);
				for (int j = 0; j < list.size(); j++) {
					// 根据月度,创建时间,警号查询月度预警
					LeaveCompensatoryAlarm quarterItem = leaveProcessService.quarterPoliceItem(1, 2,
							list.get(j).getPoliceId(), startTime, endTime);
					LeaveCompensatoryAlarm item = new LeaveCompensatoryAlarm();
					if (quarterItem == null) {
						item.setType(1);
						item.setRuleId(2);
						item.setPoliceId(list.get(j).getPoliceId());
						// 根据policeId查询部门
						User user = userService.policeItem(list.get(j).getPoliceId(), null, null);
						if (user != null) {
							item.setName(user.getName());
							item.setDepartmentId(user.getDepartmentId());
							item.setPositionId(user.getPositionId());
						}
						item.setYear(currentYearStr);
						item.setWorkDays(list.get(j).getWorkDays());
						item.setIsRest(0);
						item.setIsDeal(0);
						item.setCreationDate(new Date());
						leaveProcessService.leaveCompensatoryAlarmCreat(item);
					} else {
						item.setId(quarterItem.getId());
						item.setWorkDays(list.get(j).getWorkDays());
						item.setUpdateDate(new Date());
						leaveProcessService.leaveCompensatoryAlarmUpdate(item);
					}
				}
			} else if (ruleList.get(i).getId() == 3) {// 民警单次出差天数
				// 查询单次民警出差天数
				List<LeaveCompensatoryAlarm> list = leaveProcessService.singlePoliceList();
				if (list.size() > 0) {
					for (int j = 0; j < list.size(); j++) {
						LeaveCompensatoryAlarm item = new LeaveCompensatoryAlarm();
						item.setType(1);
						item.setRuleId(3);
						item.setPoliceId(list.get(j).getPoliceId());
						// 根据policeId查询部门
						User user = userService.policeItem(list.get(j).getPoliceId(), null, null);
						if (user != null) {
							item.setName(user.getName());
							item.setDepartmentId(user.getDepartmentId());
							item.setPositionId(user.getPositionId());
						}
						item.setYear(currentYearStr);
						item.setWorkDays(list.get(j).getWorkDays());
						item.setIsRest(0);
						item.setIsDeal(0);
						item.setCreationDate(new Date());
						leaveProcessService.leaveCompensatoryAlarmCreat(item);
						LeaveProcess lProcess = new LeaveProcess();
						lProcess.setId(list.get(j).getId());
						lProcess.setAlarmItem(2);
						lProcess.setUpdateDate(new Date());
						leaveProcessService.leaveProcessAlarmItemUpdate(lProcess);// 修改请假表状态
					}
				}
			} else if (ruleList.get(i).getId() == 4 || ruleList.get(i).getId() == 5) {// 民警连续工作超3天
				double ruleDay1 = ruleItem1.getDay();
				double ruleDay2 = ruleItem2.getDay();
				// 查询连续未打卡天数
				List<LeaveCompensatoryAlarm> list = leaveProcessService.notRecordList();
				List<String> policeIdList = new ArrayList<String>();
				if (list.size() > 0) {
					for (int j = 0; j < list.size(); j++) {
						policeIdList.add(list.get(j).getPoliceId());
						// 查询连续工作预警
						LeaveCompensatoryAlarm continueItem = leaveProcessService.continuePoliceItem(2,
								list.get(j).getPoliceId());
						// 查询个人连续未打卡天数
//						LeaveCompensatoryAlarm notItem = leaveProcessService.notRecordPoliceItem(list.get(j).getPoliceId());
						LeaveCompensatoryAlarm item = new LeaveCompensatoryAlarm();
						if (continueItem == null) {
							item.setType(2);
							item.setPoliceId(list.get(j).getPoliceId());
							// 根据policeId查询部门
							User user = userService.policeItem(list.get(j).getPoliceId(), null, null);
							if (user != null) {
								item.setName(user.getName());
								item.setDepartmentId(user.getDepartmentId());
								item.setPositionId(user.getPositionId());
							}
							if (list.get(j).getWorkDays() >= ruleDay1 && list.get(j).getWorkDays() < ruleDay2) {
								item.setRuleId(4);
							} else {
								item.setRuleId(5);
							}
							item.setYear(currentYearStr);
							item.setWorkDays(list.get(j).getWorkDays());
							item.setIsRest(0);
							item.setIsDeal(0);
							item.setIdentification(1);
							item.setCreationDate(new Date());
							leaveProcessService.leaveCompensatoryAlarmCreat(item);
						} else {
							if (list.get(j).getWorkDays() != continueItem.getWorkDays()) {
								if (continueItem.getWorkDays() >= ruleDay1 && continueItem.getWorkDays() < ruleDay2) {
									item.setRuleId(4);
								} else {
									item.setRuleId(5);
								}
								item.setId(continueItem.getId());
								item.setWorkDays(list.get(j).getWorkDays());
								item.setUpdateDate(new Date());
								leaveProcessService.leaveCompensatoryAlarmUpdate(item);
							}
						}
					}
				}
				// 查询预警连续加班人员
				List<LeaveCompensatoryAlarm> list2 = leaveProcessService.alarmComRecordList();
				List<String> alarmPoliceIdList = new ArrayList<String>();
				if (list2.size() > 0) {
					for (int x = 0; x < list2.size(); x++) {
						alarmPoliceIdList.add(list2.get(x).getPoliceId());
					}
				}
				alarmPoliceIdList.removeAll(policeIdList);
				if (alarmPoliceIdList.size() > 0) {
					for (int k = 0; k < alarmPoliceIdList.size(); k++) {
						LeaveCompensatoryAlarm coItem = leaveProcessService.continuePoliceItem(2,
								alarmPoliceIdList.get(k));
						if (coItem != null) {
							LeaveCompensatoryAlarm citem = new LeaveCompensatoryAlarm();
							citem.setId(coItem.getId());
							citem.setIdentification(2);
							citem.setUpdateDate(new Date());
							leaveProcessService.leaveCompensatoryAlarmUpdate(citem);
						}
					}
				}
			}
		}
	}

	// 获取当前日期当前周,当前月,当前季度,当前半年,当前年的时间段
	private ReportDataFillTime fillDate(String dataTime, Integer fillId) throws ParseException {
		ReportDataFillTime report = new ReportDataFillTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		int year = 0;
		int month = 0;
		String startTime = null;
		String endTime = null;
		Date time = null;
		if (dataTime == null || dataTime == "") {
			time = new Date();
		} else {
			if (dataTime.length() == 10) {// 2020-02-02
				time = sdf.parse(dataTime);
			} else if (dataTime.length() == 7) {// 2020-02
				time = sdf.parse(dataTime + "-01");
				year = Integer.valueOf(dataTime.substring(0, 4));
				month = Integer.valueOf(dataTime.substring(5, 7));
			} else if (dataTime.length() == 4) {// 2020
				time = sdf.parse(dataTime + "-01" + "-01");
				year = Integer.valueOf(dataTime);
				month = 1;
			}
		}
		Calendar cal = Calendar.getInstance();
		if (dataTime == null || dataTime == "") {
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH) + 1;
		}
		cal.setTime(time);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		String imptimeBegin = sdf.format(cal.getTime());
		cal.add(Calendar.DATE, 6);
		String imptimeEnd = sdf.format(cal.getTime());
		cal.add(Calendar.DATE, 13);
		String dateTime = null;
		if (fillId == 1) {// 周报
			dateTime = imptimeBegin + "至" + imptimeEnd;
			startTime = imptimeBegin;
			endTime = imptimeEnd;
		} else if (fillId == 2) {// 月报
			dateTime = year + "年" + month + "月";
			startTime = year + "-" + month + "-01";
			if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
				endTime = year + "-" + month + "-31";
			} else if (month == 4 || month == 6 || month == 9 || month == 11) {
				endTime = year + "-" + month + "-30";
			} else if (month == 2) {
				if (year % 400 == 0 || year % 4 == 0 && year % 100 != 0) {
					endTime = year + "-" + month + "-29";
				} else {
					endTime = year + "-" + month + "-28";
				}
			}
		} else if (fillId == 3) {// 季报
			if (month < 4) {
				dateTime = year + "年1月至" + year + "年3月";
				startTime = year + "-01-01";
				endTime = year + "-03-31";
			} else if (month > 3 && month < 7) {
				dateTime = year + "年4月至" + year + "年6月";
				startTime = year + "-04-01";
				endTime = year + "-06-30";
			} else if (month > 6 && month < 10) {
				dateTime = year + "年7月至" + year + "年9月";
				startTime = year + "-07-01";
				endTime = year + "-09-30";
			} else {
				dateTime = year + "年10月至" + year + "年12月";
				startTime = year + "-10-01";
				endTime = year + "-12-31";
			}
		} else if (fillId == 4) {// 半年报
			if (month < 7) {
				dateTime = year + "年1月至" + year + "年6月";
				startTime = year + "-01-01";
				endTime = year + "-06-30";
			} else {
				dateTime = year + "年7月至" + year + "年12月";
				startTime = year + "-07-01";
				endTime = year + "-12-31";
			}
		} else if (fillId == 5) {// 年报
			dateTime = year + "年";
			startTime = year + "-01-01";// 2020-01-01
			endTime = year + "-12-31";
		}
		report.setFillTime(dateTime);
		report.setStartTime(startTime);
		report.setEndTime(endTime);
		return report;
	}

}

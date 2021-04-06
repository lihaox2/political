/**
 * 
 */
package com.bayee.political.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bayee.political.domain.AlarmPoliceMonth;
import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.ClockRecord;
import com.bayee.political.domain.DepOvertimeDutypoliceNum;
import com.bayee.political.domain.Department;
import com.bayee.political.domain.LeaveAlarmLeaderStatistics;
import com.bayee.political.domain.LeaveAnnualStatistics;
import com.bayee.political.domain.LeaveChart;
import com.bayee.political.domain.LeaveCompensatoryAlarm;
import com.bayee.political.domain.LeaveCompensatoryReadRecord;
import com.bayee.political.domain.LeaveCompensatoryRecord;
import com.bayee.political.domain.LeaveCompensatoryRecordStatistics;
import com.bayee.political.domain.LeaveDepAnnualChart;
import com.bayee.political.domain.LeavePower;
import com.bayee.political.domain.LeavePowerObject;
import com.bayee.political.domain.LeaveIntegralExchangeRule;
import com.bayee.political.domain.LeaveIntegralManage;
import com.bayee.political.domain.LeaveLeaderAlarm;
import com.bayee.political.domain.LeaveLeaderAlarmList;
import com.bayee.political.domain.LeaveLine;
import com.bayee.political.domain.LeaveOverDutyStatistics;
import com.bayee.political.domain.LeaveOvertime;
import com.bayee.political.domain.LeaveOvertimeRule;
import com.bayee.political.domain.LeavePersonalStatistics;
import com.bayee.political.domain.LeavePoints;
import com.bayee.political.domain.LeavePointsExchangeRecord;
import com.bayee.political.domain.LeaveProcess;
import com.bayee.political.domain.LeaveProcessCode;
import com.bayee.political.domain.LeaveProcessOperationRecord;
import com.bayee.political.domain.LeaveProcessTask;
import com.bayee.political.domain.LeaveRestAlarmRule;
import com.bayee.political.domain.LeaveRestManage;
import com.bayee.political.domain.LeaveThisYearStatistics;
import com.bayee.political.domain.LeaveTrain;
import com.bayee.political.domain.LeaveTrainStatistics;
import com.bayee.political.domain.LeaveVacationChart;
import com.bayee.political.domain.PoliceForceOnlineStatistics;
import com.bayee.political.domain.PolicePosition;
import com.bayee.political.domain.User;
import com.bayee.political.model.LeaveProcessAlert;
import com.bayee.political.service.AlarmService;
import com.bayee.political.service.ClockService;
import com.bayee.political.service.DepartmentService;
import com.bayee.political.service.LeaveProcessCodeService;
import com.bayee.political.service.LeaveProcessOperationRecordService;
import com.bayee.political.service.LeaveProcessService;
import com.bayee.political.service.LeaveProcessTaskService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListPage;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
import com.bayee.political.utils.ExportExcelUtils;
import com.bayee.political.utils.GetExcel;
import com.bayee.political.utils.LeaveIntegralManageExcel;
import com.bayee.political.utils.LeaveTrainExcel;
import com.bayee.political.utils.StatusCode;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiProcessListbyuseridRequest;
import com.dingtalk.api.request.OapiProcessinstanceGetRequest;
import com.dingtalk.api.request.OapiProcessinstanceListidsRequest;
import com.dingtalk.api.response.OapiProcessListbyuseridResponse;
import com.dingtalk.api.response.OapiProcessListbyuseridResponse.ProcessTopVo;
import com.dingtalk.api.response.OapiProcessinstanceGetResponse;
import com.dingtalk.api.response.OapiProcessinstanceGetResponse.FormComponentValueVo;
import com.dingtalk.api.response.OapiProcessinstanceGetResponse.OperationRecordsVo;
import com.dingtalk.api.response.OapiProcessinstanceGetResponse.ProcessInstanceTopVo;
import com.dingtalk.api.response.OapiProcessinstanceGetResponse.TaskTopVo;
import com.dingtalk.api.response.OapiProcessinstanceListidsResponse;
import com.dingtalk.api.response.OapiProcessinstanceListidsResponse.PageResult;
import com.sun.mail.imap.protocol.Item;
import com.taobao.api.ApiException;

import cn.hutool.core.date.DateUtil;

/**
 * @author seanguo
 *
 */
@Controller
@Component
@EnableScheduling
public class ProcessController extends BaseController {

	@Autowired
	private LeaveProcessCodeService leaveProcessCodeService;

	@Autowired
	private LeaveProcessService leaveProcessService;

	@Autowired
	private LeaveProcessTaskService leaveProcessTaskService;

	@Autowired
	private LeaveProcessOperationRecordService leaveProcessOperationRecordService;

	@Autowired
	private LeaveProcessAlert leaveProcessAlert;

	@Autowired
	private ClockService clockService;

	@Autowired
	private UserService userService;

	@Autowired
	private AlarmService alarmService;

	@Autowired
	private DepartmentService departmentService;

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	SimpleDateFormat sdfs = new SimpleDateFormat("yyyy年MM月dd日");

	String currentYearStr = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));

	DecimalFormat df = new DecimalFormat("#.00");

	/**
	 * 获取所有请假模板
	 */
	@RequestMapping(value = { "/process-code/snyc" })
	public ResponseEntity<String> snycProcessFromDingtalk() throws ApiException {
		String accessToken = getAccessToken();
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/process/listbyuserid");
		OapiProcessListbyuseridRequest request = new OapiProcessListbyuseridRequest();
		request.setOffset(0L);
		request.setSize(100L);
		OapiProcessListbyuseridResponse response = client.execute(request, accessToken);

		int count = 0;
		List<ProcessTopVo> processList = response.getResult().getProcessList();
		for (ProcessTopVo processTopVo : processList) {
			String processCode = processTopVo.getProcessCode();
			String name = processTopVo.getName();

			if (StringUtils.isNotEmpty(name) && name.contains("请假")) {
				LeaveProcessCode process = new LeaveProcessCode();
				process.setName(name);
				process.setProcessCode(processCode);

				leaveProcessCodeService.save(process);
				count++;
			}
		}

		return new ResponseEntity<String>(String.valueOf("Process saved: " + count), HttpStatus.OK);
	}

	/**
	 * 
	 * 获取去年一年的请假数据
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value = { "/process/snyc" })
	public ResponseEntity<String> snycLastYearLeaveProcessFromDingtalk() throws ApiException, ParseException {
		String accessToken = getAccessToken();

		List<LeaveProcessCode> leaveProcessList = leaveProcessCodeService.findAll();
		int count = 0;
		long startTime = DateUtils.getUnixTime("2020-01-01 00:00:00");
		for (LeaveProcessCode process : leaveProcessList) {
			System.out.println("###### fetch [" + process.getName() + "]'s instance ids...");

			PageResult pageResult = fetchProcessInstanceIds(accessToken, process.getProcessCode(), 0, startTime);
			List<String> processInstanceIdList = pageResult.getList();
			// fetch process detail
			fetchAndSaveProcessInstance(accessToken, process.getProcessCode(), processInstanceIdList);
			count += processInstanceIdList.size();

			while (pageResult.getNextCursor() != null) {
				pageResult = fetchProcessInstanceIds(accessToken, process.getProcessCode(), pageResult.getNextCursor(),
						startTime);
				processInstanceIdList = pageResult.getList();
				// fetch process detail
				fetchAndSaveProcessInstance(accessToken, process.getProcessCode(), processInstanceIdList);
				count += processInstanceIdList.size();
			}
		}

		return new ResponseEntity<String>(String.valueOf("Process instance saved: " + count), HttpStatus.OK);
	}

	/**
	 * 自动定时抓取钉钉请假数据，每1分钟执行一次
	 * 
	 * @throws ParseException
	 */
	@Scheduled(cron = "0 */1 * * * ?") // 间隔1分钟执行
//	@RequestMapping(value = "/process/save/leader", method = RequestMethod.GET)
	public void autoSnycLeaveProcessFromDingtalk() throws ApiException, ParseException {
		String accessToken = getAccessToken();
		List<LeaveProcessCode> leaveProcessList = leaveProcessCodeService.findAll();
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.MINUTE, -1);// 1分钟前的时间
		long startTime = nowTime.getTime().getTime();
//		Calendar cal = Calendar.getInstance();// 获取一个Calendar对象
//		cal.setTime(new Date());
//		cal.add(Calendar.MONTH, -1);// 获取当前时间上一个月
//		long startTime = cal.getTime().getTime();
		for (LeaveProcessCode process : leaveProcessList) {
//			System.out.println("###### fetch [" + process.getName() + "]'s instance ids...");

			PageResult pageResult = fetchProcessInstanceIds(accessToken, process.getProcessCode(), 0, startTime);
			List<String> processInstanceIdList = pageResult.getList();
			// fetch process detail
			fetchAndSaveProcessInstance(accessToken, process.getProcessCode(), processInstanceIdList);

			while (pageResult.getNextCursor() != null) {
				pageResult = fetchProcessInstanceIds(accessToken, process.getProcessCode(), pageResult.getNextCursor(),
						startTime);
				processInstanceIdList = pageResult.getList();
				// fetch process detail
				fetchAndSaveProcessInstance(accessToken, process.getProcessCode(), processInstanceIdList);
			}
		}
	}

	private PageResult fetchProcessInstanceIds(String accessToken, String processCode, long cursor, long startTime)
			throws ApiException {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/processinstance/listids");
		OapiProcessinstanceListidsRequest req = new OapiProcessinstanceListidsRequest();
		req.setProcessCode(processCode);
		req.setStartTime(startTime);
//		req.setEndTime(DateUtils.getUnixTime("2019-12-31 23:59:59"));
		req.setSize(20L);
		req.setCursor(cursor);
		OapiProcessinstanceListidsResponse response = client.execute(req, accessToken);
		return response.getResult();
	}

	private void fetchAndSaveProcessInstance(String accessToken, String processCode, List<String> processInstanceIdList)
			throws ApiException, ParseException {
		for (String instanceId : processInstanceIdList) {
			DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/processinstance/get");
			OapiProcessinstanceGetRequest request = new OapiProcessinstanceGetRequest();
			request.setProcessInstanceId(instanceId);
			OapiProcessinstanceGetResponse response = client.execute(request, accessToken);
			ProcessInstanceTopVo instance = response.getProcessInstance();

			System.out.println("save process: " + instance.getTitle());

			LeaveProcess leaveProcess = new LeaveProcess();
			leaveProcess.setProcessCode(processCode);
			leaveProcess.setProcessInstanceId(instanceId);
			// 审批实例业务动作，MODIFY表示该审批实例是基于原来的实例修改而来，
			// REVOKE表示该审批实例对原来的实例进行撤销，NONE表示正常发起
			String title = instance.getTitle();
			leaveProcess.setTitle(title);
			String bizAction = instance.getBizAction();
			leaveProcess.setBizAction(bizAction);
			String businessId = instance.getBusinessId();
			leaveProcess.setBusinessId(businessId);
			Date creationTime = instance.getCreateTime();
			leaveProcess.setCreateTime(creationTime);
			Date finishTime = instance.getFinishTime();
			leaveProcess.setFinishTime(finishTime);
			String departmentId = instance.getOriginatorDeptId();
			leaveProcess.setDepartmentId(departmentId);
			String userId = instance.getOriginatorUserid();
			leaveProcess.setUserId(userId);
			String result = instance.getResult(); // 审批结果，分为 agree 和 refuse
			leaveProcess.setResult(result);
			String status = instance.getStatus(); // 审批状态，分为NEW（新创建） RUNNING（运行中）TERMINATED（被终止）COMPLETED（完成）
			leaveProcess.setStatus(status);
			if (instance.getCcUserids() != null && instance.getCcUserids().size() > 0) {
				String ccUserIds = Arrays.toString(instance.getCcUserids().toArray()).replaceAll("\\[", "")
						.replaceAll("\\]", "");
				leaveProcess.setCcUserIds(ccUserIds);
			}

			List<FormComponentValueVo> formValues = instance.getFormComponentValues();
			for (FormComponentValueVo formValue : formValues) {
				if (formValue.getComponentType().equals("DDHolidayField")) {
					String leaveFormValue = formValue.getValue();
					System.out.println(formValue.getValue());
					String[] leaveFormFiledValues = leaveFormValue.replaceAll("\\[", "").replaceAll("\\]", "")
							.replaceAll("\"", "").split(",");
					List<String> strsToList = Arrays.asList(leaveFormFiledValues);
//					String startDate = leaveFormFiledValues[0]; // 请假开始时间
//					System.out.println("请假开始时间:" + leaveFormFiledValues[0]);
//					leaveProcess.setLeaveStartDate(DateUtils.toDate(startDate));
//					String endDate = leaveFormFiledValues[1]; // 请假结束时间
//					leaveProcess.setLeaveEndDate(DateUtils.toDate(endDate));
					String startDate = strsToList.get(0); // 请假开始时间
					leaveProcess.setLeaveStartDate(formatter.parse(startDate));
//					System.out.println("请假开始时间:" + startDate);
					String endDate = strsToList.get(1); // 请假结束时间
					leaveProcess.setLeaveEndDate(formatter.parse(endDate));
//					System.out.println("请假结束时间:" + endDate);
					double durationInDays = Double.valueOf(leaveFormFiledValues[2]); // 请假天数
					leaveProcess.setLeaveDuarationDays(durationInDays);
					String leaveType = leaveFormFiledValues[4]; // 请假类型
					leaveProcess.setLeaveType(leaveType);

				} else if (formValue.getComponentType().equals("TextareaField")) {
					if (formValue.getName().equals("请假事由")) {
						String leaveReason = formValue.getValue();
						leaveProcess.setLeaveReason(leaveReason);
					}

				} else if (formValue.getComponentType().equals("DDSelectField")) {
					if (formValue.getId().equals("DDSelectField-IUTW8VHE")) { // 请假类型
						String leaveType = formValue.getValue();
						leaveProcess.setLeaveType(leaveType);
					}
				} else if (formValue.getComponentType().equals("TextField")) {
					if (formValue.getId().equals("TextField-J2JZF425")) { // 外出目的地
						String destination = formValue.getValue();
						leaveProcess.setDestination(destination);
					}
				} else if (formValue.getComponentType().equals("DDMultiSelectField")) {
					if (formValue.getId().equals("DDMultiSelectField-J2JZF426")) { // 出行方式
						String transportation = formValue.getValue();
						leaveProcess.setTransportation(transportation);
					}
				} else if (formValue.getComponentType().equals("DDDateRangeField")) {
					if (formValue.getId().equals("DDDateRangeField-IUTW8VHG")) { // 时间
						String[] dateRanges = formValue.getValue().replaceAll("\\[", "").replaceAll("\\]", "")
								.replaceAll("\"", "").split(",");
						String startDate = dateRanges[0]; // 请假开始时间
						leaveProcess.setLeaveStartDate(DateUtils.toDate(startDate));
						String endDate = dateRanges[1]; // 请假结束时间
						leaveProcess.setLeaveEndDate(DateUtils.toDate(endDate));
						double durationInDays = Double.valueOf(dateRanges[2]); // 请假天数
						leaveProcess.setLeaveDuarationDays(durationInDays);
					}
				} else if (formValue.getComponentType().equals("TextareaField")) {
					if (formValue.getId().equals("TextareaField-IUTW8VHI")) { // 事由
						String leaveReason = formValue.getValue();
						leaveProcess.setLeaveReason(leaveReason);
					}
				}
			}

			// save leave process instance
			leaveProcessService.saveOrUpdate(leaveProcess);

			// tasks
			List<TaskTopVo> tasks = instance.getTasks();
			leaveProcessTaskService.deleteByProcessId(leaveProcess.getId());
			for (TaskTopVo task : tasks) {
				// save leave process tasks
				LeaveProcessTask processTask = new LeaveProcessTask();
				processTask.setLeaveProcessId(leaveProcess.getId());
				processTask.setBusinessId(businessId);
				processTask.setResult(task.getTaskResult());
				processTask.setStatus(task.getTaskStatus());
				processTask.setTaskId(task.getTaskid());
				processTask.setUserId(task.getUserid());
				processTask.setTaskCreateTime(task.getCreateTime());
				processTask.setTaskFinishTime(task.getFinishTime());
				leaveProcessTaskService.save(processTask);
			}

			// records
			List<OperationRecordsVo> records = instance.getOperationRecords();
			leaveProcessOperationRecordService.deleteByProcessId(leaveProcess.getId());
			for (OperationRecordsVo record : records) {
				// 保存时，需根据业务编码查看是否已存，如已存在则做更新操作，否则做新增操作
				// save leave process operation records
				LeaveProcessOperationRecord operationRecord = new LeaveProcessOperationRecord();
				operationRecord.setBusinessId(businessId);
				operationRecord.setLeaveProcessId(leaveProcess.getId());
				operationRecord.setOperationDate(record.getDate());
				operationRecord.setOperationResult(record.getOperationResult());
				operationRecord.setOperationType(record.getOperationType());
				operationRecord.setRemark(record.getRemark());
				operationRecord.setUserId(record.getUserid());
				leaveProcessOperationRecordService.save(operationRecord);
			}

			// 异步执行预警模型
			leaveProcessAlert.process(leaveProcess);
		}
	}

	// -----------------AI休假(预警)api-----------------------
	// 局领导预警页面统计
	@RequestMapping(value = "/leave/alarm/leader/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> leaveAlarmLeaderStatistics(
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		LeaveAlarmLeaderStatistics item = new LeaveAlarmLeaderStatistics();
		// 民警总数
		int policeNum = userService.userProcessCount(departmentId, null, null, null);
		item.setPoliceNum(policeNum);
		// 年休假人数统计
		int annualLeaveNum = leaveProcessService.annualLeaveNum(departmentId);
		item.setAnnualLeaveNum(annualLeaveNum);
		// 未休年假人数统计
		int notAnnualLeaveNum = leaveProcessService.notAnnualLeaveNum(departmentId);
		item.setNotAnnualLeaveNum(notAnnualLeaveNum);
		double totalNum = annualLeaveNum + notAnnualLeaveNum;
		double annualLeaveNumDou = annualLeaveNum;
		double notAnnualLeaveNumDou = notAnnualLeaveNum;
		item.setAnnualLeaveRate(Double.valueOf(df.format(annualLeaveNumDou / totalNum * 100)));
		item.setNotAnnualLeaveRate(Double.valueOf(df.format(notAnnualLeaveNumDou / totalNum * 100)));
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 局领导最新调休提醒查询(1条)
	@RequestMapping(value = "/leave/leader/compensatory/record/newest/list", method = RequestMethod.GET)
	public ResponseEntity<?> leaveLeaderCompensatoryRecordNewestList(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		LeaveCompensatoryRecordStatistics recordItme = new LeaveCompensatoryRecordStatistics();
		// 个人最新调休记录查询
		List<LeaveCompensatoryRecord> list = leaveProcessService.leaveCompensatoryRecordNewestList(policeId, 0);
		// 个人调休记录总数统计
		int total = leaveProcessService.leaveCompensatoryRecordCount(policeId, null, null, 0);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int timeItem = Math.abs(list.get(i).getTimeChange());
				if (timeItem == 0) {
					list.get(i).setCreationDateStr("1分钟前");
				} else if (timeItem < 60 && timeItem > 0) {
					list.get(i).setCreationDateStr(timeItem + "分钟前");
				} else if (timeItem >= 60 && timeItem < 1440) {
					int timeInt = (int) Math.floor(timeItem / 60);
					list.get(i).setCreationDateStr(timeInt + "小时前");
				} else if (timeItem >= 1440 && timeItem <= 4320) {
					int timeInt = (int) Math.floor(timeItem / 60 / 24);
					list.get(i).setCreationDateStr(timeInt + "天前");
				} else if (timeItem > 4320) {
					int yearInt = Integer.valueOf(currentYear);
					int createYear = Integer.valueOf(sdf.format(list.get(i).getCreationDate()).substring(0, 4));
					if (yearInt == createYear) {
						list.get(i).setCreationDateStr(sdfs.format(list.get(i).getCreationDate()).substring(5, 11));
					} else {
						String timeString = sdfs.format(list.get(i).getCreationDate());
						list.get(i).setCreationDateStr(timeString);
					}
				}
				LeaveCompensatoryRecord rItem = new LeaveCompensatoryRecord();
				rItem.setId(list.get(i).getId());
				rItem.setType(list.get(i).getType());
				rItem.setPoliceId(policeId);
				rItem.setReadStatus(1);
				rItem.setUpdateDate(new Date());
				leaveProcessService.leaveReadUpdate(rItem);
				list.get(i).setReadStatus(rItem.getReadStatus());
				recordItme.setList(list);
			}
		}
		if (total > 0) {
			recordItme.setIsRead(1);
		} else {
			recordItme.setIsRead(0);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(recordItme);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 局领导强制调休提醒分页查询
	@RequestMapping(value = "/leave/leader/compensatory/record/list", method = RequestMethod.GET)
	public ResponseEntity<?> leaveLeaderCompensatoryRecordList(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "dateTime", required = false) String dateTime,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageNum", required = false) Integer pageNum) throws ApiException {
		DataListPage dlr = new DataListPage();
		String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
//		if (StringUtils.isEmpty(dateTime)) {
//			dateTime = sdf.format(new Date());
//		}
//		dateTime = dateTime.substring(0, 7);
		Integer pageNums = pageNum;
		if (pageSize == null) {
			pageSize = 10;
		}
		pageNum = ((pageNum) - 1) * pageSize;
		// 个人调休记录查询
		List<LeaveCompensatoryRecord> list = leaveProcessService.leaveCompensatoryRecordList(policeId, type, dateTime,
				null, pageSize, pageNum);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int timeItem = Math.abs(list.get(i).getTimeChange());
				if (timeItem == 0) {
					list.get(i).setCreationDateStr("1分钟前");
				} else if (timeItem < 60 && timeItem > 0) {
					list.get(i).setCreationDateStr(timeItem + "分钟前");
				} else if (timeItem >= 60 && timeItem < 1440) {
					int timeInt = (int) Math.floor(timeItem / 60);
					list.get(i).setCreationDateStr(timeInt + "小时前");
				} else if (timeItem >= 1440 && timeItem <= 4320) {
					int timeInt = (int) Math.floor(timeItem / 60 / 24);
					list.get(i).setCreationDateStr(timeInt + "天前");
				} else if (timeItem > 4320) {
					int yearInt = Integer.valueOf(currentYear);
					int createYear = Integer.valueOf(sdf.format(list.get(i).getCreationDate()).substring(0, 4));
					if (yearInt == createYear) {
						list.get(i).setCreationDateStr(sdfs.format(list.get(i).getCreationDate()).substring(5, 11));
					} else {
						String timeString = sdfs.format(list.get(i).getCreationDate());
						list.get(i).setCreationDateStr(timeString);
					}
				}
				LeaveCompensatoryRecord rItem = new LeaveCompensatoryRecord();
				rItem.setId(list.get(i).getId());
				rItem.setType(list.get(i).getType());
				rItem.setPoliceId(policeId);
				rItem.setReadStatus(1);
				rItem.setUpdateDate(new Date());
				leaveProcessService.leaveReadUpdate(rItem);
				list.get(i).setReadStatus(rItem.getReadStatus());
			}
		}
		// 个人调休记录总数统计
		int total = leaveProcessService.leaveCompensatoryRecordCount(policeId, type, dateTime, null);

		// DataListPage dlr = new DataListPage();--
////		if (StringUtils.isEmpty(dateTime)) {
////			dateTime = sdf.format(new Date());
////		}
////		dateTime = dateTime.substring(0, 7);
//		Integer pageNums = pageNum;
//		if (pageSize == null) {
//			pageSize = 10;
//		}
//		pageNum = ((pageNum) - 1) * pageSize;
//		// 局领导调休提醒分页查询
//		List<LeaveCompensatoryReadRecord> list = leaveProcessService.leaveLeaderCompensatoryRecordList(policeId, type,
//				dateTime, null, pageSize, pageNum);
//		list.removeAll(Collections.singleton(null));
//		if (list.size() > 0) {
//			for (int i = 0; i < list.size(); i++) {
//				int timeItem = Math.abs(list.get(i).getTimeChange());
//				if (timeItem == 0) {
//					list.get(i).setCreationDateStr("1分钟前");
//				} else if (timeItem < 60 && timeItem > 0) {
//					list.get(i).setCreationDateStr(timeItem + "分钟前");
//				} else if (timeItem >= 60 && timeItem < 1440) {
//					int timeInt = (int) Math.floor(timeItem / 60);
//					list.get(i).setCreationDateStr(timeInt + "小时前");
//				} else if (timeItem >= 1440 && timeItem <= 4320) {
//					int timeInt = (int) Math.floor(timeItem / 60 / 24);
//					list.get(i).setCreationDateStr(timeInt + "天前");
//				} else if (timeItem > 4320) {
//					int yearInt = Integer.valueOf(currentYearStr);
//					int createYear = Integer.valueOf(sdf.format(list.get(i).getCreationDate()).substring(0, 4));
//					if (yearInt == createYear) {
//						list.get(i).setCreationDateStr(sdfs.format(list.get(i).getCreationDate()).substring(5, 11));
//					} else {
//						String timeString = sdfs.format(list.get(i).getCreationDate());
//						list.get(i).setCreationDateStr(timeString);
//					}
//				}
//				LeaveCompensatoryReadRecord rItem = new LeaveCompensatoryReadRecord();
//				rItem.setId(list.get(i).getId());
//				rItem.setApprovedReadStatus(1);
//				rItem.setUpdateDate(new Date());
//				leaveProcessService.leaveCompensatoryReadRecordUpdate(rItem);
//				list.get(i).setApprovedReadStatus(rItem.getApprovedReadStatus());
//			}
//		}
//		// 局领导调休提醒记录总数统计
//		int total = leaveProcessService.leaveLeaderCompensatoryRecordCount(policeId, type, dateTime, null);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		dlr.setTotal(total);
		dlr.setPageNum(pageNums);
		dlr.setPageSize(pageSize);
		dlr.setTotalPage(((int) Math.ceil((double) total / (double) pageSize)));
		int count = ((int) Math.ceil((double) total / (double) pageSize));
		if (count - pageNums > 0) {
			dlr.setPageNext(1);
		} else {
			dlr.setPageNext(0);
		}
		return new ResponseEntity<DataListPage>(dlr, HttpStatus.OK);
	}

	// 局领导需强制调休人员查询(最新3条)
	@RequestMapping(value = "/leave/leader/compensatory/alarm/newest/list", method = RequestMethod.GET)
	public ResponseEntity<?> leaveLeaderCompensatoryAlarmNewestList(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		LeaveLeaderAlarmList itme = new LeaveLeaderAlarmList();
		// 局领导需强制调休人员查询(最新3条)
		List<LeaveCompensatoryAlarm> list = leaveProcessService.leaveLeaderCompensatoryAlarmNewestList(policeId);
		list.removeAll(Collections.singleton(null));
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getColour() == null) {
					if (list.get(i).getType() == 1) {
						list.get(i).setColour("#d81e06");
					} else {
						list.get(i).setColour("#e0620d");
					}
				}
				int timeItem = list.get(i).getTimeChange();
				if (timeItem == 0) {
					list.get(i).setCreationDateStr("1分钟前");
				} else if (timeItem < 60 && timeItem > 0) {
					list.get(i).setCreationDateStr(timeItem + "分钟前");
				} else if (timeItem >= 60 && timeItem < 1440) {
					int timeInt = (int) Math.floor(timeItem / 60);
					list.get(i).setCreationDateStr(timeInt + "小时前");
				} else if (timeItem >= 1440 && timeItem < 2880) {
//					String dateTime = sdf.format(list.get(i).getCreationDate()).substring(11, 16);
					list.get(i).setCreationDateStr("1天前");
				} else if (timeItem >= 2880 && timeItem < 4320) {
//					String dateTime = sdf.format(list.get(i).getCreationDate()).substring(11, 16);
					list.get(i).setCreationDateStr("2天开始");
				} else if (timeItem >= 4320) {
					int yearInt = Integer.valueOf(currentYear);
					int createYear = Integer.valueOf(sdf.format(list.get(i).getCreationDate()).substring(0, 4));
					if (yearInt == createYear) {
						list.get(i).setCreationDateStr(sdf.format(list.get(i).getCreationDate()).substring(5, 10));
					} else {
						String timeString = sdf.format(list.get(i).getCreationDate()).substring(0, 10);
						list.get(i).setCreationDateStr(timeString);
					}
				}
			}
		}
		itme.setCompensatoryAlarmList(list);
		// 调休预警总数统计
		int count = leaveProcessService.leaveLeaderCompensatoryAlarmNewestCount(policeId);
		itme.setNum(count);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(itme);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 局领导需调休人员列表分页查询
	@RequestMapping(value = "/leave/leader/compensatory/alarm/list", method = RequestMethod.GET)
	public ResponseEntity<?> leaveLeaderCompensatoryAlarmList(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageNum", required = false) Integer pageNum) throws ApiException {
		DataListPage dlr = new DataListPage();
		String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		Integer pageNums = pageNum;
		if (pageSize == null) {
			pageSize = 10;
		}
		pageNum = ((pageNum) - 1) * pageSize;
		// 调休预警分页列表查询
		List<LeaveCompensatoryAlarm> list = leaveProcessService.leaveLeaderCompensatoryAlarmList(policeId, pageSize,
				pageNum);
		list.removeAll(Collections.singleton(null));
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getColour() == null) {
					if (list.get(i).getType() == 1) {
						list.get(i).setColour("#d81e06");
					} else {
						list.get(i).setColour("#e0620d");
					}
				}
				int timeItem = list.get(i).getTimeChange();
				if (timeItem == 0) {
					list.get(i).setCreationDateStr("1分钟前");
				} else if (timeItem < 60 && timeItem > 0) {
					list.get(i).setCreationDateStr(timeItem + "分钟前");
				} else if (timeItem >= 60 && timeItem < 1440) {
					int timeInt = (int) Math.floor(timeItem / 60);
					list.get(i).setCreationDateStr(timeInt + "小时前");
				} else if (timeItem >= 1440 && timeItem < 2880) {
					list.get(i).setCreationDateStr("1天前");
				} else if (timeItem >= 2880 && timeItem < 4320) {
					list.get(i).setCreationDateStr("2天开始");
				} else if (timeItem >= 4320) {
					int yearInt = Integer.valueOf(currentYear);
					int createYear = Integer.valueOf(sdf.format(list.get(i).getCreationDate()).substring(0, 4));
					if (yearInt == createYear) {
						list.get(i).setCreationDateStr(sdf.format(list.get(i).getCreationDate()).substring(5, 10));
					} else {
						String timeString = sdf.format(list.get(i).getCreationDate()).substring(0, 10);
						list.get(i).setCreationDateStr(timeString);
					}
				}
			}
		}
		// 调休预警总数统计
		int total = leaveProcessService.leaveLeaderCompensatoryAlarmNewestCount(policeId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		dlr.setTotal(total);
		dlr.setPageNum(pageNums);
		dlr.setPageSize(pageSize);
		dlr.setTotalPage(((int) Math.ceil((double) total / (double) pageSize)));
		int count = ((int) Math.ceil((double) total / (double) pageSize));
		if (count - pageNums > 0) {
			dlr.setPageNext(1);
		} else {
			dlr.setPageNext(0);
		}
		return new ResponseEntity<DataListPage>(dlr, HttpStatus.OK);
	}

	// 局领导需强制调休人员详情查询
	@RequestMapping(value = "/leave/leader/compensatory/alarm/item", method = RequestMethod.GET)
	public ResponseEntity<?> leaveLeaderCompensatoryAlarmItem(@RequestParam(value = "id", required = false) Integer id)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 局领导需强制调休人员详情查询
		LeaveCompensatoryAlarm item = leaveProcessService.leaveLeaderCompensatoryAlarmItem(id);
		if (item != null) {
			if (item.getRuleId() == 1) {// 预警规则id(1每季度民警累计出差天数；2每月民警累计出差天数；3民警单次出差天数；4民警连续工作超3天；5民警连续工作天数超5天)
				item.setReason(item.getName() + "最近一季度已累计出差" + item.getWorkDays() + "天，建议强制调休");
			} else if (item.getRuleId() == 2) {
				item.setReason(item.getName() + "最近一月已累计出差" + item.getWorkDays() + "天，建议强制调休");
			} else if (item.getRuleId() == 3) {
				item.setReason(item.getName() + "单次出差" + item.getWorkDays() + "天，建议强制调休");
			} else if (item.getRuleId() == 4) {
				item.setReason(item.getName() + "最近已经连续工作" + item.getWorkDays() + "天，建议强制调休");
			} else if (item.getRuleId() == 5) {
				item.setReason(item.getName() + "最近已经连续工作" + item.getWorkDays() + "天，建议强制调休");
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 办案积分需调休人员查询(3条)
	@RequestMapping(value = "/leave/leader/points/alarm/newest/list", method = RequestMethod.GET)
	public ResponseEntity<?> leaveLeaderPointsAlarmNewestList(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		String currentYear = String.valueOf(cal.get(Calendar.YEAR));
		int halfYear = 0;
		if (month <= 6) {
			halfYear = 2;
			int yearInt = Integer.valueOf(currentYear) - 1;
			currentYear = String.valueOf(yearInt);
		} else if (month > 6) {
			halfYear = 1;
		}
		LeaveLeaderAlarmList itme = new LeaveLeaderAlarmList();
		// 办案积分预警(3条)查询
		List<LeaveLeaderAlarm> list = leaveProcessService.leaveLeaderPointsAlarmNewestList(policeId, currentYear,
				halfYear);
		list.removeAll(Collections.singleton(null));
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				// 个人积分可调休天数查询
				LeavePersonalStatistics count2 = leaveProcessService
						.leavePersonalpointsChangeDays(list.get(i).getPoliceId(), currentYear, halfYear);
				if (count2 != null) {
					double currentScore = count2.getPointsChangeDays();// 时长(天);
					String number = String.valueOf(currentScore);
					int index = number.indexOf(".");
					double douNumber1 = Double.valueOf(number.substring(0, index)) + 0.5;
					double douNumber2 = Double.valueOf(number.substring(0, index));
					if (douNumber2 == currentScore) {
						currentScore = douNumber2;
					} else {
						if (douNumber1 < currentScore) {
							currentScore = douNumber1;
						} else if (douNumber1 > currentScore) {
							currentScore = douNumber2;
						}
					}
					list.get(i).setDateTime(currentScore);
				} else {
					list.get(i).setDateTime(0);
				}
			}
		}
		itme.setAlarmList(list);
		// 办案积分需调休人员总数统计
		int count = leaveProcessService.leaveLeaderPointsAlarmNewestCount(policeId, currentYear, halfYear);
		itme.setNum(count);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(itme);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 办案积分需调休人员列表分页查询
	@RequestMapping(value = "/leave/leader/points/alarm/list", method = RequestMethod.GET)
	public ResponseEntity<?> leaveLeaderPointsAlarmList(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageNum", required = false) Integer pageNum) throws ApiException {
		DataListPage dlr = new DataListPage();
		String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		Integer pageNums = pageNum;
		if (pageSize == null) {
			pageSize = 10;
		}
		pageNum = ((pageNum) - 1) * pageSize;
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		int halfYear = 0;
		if (month <= 6) {
			halfYear = 2;
			int yearInt = Integer.valueOf(currentYear) - 1;
			currentYear = String.valueOf(yearInt);
		} else if (month > 6) {
			halfYear = 1;
		}
		// 办案积分预警分页查询
		List<LeaveLeaderAlarm> list = leaveProcessService.leaveLeaderPointsAlarmList(policeId, currentYear, halfYear,
				pageSize, pageNum);
		list.removeAll(Collections.singleton(null));
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				// 个人积分可调休天数查询
				LeavePersonalStatistics count2 = leaveProcessService
						.leavePersonalpointsChangeDays(list.get(i).getPoliceId(), currentYear, halfYear);
				if (count2 != null) {
					double currentScore = count2.getPointsChangeDays();// 时长(天);
					String number = String.valueOf(currentScore);
					int index = number.indexOf(".");
					double douNumber1 = Double.valueOf(number.substring(0, index)) + 0.5;
					double douNumber2 = Double.valueOf(number.substring(0, index));
					if (douNumber2 == currentScore) {
						currentScore = douNumber2;
					} else {
						if (douNumber1 < currentScore) {
							currentScore = douNumber1;
						} else if (douNumber1 > currentScore) {
							currentScore = douNumber2;
						}
					}
					list.get(i).setDateTime(currentScore);
				} else {
					list.get(i).setDateTime(0);
				}
			}
		}
		// 办案积分预警总数统计
		int total = leaveProcessService.leaveLeaderPointsAlarmNewestCount(policeId, currentYear, halfYear);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		dlr.setTotal(total);
		dlr.setPageNum(pageNums);
		dlr.setPageSize(pageSize);
		dlr.setTotalPage(((int) Math.ceil((double) total / (double) pageSize)));
		int count = ((int) Math.ceil((double) total / (double) pageSize));
		if (count - pageNums > 0) {
			dlr.setPageNext(1);
		} else {
			dlr.setPageNext(0);
		}
		return new ResponseEntity<DataListPage>(dlr, HttpStatus.OK);
	}

	// 加班需调休人员(3条)
	@RequestMapping(value = "/leave/leader/overtime/alarm/newest/list", method = RequestMethod.GET)
	public ResponseEntity<?> leaveLeaderOvertimeAlarmNewestList(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		LeaveLeaderAlarmList itme = new LeaveLeaderAlarmList();
		// 加班预警(3条)查询
		List<LeaveLeaderAlarm> list = leaveProcessService.leaveLeaderOvertimeAlarmNewestList(policeId);
		list.removeAll(Collections.singleton(null));
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				// 个人加班可调休天数查询
				LeavePersonalStatistics count = leaveProcessService
						.leavePersonalOverTimeChangeDays(list.get(i).getPoliceId(), currentYear);
				if (count != null) {
					double currentScore = count.getOvertimeChangeDays();// 时长(天);
					String number = String.valueOf(currentScore);
					int index = number.indexOf(".");
					double douNumber1 = Double.valueOf(number.substring(0, index)) + 0.5;
					double douNumber2 = Double.valueOf(number.substring(0, index));
					if (douNumber2 == currentScore) {
						currentScore = douNumber2;
					} else {
						if (douNumber1 < currentScore) {
							currentScore = douNumber1;
						} else if (douNumber1 > currentScore) {
							currentScore = douNumber2;
						}
					}
					list.get(i).setDateTime(currentScore);
				} else {
					list.get(i).setDateTime(0);
				}
			}
		}
		itme.setAlarmList(list);
		// 加班总数统计
		int count = leaveProcessService.leaveLeaderOvertimeAlarmNewestCount(policeId);
		itme.setNum(count);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(itme);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 加班需调休人员分页列表查询
	@RequestMapping(value = "/leave/leader/overtime/alarm/list", method = RequestMethod.GET)
	public ResponseEntity<?> leaveLeaderOvertimeAlarmList(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageNum", required = false) Integer pageNum) throws ApiException {
		DataListPage dlr = new DataListPage();
		String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		Integer pageNums = pageNum;
		if (pageSize == null) {
			pageSize = 10;
		}
		pageNum = ((pageNum) - 1) * pageSize;
		// 加班预警分页查询
		List<LeaveLeaderAlarm> list = leaveProcessService.leaveLeaderOvertimeAlarmList(policeId, pageSize, pageNum);
		list.removeAll(Collections.singleton(null));
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				// 个人加班可调休天数查询
				LeavePersonalStatistics count = leaveProcessService
						.leavePersonalOverTimeChangeDays(list.get(i).getPoliceId(), currentYear);
				if (count != null) {
					double currentScore = count.getOvertimeChangeDays();// 时长(天);
					String number = String.valueOf(currentScore);
					int index = number.indexOf(".");
					double douNumber1 = Double.valueOf(number.substring(0, index)) + 0.5;
					double douNumber2 = Double.valueOf(number.substring(0, index));
					if (douNumber2 == currentScore) {
						currentScore = douNumber2;
					} else {
						if (douNumber1 < currentScore) {
							currentScore = douNumber1;
						} else if (douNumber1 > currentScore) {
							currentScore = douNumber2;
						}
					}
					list.get(i).setDateTime(currentScore);
				} else {
					list.get(i).setDateTime(0);
				}
			}
		}
		// 加班预警总数统计
		int total = leaveProcessService.leaveLeaderOvertimeAlarmNewestCount(policeId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		dlr.setTotal(total);
		dlr.setPageNum(pageNums);
		dlr.setPageSize(pageSize);
		dlr.setTotalPage(((int) Math.ceil((double) total / (double) pageSize)));
		int count = ((int) Math.ceil((double) total / (double) pageSize));
		if (count - pageNums > 0) {
			dlr.setPageNext(1);
		} else {
			dlr.setPageNext(0);
		}
		return new ResponseEntity<DataListPage>(dlr, HttpStatus.OK);
	}

	// 待办事项查询(2条)
	@RequestMapping(value = "/leave/leader/need/dealt/newest/list", method = RequestMethod.GET)
	public ResponseEntity<?> leaveLeaderNeedDealtNewestList(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		LeaveLeaderAlarmList itme = new LeaveLeaderAlarmList();
		// 待办事项查询(2条)
		List<LeaveCompensatoryRecord> list = leaveProcessService.leaveLeaderNeedDealtNewestList(policeId);
		list.removeAll(Collections.singleton(null));
		for (int i = 0; i < list.size(); i++) {
			int timeItem = list.get(i).getTimeChange();
			if (timeItem == 0) {
				list.get(i).setCreationDateStr("1分钟前");
			} else if (timeItem < 60 && timeItem > 0) {
				list.get(i).setCreationDateStr(timeItem + "分钟前");
			} else if (timeItem >= 60 && timeItem < 1440) {
				int timeInt = (int) Math.floor(timeItem / 60);
				list.get(i).setCreationDateStr(timeInt + "小时前");
			} else if (timeItem >= 1440 && timeItem < 4320) {
				int timeInt = (int) Math.floor(timeItem / 60 / 24);
				list.get(i).setCreationDateStr(timeInt + "天前");
			} else if (timeItem >= 4320) {
				int yearInt = Integer.valueOf(currentYear);
				int createYear = Integer.valueOf(sdf.format(list.get(i).getCreationDate()).substring(0, 4));
				if (yearInt == createYear) {
					list.get(i).setCreationDateStr(sdfs.format(list.get(i).getCreationDate()).substring(5, 11));
				} else {
					String timeString = sdfs.format(list.get(i).getCreationDate());
					list.get(i).setCreationDateStr(timeString);
				}
			}
		}
		itme.setNeedDealtList(list);
		// 待办事项总数统计
		int count = leaveProcessService.leaveLeaderNeedDealtNewestCount(policeId);
		itme.setNum(count);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(itme);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 待办事项分页列表查询
	@RequestMapping(value = "/leave/leader/need/dealt/list", method = RequestMethod.GET)
	public ResponseEntity<?> leaveLeaderNeedDealtList(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "objectType", required = false) Integer objectType,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "dateTime", required = false) String dateTime,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageNum", required = false) Integer pageNum) throws ApiException {
		DataListPage dlr = new DataListPage();
		String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		if (StringUtils.isEmpty(dateTime)) {
			dateTime = sdf.format(new Date());
		}
		dateTime = dateTime.substring(0, 7);
		Integer pageNums = pageNum;
		if (pageSize == null) {
			pageSize = 10;
		}
		pageNum = ((pageNum) - 1) * pageSize;
		List<LeaveCompensatoryRecord> list = new ArrayList<LeaveCompensatoryRecord>();
		int total = 0;
		if (objectType == null || objectType == 1) {
			// 待办事项分页查询
			list = leaveProcessService.leaveLeaderNeedDealtList(policeId, type, dateTime, pageSize, pageNum);
			// 待办事项总数统计
			total = leaveProcessService.leaveLeaderNeedDealtCount(policeId, type, dateTime);
		} else if (objectType == 2) {
			// 已办事项分页查询
			list = leaveProcessService.leaveLeaderOverDealtList(policeId, type, dateTime, pageSize, pageNum);
			// 已办事项总数统计
			total = leaveProcessService.leaveLeaderOverDealtCount(policeId, type, dateTime);
		}
		for (int i = 0; i < list.size(); i++) {
			int timeItem = list.get(i).getTimeChange();
			if (timeItem == 0) {
				list.get(i).setCreationDateStr("1分钟前");
			} else if (timeItem < 60 && timeItem > 0) {
				list.get(i).setCreationDateStr(timeItem + "分钟前");
			} else if (timeItem >= 60 && timeItem < 1440) {
				int timeInt = (int) Math.floor(timeItem / 60);
				list.get(i).setCreationDateStr(timeInt + "小时前");
			} else if (timeItem >= 1440 && timeItem < 4320) {
				int timeInt = (int) Math.floor(timeItem / 60 / 24);
				list.get(i).setCreationDateStr(timeInt + "天前");
			} else if (timeItem >= 4320) {
				int yearInt = Integer.valueOf(currentYear);
				int createYear = Integer.valueOf(sdf.format(list.get(i).getCreationDate()).substring(0, 4));
				if (yearInt == createYear) {
					list.get(i).setCreationDateStr(sdfs.format(list.get(i).getCreationDate()).substring(5, 11));
				} else {
					String timeString = sdfs.format(list.get(i).getCreationDate());
					list.get(i).setCreationDateStr(timeString);
				}
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		dlr.setTotal(total);
		dlr.setPageNum(pageNums);
		dlr.setPageSize(pageSize);
		dlr.setTotalPage(((int) Math.ceil((double) total / (double) pageSize)));
		int count = ((int) Math.ceil((double) total / (double) pageSize));
		if (count - pageNums > 0) {
			dlr.setPageNext(1);
		} else {
			dlr.setPageNext(0);
		}
		return new ResponseEntity<DataListPage>(dlr, HttpStatus.OK);
	}

	// 局领导新增调休记录
	@ResponseBody
	@RequestMapping(value = "/leave/leader/need/dealt/creat", method = RequestMethod.POST)
	public DataListReturn leaveLeaderNeedDealtCreat(@RequestParam(value = "type", required = true) Integer type,
			@RequestParam(value = "itemId", required = false) Integer itemId,
			@RequestParam(value = "policeId", required = true) String policeId,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "approvedId", required = true) String approvedId,
			@RequestParam(value = "remarks", required = false) String remarks,
			@RequestParam(value = "isRest", required = true) Integer isRest,
			@RequestParam(value = "status", required = false) Integer status) throws IOException, ParseException {
		DataListReturn dlr = new DataListReturn();
		LeaveCompensatoryRecord item = new LeaveCompensatoryRecord();
		item.setPoliceId(policeId);
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		item.setType(1);
		if (user != null) {
			item.setDepartmentId(user.getDepartmentId());
			item.setPositionId(user.getPositionId());
		}
		item.setRemarks(remarks);
		if (isRest == null || isRest == 0) {
			item.setIsRest(0);
			item.setStatus(3);
		} else {
			item.setStartTime(sdf.parse(startTime));
			item.setEndTime(sdf.parse(endTime));
			item.setIsRest(1);
			item.setStatus(2);
			long currentTimeLong = new Date().getTime();
			long startTimeLong = sdf.parse(startTime).getTime();
			long endTimeLong = sdf.parse(endTime).getTime();
			if (startTimeLong < currentTimeLong) {
				dlr.setStatus(false);
				dlr.setMessage("开始时间不能小于当前时间");
				dlr.setResult(0);
				dlr.setCode(StatusCode.getFailcode());
				return dlr;
			}
			if (startTimeLong > endTimeLong) {
				dlr.setStatus(false);
				dlr.setMessage("开始时间不能大于结束时间");
				dlr.setResult(0);
				dlr.setCode(StatusCode.getFailcode());
				return dlr;
			}
		}
		item.setItemId(itemId);
		if (isRest == 0) {
			item.setResult("通知您不可进行强制调休");
			item.setApprovedResult("不可进行强制调休");
		} else if (isRest == 1) {
			item.setResult("通知您强制调休");
			item.setApprovedResult("进行强制调休");
		}
		item.setApprovedId(approvedId);
		item.setReadStatus(0);
		item.setApprovedReadStatus(0);
		item.setDealTime(new Date());
		item.setCreationDate(new Date());
		int count = leaveProcessService.leaveCompensatoryRecordCreat(item);
		if (count > 0) {
			LeaveCompensatoryRecord recordItem = leaveProcessService.leaveCompensatoryRecordItem(item.getId());
			// 局领导需强制调休人员详情查询
			LeaveCompensatoryAlarm aItem = leaveProcessService.leaveLeaderCompensatoryAlarmItem(itemId);
			if (aItem != null) {
				LeaveCompensatoryAlarm comItem = new LeaveCompensatoryAlarm();
				comItem.setId(itemId);
				comItem.setIsDeal(1);
				comItem.setUpdateDate(new Date());
				leaveProcessService.leaveCompensatoryAlarmUpdate(comItem);
				if (aItem.getRuleId() == 1) {// 预警规则id(1每季度民警累计出差天数；2每月民警累计出差天数；3民警单次出差天数；4民警连续工作超3天；5民警连续工作天数超5天)
					recordItem.setLeaveReason(aItem.getName() + "最近一季度已累计出差" + aItem.getWorkDays() + "天，建议强制调休");
				} else if (aItem.getRuleId() == 2) {
					recordItem.setLeaveReason(aItem.getName() + "最近一月已累计出差" + aItem.getWorkDays() + "天，建议强制调休");
				} else if (aItem.getRuleId() == 3) {
					recordItem.setLeaveReason(aItem.getName() + "单次出差" + aItem.getWorkDays() + "天，建议强制调休");
				} else if (aItem.getRuleId() == 4) {
					recordItem.setLeaveReason(aItem.getName() + "最近已经连续工作" + aItem.getWorkDays() + "天，建议强制调休");
				} else if (aItem.getRuleId() == 5) {
					recordItem.setLeaveReason(aItem.getName() + "最近已经连续工作" + aItem.getWorkDays() + "天，建议强制调休");
				}
			}
			if (isRest == 0) {
				recordItem.setResult("无需安排调休");
			} else if (isRest == 1) {
				recordItem.setResult("已安排调休");
				double times = recordItem.getTimeChange();
				double currentScore = times / 1440.0;// 时长(天);
				String number = String.valueOf(currentScore);
				int index = number.indexOf(".");
				double douNumber1 = Double.valueOf(number.substring(0, index)) + 0.5;
				double douNumber2 = Double.valueOf(number.substring(0, index));
				if (douNumber2 == currentScore) {
					currentScore = douNumber2;
				} else {
					if (douNumber1 < currentScore) {
						currentScore = douNumber1;
					} else if (douNumber1 > currentScore) {
						currentScore = douNumber2;
					}
				}
				recordItem.setLeaveDays(currentScore);
			}
			// 查询当前民警所属领导
			List<LeavePower> pList = leaveProcessService.LeavePowerPoliceList(policeId);
			if (pList.size() > 0) {
				for (int i = 0; i < pList.size(); i++) {
					LeaveCompensatoryReadRecord rrItem = new LeaveCompensatoryReadRecord();
					rrItem.setRecordId(item.getId());
					rrItem.setPoliceId(policeId);
					rrItem.setDepartmentId(item.getDepartmentId());
					rrItem.setPositionId(item.getPositionId());
					rrItem.setApprovedId(pList.get(i).getCheckerId());
					rrItem.setApprovedReadStatus(0);
					rrItem.setCreationDate(new Date());
					leaveProcessService.leaveCompensatoryReadRecordCreat(rrItem);
				}
			}
			dlr.setStatus(true);
			dlr.setMessage("新增成功");
			dlr.setResult(recordItem);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("新增失败");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
		}
		return dlr;
	}

	// 局领导修改调休记录
	@ResponseBody
	@RequestMapping(value = "/leave/leader/need/dealt/update", method = RequestMethod.POST)
	public DataListReturn leaveLeaderNeedDealtUpdate(@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "approvedId", required = false) String approvedId,
			@RequestParam(value = "remarks", required = false) String remarks,
			@RequestParam(value = "isRest", required = false) Integer isRest,
			@RequestParam(value = "status", required = false) Integer status) throws IOException, ParseException {
		DataListReturn dlr = new DataListReturn();
		LeaveCompensatoryRecord item = new LeaveCompensatoryRecord();
		item.setId(id);
		item.setPoliceId(policeId);
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		item.setType(type);
		if (user != null) {
			item.setDepartmentId(user.getDepartmentId());
			item.setPositionId(user.getPositionId());
		}
		item.setRemarks(remarks);
		if (isRest == null || isRest == 0) {
			item.setIsRest(0);
			item.setStatus(3);
			item.setStartTime(null);
			item.setEndTime(null);
		} else {
			LeaveCompensatoryRecord recordItem = leaveProcessService.leaveCompensatoryRecordItem(id);
			item.setIsRest(1);
			item.setStatus(2);
			item.setStartTime(sdf.parse(startTime));
			item.setEndTime(sdf.parse(endTime));
			long currentTimeLong = new Date().getTime();
			long startTimeLong = sdf.parse(startTime).getTime();
			long endTimeLong = sdf.parse(endTime).getTime();
			if (recordItem != null) {
				if (recordItem.getStartTime() != null) {
					long startTimeOriginal = recordItem.getStartTime().getTime();
					long endTimeOriginal = recordItem.getEndTime().getTime();
					if (startTimeOriginal != startTimeLong || endTimeLong != endTimeOriginal) {
						if (startTimeLong < currentTimeLong) {
							dlr.setStatus(false);
							dlr.setMessage("开始时间不能小于当前时间");
							dlr.setResult(0);
							dlr.setCode(StatusCode.getFailcode());
							return dlr;
						}
						if (startTimeLong > endTimeLong) {
							dlr.setStatus(false);
							dlr.setMessage("开始时间不能大于结束时间");
							dlr.setResult(0);
							dlr.setCode(StatusCode.getFailcode());
							return dlr;
						}
					}
				} else {
					if (startTimeLong < currentTimeLong) {
						dlr.setStatus(false);
						dlr.setMessage("开始时间不能小于当前时间");
						dlr.setResult(0);
						dlr.setCode(StatusCode.getFailcode());
						return dlr;
					}
					if (startTimeLong > endTimeLong) {
						dlr.setStatus(false);
						dlr.setMessage("开始时间不能大于结束时间");
						dlr.setResult(0);
						dlr.setCode(StatusCode.getFailcode());
						return dlr;
					}
				}
			}
		}
		item.setApprovedId(approvedId);
		item.setUpdateDate(new Date());
		int count = leaveProcessService.leaveCompensatoryRecordUpdate(item);
		if (count > 0) {
			dlr.setStatus(true);
			dlr.setMessage("修改成功");
			dlr.setResult(count);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("修改失败");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
		}
		return dlr;
	}

	// 局领导待办事项修改状态
	@ResponseBody
	@RequestMapping(value = "/leave/leader/need/dealt/status/update", method = RequestMethod.POST)
	public DataListReturn leaveLeaderNeedDealtStatusUpdate(@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "type", required = true) Integer type,
			@RequestParam(value = "status", required = true) Integer status,
			@RequestParam(value = "policeId", required = true) String policeId,
			@RequestParam(value = "remarks", required = false) String remarks) throws IOException, ParseException {
		DataListReturn dlr = new DataListReturn();
		String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		int halfYear = 0;
		if (month <= 6) {
			halfYear = 2;
			int yearInt = Integer.valueOf(currentYear) - 1;
			currentYear = String.valueOf(yearInt);
		} else if (month > 6) {
			halfYear = 1;
		}
		LeaveCompensatoryRecord item = new LeaveCompensatoryRecord();
		// 调休记录详情查询
		LeaveCompensatoryRecord recordItem = leaveProcessService.leaveCompensatoryRecordItem(id);
		item.setStartTime(recordItem.getStartTime());
		item.setEndTime(recordItem.getEndTime());
		long startTimeLong = recordItem.getStartTime().getTime();
		long endTimeLong = recordItem.getEndTime().getTime();
		if (status == 2) {
			item.setIsRest(1);
			if (recordItem != null) {
				if (type == 3) {
					// 个人积分查询
					LeavePersonalStatistics pItem = leaveProcessService
							.leavePersonalPointsItems(recordItem.getPoliceId(), currentYear, halfYear);
					// 个人积分兑换记录详情查询
					LeavePointsExchangeRecord eItem = leaveProcessService
							.leavePointsExchangeRecordItem(recordItem.getItemId());
					LeavePoints lItem = new LeavePoints();
					lItem.setPoliceId(recordItem.getPoliceId());
					lItem.setYear(currentYear);
					lItem.setHalfYear(halfYear);
					lItem.setResidualPoints(pItem.getResidualPoints() + eItem.getPoints());
					lItem.setUpdateDate(new Date());
					leaveProcessService.leavePointsResidualUpdate(lItem);// 修改个人剩余积分
					LeavePointsExchangeRecord record = new LeavePointsExchangeRecord();
					record.setId(recordItem.getItemId());
					record.setStatus(3);
					record.setUpdateDate(new Date());
					leaveProcessService.leavePointsExchangeRecordUpdate(record);
				} else if (type == 2) {
					// 获得加班兑换规则数据
					List<LeaveOvertimeRule> ruleList = leaveProcessService.getLeaveOvertimeRuleList();
					double ruleHour = ruleList.get(0).getHour();// 累计加班时长6
					double ruleComDay = ruleList.get(0).getDay1();// 可调休天数0.5
					double endTimeDou = endTimeLong;
					double startTimeDou = startTimeLong;
					double currentScore = (endTimeDou - startTimeDou) / 86400000.00;// 时长(天);
					String number = String.valueOf(currentScore);
					int index = number.indexOf(".");
					double douNumber1 = Double.valueOf(number.substring(0, index)) + 0.5;
					double douNumber2 = Double.valueOf(number.substring(0, index));
					if (douNumber2 == currentScore) {
						currentScore = douNumber2;
					} else {
						if (douNumber1 > currentScore) {
							currentScore = douNumber1;
						} else if (douNumber1 < currentScore) {
							currentScore = douNumber2 + 1;
						}
					}
					double reduceTimes = currentScore / ruleComDay * ruleHour;
					// 个人加班时长查询
					LeavePersonalStatistics pItem = leaveProcessService
							.leavePersonalOvertimeItem(recordItem.getPoliceId());
					LeaveOvertime tItem = new LeaveOvertime();
					tItem.setPoliceId(recordItem.getPoliceId());
					tItem.setYear(currentYear);
					tItem.setResidualOvertime(pItem.getResidualOvertime() - reduceTimes);
					tItem.setUpdateDate(new Date());
					leaveProcessService.leaveOvertimeYearUpdate(tItem);
				}
			}
		} else if (status == 3) {
			item.setIsRest(0);
			if (type == 3) {
				LeavePointsExchangeRecord record = new LeavePointsExchangeRecord();
				record.setId(recordItem.getItemId());
				record.setStatus(2);
				record.setUpdateDate(new Date());
				leaveProcessService.leavePointsExchangeRecordUpdate(record);
			}
		}
		item.setId(id);
		item.setType(type);
		item.setRemarks(remarks);
		item.setStatus(status);
		item.setApprovedId(policeId);
		item.setUpdateDate(new Date());
		int count = leaveProcessService.leaveCompensatoryRecordUpdate(item);
		if (count > 0) {
			dlr.setStatus(true);
			dlr.setMessage("修改成功");
			dlr.setResult(count);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("修改失败");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
		}
		return dlr;
	}

	// 单位年休假达标概况
	@RequestMapping(value = "/leave/dep/annual/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> leaveDepAnnualStatistics(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		LeaveAlarmLeaderStatistics item = new LeaveAlarmLeaderStatistics();
		// 单位年休假达标数量统计
		int depAnnualLeaveNum = leaveProcessService.depAnnualLeaveNum();
		item.setAnnualLeaveNum(depAnnualLeaveNum);
		// 有年休假的单位数量统计
		int totalDepAnnualLeaveNum = leaveProcessService.totalDepAnnualLeaveNum();
		item.setNotAnnualLeaveNum(totalDepAnnualLeaveNum - depAnnualLeaveNum);
		double totalNum = totalDepAnnualLeaveNum;
		double annualLeaveNumDou = depAnnualLeaveNum;
		double notAnnualLeaveNum = item.getNotAnnualLeaveNum();
		item.setAnnualLeaveRate(Double.valueOf(df.format(annualLeaveNumDou / totalNum * 100)));
		item.setNotAnnualLeaveRate(Double.valueOf(df.format(notAnnualLeaveNum / totalNum * 100)));
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 单位年休假未达标概况
	@RequestMapping(value = "/leave/dep/annual/not/standard/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> leaveDepAnnualNotStandardStatistics(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		LeaveAlarmLeaderStatistics item = new LeaveAlarmLeaderStatistics();
		// 单位年休假达标数量统计
		int depAnnualLeaveNum = leaveProcessService.depAnnualLeaveNum();
		item.setAnnualLeaveNum(depAnnualLeaveNum);
		// 有年休假的单位数量统计
		int totalDepAnnualLeaveNum = leaveProcessService.totalDepAnnualLeaveNum();
		item.setNotAnnualLeaveNum(totalDepAnnualLeaveNum - depAnnualLeaveNum);
		double totalNum = totalDepAnnualLeaveNum;
		double annualLeaveNumDou = depAnnualLeaveNum;
		double notAnnualLeaveNum = item.getNotAnnualLeaveNum();
		item.setAnnualLeaveRate(Double.valueOf(df.format(annualLeaveNumDou / totalNum * 100)));
		item.setNotAnnualLeaveRate(Double.valueOf(df.format(notAnnualLeaveNum / totalNum * 100)));
		// 各单位年休假率查询
		List<LeaveChart> list = leaveProcessService.leaveDepAnnualNotStandardStatistics();
		item.setDepList(list);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 各单位疗休养人员
	@RequestMapping(value = "/leave/dep/train/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> leaveDepTrainStatistics(@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		LeaveTrainStatistics item = new LeaveTrainStatistics();
		// 各单位疗休养人员数量统计(立功受奖优先安排人数)类型(1连续三年未休养2立功受奖优先安排人数)
		int count = leaveProcessService.leaveDepTrainStatisticsNum(2, departmentId);
		// 各单位疗休养人员数量统计(连续三年未休养人数)
		int count2 = leaveProcessService.leaveDepTrainStatisticsNum(1, departmentId);
		// 中层领导查看自己部门疗休养人员数据
		List<LeaveTrain> list = leaveProcessService.leaveDepTrainStatisticsList(type, policeId);
		item.setAwardsLeaveNum(count);
		item.setContinuousNotLeaveNum(count2);
		item.setLeaveTrainList(list);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 各单位需调休人数趋势图
	@RequestMapping(value = "/leave/dep/compensatory/chart", method = RequestMethod.GET)
	public ResponseEntity<?> leaveDepCompensatoryChart() throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 各单位需调休人数查询
		List<CalculationChart> list = leaveProcessService.leaveDepCompensatoryChart();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 各单位积分未兑换人数趋势图
	@RequestMapping(value = "/leave/dep/points/exchange/chart", method = RequestMethod.GET)
	public ResponseEntity<?> leaveDepPointsExchangeChart() throws ApiException {
		DataListReturn dlr = new DataListReturn();
		String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		int halfYear = 0;
		if (month <= 6) {
			halfYear = 2;
			int yearInt = Integer.valueOf(currentYear) - 1;
			currentYear = String.valueOf(yearInt);
		} else if (month > 6) {
			halfYear = 1;
		}
		// 各单位积分未兑换人数查询
		List<CalculationChart> list = leaveProcessService.leaveDepPointsExchangeChart(currentYear, halfYear);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// -----------------AI休假(我的)api-----------------------
	// 个人休假情况统计
	@RequestMapping(value = "/leave/personal/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> leavePersonalStatistics(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		int halfYear = 0;
		if (month <= 6) {
			halfYear = 2;
			int yearInt = Integer.valueOf(currentYear) - 1;
			currentYear = String.valueOf(yearInt);
		} else if (month > 6) {
			halfYear = 1;
		}
		// 个人休假情况统计（api）
		LeavePersonalStatistics statisticsItem = leaveProcessService.leavePersonalStatisticsItem(policeId,
				currentYear, halfYear);
		// 个人加班可调休天数查询
		LeavePersonalStatistics count = leaveProcessService.leavePersonalOverTimeChangeDays(policeId, currentYear);
		if (count != null) {
			double currentScore = count.getOvertimeChangeDays();// 时长(天);
			String number = String.valueOf(currentScore);
			int index = number.indexOf(".");
			double douNumber1 = Double.valueOf(number.substring(0, index)) + 0.5;
			double douNumber2 = Double.valueOf(number.substring(0, index));
			if (douNumber2 == currentScore) {
				currentScore = douNumber2;
			} else {
				if (douNumber1 < currentScore) {
					currentScore = douNumber1;
				} else if (douNumber1 > currentScore) {
					currentScore = douNumber2;
				}
			}
			statisticsItem.setOvertimeChangeDays(currentScore);
		} else {
			statisticsItem.setOvertimeChangeDays(0);
		}
		// 个人积分可调休天数查询
		LeavePersonalStatistics count2 = leaveProcessService.leavePersonalpointsChangeDays(policeId, currentYear,
				halfYear);
		if (count2 != null) {
			double currentScore = count2.getPointsChangeDays();// 时长(天);
			String number = String.valueOf(currentScore);
			int index = number.indexOf(".");
			double douNumber1 = Double.valueOf(number.substring(0, index)) + 0.5;
			double douNumber2 = Double.valueOf(number.substring(0, index));
			if (douNumber2 == currentScore) {
				currentScore = douNumber2;
			} else {
				if (douNumber1 < currentScore) {
					currentScore = douNumber1;
				} else if (douNumber1 > currentScore) {
					currentScore = douNumber2;
				}
			}
			statisticsItem.setPointsChangeDays(currentScore);
		} else {
			statisticsItem.setPointsChangeDays(0);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(statisticsItem);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人当前年情况统计
	@RequestMapping(value = "/leave/personal/this/year/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> leavePersonalThisYearStatistics(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		String currentYear = String.valueOf(cal.get(Calendar.YEAR));
		int halfYear = 0;
		if (month <= 6) {
			halfYear = 2;
			int yearInt = Integer.valueOf(currentYear) - 1;
			currentYear = String.valueOf(yearInt);
		} else if (month > 6) {
			halfYear = 1;
		}
		// 个人当前年情况统计
		LeaveThisYearStatistics yearItem = leaveProcessService.leaveThisYearStatisticsItem(policeId);
		// 个人休假情况统计（api）
		LeavePersonalStatistics statisticsItem = leaveProcessService.leavePersonalStatisticsItem(policeId,
				currentYear, halfYear);
		yearItem.setTotalPoints(statisticsItem.getPoints());
		yearItem.setResidualPoints(statisticsItem.getResidualPoints());
		// 个人积分可调休天数查询
		LeavePersonalStatistics count2 = leaveProcessService.leavePersonalpointsChangeDays(policeId, currentYear,
				halfYear);
		if (count2 != null) {
			double currentScore = count2.getPointsChangeDays();// 时长(天);
			String number = String.valueOf(currentScore);
			int index = number.indexOf(".");
			double douNumber1 = Double.valueOf(number.substring(0, index)) + 0.5;
			double douNumber2 = Double.valueOf(number.substring(0, index));
			if (douNumber2 == currentScore) {
				currentScore = douNumber2;
			} else {
				if (douNumber1 < currentScore) {
					currentScore = douNumber1;
				} else if (douNumber1 > currentScore) {
					currentScore = douNumber2;
				}
			}
			yearItem.setPointsChangeDays(currentScore);
		} else {
			yearItem.setPointsChangeDays(0);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(yearItem);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人最新强制调休提醒记录查询(1条)
	@RequestMapping(value = "/leave/personal/compensatory/record/newest/list", method = RequestMethod.GET)
	public ResponseEntity<?> leavePersonalCompensatoryRecordNewestList(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		LeaveCompensatoryRecordStatistics recordItme = new LeaveCompensatoryRecordStatistics();
		// 个人最新调休记录查询
		List<LeaveCompensatoryRecord> list = leaveProcessService.leaveCompensatoryRecordNewestList(policeId, 0);
		// 个人调休记录总数统计
		int total = leaveProcessService.leaveCompensatoryRecordCount(policeId, null, null, 0);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int timeItem = Math.abs(list.get(i).getTimeChange());
				if (timeItem == 0) {
					list.get(i).setCreationDateStr("1分钟前");
				} else if (timeItem < 60 && timeItem > 0) {
					list.get(i).setCreationDateStr(timeItem + "分钟前");
				} else if (timeItem >= 60 && timeItem < 1440) {
					int timeInt = (int) Math.floor(timeItem / 60);
					list.get(i).setCreationDateStr(timeInt + "小时前");
				} else if (timeItem >= 1440 && timeItem <= 4320) {
					int timeInt = (int) Math.floor(timeItem / 60 / 24);
					list.get(i).setCreationDateStr(timeInt + "天前");
				} else if (timeItem > 4320) {
					int yearInt = Integer.valueOf(currentYear);
					int createYear = Integer.valueOf(sdf.format(list.get(i).getCreationDate()).substring(0, 4));
					if (yearInt == createYear) {
						list.get(i).setCreationDateStr(sdfs.format(list.get(i).getCreationDate()).substring(5, 11));
					} else {
						String timeString = sdfs.format(list.get(i).getCreationDate());
						list.get(i).setCreationDateStr(timeString);
					}
				}
				LeaveCompensatoryRecord rItem = new LeaveCompensatoryRecord();
				rItem.setId(list.get(i).getId());
				rItem.setType(list.get(i).getType());
				rItem.setPoliceId(policeId);
				rItem.setReadStatus(1);
				rItem.setUpdateDate(new Date());
				leaveProcessService.leaveReadUpdate(rItem);
				list.get(i).setReadStatus(rItem.getReadStatus());
				recordItme.setList(list);
			}
		}
		if (total > 0) {
			recordItme.setIsRead(1);
		} else {
			recordItme.setIsRead(0);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(recordItme);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人强制调休提醒记录查询
	@RequestMapping(value = "/leave/personal/compensatory/record/list", method = RequestMethod.GET)
	public ResponseEntity<?> leavePersonalCompensatoryRecordList(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "dateTime", required = false) String dateTime,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageNum", required = false) Integer pageNum) throws ApiException {
		DataListPage dlr = new DataListPage();
		String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
//		if (StringUtils.isEmpty(dateTime)) {
//			dateTime = sdf.format(new Date());
//		}
//		dateTime = dateTime.substring(0, 7);
		Integer pageNums = pageNum;
		if (pageSize == null) {
			pageSize = 10;
		}
		pageNum = ((pageNum) - 1) * pageSize;
		// 个人调休记录查询
		List<LeaveCompensatoryRecord> list = leaveProcessService.leaveCompensatoryRecordList(policeId, type, dateTime,
				null, pageSize, pageNum);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int timeItem = Math.abs(list.get(i).getTimeChange());
				if (timeItem == 0) {
					list.get(i).setCreationDateStr("1分钟前");
				} else if (timeItem < 60 && timeItem > 0) {
					list.get(i).setCreationDateStr(timeItem + "分钟前");
				} else if (timeItem >= 60 && timeItem < 1440) {
					int timeInt = (int) Math.floor(timeItem / 60);
					list.get(i).setCreationDateStr(timeInt + "小时前");
				} else if (timeItem >= 1440 && timeItem <= 4320) {
					int timeInt = (int) Math.floor(timeItem / 60 / 24);
					list.get(i).setCreationDateStr(timeInt + "天前");
				} else if (timeItem > 4320) {
					int yearInt = Integer.valueOf(currentYear);
					int createYear = Integer.valueOf(sdf.format(list.get(i).getCreationDate()).substring(0, 4));
					if (yearInt == createYear) {
						list.get(i).setCreationDateStr(sdfs.format(list.get(i).getCreationDate()).substring(5, 11));
					} else {
						String timeString = sdfs.format(list.get(i).getCreationDate());
						list.get(i).setCreationDateStr(timeString);
					}
				}
				LeaveCompensatoryRecord rItem = new LeaveCompensatoryRecord();
				rItem.setId(list.get(i).getId());
				rItem.setType(list.get(i).getType());
				rItem.setPoliceId(policeId);
				rItem.setReadStatus(1);
				rItem.setUpdateDate(new Date());
				leaveProcessService.leaveReadUpdate(rItem);
				list.get(i).setReadStatus(rItem.getReadStatus());
			}
		}
		// 个人调休记录总数统计
		int total = leaveProcessService.leaveCompensatoryRecordCount(policeId, type, dateTime, null);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		dlr.setTotal(total);
		dlr.setPageNum(pageNums);
		dlr.setPageSize(pageSize);
		dlr.setTotalPage(((int) Math.ceil((double) total / (double) pageSize)));
		int count = ((int) Math.ceil((double) total / (double) pageSize));
		if (count - pageNums > 0) {
			dlr.setPageNext(1);
		} else {
			dlr.setPageNext(0);
		}
		return new ResponseEntity<DataListPage>(dlr, HttpStatus.OK);
	}

	// 个人调休记录详情查询
	@RequestMapping(value = "/leave/personal/compensatory/record/Item", method = RequestMethod.GET)
	public ResponseEntity<?> leaveCompensatoryRecordItem(@RequestParam(value = "id", required = false) Integer id)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 个人调休记录详情查询
		LeaveCompensatoryRecord item = leaveProcessService.leaveCompensatoryRecordItem(id);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 新增个人调休记录
	@ResponseBody
	@RequestMapping(value = "/leave/personal/compensatory/record/creat", method = RequestMethod.POST)
	public DataListReturn leaveCompensatoryRecordCreat(@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "startTime", required = true) String startTime,
			@RequestParam(value = "endTime", required = true) String endTime,
			@RequestParam(value = "remarks", required = false) String remarks,
			@RequestParam(value = "status", required = false) Integer status) throws IOException, ParseException {
		DataListReturn dlr = new DataListReturn();
		int count = 0;
		LeaveCompensatoryRecord item = new LeaveCompensatoryRecord();
		item.setItemId(0);
		item.setType(2);
		item.setPoliceId(policeId);
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		if (user != null) {
			item.setDepartmentId(user.getDepartmentId());
			item.setPositionId(user.getPositionId());
		}
		item.setStartTime(sdf.parse(startTime));
		item.setEndTime(sdf.parse(endTime));
		long currentTimeLong = new Date().getTime();
		long startTimeLong = sdf.parse(startTime).getTime();
		long endTimeLong = sdf.parse(endTime).getTime();
		if (startTimeLong < currentTimeLong) {
			dlr.setStatus(false);
			dlr.setMessage("开始时间不能小于当前时间");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
		if (startTimeLong > endTimeLong) {
			dlr.setStatus(false);
			dlr.setMessage("开始时间不能大于结束时间");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
		// 获得加班兑换规则数据
		List<LeaveOvertimeRule> ruleList = leaveProcessService.getLeaveOvertimeRuleList();
		double ruleHour = ruleList.get(0).getHour();// 累计加班时长6
		double ruleComDay = ruleList.get(0).getDay1();// 可调休天数0.5
		double ruleOverDay = ruleList.get(0).getDay2();// 加班累计时长超过天数2
		double endTimeDou = Double.valueOf(endTimeLong);
		double startTimeDou = Double.valueOf(startTimeLong);
		double currentScore = (endTimeDou - startTimeDou) / 86400000.00;// 时长(天);
		String number = String.valueOf(currentScore);
		int index = number.indexOf(".");
		double douNumber1 = Double.valueOf(number.substring(0, index)) + 0.5;
		double douNumber2 = Double.valueOf(number.substring(0, index));
		if (douNumber2 == currentScore) {
			currentScore = douNumber2;
		} else {
			if (douNumber1 > currentScore) {
				currentScore = douNumber1;
			} else if (douNumber1 < currentScore) {
				currentScore = douNumber2 + 1;
			}
		}
		double reduceTimes = currentScore / ruleComDay * ruleHour;
		// 个人加班时长查询
		LeavePersonalStatistics pItem = leaveProcessService.leavePersonalOvertimeItem(policeId);
		if (pItem != null && pItem.getResidualOvertime() > 0) {
			if (pItem.getOvertimeHours() / 24 >= ruleOverDay) {
				if (pItem.getResidualOvertime() >= reduceTimes) {
					item.setRemarks(remarks);
					item.setStatus(status);
					item.setCreationDate(new Date());
					item.setIsRest(1);
					item.setStatus(1);
					item.setCreationDate(new Date());
					count = leaveProcessService.leaveCompensatoryRecordCreat(item);
				} else {
					dlr.setStatus(false);
					dlr.setMessage("当前时长不够兑换请假时长");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
			} else {
				dlr.setStatus(false);
				dlr.setMessage("加班时长未达到" + ruleOverDay + "天无法调休");
				dlr.setResult(0);
				dlr.setCode(StatusCode.getFailcode());
				return dlr;
			}
		} else {
			dlr.setStatus(false);
			dlr.setMessage("当前时长不够兑换请假时长");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
		if (count > 0) {
			dlr.setStatus(true);
			dlr.setMessage("新增成功");
			dlr.setResult(count);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("新增失败");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
		}
		return dlr;
	}

	// 修改个人调休记录
	@ResponseBody
	@RequestMapping(value = "/leave/personal/compensatory/record/update", method = RequestMethod.POST)
	public DataListReturn leaveCompensatoryRecordUpdate(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "remarks", required = false) String remarks,
			@RequestParam(value = "status", required = false) Integer status) throws IOException, ParseException {
		DataListReturn dlr = new DataListReturn();
		LeaveCompensatoryRecord item = new LeaveCompensatoryRecord();
		item.setId(id);
		item.setType(type);
		item.setPoliceId(policeId);
		if (startTime != null) {
			item.setStartTime(sdf.parse(startTime));
		}
		if (endTime != null) {
			item.setEndTime(sdf.parse(endTime));
		}
		item.setRemarks(remarks);
		item.setStatus(status);
		item.setUpdateDate(new Date());
		int count = leaveProcessService.leaveCompensatoryRecordUpdate(item);
		if (count > 0) {
			dlr.setStatus(true);
			dlr.setMessage("修改成功");
			dlr.setResult(count);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("修改失败");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
		}
		return dlr;
	}

	// 个人积分/加班时长查询
	@RequestMapping(value = "/leave/personal/points/time/item", method = RequestMethod.GET)
	public ResponseEntity<?> leavePointsTimeItem(@RequestParam(value = "type", required = false) int type,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		LeavePersonalStatistics item = new LeavePersonalStatistics();
		if (type == 1) {
			// 个人加班时长查询
			item = leaveProcessService.leavePersonalOvertimeItem(policeId);
		} else if (type == 2) {
			Calendar cal = Calendar.getInstance();
			int month = cal.get(Calendar.MONTH) + 1;
			String currentYear = String.valueOf(cal.get(Calendar.YEAR));
			int halfYear = 0;
			if (month <= 6) {
				halfYear = 2;
				int yearInt = Integer.valueOf(currentYear) - 1;
				currentYear = String.valueOf(yearInt);
			} else if (month > 6) {
				halfYear = 1;
			}
			// 个人积分查询
			item = leaveProcessService.leavePersonalPointsItems(policeId, currentYear, halfYear);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人最新积分兑换记录查询(2条)
	@RequestMapping(value = "/leave/personal/points/exchange/record/newest/list", method = RequestMethod.GET)
	public ResponseEntity<?> leavePointsExchangeRecordNewestList(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 个人最新积分兑换记录查询
		List<LeavePointsExchangeRecord> list = leaveProcessService.leavePointsExchangeRecordNewestList(policeId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人积分兑换记录查询
	@RequestMapping(value = "/leave/personal/points/exchange/record/list", method = RequestMethod.GET)
	public ResponseEntity<?> leavePointsExchangeRecordList(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "dateTime", required = false) String dateTime,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageNum", required = false) Integer pageNum) throws ApiException {
		DataListPage dlr = new DataListPage();
		if (StringUtils.isEmpty(dateTime)) {
			dateTime = sdf.format(new Date());
		}
		dateTime = dateTime.substring(0, 7);
		Integer pageNums = pageNum;
		if (pageSize == null) {
			pageSize = 10;
		}
		pageNum = ((pageNum) - 1) * pageSize;
		// 个人积分兑换记录查询
		List<LeavePointsExchangeRecord> list = leaveProcessService.leavePointsExchangeRecordList(policeId, dateTime,
				pageSize, pageNum);
		// 个人积分兑换记录总数统计
		int total = leaveProcessService.leavePointsExchangeRecordCount(policeId, dateTime);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		dlr.setTotal(total);
		dlr.setPageNum(pageNums);
		dlr.setPageSize(pageSize);
		dlr.setTotalPage(((int) Math.ceil((double) total / (double) pageSize)));
		int count = ((int) Math.ceil((double) total / (double) pageSize));
		if (count - pageNums > 0) {
			dlr.setPageNext(1);
		} else {
			dlr.setPageNext(0);
		}
		return new ResponseEntity<DataListPage>(dlr, HttpStatus.OK);
	}

	// 个人积分兑换记录详情查询
	@RequestMapping(value = "/leave/personal/points/exchange/record/item", method = RequestMethod.GET)
	public ResponseEntity<?> leavePointsExchangeRecordItem(@RequestParam(value = "id", required = false) Integer id)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 个人积分兑换记录详情查询
		LeavePointsExchangeRecord item = leaveProcessService.leavePointsExchangeRecordItem(id);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 新增个人积分兑换记录
	@ResponseBody
	@RequestMapping(value = "/leave/personal/points/exchange/record/creat", method = RequestMethod.POST)
	public DataListReturn leavePointsExchangeRecordCreat(
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "startTime", required = true) String startTime,
			@RequestParam(value = "endTime", required = true) String endTime,
			@RequestParam(value = "points", required = false) Double points,
			@RequestParam(value = "remarks", required = false) String remarks,
			@RequestParam(value = "status", required = false) Integer status) throws IOException, ParseException {
		DataListReturn dlr = new DataListReturn();
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		String currentYear = String.valueOf(cal.get(Calendar.YEAR));
		int halfYear = 0;
		if (month <= 6) {
			halfYear = 2;
			int yearInt = Integer.valueOf(currentYear) - 1;
			currentYear = String.valueOf(yearInt);
		} else if (month > 6) {
			halfYear = 1;
		}
		LeavePointsExchangeRecord item = new LeavePointsExchangeRecord();
		item.setPoliceId(policeId);
		// 根据policeId查询部门
		User user = userService.policeItem(policeId, null, null);
		if (user != null) {
			item.setDepartmentId(user.getDepartmentId());
			item.setPositionId(user.getPositionId());
		}
		item.setStartTime(sdf.parse(startTime));
		item.setEndTime(sdf.parse(endTime));
		long currentTimeLong = new Date().getTime();
		long startTimeLong = sdf.parse(startTime).getTime();
		long endTimeLong = sdf.parse(endTime).getTime();
		if (startTimeLong < currentTimeLong) {
			dlr.setStatus(false);
			dlr.setMessage("开始时间不能小于当前时间");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
		if (startTimeLong > endTimeLong) {
			dlr.setStatus(false);
			dlr.setMessage("开始时间不能大于结束时间");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
		// 获得积分兑换规则数据
		List<LeaveIntegralExchangeRule> ruleList = leaveProcessService.getLeaveIntegralExchangeRuleList();
		double rulePoints = ruleList.get(0).getIntegralValue();// 规则积分
		double ruleDay = ruleList.get(0).getDay();// 积分兑换天数
		double endTimeDou = endTimeLong;
		double startTimeDou = startTimeLong;
		double currentScore = (endTimeDou - startTimeDou) / 86400000.00;// 时长(天);
		String number = String.valueOf(currentScore);
		int index = number.indexOf(".");
		double douNumber1 = Double.valueOf(number.substring(0, index)) + 0.5;
		double douNumber2 = Double.valueOf(number.substring(0, index));
		if (douNumber2 == currentScore) {
			currentScore = douNumber2;
		} else {
			if (douNumber1 > currentScore) {
				currentScore = douNumber1;
			} else if (douNumber1 < currentScore) {
				currentScore = douNumber2 + 1;
			}
		}
		double reducePoints = currentScore / ruleDay * rulePoints;
		// 个人积分查询
		LeavePersonalStatistics pItem = leaveProcessService.leavePersonalPointsItems(policeId, currentYear,
				halfYear);
		int count = 0;
		int count2 = 0;
		if (pItem.getResidualPoints() >= reducePoints) {
			item.setPoints(-reducePoints);
			item.setRemarks(remarks);
			item.setStatus(status);
			item.setCreationDate(new Date());
			// 根据当前用户查询审核人
//			LeaveIntegralAuditPower powerItem = leaveProcessService.leavePowerItem(policeId);
//			if (powerItem != null) {
			count = leaveProcessService.leavePointsExchangeRecordCreat(item);
			// 调休记录新增
			LeaveCompensatoryRecord comItem = new LeaveCompensatoryRecord();
			comItem.setPoliceId(policeId);
			comItem.setItemId(item.getId());
			comItem.setType(3);
			if (user != null) {
				comItem.setDepartmentId(user.getDepartmentId());
				comItem.setPositionId(user.getPositionId());
			}
			comItem.setRemarks(remarks);
			comItem.setStartTime(sdf.parse(startTime));
			comItem.setEndTime(sdf.parse(endTime));
			comItem.setIsRest(1);
			comItem.setStatus(1);
			// comItem.setApprovedId(powerItem.getPoliceId());
			comItem.setCreationDate(new Date());
			count2 = leaveProcessService.leaveCompensatoryRecordCreat(comItem);
		} else {
			dlr.setStatus(false);
			dlr.setMessage("当前积分不够兑换请假时长");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
		if (count > 0 && count2 > 0) {
			dlr.setStatus(true);
			dlr.setMessage("新增成功");
			dlr.setResult(count);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("新增失败");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
		}
		return dlr;
	}

	// 修改个人积分兑换记录
	@ResponseBody
	@RequestMapping(value = "/leave/personal/points/exchange/record/update", method = RequestMethod.POST)
	public DataListReturn leavePointsExchangeRecordUpdate(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "remarks", required = false) String remarks,
			@RequestParam(value = "points", required = false) Double points,
			@RequestParam(value = "status", required = false) Integer status) throws IOException, ParseException {
		DataListReturn dlr = new DataListReturn();
		LeavePointsExchangeRecord item = new LeavePointsExchangeRecord();
		item.setId(id);
		item.setPoliceId(policeId);
		if (startTime != null) {
			item.setStartTime(sdf.parse(startTime));
		}
		if (endTime != null) {
			item.setEndTime(sdf.parse(endTime));
		}
		item.setRemarks(remarks);
		item.setStatus(status);
		item.setPoints(points);
		item.setUpdateDate(new Date());
		int count = leaveProcessService.leavePointsExchangeRecordUpdate(item);
		if (count > 0) {
			dlr.setStatus(true);
			dlr.setMessage("修改成功");
			dlr.setResult(count);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("修改失败");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
		}
		return dlr;
	}

	// 请假原因占比情况
	@RequestMapping(value = "/leave/reason/analysis", method = RequestMethod.GET)
	public ResponseEntity<?> leaveReasonAnalysis(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 个人请假原因占比情况（api）
		List<CalculationChart> leaveReasonAnalysisList = leaveProcessService.leaveReasonAnalysisList(policeId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(leaveReasonAnalysisList);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// -----------------AI休假(请休假)api-----------------------
	// 全局年休假统计
	@RequestMapping(value = "/leave/annual/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> leaveAnnualStatistics(
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 民警总数
		int policeNum = userService.userListCount(departmentId, null, null, null, null);
		// 各部门平均年假使用率查询（api）
		int leaveDepAverageItem = leaveProcessService.leaveDepAverageItem(departmentId);
		double leaveDepAverage = 0.0;
		double policeNumDou = policeNum;
		double AverageItemDou = leaveDepAverageItem;
		if (policeNum > 0) {
			leaveDepAverage = Double.valueOf(df.format(AverageItemDou / policeNumDou));
		}
		// 休假中民警数量（api）
		int onHolidayNumItem = leaveProcessService.onHolidayNumItem(departmentId);
		// 请假中民警数量（api）
		int askingForLeaveNumItem = leaveProcessService.askingForLeaveNumItem(departmentId);
		LeaveAnnualStatistics statistics = new LeaveAnnualStatistics();
		statistics.setPoliceNum(policeNum);
		statistics.setAverageNum(leaveDepAverage);
		statistics.setOnHolidayNum(onHolidayNumItem);
		statistics.setAskingForLeaveNum(askingForLeaveNumItem);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(statistics);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 各部门年假使用情况
	@RequestMapping(value = "/leave/dep/annual/chart", method = RequestMethod.GET)
	public ResponseEntity<?> leaveDepAnnualChartList(
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 各部门年假使用情况查询（api）
		List<LeaveChart> leaveDepAnnualChartList = leaveProcessService.leaveDepAnnualChartList();
		// 民警总数
		int policeNum = userService.userListCount(departmentId, null, null, null, null);
		// 各部门平均年假使用率查询（api）
		int leaveDepAverageItem = leaveProcessService.leaveDepAverageItem(null);
		double leaveDepAverage = 0.0;
		double policeNumDou = policeNum;
		double AverageItemDou = leaveDepAverageItem;
		if (policeNum > 0) {
			leaveDepAverage = Double.valueOf(df.format(AverageItemDou / policeNumDou));
		}
		LeaveDepAnnualChart dep = new LeaveDepAnnualChart();
		dep.setLeaveDepAnnualChartList(leaveDepAnnualChartList);
		dep.setAverageNum(leaveDepAverage * 100);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(dep);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 近12个月请假人数趋势查询
	@RequestMapping(value = "/leave/vacation/chart", method = RequestMethod.GET)
	public ResponseEntity<?> leaveVacationChartList(
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 每月请假总人数查询（api）
		List<CalculationChart> leavetotalNumList = leaveProcessService.leavetotalNumList(departmentId);
		// 每月休年假人数查询（api）
		List<CalculationChart> leaveAnnualNumList = leaveProcessService.leaveAnnualNumList(departmentId);
		// 每月病假人数查询（api）
		List<CalculationChart> leaveOffNumList = leaveProcessService.leaveOffNumList(departmentId);
		// 每月事假人数查询（api）
		List<CalculationChart> leaveNumList = leaveProcessService.leaveNumList(departmentId);
		LeaveVacationChart leaveLine = new LeaveVacationChart();
		leaveLine.setLeavetotalNumList(leavetotalNumList);
		leaveLine.setLeaveAnnualNumList(leaveAnnualNumList);
		leaveLine.setLeaveOffNumList(leaveOffNumList);
		leaveLine.setLeaveNumList(leaveNumList);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(leaveLine);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 当月请假类型占比
	@RequestMapping(value = "/leave/type/proportion", method = RequestMethod.GET)
	public ResponseEntity<?> leaveType(@RequestParam(value = "departmentId", required = false) Integer departmentId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 每月请假类型占比查询（api）
		int total = 0;
		List<CalculationChart> leaveTypeList = leaveProcessService.leaveTypeList(departmentId);
		if (leaveTypeList.size() > 0) {
			for (int i = 0; i < leaveTypeList.size(); i++) {
				total = leaveTypeList.get(i).getNum() + total;
			}
		}
		if (total > 0) {
			dlr.setResult(leaveTypeList);
		} else {
			dlr.setResult(null);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// -----------------AI休假(加值班)api-----------------------

	// 加值班统计分析
	@RequestMapping(value = "/leave/overtime/duty/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> leaveOvertimeDutyStatistics(
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		LeaveOverDutyStatistics item = new LeaveOverDutyStatistics();
		// 民警总数
		int policeNum = userService.userListCount(departmentId, null, null, null, null);
		item.setPoliceNum(policeNum);
		// 加班折线图查询（api）
		List<LeaveChart> leaveOvertimeList = clockService.leaveOvertimeList(departmentId);
		List<LeaveChart> overtimeList = new ArrayList<LeaveChart>();
		overtimeList.add(leaveOvertimeList.get(leaveOvertimeList.size() - 2));
		overtimeList.add(leaveOvertimeList.get(leaveOvertimeList.size() - 1));
		item.setOvertimeList(overtimeList);
		item.setOverTime(leaveOvertimeList.get(leaveOvertimeList.size() - 1).getNum());
		double numDou = policeNum;
		double nowNum = leaveOvertimeList.get(leaveOvertimeList.size() - 1).getNum();
		if (policeNum > 0) {
			item.setOverTimeAve(Double.valueOf(df.format(nowNum / numDou)));
		} else {
			item.setOverTimeAve(0.0);
		}
		// 值班折线图查询（api）
		List<LeaveChart> leaveDutyList = clockService.leaveDutyList(departmentId);
		for (int i = 0; i < leaveDutyList.size(); i++) {
			leaveDutyList.get(i).setNum(Double.valueOf(df.format(leaveDutyList.get(i).getNum() / 24)));
		}
		List<LeaveChart> dutyList = new ArrayList<LeaveChart>();
		dutyList.add(leaveDutyList.get(leaveDutyList.size() - 2));
		dutyList.add(leaveDutyList.get(leaveDutyList.size() - 1));
		item.setDutyList(dutyList);
		item.setDutyTime(Double.valueOf(df.format(leaveDutyList.get(leaveDutyList.size() - 1).getNum())));
		double numDutyDou = policeNum;
		double nowDutyNum = leaveDutyList.get(leaveDutyList.size() - 1).getNum();
		if (policeNum > 0) {
			item.setDutyTimeAve(Double.valueOf(df.format(nowDutyNum / numDutyDou)));
		} else {
			item.setDutyTimeAve(0.0);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 近一年内加值班趋势
	@RequestMapping(value = "/leave/last/year/line/chart", method = RequestMethod.GET)
	public ResponseEntity<?> leaveLineChartList(
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 近一年内加班折线图查询（api）
		List<LeaveChart> leaveOvertimeList = clockService.leaveOvertimeList(departmentId);
		// 近一年内值班折线图查询（api）
		List<LeaveChart> leaveDutyList = clockService.leaveDutyList(departmentId);
		LeaveLine leaveLine = new LeaveLine();
		leaveLine.setOvertimeList(leaveOvertimeList);
		leaveLine.setDutyList(leaveDutyList);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(leaveLine);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 各部门加值班排名
	@RequestMapping(value = "/leave/dep/overtime/rank", method = RequestMethod.GET)
	public ResponseEntity<?> leaveDepOvertimeRankList(@RequestParam(value = "type", required = false) Integer type)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		String startTime = null;
		String endTime = null;
		Calendar calendar = Calendar.getInstance();
		if (type == null || type == 1) {// 按每周
			calendar.add(Calendar.WEEK_OF_MONTH, 0);
			calendar.set(Calendar.DAY_OF_WEEK, 2);
			Date time = calendar.getTime();
			startTime = new SimpleDateFormat("yyyy-MM-dd").format(time) + " 00:00:00";
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
			cal.add(Calendar.DAY_OF_WEEK, 1);
			Date time2 = cal.getTime();
			endTime = new SimpleDateFormat("yyyy-MM-dd").format(time2) + " 23:59:59";
		} else if (type == 2) {// 按每月
			calendar.setTime(new Date());
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.add(Calendar.MONTH, 0);
			startTime = formatter.format(calendar.getTime()) + " 00:00:00";
			Calendar ca = Calendar.getInstance();
			ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
			endTime = formatter.format(ca.getTime()) + " 59:59:59";
		}
//		List<LeaveChart> list = new ArrayList<LeaveChart>();
//		if (type == null || type == 1) {// 加班
//			// 部门加班情况排名（api）
//			list = clockService.leaveDepOvertimeRankList();
//		} else if (type == 2) {// 值班
//			// 部门值班情况排名（api）
//			list = clockService.leaveDepDutyRankList();
//		}
		List<LeaveChart> list = clockService.leaveDepOvertimeRankList(startTime, endTime);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 个人加班排名Top10
	@RequestMapping(value = "/leave/personal/overtime/rank", method = RequestMethod.GET)
	public ResponseEntity<?> leavePersonalOvertimeRankList(@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		String startTime = null;
		String endTime = null;
		Calendar calendar = Calendar.getInstance();
		if (type == null || type == 1) {// 按每周
			calendar.add(Calendar.WEEK_OF_MONTH, 0);
			calendar.set(Calendar.DAY_OF_WEEK, 2);
			Date time = calendar.getTime();
			startTime = new SimpleDateFormat("yyyy-MM-dd").format(time) + " 00:00:00";
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
			cal.add(Calendar.DAY_OF_WEEK, 1);
			Date time2 = cal.getTime();
			endTime = new SimpleDateFormat("yyyy-MM-dd").format(time2) + " 23:59:59";
		} else if (type == 2) {// 按每月
			calendar.setTime(new Date());
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.add(Calendar.MONTH, 0);
			startTime = formatter.format(calendar.getTime()) + " 00:00:00";
			Calendar ca = Calendar.getInstance();
			ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
			endTime = formatter.format(ca.getTime()) + " 59:59:59";
		}
		// 个人加班排名Top10（api）
		List<ClockRecord> leavePersonalOvertimeRankList = clockService.leavePersonalOvertimeRankList(departmentId,
				startTime, endTime);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(leavePersonalOvertimeRankList);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 警力在线统计
	@RequestMapping(value = "/police/force/online/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> policeForceOnlineStatistics(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// 警力在线统计
		PoliceForceOnlineStatistics item = clockService.policeForceOnlineStatisticsItem();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 今日各部门加值班警员人数查询
	@RequestMapping(value = "/process/dep/overtime/duty/police/num/list", method = RequestMethod.GET)
	public ResponseEntity<?> processDepOvertimeDutypoliceNumList(
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "sortType", required = false) Integer sortType) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		String typeStr = null;
		String sortStr = null;
		if (type == 1) {// 1加班人数
			typeStr = "overtimeNum";
		} else if (type == 2) {// 2值班人数
			typeStr = "dutyNum";
		} else if (type == 3) {// 3总人数
			typeStr = "totalNum";
		}
		if (sortType == null || sortType == 1) {// 1升序2降序
			sortStr = "asc";
		} else if (sortType == 2) {
			sortStr = "desc";
		}
		// 今日各部门加值班警员人数查询
		List<DepOvertimeDutypoliceNum> list = clockService.processDepOvertimeDutypoliceNumList(departmentId, typeStr,
				sortStr);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	/**
	 * 获得所有职位
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/get_position", method = RequestMethod.POST)
	public ResponseEntity<?> getPositon() {

		// 职位列表
		List<PolicePosition> policePositionList = userService.policePositionList();

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(policePositionList);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 获得调休管理数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/get_leave_rest_manage", method = RequestMethod.POST)
	public ResponseEntity<?> getLeaveRestManage(@Param("departmentId") Integer departmentId,
			@Param("positionId") Integer positionId, @Param("keyword") String keyword,
			@Param("pageSize") Integer pageSize) {

		if (null == pageSize) {
			pageSize = 1;
		}

		List<LeaveCompensatoryRecord> leaveCompensatoryRecord = leaveProcessService
				.getLeaveCompensatoryRecord(departmentId, positionId, keyword, (pageSize - 1) * 10);
		Integer count = leaveProcessService.getLeaveCompensatoryRecordCount(departmentId, positionId, keyword);
		
		/*List<LeaveRestManage> leaveRestManageList = leaveProcessService.getLeaveRestManageList(departmentId, positionId,
				keyword, (pageSize - 1) * 10);*/

		// 查询总条数
		/*int leaveRestManageListCount = leaveProcessService.getLeaveRestManageListCount(departmentId, positionId,
				keyword);*/

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("leaveCompensatoryRecord", leaveCompensatoryRecord);
		map.put("count", count);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(map);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 根据id获得调休管理数据
	 * 
	 * @return 
	 */
	@RequestMapping(value = "/leave/get_leave_rest_manage_byid", method = RequestMethod.POST)
	public ResponseEntity<?> getLeaveRestManageById(@Param("id") Integer id) {

		//LeaveRestManage leaveRestManageOne = leaveProcessService.getLeaveRestManageOne(id);

		LeaveCompensatoryRecord leaveCompensatoryRecordOne = leaveProcessService.getLeaveCompensatoryRecordOne(id);
		
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(leaveCompensatoryRecordOne);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 获得疗修养管理数据或根据条件
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/get_leave_train_list", method = RequestMethod.POST)
	public ResponseEntity<?> getLeaveTrainList(@Param("type") Integer type, @Param("departmentId") Integer departmentId,
			@Param("keyWord") String keyWord, @Param("pageSize") Integer pageSize) {

		List<LeaveTrain> leaveTrainList = leaveProcessService.getLeaveTrainList(type, departmentId, keyWord,
				(pageSize - 1) * 10);

		int leaveTrainListCount = leaveProcessService.getLeaveTrainListCount(type, departmentId, keyWord);

		LinkedHashMap<String, Object> list = new LinkedHashMap<String, Object>();

		list.put("leaveTrainList", leaveTrainList);
		list.put("leaveTrainListCount", leaveTrainListCount);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(list);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 添加疗修养管理数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/add_leave_train_list", method = RequestMethod.POST)
	public ResponseEntity<?> addLeaveTrainList(@Param("leaveTrain") LeaveTrain leaveTrain) {

		int insertLeaveTrainList = leaveProcessService.insertLeaveTrainList(leaveTrain);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(insertLeaveTrainList);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 根据id删除疗修养管理数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/delete_leave_train_list", method = RequestMethod.POST)
	public ResponseEntity<?> deleteLeaveTrainList(@Param("id") Integer id) {

		int deleteByPrimaryKey = leaveProcessService.deleteByPrimaryKey(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(deleteByPrimaryKey);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 根据id修改疗修养管理数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/update_leave_train_list", method = RequestMethod.POST)
	public ResponseEntity<?> updateLeaveTrainList(@Param("leaveTrain") LeaveTrain leaveTrain) {

		int updateByPrimaryKeySelective = leaveProcessService.updateByPrimaryKeySelective(leaveTrain);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(updateByPrimaryKeySelective);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 根据id获得疗修养管理数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/get_leave_train_list_by_id", method = RequestMethod.POST)
	public ResponseEntity<?> getLeaveTrainListOne(@Param("id") Integer id) {

		LeaveTrain leaveTrainOne = leaveProcessService.getLeaveTrainOne(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(leaveTrainOne);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 获得积分管理数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/get_leaveintegral_manage", method = RequestMethod.POST)
	public ResponseEntity<?> getLeaveIntegralManage(@Param("policeMonth") Integer policeMonth,
			@Param("departmentId") Integer departmentId, @Param("keyword") String keyword,
			@Param("pageSize") Integer pageSize) {

		if (null == pageSize) {
			pageSize = 1;
		}

		List<LeaveIntegralManage> leaveIntegralManageList = leaveProcessService.getLeaveIntegralManageList(policeMonth,
				departmentId, keyword, (pageSize - 1) * 10);

		int leaveIntegralManageCount = leaveProcessService.getLeaveIntegralManageCount(policeMonth, departmentId,
				keyword);

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("leaveIntegralManageList", leaveIntegralManageList);
		map.put("leaveIntegralManageCount", leaveIntegralManageCount);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(map);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 新增积分
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/add_leaveIntegral_manage", method = RequestMethod.POST)
	public ResponseEntity<?> addLeaveIntegralManage(
			@Param("leaveIntegralManage") LeaveIntegralManage leaveIntegralManage) throws ParseException {

		// 判断该月是否已新增过积分
		int num = leaveProcessService.nowMonthHave(leaveIntegralManage.getScoredPoliceId(),
				leaveIntegralManage.getPoliceMonthId());

		if (num != 0) {
			// 新增失败
			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getSuccesscode());
			dataListReturn.setMessage("fail");
			dataListReturn.setResult("该警员在" + leaveIntegralManage.getPoliceMonthId() + "月内已被积分");
			dataListReturn.setStatus(true);

			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
		} else {
			// 新增时的积分维护
			maintainIntegralDate(leaveIntegralManage, false);

			// 转换时间
			leaveIntegralManage.setScoringDate(fmtDate(leaveIntegralManage.getPoliceMonthId()));

			Integer addLeaveIntegralManage = leaveProcessService.addLeaveIntegralManage(leaveIntegralManage);

			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getSuccesscode());
			dataListReturn.setMessage("success");
			dataListReturn.setResult(addLeaveIntegralManage);
			dataListReturn.setStatus(true);

			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
		}

	}

	/**
	 * 新增时维护积分
	 */
	private void maintainIntegralDate(LeaveIntegralManage leaveIntegralManage, Boolean isMore) {
		// 判断该年是否重复添加
		Calendar instance = Calendar.getInstance();
		String year = String.valueOf(instance.get(Calendar.YEAR));
		int halfYear = leaveIntegralManage.getPoliceMonthId() <= 6 ? 1 : 2;

		// 查询积分表的数据
		LeavePoints leavePoints = leaveProcessService.leavePointsItem(null, leaveIntegralManage.getScoredPoliceId(),
				year, halfYear);
		// 如果重复添加
		if (null == leavePoints) {
			if (!isMore) {
				// 新增维护积分
				leaveProcessService.leavePointsCreat(maintainIntegral(leaveIntegralManage));
			}
		} else {
			// 如果有数据则取总积分和剩余积分累加
			leavePoints.setTotalPoints(leavePoints.getTotalPoints() + leaveIntegralManage.getIntegralValue());
			leavePoints.setResidualPoints(leavePoints.getResidualPoints() + leaveIntegralManage.getIntegralValue());

			leaveProcessService.leavePointsUpdate(leavePoints);
		}

	}

	/**
	 * 新增积分转换时间
	 */
	private Date fmtDate(int policeMon) throws ParseException {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		int policeMonth = policeMon;
		if (month == policeMonth) {
			return new Date();
		} else {
			String dateTime = null;
			if (policeMonth < 10) {
				String monthString = "0" + policeMonth;
				dateTime = year + "-" + monthString + "-15 00:00:00";
			} else {
				dateTime = year + "-" + policeMonth + "-15 00:00:00";
			}
			return sdf.parse(dateTime);
		}

	}

	private LeavePoints maintainIntegral(LeaveIntegralManage leaveIntegralManage) {

		// 维护积分
		LeavePoints leavePoints = new LeavePoints();
		leavePoints.setPoliceId(leaveIntegralManage.getScoredPoliceId());
		leavePoints.setName(leaveIntegralManage.getScoredPoliceName());
		// 根据警号查询部门
		int departmentId = departmentService.getDepartmentByPoliceId(leaveIntegralManage.getScoredPoliceId()).getId();
		leavePoints.setDepartmentId(departmentId);

		leavePoints.setPositionId(
				userService.getPolicePositionByPoliceId(leaveIntegralManage.getScoredPoliceId()).getId());
		leavePoints.setYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));

		leavePoints.setHalfYear(leaveIntegralManage.getPoliceMonthId() <= 6 ? 1 : 2);

		leavePoints.setTotalPoints(leaveIntegralManage.getIntegralValue());
		leavePoints.setResidualPoints(leaveIntegralManage.getIntegralValue());

		return leavePoints;

	}

	/**
	 * 根据id删除积分管理
	 *
	 * @return
	 */
	@RequestMapping(value = "/leave/delete_leaveIntegral_manage", method = RequestMethod.POST)
	public ResponseEntity<?> deleteLeaveIntegralManage(@Param("id") Integer id) {

		// 根据id查询积分管理
		LeaveIntegralManage integralManage = leaveProcessService.getLeaveIntegralManageOne(id);

		Calendar instance = Calendar.getInstance();
		instance.setTime(integralManage.getCreationDate());
		String year = String.valueOf(instance.get(Calendar.YEAR));
		int halfYear = integralManage.getPoliceMonthId() <= 6 ? 1 : 2;

		// 查询积分表的数据
		LeavePoints leavePoints = leaveProcessService.leavePointsItem(null, integralManage.getScoredPoliceId(), year,
				halfYear);

		// 如果记分表中有数据
		if (null != leavePoints) {
			// 将积分减去
			Double total = leavePoints.getTotalPoints() - integralManage.getIntegralValue();
			Double residual = leavePoints.getResidualPoints() - integralManage.getIntegralValue();

			leavePoints.setTotalPoints(total < 0 ? 0 : total);
			leavePoints.setResidualPoints(residual < 0 ? 0 : residual);

			// 修改积分表
			leaveProcessService.leavePointsUpdate(leavePoints);
		}

		int deleteLeaveIntegralManageById = leaveProcessService.deleteLeaveIntegralManageById(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(deleteLeaveIntegralManageById);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 根据id修改积分管理
	 * 
	 * @param leaveIntegralManage
	 * @return
	 */
	@RequestMapping(value = "/leave/update_leaveIntegral_manage", method = RequestMethod.POST)
	public ResponseEntity<?> updateLeaveIntegralManage(
			@Param("leaveIntegralManage") LeaveIntegralManage leaveIntegralManage) {

		// 查询原来的积分
		LeaveIntegralManage leaveIntegralManageOne = leaveProcessService
				.getLeaveIntegralManageOne(leaveIntegralManage.getId());

		// 查询积分表的数据
		Calendar instance = Calendar.getInstance();
		instance.setTime(leaveIntegralManageOne.getCreationDate());
		String year = String.valueOf(instance.get(Calendar.YEAR));
		int halfYear = leaveIntegralManageOne.getPoliceMonthId() <= 6 ? 1 : 2;

		LeavePoints leavePoints = leaveProcessService.leavePointsItem(null, leaveIntegralManageOne.getScoredPoliceId(),
				year, halfYear);

		if (null != leavePoints) {
			double change = leaveIntegralManage.getIntegralValue() - leaveIntegralManageOne.getIntegralValue();

			// 修改记分表当前的积分
			leavePoints.setTotalPoints(
					(leavePoints.getTotalPoints() + change) < 0 ? 0 : (leavePoints.getTotalPoints() + change));
			leavePoints.setResidualPoints(
					(leavePoints.getResidualPoints() + change) < 0 ? 0 : (leavePoints.getResidualPoints() + change));

			leaveProcessService.leavePointsUpdate(leavePoints);
		}

		int updateLeaveIntegralManageById = leaveProcessService.updateLeaveIntegralManageById(leaveIntegralManage);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(updateLeaveIntegralManageById);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 根据id获得积分管理
	 *
	 * @return
	 */
	@RequestMapping(value = "/leave/get_leaveIntegral_manage_by_id", method = RequestMethod.POST)
	public ResponseEntity<?> getLeaveIntegralManageOne(@Param("id") Integer id) {

		LeaveIntegralManage leaveIntegralManageOne = leaveProcessService.getLeaveIntegralManageOne(id);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(leaveIntegralManageOne);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 获得积分审核权限
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/get_leave_integrala_udit_power", method = RequestMethod.POST)
	public ResponseEntity<?> getLeaveIntegralAuditPower(@Param("departmentId") Integer departmentId,
			@Param("keyword") String keyword, @Param("pageSize") Integer pageSize) {

		if (null == pageSize) {
			pageSize = 1;
		}

		List<LeavePower> leaveIntegralAuditPowerList = leaveProcessService.getLeaveIntegralAuditPowerList(departmentId,
				keyword, (pageSize - 1) * 10);

		int leaveIntegralAuditPowerListCount = leaveProcessService.getLeaveIntegralAuditPowerCount(departmentId,
				keyword);

		for (LeavePower leave : leaveIntegralAuditPowerList) {
			// 根据对象集合字符串查询对象名集合字符串
			String userNameByPoliceIds = userService.getUserNameByPoliceIds(leave.getPoliceObjectIds());
			leave.setPoliceObjectNames(userNameByPoliceIds);
			// 数量
			leave.setCount(null != leave.getPoliceObjectIds() ? leave.getPoliceObjectIds().split(",").length : 0);
		}

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("leaveIntegralAuditPowerList", leaveIntegralAuditPowerList);
		map.put("leaveIntegralAuditPowerListCount", leaveIntegralAuditPowerListCount);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(map);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 添加权限管理(原积分审核权限)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/add_leave_integrala_udit_power", method = RequestMethod.POST)
	public ResponseEntity<?> addLeaveIntegralAuditPower(
			@Param("leaveIntegralAuditPower") LeavePower leaveIntegralAuditPower,
			@Param("policeObjectIdsArr") String policeObjectIdsArr) {

		// 查询是否重复添加
		int exist = leaveProcessService.isExist(leaveIntegralAuditPower.getDepartmentId(),
				leaveIntegralAuditPower.getCheckerId(), leaveIntegralAuditPower.getModuleId());

		DataListReturn dataListReturn = new DataListReturn();

		if (exist > 0) {
			dataListReturn.setCode(StatusCode.getSuccesscode());
			dataListReturn.setMessage("fiale");
			dataListReturn.setResult(
					"该管理者在" + (leaveIntegralAuditPower.getModuleId() == 1 ? "AI预警" : "AI休假") + "使用范围已分配管理对象,仅可以编辑操作");
			dataListReturn.setStatus(true);
		} else {
			leaveIntegralAuditPower.setCreationDate(new Date());
			String[] departmentObjectIds = leaveIntegralAuditPower.getDepartmentObjectIds().split(",");

			if (departmentObjectIds.length > 1) {
				String[] policeObjectIdsArrs = policeObjectIdsArr.split("-");
				// leave_power
				leaveProcessService.addLeaveIntegralAuditPower(leaveIntegralAuditPower);
				for (int i = 0; i < departmentObjectIds.length; i++) {
					// leave_power_objec
					LeavePowerObject leavePowerObject = new LeavePowerObject();
					leavePowerObject.setPowerId(leaveIntegralAuditPower.getId());
					leavePowerObject.setCheckerId(leaveIntegralAuditPower.getCheckerId());
					leavePowerObject.setDepartmentId(Integer.parseInt(departmentObjectIds[i]));
					leavePowerObject.setPoliceObjectIds(policeObjectIdsArrs[i]);
					leavePowerObject.setCreationDate(new Date());
					leaveProcessService.leavePowerObjectCreat(leavePowerObject);
				}
			} else {
				// leave_power
				leaveProcessService.addLeaveIntegralAuditPower(leaveIntegralAuditPower);
				// leave_power_objec
				LeavePowerObject leavePowerObject = new LeavePowerObject();
				leavePowerObject.setPowerId(leaveIntegralAuditPower.getId());
				leavePowerObject.setCheckerId(leaveIntegralAuditPower.getCheckerId());
				leavePowerObject.setDepartmentId(leaveIntegralAuditPower.getDepartmentId());
				leavePowerObject.setPoliceObjectIds(leaveIntegralAuditPower.getPoliceObjectIds());
				leavePowerObject.setCreationDate(new Date());
				leaveProcessService.leavePowerObjectCreat(leavePowerObject);
			}

			dataListReturn.setCode(StatusCode.getSuccesscode());
			dataListReturn.setMessage("success");
			dataListReturn.setResult(null);
			dataListReturn.setStatus(true);
		}

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 修改权限管理(原积分审核权限)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/updateLeaveIntegralAuditPower", method = RequestMethod.POST)
	public ResponseEntity<?> updateLeaveIntegralAuditPower(
			@Param("leaveIntegralAuditPower") LeavePower leaveIntegralAuditPower,
			@Param("policeObjectIdsArr") String policeObjectIdsArr) {

		leaveIntegralAuditPower.setUpdateDate(new Date());
		String[] departmentObjectIds = leaveIntegralAuditPower.getDepartmentObjectIds().split(",");

		if (departmentObjectIds.length > 1) {
			String[] policeObjectIdsArrs = policeObjectIdsArr.split("-");
			// leave_power
			leaveProcessService.updateLeaveIntegralAuditPower(leaveIntegralAuditPower);
			// 根据powerId删除leave_power_objec
			leaveProcessService.leavePowerObjectDeleteByPowerId(leaveIntegralAuditPower.getId());
			for (int i = 0; i < departmentObjectIds.length; i++) {
				// 新增leave_power_objec
				LeavePowerObject leavePowerObject = new LeavePowerObject();
				leavePowerObject.setPowerId(leaveIntegralAuditPower.getId());
				leavePowerObject.setCheckerId(leaveIntegralAuditPower.getCheckerId());
				leavePowerObject.setDepartmentId(Integer.parseInt(departmentObjectIds[i]));
				leavePowerObject.setPoliceObjectIds(policeObjectIdsArrs[i]);
				leavePowerObject.setUpdateDate(new Date());
				leaveProcessService.leavePowerObjectCreat(leavePowerObject);
			}
		} else {
			// leave_power
			leaveProcessService.updateLeaveIntegralAuditPower(leaveIntegralAuditPower);
			// 根据powerId删除leave_power_objec
			leaveProcessService.leavePowerObjectDeleteByPowerId(leaveIntegralAuditPower.getId());
			// 新增leave_power_objec
			LeavePowerObject leavePowerObject = new LeavePowerObject();
			leavePowerObject.setPowerId(leaveIntegralAuditPower.getId());
			leavePowerObject.setCheckerId(leaveIntegralAuditPower.getCheckerId());
			leavePowerObject.setDepartmentId(leaveIntegralAuditPower.getDepartmentId());
			leavePowerObject.setPoliceObjectIds(leaveIntegralAuditPower.getPoliceObjectIds());
			leavePowerObject.setUpdateDate(new Date());
			leaveProcessService.leavePowerObjectCreat(leavePowerObject);
		}

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(null);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * 根据id查询积分审核权限
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/get_leave_integrala_udit_power_by_id", method = RequestMethod.POST)
	public ResponseEntity<?> getLeaveIntegralAuditPowerOne(@Param("id") Integer id) {

		LeavePower leavePower = leaveProcessService.getLeaveIntegralAuditPowerById(id);

		if (leavePower.getDepartmentId() == 1) {
			leavePower.setPoliceObjectIdsArr(leaveProcessService.getPoliceObjectIdsByPowerId(id));

			// 查询department_object_ids对应的部门名称
			List<Department> dept = departmentService.getDepartmentByIds(leavePower.getDepartmentObjectIds());
			String deptName = "";
			for (Department d : dept) {
				deptName += d.getName() + ",";
			}
			leavePower.setDepartmentObjectNames(deptName.substring(0, deptName.length() - 1));

			if (leavePower.getPoliceObjectIdsArr() != null) {
				// 查询policeObjectIdsArr对应的名称
				String names = "";
				String[] idArr = leavePower.getPoliceObjectIdsArr().split("-");
				for (String ids : idArr) {
					names += userService.getUserNameByPoliceIds(ids) + "-";
				}
				leavePower.setPoliceObjectNamesArr(names.substring(0, names.length() - 1));
			}
		}

		// 根据对象集合字符串查询对象名集合字符串
		String userNameByPoliceIds = userService.getUserNameByPoliceIds(leavePower.getPoliceObjectIds());
		leavePower.setPoliceObjectNames(userNameByPoliceIds);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(leavePower);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 根据id删除积分审核权限
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/delete_leave_integrala_udit_power", method = RequestMethod.POST)
	public ResponseEntity<?> deleteLeaveIntegralAuditPower(@Param("id") Integer id) {

		// 查询该权限是否已被使用
		// 检验结果
		Boolean success = false;
		List<String> scoredPoliceIds = leaveProcessService.getScoredPoliceIds();
		for (String policeId : scoredPoliceIds) {
			int checkIsExist = leaveProcessService.checkIsExist(policeId, id);
			if (checkIsExist > 0) {
				success = true;
				break;
			}
		}

		DataListReturn dataListReturn = new DataListReturn();

		if (success) {
			dataListReturn.setCode(StatusCode.getSuccesscode());
			dataListReturn.setMessage("faile");
			dataListReturn.setResult("删除失败,该约谈权限已被引用!");
			dataListReturn.setStatus(true);
		} else {
			// 删除该权限
			int deleteLeaveIntegralAuditPower = leaveProcessService.deleteLeaveIntegralAuditPower(id);
			leaveProcessService.leavePowerObjectDeleteByPowerId(id);
			dataListReturn.setCode(StatusCode.getSuccesscode());
			dataListReturn.setMessage("success");
			dataListReturn.setResult(deleteLeaveIntegralAuditPower);
			dataListReturn.setStatus(true);
		}

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 获得调休预警规则
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/get_leave_rest_alarm_rule", method = RequestMethod.POST)
	public ResponseEntity<?> getLeaveRestAlarmRule() {

		List<LeaveRestAlarmRule> leaveRestAlarmRuleList = leaveProcessService.getLeaveRestAlarmRuleList();

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(leaveRestAlarmRuleList);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 修改调休预警规则
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/update_leave_rest_alarm_rule", method = RequestMethod.POST)
	public ResponseEntity<?> updateLeaveRestAlarmRule(@Param("id") Integer[] id, @Param("day") Double[] day,
			@Param("value") String[] value) {

		for (int i = 0; i < id.length; i++) {
			leaveProcessService.updateByPrimaryKeySelective(new LeaveRestAlarmRule(id[i], day[i], value[i]));
		}

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(null);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 获得加班规则
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/get_leave_overtime_rule", method = RequestMethod.POST)
	public ResponseEntity<?> getLeaveOvertimeRule() {

		List<LeaveOvertimeRule> leaveOvertimeRuleList = leaveProcessService.getLeaveOvertimeRuleList();
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(leaveOvertimeRuleList);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 修改加班规则
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/update_leave_overtime_rule", method = RequestMethod.POST)
	public ResponseEntity<?> updateLeaveOvertimeRule(@Param("id") Integer[] id, @Param("hour") Double[] hour,
			@Param("day1") Double[] day1, @Param("day2") Double[] day2, @Param("day3") Double[] day3) {

		for (int i = 0; i < hour.length; i++) {
			leaveProcessService
					.updateByPrimaryKeySelective(new LeaveOvertimeRule(id[i], hour[i], day1[i], day2[i], day3[i]));
		}

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(null);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 获得积分兑换规则
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/get_leave_integral_exchange_rule", method = RequestMethod.POST)
	public ResponseEntity<?> getLeaveIntegralExchangeRule() {

		List<LeaveIntegralExchangeRule> leaveIntegralExchangeRuleList = leaveProcessService
				.getLeaveIntegralExchangeRuleList();
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(leaveIntegralExchangeRuleList);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 修改积分兑换规则
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/update_leave_integral_exchange_rule", method = RequestMethod.POST)
	public ResponseEntity<?> upLeaveIntegralExchangeRule(@Param("id") Integer[] id,
			@Param("integralValue") Integer[] integralValue, @Param("day") Double[] day) {

		for (int i = 0; i < id.length; i++) {
			leaveProcessService
					.updateByPrimaryKeySelective(new LeaveIntegralExchangeRule(id[i], integralValue[i], day[i]));
		}

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(null);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 疗修养管理导出
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/leave_train_export", method = RequestMethod.GET)
	public void leaveTrainExport(@Param("policeMonth") Integer type, @Param("departmentId") Integer departmentId,
			@Param("keyword") String keyWord, HttpServletResponse response) {

		try {
			ExportExcelUtils.exportExcel(response, doExcel1(type, departmentId, keyWord));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private List<Map<String, Object>> doExcel1(Integer type, Integer departmentId, String keyWord) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();

		String fileName = "疗休养管理" + DateUtil.format(new Date(), "yyyyMMddHHmmss");
		String excelHeader[] = { "姓名", "警号", "职位", "所属单位", "类型" };

		String sheetName = "疗休养管理" + DateUtil.format(new Date(), "yyyy-MM-dd");

		// 文件名
		map.put("fileName", fileName);
		// 表头
		map.put("excelHeader", excelHeader);
		// 数据
		map.put("keys", excelHeader);
		// sheet名
		map.put("sheetName", sheetName);

		list.add(map);

		// 查询所有疗修养管理
		List<LeaveTrain> leaveTrainList = leaveProcessService.getLeaveTrainList(type, departmentId, keyWord, null);

		for (int i = 0; i < leaveTrainList.size(); i++) {
			LinkedHashMap<String, Object> mapBody = new LinkedHashMap<String, Object>();
			mapBody.put("姓名", leaveTrainList.get(i).getPoliceName());
			mapBody.put("警号", leaveTrainList.get(i).getPoliceId());
			mapBody.put("职位", leaveTrainList.get(i).getPositionName());
			mapBody.put("所属单位", leaveTrainList.get(i).getDepartmentName());
			mapBody.put("类型", leaveTrainList.get(i).getType() == 1 ? "连续三年未休养" : "	立功受奖优先安排人数");
			list.add(mapBody);
		}

		return list;

	}

	/**
	 * 积分管理导出
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/leave_integral_manage_export", method = RequestMethod.GET)
	public void leaveIntegralManageExport(@Param("policeMonth") Integer policeMonth,
			@Param("departmentId") Integer departmentId, @Param("keyword") String keyword,
			HttpServletResponse response) {

		try {
			ExportExcelUtils.exportExcel(response, doExcel2(policeMonth, departmentId, keyword));
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private List<Map<String, Object>> doExcel2(Integer policeMonth, Integer departmentId, String keyword) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();

		String fileName = "积分管理" + DateUtil.format(new Date(), "yyyyMMddHHmmss");
		String excelHeader[] = { "被记分人", "警号", "公安月", "所属单位", "积分值", "记分人", "记分日期" };

		String sheetName = "积分管理" + DateUtil.format(new Date(), "yyyy-MM-dd");

		// 文件名
		map.put("fileName", fileName);
		// 表头
		map.put("excelHeader", excelHeader);
		// 数据
		map.put("keys", excelHeader);
		// sheet名
		map.put("sheetName", sheetName);

		list.add(map);

		// 查询所有积分管理
		List<LeaveIntegralManage> leaveIntegralManage = leaveProcessService.getLeaveIntegralManageList(policeMonth,
				departmentId, keyword, null);

		for (int i = 0; i < leaveIntegralManage.size(); i++) {
			LinkedHashMap<String, Object> mapBody = new LinkedHashMap<String, Object>();
			mapBody.put("被记分人", leaveIntegralManage.get(i).getScoredPoliceName());
			mapBody.put("警号", leaveIntegralManage.get(i).getScoredPoliceId());
			mapBody.put("公安月", leaveIntegralManage.get(i).getPoliceMonth());
			mapBody.put("所属单位", leaveIntegralManage.get(i).getDepartmentName());
			mapBody.put("积分值", leaveIntegralManage.get(i).getIntegralValue());
			mapBody.put("记分人", leaveIntegralManage.get(i).getScoringPoliceName());
			mapBody.put("记分日期", DateUtil.format(leaveIntegralManage.get(i).getScoringDate(), "yyyy/MM/dd HH:mm"));
			list.add(mapBody);
		}

		return list;

	}

	/**
	 * 获得疗修养管理模板
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/alarm/leave_train_excel", method = RequestMethod.GET)
	public void leaveTrainExcel(HttpServletResponse response) {

		try {
			LeaveTrainExcel.exportExcel(response, getLeaveTrainExcel());
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private List<Map<String, Object>> getLeaveTrainExcel() {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		String fileName = "疗休养管理模板导入";
		String excelHeader[] = { "姓名", "警号", "类型" };

		String sheetName = "疗休养管理" + DateUtil.format(new Date(), "yyyy-MM-dd");
		// 文件名
		map.put("fileName", fileName);
		// 表头
		map.put("excelHeader", excelHeader);
		// 数据
		map.put("keys", excelHeader);
		// sheet名
		map.put("sheetName", sheetName);

		list.add(map);

		return list;

	}

	/**
	 * 疗休养管理导入
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/leave_train_import", method = RequestMethod.POST)
	public ResponseEntity<?> leaveTrainImport(@Param("file") MultipartFile file) {

		List<List<String>> readExcel = null;
		try {
			readExcel = GetExcel.ReadExcel(file);
		} catch (Exception e) {

			e.printStackTrace();
		}

		// 读取到的数据
		List<LeaveTrain> list = new ArrayList<LeaveTrain>();

		// 记录错误行
		int index = 0;

		try {
			for (List<String> excel : readExcel) {
				// 累计错误行
				index++;

				LeaveTrain leaveTrain = new LeaveTrain();
				leaveTrain.setPoliceId(excel.get(1).toString().substring(0, 6));
				leaveTrain.setType(excel.get(2).toString().equals("连续三年未休养") ? 1 : 2);

				list.add(leaveTrain);

			}
		} catch (Exception e) {

			e.printStackTrace();

			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getFailcode());
			dataListReturn.setMessage("faile");
			dataListReturn.setResult("第" + index + "条数据错误,导入失败!");
			dataListReturn.setStatus(true);
			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
		}

		// 批量添加到数据库
		leaveProcessService.addMoreLeaveTrain(list);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(null);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * 获得积分管理模板
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/alarm/leave_integral_manage_excel", method = RequestMethod.GET)
	public void leaveIntegralManageExcel(HttpServletResponse response) {

		String[] months;
		String[] department;

		try {

			// 查询公安月
			List<AlarmPoliceMonth> allMonth = alarmService.getAllMonth();
			months = new String[allMonth.size()];
			for (int i = 0; i < allMonth.size(); i++) {
				months[i] = allMonth.get(i).getId().toString();
			}

			// 查询部门
			List<Department> allDepartment = departmentService.findAll();
			department = new String[allDepartment.size()];
			for (int i = 0; i < allDepartment.size(); i++) {
				department[i] = allDepartment.get(i).getName();
			}

			new LeaveIntegralManageExcel(department, months).exportExcel(response, getLeaveIntegralManageExcel());
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private List<Map<String, Object>> getLeaveIntegralManageExcel() {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		String fileName = "积分管理模板导入";
		String excelHeader[] = { "被记分人", "警号", "所属单位", "公安月", "积分值", "记分人", "警号", "记分日期" };

		String sheetName = "积分管理" + DateUtil.format(new Date(), "yyyy-MM-dd");
		// 文件名
		map.put("fileName", fileName);
		// 表头
		map.put("excelHeader", excelHeader);
		// 数据
		map.put("keys", excelHeader);
		// sheet名
		map.put("sheetName", sheetName);

		list.add(map);

		return list;

	}

	/**
	 * 积分管理导入
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/leave_integral_manage_import", method = RequestMethod.POST)
	public ResponseEntity<?> leaveIntegralManageImport(@Param("file") MultipartFile file) {

		List<List<String>> readExcel = null;
		try {
			readExcel = GetExcel.ReadExcel(file);
		} catch (Exception e) {

			e.printStackTrace();
		}

		// 读取到的数据
		List<LeaveIntegralManage> list = new ArrayList<LeaveIntegralManage>();

		// 将新增的数据依次转为记分 放入集合
		ArrayList<LeavePoints> leavePointsList = new ArrayList<LeavePoints>();

		// 记录错误行
		int index = 0;

		try {
			for (List<String> excel : readExcel) {
				// 累计错误行
				index++;

				LeaveIntegralManage leaveIntegralManage = new LeaveIntegralManage();
				leaveIntegralManage.setScoredPoliceName(excel.get(0).toString());
				leaveIntegralManage.setScoredPoliceId(excel.get(1).toString().substring(0, 6));
				// 根据部门名称查询部门id
				leaveIntegralManage
						.setDepartmentId(departmentService.getDepartmentByName(excel.get(2).toString()).getId());
				leaveIntegralManage.setPoliceMonthId(Double.valueOf(excel.get(3).toString()).intValue());
				leaveIntegralManage.setIntegralValue(Double.valueOf(excel.get(4).toString()));
				leaveIntegralManage.setScoringPoliceId(excel.get(6).toString().substring(0, 6));
				leaveIntegralManage.setScoringDate(DateUtil.parseDate(excel.get(7).toString()));

				// 判断是否重复
				// 判断该月是否已新增过积分
				int num = leaveProcessService.nowMonthHave(leaveIntegralManage.getScoredPoliceId(),
						leaveIntegralManage.getPoliceMonthId());

				if (num != 0) {
					DataListReturn dataListReturn = new DataListReturn();
					dataListReturn.setCode(StatusCode.getFailcode());
					dataListReturn.setMessage("faile");
					dataListReturn.setResult("第" + index + "条数据重复,导入失败!");
					dataListReturn.setStatus(true);
					return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
				}

				// 处理每一条导入的数据
				maintainIntegralDate(leaveIntegralManage, true);
				// 转换时间
				leaveIntegralManage.setScoringDate(fmtDate(leaveIntegralManage.getPoliceMonthId()));

				leavePointsList.add(maintainIntegral(leaveIntegralManage));

				list.add(leaveIntegralManage);

			}
		} catch (Exception e) {

			e.printStackTrace();

			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getFailcode());
			dataListReturn.setMessage("faile");
			dataListReturn.setResult("第" + index + "条数据错误,导入失败!");
			dataListReturn.setStatus(true);
			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
		}

		// 批量添加到数据库
		leaveProcessService.addMoreLeaveIntegralManage(list);

		// 批量维护积分
		leaveProcessService.addMoreleavePoints(leavePointsList);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(null);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}


	/**
	 * 本周个人加班前五
	 * @return
	 */
	@RequestMapping(value = "/leave/index/overtime-top-five", method = RequestMethod.POST)
	public ResponseEntity<?> getLeaveOverTimeTopFiveWeek() {
		
		List<LeaveOvertime> leaveOverTimeTopFiveWeek = leaveProcessService.leaveOverTimeTopFiveWeek();
		
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(leaveOverTimeTopFiveWeek);
		dataListReturn.setStatus(true);
		
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
		
	}
	
	/**
	 * 全局年假使用情况
	 * @return
	 */
	@RequestMapping(value = "/leave/index/year-leave-Rator", method = RequestMethod.POST)
	public ResponseEntity<?> getNewThreeTalk() {
		
		Double leaveYearUsedRator = leaveProcessService.leaveYearUsedRator();
		
		LinkedHashMap<String, Double> map = new LinkedHashMap<String, Double>();
		map.put("leaveYearUsedRator", leaveYearUsedRator);
		map.put("leaveYearNotUseRator", 100 - leaveYearUsedRator);
		
		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(map);
		dataListReturn.setStatus(true);
		
		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
		
	}

}

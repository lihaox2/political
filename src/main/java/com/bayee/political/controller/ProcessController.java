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

	SimpleDateFormat sdfs = new SimpleDateFormat("yyyy???MM???dd???");

	String currentYearStr = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));

	DecimalFormat df = new DecimalFormat("#.00");

	/**
	 * ????????????????????????
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

			if (StringUtils.isNotEmpty(name) && name.contains("??????")) {
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
	 * ?????????????????????????????????
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
	 * ??????????????????????????????????????????1??????????????????
	 * 
	 * @throws ParseException
	 */
	@Scheduled(cron = "0 */1 * * * ?") // ??????1????????????
//	@RequestMapping(value = "/process/save/leader", method = RequestMethod.GET)
	public void autoSnycLeaveProcessFromDingtalk() throws ApiException, ParseException {
		String accessToken = getAccessToken();
		List<LeaveProcessCode> leaveProcessList = leaveProcessCodeService.findAll();
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.MINUTE, -1);// 1??????????????????
		long startTime = nowTime.getTime().getTime();
//		Calendar cal = Calendar.getInstance();// ????????????Calendar??????
//		cal.setTime(new Date());
//		cal.add(Calendar.MONTH, -1);// ??????????????????????????????
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
			// ???????????????????????????MODIFY????????????????????????????????????????????????????????????
			// REVOKE??????????????????????????????????????????????????????NONE??????????????????
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
			String result = instance.getResult(); // ????????????????????? agree ??? refuse
			leaveProcess.setResult(result);
			String status = instance.getStatus(); // ?????????????????????NEW??????????????? RUNNING???????????????TERMINATED???????????????COMPLETED????????????
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
//					String startDate = leaveFormFiledValues[0]; // ??????????????????
//					System.out.println("??????????????????:" + leaveFormFiledValues[0]);
//					leaveProcess.setLeaveStartDate(DateUtils.toDate(startDate));
//					String endDate = leaveFormFiledValues[1]; // ??????????????????
//					leaveProcess.setLeaveEndDate(DateUtils.toDate(endDate));
					String startDate = strsToList.get(0); // ??????????????????
					leaveProcess.setLeaveStartDate(formatter.parse(startDate));
//					System.out.println("??????????????????:" + startDate);
					String endDate = strsToList.get(1); // ??????????????????
					leaveProcess.setLeaveEndDate(formatter.parse(endDate));
//					System.out.println("??????????????????:" + endDate);
					double durationInDays = Double.valueOf(leaveFormFiledValues[2]); // ????????????
					leaveProcess.setLeaveDuarationDays(durationInDays);
					String leaveType = leaveFormFiledValues[4]; // ????????????
					leaveProcess.setLeaveType(leaveType);

				} else if (formValue.getComponentType().equals("TextareaField")) {
					if (formValue.getName().equals("????????????")) {
						String leaveReason = formValue.getValue();
						leaveProcess.setLeaveReason(leaveReason);
					}

				} else if (formValue.getComponentType().equals("DDSelectField")) {
					if (formValue.getId().equals("DDSelectField-IUTW8VHE")) { // ????????????
						String leaveType = formValue.getValue();
						leaveProcess.setLeaveType(leaveType);
					}
				} else if (formValue.getComponentType().equals("TextField")) {
					if (formValue.getId().equals("TextField-J2JZF425")) { // ???????????????
						String destination = formValue.getValue();
						leaveProcess.setDestination(destination);
					}
				} else if (formValue.getComponentType().equals("DDMultiSelectField")) {
					if (formValue.getId().equals("DDMultiSelectField-J2JZF426")) { // ????????????
						String transportation = formValue.getValue();
						leaveProcess.setTransportation(transportation);
					}
				} else if (formValue.getComponentType().equals("DDDateRangeField")) {
					if (formValue.getId().equals("DDDateRangeField-IUTW8VHG")) { // ??????
						String[] dateRanges = formValue.getValue().replaceAll("\\[", "").replaceAll("\\]", "")
								.replaceAll("\"", "").split(",");
						String startDate = dateRanges[0]; // ??????????????????
						leaveProcess.setLeaveStartDate(DateUtils.toDate(startDate));
						String endDate = dateRanges[1]; // ??????????????????
						leaveProcess.setLeaveEndDate(DateUtils.toDate(endDate));
						double durationInDays = Double.valueOf(dateRanges[2]); // ????????????
						leaveProcess.setLeaveDuarationDays(durationInDays);
					}
				} else if (formValue.getComponentType().equals("TextareaField")) {
					if (formValue.getId().equals("TextareaField-IUTW8VHI")) { // ??????
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
				// ????????????????????????????????????????????????????????????????????????????????????????????????????????????
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

			// ????????????????????????
			leaveProcessAlert.process(leaveProcess);
		}
	}

	// -----------------AI??????(??????)api-----------------------
	// ???????????????????????????
	@RequestMapping(value = "/leave/alarm/leader/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> leaveAlarmLeaderStatistics(
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		LeaveAlarmLeaderStatistics item = new LeaveAlarmLeaderStatistics();
		// ????????????
		int policeNum = userService.userProcessCount(departmentId, null, null, null);
		item.setPoliceNum(policeNum);
		// ?????????????????????
		int annualLeaveNum = leaveProcessService.annualLeaveNum(departmentId);
		item.setAnnualLeaveNum(annualLeaveNum);
		// ????????????????????????
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

	// ?????????????????????????????????(1???)
	@RequestMapping(value = "/leave/leader/compensatory/record/newest/list", method = RequestMethod.GET)
	public ResponseEntity<?> leaveLeaderCompensatoryRecordNewestList(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		LeaveCompensatoryRecordStatistics recordItme = new LeaveCompensatoryRecordStatistics();
		// ??????????????????????????????
		List<LeaveCompensatoryRecord> list = leaveProcessService.leaveCompensatoryRecordNewestList(policeId, 0);
		// ??????????????????????????????
		int total = leaveProcessService.leaveCompensatoryRecordCount(policeId, null, null, 0);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int timeItem = Math.abs(list.get(i).getTimeChange());
				if (timeItem == 0) {
					list.get(i).setCreationDateStr("1?????????");
				} else if (timeItem < 60 && timeItem > 0) {
					list.get(i).setCreationDateStr(timeItem + "?????????");
				} else if (timeItem >= 60 && timeItem < 1440) {
					int timeInt = (int) Math.floor(timeItem / 60);
					list.get(i).setCreationDateStr(timeInt + "?????????");
				} else if (timeItem >= 1440 && timeItem <= 4320) {
					int timeInt = (int) Math.floor(timeItem / 60 / 24);
					list.get(i).setCreationDateStr(timeInt + "??????");
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

	// ???????????????????????????????????????
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
		// ????????????????????????
		List<LeaveCompensatoryRecord> list = leaveProcessService.leaveCompensatoryRecordList(policeId, type, dateTime,
				null, pageSize, pageNum);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int timeItem = Math.abs(list.get(i).getTimeChange());
				if (timeItem == 0) {
					list.get(i).setCreationDateStr("1?????????");
				} else if (timeItem < 60 && timeItem > 0) {
					list.get(i).setCreationDateStr(timeItem + "?????????");
				} else if (timeItem >= 60 && timeItem < 1440) {
					int timeInt = (int) Math.floor(timeItem / 60);
					list.get(i).setCreationDateStr(timeInt + "?????????");
				} else if (timeItem >= 1440 && timeItem <= 4320) {
					int timeInt = (int) Math.floor(timeItem / 60 / 24);
					list.get(i).setCreationDateStr(timeInt + "??????");
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
		// ??????????????????????????????
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
//		// ?????????????????????????????????
//		List<LeaveCompensatoryReadRecord> list = leaveProcessService.leaveLeaderCompensatoryRecordList(policeId, type,
//				dateTime, null, pageSize, pageNum);
//		list.removeAll(Collections.singleton(null));
//		if (list.size() > 0) {
//			for (int i = 0; i < list.size(); i++) {
//				int timeItem = Math.abs(list.get(i).getTimeChange());
//				if (timeItem == 0) {
//					list.get(i).setCreationDateStr("1?????????");
//				} else if (timeItem < 60 && timeItem > 0) {
//					list.get(i).setCreationDateStr(timeItem + "?????????");
//				} else if (timeItem >= 60 && timeItem < 1440) {
//					int timeInt = (int) Math.floor(timeItem / 60);
//					list.get(i).setCreationDateStr(timeInt + "?????????");
//				} else if (timeItem >= 1440 && timeItem <= 4320) {
//					int timeInt = (int) Math.floor(timeItem / 60 / 24);
//					list.get(i).setCreationDateStr(timeInt + "??????");
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
//		// ???????????????????????????????????????
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

	// ????????????????????????????????????(??????3???)
	@RequestMapping(value = "/leave/leader/compensatory/alarm/newest/list", method = RequestMethod.GET)
	public ResponseEntity<?> leaveLeaderCompensatoryAlarmNewestList(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		LeaveLeaderAlarmList itme = new LeaveLeaderAlarmList();
		// ????????????????????????????????????(??????3???)
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
					list.get(i).setCreationDateStr("1?????????");
				} else if (timeItem < 60 && timeItem > 0) {
					list.get(i).setCreationDateStr(timeItem + "?????????");
				} else if (timeItem >= 60 && timeItem < 1440) {
					int timeInt = (int) Math.floor(timeItem / 60);
					list.get(i).setCreationDateStr(timeInt + "?????????");
				} else if (timeItem >= 1440 && timeItem < 2880) {
//					String dateTime = sdf.format(list.get(i).getCreationDate()).substring(11, 16);
					list.get(i).setCreationDateStr("1??????");
				} else if (timeItem >= 2880 && timeItem < 4320) {
//					String dateTime = sdf.format(list.get(i).getCreationDate()).substring(11, 16);
					list.get(i).setCreationDateStr("2?????????");
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
		// ????????????????????????
		int count = leaveProcessService.leaveLeaderCompensatoryAlarmNewestCount(policeId);
		itme.setNum(count);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(itme);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ??????????????????????????????????????????
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
		// ??????????????????????????????
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
					list.get(i).setCreationDateStr("1?????????");
				} else if (timeItem < 60 && timeItem > 0) {
					list.get(i).setCreationDateStr(timeItem + "?????????");
				} else if (timeItem >= 60 && timeItem < 1440) {
					int timeInt = (int) Math.floor(timeItem / 60);
					list.get(i).setCreationDateStr(timeInt + "?????????");
				} else if (timeItem >= 1440 && timeItem < 2880) {
					list.get(i).setCreationDateStr("1??????");
				} else if (timeItem >= 2880 && timeItem < 4320) {
					list.get(i).setCreationDateStr("2?????????");
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
		// ????????????????????????
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

	// ??????????????????????????????????????????
	@RequestMapping(value = "/leave/leader/compensatory/alarm/item", method = RequestMethod.GET)
	public ResponseEntity<?> leaveLeaderCompensatoryAlarmItem(@RequestParam(value = "id", required = false) Integer id)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// ??????????????????????????????????????????
		LeaveCompensatoryAlarm item = leaveProcessService.leaveLeaderCompensatoryAlarmItem(id);
		if (item != null) {
			if (item.getRuleId() == 1) {// ????????????id(1????????????????????????????????????2?????????????????????????????????3???????????????????????????4?????????????????????3??????5???????????????????????????5???)
				item.setReason(item.getName() + "??????????????????????????????" + item.getWorkDays() + "????????????????????????");
			} else if (item.getRuleId() == 2) {
				item.setReason(item.getName() + "???????????????????????????" + item.getWorkDays() + "????????????????????????");
			} else if (item.getRuleId() == 3) {
				item.setReason(item.getName() + "????????????" + item.getWorkDays() + "????????????????????????");
			} else if (item.getRuleId() == 4) {
				item.setReason(item.getName() + "????????????????????????" + item.getWorkDays() + "????????????????????????");
			} else if (item.getRuleId() == 5) {
				item.setReason(item.getName() + "????????????????????????" + item.getWorkDays() + "????????????????????????");
			}
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ?????????????????????????????????(3???)
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
		// ??????????????????(3???)??????
		List<LeaveLeaderAlarm> list = leaveProcessService.leaveLeaderPointsAlarmNewestList(policeId, currentYear,
				halfYear);
		list.removeAll(Collections.singleton(null));
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				// ?????????????????????????????????
				LeavePersonalStatistics count2 = leaveProcessService
						.leavePersonalpointsChangeDays(list.get(i).getPoliceId(), currentYear, halfYear);
				if (count2 != null) {
					double currentScore = count2.getPointsChangeDays();// ??????(???);
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
		// ???????????????????????????????????????
		int count = leaveProcessService.leaveLeaderPointsAlarmNewestCount(policeId, currentYear, halfYear);
		itme.setNum(count);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(itme);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ?????????????????????????????????????????????
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
		// ??????????????????????????????
		List<LeaveLeaderAlarm> list = leaveProcessService.leaveLeaderPointsAlarmList(policeId, currentYear, halfYear,
				pageSize, pageNum);
		list.removeAll(Collections.singleton(null));
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				// ?????????????????????????????????
				LeavePersonalStatistics count2 = leaveProcessService
						.leavePersonalpointsChangeDays(list.get(i).getPoliceId(), currentYear, halfYear);
				if (count2 != null) {
					double currentScore = count2.getPointsChangeDays();// ??????(???);
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
		// ??????????????????????????????
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

	// ?????????????????????(3???)
	@RequestMapping(value = "/leave/leader/overtime/alarm/newest/list", method = RequestMethod.GET)
	public ResponseEntity<?> leaveLeaderOvertimeAlarmNewestList(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		LeaveLeaderAlarmList itme = new LeaveLeaderAlarmList();
		// ????????????(3???)??????
		List<LeaveLeaderAlarm> list = leaveProcessService.leaveLeaderOvertimeAlarmNewestList(policeId);
		list.removeAll(Collections.singleton(null));
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				// ?????????????????????????????????
				LeavePersonalStatistics count = leaveProcessService
						.leavePersonalOverTimeChangeDays(list.get(i).getPoliceId(), currentYear);
				if (count != null) {
					double currentScore = count.getOvertimeChangeDays();// ??????(???);
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
		// ??????????????????
		int count = leaveProcessService.leaveLeaderOvertimeAlarmNewestCount(policeId);
		itme.setNum(count);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(itme);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ???????????????????????????????????????
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
		// ????????????????????????
		List<LeaveLeaderAlarm> list = leaveProcessService.leaveLeaderOvertimeAlarmList(policeId, pageSize, pageNum);
		list.removeAll(Collections.singleton(null));
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				// ?????????????????????????????????
				LeavePersonalStatistics count = leaveProcessService
						.leavePersonalOverTimeChangeDays(list.get(i).getPoliceId(), currentYear);
				if (count != null) {
					double currentScore = count.getOvertimeChangeDays();// ??????(???);
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
		// ????????????????????????
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

	// ??????????????????(2???)
	@RequestMapping(value = "/leave/leader/need/dealt/newest/list", method = RequestMethod.GET)
	public ResponseEntity<?> leaveLeaderNeedDealtNewestList(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		LeaveLeaderAlarmList itme = new LeaveLeaderAlarmList();
		// ??????????????????(2???)
		List<LeaveCompensatoryRecord> list = leaveProcessService.leaveLeaderNeedDealtNewestList(policeId);
		list.removeAll(Collections.singleton(null));
		for (int i = 0; i < list.size(); i++) {
			int timeItem = list.get(i).getTimeChange();
			if (timeItem == 0) {
				list.get(i).setCreationDateStr("1?????????");
			} else if (timeItem < 60 && timeItem > 0) {
				list.get(i).setCreationDateStr(timeItem + "?????????");
			} else if (timeItem >= 60 && timeItem < 1440) {
				int timeInt = (int) Math.floor(timeItem / 60);
				list.get(i).setCreationDateStr(timeInt + "?????????");
			} else if (timeItem >= 1440 && timeItem < 4320) {
				int timeInt = (int) Math.floor(timeItem / 60 / 24);
				list.get(i).setCreationDateStr(timeInt + "??????");
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
		// ????????????????????????
		int count = leaveProcessService.leaveLeaderNeedDealtNewestCount(policeId);
		itme.setNum(count);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(itme);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ??????????????????????????????
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
			// ????????????????????????
			list = leaveProcessService.leaveLeaderNeedDealtList(policeId, type, dateTime, pageSize, pageNum);
			// ????????????????????????
			total = leaveProcessService.leaveLeaderNeedDealtCount(policeId, type, dateTime);
		} else if (objectType == 2) {
			// ????????????????????????
			list = leaveProcessService.leaveLeaderOverDealtList(policeId, type, dateTime, pageSize, pageNum);
			// ????????????????????????
			total = leaveProcessService.leaveLeaderOverDealtCount(policeId, type, dateTime);
		}
		for (int i = 0; i < list.size(); i++) {
			int timeItem = list.get(i).getTimeChange();
			if (timeItem == 0) {
				list.get(i).setCreationDateStr("1?????????");
			} else if (timeItem < 60 && timeItem > 0) {
				list.get(i).setCreationDateStr(timeItem + "?????????");
			} else if (timeItem >= 60 && timeItem < 1440) {
				int timeInt = (int) Math.floor(timeItem / 60);
				list.get(i).setCreationDateStr(timeInt + "?????????");
			} else if (timeItem >= 1440 && timeItem < 4320) {
				int timeInt = (int) Math.floor(timeItem / 60 / 24);
				list.get(i).setCreationDateStr(timeInt + "??????");
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

	// ???????????????????????????
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
		// ??????policeId????????????
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
				dlr.setMessage("????????????????????????????????????");
				dlr.setResult(0);
				dlr.setCode(StatusCode.getFailcode());
				return dlr;
			}
			if (startTimeLong > endTimeLong) {
				dlr.setStatus(false);
				dlr.setMessage("????????????????????????????????????");
				dlr.setResult(0);
				dlr.setCode(StatusCode.getFailcode());
				return dlr;
			}
		}
		item.setItemId(itemId);
		if (isRest == 0) {
			item.setResult("?????????????????????????????????");
			item.setApprovedResult("????????????????????????");
		} else if (isRest == 1) {
			item.setResult("?????????????????????");
			item.setApprovedResult("??????????????????");
		}
		item.setApprovedId(approvedId);
		item.setReadStatus(0);
		item.setApprovedReadStatus(0);
		item.setDealTime(new Date());
		item.setCreationDate(new Date());
		int count = leaveProcessService.leaveCompensatoryRecordCreat(item);
		if (count > 0) {
			LeaveCompensatoryRecord recordItem = leaveProcessService.leaveCompensatoryRecordItem(item.getId());
			// ??????????????????????????????????????????
			LeaveCompensatoryAlarm aItem = leaveProcessService.leaveLeaderCompensatoryAlarmItem(itemId);
			if (aItem != null) {
				LeaveCompensatoryAlarm comItem = new LeaveCompensatoryAlarm();
				comItem.setId(itemId);
				comItem.setIsDeal(1);
				comItem.setUpdateDate(new Date());
				leaveProcessService.leaveCompensatoryAlarmUpdate(comItem);
				if (aItem.getRuleId() == 1) {// ????????????id(1????????????????????????????????????2?????????????????????????????????3???????????????????????????4?????????????????????3??????5???????????????????????????5???)
					recordItem.setLeaveReason(aItem.getName() + "??????????????????????????????" + aItem.getWorkDays() + "????????????????????????");
				} else if (aItem.getRuleId() == 2) {
					recordItem.setLeaveReason(aItem.getName() + "???????????????????????????" + aItem.getWorkDays() + "????????????????????????");
				} else if (aItem.getRuleId() == 3) {
					recordItem.setLeaveReason(aItem.getName() + "????????????" + aItem.getWorkDays() + "????????????????????????");
				} else if (aItem.getRuleId() == 4) {
					recordItem.setLeaveReason(aItem.getName() + "????????????????????????" + aItem.getWorkDays() + "????????????????????????");
				} else if (aItem.getRuleId() == 5) {
					recordItem.setLeaveReason(aItem.getName() + "????????????????????????" + aItem.getWorkDays() + "????????????????????????");
				}
			}
			if (isRest == 0) {
				recordItem.setResult("??????????????????");
			} else if (isRest == 1) {
				recordItem.setResult("???????????????");
				double times = recordItem.getTimeChange();
				double currentScore = times / 1440.0;// ??????(???);
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
			// ??????????????????????????????
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
			dlr.setMessage("????????????");
			dlr.setResult(recordItem);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("????????????");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
		}
		return dlr;
	}

	// ???????????????????????????
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
		// ??????policeId????????????
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
							dlr.setMessage("????????????????????????????????????");
							dlr.setResult(0);
							dlr.setCode(StatusCode.getFailcode());
							return dlr;
						}
						if (startTimeLong > endTimeLong) {
							dlr.setStatus(false);
							dlr.setMessage("????????????????????????????????????");
							dlr.setResult(0);
							dlr.setCode(StatusCode.getFailcode());
							return dlr;
						}
					}
				} else {
					if (startTimeLong < currentTimeLong) {
						dlr.setStatus(false);
						dlr.setMessage("????????????????????????????????????");
						dlr.setResult(0);
						dlr.setCode(StatusCode.getFailcode());
						return dlr;
					}
					if (startTimeLong > endTimeLong) {
						dlr.setStatus(false);
						dlr.setMessage("????????????????????????????????????");
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
			dlr.setMessage("????????????");
			dlr.setResult(count);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("????????????");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
		}
		return dlr;
	}

	// ?????????????????????????????????
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
		// ????????????????????????
		LeaveCompensatoryRecord recordItem = leaveProcessService.leaveCompensatoryRecordItem(id);
		item.setStartTime(recordItem.getStartTime());
		item.setEndTime(recordItem.getEndTime());
		long startTimeLong = recordItem.getStartTime().getTime();
		long endTimeLong = recordItem.getEndTime().getTime();
		if (status == 2) {
			item.setIsRest(1);
			if (recordItem != null) {
				if (type == 3) {
					// ??????????????????
					LeavePersonalStatistics pItem = leaveProcessService
							.leavePersonalPointsItems(recordItem.getPoliceId(), currentYear, halfYear);
					// ????????????????????????????????????
					LeavePointsExchangeRecord eItem = leaveProcessService
							.leavePointsExchangeRecordItem(recordItem.getItemId());
					LeavePoints lItem = new LeavePoints();
					lItem.setPoliceId(recordItem.getPoliceId());
					lItem.setYear(currentYear);
					lItem.setHalfYear(halfYear);
					lItem.setResidualPoints(pItem.getResidualPoints() + eItem.getPoints());
					lItem.setUpdateDate(new Date());
					leaveProcessService.leavePointsResidualUpdate(lItem);// ????????????????????????
					LeavePointsExchangeRecord record = new LeavePointsExchangeRecord();
					record.setId(recordItem.getItemId());
					record.setStatus(3);
					record.setUpdateDate(new Date());
					leaveProcessService.leavePointsExchangeRecordUpdate(record);
				} else if (type == 2) {
					// ??????????????????????????????
					List<LeaveOvertimeRule> ruleList = leaveProcessService.getLeaveOvertimeRuleList();
					double ruleHour = ruleList.get(0).getHour();// ??????????????????6
					double ruleComDay = ruleList.get(0).getDay1();// ???????????????0.5
					double endTimeDou = endTimeLong;
					double startTimeDou = startTimeLong;
					double currentScore = (endTimeDou - startTimeDou) / 86400000.00;// ??????(???);
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
					// ????????????????????????
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
			dlr.setMessage("????????????");
			dlr.setResult(count);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("????????????");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
		}
		return dlr;
	}

	// ???????????????????????????
	@RequestMapping(value = "/leave/dep/annual/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> leaveDepAnnualStatistics(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		LeaveAlarmLeaderStatistics item = new LeaveAlarmLeaderStatistics();
		// ?????????????????????????????????
		int depAnnualLeaveNum = leaveProcessService.depAnnualLeaveNum();
		item.setAnnualLeaveNum(depAnnualLeaveNum);
		// ?????????????????????????????????
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

	// ??????????????????????????????
	@RequestMapping(value = "/leave/dep/annual/not/standard/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> leaveDepAnnualNotStandardStatistics(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		LeaveAlarmLeaderStatistics item = new LeaveAlarmLeaderStatistics();
		// ?????????????????????????????????
		int depAnnualLeaveNum = leaveProcessService.depAnnualLeaveNum();
		item.setAnnualLeaveNum(depAnnualLeaveNum);
		// ?????????????????????????????????
		int totalDepAnnualLeaveNum = leaveProcessService.totalDepAnnualLeaveNum();
		item.setNotAnnualLeaveNum(totalDepAnnualLeaveNum - depAnnualLeaveNum);
		double totalNum = totalDepAnnualLeaveNum;
		double annualLeaveNumDou = depAnnualLeaveNum;
		double notAnnualLeaveNum = item.getNotAnnualLeaveNum();
		item.setAnnualLeaveRate(Double.valueOf(df.format(annualLeaveNumDou / totalNum * 100)));
		item.setNotAnnualLeaveRate(Double.valueOf(df.format(notAnnualLeaveNum / totalNum * 100)));
		// ???????????????????????????
		List<LeaveChart> list = leaveProcessService.leaveDepAnnualNotStandardStatistics();
		item.setDepList(list);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ????????????????????????
	@RequestMapping(value = "/leave/dep/train/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> leaveDepTrainStatistics(@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		LeaveTrainStatistics item = new LeaveTrainStatistics();
		// ????????????????????????????????????(??????????????????????????????)??????(1?????????????????????2??????????????????????????????)
		int count = leaveProcessService.leaveDepTrainStatisticsNum(2, departmentId);
		// ????????????????????????????????????(???????????????????????????)
		int count2 = leaveProcessService.leaveDepTrainStatisticsNum(1, departmentId);
		// ???????????????????????????????????????????????????
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

	// ?????????????????????????????????
	@RequestMapping(value = "/leave/dep/compensatory/chart", method = RequestMethod.GET)
	public ResponseEntity<?> leaveDepCompensatoryChart() throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// ??????????????????????????????
		List<CalculationChart> list = leaveProcessService.leaveDepCompensatoryChart();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ???????????????????????????????????????
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
		// ????????????????????????????????????
		List<CalculationChart> list = leaveProcessService.leaveDepPointsExchangeChart(currentYear, halfYear);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// -----------------AI??????(??????)api-----------------------
	// ????????????????????????
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
		// ???????????????????????????api???
		LeavePersonalStatistics statisticsItem = leaveProcessService.leavePersonalStatisticsItem(policeId,
				currentYear, halfYear);
		// ?????????????????????????????????
		LeavePersonalStatistics count = leaveProcessService.leavePersonalOverTimeChangeDays(policeId, currentYear);
		if (count != null) {
			double currentScore = count.getOvertimeChangeDays();// ??????(???);
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
		// ?????????????????????????????????
		LeavePersonalStatistics count2 = leaveProcessService.leavePersonalpointsChangeDays(policeId, currentYear,
				halfYear);
		if (count2 != null) {
			double currentScore = count2.getPointsChangeDays();// ??????(???);
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

	// ???????????????????????????
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
		// ???????????????????????????
		LeaveThisYearStatistics yearItem = leaveProcessService.leaveThisYearStatisticsItem(policeId);
		// ???????????????????????????api???
		LeavePersonalStatistics statisticsItem = leaveProcessService.leavePersonalStatisticsItem(policeId,
				currentYear, halfYear);
		yearItem.setTotalPoints(statisticsItem.getPoints());
		yearItem.setResidualPoints(statisticsItem.getResidualPoints());
		// ?????????????????????????????????
		LeavePersonalStatistics count2 = leaveProcessService.leavePersonalpointsChangeDays(policeId, currentYear,
				halfYear);
		if (count2 != null) {
			double currentScore = count2.getPointsChangeDays();// ??????(???);
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

	// ??????????????????????????????????????????(1???)
	@RequestMapping(value = "/leave/personal/compensatory/record/newest/list", method = RequestMethod.GET)
	public ResponseEntity<?> leavePersonalCompensatoryRecordNewestList(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		LeaveCompensatoryRecordStatistics recordItme = new LeaveCompensatoryRecordStatistics();
		// ??????????????????????????????
		List<LeaveCompensatoryRecord> list = leaveProcessService.leaveCompensatoryRecordNewestList(policeId, 0);
		// ??????????????????????????????
		int total = leaveProcessService.leaveCompensatoryRecordCount(policeId, null, null, 0);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int timeItem = Math.abs(list.get(i).getTimeChange());
				if (timeItem == 0) {
					list.get(i).setCreationDateStr("1?????????");
				} else if (timeItem < 60 && timeItem > 0) {
					list.get(i).setCreationDateStr(timeItem + "?????????");
				} else if (timeItem >= 60 && timeItem < 1440) {
					int timeInt = (int) Math.floor(timeItem / 60);
					list.get(i).setCreationDateStr(timeInt + "?????????");
				} else if (timeItem >= 1440 && timeItem <= 4320) {
					int timeInt = (int) Math.floor(timeItem / 60 / 24);
					list.get(i).setCreationDateStr(timeInt + "??????");
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

	// ????????????????????????????????????
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
		// ????????????????????????
		List<LeaveCompensatoryRecord> list = leaveProcessService.leaveCompensatoryRecordList(policeId, type, dateTime,
				null, pageSize, pageNum);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int timeItem = Math.abs(list.get(i).getTimeChange());
				if (timeItem == 0) {
					list.get(i).setCreationDateStr("1?????????");
				} else if (timeItem < 60 && timeItem > 0) {
					list.get(i).setCreationDateStr(timeItem + "?????????");
				} else if (timeItem >= 60 && timeItem < 1440) {
					int timeInt = (int) Math.floor(timeItem / 60);
					list.get(i).setCreationDateStr(timeInt + "?????????");
				} else if (timeItem >= 1440 && timeItem <= 4320) {
					int timeInt = (int) Math.floor(timeItem / 60 / 24);
					list.get(i).setCreationDateStr(timeInt + "??????");
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
		// ??????????????????????????????
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

	// ??????????????????????????????
	@RequestMapping(value = "/leave/personal/compensatory/record/Item", method = RequestMethod.GET)
	public ResponseEntity<?> leaveCompensatoryRecordItem(@RequestParam(value = "id", required = false) Integer id)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// ??????????????????????????????
		LeaveCompensatoryRecord item = leaveProcessService.leaveCompensatoryRecordItem(id);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ????????????????????????
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
		// ??????policeId????????????
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
			dlr.setMessage("????????????????????????????????????");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
		if (startTimeLong > endTimeLong) {
			dlr.setStatus(false);
			dlr.setMessage("????????????????????????????????????");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
		// ??????????????????????????????
		List<LeaveOvertimeRule> ruleList = leaveProcessService.getLeaveOvertimeRuleList();
		double ruleHour = ruleList.get(0).getHour();// ??????????????????6
		double ruleComDay = ruleList.get(0).getDay1();// ???????????????0.5
		double ruleOverDay = ruleList.get(0).getDay2();// ??????????????????????????????2
		double endTimeDou = Double.valueOf(endTimeLong);
		double startTimeDou = Double.valueOf(startTimeLong);
		double currentScore = (endTimeDou - startTimeDou) / 86400000.00;// ??????(???);
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
		// ????????????????????????
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
					dlr.setMessage("????????????????????????????????????");
					dlr.setResult(0);
					dlr.setCode(StatusCode.getFailcode());
					return dlr;
				}
			} else {
				dlr.setStatus(false);
				dlr.setMessage("?????????????????????" + ruleOverDay + "???????????????");
				dlr.setResult(0);
				dlr.setCode(StatusCode.getFailcode());
				return dlr;
			}
		} else {
			dlr.setStatus(false);
			dlr.setMessage("????????????????????????????????????");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
		if (count > 0) {
			dlr.setStatus(true);
			dlr.setMessage("????????????");
			dlr.setResult(count);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("????????????");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
		}
		return dlr;
	}

	// ????????????????????????
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
			dlr.setMessage("????????????");
			dlr.setResult(count);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("????????????");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
		}
		return dlr;
	}

	// ????????????/??????????????????
	@RequestMapping(value = "/leave/personal/points/time/item", method = RequestMethod.GET)
	public ResponseEntity<?> leavePointsTimeItem(@RequestParam(value = "type", required = false) int type,
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		LeavePersonalStatistics item = new LeavePersonalStatistics();
		if (type == 1) {
			// ????????????????????????
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
			// ??????????????????
			item = leaveProcessService.leavePersonalPointsItems(policeId, currentYear, halfYear);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ????????????????????????????????????(2???)
	@RequestMapping(value = "/leave/personal/points/exchange/record/newest/list", method = RequestMethod.GET)
	public ResponseEntity<?> leavePointsExchangeRecordNewestList(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// ????????????????????????????????????
		List<LeavePointsExchangeRecord> list = leaveProcessService.leavePointsExchangeRecordNewestList(policeId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ??????????????????????????????
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
		// ??????????????????????????????
		List<LeavePointsExchangeRecord> list = leaveProcessService.leavePointsExchangeRecordList(policeId, dateTime,
				pageSize, pageNum);
		// ????????????????????????????????????
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

	// ????????????????????????????????????
	@RequestMapping(value = "/leave/personal/points/exchange/record/item", method = RequestMethod.GET)
	public ResponseEntity<?> leavePointsExchangeRecordItem(@RequestParam(value = "id", required = false) Integer id)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// ????????????????????????????????????
		LeavePointsExchangeRecord item = leaveProcessService.leavePointsExchangeRecordItem(id);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ??????????????????????????????
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
		// ??????policeId????????????
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
			dlr.setMessage("????????????????????????????????????");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
		if (startTimeLong > endTimeLong) {
			dlr.setStatus(false);
			dlr.setMessage("????????????????????????????????????");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
		// ??????????????????????????????
		List<LeaveIntegralExchangeRule> ruleList = leaveProcessService.getLeaveIntegralExchangeRuleList();
		double rulePoints = ruleList.get(0).getIntegralValue();// ????????????
		double ruleDay = ruleList.get(0).getDay();// ??????????????????
		double endTimeDou = endTimeLong;
		double startTimeDou = startTimeLong;
		double currentScore = (endTimeDou - startTimeDou) / 86400000.00;// ??????(???);
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
		// ??????????????????
		LeavePersonalStatistics pItem = leaveProcessService.leavePersonalPointsItems(policeId, currentYear,
				halfYear);
		int count = 0;
		int count2 = 0;
		if (pItem.getResidualPoints() >= reducePoints) {
			item.setPoints(-reducePoints);
			item.setRemarks(remarks);
			item.setStatus(status);
			item.setCreationDate(new Date());
			// ?????????????????????????????????
//			LeaveIntegralAuditPower powerItem = leaveProcessService.leavePowerItem(policeId);
//			if (powerItem != null) {
			count = leaveProcessService.leavePointsExchangeRecordCreat(item);
			// ??????????????????
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
			dlr.setMessage("????????????????????????????????????");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			return dlr;
		}
		if (count > 0 && count2 > 0) {
			dlr.setStatus(true);
			dlr.setMessage("????????????");
			dlr.setResult(count);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("????????????");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
		}
		return dlr;
	}

	// ??????????????????????????????
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
			dlr.setMessage("????????????");
			dlr.setResult(count);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("????????????");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
		}
		return dlr;
	}

	// ????????????????????????
	@RequestMapping(value = "/leave/reason/analysis", method = RequestMethod.GET)
	public ResponseEntity<?> leaveReasonAnalysis(@RequestParam(value = "policeId", required = false) String policeId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// ?????????????????????????????????api???
		List<CalculationChart> leaveReasonAnalysisList = leaveProcessService.leaveReasonAnalysisList(policeId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(leaveReasonAnalysisList);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// -----------------AI??????(?????????)api-----------------------
	// ?????????????????????
	@RequestMapping(value = "/leave/annual/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> leaveAnnualStatistics(
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// ????????????
		int policeNum = userService.userListCount(departmentId, null, null, null, null);
		// ???????????????????????????????????????api???
		int leaveDepAverageItem = leaveProcessService.leaveDepAverageItem(departmentId);
		double leaveDepAverage = 0.0;
		double policeNumDou = policeNum;
		double AverageItemDou = leaveDepAverageItem;
		if (policeNum > 0) {
			leaveDepAverage = Double.valueOf(df.format(AverageItemDou / policeNumDou));
		}
		// ????????????????????????api???
		int onHolidayNumItem = leaveProcessService.onHolidayNumItem(departmentId);
		// ????????????????????????api???
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

	// ???????????????????????????
	@RequestMapping(value = "/leave/dep/annual/chart", method = RequestMethod.GET)
	public ResponseEntity<?> leaveDepAnnualChartList(
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// ????????????????????????????????????api???
		List<LeaveChart> leaveDepAnnualChartList = leaveProcessService.leaveDepAnnualChartList();
		// ????????????
		int policeNum = userService.userListCount(departmentId, null, null, null, null);
		// ???????????????????????????????????????api???
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

	// ???12??????????????????????????????
	@RequestMapping(value = "/leave/vacation/chart", method = RequestMethod.GET)
	public ResponseEntity<?> leaveVacationChartList(
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// ??????????????????????????????api???
		List<CalculationChart> leavetotalNumList = leaveProcessService.leavetotalNumList(departmentId);
		// ??????????????????????????????api???
		List<CalculationChart> leaveAnnualNumList = leaveProcessService.leaveAnnualNumList(departmentId);
		// ???????????????????????????api???
		List<CalculationChart> leaveOffNumList = leaveProcessService.leaveOffNumList(departmentId);
		// ???????????????????????????api???
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

	// ????????????????????????
	@RequestMapping(value = "/leave/type/proportion", method = RequestMethod.GET)
	public ResponseEntity<?> leaveType(@RequestParam(value = "departmentId", required = false) Integer departmentId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// ?????????????????????????????????api???
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

	// -----------------AI??????(?????????)api-----------------------

	// ?????????????????????
	@RequestMapping(value = "/leave/overtime/duty/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> leaveOvertimeDutyStatistics(
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		LeaveOverDutyStatistics item = new LeaveOverDutyStatistics();
		// ????????????
		int policeNum = userService.userListCount(departmentId, null, null, null, null);
		item.setPoliceNum(policeNum);
		// ????????????????????????api???
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
		// ????????????????????????api???
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

	// ???????????????????????????
	@RequestMapping(value = "/leave/last/year/line/chart", method = RequestMethod.GET)
	public ResponseEntity<?> leaveLineChartList(
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// ????????????????????????????????????api???
		List<LeaveChart> leaveOvertimeList = clockService.leaveOvertimeList(departmentId);
		// ????????????????????????????????????api???
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

	// ????????????????????????
	@RequestMapping(value = "/leave/dep/overtime/rank", method = RequestMethod.GET)
	public ResponseEntity<?> leaveDepOvertimeRankList(@RequestParam(value = "type", required = false) Integer type)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		String startTime = null;
		String endTime = null;
		Calendar calendar = Calendar.getInstance();
		if (type == null || type == 1) {// ?????????
			calendar.add(Calendar.WEEK_OF_MONTH, 0);
			calendar.set(Calendar.DAY_OF_WEEK, 2);
			Date time = calendar.getTime();
			startTime = new SimpleDateFormat("yyyy-MM-dd").format(time) + " 00:00:00";
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
			cal.add(Calendar.DAY_OF_WEEK, 1);
			Date time2 = cal.getTime();
			endTime = new SimpleDateFormat("yyyy-MM-dd").format(time2) + " 23:59:59";
		} else if (type == 2) {// ?????????
			calendar.setTime(new Date());
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.add(Calendar.MONTH, 0);
			startTime = formatter.format(calendar.getTime()) + " 00:00:00";
			Calendar ca = Calendar.getInstance();
			ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
			endTime = formatter.format(ca.getTime()) + " 59:59:59";
		}
//		List<LeaveChart> list = new ArrayList<LeaveChart>();
//		if (type == null || type == 1) {// ??????
//			// ???????????????????????????api???
//			list = clockService.leaveDepOvertimeRankList();
//		} else if (type == 2) {// ??????
//			// ???????????????????????????api???
//			list = clockService.leaveDepDutyRankList();
//		}
		List<LeaveChart> list = clockService.leaveDepOvertimeRankList(startTime, endTime);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ??????????????????Top10
	@RequestMapping(value = "/leave/personal/overtime/rank", method = RequestMethod.GET)
	public ResponseEntity<?> leavePersonalOvertimeRankList(@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "departmentId", required = false) Integer departmentId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		String startTime = null;
		String endTime = null;
		Calendar calendar = Calendar.getInstance();
		if (type == null || type == 1) {// ?????????
			calendar.add(Calendar.WEEK_OF_MONTH, 0);
			calendar.set(Calendar.DAY_OF_WEEK, 2);
			Date time = calendar.getTime();
			startTime = new SimpleDateFormat("yyyy-MM-dd").format(time) + " 00:00:00";
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
			cal.add(Calendar.DAY_OF_WEEK, 1);
			Date time2 = cal.getTime();
			endTime = new SimpleDateFormat("yyyy-MM-dd").format(time2) + " 23:59:59";
		} else if (type == 2) {// ?????????
			calendar.setTime(new Date());
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.add(Calendar.MONTH, 0);
			startTime = formatter.format(calendar.getTime()) + " 00:00:00";
			Calendar ca = Calendar.getInstance();
			ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
			endTime = formatter.format(ca.getTime()) + " 59:59:59";
		}
		// ??????????????????Top10???api???
		List<ClockRecord> leavePersonalOvertimeRankList = clockService.leavePersonalOvertimeRankList(departmentId,
				startTime, endTime);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(leavePersonalOvertimeRankList);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ??????????????????
	@RequestMapping(value = "/police/force/online/statistics", method = RequestMethod.GET)
	public ResponseEntity<?> policeForceOnlineStatistics(
			@RequestParam(value = "policeId", required = false) String policeId) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// ??????????????????
		PoliceForceOnlineStatistics item = clockService.policeForceOnlineStatisticsItem();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(item);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ??????????????????????????????????????????
	@RequestMapping(value = "/process/dep/overtime/duty/police/num/list", method = RequestMethod.GET)
	public ResponseEntity<?> processDepOvertimeDutypoliceNumList(
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "sortType", required = false) Integer sortType) throws ApiException {
		DataListReturn dlr = new DataListReturn();
		String typeStr = null;
		String sortStr = null;
		if (type == 1) {// 1????????????
			typeStr = "overtimeNum";
		} else if (type == 2) {// 2????????????
			typeStr = "dutyNum";
		} else if (type == 3) {// 3?????????
			typeStr = "totalNum";
		}
		if (sortType == null || sortType == 1) {// 1??????2??????
			sortStr = "asc";
		} else if (sortType == 2) {
			sortStr = "desc";
		}
		// ??????????????????????????????????????????
		List<DepOvertimeDutypoliceNum> list = clockService.processDepOvertimeDutypoliceNumList(departmentId, typeStr,
				sortStr);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(list);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	/**
	 * ??????????????????
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/get_position", method = RequestMethod.POST)
	public ResponseEntity<?> getPositon() {

		// ????????????
		List<PolicePosition> policePositionList = userService.policePositionList();

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(policePositionList);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);

	}

	/**
	 * ????????????????????????
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

		// ???????????????
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
	 * ??????id????????????????????????
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
	 * ??????????????????????????????????????????
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
	 * ???????????????????????????
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
	 * ??????id???????????????????????????
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
	 * ??????id???????????????????????????
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
	 * ??????id???????????????????????????
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
	 * ????????????????????????
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
	 * ????????????
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/add_leaveIntegral_manage", method = RequestMethod.POST)
	public ResponseEntity<?> addLeaveIntegralManage(
			@Param("leaveIntegralManage") LeaveIntegralManage leaveIntegralManage) throws ParseException {

		// ????????????????????????????????????
		int num = leaveProcessService.nowMonthHave(leaveIntegralManage.getScoredPoliceId(),
				leaveIntegralManage.getPoliceMonthId());

		if (num != 0) {
			// ????????????
			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getSuccesscode());
			dataListReturn.setMessage("fail");
			dataListReturn.setResult("????????????" + leaveIntegralManage.getPoliceMonthId() + "??????????????????");
			dataListReturn.setStatus(true);

			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
		} else {
			// ????????????????????????
			maintainIntegralDate(leaveIntegralManage, false);

			// ????????????
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
	 * ?????????????????????
	 */
	private void maintainIntegralDate(LeaveIntegralManage leaveIntegralManage, Boolean isMore) {
		// ??????????????????????????????
		Calendar instance = Calendar.getInstance();
		String year = String.valueOf(instance.get(Calendar.YEAR));
		int halfYear = leaveIntegralManage.getPoliceMonthId() <= 6 ? 1 : 2;

		// ????????????????????????
		LeavePoints leavePoints = leaveProcessService.leavePointsItem(null, leaveIntegralManage.getScoredPoliceId(),
				year, halfYear);
		// ??????????????????
		if (null == leavePoints) {
			if (!isMore) {
				// ??????????????????
				leaveProcessService.leavePointsCreat(maintainIntegral(leaveIntegralManage));
			}
		} else {
			// ???????????????????????????????????????????????????
			leavePoints.setTotalPoints(leavePoints.getTotalPoints() + leaveIntegralManage.getIntegralValue());
			leavePoints.setResidualPoints(leavePoints.getResidualPoints() + leaveIntegralManage.getIntegralValue());

			leaveProcessService.leavePointsUpdate(leavePoints);
		}

	}

	/**
	 * ????????????????????????
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

		// ????????????
		LeavePoints leavePoints = new LeavePoints();
		leavePoints.setPoliceId(leaveIntegralManage.getScoredPoliceId());
		leavePoints.setName(leaveIntegralManage.getScoredPoliceName());
		// ????????????????????????
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
	 * ??????id??????????????????
	 *
	 * @return
	 */
	@RequestMapping(value = "/leave/delete_leaveIntegral_manage", method = RequestMethod.POST)
	public ResponseEntity<?> deleteLeaveIntegralManage(@Param("id") Integer id) {

		// ??????id??????????????????
		LeaveIntegralManage integralManage = leaveProcessService.getLeaveIntegralManageOne(id);

		Calendar instance = Calendar.getInstance();
		instance.setTime(integralManage.getCreationDate());
		String year = String.valueOf(instance.get(Calendar.YEAR));
		int halfYear = integralManage.getPoliceMonthId() <= 6 ? 1 : 2;

		// ????????????????????????
		LeavePoints leavePoints = leaveProcessService.leavePointsItem(null, integralManage.getScoredPoliceId(), year,
				halfYear);

		// ???????????????????????????
		if (null != leavePoints) {
			// ???????????????
			Double total = leavePoints.getTotalPoints() - integralManage.getIntegralValue();
			Double residual = leavePoints.getResidualPoints() - integralManage.getIntegralValue();

			leavePoints.setTotalPoints(total < 0 ? 0 : total);
			leavePoints.setResidualPoints(residual < 0 ? 0 : residual);

			// ???????????????
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
	 * ??????id??????????????????
	 * 
	 * @param leaveIntegralManage
	 * @return
	 */
	@RequestMapping(value = "/leave/update_leaveIntegral_manage", method = RequestMethod.POST)
	public ResponseEntity<?> updateLeaveIntegralManage(
			@Param("leaveIntegralManage") LeaveIntegralManage leaveIntegralManage) {

		// ?????????????????????
		LeaveIntegralManage leaveIntegralManageOne = leaveProcessService
				.getLeaveIntegralManageOne(leaveIntegralManage.getId());

		// ????????????????????????
		Calendar instance = Calendar.getInstance();
		instance.setTime(leaveIntegralManageOne.getCreationDate());
		String year = String.valueOf(instance.get(Calendar.YEAR));
		int halfYear = leaveIntegralManageOne.getPoliceMonthId() <= 6 ? 1 : 2;

		LeavePoints leavePoints = leaveProcessService.leavePointsItem(null, leaveIntegralManageOne.getScoredPoliceId(),
				year, halfYear);

		if (null != leavePoints) {
			double change = leaveIntegralManage.getIntegralValue() - leaveIntegralManageOne.getIntegralValue();

			// ??????????????????????????????
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
	 * ??????id??????????????????
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
	 * ????????????????????????
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
			// ?????????????????????????????????????????????????????????
			String userNameByPoliceIds = userService.getUserNameByPoliceIds(leave.getPoliceObjectIds());
			leave.setPoliceObjectNames(userNameByPoliceIds);
			// ??????
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
	 * ??????????????????(?????????????????????)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/add_leave_integrala_udit_power", method = RequestMethod.POST)
	public ResponseEntity<?> addLeaveIntegralAuditPower(
			@Param("leaveIntegralAuditPower") LeavePower leaveIntegralAuditPower,
			@Param("policeObjectIdsArr") String policeObjectIdsArr) {

		// ????????????????????????
		int exist = leaveProcessService.isExist(leaveIntegralAuditPower.getDepartmentId(),
				leaveIntegralAuditPower.getCheckerId(), leaveIntegralAuditPower.getModuleId());

		DataListReturn dataListReturn = new DataListReturn();

		if (exist > 0) {
			dataListReturn.setCode(StatusCode.getSuccesscode());
			dataListReturn.setMessage("fiale");
			dataListReturn.setResult(
					"???????????????" + (leaveIntegralAuditPower.getModuleId() == 1 ? "AI??????" : "AI??????") + "?????????????????????????????????,?????????????????????");
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
	 * ??????????????????(?????????????????????)
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
			// ??????powerId??????leave_power_objec
			leaveProcessService.leavePowerObjectDeleteByPowerId(leaveIntegralAuditPower.getId());
			for (int i = 0; i < departmentObjectIds.length; i++) {
				// ??????leave_power_objec
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
			// ??????powerId??????leave_power_objec
			leaveProcessService.leavePowerObjectDeleteByPowerId(leaveIntegralAuditPower.getId());
			// ??????leave_power_objec
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
	 * ??????id????????????????????????
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/get_leave_integrala_udit_power_by_id", method = RequestMethod.POST)
	public ResponseEntity<?> getLeaveIntegralAuditPowerOne(@Param("id") Integer id) {

		LeavePower leavePower = leaveProcessService.getLeaveIntegralAuditPowerById(id);

		if (leavePower.getDepartmentId() == 1) {
			leavePower.setPoliceObjectIdsArr(leaveProcessService.getPoliceObjectIdsByPowerId(id));

			// ??????department_object_ids?????????????????????
			List<Department> dept = departmentService.getDepartmentByIds(leavePower.getDepartmentObjectIds());
			String deptName = "";
			for (Department d : dept) {
				deptName += d.getName() + ",";
			}
			leavePower.setDepartmentObjectNames(deptName.substring(0, deptName.length() - 1));

			if (leavePower.getPoliceObjectIdsArr() != null) {
				// ??????policeObjectIdsArr???????????????
				String names = "";
				String[] idArr = leavePower.getPoliceObjectIdsArr().split("-");
				for (String ids : idArr) {
					names += userService.getUserNameByPoliceIds(ids) + "-";
				}
				leavePower.setPoliceObjectNamesArr(names.substring(0, names.length() - 1));
			}
		}

		// ?????????????????????????????????????????????????????????
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
	 * ??????id????????????????????????
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leave/delete_leave_integrala_udit_power", method = RequestMethod.POST)
	public ResponseEntity<?> deleteLeaveIntegralAuditPower(@Param("id") Integer id) {

		// ?????????????????????????????????
		// ????????????
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
			dataListReturn.setResult("????????????,???????????????????????????!");
			dataListReturn.setStatus(true);
		} else {
			// ???????????????
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
	 * ????????????????????????
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
	 * ????????????????????????
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
	 * ??????????????????
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
	 * ??????????????????
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
	 * ????????????????????????
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
	 * ????????????????????????
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
	 * ?????????????????????
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

		String fileName = "???????????????" + DateUtil.format(new Date(), "yyyyMMddHHmmss");
		String excelHeader[] = { "??????", "??????", "??????", "????????????", "??????" };

		String sheetName = "???????????????" + DateUtil.format(new Date(), "yyyy-MM-dd");

		// ?????????
		map.put("fileName", fileName);
		// ??????
		map.put("excelHeader", excelHeader);
		// ??????
		map.put("keys", excelHeader);
		// sheet???
		map.put("sheetName", sheetName);

		list.add(map);

		// ???????????????????????????
		List<LeaveTrain> leaveTrainList = leaveProcessService.getLeaveTrainList(type, departmentId, keyWord, null);

		for (int i = 0; i < leaveTrainList.size(); i++) {
			LinkedHashMap<String, Object> mapBody = new LinkedHashMap<String, Object>();
			mapBody.put("??????", leaveTrainList.get(i).getPoliceName());
			mapBody.put("??????", leaveTrainList.get(i).getPoliceId());
			mapBody.put("??????", leaveTrainList.get(i).getPositionName());
			mapBody.put("????????????", leaveTrainList.get(i).getDepartmentName());
			mapBody.put("??????", leaveTrainList.get(i).getType() == 1 ? "?????????????????????" : "	??????????????????????????????");
			list.add(mapBody);
		}

		return list;

	}

	/**
	 * ??????????????????
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

		String fileName = "????????????" + DateUtil.format(new Date(), "yyyyMMddHHmmss");
		String excelHeader[] = { "????????????", "??????", "?????????", "????????????", "?????????", "?????????", "????????????" };

		String sheetName = "????????????" + DateUtil.format(new Date(), "yyyy-MM-dd");

		// ?????????
		map.put("fileName", fileName);
		// ??????
		map.put("excelHeader", excelHeader);
		// ??????
		map.put("keys", excelHeader);
		// sheet???
		map.put("sheetName", sheetName);

		list.add(map);

		// ????????????????????????
		List<LeaveIntegralManage> leaveIntegralManage = leaveProcessService.getLeaveIntegralManageList(policeMonth,
				departmentId, keyword, null);

		for (int i = 0; i < leaveIntegralManage.size(); i++) {
			LinkedHashMap<String, Object> mapBody = new LinkedHashMap<String, Object>();
			mapBody.put("????????????", leaveIntegralManage.get(i).getScoredPoliceName());
			mapBody.put("??????", leaveIntegralManage.get(i).getScoredPoliceId());
			mapBody.put("?????????", leaveIntegralManage.get(i).getPoliceMonth());
			mapBody.put("????????????", leaveIntegralManage.get(i).getDepartmentName());
			mapBody.put("?????????", leaveIntegralManage.get(i).getIntegralValue());
			mapBody.put("?????????", leaveIntegralManage.get(i).getScoringPoliceName());
			mapBody.put("????????????", DateUtil.format(leaveIntegralManage.get(i).getScoringDate(), "yyyy/MM/dd HH:mm"));
			list.add(mapBody);
		}

		return list;

	}

	/**
	 * ???????????????????????????
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
		String fileName = "???????????????????????????";
		String excelHeader[] = { "??????", "??????", "??????" };

		String sheetName = "???????????????" + DateUtil.format(new Date(), "yyyy-MM-dd");
		// ?????????
		map.put("fileName", fileName);
		// ??????
		map.put("excelHeader", excelHeader);
		// ??????
		map.put("keys", excelHeader);
		// sheet???
		map.put("sheetName", sheetName);

		list.add(map);

		return list;

	}

	/**
	 * ?????????????????????
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

		// ??????????????????
		List<LeaveTrain> list = new ArrayList<LeaveTrain>();

		// ???????????????
		int index = 0;

		try {
			for (List<String> excel : readExcel) {
				// ???????????????
				index++;

				LeaveTrain leaveTrain = new LeaveTrain();
				leaveTrain.setPoliceId(excel.get(1).toString().substring(0, 6));
				leaveTrain.setType(excel.get(2).toString().equals("?????????????????????") ? 1 : 2);

				list.add(leaveTrain);

			}
		} catch (Exception e) {

			e.printStackTrace();

			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getFailcode());
			dataListReturn.setMessage("faile");
			dataListReturn.setResult("???" + index + "???????????????,????????????!");
			dataListReturn.setStatus(true);
			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
		}

		// ????????????????????????
		leaveProcessService.addMoreLeaveTrain(list);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(null);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * ????????????????????????
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/alarm/leave_integral_manage_excel", method = RequestMethod.GET)
	public void leaveIntegralManageExcel(HttpServletResponse response) {

		String[] months;
		String[] department;

		try {

			// ???????????????
			List<AlarmPoliceMonth> allMonth = alarmService.getAllMonth();
			months = new String[allMonth.size()];
			for (int i = 0; i < allMonth.size(); i++) {
				months[i] = allMonth.get(i).getId().toString();
			}

			// ????????????
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
		String fileName = "????????????????????????";
		String excelHeader[] = { "????????????", "??????", "????????????", "?????????", "?????????", "?????????", "??????", "????????????" };

		String sheetName = "????????????" + DateUtil.format(new Date(), "yyyy-MM-dd");
		// ?????????
		map.put("fileName", fileName);
		// ??????
		map.put("excelHeader", excelHeader);
		// ??????
		map.put("keys", excelHeader);
		// sheet???
		map.put("sheetName", sheetName);

		list.add(map);

		return list;

	}

	/**
	 * ??????????????????
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

		// ??????????????????
		List<LeaveIntegralManage> list = new ArrayList<LeaveIntegralManage>();

		// ???????????????????????????????????? ????????????
		ArrayList<LeavePoints> leavePointsList = new ArrayList<LeavePoints>();

		// ???????????????
		int index = 0;

		try {
			for (List<String> excel : readExcel) {
				// ???????????????
				index++;

				LeaveIntegralManage leaveIntegralManage = new LeaveIntegralManage();
				leaveIntegralManage.setScoredPoliceName(excel.get(0).toString());
				leaveIntegralManage.setScoredPoliceId(excel.get(1).toString().substring(0, 6));
				// ??????????????????????????????id
				leaveIntegralManage
						.setDepartmentId(departmentService.getDepartmentByName(excel.get(2).toString()).getId());
				leaveIntegralManage.setPoliceMonthId(Double.valueOf(excel.get(3).toString()).intValue());
				leaveIntegralManage.setIntegralValue(Double.valueOf(excel.get(4).toString()));
				leaveIntegralManage.setScoringPoliceId(excel.get(6).toString().substring(0, 6));
				leaveIntegralManage.setScoringDate(DateUtil.parseDate(excel.get(7).toString()));

				// ??????????????????
				// ????????????????????????????????????
				int num = leaveProcessService.nowMonthHave(leaveIntegralManage.getScoredPoliceId(),
						leaveIntegralManage.getPoliceMonthId());

				if (num != 0) {
					DataListReturn dataListReturn = new DataListReturn();
					dataListReturn.setCode(StatusCode.getFailcode());
					dataListReturn.setMessage("faile");
					dataListReturn.setResult("???" + index + "???????????????,????????????!");
					dataListReturn.setStatus(true);
					return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
				}

				// ??????????????????????????????
				maintainIntegralDate(leaveIntegralManage, true);
				// ????????????
				leaveIntegralManage.setScoringDate(fmtDate(leaveIntegralManage.getPoliceMonthId()));

				leavePointsList.add(maintainIntegral(leaveIntegralManage));

				list.add(leaveIntegralManage);

			}
		} catch (Exception e) {

			e.printStackTrace();

			DataListReturn dataListReturn = new DataListReturn();
			dataListReturn.setCode(StatusCode.getFailcode());
			dataListReturn.setMessage("faile");
			dataListReturn.setResult("???" + index + "???????????????,????????????!");
			dataListReturn.setStatus(true);
			return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
		}

		// ????????????????????????
		leaveProcessService.addMoreLeaveIntegralManage(list);

		// ??????????????????
		leaveProcessService.addMoreleavePoints(leavePointsList);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("success");
		dataListReturn.setResult(null);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}


	/**
	 * ????????????????????????
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
	 * ????????????????????????
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

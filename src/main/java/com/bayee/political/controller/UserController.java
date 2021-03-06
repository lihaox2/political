/**
 * 
 */
package com.bayee.political.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.bayee.political.json.UpdatePoliceRiskHealthShowFlagParam;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bayee.political.domain.Department;
import com.bayee.political.domain.GroupManagement;
import com.bayee.political.domain.PolicePosition;
import com.bayee.political.domain.User;
import com.bayee.political.domain.UserEvaluation;
import com.bayee.political.domain.UserReward;
import com.bayee.political.service.AccountService;
import com.bayee.political.service.DepartmentService;
import com.bayee.political.service.EvaluationService;
import com.bayee.political.service.GroupManagementService;
import com.bayee.political.service.UserEvaluationService;
import com.bayee.political.service.UserRewardService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
import com.bayee.political.utils.StatusCode;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.google.gson.Gson;
import com.taobao.api.ApiException;

import cn.hutool.core.date.DateUtil;

/**
 * @author seanguo
 * @param <V>
 *
 */
@Controller
public class UserController<V> extends BaseController {

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserEvaluationService userEvaluationService;

	@Autowired
	private UserRewardService userRewardService;

	@Autowired
	AccountService accountService;

	@Autowired
	private GroupManagementService groupManagementService;

	@Autowired
	EvaluationService evaluationService;

	private static Gson gson;

	static {
		gson = new Gson();
	}

	/**
	 * ???????????????????????????????????????
	 */
	@PostMapping("/user/update/riskHealth/showFlag")
	public ResponseEntity<?> updatePoliceRiskHealthShowFlag(@RequestBody UpdatePoliceRiskHealthShowFlagParam param) {

		userService.updateRiskHealthShowFlagByPoliceId(param.getPoliceId(), param.getShowFlag());
		return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
	}

	// ??????????????????????????????(?????????????????????????????????????????????6???????????????)
//	@Scheduled(cron = "0 0 6 * * ?") // ??????6?????????
//	@Scheduled(cron = "0 */1 * * * ?") // ??????1????????????
	@RequestMapping(value = "/user/work/time/save", method = RequestMethod.GET)
	public void userWorkTimeSave() throws ApiException {
		DataListReturn dlr = new DataListReturn();
		// ????????????????????????
		List<User> userList = userService.userAllList();
		List<String> srtUserList = new ArrayList<String>();
		List<Integer> intUserList = new ArrayList<Integer>();
		for (int i = 0; i < userList.size(); i++) {
			srtUserList.add(userList.get(i).getDdUserId());
			intUserList.add(userList.get(i).getId());
		}
		String accessToken = getAccessToken();
		for (int i = 0; i < srtUserList.size(); i++) {
			DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/get");
			OapiUserGetRequest request = new OapiUserGetRequest();
			request.setUserid(srtUserList.get(i));
			request.setHttpMethod("GET");
			OapiUserGetResponse response = client.execute(request, accessToken);
			System.out.println("????????????");
			if (response.getHiredDate() != null) {
				User item = userService.userItem(intUserList.get(i));
				if (item != null) {
					item.setWorkingStartDate(response.getHiredDate());
					userService.userUpdate(item);
					System.out.println("????????????");
				}
			}
		}
		System.out.println("???????????????");
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(1);
		dlr.setCode(StatusCode.getSuccesscode());
	}

	@RequestMapping(value = { "/user/file/update/phone" })
	public ResponseEntity<String> updatePhone() throws Exception {
		// ???????????????
		FileInputStream fis = new FileInputStream(new File("/Users/seanguo/Downloads/20200106.xls"));
		// ????????????????????????
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		// ???????????????
		HSSFSheet sheet = workbook.getSheetAt(0);
		// ?????????,???????????????????????????getRow??????,????????????0????????????
		int rowCount = sheet.getLastRowNum();
		int count = 0;
		for (int i = 0; i < rowCount; i++) {
			HSSFRow row = sheet.getRow(i);
			// ???????????????,row?????????????????????,???????????????????????????getCell,????????????0????????????
			HSSFCell cell = row.getCell(0);
			// ?????????????????????,???C1??????(?????????,?????????)
			String userId = cell.getStringCellValue();

			cell = row.getCell(8);
			String phone = cell.getStringCellValue();

			User user = userService.findByDDUserId(userId);
			if (user != null) {
				user.setPhone(phone);
				userService.updatePhone(user);
				count++;
			}
		}
		fis.close();

		return new ResponseEntity<String>(String.valueOf("User phone updated: " + count), HttpStatus.OK);
	}

	/**
	 * ??????????????????
	 */
	@RequestMapping(value = { "/user/file/update/evaluation" })
	public ResponseEntity<String> updateEvaluation() throws Exception {
		// ???????????????
		FileInputStream fis = new FileInputStream(
				new File("/Users/seanguo/Documents/Projects/??????????????????/??????/????????????/???????????????/????????????.xls"));
		// ????????????????????????
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		// ???????????????
		HSSFSheet sheet = workbook.getSheetAt(0);
		// ?????????,???????????????????????????getRow??????,????????????0????????????
		int rowCount = sheet.getLastRowNum();
		int count = 0;
		for (int i = 0; i < rowCount; i++) {
			if (i == 0)
				continue;

			HSSFRow row = sheet.getRow(i);

			HSSFCell cell = row.getCell(2);
			// ?????????????????????,???C1??????(?????????,?????????)
			String residentId = cell.getStringCellValue();

			cell = row.getCell(3);
			String year = cell.getStringCellValue();

			cell = row.getCell(4);
			String result = cell.getStringCellValue();

			User user = userService.findByResidentId(residentId);

			if (user != null) {
				UserEvaluation userEval = new UserEvaluation();
				userEval.setUserId(user.getId());
				userEval.setDdUserId(user.getDdUserId());
				userEval.setPoliceId(user.getPoliceId());
				userEval.setResult(result);
				userEval.setEvaluateYear(year);

				userEvaluationService.save(userEval);

				count++;
			} else {
				System.out.println("#### User not found for [" + residentId + "]");
			}
		}
		fis.close();

		return new ResponseEntity<String>(String.valueOf("User evaluation updated: " + count), HttpStatus.OK);
	}

	/**
	 * ??????????????????
	 */
	@RequestMapping(value = { "/user/file/update/reward" })
	public ResponseEntity<String> updateReward() throws Exception {
		// ???????????????
		FileInputStream fis = new FileInputStream(
				new File("/Users/seanguo/Documents/Projects/??????????????????/??????/????????????/???????????????/??????.xls"));
		// ????????????????????????
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		// ???????????????
		HSSFSheet sheet = workbook.getSheetAt(0);

		// ?????????,???????????????????????????getRow??????,????????????0????????????
		int rowCount = sheet.getLastRowNum();
		int count = 0;
		for (int i = 0; i < rowCount; i++) {
			if (i == 0)
				continue;

			HSSFRow row = sheet.getRow(i);

			HSSFCell cell = row.getCell(2);
			// ?????????????????????,???C1??????(?????????,?????????)
			String residentId = cell.getStringCellValue();

			cell = row.getCell(4);
			String rewardType = cell.getStringCellValue();

			cell = row.getCell(5);
			String rewardTitle = cell.getStringCellValue();

			cell = row.getCell(6);
			Date rewardDate = DateUtils.parseDate(cell.getStringCellValue(), "yyyy-MM-dd");

			cell = row.getCell(7);
			String approvalGroup = cell.getStringCellValue();

			cell = row.getCell(8);
			String approvalFileNum = cell.getStringCellValue();

			User user = userService.findByResidentId(residentId);

			if (user != null) {
				String[] rewardTypes = rewardType.split("???");
				if (rewardTypes.length > 1)
					System.out.println("#### multiple reward type found");
				for (int j = 0; j < rewardTypes.length; j++) {
					UserReward userReward = new UserReward();
					userReward.setUserId(user.getId());
					userReward.setDdUserId(user.getDdUserId());
					userReward.setPoliceId(user.getPoliceId());
					userReward.setRewardDate(rewardDate);
					userReward.setApprovalGroup(approvalGroup);
					userReward.setApprovalFileNum(approvalFileNum);
					userReward.setRewardType(rewardType);
					userReward.setRewardTitle(rewardTitle);

					userRewardService.save(userReward);

					count++;
				}
			} else {
				System.out.println("#### User not found for [" + residentId + "]");
			}
		}
		fis.close();

		return new ResponseEntity<String>(String.valueOf("User reward updated: " + count), HttpStatus.OK);
	}

	// ??????????????????
	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public ResponseEntity<?> userList(@RequestParam(value = "id", required = false) Integer id) {
		DataListReturn dlr = new DataListReturn();
		List<User> userList = userService.userList(null, null, null, null, null, null);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(userList);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// ??????????????????
	@RequestMapping(value = "/user/item", method = RequestMethod.GET)
	public ResponseEntity<?> userItem(@RequestParam(value = "policeId", required = false) String policeId) {
		DataListReturn dlr = new DataListReturn();
		User userItem = userService.policeItem(policeId, null, null);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(userItem);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// **************************** ??????????????????***********************????????????

	// ????????????????????????
	@RequestMapping(value = { "/account/list" })
	public @ResponseBody byte[] accountsList(Model model, Integer departmentId, Integer positionId,
			Integer isUnitLeader, Integer isCadre, String keywords, Integer currentPage, HttpServletRequest request)
			throws ParseException, UnsupportedEncodingException {
		if (currentPage == null || currentPage <= 0) {
			currentPage = 1;
		}
		// ????????????
		List<Department> departmentList = departmentService.findAll();
		// ????????????
		List<PolicePosition> policePositionList = userService.policePositionList();
		// ??????????????????
		List<User> userList = userService.userList(departmentId, positionId, isUnitLeader, isCadre, keywords,
				(currentPage - 1) * 10);
		// ????????????????????????
		int count = userService.userListCount(departmentId, positionId, isUnitLeader, isCadre, keywords);

		// ?????????Object????????????
		ArrayList<Object> list = new ArrayList<Object>();

		list.add(departmentList);
		list.add(policePositionList);
		list.add(userList);
		list.add(count);
		list.add(currentPage);
		list.add(departmentId);
		list.add(positionId);
		list.add(keywords);
		list.add(isUnitLeader);
		list.add(isCadre);
		return gson.toJson(list).getBytes("utf-8");
	}

	/**
	 * ??????id????????????
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/account/only")
	public @ResponseBody byte[] queryAccountById(Integer id) throws UnsupportedEncodingException, ParseException {

		User userItem = userService.userItem(id);

		// ??????id???????????????????????????
		if (null != userItem.getAlarmDepartmentIds() && !"".equals(userItem.getAlarmDepartmentIds().trim())) {

			String[] alarmDepartmentIds = userItem.getAlarmDepartmentIds().split(",");

			String names = "";

			for (String departmentId : alarmDepartmentIds) {
				Department department = departmentService.findById(Integer.parseInt(departmentId));
				names += department.getName() + ",";
			}

			names = names.substring(0, names.length() - 1);
			userItem.setAlarmDepartmentNames(names);

		}
		// ???????????????
		userItem.setWorkingStartDateStr(DateUtils.formatDate(userItem.getWorkingStartDate(), "yyyy-MM-dd"));

		Map map = JSON.parseObject(JSON.toJSONString(userItem), Map.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy???MM???dd???");
		if(userItem.getBirthday()!=null || userItem.getBirthday()!=""){
			Date parse = sdf1.parse(userItem.getBirthday());
			String format = sdf2.format(parse);
			map.put("birthday",format);
		}
		if(userItem.getEmploymentDate()!=null){
			String format1 = sdf.format(userItem.getEmploymentDate());
			map.put("employmentDate",format1);
		}
		return gson.toJson(map).getBytes("utf-8");

	}

	// ????????????????????????
	@RequestMapping(value = { "/account/edit" })
	public @ResponseBody byte[] accountEdit(Model model, Integer id, HttpServletRequest request)
			throws UnsupportedEncodingException {
		User account = getSessionAccount(request);
		// ????????????
		List<Department> departmentList = departmentService.findAll();
		// ????????????
		List<PolicePosition> policePositionList = userService.policePositionList();
		// ????????????
		List<GroupManagement> groupManagementList = groupManagementService.groupManagementList(1, null);

		// ?????????Object????????????
		ArrayList<Object> list = new ArrayList<Object>();

		if (id != null) {
			User userItem = userService.userItem(id);
			list.add(userItem);
		}
		list.add(departmentList);
		list.add(policePositionList);
		list.add(groupManagementList);
		list.add(account);
		return gson.toJson(list).getBytes("utf-8");
	}

	// ????????????or??????
	@RequestMapping(value = { "/account/submit" }, method = RequestMethod.POST)
	public @ResponseBody byte[] saveAccountSubmits(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "headImage", required = false) String headImage,
			@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "gender", required = false) Integer gender,
			@RequestParam(value = "idCard", required = false) String idCard,
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "positionId", required = false) Integer positionId,
			@RequestParam(value = "isUnitLeader", required = false) Integer isUnitLeader,
			@RequestParam(value = "isCadre", required = false) Integer isCadre,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "policeType", required = false) Integer policeType,
			@RequestParam(value = "stationPostId", required = false) Integer stationPostId,
			@RequestParam(value = "roleId", required = false) Integer roleId,
			@RequestParam(value = "groupId", required = false) Integer groupId,
			@RequestParam(value = "lastLoginTime", required = false) String lastLoginTime,
			@RequestParam(value = "isDisable", required = false) Integer isDisable,
			@RequestParam(value = "scorer", required = false) Integer scorer,
			@RequestParam(value = "isBlock", required = false) Integer isBlock,
			@RequestParam(value = "trainScorer", required = false) Integer trainScorer,
			@RequestParam(value = "alarmDepartmentIds", required = false) String alarmDepartmentIds,
			@RequestParam(value = "workingStartDate", required = false) String workingStartDate,
			HttpServletRequest request) throws IOException, ParseException {
		// ????????????
		MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
		MultipartFile file = multipart.getFile("file");
		// ????????????
		String nameBelong = null;
		try {
			if (null != file.getOriginalFilename() && !"".equals(file.getOriginalFilename())) {
				// ????????????
				byte[] bytes = file.getBytes();
				// ?????????????????? UUID
				String uuid = UUID.randomUUID().toString();
				// ?????????
				String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."))
						+ uuid;
				// ??????????????????
				String url = ("/mnt/qiantang/police/");
				// ???????????????
				String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				// ????????????????????????
				nameBelong = fileName + suffix;
				Path path = Paths.get(url, nameBelong);
				Files.write(path, bytes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean isUpdate = false;
		User item = userService.userItem(id);
		if (item == null) {
			item = new User();
			item.setIsActive(0);
			item.setLoginTimes(0);
			item.setCreationDate(new Date());
		} else {
			isUpdate = true;
			item.setUpdateDate(new Date());
		}
		item.setPoliceId(policeId);
		item.setName(name);
		item.setPhone(phone);
		item.setGender(gender);
		item.setIdCard(idCard);
		item.setIsBlock(isBlock);
//			if (idCard != null && idCard != "") {
//				item.setBirthday(idCard.substring(6, 14));
//			}
		item.setDepartmentId(departmentId);
		Department depItem = departmentService.findById(departmentId);
		item.setDepartmentType(depItem.getParentId());
		if (departmentId != null && departmentId == 1) {
			item.setPositionId(1);
			item.setIsUnitLeader(1);
			item.setIsCadre(1);
		} else {
			item.setPositionId(positionId);
			item.setIsUnitLeader(isUnitLeader);
			item.setIsCadre(isCadre);
		}
		item.setPoliceType(1);
		item.setStationPostId(stationPostId);
		item.setRoleId(roleId);
//			item.setLastLoginTime(lastLoginTime);
		// ???????????????
		item.setAlarmDepartmentIds(alarmDepartmentIds);
		// ?????????
		item.setScorer(scorer);
		item.setIsDisable(isDisable);
		//??????????????????
		item.setTrainScorer(trainScorer);
		// ??????????????????
//		item.setWorkingStartDate(DateUtil.parseDate(workingStartDate));
		if (null != file.getOriginalFilename() && !"".equals(file.getOriginalFilename())) {
			item.setHeadImage("/police/" + nameBelong);
		}
		if (item.getDepartmentType() == 1) {// ?????????
			item.setGroupId(1);
			item.setParticipantId(1);
		} else if (item.getDepartmentType() == 2) {// ????????????
			if (item.getIsUnitLeader() == 1 && item.getIsCadre() == 1) {
				item.setGroupId(2);
				item.setParticipantId(2);
			} else if (item.getIsUnitLeader() == 0 && item.getIsCadre() == 1) {
				item.setGroupId(3);
				item.setParticipantId(3);
			} else if (item.getIsUnitLeader() == 0 && item.getIsCadre() == 0) {
				item.setGroupId(4);
				item.setParticipantId(4);
			}
		} else if (item.getDepartmentType() == 3) {// ?????????
			if (item.getIsUnitLeader() == 1 && item.getIsCadre() == 1) {
				item.setGroupId(5);
				item.setParticipantId(5);
			} else if (item.getIsUnitLeader() == 0 && item.getIsCadre() == 1) {
				item.setGroupId(6);
				item.setParticipantId(6);
			} else if (item.getIsUnitLeader() == 0 && item.getIsCadre() == 0) {
				item.setGroupId(7);
				item.setParticipantId(7);
			}
		}
		if (!isUpdate) { // ??????
			item.setPassword("123456");
			userService.userCreat(item);
		} else {
			userService.userUpdate(item);
		}
		return gson.toJson("ok").getBytes("utf-8");
	}

	/**
	 * ?????????????????????
	 * 
	 * @param
	 * @param id
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = { "/account/disable" })
	public @ResponseBody byte[] disableCategoriesCount(@Param("id") Integer id, @Param("isDisable") Integer isDisable)
			throws UnsupportedEncodingException {

		User user = new User();
		user.setId(id);
		user.setIsDisable(isDisable);
		userService.userUpdate(user);

		return gson.toJson("ok").getBytes("utf-8");
	}

	// ????????????
	@RequestMapping(value = { "/account/delete" })
	public @ResponseBody String selectCategoriesCount(Model model, Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		userService.userDelete(id);
		map.put("codes", 200);
		Gson gson = new Gson();
		String json = gson.toJson(map);
		return json;
	}

	// ???????????????????????????????????????????????????
	@RequestMapping("/account/identity")
	public @ResponseBody byte[] accountIdentity(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "idCard", required = false) String idCard) throws UnsupportedEncodingException {
		User userItem = userService.accountOnly(policeId, phone, idCard);
		if (userItem == null) {
			return gson.toJson("true").getBytes("utf-8");
		} else {
			return gson.toJson("false").getBytes("utf-8");
		}
	}

	/**
	 * ??????????????????????????????
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/account/get_target_department", method = RequestMethod.POST)
	public ResponseEntity<?> getTargetDepartment() {
		// ?????????
		List<Department> leaders = departmentService.getDepartmentByType(1);
		// ????????????
		List<Department> unit = departmentService.getDepartmentByType(2);
		// ?????????
		List<Department> policeStaion = departmentService.getDepartmentByType(3);

		LinkedHashMap<String, List<Department>> map = new LinkedHashMap<String, List<Department>>();
		map.put("leaders", leaders);
		map.put("unit", unit);
		map.put("policeStaion", policeStaion);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(map);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * ????????????id???????????????????????????
	 * 
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value = "/account/get_user_by_department_id", method = RequestMethod.POST)
	public ResponseEntity<?> getPoliceByDepartmentId(@Param("departmentId") Integer departmentId) {

		List<User> users = userService.getPoliceByDepartmentId(departmentId);

		DataListReturn dataListReturn = new DataListReturn();
		dataListReturn.setCode(StatusCode.getSuccesscode());
		dataListReturn.setMessage("message");
		dataListReturn.setResult(users);
		dataListReturn.setStatus(true);

		return new ResponseEntity<DataListReturn>(dataListReturn, HttpStatus.OK);
	}

	/**
	 * ?????????????????????
	 * @return
	 */
	@GetMapping("user/export")
	public ResponseEntity<?> export(){
		return userService.export();
	}

}

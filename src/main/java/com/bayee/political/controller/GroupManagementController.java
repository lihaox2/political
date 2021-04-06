package com.bayee.political.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bayee.political.domain.Department;
import com.bayee.political.domain.GroupManagement;
import com.bayee.political.domain.PolicePosition;
import com.bayee.political.domain.User;
import com.bayee.political.service.DepartmentService;
import com.bayee.political.service.GroupManagementService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.StatusCode;
import com.google.gson.Gson;

import cn.hutool.core.date.DateUtil;

/**
 * @author shentuqiwei
 * @version 2020年5月27日 下午4:53:00
 */
@Controller
public class GroupManagementController extends BaseController {

	@Autowired
	private GroupManagementService groupManagementService;

	@Autowired
	private UserService userService;

	@Autowired
	private DepartmentService departmentService;

	private static Gson gson;

	static {
		gson = new Gson();
	}

	/**
	 * 获得所有群组
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/group-management/all-group-management")
	public @ResponseBody byte[] allGroupManagement(@Param("pageSize") Integer pageSize,
			@Param("searchValue") String searchValue) throws UnsupportedEncodingException {

		ArrayList<Object> list = new ArrayList<Object>();

		List<GroupManagement> groupManagementList = groupManagementService.groupManagementList((pageSize - 1) * 10,
				searchValue);

		for (GroupManagement g : groupManagementList) {
			g.setCreationDateStr(DateUtil.format(g.getCreationDate(), "yyyy-MM-dd HH:mm"));
		}

		Integer managementSum = groupManagementService.groupManagementSum();

		list.add(groupManagementList);
		list.add(managementSum);

		return gson.toJson(list).getBytes("utf-8");

	}

	/**
	 * 添加群组
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/group-management/add-group-management")
	public @ResponseBody byte[] addGroup(@Param("groupName") String groupName, @Param("description") String description,
			@Param("policeId") Integer policeId[]) throws UnsupportedEncodingException {

		GroupManagement groupItem = new GroupManagement();
		groupItem.setGroupName(groupName);
		groupItem.setDescription(description);
		groupManagementService.groupCreat(groupItem);
		// 获得新添加的数据的id
		int groupId = groupItem.getId();

		// 修改用户的群组id
		if (null != policeId && policeId.length > 0) {

			for (Integer p : policeId) {

				groupManagementService.updateUserGroup(p, groupId);

			}

		}

		// 添加第二张群组表
		groupManagementService.addEvaluateParticipant(groupId, groupName);

		return gson.toJson("ok").getBytes("utf-8");

	}

	/**
	 * 删除群组
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/group-management/delete-group-management")
	public @ResponseBody byte[] deleteGroup(Integer groupId) throws UnsupportedEncodingException {
		// 查询当前群组是否被引用
		DataListReturn dlr = new DataListReturn();
		List<User> groupPolicesList = userService.groupPolicesList(groupId);
		if (groupPolicesList.size()==0) {
			int count = groupManagementService.deleteGroup(groupId);
			dlr.setStatus(true);
			dlr.setMessage("success");
			dlr.setResult(count);
			dlr.setCode(StatusCode.getSuccesscode());
		}else {
			dlr.setStatus(false);
			dlr.setMessage("fail");
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
		}
		return gson.toJson(dlr).getBytes("utf-8");
	}

	/**
	 * 根据goupId查询群组
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/group-management/get-group-management-id")
	public @ResponseBody byte[] getGroupById(Integer groupId) throws UnsupportedEncodingException {

		ArrayList<Object> list = new ArrayList<Object>();

		// 根据groupId查询群组
		GroupManagement groupManagement = groupManagementService.getGroupById(groupId);

		// 根据groupId查询用户
		// List<User> userList = userService.getUserByGroupId(groupId);

		list.add(groupManagement);
		// list.add(userList);

		return gson.toJson(list).getBytes("utf-8");

	}

	/**
	 * 根据groupId查询用户
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/group-management/get-user-management-id")
	public @ResponseBody byte[] userByGroupId(@Param("groupId") Integer groupId,
			@Param("departmentId") Integer departmentId, @Param("positionId") Integer positionId,
			@Param("isUnitLeader") Integer isUnitLeader, @Param("isCadre") Integer isCadre,
			@Param("keywords") String keywords, @Param("currentPage") Integer currentPage)
			throws UnsupportedEncodingException {

		if (currentPage == null || currentPage <= 0) {
			currentPage = 1;
		}

		// 部门列表
		List<Department> departmentList = departmentService.findAll();
		// 职位列表
		List<PolicePosition> policePositionList = userService.policePositionList();
		// 用户列表查询
		// List<User> userList = userService.userList(departmentId, positionId,
		// isUnitLeader, isCadre, keywords,
		// (currentPage - 1) * 10);

		// 根据groupId查询用户
		List<User> userList = userService.getUserByGroupId(groupId, departmentId, positionId, isUnitLeader, isCadre,
				keywords, (currentPage - 1) * 10);

		// 用户列表数量统计
		int count = userService.userListCountSum(groupId, departmentId, positionId, isUnitLeader, isCadre, keywords);

		// 查询群组
		List<GroupManagement> allGroupManagement = groupManagementService.getAllGroupManagement();

		// 创建个Object类型集合
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
		list.add(allGroupManagement);

		return gson.toJson(list).getBytes("utf-8");

	}

	/**
	 * 修改用户groupId
	 */
	@RequestMapping("/group-management/change-user-management-id")
	public @ResponseBody byte[] changeUserByGroupId(@Param("groupName") String groupName,
			@Param("description") String description, @Param("participantId") Integer participantId,
			@Param("id") Integer id[], @Param("groupId") Integer groupId[]) throws UnsupportedEncodingException {

		if (null != groupId && groupId.length > 0) {
			// 修改用户groupId
			for (int i = 0; i < groupId.length; i++) {
				groupManagementService.changeGroupId(id[i], groupId[i]);
			}
			// 修改群组
			groupManagementService.changeParticipantByGroupId(groupName, description, participantId);

		}
		return gson.toJson("ok").getBytes("utf-8");

	}

}

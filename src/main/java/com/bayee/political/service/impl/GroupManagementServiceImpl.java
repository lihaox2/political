package com.bayee.political.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.GroupManagement;
import com.bayee.political.domain.User;
import com.bayee.political.mapper.GroupManagementMapper;
import com.bayee.political.service.GroupManagementService;

/**
 * @author shentuqiwei
 * @version 2020年5月27日 下午4:56:11
 */
@Service
public class GroupManagementServiceImpl implements GroupManagementService {

	@Autowired
	GroupManagementMapper groupManagementMapper;

	// 群主列表
	@Override
	public List<GroupManagement> groupManagementList(@Param("pageSize") Integer pageSize,
			@Param("searchValue") String searchValue) {
		return groupManagementMapper.groupManagementList(pageSize, searchValue);
	}

	@Override
	public Integer groupManagementSum() {
		// TODO Auto-generated method stub
		return groupManagementMapper.groupManagementSum();
	}

	@Override
	public Integer updateUserGroup(Integer policeId, Integer groupId) {
		// TODO Auto-generated method stub
		return groupManagementMapper.updateUserGroup(policeId, groupId);
	}

	@Override
	public Integer addEvaluateParticipant(Integer groupId, String groupName) {
		// TODO Auto-generated method stub
		return groupManagementMapper.addEvaluateParticipant(groupId, groupName);
	}

	@Override
	public int groupCreat(GroupManagement groupItem) {
		return groupManagementMapper.groupCreat(groupItem);
	}

	@Override
	public Integer deleteGroup(Integer groupId) {
		// TODO Auto-generated method stub
		return groupManagementMapper.deleteGroup(groupId);
	}

	@Override
	public GroupManagement getGroupById(Integer groupId) {
		// TODO Auto-generated method stub
		return groupManagementMapper.getGroupById(groupId);
	}

	@Override
	public Integer changeGroupId(@Param("id") Integer id,
			@Param("groupId") Integer groupId) {
		// TODO Auto-generated method stub
		return groupManagementMapper.changeGroupId(id, groupId);
	}

	@Override
	public Integer changeParticipantByGroupId(String groupName, String description, Integer participantId) {
		// TODO Auto-generated method stub
		return groupManagementMapper.changeParticipantByGroupId(groupName, description, participantId);
	}

	@Override
	public List<GroupManagement> getAllGroupManagement() {
		// TODO Auto-generated method stub
		return groupManagementMapper.getAllGroupManagement();
	}



	
	
}

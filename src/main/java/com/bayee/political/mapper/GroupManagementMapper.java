package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.GroupManagement;
import com.bayee.political.domain.User;

public interface GroupManagementMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(GroupManagement record);

	int insertSelective(GroupManagement record);

	GroupManagement selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(GroupManagement record);

	int updateByPrimaryKey(GroupManagement record);

	/**
	 * 群组列表
	 * @return
	 */
	List<GroupManagement> groupManagementList(@Param("pageSize") Integer pageSize,
			@Param("searchValue") String searchValue);
	
	/**
	 * 查询群组数量
	 */
	Integer groupManagementSum();
	
	/**
	 * 根据警号修改用户群组
	 */
	Integer updateUserGroup(@Param("policeId") Integer policeId, @Param("groupId") Integer groupId);
	
	/**
	 * 添加评价参与人表
	 */
	Integer addEvaluateParticipant(@Param("groupId") Integer groupId, @Param("groupName") String groupName);
	
	/**
	 * 添加群组
	 * @param groupItem
	 * @return
	 */
	int groupCreat(GroupManagement groupItem);
	
	/**
	 * 根据id删除群组
	 */
	Integer deleteGroup(Integer groupId);
	
	/**
	 * 根据群组id查询群组
	 */
	GroupManagement getGroupById(Integer groupId);
	
	/**
	 * 根据用户id修改groupId
	 */
	Integer changeGroupId(@Param("id") Integer id,
			@Param("groupId") Integer groupId);
	
	/**
	 * 根据groupId修改群组
	 */
	Integer changeParticipantByGroupId(@Param("groupName") String groupName,
			@Param("description") String description,
			@Param("participantId") Integer participantId);
	
	/**
	 * 查询全部群组
	 */
	List<GroupManagement> getAllGroupManagement();
	
}
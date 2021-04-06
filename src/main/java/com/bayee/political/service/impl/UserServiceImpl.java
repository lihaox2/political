/**
 * 
 */
package com.bayee.political.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.PolicePosition;
import com.bayee.political.domain.User;
import com.bayee.political.mapper.PolicePositionMapper;
import com.bayee.political.mapper.UserMapper;
import com.bayee.political.service.UserService;

/**
 * @author seanguo
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Autowired
	PolicePositionMapper policePositionMapper;

	// 查询全部警员数据
	@Override
	public List<User> userAllList() {
		return userMapper.userAllList();
	}

	// 人员新增
	@Override
	public int userCreat(User user) {
		return userMapper.userCreat(user);
	}

	// 人员修改
	@Override
	public int userUpdate(User user) {
		return userMapper.userUpdate(user);
	}

	@Override
	public void save(User user) {
		if (findByDDUserId(user.getDdUserId()) == null)
			userMapper.save(user);
	}

	@Override
	public List<User> findAll() {
		return userMapper.findAll();
	}

	@Override
	public User findByDDUserId(String ddUserId) {
		return userMapper.findByDDUserId(ddUserId);
	}

	@Override
	public int countTotal() {
		return userMapper.countTotal();
	}

	@Override
	public void updatePhone(User user) {
		userMapper.updatePhone(user);
	}

	@Override
	public User authenticate(String phone, String password) {
		return userMapper.authenticate(phone, password);
	}

	@Override
	public List<User> findUserByName(String name) {
		return userMapper.findUserByName(name);
	}

	@Override
	public void update(User user) {
		userMapper.update(user);
	}

	@Override
	public User findByResidentId(String residentId) {
		return userMapper.findByResidentId(residentId);
	}

	// 人员列表查询
	@Override
	public List<User> userList(Integer departmentId, Integer positionId, Integer isUnitLeader, Integer isCadre,
			String keywords, Integer pageSize) {
		return userMapper.userList(departmentId, positionId, isUnitLeader, isCadre, keywords, pageSize);
	}

	// 人员列表数量统计
	@Override
	public int userListCount(Integer departmentId, Integer positionId, Integer isUnitLeader, Integer isCadre,
			String keywords) {
		return userMapper.userListCount(departmentId, positionId, isUnitLeader, isCadre, keywords);
	}

	// 人员详情查询
	@Override
	public User userItem(Integer id) {
		return userMapper.userItem(id);
	}

	// 职位列表
	@Override
	public List<PolicePosition> policePositionList() {
		return policePositionMapper.policePositionList();
	}

	// 人员删除
	@Override
	public int userDelete(Integer id) {
		return userMapper.userDelete(id);
	}

	// 查询参与人相关评价任务(api)
	@Override
	public List<User> userTaskList(Integer departmentId, Integer parentId, Integer isUnitLeader, Integer templateId) {
		return userMapper.userTaskList(departmentId, parentId, isUnitLeader, templateId);
	}

	// 人员详情查询根据policeId
	@Override
	public User policeItem(String policeId, String password, String ddUserId) {
		return userMapper.policeItem(policeId, password, ddUserId);
	}

	@Override
	public List<User> getUserByGroupId(Integer groupId, Integer departmentId, Integer positionId, Integer isUnitLeader,
			Integer isCadre, String keywords, Integer currentPage) {
		return userMapper.getUserByGroupId(groupId, departmentId, positionId, isUnitLeader, isCadre, keywords,
				currentPage);
	}

	@Override
	public Integer userListCountSum(Integer groupId, Integer departmentId, Integer positionId, Integer isUnitLeader,
			Integer isCadre, String keywords) {
		return userMapper.userListCountSum(groupId, departmentId, positionId, isUnitLeader, isCadre, keywords);
	}

	// 警号，身份证号码，手机号唯一性验证
	@Override
	public User accountOnly(String policeId, String phone, String idCard) {
		return userMapper.accountOnly(policeId, phone, idCard);
	}

	// 查询当前群组是否被引用
	@Override
	public List<User> groupPolicesList(Integer groupId) {
		return userMapper.groupPolicesList(groupId);
	}

	@Override
	public List<User> getUsersByPoliceId(String[] policeIds) {
		return userMapper.getUsersByPoliceId(policeIds);
	}

	@Override
	public List<User> getUsersByDepUnitCard(Integer departmentId) {
		return userMapper.getUsersByDepUnitCard(departmentId);
	}

	@Override
	public List<User> getUsersByNotDepUnitCard(Integer departmentId, String policeId) {
		return userMapper.getUsersByNotDepUnitCard(departmentId, policeId);
	}

	@Override
	public List<User> getUsersByDepartmentId(Integer departmentId) {
		return userMapper.getUsersByDepartmentId(departmentId);
	}

	@Override
	public List<User> allUser(Integer departmentId) {
		return userMapper.allUser(departmentId);
	}

	@Override
	public String getUserNameByPoliceIds(String PoliceIds) {
		return userMapper.getUserNameByPoliceIds(PoliceIds);
	}

	@Override
	public PolicePosition getPolicePositionByPoliceId(String policeId) {
		return policePositionMapper.getPolicePositionByPoliceId(policeId);
	}

	// 录分训练计划中综合体能项目查询
	@Override
	public List<User> trainRecordPoliceHeightList(Integer projectId, Integer trainPhysicalId) {
		return userMapper.trainRecordPoliceHeightList(projectId, trainPhysicalId);
	}

	// 根据警员人员数据修改
	@Override
	public int userPoliceUpdate(User user) {
		return userMapper.userPoliceUpdate(user);
	}

	@Override
	public int addHeightWeight(String policeId, Double height, Double weight) {
		return userMapper.addHeightWeight(policeId, height, weight);
	}

	@Override
	public String getPoliceIdByDepartmentId(Integer departmentId) {
		return userMapper.getPoliceIdByDepartmentId(departmentId);
	}

	@Override
	public List<User> getPoliceByDepartmentId(Integer departmentId) {
		return userMapper.getPoliceByDepartmentId(departmentId);
	}

	@Override
	public List<User> getAllLeaders() {
		return userMapper.getAllLeaders();
	}

	@Override
	public String getResponsibleDepartment(String policeId) {
		return userMapper.getResponsibleDepartment(policeId);
	}

	// 警力在线民警总数
	@Override
	public int policeForceOnlineCount() {
		return userMapper.policeForceOnlineCount();
	}

	// 用户数量统计
	@Override
	public int userProcessCount(Integer departmentId, Integer positionId, Integer isUnitLeader, Integer isCadre) {
		return userMapper.userProcessCount(departmentId, positionId, isUnitLeader, isCadre);
	}

	// 训练人员列表查询
	@Override
	public List<User> userTrainList() {
		return userMapper.userTrainList();
	}

	// 查询全部警员详细信息数据
	@Override
	public List<User> userInfoAllList() {
		return userMapper.userInfoAllList();
	}

}

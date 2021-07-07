/**
 * 
 */
package com.bayee.political.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.PolicePosition;
import com.bayee.political.domain.User;

/**
 * @author seanguo
 *
 */
@Service
public interface UserService {

	// 查询全部警员数据
	List<User> userAllList();
	
	// 训练人员列表查询
	List<User> userTrainList();

	/**
	 * 查询所有警员/仅名字和警号
	 * 
	 * @return
	 */
	List<User> allUser(@Param("departmentId") Integer departmentId);

	// 人员新增
	int userCreat(User user);

	// 人员修改
	int userUpdate(User user);

	// 人员删除
	int userDelete(Integer id);

	void save(User user);

	List<User> findAll();

	User findByDDUserId(String ddUserId);

	int countTotal();

	void updatePhone(User user);

	void update(User user);

	User authenticate(String phone, String password);

	List<User> findUserByName(String name);

	User findByResidentId(String residentId);

	// 人员列表查询
	List<User> userList(Integer departmentId, Integer positionId, Integer isUnitLeader, Integer isCadre,
			String keywords, Integer pageSize);

	// 用户列表数量统计
	int userListCount(Integer departmentId, Integer positionId, Integer isUnitLeader, Integer isCadre, String keywords);

	// 用户数量统计
	int userProcessCount(Integer departmentId, Integer positionId, Integer isUnitLeader, Integer isCadre);

	// 人员详情查询
	User userItem(Integer id);

	// 职位列表
	List<PolicePosition> policePositionList();

	// 查询参与人相关评价任务(api)
	List<User> userTaskList(Integer departmentId, Integer parentId, Integer isUnitLeader, Integer templateId);

	// 人员详情查询根据policeId
	User policeItem(String policeId, String password, String ddUserId);

	/**
	 * 根据群组id查询用户
	 */
	List<User> getUserByGroupId(@Param("groupId") Integer groupId, @Param("departmentId") Integer departmentId,
			@Param("positionId") Integer positionId, @Param("isUnitLeader") Integer isUnitLeader,
			@Param("isCadre") Integer isCadre, @Param("keywords") String keywords,
			@Param("currentPage") Integer currentPage);

	/**
	 * 根据groupId查询用户数量
	 */
	Integer userListCountSum(@Param("groupId") Integer groupId, @Param("departmentId") Integer departmentId,
			@Param("positionId") Integer positionId, @Param("isUnitLeader") Integer isUnitLeader,
			@Param("isCadre") Integer isCadre, @Param("keywords") String keywords);

	// 警号，身份证号码，手机号唯一性验证
	User accountOnly(String policeId, String phone, String idCard);

	// 查询当前群组是否被引用
	List<User> groupPolicesList(Integer groupId);

	/**
	 * 根据policeId集合查询警员数据
	 * 
	 * @return
	 */
	List<User> getUsersByPoliceId(@Param("policeIds") String[] policeIds);

	/**
	 * 根据多个policeId字符串查询对应的警员姓名字符串
	 */
	String getUserNameByPoliceIds(@Param("PoliceIds") String PoliceIds);

	/**
	 * 根据单位查询该单位中层干部及局领导
	 * 
	 * @return
	 */
	List<User> getUsersByDepUnitCard(@Param("departmentId") Integer departmentId);

	/**
	 * 根据单位查询该单不是中层干部不是局领导的警员
	 * 
	 * @return
	 */
	List<User> getUsersByNotDepUnitCard(@Param("departmentId") Integer departmentId,
			@Param("policeId") String policeId);

	/**
	 * 根据部门id查询用户表管理该部门的人员
	 * 
	 * @param departmentId
	 * @return
	 */
	List<User> getUsersByDepartmentId(@Param("departmentId") Integer departmentId);

	/**
	 * 根据policeId查询职位
	 * 
	 * @return
	 */
	PolicePosition getPolicePositionByPoliceId(@Param("policeId") String policeId);

	// 录分训练计划中综合体能项目查询
	List<User> trainRecordPoliceHeightList(Integer projectId, Integer trainPhysicalId);

	// 根据警员人员数据修改
	int userPoliceUpdate(User user);

	/**
	 * 录入身高体重根据警号
	 * 
	 * @param policeId
	 * @param height
	 * @param weight
	 * @return
	 */
	int addHeightWeight(@Param("policeId") String policeId, @Param("height") Double height,
			@Param("weight") Double weight);

	/**
	 * 根据部门id获得该部门下所有警员的警号
	 * 
	 * @param departmentId
	 * @return
	 */
	String getPoliceIdByDepartmentId(@Param("departmentId") Integer departmentId);

	/**
	 * 根据部门id查询该部门下的警员
	 * 
	 * @param departmentId
	 * @return
	 */
	List<User> getPoliceByDepartmentId(@Param("departmentId") Integer departmentId);

	/**
	 * 获得所有局领导
	 * 
	 * @return
	 */
	List<User> getAllLeaders();

	/**
	 * 获得分管部门（一般用于局领导）
	 * 
	 * @param policeId
	 * @return
	 */
	String getResponsibleDepartment(@Param("policeId") String policeId);

	// 警力在线民警总数
	int policeForceOnlineCount();

	// 查询全部警员详细信息数据
	List<User> userInfoAllList();

	/**
	 * 根据警员编号查询警员
	 * @param policeId
	 * @return
	 */
	User findByPoliceId(String policeId);

	/**
	 * 统计所有警员
	 * @return
	 */
	Integer countAllPolice();

	/**
	 * 根据部门查询警员
	 * @param deptId
	 * @return
	 */
	List<User> findUserByDeptId(Integer deptId);

}

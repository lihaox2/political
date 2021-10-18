/**
 * 
 */
package com.bayee.political.mapper;

import java.util.List;

import com.bayee.political.json.TalentsParticularsResult;
import com.bayee.political.json.TalentsUserParam;
import com.bayee.political.json.TalentsUserResult;
import com.bayee.political.pojo.dto.HolographicPoliceListDO;
import com.bayee.political.pojo.dto.UserDO;
import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.User;

/**
 * @author seanguo
 *
 */
public interface UserMapper {

	// 查询全部警员数据
	List<User> userAllList();

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

	void save(User user);

	List<User> findAll();

	User findByDDUserId(@Param("ddUserId") String ddUserId);

	int countTotal();

	void updatePhone(User user);

	User authenticate(@Param("phone") String phone, @Param("password") String password);

	List<User> findUserByName(@Param("name") String name);

	void update(User user);

	User findByResidentId(@Param("residentId") String residentId);

	/**
	 * 根据警员编号查询警员
	 * @param policeId
	 * @return
	 */
	User findByPoliceId(@Param("policeId") String policeId);

	// 人员列表查询
	List<User> userList(@Param("departmentId") Integer departmentId, @Param("positionId") Integer positionId,
			@Param("isUnitLeader") Integer isUnitLeader, @Param("isCadre") Integer isCadre,
			@Param("keywords") String keywords, @Param("pageSize") Integer pageSize);

	// 人员列表数量统计
	int userListCount(@Param("departmentId") Integer departmentId, @Param("positionId") Integer positionId,
			@Param("isUnitLeader") Integer isUnitLeader, @Param("isCadre") Integer isCadre,
			@Param("keywords") String keywords);

	// 人员详情查询
	User userItem(@Param("id") Integer id);

	// 系统登录
	User login(@Param("policeId") String policeId, @Param("phone") String phone, @Param("password") String password);

	// 注册、修改密码验证
	User userCheck(@Param("policeId") String policeId, @Param("name") String name, @Param("idCard") String idCard);

	// 人员删除
	int userDelete(@Param("id") Integer id);

	// 查询参与人相关评价任务(api)
	List<User> userTaskList(@Param("departmentId") Integer departmentId, @Param("parentId") Integer parentId,
			@Param("isUnitLeader") Integer isUnitLeader, @Param("templateId") Integer templateId);

	// 人员详情查询根据policeId
	User policeItem(@Param("policeId") String policeId, @Param("password") String password,
			@Param("ddUserId") String ddUserId);

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
	User accountOnly(@Param("policeId") String policeId, @Param("phone") String phone, @Param("idCard") String idCard);

	// 查询当前群组是否被引用
	List<User> groupPolicesList(@Param("groupId") Integer groupId);

	/**
	 * 根据名字模糊查
	 * 
	 * @param name
	 * @return
	 */
	List<User> likeName(@Param("name") String name);

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
	 * 根据单位查询该单除了选中的约谈人以外的警员
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

	// 录分训练计划中综合体能项目查询
	List<User> trainRecordPoliceHeightList(@Param("projectId") Integer projectId,
			@Param("trainPhysicalId") Integer trainPhysicalId);

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

	// 用户数量统计
	int userProcessCount(@Param("departmentId") Integer departmentId, @Param("positionId") Integer positionId,
			@Param("isUnitLeader") Integer isUnitLeader, @Param("isCadre") Integer isCadre);

	// 训练人员列表查询
	List<User> userTrainList();

	// 查询全部警员详细信息数据
	List<User> userInfoAllList();

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
	List<User> findUserByDeptId(@Param("deptId") Integer deptId);

	/**
	 * 警员全息 通过key 查询警员信息
	 * @param key 警号、警员姓名
	 * @return
	 */
	List<HolographicPoliceListDO> holographicFindPoliceByKey(@Param("key") String key);

	/**
	 * 根据部门id查询
	 * @param deptId
	 * @return
	 */
	List<User> findByDeptId(@Param("deptId") Integer deptId);

	/**
	 * 人才库 俩名警员对比查询
	 * @param id
	 * @return
	 */
	TalentsParticularsResult findTalentsUserInfo(@Param("id") String id);

	/**
	 * 人才库列表
	 * @param talentsUserParam
	 * @return
	 */
	List<TalentsUserResult> talentsFindPageList(TalentsUserParam talentsUserParam);

	/**
	 * 人才列表分页查询
	 * @param talentsUserParam
	 * @return
	 */
	Integer talentsFindPageCount(TalentsUserParam talentsUserParam);

	/**
	 * 查询宣传报道警员排名TOP5
	 * @return
	 */
	List<UserDO> findUserRanKing();

	/**
	 * 修改警员健康风险全局查看标识
	 * @param policeId 警号
	 * @param showFlag 查看标识 1.允许，2.不允许
	 */
	void updateRiskHealthShowFlagByPoliceId(@Param("policeId") String policeId, @Param("showFlag") Integer showFlag);
}

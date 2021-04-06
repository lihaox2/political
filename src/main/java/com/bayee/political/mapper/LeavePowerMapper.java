package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.LeavePower;

public interface LeavePowerMapper {

	/**
	 * 根据id删除积分审核权限
	 * 
	 * @param id
	 * @return
	 */
	int deleteLeaveIntegralAuditPower(Integer id);

	int insert(LeavePower record);

	int insertSelective(LeavePower record);

	LeavePower selectByPrimaryKey(Integer id);

	/**
	 * 根据id修改积分审核权限
	 * 
	 * @param record
	 * @return
	 */
	int updateLeaveIntegralAuditPower(LeavePower record);

	int updateByPrimaryKey(LeavePower record);

	/**
	 * 查询积分审核权限或根据条件查询
	 * 
	 * @return
	 */
	List<LeavePower> getLeaveIntegralAuditPowerList(@Param("departmentId") Integer departmentId,
			@Param("keyword") String keyword, @Param("pageSize") Integer pageSize);

	/**
	 * 查询积分审核权限数量或根据条件查询数量
	 * 
	 * @return
	 */
	int getLeaveIntegralAuditPowerCount(@Param("departmentId") Integer departmentId, @Param("keyword") String keyword);

	/**
	 * 添加积分审核权限
	 * 
	 * @return
	 */
	int addLeaveIntegralAuditPower(@Param("leaveIntegralAuditPower") LeavePower leaveIntegralAuditPower);

	/**
	 * 根据id查询积分审核权限
	 * 
	 * @param id
	 * @return
	 */
	LeavePower getLeaveIntegralAuditPowerById(@Param("id") Integer id);

	/**
	 * 查询积分管理中所有的被记分人id
	 * 
	 * @return
	 */
	List<String> getScoredPoliceIds();

	/**
	 * 根据审核权限id及审核对象id查询在审核权限中是否存在
	 * 
	 * @return
	 */
	int checkIsExist(@Param("policeId") String policeId, @Param("id") Integer id);

	/**
	 * 根据兑换单位id及审核人id查询是否已创建审核权限
	 * 
	 * @return
	 */
	int isExist(@Param("departmentId") Integer departmentId, @Param("policeId") String policeId,
			@Param("moduleId") Integer moduleId);

	// 根据当前用户查询审核人
	LeavePower leavePowerItem(@Param("policeId") String policeId);

	// 查询当前民警所属领导
	List<LeavePower> LeavePowerPoliceList(String policeId);

}
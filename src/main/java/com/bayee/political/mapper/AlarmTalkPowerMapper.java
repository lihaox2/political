package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.AlarmTalkPower;
import com.bayee.political.domain.LeavePower;

public interface AlarmTalkPowerMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(AlarmTalkPower record);

	/**
	 * 新增约谈权限
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(AlarmTalkPower record);

	AlarmTalkPower selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(AlarmTalkPower record);

	int updateByPrimaryKeyWithBLOBs(AlarmTalkPower record);

	int updateByPrimaryKey(AlarmTalkPower record);

	/**
	 * 获取约谈权限列表
	 * 
	 * @return
	 */
	List<AlarmTalkPower> getAlarmTalkPower(@Param("departmentId") Integer departmentId,
			@Param("keywords") String keywords, @Param("pageSize") Integer pageSize);

	/**
	 * 获取约谈权限列表数量
	 * 
	 * @return
	 */
	Integer getAlarmTalkPowerCount(@Param("departmentId") Integer departmentId, @Param("keywords") String keywords);

	/**
	 * 查询约谈详情
	 * 
	 * @param id
	 * @return
	 */
	AlarmTalkPower getAlarmTalkPowerDetails(Integer id);

	/**
	 * 修改约谈权限
	 * 
	 * @return
	 */
	Integer alarmTalkPowerUpdate(@Param("alarmTalkPower") AlarmTalkPower alarmTalkPower);

	/**
	 * 查询指定部门对应下的约谈人数量
	 * 
	 * @param departmentId
	 * @param policeId
	 * @return
	 */
	Integer isHave(@Param("departmentId") Integer departmentId, @Param("policeId") String policeId);

	/**
	 * 删除约谈权限根据id
	 * 
	 * @param id
	 * @return
	 */
	Integer alarmTalkPowerDelete(@Param("id") Integer id);

	/**
	 * 检查约谈权限是否被引用
	 * 
	 * @param id
	 * @return
	 */
	Integer checkAlarmTalkPower(@Param("policeId") String policeId, @Param("id") Integer id);

	/**
	 * 获得所有被记分人id及约谈对象id
	 * 
	 * @return
	 */
	List<String> findEvaltionTalk();

	// 查询约谈人约谈条件
	LeavePower powerItem(@Param("scoringPoliceId") String scoringPoliceId,
			@Param("departmentId") Integer departmentId);

}
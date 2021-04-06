package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.LeaveRestAlarmRule;

public interface LeaveRestAlarmRuleMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(LeaveRestAlarmRule record);

	int insertSelective(LeaveRestAlarmRule record);

	LeaveRestAlarmRule selectByPrimaryKey(Integer id);

	/**
	 * 根据id修改调休预警规则
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(LeaveRestAlarmRule leaveRestAlarmRule);

	int updateByPrimaryKey(LeaveRestAlarmRule record);

	/**
	 * 获得调修预警规则
	 * 
	 * @return
	 */
	List<LeaveRestAlarmRule> getLeaveRestAlarmRuleList();

	// 获得调修预警规则
	LeaveRestAlarmRule getLeaveRestAlarmRuleItem(@Param("id") Integer id);
}
package com.bayee.political.mapper;

import java.util.List;

import com.bayee.political.domain.LeaveOvertimeRule;

public interface LeaveOvertimeRuleMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(LeaveOvertimeRule record);

	int insertSelective(LeaveOvertimeRule record);

	LeaveOvertimeRule selectByPrimaryKey(Integer id);

	/**
	 * 根据id修改加班规则
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(LeaveOvertimeRule leaveOvertimeRule);

	int updateByPrimaryKey(LeaveOvertimeRule record);

	/**
	 * 获得加班规则数据
	 * 
	 * @return
	 */
	List<LeaveOvertimeRule> getLeaveOvertimeRuleList();

}
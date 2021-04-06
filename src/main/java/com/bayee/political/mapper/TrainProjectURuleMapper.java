package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.TrainProjectURule;

public interface TrainProjectURuleMapper {
	int deleteByPrimaryKey(Integer id);

	/**
	 * 添加U型射击项目规则数据
	 * @param trainProjectURule
	 * @return
	 */
	int addTrainProjectURule(TrainProjectURule trainProjectURule);

	TrainProjectURule selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(TrainProjectURule record);

	int updateByPrimaryKey(TrainProjectURule record);

	// 根据项目规则id查询U型靶成绩规则
	List<TrainProjectURule> TrainProjectURuleList(Integer ruleId);
	
	/**
	 * 根据ruleId删除U型靶规则
	 * @return
	 */
	int deleteTrainProjectURule(@Param("ruleId") Integer ruleId);
	
}
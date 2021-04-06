package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.TrainProject;
import com.bayee.political.domain.TrainProjectRule;

public interface TrainProjectRuleMapper {

	/**
	 * 删除项目规则根据id
	 * 
	 * @param id
	 * @return
	 */
	int deleteTrainProjectRule(Integer id);

	/**
	 * 新增项目规则
	 * 
	 * @param trainProjectRule
	 * @return
	 */
	int addTrainProjectRule(TrainProjectRule trainProjectRule);

	int insertSelective(TrainProjectRule record);

	TrainProjectRule selectByPrimaryKey(Integer id);

	/**
	 * 修改项目规则
	 * 
	 * @param trainProjectRule
	 * @return
	 */
	int updateTrainProjectRule(TrainProjectRule trainProjectRule);

	int updateByPrimaryKey(TrainProjectRule record);

	/**
	 * 项目规则管理
	 * 
	 * @param keyWords 关键字
	 * @param pageSize 分页大小
	 * @return
	 */
	List<TrainProjectRule> getTrainProjectRuleList(@Param("keyWords") String keyWords,
			@Param("pageSize") Integer pageSize);

	/**
	 * 项目规则管理数量
	 * 
	 * @param keyWords 关键字
	 * @return
	 */
	int getTrainProjectRuleCount(@Param("keyWords") String keyWords);

	/**
	 * 查询所有组别对应的项目
	 * 
	 * @return
	 */
	List<TrainProjectRule> getGroAndPro();

	/**
	 * 获得所有射击类型
	 * 
	 * @return
	 */
	List<TrainProjectRule> getTrainFirearmType();
	
	/**
	 * 根据射击类型名称获得射击类型(射击项目中的射击类型)
	 * @return
	 */
	TrainProjectRule getTrainFirearmTypeByName(@Param("name") String name);

	// 根据项目id/组别查询算分规则
	TrainProjectRule trainProjectPoliceRuleItem(@Param("projectId") Integer projectId,
			@Param("groupId") Integer groupId);

	/**
	 * 根据id查询项目规则
	 * 
	 * @param id
	 * @return
	 */
	TrainProjectRule getTrainProjectRuleById(@Param("id") Integer id);
	
	/**
	 * 根据组别和体能训练项目查询合格分数及项目的规则
	 * @param projectId
	 * @param groupId
	 * @return
	 */
	TrainProjectRule getAchievement(
			@Param("projectId") Integer projectId,
			@Param("groupId") Integer groupId
			);
	
	/**
	 * 根据综合体能训练成绩id及群组id查询合格成绩及该项目的升降序
	 * @return
	 */
	TrainProjectRule getQualifiedAchievement(@Param("id") Integer id, 
			@Param("goupId") Integer goupId);
	
	/**
	 * 根据项目Id查询项目规则
	 * @param projectId
	 * @return
	 */
	List<TrainProjectRule> getTrainProjectRuleByProjectId(@Param("projectId") Integer projectId);

}
package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.TrainPhysicalProjectRecord;

public interface TrainPhysicalProjectRecordMapper {
	int deleteByPrimaryKey(Integer id);

	/**
	 * 添加组别项目对应
	 * 
	 * @param record
	 * @return
	 */
	int addTrainPhysicalProjectRecord(TrainPhysicalProjectRecord record);

	/**
	 * 根据多个组别字符串及体能训练id查询对应组别体能项目
	 * 
	 * @param ids 组别字符串
	 * @param id  体能训练id
	 * @return
	 */
	List<TrainPhysicalProjectRecord> trainPhysicalProjectRecordByGroupIds(@Param("ids") String ids,
			@Param("id") Integer id);

	/**
	 * 根据trainPhysicalId删除记录项目
	 * 
	 * @param trainPhysicalId
	 * @return
	 */
	int deleteByTrainPhysicalId(@Param("trainPhysicalId") Integer trainPhysicalId);

	int insertSelective(TrainPhysicalProjectRecord record);

	TrainPhysicalProjectRecord selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(TrainPhysicalProjectRecord record);

	int updateByPrimaryKey(TrainPhysicalProjectRecord record);

	// 查询当前用户所在组包括的项目
	TrainPhysicalProjectRecord projectNamesItem(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("policeId") String policeId);
	
	/**
	 * 根据规则ID查询是训练是否引用了该规则
	 * @param projectRuleId
	 * @return
	 */
	List<TrainPhysicalProjectRecord> getTrainPhysicalProjectRecordByProjectRuleId(@Param("projectRuleId") Integer projectRuleId);

}
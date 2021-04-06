package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.TrainMatchType;

public interface TrainMatchTypeMapper {

	/**
	 * 删除赛事类型
	 * 
	 * @param id
	 * @return
	 */
	int deleteTrainMatchType(Integer id);

	/**
	 * 新增赛事类型
	 * 
	 * @param trainMatchType
	 * @return
	 */
	int addTrainMatchType(TrainMatchType trainMatchType);

	int insertSelective(TrainMatchType record);

	/**
	 * 赛事类型详情
	 * 
	 * @param id
	 * @return
	 */
	TrainMatchType selectTrainMatchTypeById(Integer id);

	/**
	 * 修改赛事类型
	 * 
	 * @param record
	 * @return
	 */
	int updateTrainMatchType(TrainMatchType trainMatchType);

	int updateByPrimaryKey(TrainMatchType record);

	/**
	 * 查询赛事类型
	 * 
	 * @return
	 */
	List<TrainMatchType> getTrainMatchTypeList(@Param("keyword") String keyword, @Param("pageSize") Integer pageSize);

	/**
	 * 查询赛事类型
	 * 
	 * @return
	 */
	int getTrainMatchTypeCount(@Param("keyword") String keyword);

	// 个人赛事类型查询
	List<TrainMatchType> matchPersonalTypeList(@Param("policeId") String policeId);

}
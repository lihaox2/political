package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.TrainLeader;

public interface TrainLeaderMapper {

	// 领队删除
	int trainLeaderDelete(Integer id);

	// 领队新增
	int trainLeaderCreat(TrainLeader record);

	// 领队修改
	int trainLeaderUpdate(TrainLeader record);

	// 领队详情查询
	TrainLeader trainLeaderItem(@Param("id") Integer id, @Param("policeId") String policeId,
			@Param("departmentId") Integer departmentId);

	/**
	 * 查询领队
	 * 
	 * @param keyword  关键字
	 * @param pageSize 分页大小
	 * @return
	 */
	List<TrainLeader> getTrainLeaderList(@Param("keyword") String keyword, @Param("pageSize") Integer pageSize);

	/**
	 * 查询领队数量
	 * 
	 * @param keyword 关键字
	 * @return
	 */
	int getTrainLeaderListCount(@Param("keyword") String keyword);

	/**
	 * 根据id查询领队
	 * 
	 * @param id
	 * @return
	 */
	TrainLeader getTrainLeaderById(@Param("id") Integer id);

	// 是否是记分员判断查询
	List<TrainLeader> trainIsScorerList(@Param("policeId") String policeId);

}
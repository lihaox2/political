package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.TrainScorer;

public interface TrainScorerMapper {
	
	/**
	 * 删除记分员
	 * @param id
	 * @return
	 */
	int deleteTrainScorer(Integer id);

	/**
	 * 添加记分员
	 * @param trainScorer
	 * @return
	 */
	int addTrainScorer(TrainScorer trainScorer);

	int insertSelective(TrainScorer record);

	TrainScorer selectByPrimaryKey(Integer id);

	/**
	 * 修改记分员配置
	 * @param trainScorer
	 * @return
	 */
	int updateTrainScorer(TrainScorer trainScorer);

	int updateByPrimaryKey(TrainScorer record);

	/**
	 * 记分员配置列表
	 * 
	 * @param keyWords 关键字
	 * @param pageSize 分页大小
	 * @return
	 */
	List<TrainScorer> getTrainScorerList(@Param("keyWords") String keyWords, @Param("pageSize") Integer pageSize);

	/**
	 * 记分员配置列表数量
	 * 
	 * @param keyWords 关键字
	 * @return
	 */
	int getTrainScorerCount(@Param("keyWords") String keyWords);

	// 综合体能未录分数量
	int trainPhysicalNoScoreNum(@Param("policeId") String policeId);
	
	/**
	 * 根据id查询记分员配置表详情
	 * @param id
	 * @return
	 */
	TrainScorer getTrainScorerById(@Param("id") Integer id);

}
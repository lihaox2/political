package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.TrainActivityStyle;

public interface TrainActivityStyleMapper {

	/**
	 * 删除活动风采
	 * 
	 * @param id
	 * @return
	 */
	int deleteTrainActivityStyle(Integer id);

	/**
	 * 添加活动风采
	 * 
	 * @param trainActivityStyle
	 * @return
	 */
	int addTrainActivityStyle(TrainActivityStyle trainActivityStyle);

	int insertSelective(TrainActivityStyle record);

	TrainActivityStyle selectByPrimaryKey(Integer id);

	/**
	 * 修改活动风采
	 * 
	 * @param record
	 * @return
	 */
	int updateTrainActivityStyle(TrainActivityStyle trainActivityStyle);

	/**
	 * 获得活动风采
	 * 
	 * @param status      状态
	 * @param isRecommend 是否推荐
	 * @param keyWords    关键字
	 * @param pageSize    分页大小
	 * @return
	 */
	List<TrainActivityStyle> getTrainActivityStyleList(@Param("status") Integer status,
			@Param("isRecommend") Integer isRecommend, @Param("keyWords") String keyWords,
			@Param("sort") Integer sort, @Param("pageSize") Integer pageSize);

	/**
	 * 获得活动风采数量
	 * 
	 * @param status      状态
	 * @param isRecommend 是否推荐
	 * @param keyWords    关键字
	 * @return
	 */
	int getTrainActivityStyleCount(@Param("status") Integer status, @Param("isRecommend") Integer isRecommend,
			@Param("keyWords") String keyWords);

	/**
	 * 根据id获得活动风采
	 * 
	 * @param id
	 */
	TrainActivityStyle getTrainActivityStyleOnely(@Param("id") Integer id);

	/**
	 * 设置推荐或上下架
	 * 
	 * @param id
	 * @param isRecommend
	 * @param status
	 * @return
	 */
	Integer isRecommendOrStatus(@Param("id") Integer id, @Param("isRecommend") Integer isRecommend,
			@Param("status") Integer status);

	// 推荐查询
	List<TrainActivityStyle> recommendList(@Param("limitNum") Integer limitNum);

	// 活动风采查询
	List<TrainActivityStyle> activityStyleList();

	// 训练标兵查询
	List<TrainActivityStyle> pacesetterList();

	// 训练章程查询
	List<TrainActivityStyle> constitutionList();

	// 活动风采详情查询
	TrainActivityStyle activityStyleItem(@Param("id") Integer id);

	// 训练标兵详情查询
	TrainActivityStyle pacesetterItem(@Param("id") Integer id);

	// 训练章程详情查询
	TrainActivityStyle constitutionItem(@Param("id") Integer id);

}
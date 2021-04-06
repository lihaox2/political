package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.TrainPacesetter;

public interface TrainPacesetterMapper {
	
	/**
	 * 删除训练标兵
	 * @param id
	 * @return
	 */
    int deleteTrainPacesetter(Integer id);

    /**
     * 添加训练标兵
     * @param trainPacesetter
     * @return
     */
    int addTrainPacesetter(TrainPacesetter trainPacesetter);

    int insertSelective(TrainPacesetter record);
    
    /**
     * 根据id查询训练标兵
     * @param id
     * @return
     */
    TrainPacesetter getTrainPacesetterOnely(Integer id);

    /**
     * 修改训练标兵
     * @param trainPacesetter
     * @return
     */
    int updateTrainPacesetter(TrainPacesetter trainPacesetter);

    int updateByPrimaryKey(TrainPacesetter record);
    
    /**
     * 获得训练标兵
     * @param status 状态
     * @param isRecommend 是否推荐
     * @param keyWords 关键字
     * @param pageSize 分页大小
     * @return
     */
    List<TrainPacesetter> getTrainPacesetterList(
    		@Param("status") Integer status,
    		@Param("isRecommend") Integer isRecommend,
    		@Param("keyWords") String keyWords,
    		@Param("sort") Integer sort, 
			@Param("pageSize") Integer pageSize
    		);
    
    /**
     * 获得训练标兵数量
     * @param status 状态
     * @param isRecommend 是否推荐
     * @param keyWords 关键字
     * @param pageSize 分页大小
     * @return
     */
    int getTrainPacesetterCount(
    		@Param("status") Integer status,
    		@Param("isRecommend") Integer isRecommend,
    		@Param("keyWords") String keyWords
    		);
    
    /**
     * 训练标兵设置推荐或上下架
     * @param id
     * @param isRecommend
     * @param status
     * @return
     */
    Integer pacesetterIsRecommendOrStatus(
    		@Param("id") Integer id,
			@Param("isRecommend") Integer isRecommend,
			@Param("status") Integer status
    		);
    
}
package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.TrainConstitution;

public interface TrainConstitutionMapper {
	
	/**
	 * 删除训练章程
	 * @param id
	 * @return
	 */
    int deleteTrainConstitution(Integer id);

    /**
     * 添加训练章程
     * @param trainConstitution
     * @return
     */
    int addTrainConstitution(TrainConstitution trainConstitution);

    int insertSelective(TrainConstitution record);

    /**
     * 根据id查询训练章程
     * @param id
     * @return
     */
    TrainConstitution getTrainConstitutionOnely(Integer id);

    /**
     * 修改训练章程
     * @param trainConstitution
     * @return
     */
    int updateTrainConstitution(TrainConstitution trainConstitution);

    int updateByPrimaryKey(TrainConstitution record);
    
    /**
     * 训练章程
     * @param status 状态
     * @param isRecommend 是否推荐
     * @param keyWords 关键字
     * @param pageSize 分页大小
     * @return
     */
    List<TrainConstitution> getTrainConstitutionList(
    		@Param("status") Integer status,
    		@Param("isRecommend") Integer isRecommend,
    		@Param("keyWords") String keyWords,
    		@Param("sort") Integer sort, 
			@Param("pageSize") Integer pageSize
    		);
    
    /**
     * 训练章程数量
     * @param status 状态
     * @param isRecommend 是否推荐
     * @param keyWords 关键字
     * @return
     */
    int getTrainConstitutionCount(
    		@Param("status") Integer status,
    		@Param("isRecommend") Integer isRecommend,
    		@Param("keyWords") String keyWords
    		);
    
    /**
     * 训练章程设置推荐或上下架
     * @param id
     * @param isRecommend
     * @param status
     * @return
     */
    Integer constitutionIsRecommendOrStatus(
    		@Param("id") Integer id,
			@Param("isRecommend") Integer isRecommend,
			@Param("status") Integer status
    		);
    
}
package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.TrainMatchProject;

public interface TrainMatchProjectMapper {
	
	/**
	 * 删除比赛项目
	 * @param id
	 * @return
	 */
    int deleteTrainMatchProject(Integer id);

    /**
     * 添加比赛项目
     * @param record
     * @return
     */
    int addTrainMatchProject(TrainMatchProject trainMatchProject);

    int insertSelective(TrainMatchProject record);

    /**
     * 比赛项目详情
     * @param id
     * @return
     */
    TrainMatchProject selectTrainMatchProjectById(Integer id);

    /**
     * 修改比赛项目
     * @param record
     * @return
     */
    int updateTrainMatchProject(TrainMatchProject trainMatchProject);

    int updateByPrimaryKey(TrainMatchProject record);
    
    /**
     * 获得比赛项目列表
     * @param keyword
     * @param pageSize
     * @return
     */
    List<TrainMatchProject> getTrainMatchProjectList(
    		@Param("keyword") String keyword,
    		@Param("pageSize") Integer pageSize
    		);
    
    /**
     * 获得比赛项目列表数量
     * @param keyword
     * @param pageSize
     * @return
     */
    int getTrainMatchProjectCount(
    		@Param("keyword") String keyword
    		);
    
    /**
     * 根据赛事类型type查询该类型下的项目
     * @param type
     * @return
     */
    List<TrainMatchProject> getTrainMatchProjectByType(@Param("type") Integer type);
    
    
    
}
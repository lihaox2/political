package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.TrainProject;

public interface TrainProjectMapper {

	/**
	 * 删除训练项目
	 * 
	 * @param id
	 * @return
	 */
	int deleteTrainProject(Integer id);

	/**
	 * 新增训练项目
	 * 
	 * @param trainProject
	 * @return
	 */
	int addTrainProject(TrainProject trainProject);

	int insertSelective(TrainProject record);

	TrainProject selectByPrimaryKey(Integer id);

	/**
	 * 修改训练项目
	 * 
	 * @param trainProject
	 * @return
	 */
	int updateTrainProject(TrainProject trainProject);

	int updateByPrimaryKey(TrainProject record);

	/**
	 * 训练项目管理查询
	 * 
	 * @param keyWords
	 * @param pageSize
	 * @return
	 */
	List<TrainProject> getTrainProjectList(@Param("keyWords") String keyWords, @Param("pageSize") Integer pageSize);

	/**
	 * 训练项目管理查询数量
	 * 
	 * @param keyWords
	 * @return
	 */
	int getTrainProjectCount(@Param("keyWords") String keyWords);

	/**
	 * 训练项目管理查询项目名id是否U型
	 * 
	 * @return
	 */
	List<TrainProject> getTrainProjectNameIdIsU();

	/**
	 * 根据项目ids字符串查询项目名names
	 * 
	 * @param ids多个id的字符串
	 * @return
	 */
	String getTrainProjectNamesByIds(@Param("ids") String ids);

	/**
	 * 根据id查询训练项目详情
	 * 
	 * @param id
	 * @return
	 */
	TrainProject getTrainProjectById(@Param("id") Integer id);

	/**
	 * 根据项目名称查询该项目的数量
	 * 
	 * @param name 项目名称
	 * @return
	 */
	int getRepeatTrainProjectCount(@Param("name") String name);

	/**
	 * 根据训练成绩id及警号 查询训练项目
	 * 
	 * @param trainFirearmId
	 * @param policeId
	 * @return
	 */
	TrainProject getTrainProjectByConditon(@Param("trainFirearmId") Integer trainFirearmId,
			@Param("policeId") String policeId);

	/**
	 * 根据项目名称查询项目id
	 * 
	 * @param name
	 * @return
	 */
	Integer getTrainProjectByName(@Param("name") String name);

	// 参赛项目名称查询
	List<CalculationChart> trainProjectRankList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("trainPhysicalAchievementId") Integer trainPhysicalAchievementId, @Param("policeId") String policeId,
			@Param("projectId") Integer projectId);

	// 参赛枪械项目名称查询
	List<CalculationChart> trainProjectFirearmRankList(@Param("trainFirearmId") Integer trainFirearmId,
			@Param("policeId") String policeId);

	// 查询当前民警所属组别中的综合体能项目
	List<TrainProject> trainPoliceBelongToList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("groupId") Integer groupId);

	// 录分训练计划中综合体能项目查询
	List<TrainProject> trainRecordProjectPhysicalList(@Param("trainPhysicalId") Integer trainPhysicalId);

	// 录分训练计划中枪械查询
	List<TrainProject> trainRecordProjectFirearmList(@Param("trainFirearmId") Integer trainFirearmId);

	// 根据项目名查询项目id
	TrainProject trainProjectIdItem(@Param("name") String name);

	/**
	 * 根据类型查询训练项目
	 * @param type 1综合体能 2枪械
	 * @return
	 */
	List<TrainProject> findTrainProjectByType(@Param("type") Integer type);

}
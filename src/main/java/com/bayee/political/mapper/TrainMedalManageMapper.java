package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.TrainMedalManage;

public interface TrainMedalManageMapper {

	/**
	 * 删除奖章
	 * 
	 * @param id
	 * @return
	 */
	int deleteTrainMedal(Integer id);

	int insert(TrainMedalManage record);

	int insertSelective(TrainMedalManage record);

	/**
	 * 查询奖章详情
	 * 
	 * @param id
	 * @return
	 */
	TrainMedalManage getTrainMedalOnely(Integer id);

	/**
	 * 编辑奖章
	 * 
	 * @param trainMedalManage
	 * @return
	 */
	int updateTrainMedal(TrainMedalManage trainMedalManage);

	int updateByPrimaryKey(TrainMedalManage record);

	/**
	 * 查询奖章
	 * 
	 * @param type     奖章发行类型
	 * @param keyWords 关键字
	 * @param pageSize 分页大小
	 * @return
	 */
	List<TrainMedalManage> getTrainMedalList(@Param("type") Integer type, @Param("keyWords") String keyWords,
			@Param("sort") Integer sort, @Param("pageSize") Integer pageSize);

	/**
	 * 查询奖章
	 * 
	 * @param type     奖章发行类型
	 * @param keyWords 关键字
	 * @return
	 */
	int getTrainMedalCount(@Param("type") Integer type, @Param("keyWords") String keyWords);

	// 个人获得训练奖章/赛事奖章查询
	List<TrainMedalManage> trainGetMedalPersonalList(@Param("policeId") String policeId, @Param("type") Integer type);

	// 个人训练奖章/赛事奖章详情查询
	TrainMedalManage trainGetMedalPersonalItem(@Param("policeId") String policeId, @Param("type") Integer type,
			@Param("medalManageId") Integer medalManageId);

	// 个人奖章墙查询
	List<TrainMedalManage> trainMedalPersonalWallList(@Param("policeId") String policeId, @Param("type") Integer type);

	// 查询奖章数量
	List<TrainMedalManage> getMedalNumList();

	// 查询剩余奖牌id
	List<TrainMedalManage> surplusMedalList(@Param("type") Integer type, @Param("medalManageId") Integer medalManageId);

}
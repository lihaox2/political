package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.TrainGroup;
import com.bayee.political.domain.TrainGroupPolice;

public interface TrainGroupMapper {

	/**
	 * 删除组别
	 * 
	 * @param id
	 * @return
	 */
	int deleteGroup(Integer id);

	/**
	 * 添加组别
	 * 
	 * @param trainGroup 组别实体类
	 * @return
	 */
	int addTrainGroup(TrainGroup trainGroup);

	int insertSelective(TrainGroup record);

	/**
	 * 更新组别
	 * 
	 * @param record
	 * @return
	 */
	int updateTrainGroup(TrainGroup trainGroup);

	int updateByPrimaryKey(TrainGroup record);

	// 查询组别列表
	List<TrainGroup> trainGroupList(@Param("id") Integer id);

	// 查询组别详情
	TrainGroup trainGroupItem(@Param("id") Integer id);

	// 查询组别民警
	List<TrainGroupPolice> trainGroupPoliceList(@Param("policeId") String policeId);

	/**
	 * 根据id字符串 id1,id2,id3...获得训练组别姓名字符串 name1,name2,name3...
	 *
	 * @param ids ids 组别id字符串
	 * @return
	 */
	String getTrainGroupByIds(@Param("ids") String ids);

	/**
	 * 查询组别数据
	 * 
	 * @param sex      性别 1男2女
	 * @param minAge   最小年龄
	 * @param maxAge   最大年龄
	 * @param keyWords 关键字
	 * @param pageSize 分页大小
	 * @return
	 */
	List<TrainGroup> getTrainGroupList(@Param("sex") Integer sex, @Param("minAge") Integer minAge,
			@Param("maxAge") Integer maxAge, @Param("keyWords") String keyWords, @Param("pageSize") Integer pageSize);

	/**
	 * 查询组别数据
	 * 
	 * @param sex      性别 1男2女
	 * @param minAge   最小年龄
	 * @param maxAge   最大年龄
	 * @param keyWords 关键字
	 * @param pageSize 分页大小
	 * @return
	 */
	int getTrainGroupCount(@Param("sex") Integer sex, @Param("minAge") Integer minAge, @Param("maxAge") Integer maxAge,
			@Param("keyWords") String keyWords);

	/**
	 * 查询所有组别的id和name
	 * 
	 * @return
	 */
	List<TrainGroup> getTrainGroupNameId();

	/**
	 * 根据组别id查询组别是否被引用 >=0既被引用
	 * 
	 * @param id组别id
	 * @return
	 */
	int groupQuote(@Param("id") Integer id);

}
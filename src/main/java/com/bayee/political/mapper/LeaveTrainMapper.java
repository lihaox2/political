package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.LeaveTrain;

public interface LeaveTrainMapper {
	/**
	 * 根据id删除辽修养
	 * 
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Integer id);

	int insert(LeaveTrain record);

	/**
	 * 添加疗休养数据
	 * 
	 * @param record
	 * @return
	 */
	int insertLeaveTrainList(LeaveTrain leaveTrain);

	LeaveTrain selectByPrimaryKey(Integer id);

	/**
	 * 根据id修改辽修养
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(LeaveTrain leaveTrain);

	int updateByPrimaryKey(LeaveTrain record);

	/**
	 * 查询所有疗休养数据或根据条件
	 * 
	 * @return
	 */
	List<LeaveTrain> getLeaveTrainList(@Param("type") Integer type, @Param("departmentId") Integer departmentId,
			@Param("keyWord") String keyWord, @Param("pageSize") Integer pageSize);

	/**
	 * 查询所有疗休养数据或根据条件
	 * 
	 * @return
	 */
	int getLeaveTrainListCount(@Param("type") Integer type, @Param("departmentId") Integer departmentId,
			@Param("keyWord") String keyWord);

	/**
	 * 根据id获得疗修养数据
	 * 
	 * @param id
	 * @return
	 */
	LeaveTrain getLeaveTrainOne(@Param("id") Integer id);

	/**
	 * 批量插入疗休养数据
	 * 
	 * @return
	 */
	int addMoreLeaveTrain(@Param("leaveTrain") List<LeaveTrain> leaveTrain);

	// 各单位疗休养人员数量统计
	int leaveDepTrainStatisticsNum(@Param("type") Integer type, @Param("departmentId") Integer departmentId);

	// 中层领导查看自己部门疗休养人员数据
	List<LeaveTrain> leaveDepTrainStatisticsList(@Param("type") Integer type, @Param("policeId") String policeId);

}
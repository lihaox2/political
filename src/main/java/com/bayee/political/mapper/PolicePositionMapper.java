package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.PolicePosition;

public interface PolicePositionMapper {
	
	int deleteByPrimaryKey(Integer id);

	int insert(PolicePosition record);

	PolicePosition selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(PolicePosition record);

	// 职位列表
	List<PolicePosition> policePositionList();
	
	/**
	 * 根据policeId查询职位
	 * @return
	 */
	PolicePosition getPolicePositionByPoliceId(@Param("policeId") String policeId);
	
}
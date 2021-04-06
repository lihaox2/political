package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.PoliceStationPost;

public interface PoliceStationPostMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(PoliceStationPost record);

	int insertSelective(PoliceStationPost record);

	PoliceStationPost selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(PoliceStationPost record);

	int updateByPrimaryKey(PoliceStationPost record);

	// 派出所民警岗位查询(api)
	List<PoliceStationPost> policeStationPostApiList(@Param("policeType") Integer policeType);
	
	// 派出所民警岗位及总数查询(api)
	List<PoliceStationPost> policeStationPostApiListSum(@Param("policeType") Integer policeType);
	
}
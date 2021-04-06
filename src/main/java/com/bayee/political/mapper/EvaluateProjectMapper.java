package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.EvaluateProject;

public interface EvaluateProjectMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(EvaluateProject record);

	int insertSelective(EvaluateProject record);

	EvaluateProject selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(EvaluateProject record);

	int updateByPrimaryKey(EvaluateProject record);

	// 项目列表查询(后台)
	List<EvaluateProject> evaluateProjectList();

	// 查询项目所属部门
	EvaluateProject evaluateProjectItem(@Param("objectId") String objectId);
}
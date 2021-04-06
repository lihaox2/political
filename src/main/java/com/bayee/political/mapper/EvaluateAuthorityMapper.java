package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.EvaluateAuthority;
import com.bayee.political.domain.EvaluateRank;

public interface EvaluateAuthorityMapper {

	// 评价权限详情查询(后台)
	EvaluateAuthority evaluateAuthorityItem(@Param("id") Integer id);

	// 评价权限新增(后台)
	int evaluateAuthorityCreat(EvaluateAuthority evaluateAuthority);

	// 评价权限修改(后台)
	int evaluateAuthorityUpdate(EvaluateAuthority evaluateAuthority);

	// 评价权限删除(后台)
	int evaluateAuthorityDelete(@Param("id") Integer id);

	// 评价权限列表查询(后台)
	List<EvaluateAuthority> evaluationAuthorityList(@Param("keywords") String keywords,
			@Param("pageSize") Integer pageSize);

	// 评价权限列表数量统计(后台)
	int evaluationAuthorityListCount(@Param("keywords") String keywords);
	
	/**
	 * 根据id查询权限例表
	 */
	EvaluateAuthority getEvaluateAuthorityById(Integer id);
	
	/**
	 * 查询所有权限列表
	 */
	List<EvaluateAuthority> getAllEvaluateAuthority();
	
}
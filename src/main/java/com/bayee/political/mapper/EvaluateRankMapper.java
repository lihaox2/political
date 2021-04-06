package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.EvaluateRank;

public interface EvaluateRankMapper {

	// 等次规则删除
	int evaluationRankDelete(Integer id);

	// 等次规则新增
	int evaluationRankCreat(EvaluateRank evaluateRank);

	// 等次规则修改
	int evaluationRankUpdate(EvaluateRank evaluateRank);

	// 查询等次规则(api)
	List<EvaluateRank> evaluateRankApiList(Integer type);

	// 评价等次列表查询(后台)
	List<EvaluateRank> evaluationRankList(@Param("type") Integer type, @Param("childType") Integer childType,
			@Param("pageSize") Integer pageSize);

	// 评价等次列表数量统计(后台)
	int evaluationRankListCount(@Param("type") Integer type, @Param("childType") Integer childType);

	// 评价等次详情查询(后台)
	EvaluateRank evaluateRankItem(@Param("id") Integer id);

	// 查询全部评价等次列表(后台)
	List<EvaluateRank> evaluationAllRankList();

	/**
	 * 根据id查询等次列表
	 */
	EvaluateRank getEvaluateRankById(Integer id);

}
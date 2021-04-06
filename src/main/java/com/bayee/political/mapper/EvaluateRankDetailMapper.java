package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.EvaluateRankDetail;

public interface EvaluateRankDetailMapper {

	// 根据rankId删除等次详情规则(后台)
	int evaluateRankDetailTotalDelete(@Param("rankId") Integer rankId);

	// 新增等次详情规则（后台）
	int evaluateRankDetailCreat(EvaluateRankDetail evaluateRankDetail);

	// 评价等次详情列表修改(后台)
	int evaluateRankDetailUpdate(EvaluateRankDetail detailItem);

	// 查询等次详情规则(api)
	List<EvaluateRankDetail> evaluateRankDetailApiList(@Param("rankId") Integer rankId);

	// 评价等次详情信息查询(后台)
	List<EvaluateRankDetail> evaluateRankDetailList(@Param("rankId") Integer rankId);

	// 投票类型分类统计(api)
	List<EvaluateRankDetail> voteDetailTypeList(@Param("policeId") Integer policeId, @Param("taskId") Integer taskId);

	// 规则详情查询(api)
	EvaluateRankDetail evaluateRankDetailItem(@Param("id") Integer id);

	// 根据rankId和规则名称查询(api)
	EvaluateRankDetail rankDetailItem(@Param("rankId") Integer rankId, @Param("detailName") String detailName);

}
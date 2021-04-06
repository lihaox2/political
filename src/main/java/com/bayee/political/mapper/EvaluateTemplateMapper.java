package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.EvaluateTemplate;

public interface EvaluateTemplateMapper {

	// 评价模板查询(后台)不分页
	List<EvaluateTemplate> evaluateTemplateList();

	// 查询模板中的机关单位(api)
	List<EvaluateTemplate> templateDepList(@Param("id") Integer id);

	// 评价模板列表查询(后台)分页
	List<EvaluateTemplate> evaluationTemplateList(@Param("templateType") Integer templateType,
			@Param("keywords") String keywords, @Param("pageSize") Integer pageSize);

	// 评价模板列表数量统计(后台)
	int evaluationTemplateListCount(@Param("templateType") Integer templateType, @Param("keywords") String keywords);

	// 评价模板详情查询(后台)
	EvaluateTemplate evaluateTemplateItem(@Param("id") Integer id);

	// 评价模板新增(后台)
	int evaluateTemplateCreat(EvaluateTemplate evaluateTemplate);

	// 评价模板修改(后台)
	int evaluateTemplateUpdate(EvaluateTemplate evaluateTemplate);

	// 评价模板删除(后台)
	int evaluateTemplateDelete(@Param("id") Integer id);

	// 判断模板类型(后台)
	EvaluateTemplate templateType(@Param("id") Integer id);

	// 判断当前等次是否被模板引用(后台)
	List<EvaluateTemplate> existRankList(@Param("rankId") Integer rankId);
}
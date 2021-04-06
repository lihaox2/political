package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.QuestionnaireOption;

public interface QuestionnaireOptionMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(QuestionnaireOption record);

	int insertSelective(QuestionnaireOption record);

	QuestionnaireOption selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(QuestionnaireOption record);

	int updateByPrimaryKey(QuestionnaireOption record);

	// 问卷答案查询(api)
	List<QuestionnaireOption> questionnaireOptionList(@Param("questionnaireId") Integer questionnaireId);
}
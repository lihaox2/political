package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.QuestionnaireAnswer;

public interface QuestionnaireAnswerMapper {
	int deleteByPrimaryKey(Integer id);

	// 问卷新增
	int questionnaireCreat(QuestionnaireAnswer answer);

	QuestionnaireAnswer selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(QuestionnaireAnswer record);

	int updateByPrimaryKey(QuestionnaireAnswer record);

	// 个性标签查询
	List<QuestionnaireAnswer> answerItemList(@Param("policeId") String policeId,
			@Param("questionnaireId") Integer questionnaireId);

}
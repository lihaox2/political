package com.bayee.political.mapper;

import java.util.List;

import com.bayee.political.domain.Questionnaire;

public interface QuestionnaireMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Questionnaire record);

	int insertSelective(Questionnaire record);

	Questionnaire selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Questionnaire record);

	int updateByPrimaryKey(Questionnaire record);

	// 问卷查询(api)
	List<Questionnaire> questionnaireApiList();
}
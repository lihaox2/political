package com.bayee.political.service.impl;
/** 
* @author  shentuqiwei 
* @version 2020年6月8日 下午2:54:28 
*/

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.Questionnaire;
import com.bayee.political.domain.QuestionnaireAnswer;
import com.bayee.political.domain.QuestionnaireOption;
import com.bayee.political.mapper.QuestionnaireAnswerMapper;
import com.bayee.political.mapper.QuestionnaireMapper;
import com.bayee.political.mapper.QuestionnaireOptionMapper;
import com.bayee.political.service.QuestionnaireService;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

	@Autowired
	QuestionnaireMapper questionnaireMapper;

	@Autowired
	QuestionnaireAnswerMapper questionnaireAnswerMapper;

	@Autowired
	QuestionnaireOptionMapper questionnaireOptionMapper;

	// 问卷查询(api)
	@Override
	public List<Questionnaire> questionnaireApiList() {
		return questionnaireMapper.questionnaireApiList();
	}

	// 问卷答案查询(api)
	@Override
	public List<QuestionnaireOption> questionnaireOptionList(Integer questionnaireId) {
		return questionnaireOptionMapper.questionnaireOptionList(questionnaireId);
	}

	// 问卷新增
	@Override
	public int questionnaireCreat(QuestionnaireAnswer answer) {
		return questionnaireAnswerMapper.questionnaireCreat(answer);
	}

	// 个性标签查询
	@Override
	public List<QuestionnaireAnswer> answerItemList(String policeId, Integer questionnaireId) {
		return questionnaireAnswerMapper.answerItemList(policeId, questionnaireId);
	}
}

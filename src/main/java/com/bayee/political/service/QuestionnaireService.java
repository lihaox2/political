package com.bayee.political.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bayee.political.domain.Questionnaire;
import com.bayee.political.domain.QuestionnaireAnswer;
import com.bayee.political.domain.QuestionnaireOption;

/**
 * @author shentuqiwei
 * @version 2020年6月8日 下午2:53:05
 */
@Service
public interface QuestionnaireService {

	// 问卷查询(api)
	List<Questionnaire> questionnaireApiList();

	// 问卷答案查询(api)
	List<QuestionnaireOption> questionnaireOptionList(Integer questionnaireId);

	// 问卷新增
	int questionnaireCreat(QuestionnaireAnswer answer);

	// 个性标签查询
	List<QuestionnaireAnswer> answerItemList(String policeId, Integer questionnaireId);

}

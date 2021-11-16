/*
package com.bayee.political.service.impl;

import com.bayee.evaluation.domain.EvaluationTopic;
import com.bayee.evaluation.mapper.EvaluationTopicMapper;
import com.bayee.evaluation.service.EvaluationTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

*/
/**
 * @author tlt
 * @date 2021/10/25
 *//*

@Service
public class EvaluationTopicServiceImpl implements EvaluationTopicService {

    @Autowired
    private EvaluationTopicMapper evaluationTopicMapper;

    @Override
    public void addEvaluationTopic(EvaluationTopic evaluationTopic) {
        evaluationTopicMapper.insert(evaluationTopic);
    }

    @Override
    public Integer countEvaluationTopic(Integer id) {
        return evaluationTopicMapper.countByPrimaryKey(id);
    }

    @Override
    public List<EvaluationTopic> findEvaluationTopicList(EvaluationTopic evaluationTopic) {
        return evaluationTopicMapper.selectByActityId(evaluationTopic);
    }
}
*/

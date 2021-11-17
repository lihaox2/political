package com.bayee.political.service.impl;

import com.bayee.political.domain.EvaluationTopic;
import com.bayee.political.mapper.EvaluationTopicMapper;
import com.bayee.political.service.EvaluationTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tlt
 * @date 2021/10/25
 */
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

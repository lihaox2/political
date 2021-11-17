package com.bayee.political.service;

import com.bayee.political.domain.EvaluationTopic;

import java.util.List;

/**
 * @author tlt
 * @date 2021/10/25
 */
public interface EvaluationTopicService {

    /**
     * 评价活动-新增题目
     * @param evaluationTopic
     */
    void addEvaluationTopic(EvaluationTopic evaluationTopic);

    /**
     *查询题目数
     * @param id
     * @return
     */
    Integer countEvaluationTopic(Integer id);

    /**
     * 查询题目
     * @param evaluationTopic
     * @return
     */
    List<EvaluationTopic> findEvaluationTopicList(EvaluationTopic evaluationTopic);
}

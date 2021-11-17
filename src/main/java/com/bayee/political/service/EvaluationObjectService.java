package com.bayee.political.service;

import com.bayee.political.domain.EvaluationObject;

import java.util.List;

/**
 * @author tlt
 * @date 2021/10/25
 */

public interface EvaluationObjectService {

    /**
     * 评价对象-详情查询
     * @param id
     * @return
     */
    EvaluationObject findById (Integer id);

    /**
     * 评价对象-查询所有
     * @return
     */
    List<EvaluationObject> findAll ();

    /**
     * 警号查询
     * @return
     */
    EvaluationObject findByPoliceId (String policeId);
}

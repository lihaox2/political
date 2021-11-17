package com.bayee.political.service.impl;

import com.bayee.political.domain.EvaluationObject;
import com.bayee.political.mapper.EvaluationObjectMapper;
import com.bayee.political.service.EvaluationObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tlt
 * @date 2021/10/25
 */
@Service
public class EvaluationObjectServiceImpl implements EvaluationObjectService {

    @Autowired
    private EvaluationObjectMapper evaluationObjectMapper;

    @Override
    public EvaluationObject findById(Integer id) {
        return evaluationObjectMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<EvaluationObject> findAll() {
        return evaluationObjectMapper.findAll();
    }

    @Override
    public EvaluationObject findByPoliceId(String policeId) {
        return evaluationObjectMapper.selectByPoliceId(policeId);
    }
}

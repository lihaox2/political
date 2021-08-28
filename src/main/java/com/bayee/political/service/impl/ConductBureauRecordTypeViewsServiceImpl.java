package com.bayee.political.service.impl;

import com.bayee.political.domain.ConductBureauRecordTypeViews;
import com.bayee.political.mapper.ConductBureauRecordTypeViewsMapper;
import com.bayee.political.service.ConductBureauRecordTypeViewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/8/20
 */
@Service
public class ConductBureauRecordTypeViewsServiceImpl implements ConductBureauRecordTypeViewsService {

    @Autowired
    ConductBureauRecordTypeViewsMapper conductBureauRecordTypeViewsMapper;

    @Override
    public List<ConductBureauRecordTypeViews> findAll() {
        return conductBureauRecordTypeViewsMapper.findAll();
    }
}

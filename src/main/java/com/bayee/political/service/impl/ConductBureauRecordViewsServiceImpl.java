package com.bayee.political.service.impl;

import com.bayee.political.domain.ConductBureauRecordViews;
import com.bayee.political.mapper.ConductBureauRecordViewsMapper;
import com.bayee.political.service.ConductBureauRecordViewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/8/20
 */
@Service
public class ConductBureauRecordViewsServiceImpl implements ConductBureauRecordViewsService {

    @Autowired
    ConductBureauRecordViewsMapper conductBureauRecordViewsMapper;

    @Override
    public List<ConductBureauRecordViews> findAll() {
        return conductBureauRecordViewsMapper.findAll();
    }
}

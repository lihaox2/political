package com.bayee.political.service.impl;

import com.bayee.political.domain.MeasuresViews;
import com.bayee.political.mapper.MeasuresViewsMapper;
import com.bayee.political.service.MeasuresViewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/9/3 18:12
 */
@Service
public class MeasuresViewsServiceImpl implements MeasuresViewsService {

    @Autowired
    MeasuresViewsMapper measuresViewsMapper;

    @Override
    public List<MeasuresViews> findAll() {
        return measuresViewsMapper.findAll();
    }
}

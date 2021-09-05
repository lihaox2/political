package com.bayee.political.service.impl;

import com.bayee.political.domain.Measures;
import com.bayee.political.mapper.MeasuresMapper;
import com.bayee.political.service.MeasuresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/5/15
 */
@Service
public class MeasuresServiceImpl implements MeasuresService {

    @Autowired
    MeasuresMapper measuresMapper;

    @Override
    public List<Measures> findAllMeasures() {
        return measuresMapper.findAllMeasures();
    }

    @Override
    public Measures findByName(String name) {
        return measuresMapper.findByName(name);
    }

    @Override
    public void insertMeasures(Measures measures) {
        measuresMapper.insertMeasures(measures);
    }
}

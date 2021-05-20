package com.bayee.political.service.impl;

import com.bayee.political.mapper.TrainPhysicalMapper;
import com.bayee.political.service.TrainPhysicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xxl
 * @date 2021/5/20
 */
@Service
public class TrainPhysicalServiceImpl implements TrainPhysicalService {

    @Autowired
    TrainPhysicalMapper trainPhysicalMapper;

    @Override
    public Integer countAll() {
        return trainPhysicalMapper.countAll();
    }
}

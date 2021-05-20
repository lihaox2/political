package com.bayee.political.service.impl;

import com.bayee.political.mapper.TrainFirearmMapper;
import com.bayee.political.service.TrainFirearmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xxl
 * @date 2021/5/20
 */
@Service
public class TrainFirearmServiceImpl implements TrainFirearmService {

    @Autowired
    TrainFirearmMapper trainFirearmMapper;

    @Override
    public Integer countAll() {
        return trainFirearmMapper.countAll();
    }
}

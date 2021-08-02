package com.bayee.political.service.impl;

import com.bayee.political.domain.TrainProject;
import com.bayee.political.mapper.TrainProjectMapper;
import com.bayee.political.service.TrainProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/8/2
 */
@Service
public class TrainProjectServiceImpl implements TrainProjectService {

    @Autowired
    TrainProjectMapper trainProjectMapper;

    @Override
    public List<TrainProject> findTrainProjectByType(Integer type) {
        return trainProjectMapper.findTrainProjectByType(type);
    }
}

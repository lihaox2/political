package com.bayee.political.service.impl;

import com.bayee.political.domain.TrainPhysical;
import com.bayee.political.mapper.TrainPhysicalMapper;
import com.bayee.political.service.TrainPhysicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<TrainPhysical> findTrainPhysicalPage(Integer pageIndex, Integer pageSize, String date,
                                                     String trainName, Integer position) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        pageIndex = (pageIndex - 1) * pageSize;

        return trainPhysicalMapper.findTrainPhysicalPage(pageIndex, pageSize, date, trainName, position);
    }

    @Override
    public Integer countTrainPhysicalPage(String date, String trainName, Integer position) {
        return trainPhysicalMapper.countTrainPhysicalPage(date, trainName, position);
    }
}

package com.bayee.political.service.impl;

import com.bayee.political.domain.TrainFirearm;
import com.bayee.political.mapper.TrainFirearmMapper;
import com.bayee.political.service.TrainFirearmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<TrainFirearm> findTrainFirearmPage(Integer pageIndex, Integer pageSize, String trainBeginDate,
                                                   String trainEndDate, String trainName, Integer position) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        pageIndex = (pageIndex - 1) * pageSize;

        return trainFirearmMapper.findTrainFirearmPage(pageIndex, pageSize, trainBeginDate, trainEndDate, trainName, position);
    }

    @Override
    public Integer countTrainFirearmPage(String trainBeginDate, String trainEndDate, String trainName, Integer position) {
        return trainFirearmMapper.countTrainFirearmPage(trainBeginDate, trainEndDate, trainName, position);
    }
}

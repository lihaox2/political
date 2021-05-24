package com.bayee.political.service.impl;

import com.bayee.political.domain.TrainPhysicalAchievement;
import com.bayee.political.mapper.TrainPhysicalAchievementMapper;
import com.bayee.political.pojo.dto.TrainProjectDO;
import com.bayee.political.service.TrainPhysicalAchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xxl
 * @date 2021/5/21
 */
@Service
public class TrainPhysicalAchievementServiceImpl implements TrainPhysicalAchievementService {

    @Autowired
    TrainPhysicalAchievementMapper trainPhysicalAchievementMapper;

    @Override
    public List<TrainPhysicalAchievement> findTrainPhysicalAchievementPage(Integer pageIndex, Integer pageSize,
                                                                           Integer physicalId, Integer position,
                                                                           String key, String trainStr, Integer trainFlag, Integer searchFlag) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        pageIndex = (pageIndex - 1) * pageSize;
        List<Integer> trainList = new ArrayList<>();
        if (trainStr != null && !"".equals(trainStr)) {
            trainList = Arrays.asList(trainStr.split(",")).stream().map(e -> Integer.valueOf(e)).collect(Collectors.toList());
        }

        return trainPhysicalAchievementMapper.findTrainPhysicalAchievementPage(pageIndex, pageSize, physicalId, position,
                key, trainList, trainFlag, searchFlag);
    }

    @Override
    public Integer countTrainPhysicalAchievementPage(Integer physicalId, Integer position, String key, String trainStr, Integer trainFlag, Integer searchFlag) {
        List<Integer> trainList = new ArrayList<>();
        if (trainStr != null && !"".equals(trainStr)) {
            trainList = Arrays.asList(trainStr.split(",")).stream().map(e -> Integer.valueOf(e)).collect(Collectors.toList());
        }

        return trainPhysicalAchievementMapper.countTrainPhysicalAchievementPage(physicalId, position, key, trainList,
                trainFlag, searchFlag);
    }

    @Override
    public Integer countPhysicalByAchievementGrade(Integer physicalId, String policeId, Integer grade) {
        return trainPhysicalAchievementMapper.countPhysicalByAchievementGrade(physicalId, policeId, grade);
    }

    @Override
    public List<TrainProjectDO> findAllPhysicalTrainProject() {
        return trainPhysicalAchievementMapper.findAllPhysicalTrainProject();
    }
}

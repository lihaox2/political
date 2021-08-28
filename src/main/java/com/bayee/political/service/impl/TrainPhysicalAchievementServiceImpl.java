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
                                                                           String key, String trainStr, Integer trainFlag,
                                                                           Integer searchFlag, Integer deptId) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        pageIndex = (pageIndex - 1) * pageSize;
        List<Integer> trainList = new ArrayList<>();
        if (trainStr != null && !"".equals(trainStr)) {
            trainList = Arrays.asList(trainStr.split(",")).stream().map(e -> Integer.valueOf(e)).collect(Collectors.toList());
        }

        return trainPhysicalAchievementMapper.findTrainPhysicalAchievementPage(pageIndex, pageSize, physicalId, position,
                key, trainList, trainFlag, searchFlag, deptId);
    }

    @Override
    public Integer countTrainPhysicalAchievementPage(Integer physicalId, Integer position, String key,
                                                     String trainStr, Integer trainFlag, Integer searchFlag,
                                                     Integer deptId) {
        List<Integer> trainList = new ArrayList<>();
        if (trainStr != null && !"".equals(trainStr)) {
            trainList = Arrays.asList(trainStr.split(",")).stream().map(e -> Integer.valueOf(e)).collect(Collectors.toList());
        }

        return trainPhysicalAchievementMapper.countTrainPhysicalAchievementPage(physicalId, position, key, trainList,
                trainFlag, searchFlag, deptId);
    }

    @Override
    public Integer countPhysicalByAchievementGrade(Integer physicalId, String policeId, Integer grade) {
        return trainPhysicalAchievementMapper.countPhysicalByAchievementGrade(physicalId, policeId, grade);
    }

    @Override
    public List<TrainProjectDO> findAllPhysicalTrainProject() {
        return trainPhysicalAchievementMapper.findAllPhysicalTrainProject();
    }

    @Override
    public List<TrainPhysicalAchievement> findPhysicalTrainingRecordYearAndMonth(String physicalTrainingRecordYear,
                                                                                 String physicalTrainingRecordMonth,
                                                                                 String policeId) {
        return trainPhysicalAchievementMapper.findPhysicalTrainingRecordYearAndMonth(physicalTrainingRecordYear,
                physicalTrainingRecordMonth, policeId);
    }

    @Override
    public boolean physicalTrainingRecordStatistics(String policeId, Integer count, String beginDate, String endDate) {
        //如果有不合格次数就不能当做训练标兵
        Integer fillCount = trainPhysicalAchievementMapper.physicalTrainingRecordStatistics(policeId, 2, beginDate, endDate);
        if (fillCount != null && fillCount > 0) {
            return false;
        }

        //判断合格次数是否大于要求次数
        Integer passCount = trainPhysicalAchievementMapper.physicalTrainingRecordStatistics(policeId, 1, beginDate, endDate);
        if (passCount<count) {
            return false;
        }
        return true;
    }
}

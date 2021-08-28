package com.bayee.political.service.impl;

import com.bayee.political.domain.TrainFirearmAchievement;
import com.bayee.political.mapper.TrainFirearmAchievementMapper;
import com.bayee.political.service.TrainFirearmAchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/5/20
 */
@Service
public class TrainFirearmAchievementServiceImpl implements TrainFirearmAchievementService {

    @Autowired
    TrainFirearmAchievementMapper trainFirearmAchievementMapper;

    @Override
    public List<TrainFirearmAchievement> findTrainFirearmAchievementPage(Integer pageIndex, Integer pageSize,
                                                                         Integer firearmId, Integer position,
                                                                         String key, Integer searchFlag,Integer deptId) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        pageIndex = (pageIndex - 1) * pageSize;

        return trainFirearmAchievementMapper.findTrainFirearmAchievementPage(pageIndex, pageSize, firearmId, position,
                key, searchFlag, deptId);
    }

    @Override
    public Integer countTrainFirearmAchievementPage(Integer firearmId, Integer position, String key,
                                                    Integer searchFlag,Integer deptId) {
        return trainFirearmAchievementMapper.countTrainFirearmAchievementPage(firearmId, position, key, searchFlag, deptId);
    }

    @Override
    public List<TrainFirearmAchievement> findFirearmRecordYearAndMont(String firearmRecordYear,
                                                                      String firearmRecordMonth, String policeId) {
        return trainFirearmAchievementMapper.findFirearmRecordYearAndMonth(firearmRecordYear,firearmRecordMonth,policeId);
    }

    @Override
    public Integer policeFirearmAchievementAvg(String police, String beginDate, String endDate) {
        return trainFirearmAchievementMapper.policeFirearmAchievementAvg(police, beginDate, endDate);
    }
}

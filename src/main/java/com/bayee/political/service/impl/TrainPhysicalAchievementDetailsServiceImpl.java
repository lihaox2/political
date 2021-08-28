package com.bayee.political.service.impl;

import com.bayee.political.mapper.TrainPhysicalAchievementDetailsMapper;
import com.bayee.political.pojo.dto.RiskPhysicalTrainingRecordDO;
import com.bayee.political.pojo.dto.TrainPhysicalPoliceDetailsResultDO;
import com.bayee.political.service.TrainPhysicalAchievementDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxl
 * @date 2021/8/3
 */
@Service
public class TrainPhysicalAchievementDetailsServiceImpl implements TrainPhysicalAchievementDetailsService {

    @Autowired
    TrainPhysicalAchievementDetailsMapper trainPhysicalAchievementDetailsMapper;

    @Override
    public List<TrainPhysicalPoliceDetailsResultDO> findPoliceAchievementById(Integer physicalAchievementId) {
        return trainPhysicalAchievementDetailsMapper.findPoliceAchievementById(physicalAchievementId);
    }

    @Override
    public List<RiskPhysicalTrainingRecordDO> physicalTrainingRecordCareerQuery(Integer physicalAchievementId) {
        return trainPhysicalAchievementDetailsMapper.physicalTrainingRecordCareerQuery(physicalAchievementId);
    }

}

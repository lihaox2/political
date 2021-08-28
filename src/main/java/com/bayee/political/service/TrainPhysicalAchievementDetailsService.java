package com.bayee.political.service;

import com.bayee.political.pojo.dto.RiskPhysicalTrainingRecordDO;
import com.bayee.political.pojo.dto.TrainPhysicalPoliceDetailsResultDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xxl
 * @date 2021/8/3
 */
public interface TrainPhysicalAchievementDetailsService {

    /**
     * 查询警员训练
     * @param physicalAchievementId
     * @return
     */
    List<TrainPhysicalPoliceDetailsResultDO> findPoliceAchievementById(Integer physicalAchievementId);

    /**
     * 根据年份和月份进行查询
     * @param physicalTrainingRecordYear
     * @param physicalTrainingRecordMonth
     * @param policeId
     * @return
     */
    List<RiskPhysicalTrainingRecordDO> physicalTrainingRecordCareerQuery(Integer physicalAchievementId);
}

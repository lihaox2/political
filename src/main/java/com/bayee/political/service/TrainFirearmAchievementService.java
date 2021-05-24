package com.bayee.political.service;

import com.bayee.political.domain.TrainFirearmAchievement;

import java.util.List;

/**
 * @author xxl
 * @date 2021/5/20
 */
public interface TrainFirearmAchievementService {

    /**
     * 分页查询枪械训练数据
     * @param pageIndex
     * @param pageSize
     * @param firearmId
     * @param position
     * @param key
     * @param searchFlag
     * @return
     */
    List<TrainFirearmAchievement> findTrainFirearmAchievementPage(Integer pageIndex, Integer pageSize, Integer firearmId,
                                                                  Integer position, String key, Integer searchFlag);

    /**
     * 统计分页数据条数
     * @param firearmId
     * @param position
     * @param key
     * @param searchFlag
     * @return
     */
    Integer countTrainFirearmAchievementPage(Integer firearmId, Integer position, String key, Integer searchFlag);

}

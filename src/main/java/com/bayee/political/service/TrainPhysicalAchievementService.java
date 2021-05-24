package com.bayee.political.service;

import com.bayee.political.domain.TrainPhysicalAchievement;
import com.bayee.political.pojo.dto.TrainProjectDO;

import java.util.List;

/**
 * @author xxl
 * @date 2021/5/21
 */
public interface TrainPhysicalAchievementService {

    /**
     * 分页查询综合训练数据
     * @param pageIndex
     * @param pageSize
     * @param physicalId
     * @param position
     * @param key
     * @param trainStr
     * @param trainFlag
     * @param searchFlag
     * @return
     */
    List<TrainPhysicalAchievement> findTrainPhysicalAchievementPage(Integer pageIndex, Integer pageSize, Integer physicalId,
                                                                    Integer position, String key, String trainStr,
                                                                    Integer trainFlag, Integer searchFlag);

    /**
     * 统计分页数据条数
     * @param physicalId
     * @param position
     * @param key
     * @param trainStr
     * @param trainFlag
     * @param searchFlag
     * @return
     */
    Integer countTrainPhysicalAchievementPage(Integer physicalId, Integer position, String key, String trainStr,
                                              Integer trainFlag, Integer searchFlag);

    /**
     * 根据合格状态统计警员数据条数
     * @param physicalId
     * @param policeId
     * @param grade
     * @return
     */
    Integer countPhysicalByAchievementGrade(Integer physicalId, String policeId, Integer grade);

    /**
     * 查询所有综合训练项目类型
     * @return
     */
    List<TrainProjectDO> findAllPhysicalTrainProject();

}

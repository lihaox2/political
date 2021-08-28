package com.bayee.political.service;

import com.bayee.political.domain.TrainFirearmAchievement;
import org.apache.ibatis.annotations.Param;

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
                                                                  Integer position, String key, Integer searchFlag,
                                                                  Integer deptId);

    /**
     * 统计分页数据条数
     * @param firearmId
     * @param position
     * @param key
     * @param searchFlag
     * @return
     */
    Integer countTrainFirearmAchievementPage(Integer firearmId, Integer position, String key, Integer searchFlag,
                                             Integer deptId);

    /**
     * 根据年份和月份进行查询
     * @param firearmRecordYear
     * @param firearmRecordMonth
     * @param policeId
     * @return
     */
    List<TrainFirearmAchievement> findFirearmRecordYearAndMont(
            @Param("firearmRecordYear") String firearmRecordYear,
            @Param("firearmRecordMonth") String firearmRecordMonth,
            @Param("policeId") String policeId
    );

    /**
     * 统计警员平均射击成绩
     * @param police
     * @param beginDate yyyy-mm
     * @param endDate yyyy-mm
     * @return
     */
    Integer policeFirearmAchievementAvg(String police, String beginDate, String endDate);

}

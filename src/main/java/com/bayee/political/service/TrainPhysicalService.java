package com.bayee.political.service;

import com.bayee.political.domain.TrainPhysical;

import java.util.List;

/**
 * @author xxl
 * @date 2021/5/20
 */
public interface TrainPhysicalService {

    /**
     * 统计所有综合训练数据
     * @return
     */
    Integer countAll();

    /**
     * 分页查询综合训练
     * @param pageIndex
     * @param pageSize
     * @param date
     * @param trainName
     * @param position
     * @return
     */
    List<TrainPhysical> findTrainPhysicalPage(Integer pageIndex, Integer pageSize, String trainBeginDate,
                                              String trainEndDate, String trainName, Integer position);

    /**
     * 统计分页数据条数
     * @param date
     * @param trainName
     * @param position
     * @return
     */
    Integer countTrainPhysicalPage(String trainBeginDate, String trainEndDate, String trainName, Integer position);

}

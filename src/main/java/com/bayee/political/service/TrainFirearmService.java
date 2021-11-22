package com.bayee.political.service;

import com.bayee.political.domain.TrainFirearm;

import java.util.List;

/**
 * @author xxl
 * @date 2021/5/20
 */
public interface TrainFirearmService {

    /**
     * 统计所有枪械训练数据
     * @return
     */
    Integer countAll();

    /**
     * 统计所有枪械训练数据
     * @param date yyyy-MM-dd
     * @return
     */
    Integer countByDate(String date);

    /**
     * 分页查询枪械训练数据
     * @param pageIndex
     * @param pageSize
     * @param date
     * @param trainName
     * @param position
     * @return
     */
    List<TrainFirearm> findTrainFirearmPage(Integer pageIndex, Integer pageSize, String trainBeginDate,
                                            String trainEndDate, String trainName, Integer position);

    /**
     * 统计分页数据条数
     * @param date
     * @param trainName
     * @param position
     * @return
     */
    Integer countTrainFirearmPage(String trainBeginDate, String trainEndDate, String trainName, Integer position);

}

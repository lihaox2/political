package com.bayee.political.service;

import com.bayee.political.domain.TrainProject;

import java.util.List;

/**
 * @author xxl
 * @date 2021/8/2
 */
public interface TrainProjectService {

    /**
     * 训练类型查询
     * @param type 1综合体能 2枪械
     * @return
     */
    List<TrainProject> findTrainProjectByType(Integer type);

}

package com.bayee.political.service;

import com.bayee.political.json.TrainCensusResult;
import com.bayee.political.json.TrainQuantityResult;

import java.util.List;
import java.util.Map;

/**
 * @author zouya
 */
public interface PoliceSkillsService {

    /**
     * 统计：训练总数、本月训练数、参与总人数、总合格率
     * @return
     */
    TrainCensusResult census();

    /**
     * 排名信息
     * @return
     */
    Map<String,Object> rankData();

    /**
     * 近6个月不同训练类型合格情况趋势
     * @return
     */
    Map<String,Object> near6Month();

    /**
     * 不同类型训练数量占比
     * @return
     */
    List<TrainQuantityResult> trainingQuantity();
}

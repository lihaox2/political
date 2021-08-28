package com.bayee.political.pojo.dto;

import java.util.Date;

/**
 * @author xxl
 * @date 2021/8/8
 */
public class RiskPhysicalTrainingRecordDO {

    /**
     * 训练项目名称
     */
    private String trainProjectName;

    /**
     * 训练成绩
     */
    private Integer trainAchievement;

    public String getTrainProjectName() {
        return trainProjectName;
    }

    public void setTrainProjectName(String trainProjectName) {
        this.trainProjectName = trainProjectName;
    }

    public Integer getTrainAchievement() {
        return trainAchievement;
    }

    public void setTrainAchievement(Integer trainAchievement) {
        this.trainAchievement = trainAchievement;
    }

}

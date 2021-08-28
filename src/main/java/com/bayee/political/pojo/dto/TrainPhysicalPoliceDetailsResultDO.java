package com.bayee.political.pojo.dto;

/**
 * @author xxl
 * @date 2021/8/3
 */
public class TrainPhysicalPoliceDetailsResultDO {

    /**
     * 训练项目名称
     */
    private String trainProjectName;

    /**
     * 成绩
     */
    private String achievementStr;

    /**
     * 训练成绩
     */
    private String trainAchievement;

    public String getAchievementStr() {
        return achievementStr;
    }

    public void setAchievementStr(String achievementStr) {
        this.achievementStr = achievementStr;
    }

    public String getTrainProjectName() {
        return trainProjectName;
    }

    public void setTrainProjectName(String trainProjectName) {
        this.trainProjectName = trainProjectName;
    }

    public String getTrainAchievement() {
        return trainAchievement;
    }

    public void setTrainAchievement(String trainAchievement) {
        this.trainAchievement = trainAchievement;
    }
}

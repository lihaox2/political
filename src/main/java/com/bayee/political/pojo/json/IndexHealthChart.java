package com.bayee.political.pojo.json;

/**
 * @author xxl
 * @date 2021/5/20
 */
public class IndexHealthChart {

    /**
     * 健康人数
     */
    private Integer healthyCount;

    /**
     * 风险人数
     */
    private Integer alarmCount;

    /**
     * 类型名称
     */
    private String name;

    public Integer getHealthyCount() {
        return healthyCount;
    }

    public void setHealthyCount(Integer healthyCount) {
        this.healthyCount = healthyCount;
    }

    public Integer getAlarmCount() {
        return alarmCount;
    }

    public void setAlarmCount(Integer alarmCount) {
        this.alarmCount = alarmCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

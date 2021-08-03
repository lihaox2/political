package com.bayee.political.json;

/**
 * @author zouya
 */
public class TrainLineChartResult {

    /**
     * 月份
     */
    private String name;

    /**
     * 合格人数
     */
    private Integer value;

    /**
     * 合格率
     */
    private double qualifiedRate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public double getQualifiedRate() {
        return qualifiedRate;
    }

    public void setQualifiedRate(double qualifiedRate) {
        this.qualifiedRate = qualifiedRate;
    }
}

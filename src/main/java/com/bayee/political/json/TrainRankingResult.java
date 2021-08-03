package com.bayee.political.json;

/**
 * @author zouya
 */
public class TrainRankingResult {
    /**
     * 排名
     */
    private Integer rank;

    /**
     * 警号
     */
    private String policeId;

    /**
     * 警员姓名
     */
    private String policeName;

    /**
     * 单位
     */
    private String departmentName;

    /**
     * 合格率
     */
    private double qualifiedRate;

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public double getQualifiedRate() {
        return qualifiedRate;
    }

    public void setQualifiedRate(double qualifiedRate) {
        this.qualifiedRate = qualifiedRate;
    }
}

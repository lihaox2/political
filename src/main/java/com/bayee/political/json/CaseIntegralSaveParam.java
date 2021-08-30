package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/8/28 19:14
 */
public class CaseIntegralSaveParam {

    /**
     * 警号
     */
    private String policeId;

    /**
     * 所属部门
     */
    private Integer deptId;

    /**
     * 所属月度
     */
    private String businessTime;

    /**
     * 办案积分
     */
    private Double score;

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}

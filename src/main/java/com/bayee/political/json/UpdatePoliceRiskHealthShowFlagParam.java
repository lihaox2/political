package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/10/11 16:34
 */
public class UpdatePoliceRiskHealthShowFlagParam {

    /**
     * 警号
     */
    private String policeId;

    /**
     * 查看标识：1.允许，2.不允许
     */
    private Integer showFlag;

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public Integer getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(Integer showFlag) {
        this.showFlag = showFlag;
    }
}

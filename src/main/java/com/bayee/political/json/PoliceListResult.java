package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/5/7
 */
public class PoliceListResult {

    /**
     * 警员编号
     */
    private String policeId;

    /**
     * 警员名称
     */
    private String policeName;

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }
}

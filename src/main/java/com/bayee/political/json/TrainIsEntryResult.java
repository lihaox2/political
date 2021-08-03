package com.bayee.political.json;

/**
 * @author zouya
 */
public class TrainIsEntryResult {

    /**
     * 警号
     */
    private String policeId;

    /**
     * 是否参加（1未参加2已参加）
     */
    private Integer isEntry;

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public Integer getIsEntry() {
        return isEntry;
    }

    public void setIsEntry(Integer isEntry) {
        this.isEntry = isEntry;
    }
}

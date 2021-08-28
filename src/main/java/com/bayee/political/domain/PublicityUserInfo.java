package com.bayee.political.domain;

/**
 * 民警表 与 宣传表 的中间表
 */
public class PublicityUserInfo {

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 民警表id
     */
    private String policeId;
    /**
     * 宣传表id
     */
    private Integer infoId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }
}

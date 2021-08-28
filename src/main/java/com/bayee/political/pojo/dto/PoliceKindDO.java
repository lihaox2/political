package com.bayee.political.pojo.dto;

public class PoliceKindDO {

    /**
     * 警种名称
     */
    private String kindName;
    /**
     * 数量
     */
    private Integer countNum;

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public Integer getCountNum() {
        return countNum;
    }

    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }
}

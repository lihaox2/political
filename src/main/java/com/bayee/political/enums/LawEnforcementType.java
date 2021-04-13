package com.bayee.political.enums;

/**
 * 执法类型枚举
 * @author xxl
 * @date 2021/3/30
 */
public enum LawEnforcementType {

    /**
     * 接处警
     */
    CALL_THE_POLICE(12001, "jcj", 0.5),

    /**
     * 系统使用
     */
    SYSTEM_USE(12002, "xtsy", 1.0),

    /**
     * 涉案物品
     */
    GOODS_INVOLVED(12003, "sawp", 0.3),

    /**
     * 办案区
     */
    HANDLING_CASES(12004, "baq", 0.6),

    /**
     * 网上办案
     */
    NETWORK_HANDLING_CASES(12005, "wsba", 0.5);

    private Integer id;
    private String code;
    private double score;

    LawEnforcementType(Integer id, String code, double score) {
        this.id = id;
        this.code = code;
        this.score = score;
    }

    public Integer getId(){
        return this.id;
    }

    public String getCode(){
        return this.code;
    }

    public double getScore(){
        return this.score;
    }
}

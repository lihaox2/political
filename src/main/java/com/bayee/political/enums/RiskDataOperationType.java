package com.bayee.political.enums;

/**
 * @author xxl
 * @date 2021/11/1
 */
public enum RiskDataOperationType {

    /**
     * 新增
     */
    ADD("ADD"),

    /**
     * 修改
     */
    UPDATE("UPDATE"),

    /**
     * 删除
     */
    DELETE("DELETE"),

    /**
     * 导入
     */
    DATA_IMPORT("DATA_IMPORT");

    private String v;

    RiskDataOperationType(String v) {
        this.v = v;
    }

    public String getValue() {
        return this.v;
    }

}

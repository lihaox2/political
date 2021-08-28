package com.bayee.political.pojo;

/**
 * @author xxl
 * @date 2021/8/26 11:24
 */
public class RiskReportTypeStatisticsDO {

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 数据项统计
     */
    private Integer typeCount;

    public RiskReportTypeStatisticsDO() {

    }

    public RiskReportTypeStatisticsDO(String typeName, Integer typeCount) {
        this.typeName = typeName;
        this.typeCount = typeCount;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getTypeCount() {
        return typeCount;
    }

    public void setTypeCount(Integer typeCount) {
        this.typeCount = typeCount;
    }
}

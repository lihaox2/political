package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/8/28 16:27
 */
public class RiskHonourPageQueryParam {

    /**
     * 奖励类型 code
     */
    private String honourTypeCode;

    /**
     * 授奖机关级别
     */
    private String honorUnitLevel;

    /**
     * 模糊查询字段(警号、警员名称、奖励名称、授奖机关)
     */
    private String key;

    /**
     * 当前页码
     */
    private Integer pageIndex;

    /**
     * 数据条数
     */
    private Integer pageSize;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getHonourTypeCode() {
        return honourTypeCode;
    }

    public void setHonourTypeCode(String honourTypeCode) {
        this.honourTypeCode = honourTypeCode;
    }

    public String getHonorUnitLevel() {
        return honorUnitLevel;
    }

    public void setHonorUnitLevel(String honorUnitLevel) {
        this.honorUnitLevel = honorUnitLevel;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

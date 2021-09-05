package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/9/4 19:58
 */
public class PoliceRelevantPageQueryParam {

    /**
     * 类型code
     */
    private String typeCode;

    /**
     * 模糊搜索字段
     */
    private String key;

    /**
     * 页码
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

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

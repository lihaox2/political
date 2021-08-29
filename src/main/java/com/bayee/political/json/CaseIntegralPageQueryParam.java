package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/8/28 19:18
 */
public class CaseIntegralPageQueryParam {

    /**
     * 所属时间（yyyy-MM）
     */
    private String businessTime;

    /**
     * 所属部门
     */
    private Integer deptId;

    /**
     * 模糊查询字段
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

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

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
}

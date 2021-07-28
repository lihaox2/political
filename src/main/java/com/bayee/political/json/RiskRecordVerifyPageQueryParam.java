package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/7/20
 */
public class RiskRecordVerifyPageQueryParam {

    /**
     * 当前页码数
     */
    private Integer pageIndex;

    /**
     * 要显示的数据条数
     */
    private Integer pageSize;

    /**
     * 当前登录的警号
     */
    private String currentPolice;

    /**
     * 申诉类型
     */
    private  Integer appealType;

    /**
     * 部门编号
     */
    private Integer deptId;

    /**
     * 警号、警员名称
     */
    private String key;

    /**
     * 状态  1.待复核，2.已复核
     */
    private Integer state;

    /**
     * 申诉状态
     */
    private Integer verifyState;

    public Integer getVerifyState() {
        return verifyState;
    }

    public void setVerifyState(Integer verifyState) {
        this.verifyState = verifyState;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public String getCurrentPolice() {
        return currentPolice;
    }

    public void setCurrentPolice(String currentPolice) {
        this.currentPolice = currentPolice;
    }

    public Integer getAppealType() {
        return appealType;
    }

    public void setAppealType(Integer appealType) {
        this.appealType = appealType;
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
}

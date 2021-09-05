package com.bayee.political.json;

import java.util.List;

/**
 * @author xxl
 * @date 2021/9/4 19:23
 */
public class PoliceRelevantTypeListResult {

    /**
     * code
     */
    private String code;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 父级编码
     */
    private String pCode;

    /**
     * 子集
     */
    private List<PoliceRelevantTypeListResult> childList;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public List<PoliceRelevantTypeListResult> getChildList() {
        return childList;
    }

    public void setChildList(List<PoliceRelevantTypeListResult> childList) {
        this.childList = childList;
    }
}

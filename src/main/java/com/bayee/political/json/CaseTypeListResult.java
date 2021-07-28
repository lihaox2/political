package com.bayee.political.json;

import java.util.List;

/**
 * @author xxl
 * @date 2021/5/7
 */
public class CaseTypeListResult {

    /**
     * 类型id
     */
    private Integer id;

    /**
     * 类型描述
     */
    private String name;

    /**
     * 上级id
     */
    private Integer parentId;

    /**
     *
     */
    private List<CaseTypeListResult> childType;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<CaseTypeListResult> getChildType() {
        return childType;
    }

    public void setChildType(List<CaseTypeListResult> childType) {
        this.childType = childType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

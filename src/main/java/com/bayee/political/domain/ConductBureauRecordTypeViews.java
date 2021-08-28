package com.bayee.political.domain;

/**
 * @author xxl
 * @date 2021/8/19
 */
public class ConductBureauRecordTypeViews {

    /**
     * id
     */
    private String id;

    /**
     * 记分类型名称
     */
    private String name;

    /**
     * 父级记分类型id
     */
    private String parentId;

    /**
     * 记分类型 编码
     */
    private String flag;

    /**
     * 记分类型 层级
     */
    private Integer typeLevel;

    /**
     * 排序
     */
    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getTypeLevel() {
        return typeLevel;
    }

    public void setTypeLevel(Integer typeLevel) {
        this.typeLevel = typeLevel;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}

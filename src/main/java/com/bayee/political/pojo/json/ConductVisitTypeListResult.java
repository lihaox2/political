package com.bayee.political.pojo.json;

import java.util.List;

/**
 * @author xxl
 * @date 2021/5/9
 */
public class ConductVisitTypeListResult {

    /**
     * 类型id
     */
    private Integer id;

    /**
     * 父级id
     */
    private Integer parentId;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 扣除分数
     */
    private Double deductScore;

    /**
     * 子类型
     */
    private List<ConductVisitTypeListResult> childType;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    public Double getDeductScore() {
        return deductScore;
    }

    public void setDeductScore(Double deductScore) {
        this.deductScore = deductScore;
    }

    public List<ConductVisitTypeListResult> getChildType() {
        return childType;
    }

    public void setChildType(List<ConductVisitTypeListResult> childType) {
        this.childType = childType;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}

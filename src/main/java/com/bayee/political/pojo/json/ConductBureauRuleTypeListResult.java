package com.bayee.political.pojo.json;

import java.util.List;

/**
 * @author xxl
 * @date 2021/5/9
 */
public class ConductBureauRuleTypeListResult {

    /**
     * 类型id
     */
    private Integer id;

    /**
     * 父级id
     */
    private Integer parentId;

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
    private List<ConductBureauRuleTypeListResult> childType;

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

    public List<ConductBureauRuleTypeListResult> getChildType() {
        return childType;
    }

    public void setChildType(List<ConductBureauRuleTypeListResult> childType) {
        this.childType = childType;
    }
}

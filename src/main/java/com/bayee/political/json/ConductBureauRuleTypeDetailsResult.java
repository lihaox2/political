package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/5/8
 */
public class ConductBureauRuleTypeDetailsResult {

    /**
     * 类型id
     */
    private Integer rootTypeId;

    /**
     * 类型名称
     */
    private String rootTypeName;

    /**
     * 所属类型id
     */
    private Integer parentId;

    /**
     * 父级类型名称
     */
    private String parentTypeName;

    /**
     * 名称
     */
    private String name;

    /**
     * 扣除分数
     */
    private Double deductScore;

    public Integer getRootTypeId() {
        return rootTypeId;
    }

    public void setRootTypeId(Integer rootTypeId) {
        this.rootTypeId = rootTypeId;
    }

    public String getRootTypeName() {
        return rootTypeName;
    }

    public void setRootTypeName(String rootTypeName) {
        this.rootTypeName = rootTypeName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentTypeName() {
        return parentTypeName;
    }

    public void setParentTypeName(String parentTypeName) {
        this.parentTypeName = parentTypeName;
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
}

package com.bayee.political.pojo.json;

/**
 * @author xxl
 * @date 2021/5/8
 */
public class ConductBureauRuleTypePageResult {

    private Integer id;

    /**
     * 大类名称
     */
    private String rootName;

    /**
     * 所属类型
     */
    private String typeName;

    /**
     * 名称
     */
    private String name;

    /**
     * 扣除分数
     */
    private Double deductScore;

    public String getRootName() {
        return rootName;
    }

    public void setRootName(String rootName) {
        this.rootName = rootName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

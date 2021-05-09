package com.bayee.political.pojo.json;

/**
 * @author xxl
 * @date 2021/5/8
 */
public class ConductVisitTypeDetailsResult {

    /**
     * 所属类型id
     */
    private Integer parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 扣除分数
     */
    private Double deductScore;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
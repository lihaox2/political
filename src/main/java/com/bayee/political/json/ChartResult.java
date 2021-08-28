package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/6/12
 */
public class ChartResult {

    /**
     * chartId
     */
    private Integer id;

    /**
     * chart名称
     */
    private String name;

    /**
     * chart值
     */
    private Double value;

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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}

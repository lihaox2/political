package com.bayee.political.json;

import java.util.Date;
import java.util.List;

/**
 * @author xxl
 * @date 2021/8/28 10:03
 */
public class RiskHolographicResult {

    /**
     * 类型
     */
    private Integer type;

    /**
     * 时间
     */
    private Date date;

    /**
     * 警员名称
     */
    private String policeName;

    /**
     * 内容
     */
    private List<Object> result;

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Object> getResult() {
        return result;
    }

    public void setResult(List<Object> result) {
        this.result = result;
    }
}

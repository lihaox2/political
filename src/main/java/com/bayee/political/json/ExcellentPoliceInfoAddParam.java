package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/8/24 23:57
 */
public class ExcellentPoliceInfoAddParam {

    /**
     * 警号
     */
    private String policeId;

    /**
     * 标签id
     */
    private Integer label;

    /**
     * 描述
     */
    private String desc;

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public Integer getLabel() {
        return label;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

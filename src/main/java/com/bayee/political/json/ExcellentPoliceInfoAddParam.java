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
     * 标签id，多个使用,分割
     */
    private String labelStr;

    /**
     * 描述
     */
    private String desc;

    public String getLabelStr() {
        return labelStr;
    }

    public void setLabelStr(String labelStr) {
        this.labelStr = labelStr;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

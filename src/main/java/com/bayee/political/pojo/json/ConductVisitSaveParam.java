package com.bayee.political.pojo.json;

/**
 * @author xxl
 * @date 2021/5/7
 */
public class ConductVisitSaveParam {

    private String policeId;

    /**
     * 类型id
     */
    private Integer typeId;

    /**
     * 时间
     */
    private String date;

    /**
     * 备注
     */
    private String remarks;

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}

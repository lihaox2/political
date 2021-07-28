package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/7/20
 */
public class AppealSaveParam {

    /**
     * 类型id
     */
    private Integer typeId;

    /**
     * 数据项id
     */
    private Integer moduleId;

    /**
     * 申申诉人警号
     */
    private String appealPoliceId;

    /**
     * 申诉内容
     */
    private String appealContent;

    /**
     * 申诉分数
     */
    private Double appealScore;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getAppealPoliceId() {
        return appealPoliceId;
    }

    public void setAppealPoliceId(String appealPoliceId) {
        this.appealPoliceId = appealPoliceId;
    }

    public String getAppealContent() {
        return appealContent;
    }

    public void setAppealContent(String appealContent) {
        this.appealContent = appealContent;
    }

    public Double getAppealScore() {
        return appealScore;
    }

    public void setAppealScore(Double appealScore) {
        this.appealScore = appealScore;
    }
}

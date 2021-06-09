package com.bayee.political.pojo.json;

/**
 * @author xxl
 * @date 2021/5/7
 */
public class ConductBureauRuleSaveParam {

    private String policeId;

    /**
     * 计分项
     */
    private Integer typeId;

    /**
     * 计分等级
     */
    private Integer scoringLevel;

    /**
     * 计分单位
     */
    private Integer scoringDept;

    /**
     * 采取措施
     */
    private Integer measures;

    /**
     * 时间
     */
    private String date;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 图片集合
     */
    private String fileList;

    public String getFileList() {
        return fileList;
    }

    public void setFileList(String fileList) {
        this.fileList = fileList;
    }

    public Integer getScoringLevel() {
        return scoringLevel;
    }

    public void setScoringLevel(Integer scoringLevel) {
        this.scoringLevel = scoringLevel;
    }

    public Integer getScoringDept() {
        return scoringDept;
    }

    public void setScoringDept(Integer scoringDept) {
        this.scoringDept = scoringDept;
    }

    public Integer getMeasures() {
        return measures;
    }

    public void setMeasures(Integer measures) {
        this.measures = measures;
    }

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

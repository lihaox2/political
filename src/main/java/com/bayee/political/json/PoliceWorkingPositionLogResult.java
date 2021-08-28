package com.bayee.political.json;

/**
 *
 * @author xxl
 */
public class PoliceWorkingPositionLogResult {

    /**
     * id
     */
    private Integer id;

    /**
     * 职位
     */
    private String position;

    /**
     * 职级
     */
    private String positionLabel;

    /**
     * 工作描述
     */
    private String workingDesc;

    /**
     * 工作地点
     */
    private String workingPlace;

    /**
     * 时间描述
     */
    private String dateInfo;

    /**
     * 开始时间
     */
    private String beginDate;

    /**
     * 结束时间
     */
    private String endDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPositionLabel() {
        return positionLabel;
    }

    public void setPositionLabel(String positionLabel) {
        this.positionLabel = positionLabel;
    }

    public String getWorkingDesc() {
        return workingDesc;
    }

    public void setWorkingDesc(String workingDesc) {
        this.workingDesc = workingDesc;
    }

    public String getWorkingPlace() {
        return workingPlace;
    }

    public void setWorkingPlace(String workingPlace) {
        this.workingPlace = workingPlace;
    }

    public String getDateInfo() {
        return dateInfo;
    }

    public void setDateInfo(String dateInfo) {
        this.dateInfo = dateInfo;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}

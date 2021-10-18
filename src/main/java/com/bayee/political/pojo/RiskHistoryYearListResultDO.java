package com.bayee.political.pojo;

/**
 * @author xxl
 * @date 2021/9/7 10:46
 */
public class RiskHistoryYearListResultDO {

    private String dateTimeMonth;

    private String dateTime;

    private String lastDateTime;

    private String policeId;

    private Double totalNum;

    public String getLastDateTime() {
        return lastDateTime;
    }

    public void setLastDateTime(String lastDateTime) {
        this.lastDateTime = lastDateTime;
    }

    public String getDateTimeMonth() {
        return dateTimeMonth;
    }

    public void setDateTimeMonth(String dateTimeMonth) {
        this.dateTimeMonth = dateTimeMonth;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public Double getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Double totalNum) {
        this.totalNum = totalNum;
    }
}

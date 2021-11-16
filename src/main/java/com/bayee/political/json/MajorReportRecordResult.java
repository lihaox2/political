package com.bayee.political.json;

/**
 * @author tlt
 * @date 2021/11/10
 */
public class MajorReportRecordResult {

    /**
     * 上报人
     */
    private String name;

    /**
     * 创建时间
     */
    private String creationDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}

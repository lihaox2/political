package com.bayee.political.json;

import java.util.List;

/**
 * @author tlt
 * @date 2021/11/4
 */
public class ActivityStart {

    /**
     * 活动list
     */
    private List<ActivityStartResult> activityList;

    /**
     * 年份
     */
    private String year;

    public List<ActivityStartResult> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<ActivityStartResult> activityList) {
        this.activityList = activityList;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}

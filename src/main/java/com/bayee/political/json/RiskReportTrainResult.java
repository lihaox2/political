package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/10/26 15:21
 */
public class RiskReportTrainResult {

    /**
     * 训练名称
     */
    private String name;

    /**
     * 不合格项目成绩
     */
    private String unQualifiedProject;

    /**
     * 训练时间
     */
    private String date;

    public String getUnQualifiedProject() {
        return unQualifiedProject;
    }

    public void setUnQualifiedProject(String unQualifiedProject) {
        this.unQualifiedProject = unQualifiedProject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

package com.bayee.political.json;

import java.util.Date;

/**
 * @author xxl
 * @date 2021/6/14
 */
public class RiskPhysicalTrainingRecordResult {

    /**
     * 类型11-综合训练
     */
    private Integer type = 11;

    /**
     * 警员名称
     */
    private String policeName;

    /**
     * 50米跑 合格状态（合格，不合格，未参加）
     */
    private String sprintFlag;

    /**
     * 2000米跑 合格状态（合格，不合格，未参加）
     */
    private String longDistanceRunFlag;

    /**
     * 握力 合格状态（合格，不合格，未参加）
     */
    private String gripFlag;

    /**
     * 仰卧起坐 合格状态（合格，不合格，未参加）
     */
    private String abdominalCurlFlag;

    /**
     * 俯卧撑 合格状态（合格，不合格，未参加）
     */
    private String pushUpFlag;

    /**
     * 坐立体前屈 合格状态（合格，不合格，未参加）
     */
    private String sitAndReachFlag;

    /**
     * 立定跳远 合格状态（合格，不合格，未参加）
     */
    private String sargentJumpFlag;

    /**
     * 引体向上 合格状态（合格，不合格，未参加）
     */
    private String pullUpFlag;

    /**
     * 综合状态 合格状态（合格，不合格，未参加）
     */
    private String comprehensiveFlag;

    /**
     * sort
     */
    private Date date;

    public String getPullUpFlag() {
        return pullUpFlag;
    }

    public void setPullUpFlag(String pullUpFlag) {
        this.pullUpFlag = pullUpFlag;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    public String getSprintFlag() {
        return sprintFlag;
    }

    public void setSprintFlag(String sprintFlag) {
        this.sprintFlag = sprintFlag;
    }

    public String getLongDistanceRunFlag() {
        return longDistanceRunFlag;
    }

    public void setLongDistanceRunFlag(String longDistanceRunFlag) {
        this.longDistanceRunFlag = longDistanceRunFlag;
    }

    public String getGripFlag() {
        return gripFlag;
    }

    public void setGripFlag(String gripFlag) {
        this.gripFlag = gripFlag;
    }

    public String getAbdominalCurlFlag() {
        return abdominalCurlFlag;
    }

    public void setAbdominalCurlFlag(String abdominalCurlFlag) {
        this.abdominalCurlFlag = abdominalCurlFlag;
    }

    public String getPushUpFlag() {
        return pushUpFlag;
    }

    public void setPushUpFlag(String pushUpFlag) {
        this.pushUpFlag = pushUpFlag;
    }

    public String getSitAndReachFlag() {
        return sitAndReachFlag;
    }

    public void setSitAndReachFlag(String sitAndReachFlag) {
        this.sitAndReachFlag = sitAndReachFlag;
    }

    public String getSargentJumpFlag() {
        return sargentJumpFlag;
    }

    public void setSargentJumpFlag(String sargentJumpFlag) {
        this.sargentJumpFlag = sargentJumpFlag;
    }

    public String getComprehensiveFlag() {
        return comprehensiveFlag;
    }

    public void setComprehensiveFlag(String comprehensiveFlag) {
        this.comprehensiveFlag = comprehensiveFlag;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

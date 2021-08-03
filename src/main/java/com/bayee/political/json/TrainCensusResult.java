package com.bayee.political.json;

/**
 * @author zouya
 */
public class TrainCensusResult {

    /**
     * 训练总数
     */
    private Integer total;

    /**
     * 本月训练数
     */
    private Integer thisMonthCount;

    /**
     * 参与总人数
     */
    private Integer participantsTotal;

    /**
     * 总合格率
     */
    private double qualifiedRate;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getThisMonthCount() {
        return thisMonthCount;
    }

    public void setThisMonthCount(Integer thisMonthCount) {
        this.thisMonthCount = thisMonthCount;
    }

    public Integer getParticipantsTotal() {
        return participantsTotal;
    }

    public void setParticipantsTotal(Integer participantsTotal) {
        this.participantsTotal = participantsTotal;
    }

    public double getQualifiedRate() {
        return qualifiedRate;
    }

    public void setQualifiedRate(double qualifiedRate) {
        this.qualifiedRate = qualifiedRate;
    }
}

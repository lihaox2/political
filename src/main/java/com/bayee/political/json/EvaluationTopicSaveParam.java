package com.bayee.political.json;

/**
 * @author tlt
 * @date 2021/10/25
 */
public class EvaluationTopicSaveParam {

    /**
     * 题目名称
     */
    private String topicName;

    /**
     *选择（1为是，2为否）
     */
    private Integer isChoose;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Integer getIsChoose() {
        return isChoose;
    }

    public void setIsChoose(Integer isChoose) {
        this.isChoose = isChoose;
    }
}

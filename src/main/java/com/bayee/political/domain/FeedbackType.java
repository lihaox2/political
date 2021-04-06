package com.bayee.political.domain;

public class FeedbackType {
    private Integer id;

    private String feedbackName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFeedbackName() {
        return feedbackName;
    }

    public void setFeedbackName(String feedbackName) {
        this.feedbackName = feedbackName == null ? null : feedbackName.trim();
    }
}
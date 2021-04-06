package com.bayee.political.domain;

public class EvaluateParticipant {
    private Integer id;

    private String participantName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName == null ? null : participantName.trim();
    }
}
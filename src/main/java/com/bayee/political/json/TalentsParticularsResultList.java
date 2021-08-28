package com.bayee.political.json;

public class TalentsParticularsResultList {

    /**
     * 第一个人的信息
     */
    private TalentsParticularsResult firstListMessage;

    /**
     * 第二人的信息
     */
    private TalentsParticularsResult sendListMessage;

    public TalentsParticularsResult getFirstListMessage() {
        return firstListMessage;
    }

    public void setFirstListMessage(TalentsParticularsResult firstListMessage) {
        this.firstListMessage = firstListMessage;
    }

    public TalentsParticularsResult getSendListMessage() {
        return sendListMessage;
    }

    public void setSendListMessage(TalentsParticularsResult sendListMessage) {
        this.sendListMessage = sendListMessage;
    }
}

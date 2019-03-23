package com.bcu.pojo;

import java.util.Date;

public class Message {
    /**
     *
     */
    private Integer messageId;

    /**
     *
     */
    private String messageInitiatorId;

    /**
     *
     */
    private String messageInitiatorOpenid;

    /**
     *
     */
    private String messageReceiverId;

    /**
     *
     */
    private String messageSeatId;

    /**
     *
     */
    private String messageStatus;

    /**
     *
     */
    private Date messageCreateTime;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageInitiatorId() {
        return messageInitiatorId;
    }

    public void setMessageInitiatorId(String messageInitiatorId) {
        this.messageInitiatorId = messageInitiatorId;
    }

    public String getMessageInitiatorOpenid() {
        return messageInitiatorOpenid;
    }

    public void setMessageInitiatorOpenid(String messageInitiatorOpenid) {
        this.messageInitiatorOpenid = messageInitiatorOpenid;
    }

    public String getMessageReceiverId() {
        return messageReceiverId;
    }

    public void setMessageReceiverId(String messageReceiverId) {
        this.messageReceiverId = messageReceiverId;
    }

    public String getMessageSeatId() {
        return messageSeatId;
    }

    public void setMessageSeatId(String messageSeatId) {
        this.messageSeatId = messageSeatId;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Date getMessageCreateTime() {
        return messageCreateTime;
    }

    public void setMessageCreateTime(Date messageCreateTime) {
        this.messageCreateTime = messageCreateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", messageId=").append(messageId);
        sb.append(", messageInitiatorId=").append(messageInitiatorId);
        sb.append(", messageInitiatorOpenid=").append(messageInitiatorOpenid);
        sb.append(", messageReceiverId=").append(messageReceiverId);
        sb.append(", messageSeatId=").append(messageSeatId);
        sb.append(", messageStatus=").append(messageStatus);
        sb.append(", messageCreateTime=").append(messageCreateTime);
        sb.append("]");
        return sb.toString();
    }
}
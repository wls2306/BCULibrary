package com.bcu.pojo;

import java.util.Date;

public class Feedback {
    /**
	* 反馈编号
	*/
    private Integer fbId;

    /**
	* 反馈者用户号
	*/
    private String fbUserId;

    /**
	* 反馈人联系方式
	*/
    private String fbContact;

    /**
	* 反馈提交时间
	*/
    private Date fbTime;

    /**
	* 反馈内容
	*/
    private String fbContent;

    public Integer getFbId() {
        return fbId;
    }

    public void setFbId(Integer fbId) {
        this.fbId = fbId;
    }

    public String getFbUserId() {
        return fbUserId;
    }

    public void setFbUserId(String fbUserId) {
        this.fbUserId = fbUserId;
    }

    public String getFbContact() {
        return fbContact;
    }

    public void setFbContact(String fbContact) {
        this.fbContact = fbContact;
    }

    public Date getFbTime() {
        return fbTime;
    }

    public void setFbTime(Date fbTime) {
        this.fbTime = fbTime;
    }

    public String getFbContent() {
        return fbContent;
    }

    public void setFbContent(String fbContent) {
        this.fbContent = fbContent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fbId=").append(fbId);
        sb.append(", fbUserId=").append(fbUserId);
        sb.append(", fbContact=").append(fbContact);
        sb.append(", fbTime=").append(fbTime);
        sb.append(", fbContent=").append(fbContent);
        sb.append("]");
        return sb.toString();
    }
}
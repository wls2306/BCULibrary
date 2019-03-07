package com.bcu.pojo;

public class User {
    /**
	* 学号
	*/
    private String userId;

    /**
	* 微信会话编号
	*/
    private String userOpenId;

    /**
	* 姓名
	*/
    private String userName;

    /**
	* 学部
	*/
    private String userPart;

    /**
	* 学习时长
	*/
    private Integer userStudyTime;

    /**
	* 用户状态 封禁/正常
	*/
    private Integer userStatus;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserOpenId() {
        return userOpenId;
    }

    public void setUserOpenId(String userOpenId) {
        this.userOpenId = userOpenId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPart() {
        return userPart;
    }

    public void setUserPart(String userPart) {
        this.userPart = userPart;
    }

    public Integer getUserStudyTime() {
        return userStudyTime;
    }

    public void setUserStudyTime(Integer userStudyTime) {
        this.userStudyTime = userStudyTime;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userOpenId='" + userOpenId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPart='" + userPart + '\'' +
                ", userStudyTime=" + userStudyTime +
                ", userStatus=" + userStatus +
                '}';
    }
}
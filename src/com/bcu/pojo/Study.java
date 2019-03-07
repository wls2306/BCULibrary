package com.bcu.pojo;

import java.util.Date;

public class Study {
    /**
	* id
	*/
    private Integer id;

    /**
	* studySeatId
	*/
    private String studySeatId;

    /**
	* studyUserId
	*/
    private String studyUserId;

    /**
	* studyUserName
	*/
    private String studyUserName;

    /**
	* 
	*/
    private Date studyStartTime;

    /**
	* 
	*/
    private Date studyEndTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudySeatId() {
        return studySeatId;
    }

    public void setStudySeatId(String studySeatId) {
        this.studySeatId = studySeatId;
    }

    public String getStudyUserId() {
        return studyUserId;
    }

    public void setStudyUserId(String studyUserId) {
        this.studyUserId = studyUserId;
    }

    public String getStudyUserName() {
        return studyUserName;
    }

    public void setStudyUserName(String studyUserName) {
        this.studyUserName = studyUserName;
    }

    public Date getStudyStartTime() {
        return studyStartTime;
    }

    public void setStudyStartTime(Date studyStartTime) {
        this.studyStartTime = studyStartTime;
    }

    public Date getStudyEndTime() {
        return studyEndTime;
    }

    public void setStudyEndTime(Date studyEndTime) {
        this.studyEndTime = studyEndTime;
    }
}
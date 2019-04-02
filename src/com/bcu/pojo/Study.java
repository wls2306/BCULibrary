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


    private String studyUserPart;

    public String getStudyUserPart() {
        return studyUserPart;
    }

    public void setStudyUserPart(String studyUserPart) {
        this.studyUserPart = studyUserPart;
    }

    /**
	* 
	*/
    private Date studyStartTime;

    /**
	* 
	*/
    private Date studyEndTime;


    private String studyStartTimeString;

    private String studyEndTimeString;

    public String getStudyStartTimeString() {
        return studyStartTimeString;
    }

    public void setStudyStartTimeString(String studyStartTimeString) {
        this.studyStartTimeString = studyStartTimeString;
    }

    public String getStudyEndTimeString() {
        return studyEndTimeString;
    }

    public void setStudyEndTimeString(String studyEndTimeString) {
        this.studyEndTimeString = studyEndTimeString;
    }

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
package com.bcu.pojo;

import java.util.Date;

public class Seat {
    /**
	* 座位编号
	*/
    private int seatId;

    /**
	* 座位状态 -1：不可用 1：可用
	*/
    private int seatStatus;

    /**
	* 当前学习者编号
	*/
    private String seatUserId;

    /**
	* 当前学习者姓名
	*/
    private String seatUserName;

    /**
	* 学习开始时间
	*/
    private Date seatTimeStart;

    /**
	* 学习结束时间
	*/
    private Date seatTimeEnd;



    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public int getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(Integer seatStatus) {
        this.seatStatus = seatStatus;
    }

    public String getSeatUserId() {
        return seatUserId;
    }

    public void setSeatUserId(String seatUserId) {
        this.seatUserId = seatUserId;
    }

    public String getSeatUserName() {
        return seatUserName;
    }

    public void setSeatUserName(String seatUserName) {
        this.seatUserName = seatUserName;
    }

    public Date getSeatTimeStart() {
        return seatTimeStart;
    }

    public void setSeatTimeStart(Date seatTimeStart) {
        this.seatTimeStart = seatTimeStart;
    }

    public Date getSeatTimeEnd() {
        return seatTimeEnd;
    }

    public void setSeatTimeEnd(Date seatTimeEnd) {
        this.seatTimeEnd = seatTimeEnd;
    }

    /*
    public Seat(int seatId, int seatStatus, String seatUserId, String seatUserName, Date seatTimeStart, Date seatTimeEnd) {
        this.seatId = seatId;
        this.seatStatus = seatStatus;
        this.seatUserId = seatUserId;
        this.seatUserName = seatUserName;
        this.seatTimeStart = seatTimeStart;
        this.seatTimeEnd = seatTimeEnd;
    }*/
}
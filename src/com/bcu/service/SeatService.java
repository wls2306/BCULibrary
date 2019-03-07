package com.bcu.service;

import com.bcu.mapper.UserMapper;
import com.bcu.pojo.Study;
import com.bcu.util.StudyUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import com.bcu.pojo.Seat;
import com.bcu.mapper.SeatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatService {


    @Autowired
    private SeatMapper seatDao;
    @Autowired
    private UserMapper userDao;
    @Autowired
    private SeatService seatService;


    public boolean insert(Seat pojo){
       return seatDao.insert(pojo)>0?true:false;
    }

    public boolean checkInSeat(int seatId, String userId, Date start,Date end)
    {
        boolean rs=false;

        if ( seatService.seatIsEnable(seatId)) {


            String u = userDao.findUserNameByUserId(userId);
            Seat s = new Seat(); //(seatId, -1, userId, u, start, end);
            s.setSeatId(seatId);
            s.setSeatStatus(-1);
            s.setSeatUserId(userId);
            s.setSeatUserName(u);
            s.setSeatTimeStart(start);
            s.setSeatTimeEnd(end);

            rs = seatDao.update(s) > 0 ? true : false;
            Study study=new Study();
                study.setStudySeatId(seatId+"");
                study.setStudyUserId(userId);
                study.setStudyUserName(u);
                study.setStudyStartTime(start);
                study.setStudyEndTime(end);

            StudyUtil.checkIn(study);


        }
        return rs ;
    }

    public boolean seatIsEnable(int seatId)
    {
        Seat s=seatDao.findBySeatId(seatId);
        if (s.getSeatStatus()==1)
            return true;
       else
             return false;

    }

    /**
     * 请不要直接调用此方法！
     * 请调用 StudyUtil 中的  checkOut 方法完成完整的退座流程
     * 本流程无法完成时长的累计！
     * @param seatId
     * @return
     */
    public boolean checkOutSeat(int seatId)
    {

        Seat s=new Seat();//(seatId,1,null,null,null,null);
        s.setSeatId(seatId);
        s.setSeatStatus(1);
        boolean rs=seatDao.update(s)>0?true:false;

        return rs;
    }

    public Seat selectSeatBySeatId(String seatId)
    {

        Seat s=seatDao.findBySeatId(Integer.parseInt(seatId));
        return s;
    }





    public List<Seat> select(Seat pojo){
        return seatDao.select(pojo);
    }

    public int CountAvailableSeat() {  return seatDao.CountAvailableSeat();   }


}

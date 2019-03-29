package com.bcu.util;

import com.bcu.pojo.Study;
import com.bcu.service.SeatService;
import com.bcu.service.StudyService;
import com.bcu.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

/**
 * 空指针解决
 * https://blog.csdn.net/lianzhang861/article/details/79673134
 * 一直bean报错解决： content-scan 放在 application content 里
 */
@Component
public class StudyUtil {
    @Autowired
    private UserService userService;
    @Autowired
    private StudyService studyService;
    @Autowired
    private SeatService seatService;


    private static  StudyUtil studyUtil;

    @PostConstruct
    public void init( )
    {
       studyUtil=this;
       studyUtil.userService=this.userService;
       studyUtil.studyService=this.studyService;
       studyUtil.seatService=this.seatService;
    }


    public static ArrayList<Study> studyList=new ArrayList<>();

    public static void scan()
    {
        System.out.println("当前共有:"+studyList.size()+"人正在学习");
        for(int i=0;i<studyList.size();i++)
        {
            //      System.out.println("超时查找");
            Study s=studyList.get(i);
            Date end=s.getStudyEndTime();
           // System.out.println(end);
            if (end.before(new Date())) {
                 studyUtil.checkOut(s.getStudyUserId());
                System.out.println(s.getStudyUserName() +"超时退座");
            }
        }
    }

    public static boolean checkIn(Study study)
    {

        System.out.println("Check-in:"+study.getStudyUserName());
         return studyList.add(study);
    }

    public static boolean  checkOut(String seatId)
    {

        Study s;
        for (int i=0;i<studyList.size();i++)
        {
            s=studyList.get(i);
            if ( Integer.valueOf(s.getStudySeatId()) ==Integer.valueOf(seatId)) {
                s.setStudyEndTime(StudyUtil.getTime(0)[0]);//将退座时间改为当前时间
                studyList.remove(i);
                System.out.println("Check-out:"+s.getStudyUserId());
                /**
                 *
                 * *********座位解锁操作************
                 */

                    /* 空指针？  需要在applicationContent中加上工具包*/
                         studyUtil.seatService.checkOutSeat(Integer.parseInt(seatId));

                /**
                 * *********时长添加操作************
                 */
                    Calendar start=Calendar.getInstance();
                    start.setTime(s.getStudyStartTime());
                    Calendar end=Calendar.getInstance();
                    end.setTime(s.getStudyEndTime());
                    int minute=end.get(Calendar.MINUTE)-start.get(Calendar.MINUTE);
                    int hours=(end.get(Calendar.HOUR)-end.get(Calendar.HOUR))*60;
                    int result=(hours+minute)/60;
                    if (result>0)
                        studyUtil.userService.addUserStudyTime(s.getStudyUserId(),result);
                  //      userService.addUserStudyTime(s.getStudyUserId(),result);

                /**
                 * ********学习记录录入操作************
                 */
                try {
                    studyUtil.studyService.insert(s);
             //       studyService.insert(s);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                System.out.println(s.toString());

                return true;
            }
        }
        return false;
    }


    /**
     * 时间处理 输入占座时长 返回当前时间[0]和结束时间[1]
     * @param hours
     * @return date[]
     */
    public static Date[] getTime(int hours)
    {
        Date date[]=new Date[2];
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now=new Date();
        Calendar rightnow=Calendar.getInstance();
        rightnow.setTime(now);
        rightnow.add(Calendar.HOUR,hours);
        Date end=rightnow.getTime();
        System.out.println(sdf.format(now));
        System.out.println(sdf.format(end));
        date[0]=now;
        date[1]=end;
        return date;

    }







}

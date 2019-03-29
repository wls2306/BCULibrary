package com.bcu.controller;

import com.bcu.mapper.MessageMapper;
import com.bcu.pojo.*;
import com.bcu.service.MessageService;
import com.bcu.service.SeatService;
import com.bcu.service.StudyService;
import com.bcu.service.UserService;
import com.bcu.util.StudyUtil;
import com.bcu.util.WaitingUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;



@Controller
public class StudyController  {
    @Autowired
    private SeatService seatService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private StudyService studyService;
    @Autowired
    private MessageMapper messageMapper;


    @RequestMapping(value = "/checkin",method = {RequestMethod.GET,RequestMethod.POST})
    public void checkIn(HttpServletResponse resp, HttpServletRequest req) throws Exception
    {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=UTF8");
        PrintWriter out=resp.getWriter();
        HashMap<String,String> rs=new HashMap<String, String>();
        String userId=req.getParameter("userId");
        System.out.println(userId);
        String seatId=req.getParameter("seatId");
        System.out.println(seatId);
       // String userName=req.getParameter("userName");

         User u=userService.findByUserId(userId);


        String hours=req.getParameter("hours");
        System.out.println(hours);
        Date[] date= StudyUtil.getTime(Integer.parseInt(hours));

        rs.put("result",seatService.checkInSeat(Integer.parseInt(seatId),userId,date[0],date[1],u.getUserName(),u.getUserPart())+"");
        out.println(JSONObject.fromObject(rs));

    }



    @RequestMapping(value = "/getSeatInfo",method = {RequestMethod.GET,RequestMethod.POST})
    public void getSeatInfo(HttpServletRequest req,HttpServletResponse resp) throws Exception
    {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=UTF8");
        PrintWriter out=resp.getWriter();

        String seatId=req.getParameter("seatId");
        String userId=seatService.selectSeatUserIdBySeatId(Integer.parseInt(seatId));
        User u=userService.findByUserId(userId);
        HashMap<String,String> rsMap=new HashMap<>();
        if(seatService.seatIsEnable(Integer.parseInt(seatId)))
            rsMap.put("rs","true");
        else
            {
                rsMap.put("rs", "false");

                rsMap.put("openid", u.getUserOpenId());
                rsMap.put("userNiceName",u.getUserNickName());
                rsMap.put("userImage",u.getUserImage());
                rsMap.put("userId",u.getUserId());

            }

            out.println(JSONObject.fromObject(rsMap));

    }

    @RequestMapping(value = "/checkOut",method = {RequestMethod.GET,RequestMethod.POST})
    public void checkOut(HttpServletResponse resp,HttpServletRequest req) throws Exception
    {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=UTF8");
        PrintWriter out=resp.getWriter();

        String seatId=req.getParameter("seatId");
        boolean rs= StudyUtil.checkOut(seatId);
        out.println(rs+"");
    }

    @RequestMapping(value = "/report",method = {RequestMethod.GET,RequestMethod.POST})
    public void doReport(HttpServletRequest req,HttpServletResponse resp)throws Exception
    {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=UTF8");
        PrintWriter out=resp.getWriter();

        String initiatorId=req.getParameter("initiatorId");
        String seatId=req.getParameter("seatId");
        String initiatorOpenId=req.getParameter("initiatorOpenid");
        String receiverId=seatService.selectSeatUserIdBySeatId(Integer.parseInt(seatId));

        Wait w=new Wait();
        w.setSeatId(seatId);
        w.setEndTime(WaitingUtil.getFinalTime(1));

        HashMap<String,String> rs=new HashMap<>();
        rs.put("result",( messageService.createMessage(initiatorId,receiverId,seatId,initiatorOpenId)&&WaitingUtil.checkInWatingList(w))+"");
        out.println(JSONObject.fromObject(rs));

    }

    @RequestMapping(value = "/cancelWaiting",method = {RequestMethod.GET,RequestMethod.POST})
    public void cancelWaiting(HttpServletRequest req,HttpServletResponse resp)throws Exception
    {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=UTF8");

        PrintWriter out=resp.getWriter();

        String seatId=req.getParameter("seatId");

        HashMap rs=new HashMap();
        rs.put("result",WaitingUtil.cancelOutWaitingList(Integer.parseInt(seatId))+"");

        out.println(JSONObject.fromObject(rs));
    }

    /**
     * 根据学号查找该学生是否坐下
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/getSeatInfoByStudentId")
    public void getSeatInfoByStudentId(HttpServletRequest req,HttpServletResponse resp)throws Exception
    {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=UTF8");

        PrintWriter out=resp.getWriter();
        String userId=req.getParameter("userId");

        String seatId=studyService.selectSeatInfoByStudentId(userId);

        HashMap<String,String> result=new HashMap<>();


        if (seatId==null)
            result.put("result","false");
        else {
            result.put("result", "true");
            result.put("seat", seatId + "");

        }

        out.println(JSONObject.fromObject(result));


    }

    @RequestMapping(value = "/seatIsWaiting",method = {RequestMethod.GET})
    public void seatIsWaiting(HttpServletResponse resp,HttpServletRequest req)throws Exception
    {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=UTF8");

        PrintWriter out=resp.getWriter();

        String seatId=req.getParameter("seatId");

        String n=messageService.selectByMessageSeatId(seatId);
        System.out.println("n:"+n);
        if (n!=null)
            out.println(true); //有人等待
        else
            out.println( false);//无人等待


    }

    /**
     * ***************************   注意 ！ 此处等待时间 写死 为 15分钟 ！ ***********************
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/userIsWaiting")
    public void userIsWaiting(HttpServletRequest req,HttpServletResponse resp)throws Exception
    {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=UTF8");
        PrintWriter out=resp.getWriter();

        String userId=req.getParameter("userId");

        Message m=messageMapper.selectByMessageInitiatorId(userId);
        HashMap<String, String> result = new HashMap<>();
        if (m!=null) {

            Calendar calendar=Calendar.getInstance();
            calendar.setTime(m.getMessageCreateTime());
           /** ***************************   注意 ！ 此处等待时间 写死 为 15分钟 ！*/
            calendar.add(Calendar.MINUTE,15);


            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String f = sdf.format(calendar.getTime());
            String[] time = f.split(":");
            result.put("result","true");
            /*
            result.put("Hours", time[0]);
            result.put("Minute", time[1]);
            result.put("Second", time[2]);
*/

            SimpleDateFormat now = new SimpleDateFormat("HH:mm:ss");
            String fnow = sdf.format(new Date());
            String[] nowtime = fnow.split(":");
            result.put("result","true");

            System.out.println("创建时间："+m.getMessageCreateTime());
            System.out.println("当前时间："+nowtime[0]+":"+nowtime[1]+":"+nowtime[2]);
            System.out.println("到期时间："+time[0]+":"+time[1]+":"+time[2]);

            int balance=(Integer.valueOf(time[0])-Integer.valueOf(nowtime[0]))*60*60
                    +(Integer.valueOf(time[1])-Integer.valueOf(nowtime[1]))*60
                    +(Integer.valueOf(time[2])-Integer.valueOf(nowtime[2]));

            System.out.println("balance"+balance);
            result.put("Second", balance+"");
        }else {
            result.put("result","false");
        }
        out.println(JSONObject.fromObject(result));



    }



   /* @RequestMapping(value = "/onSeat",method = {RequestMethod.GET})
    public void isOnSeat(HttpServletRequest req,HttpServletResponse resp)
    {

    }*/



}

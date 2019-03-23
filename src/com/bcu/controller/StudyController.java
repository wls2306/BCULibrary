package com.bcu.controller;

import com.bcu.pojo.Message;
import com.bcu.pojo.Seat;
import com.bcu.pojo.Study;
import com.bcu.pojo.Wait;
import com.bcu.service.MessageService;
import com.bcu.service.SeatService;
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

        String hours=req.getParameter("hours");
        System.out.println(hours);
        Date[] date= StudyUtil.getTime(Integer.parseInt(hours));

        rs.put("result",seatService.checkInSeat( Integer.parseInt(seatId),userId,date[0],date[1])+"");
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
        String openid=userService.selectUserOpenIdByUserId(userId);
        HashMap<String,String> rsMap=new HashMap<>();
        if(seatService.seatIsEnable(Integer.parseInt(seatId)))
            rsMap.put("rs","true");
        else
            {
                rsMap.put("rs", "false");
                rsMap.put("openid", openid);
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
        out.println("true");
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
        w.setEndTime(WaitingUtil.getFinalTime(15));

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



   /* @RequestMapping(value = "/onSeat",method = {RequestMethod.GET})
    public void isOnSeat(HttpServletRequest req,HttpServletResponse resp)
    {

    }*/



}

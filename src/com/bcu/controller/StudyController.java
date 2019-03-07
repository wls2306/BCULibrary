package com.bcu.controller;

import com.bcu.pojo.Message;
import com.bcu.pojo.Seat;
import com.bcu.pojo.Study;
import com.bcu.pojo.Wait;
import com.bcu.service.MessageService;
import com.bcu.service.SeatService;
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
    private MessageService messageService;

    @RequestMapping(value = "/checkin",method = {RequestMethod.GET,RequestMethod.POST})
    public void checkIn(HttpServletResponse resp, HttpServletRequest req) throws Exception
    {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=UTF8");
        PrintWriter out=resp.getWriter();
        HashMap<String,String> rs=new HashMap<String, String>();
        String userId=req.getParameter("userId");
        String seatId=req.getParameter("seatId");
       // String userName=req.getParameter("userName");
        String hours=req.getParameter("hours");
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

        if(seatService.seatIsEnable(Integer.parseInt(seatId)))
            out.println(1);
        else
            out.println(-1);
    }

    @RequestMapping(value = "/checkOut",method = {RequestMethod.GET,RequestMethod.POST})
    public void checkOut(HttpServletResponse resp,HttpServletRequest req) throws Exception
    {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=UTF8");
        PrintWriter out=resp.getWriter();

        String userId=req.getParameter("userId");
        boolean rs=StudyUtil.checkOut(userId);
        out.println(rs);
    }

    @RequestMapping(value = "/report",method = {RequestMethod.GET,RequestMethod.POST})
    public void doReport(HttpServletRequest req,HttpServletResponse resp)throws Exception
    {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=UTF8");
        PrintWriter out=resp.getWriter();

        String initiatorId=req.getParameter("initiatorId");
        String receiverId=req.getParameter("receiverId");
        String seatId=req.getParameter("seatId");


        Wait w=new Wait();
        w.setSeatId(seatId);
        w.setEndTime(WaitingUtil.getFinalTime(15));
        WaitingUtil.checkInWatingList(w);
        messageService.createMessage(initiatorId,receiverId,seatId);
        HashMap<String,String> rs=new HashMap<>();
        rs.put("result",( messageService.createMessage(initiatorId,receiverId,seatId)&&WaitingUtil.checkInWatingList(w))+"");
        out.println(JSONObject.fromObject(rs));

    }



}

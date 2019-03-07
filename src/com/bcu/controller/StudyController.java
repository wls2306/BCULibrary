package com.bcu.controller;

import com.bcu.pojo.Seat;
import com.bcu.pojo.Study;
import com.bcu.service.SeatService;
import com.bcu.util.StudyUtil;
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
        String userName=req.getParameter("userName");
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
}

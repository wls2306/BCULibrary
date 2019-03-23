package com.bcu.controller;

import com.bcu.service.SeatService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;


@Controller
public class IndexController {
    @Autowired
    private SeatService seatService;
    @RequestMapping("/getinfo")
    public void getInfo(HttpServletRequest req, HttpServletResponse resp)throws Exception
    {


        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=UTF8");

        PrintWriter out=resp.getWriter();

        HashMap<String,String> rs=new HashMap<String, String>();
        String peopleNumber=seatService.CountAvailableSeat()+"";
        /**
         * 在馆人数
         */
        rs.put("peopleNumber",200-Integer.valueOf(peopleNumber)+"");
       /**
         * 可用座位
        */
        rs.put("seatNumber",peopleNumber);
        out.println(JSONObject.fromObject(rs));
       // out.println(10);











    }
}

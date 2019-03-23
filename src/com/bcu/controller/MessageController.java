package com.bcu.controller;

import com.bcu.mapper.MessageMapper;
import com.bcu.pojo.Message;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

@Controller
public class MessageController {

    @Autowired
    private MessageMapper messageMapper;

    /**
     *传入 个人 userId
     * @param resp
     * @param req
     * @throws Exception
     */
    @RequestMapping(value = "/getMessage",method = {RequestMethod.GET,RequestMethod.POST})
    public void getMessageByUserId(HttpServletResponse resp, HttpServletRequest req) throws Exception
    {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=UTF8");

        PrintWriter out=resp.getWriter();
        String id=req.getParameter("userId");


        Message m=messageMapper.selectByMessageReceiverId(id);

        out.println(JSONObject.fromObject(m));

    }
}

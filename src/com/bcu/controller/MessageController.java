package com.bcu.controller;

import com.bcu.mapper.MessageMapper;
import com.bcu.pojo.Message;
import com.bcu.pojo.User;
import com.bcu.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;

@Controller
public class MessageController {

    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private UserService userService;

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
        HashMap<String, String> rsMap = new HashMap<>();

        Message m=messageMapper.selectByMessageReceiverId(id);
        if (m != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            User u = userService.findByUserId(m.getMessageInitiatorId());


            rsMap.put("nickName", u.getUserNickName());
            rsMap.put("avatarUrl", u.getUserImage());
            rsMap.put("time", sdf.format(m.getMessageCreateTime()));
            out.println(JSONObject.fromObject(rsMap));
        }else
        {
            out.println("null");
        }


    }
}

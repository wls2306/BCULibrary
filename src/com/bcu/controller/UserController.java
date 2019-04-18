package com.bcu.controller;

import com.bcu.mapper.UserMapper;
import com.bcu.pojo.User;
import com.bcu.service.UserService;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @RequestMapping(value = "/insertInfo",method = {RequestMethod.GET,RequestMethod.POST})
    public void insertUserInfo(HttpServletRequest req, HttpServletResponse resp) throws Exception
    {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=UTF8");
        String userName=req.getParameter("userName");
        String userId=req.getParameter("userId");
        String userPart=req.getParameter("userPart");
        String userOpenId=req.getParameter("userOpenId");
        String userNickName=req.getParameter("userNickName");
        String userImage=req.getParameter("userImage");
        User u = new User();
        u.setUserId(userId);
        u.setUserName(userName);
        u.setUserOpenId(userOpenId);
        u.setUserPart(userPart);
        u.setUserStudyTime(0);
        u.setUserStatus(1);
        u.setUserNickName(userNickName);
        u.setUserImage(userImage);
        boolean rs = userService.insertUser(u);
        PrintWriter out=resp.getWriter();
        out.println(JSONObject.fromObject(rs));

    }

    @RequestMapping(value = "/getInfoByOpenId",method = {RequestMethod.GET,RequestMethod.POST})
    public void getUserInfoByOpenId(HttpServletRequest req,HttpServletResponse resp) throws Exception
    {
       req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=UTF8");
        PrintWriter out=resp.getWriter();
        String openId=req.getParameter("openid");

        out.println(JSONObject.fromObject( userService.findUserByOpenId(openId)));

    }


    @RequestMapping(value = "/getUserRanking",method = {RequestMethod.GET,RequestMethod.POST})
    public void getUserRanking(HttpServletRequest req,HttpServletResponse resp) throws Exception
    {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=UTF8");

        PrintWriter out=resp.getWriter();
        String openid=req.getParameter("openid");
        HashMap<String,String> rank=new HashMap<>();
        rank.put("rank",userService.getUserRankByOpenId(openid)+"");
        out.println(JSONObject.fromObject(rank));

    }





}

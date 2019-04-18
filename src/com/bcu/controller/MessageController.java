package com.bcu.controller;

import com.bcu.mapper.MessageMapper;
import com.bcu.mapper.SeatMapper;
import com.bcu.pojo.Message;
import com.bcu.pojo.User;
import com.bcu.service.SeatService;
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
    @Autowired
    private SeatService seatService;

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
        String seatId=req.getParameter("seatId");
        HashMap<String, String> rsMap = new HashMap<>();

        Message m=messageMapper.selectByMessageReceiverId(id);
        if (m != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            User u = userService.findByUserId(m.getMessageInitiatorId());

            rsMap.put("nickName", u.getUserNickName());
            rsMap.put("avatarUrl", u.getUserImage());
            rsMap.put("time", sdf.format(m.getMessageCreateTime()));
            rsMap.put("result","1");
      //      out.println(JSONObject.fromObject(rsMap));
        }else if (Integer.valueOf(seatId)!=0)
        {
           if (seatService.seatIsEnable(Integer.parseInt(seatId))) {
               rsMap.put("result", "false");
               /*rsMap.put("message", "原主人已回归");*/
           }else{
               rsMap.put("result", "true");
//               rsMap.put("message", "原主人已放弃该座位，请重新扫码入座");
           }


        }else
        {
            rsMap.put("result", "0");
        }

        out.println(JSONObject.fromObject(rsMap));
    }
}

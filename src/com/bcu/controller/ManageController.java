package com.bcu.controller;

import com.bcu.pojo.Feedback;
import com.bcu.pojo.Study;
import com.bcu.pojo.User;
import com.bcu.service.FeedbackService;
import com.bcu.service.MessageService;
import com.bcu.service.SeatService;
import com.bcu.service.StudyService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class ManageController {
    /**
     *  查询时间范围内的占座信息 √
     *  通过座位号查询使用记录 √
     *  问题反馈√
     *  根据学部获取学部学生总学习时长 ×
     *  输入座位号查询当前座位学习情况 √
     */
@Autowired
private StudyService studyService;
@Autowired
private SeatService seatService;
@Autowired
private FeedbackService feedbackService;

    /**
     * 查询时间范围内的占座信息
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping(value = "/getStudyRecordByTime",method = {RequestMethod.GET,RequestMethod.POST})
    public void GetStudyRecordByTime(HttpServletRequest req, HttpServletResponse resp) throws Exception
    {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=UTF8");

        PrintWriter out=resp.getWriter();

        String timeStart=req.getParameter("timeStart");
        String timeEnd=req.getParameter("timeEnd");

        List<Study> list=studyService.selectStudyRecordByTime(timeStart,timeEnd);

        out.println(JSONArray.fromObject(list));


    }

    /**
     * 通过座位号查询使用记录
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/getStudyRecordBySeatId")
    public void getStudyRecordBySeatId(HttpServletRequest req,HttpServletResponse resp)throws Exception
    {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=UTF8");

        PrintWriter out=resp.getWriter();

        String seatId=req.getParameter("seatId");

        List<Study> list=studyService.selectStudyRecordBySeatId(seatId);

        out.println(JSONArray.fromObject(list));

    }

    @RequestMapping(value = "/selectSeatUser",method = {RequestMethod.GET,RequestMethod.POST})
    public  void selectSeatUser(HttpServletRequest req,HttpServletResponse resp)throws Exception
    {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=UTF8");

        PrintWriter out=resp.getWriter();

        String seatId=req.getParameter("seatId");

        User  u=seatService.selectSeatUser(seatId);

        out.println(JSONObject.fromObject(u));

    }

    @RequestMapping(value = "/insertFeedback",method = {RequestMethod.GET,RequestMethod.POST})
    public void insertFeedack(HttpServletRequest req,HttpServletResponse resp) throws Exception
    {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=UTF8");

        PrintWriter out=resp.getWriter();

        String userId=req.getParameter("userId");
        String content=req.getParameter("content");
        String contact=req.getParameter("contact");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss ");
        String time=sdf.format(new Date());
        Feedback f=new Feedback();
        f.setFbUserId(userId);
        f.setFbContent(content);
        f.setFbContact(contact);
        f.setFbTime(new Date());

        HashMap<String,String> result=new HashMap<>();
        if (feedbackService.insert(f))
            result.put("result",true+"");
        else
            result.put("result",false+"");


        out.println(JSONObject.fromObject(result));


    }



}

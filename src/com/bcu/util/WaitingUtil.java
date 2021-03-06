package com.bcu.util;

import com.bcu.pojo.Message;
import com.bcu.pojo.Seat;
import com.bcu.pojo.Wait;
import com.bcu.service.MessageService;
import com.bcu.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

@Component
public class WaitingUtil {
    public static List<Wait> seatWaitingList=new ArrayList<Wait>();
    @Autowired
    private MessageService messageService;
    @Autowired
    private SeatService seatService;


    private static WaitingUtil waitingUtil;

    @PostConstruct
    public void init()
    {
        waitingUtil=this;
        waitingUtil.messageService=this.messageService;
        waitingUtil.seatService=this.seatService;
    }

    /**
     * 持续扫描等待队列，等待期满后进行退座操作
     */
    public static void scan()
    {
        Date d;
       // Date now=
        Wait w;
        System.out.println("当前共有:"+seatWaitingList.size()+"人正在等待");
        for (int i=0;i<seatWaitingList.size();i++)
        {
            w=(Wait)seatWaitingList.get(i);
            d=w.getEndTime();
            System.out.println("等待队列正在遍历！ 等待座位"+w.getSeatId() +"到期时间"+w.getEndTime());
            if (d.before(new Date()))
                System.out.println("退座结果："+waitingUtil.checkOutWaitingList(Integer.parseInt(w.getSeatId())));
               // waitingUtil.checkOutWaitingList(Integer.parseInt(w.getSeatId()));
        }
    }

    /**
     * 座位被占，进入等待队列
     * @param w
     * @return
     */
    public static boolean checkInWatingList(Wait w)
    {
        System.out.println("waiting list check in --"+w.getSeatId());
        return  seatWaitingList.add(w);
    }

    /**
     * 等待时间到后，原主人仍未回归，座位被释放，退出等待队列
     * @param seatId
     * @return
     */
    public static  boolean   checkOutWaitingList(int seatId)
    {
        for (int i=0;i<seatWaitingList.size();i++)
        {
            System.out.println("开始执行退座操作！退座作为号是"+seatId);
            Wait w=seatWaitingList.get(i);
            if ( Integer.valueOf(w.getSeatId())==seatId)
            {
                /**
                 *  被举报造成的强制退座，学习时长不计
                 */
                if ( waitingUtil.seatService.checkOutSeat(seatId)) { //先在数据库中移除对象 后在队列中移除数据
                    System.out.println("数据库踢出");
                    seatWaitingList.remove(i);
                      waitingUtil.messageService.deletebyMessageSeatId(seatId+"");
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 等待时间未到，原主人回归，座位保留，退出等待队列
     * @return
     */
    public static boolean cancelOutWaitingList(int seatId)
    {
        for (int i=0;i<seatWaitingList.size();i++)
        {
            Wait w=seatWaitingList.get(i);
            System.out.println("finding:"+w.getSeatId());
            if (Integer.valueOf(w.getSeatId())==seatId)
            {
                    System.out.println("find:"+w.getSeatId());
                     seatWaitingList.remove(i);
                     waitingUtil.messageService.deletebyMessageSeatId(seatId+"");
                    return true;
            }
        }
        return false;
    }

    public static Date getFinalTime(int waitingMinute)
    {
        Date now=new Date();
        Calendar rightnow=Calendar.getInstance();
        rightnow.setTime(now);
        rightnow.add(Calendar.MINUTE,waitingMinute);
        Date rs=rightnow.getTime();
        return rs;
    }

   /*public boolean deleteMessage(String seatid)
    {
        return messageService.deletebyMessageSeatId(seatid);
    }*/



}

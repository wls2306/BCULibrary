package com.bcu.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class WaitingTimerTask extends TimerTask {
    @Override
    public void run() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.print( sdf.format(new Date()) +"等待队列 开始扫描！");
        WaitingUtil.scan();
    }
}

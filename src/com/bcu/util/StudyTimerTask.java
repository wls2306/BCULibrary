package com.bcu.util;

import com.bcu.pojo.Study;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class StudyTimerTask extends TimerTask {
    @Override
    public void run() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.print(sdf.format(new Date())+"学习队列 开始扫描！");
        StudyUtil.scan();
    }
}

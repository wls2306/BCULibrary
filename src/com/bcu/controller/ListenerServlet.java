package com.bcu.controller;

import com.bcu.util.StudyTimerTask;
import com.bcu.util.WaitingTimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;
import java.util.Date;
import java.util.Timer;

public class ListenerServlet extends HttpServlet implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Timer timer=new Timer(true);

        timer.schedule(new StudyTimerTask(),new Date(),5 *1000);
        //       一定注意new date（）
        timer.schedule(new WaitingTimerTask(),new Date(),5*1000);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("end");

    }
}

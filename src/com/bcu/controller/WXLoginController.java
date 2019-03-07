package com.bcu.controller;

import net.sf.json.JSONObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class WXLoginController {

    @RequestMapping(value = "/login",method = {RequestMethod.GET,RequestMethod.POST})
    public void dologin(HttpServletRequest req, HttpServletResponse resp) throws Exception
    {
        {
            // TODO Auto-generated method stub
            req.setCharacterEncoding("utf-8");

            String js_code=req.getParameter("js_code");

            String appid="wx53d0f9e697163f5c";
            String secret= "39eca71d60a5520dea97556f8ad28f45";
            String url="https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+secret+"&js_code="+js_code+"&grant_type=authorization_code";
            String result=get(url);
            //System.out.println(url);
            resp.setContentType("text/html;charset=UTF8");
            PrintWriter out = resp.getWriter();
            JSONObject json = JSONObject.fromObject(result);

            out.print(json);
            out.flush();
            out.close();
        }






    }



    public String get(String url){

        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpGet httpget = new HttpGet(url);
        //������
        CloseableHttpResponse response = null;
        String content ="";
        try {
            response = httpclient.execute(httpget);
            if(response.getStatusLine().getStatusCode()==200){
                content = EntityUtils.toString(response.getEntity(),"utf-8");
//                System.out.println(content);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}

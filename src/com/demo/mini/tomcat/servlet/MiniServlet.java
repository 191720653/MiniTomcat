package com.demo.mini.tomcat.servlet;

import com.demo.mini.tomcat.request.MiniRequest;
import com.demo.mini.tomcat.response.MiniResponse;

/**
 * 请求处理
 */
public abstract class MiniServlet {

    public abstract void doGet(MiniRequest request, MiniResponse response);

    public abstract  void doPost(MiniRequest request, MiniResponse response);

    public void service(MiniRequest request, MiniResponse response) {
        if (request.getMethod().equalsIgnoreCase("get")) {
            doGet(request, response);
        } else if (request.getMethod().equalsIgnoreCase("post")) {
            doPost(request, response);
        }
    }

}

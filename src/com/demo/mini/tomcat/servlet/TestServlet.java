package com.demo.mini.tomcat.servlet;

import com.demo.mini.tomcat.request.MiniRequest;
import com.demo.mini.tomcat.response.MiniResponse;
import com.demo.mini.tomcat.servlet.MiniServlet;

import java.io.IOException;

/**
 * 测试servlet
 */
public class TestServlet extends MiniServlet {

    @Override
    public void doGet(MiniRequest request, MiniResponse response) {
        System.out.println("TestServlet do get.......");
        try {
            response.write("test do get over......");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(MiniRequest request, MiniResponse response) {
        System.out.println("TestServlet do post.......");
        try {
            response.write("test do post over......");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

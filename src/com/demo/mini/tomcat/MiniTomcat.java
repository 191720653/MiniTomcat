package com.demo.mini.tomcat;

import com.demo.mini.tomcat.request.MiniRequest;
import com.demo.mini.tomcat.response.MiniResponse;
import com.demo.mini.tomcat.servlet.MiniServlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 简单迷你tomcat
 */
public class MiniTomcat {

    private static int port;

    private static Map<String, String> urlToServletMap;

    public void init() {
        port = MiniTomcatConfig.getPort();
        urlToServletMap = new HashMap<>();
        MiniTomcatConfig.getServletMappingList().forEach(mapping -> {
            urlToServletMap.put(mapping.getUrl(), mapping.getClazz());
            System.out.println("mapping servlet " + mapping.getClazz() + " with url "  + mapping.getUrl() + ".");
        });
    }

    public void start() {
        ServerSocket serverSocket = null;
        init();
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("MiniTomcat started at " + port + ".");
            while (Boolean.TRUE) {
                Socket socket = serverSocket.accept();
                System.out.println("serverSocket accept.");
                InputStream inputStream = socket.getInputStream();
                if (inputStream.available() > 0) {
                    OutputStream outputStream = socket.getOutputStream();

                    System.out.println("new request.");
                    MiniRequest request = new MiniRequest(inputStream);
                    System.out.println("new response.");
                    MiniResponse response = new MiniResponse(outputStream);

                    System.out.println("dispatch.");
                    dispatch(request, response);
                }
                System.out.println("socket close.");
                socket.close();
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void dispatch(MiniRequest request, MiniResponse response) {
        String clazz = urlToServletMap.get(request.getUrl());
        try {
            Class<MiniServlet> servletClass = (Class<MiniServlet>) Class.forName(clazz);
            MiniServlet servlet = servletClass.newInstance();
            servlet.service(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 启动
        MiniTomcat tomcat = new MiniTomcat();
        tomcat.start();
    }

}

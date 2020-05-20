package com.demo.mini.tomcat;

import com.demo.mini.tomcat.servlet.ServletMapping;
import com.demo.mini.tomcat.servlet.TestServlet;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置类
 */
public class MiniTomcatConfig {

    private static int port;

    private static List<ServletMapping> servletMappingList;

    static {
        port = 8080;
        servletMappingList = new ArrayList<>();
        servletMappingList.add(new ServletMapping("test", "/test", TestServlet.class.getName()));
        servletMappingList.add(new ServletMapping("test", "/favicon.ico", TestServlet.class.getName()));
    }

    public static int getPort() {
        return port;
    }

    public static List<ServletMapping> getServletMappingList() {
        return servletMappingList;
    }
}

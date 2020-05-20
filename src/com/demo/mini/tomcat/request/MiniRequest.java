package com.demo.mini.tomcat.request;

import java.io.IOException;
import java.io.InputStream;

/**
 * 请求
 */
public class MiniRequest {

    private String url;

    private String method;

    public MiniRequest(InputStream inputStream) throws IOException {
        // 获取请求内容
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        String requestStr = new String(bytes);

        // 解析请求内容，封装成request
        String head = requestStr.split("\n")[0];
        method = head.split("\\s")[0];
        url = head.split("\\s")[1];

        System.out.println("请求" + this);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "MiniRequest{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}

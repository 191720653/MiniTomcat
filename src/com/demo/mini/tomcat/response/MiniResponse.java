package com.demo.mini.tomcat.response;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 返回
 */
public class MiniResponse {

    private OutputStream outputStream;

    public MiniResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String response) throws IOException {
        StringBuffer buffer = new StringBuffer();
        buffer.append("HTTP/1.1 200 OK\n")
                .append("\r\n")
                .append(response);
        outputStream.write(buffer.toString().getBytes());
        outputStream.close();
    }

}

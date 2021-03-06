package com.futcamp;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;


public class IndexHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpEx) {
        IPageParser pgParser = new PageParser();

        try {
            pgParser.loadTemplate(Path.IndexPage);
        } catch (IOException e) {
            System.out.println("Load template error: " + e.getMessage());
            return;
        }

        try {
            OutputStream os = httpEx.getResponseBody();

            String s = pgParser.buildPage();


            httpEx.sendResponseHeaders(200, 0);
            try (BufferedOutputStream out = new BufferedOutputStream(httpEx.getResponseBody())) {
                try (ByteArrayInputStream bis = new ByteArrayInputStream(s.getBytes())) {
                    byte[] buffer = new byte [512];
                    int count ;
                    while ((count = bis.read(buffer)) != -1) {
                        out.write(buffer, 0, count);
                    }
                }
            }

            os.close();
        } catch (Exception e) {
            System.out.println("Send response error: " + e.getMessage());
        }
    }

}

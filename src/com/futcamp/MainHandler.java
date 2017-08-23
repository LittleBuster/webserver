package com.futcamp;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange ex) throws IOException {
        System.out.println("OK1");
        IPageParser pgParser = new PageParser();

        try {
            pgParser.loadTemplate(Path.MainPage);
        } catch (IOException e) {
            System.out.println("Load template error: " + e.getMessage());
            return;
        }

        try {
            OutputStream os = ex.getResponseBody();
            String s = pgParser.buildPage();

            ex.sendResponseHeaders(200, 0);
            try (BufferedOutputStream out = new BufferedOutputStream(ex.getResponseBody())) {
                try (ByteArrayInputStream bis = new ByteArrayInputStream(s.getBytes())) {
                    byte [] buffer = new byte [512];
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

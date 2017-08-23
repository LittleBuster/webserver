package com.futcamp;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class LoginHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange ex) throws IOException {
        String[] parts = ex.getRequestURI().toString().split("=");
        String answ = "{\"result\": \"ok\", \"login\": \"" + parts[1] + "\"}";

        try {
            ex.sendResponseHeaders(200, answ.length());
            OutputStream os = ex.getResponseBody();
            os.write(answ.getBytes());
            os.close();
        } catch (Exception e) {
            System.out.println("Send response error: " + e.getMessage());
        }
    }
}

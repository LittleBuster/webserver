package com.futcamp;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;


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

        pgParser.setElement("my", "Sergey Denisov 2017");

        try {
            httpEx.sendResponseHeaders(200, pgParser.buildPage().length());
            OutputStream os = httpEx.getResponseBody();
            os.write(pgParser.buildPage().getBytes());
            os.close();
        } catch (Exception e) {
            System.out.println("Send response error: " + e.getMessage());
        }
    }

}

package com.futcamp;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class FileHandler implements HttpHandler {
    private String path_;

    public FileHandler(String path) {
        path_ = path;
    }

    @Override
    public void handle(HttpExchange t) {
        URI uri = t.getRequestURI();

        System.out.println(path_ + uri.getPath());

        try {
            File file = new File(path_ + uri.getPath()).getCanonicalFile();
            if (!file.getPath().startsWith(path_)) {
                String response = "<h1>403 (Forbidden)</h1>\n";
                t.sendResponseHeaders(403, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else if (!file.isFile()) {
                String response = "<h1>404 (Not Found)</h1>\n";
                t.sendResponseHeaders(404, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                t.sendResponseHeaders(200, 0);
                OutputStream os = t.getResponseBody();
                FileInputStream fs = new FileInputStream(file);
                final byte[] buffer = new byte[0x10000];
                int count = 0;
                while ((count = fs.read(buffer)) >= 0) {
                    os.write(buffer, 0, count);
                }
                fs.close();
                os.close();
            }
        } catch (Exception e) {
            System.out.println("Failed to load file: " + e.getMessage());
            System.out.println("Request: " + t.getRequestURI());
        }
    }

}

package com.futcamp;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;


public class WebServer {
    private HttpServer server;

    void start() throws IOException {
        server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new IndexHandler());
        server.createContext("/images", new FileHandler(Path.FilesDir));
        server.createContext("/js", new FileHandler(Path.FilesDir));
        server.createContext("/css", new FileHandler(Path.FilesDir));
        server.createContext("/fonts", new FileHandler(Path.FilesDir));
        server.createContext("/login", new LoginHandler());
        server.createContext("/main", new MainHandler());
        server.start();
    }

}

package com.futcamp;


public class Main {

    public static void main(String[] args) {
        WebServer server = new WebServer();
        try {
            server.start();
        }
        catch (Exception e) {
            System.out.println("Failed to start server: " + e.getMessage());
        }
    }

}

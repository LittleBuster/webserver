package com.futcamp;


import java.io.IOException;

public interface IPageParser {
    void loadTemplate(String filename) throws IOException;
    String getElement(String id);
    void setElement(String id, String value);
    String buildPage();
}

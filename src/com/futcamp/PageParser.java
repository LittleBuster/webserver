package com.futcamp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class PageParser implements IPageParser {
    private Document html;

    public void loadTemplate(String filename) throws IOException {
        File pgFile = new File(filename);
        BufferedReader reader = new BufferedReader(new FileReader(pgFile.getCanonicalFile()));
        StringBuilder page = new StringBuilder();

        while (true) {
            String line = reader.readLine();
            if (line == null)
                break;
            page.append(line);
        }
        html = Jsoup.parse(page.toString());
    }

    public String getElement(String id) {
        Element element = html.body().getElementById(id);
        return element.text();
    }

    public void setElement(String id, String value) {
        Element element = html.body().getElementById(id);
        element.text(value);
    }

    public String buildPage() {
        return html.outerHtml();
    }
}

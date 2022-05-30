package com.thedariusz.pdfreaderdemo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class HtmlJsoupParser implements HtmlParser {

    @Override
    public List<String> getHrefsTextWithPattern(String url, String pattern) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements a = doc.select("a");
        return a.stream()
                .map(Element::text)
                .filter(text -> (text.toLowerCase()).contains(pattern.toLowerCase()))
                .toList();
    }
}

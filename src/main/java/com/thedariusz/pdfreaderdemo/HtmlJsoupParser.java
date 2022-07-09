package com.thedariusz.pdfreaderdemo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class HtmlJsoupParser implements HtmlParser {

    private static final String FILE_EXTENSION = "pdf";
    private static final String SELECTOR = "a";
    public static final List<String> EMPTY = List.of();

    private static final Logger logger = LoggerFactory.getLogger(HtmlJsoupParser.class);
    
    @Override
    public List<String> getPdfFileNameList(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            return doc.select(SELECTOR)
                    .stream()
                    .map(Element::text)
                    .filter(this::hasPdfExtension)
                    .toList();
        } catch (IOException e) {
            logger.warn("No pdf found, returning empty list", e);
            return EMPTY;
        }
    }

    private boolean hasPdfExtension(String text) {
        return text.toLowerCase().contains(FILE_EXTENSION);
    }
}

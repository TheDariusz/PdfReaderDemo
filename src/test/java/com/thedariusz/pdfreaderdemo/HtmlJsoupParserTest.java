package com.thedariusz.pdfreaderdemo;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HtmlJsoupParserTest {
    private static final String CURRENT_ALERTS_IMGW_URL="https://danepubliczne.imgw.pl/data/current/ost_meteo/";
    HtmlJsoupParser htmlJsoupParser = new HtmlJsoupParser();

    @Test
    void shouldReturnNonEmptyList() throws IOException {
        List<String> pdfs = htmlJsoupParser.getTextWithPatternFromHrefs(CURRENT_ALERTS_IMGW_URL, "pdf");
        assertTrue(pdfs.size() > 0);
        pdfs.forEach(System.out::println);
    }


}
package com.thedariusz.pdfreaderdemo;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


class ImgwPdfServiceTest {

    private static final String CURRENT_ALERTS_IMGW_URL = "https://danepubliczne.imgw.pl/data/current/ost_meteo/";
    public static final String PATTERN = "pdf";

    HtmlParser htmlParser = new HtmlJsoupParser();
    PdfReader pdfReader = new PdfBoxReader();

    ImgwPdfService imgwPdfService = new ImgwPdfService(htmlParser, pdfReader);

    @Test
    void shouldReturnNonZeroListOfTextAlerts() throws IOException {
        List<String> actualListOfAlerts = imgwPdfService.fetchTextAlerts();
        assertTrue(actualListOfAlerts.size() > 0);
    }


}
package com.thedariusz.pdfreaderdemo;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


class ImgwPdfServiceTest {

    private static final String CURRENT_ALERTS_IMGW_URL="https://danepubliczne.imgw.pl/data/current/ost_meteo/";
    public static final String PATTERN = "pdf";

    HtmlParser htmlParser = new HtmlJsoupParser();
    PdfReader pdfReader = new PdfBoxReader();

    ImgwPdfService imgwPdfService = new ImgwPdfService(htmlParser, pdfReader);

    @Test
    void shouldReturnNonZeroListOfFilenames() throws IOException {
        List<String> listOfActualAlertFiles = imgwPdfService.getListOfUrlsForActualAlerts(CURRENT_ALERTS_IMGW_URL, PATTERN);
        assertTrue(listOfActualAlertFiles.size()>0);
    }

    @Test
    void shouldReturnNonZeroListOfTextAlerts() throws IOException {
        List<String> actualListOfAlerts = imgwPdfService.getActualListOfAlerts();
        assertTrue(actualListOfAlerts.size()>0);
    }


}
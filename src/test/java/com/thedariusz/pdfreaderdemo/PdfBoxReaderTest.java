package com.thedariusz.pdfreaderdemo;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PdfBoxReaderTest {

    PdfBoxReader reader = new PdfBoxReader();
    private static final String CURRENT_ALERTS_IMGW_URL = "https://danepubliczne.imgw.pl/data/current/ost_meteo/";

    @Test
    void shouldGetAtLeastOneImage() throws IOException {
        String fileName = "src/main/resources/static/pdf/ZPW_STAN_20220601120844709.pdf";
        assertTrue(reader.getImages(fileName).size()>0);
    }

}
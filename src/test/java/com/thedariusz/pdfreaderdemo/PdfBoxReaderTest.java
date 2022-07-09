package com.thedariusz.pdfreaderdemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PdfBoxReaderTest {

    String fileName = "SLW_STAN_20220607205925816.pdf";
    InputStream inputStream;
    private static final String CURRENT_ALERTS_IMGW_URL = "https://danepubliczne.imgw.pl/data/current/ost_meteo/";


    @BeforeEach
    void createInputStream() {
        try {
            inputStream = (new URL(CURRENT_ALERTS_IMGW_URL + fileName)).openConnection().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    PdfBoxReader reader = new PdfBoxReader();

    @Test
    void shouldGetAtLeastOneImage() throws IOException {

        List<BufferedImage> images = reader.getImages(inputStream);
        assertTrue(images.size() > 0);
    }

    @Test
    void shouldReturnTextOfWarning() throws IOException {
        String fileName = "SLW_STAN_20220607205925816.pdf";

        String actual = reader.extractContentAsText(inputStream);
        assertThat(actual).contains("Burze z gradem/1");

    }

}
package com.thedariusz.pdfreaderdemo;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class ImgwPdfService implements PdfReader {
    private static final String CURRENT_ALERTS_IMGW_URL="https://danepubliczne.imgw.pl/data/current/ost_meteo/";
    public static final String PATTERN = "pdf";
    private final HtmlParser htmlJsoupParser;

    public ImgwPdfService(HtmlParser htmlJsoupParser) {
        this.htmlJsoupParser = htmlJsoupParser;
    }

    @Override
    public String getText() throws IOException {
        List<String> listOfActualWarnings = htmlJsoupParser.getTextWithPatternFromHrefs(CURRENT_ALERTS_IMGW_URL, PATTERN);
        return null;
    }

    @Override
    public List<BufferedImage> getImages() {
        return null;
    }
}

package com.thedariusz.pdfreaderdemo;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.net.ssl.HttpsURLConnection;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class ImgwPdfService implements PdfReader {
    private static final String CURRENT_ALERTS_IMGW_URL="https://danepubliczne.imgw.pl/data/current/ost_meteo/";
    public static final String PATTERN = "pdf";
    private final HtmlParser htmlJsoupParser;

    public ImgwPdfService(HtmlParser htmlJsoupParser) {
        this.htmlJsoupParser = htmlJsoupParser;
    }

    @Override
    public String getText(String text) throws IOException {
        List<String> listOfActualWarningsFileNames = htmlJsoupParser.getTextWithPatternFromHrefs(CURRENT_ALERTS_IMGW_URL, PATTERN);
        return null;
    }

    @Override
    public List<BufferedImage> getImages(String fileName) {
        return null;
    }

}

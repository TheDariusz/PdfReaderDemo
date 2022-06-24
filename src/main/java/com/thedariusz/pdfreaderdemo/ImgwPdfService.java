package com.thedariusz.pdfreaderdemo;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ImgwPdfService {
    private static final String ACTUAL_ALERTS_IMGW_URL = "https://danepubliczne.imgw.pl/data/current/ost_meteo/";
    private static final String PATTERN = "pdf";
    private final HtmlParser htmlJsoupParser;
    private final PdfReader pdfBoxReader;

    public ImgwPdfService(HtmlParser htmlJsoupParser, PdfReader pdfReader) {
        this.htmlJsoupParser = htmlJsoupParser;
        this.pdfBoxReader = pdfReader;
    }

    public List<String> getActualListOfAlerts() throws IOException {
        List<String> listOfUrlsForActualAlerts = getListOfUrlsForActualAlerts();
        List<String> textAlerts = new ArrayList<>();
        for (String url : listOfUrlsForActualAlerts) {
            String text = pdfBoxReader.getText(getInputStream(url));
            textAlerts.add(text);
        }
        return textAlerts;
    }


    private List<String> getListOfUrlsForActualAlerts() throws IOException {
        List<String> fileNames = htmlJsoupParser.getFilenamesWithPatternFromBaseurl(ACTUAL_ALERTS_IMGW_URL, PATTERN);
        return fileNames.stream()
                .map(fileName -> ACTUAL_ALERTS_IMGW_URL + fileName)
                .toList();
    }


    private InputStream getInputStream(String pdfUri) throws IOException {
        URL myurl = new URL(pdfUri);
        HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
        return con.getInputStream();
    }


}

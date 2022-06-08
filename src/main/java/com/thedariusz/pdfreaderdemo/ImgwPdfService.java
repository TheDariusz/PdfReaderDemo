package com.thedariusz.pdfreaderdemo;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ImgwPdfService {
    private static final String CURRENT_ALERTS_IMGW_URL="https://danepubliczne.imgw.pl/data/current/ost_meteo/";
    public static final String PATTERN = "pdf";
    private final HtmlParser htmlJsoupParser;
    private final PdfReader pdfReader;

    public ImgwPdfService(HtmlParser htmlJsoupParser, PdfReader pdfReader) {
        this.htmlJsoupParser = htmlJsoupParser;
        this.pdfReader = pdfReader;
    }

    public List<String> getListOfUrlsForActualAlerts(String baseUrl, String pattern) throws IOException {
        List<String> fileNames = htmlJsoupParser.getFilenamesWithPatternFromBaseurl(baseUrl, pattern);
        return fileNames.stream()
                .map(fileName -> CURRENT_ALERTS_IMGW_URL+fileName)
                .toList();
    }

    public List<String> getActualListOfAlerts() throws IOException {
        List<String> listOfUrlsForActualAlerts = getListOfUrlsForActualAlerts(CURRENT_ALERTS_IMGW_URL, PATTERN);
        List<String> alertText = new ArrayList<>();
        for (String url : listOfUrlsForActualAlerts) {
            String text = pdfReader.getText(getInputStream(url));
            alertText.add(text);
        }
        return alertText;
    }

    private InputStream getInputStream(String pdfUri) throws IOException {
        URL myurl = new URL(pdfUri);
        HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
        return con.getInputStream();
    }


}

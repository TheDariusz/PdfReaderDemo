package com.thedariusz.pdfreaderdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ImgwPdfService {
    private static final String ACTUAL_ALERTS_IMGW_URL = "https://danepubliczne.imgw.pl/data/current/ost_meteo/";
    private final HtmlParser htmlJsoupParser;
    private final PdfReader pdfBoxReader;
    
    private static final Logger logger = LoggerFactory.getLogger(ImgwPdfService.class);

    public ImgwPdfService(HtmlParser htmlJsoupParser, PdfReader pdfReader) {
        this.htmlJsoupParser = htmlJsoupParser;
        this.pdfBoxReader = pdfReader;
    }

    public List<String> fetchTextAlerts() {
        List<String> urlList = fetchUrlList();
        List<String> textAlerts = new ArrayList<>();
        for (String url : urlList) {
            try {
                InputStream inputStream = getInputStreamFromUrl(url);
                String alertContent = pdfBoxReader.extractContentAsText(inputStream);
                textAlerts.add(alertContent);
            } catch (IOException e) {
                logger.info("Failed to extract text from url {}", url, e);
            }
        }
        return textAlerts;
    }

    private List<String> fetchUrlList() {
        List<String> fileNames = htmlJsoupParser.getPdfFileNameList(ACTUAL_ALERTS_IMGW_URL);
        return fileNames.stream()
                .map(fileName -> ACTUAL_ALERTS_IMGW_URL + fileName)
                .toList();
    }

    private InputStream getInputStreamFromUrl(String pdfUri) throws IOException {
        URL url = new URL(pdfUri);
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        return urlConnection.getInputStream();
    }


}

package com.thedariusz.pdfreaderdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PdfReaderConfiguration {

    @Bean
    public HtmlParser jsoupHtmlParser() {
        return new HtmlJsoupParser();
    }

    @Bean
    public ImgwPdfService imgwPdfService(HtmlParser htmlJsoupParser, PdfReader pdfBoxReader) {
        return new ImgwPdfService(htmlJsoupParser, pdfBoxReader);
    }

    @Bean
    public PdfReader pdfBoxReader() {
        return new PdfBoxReader();
    }
}

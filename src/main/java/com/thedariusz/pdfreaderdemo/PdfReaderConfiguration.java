package com.thedariusz.pdfreaderdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PdfReaderConfiguration {

    @Bean
    HtmlParser jsoupHtmlParser() {
        return new HtmlJsoupParser();
    }

    @Bean
    PdfReader imgwPdfReader(HtmlParser htmlJsoupParser) {
        return new ImgwPdfService(htmlJsoupParser);
    }
}

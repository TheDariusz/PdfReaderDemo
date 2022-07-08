package com.thedariusz.pdfreaderdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PdfReaderConfiguration {

    @Bean
    public ImgwPdfService imgwPdfService() {
        HtmlJsoupParser htmlJsoupParser = new HtmlJsoupParser();
        PdfBoxReader pdfReader = new PdfBoxReader();
        
        return new ImgwPdfService(htmlJsoupParser, pdfReader);
    }

}

package com.thedariusz.pdfreaderdemo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/alerts")
public class PdfController {

    @GetMapping("/pdf")
    public String getPdfs() {

        return null;
    }
}

package com.thedariusz.pdfreaderdemo.model.example;

import java.util.regex.Pattern;

public record AlertType(String text) implements ExtractableText {
    private static final Pattern ALERT_TYPE_PATTERN = Pattern.compile("([A-Z][a-ząćęłńóśóżź\\s]+)\\/");
    
    public AlertType(String text) {
        this.text = extract(ALERT_TYPE_PATTERN, text);
    }
}

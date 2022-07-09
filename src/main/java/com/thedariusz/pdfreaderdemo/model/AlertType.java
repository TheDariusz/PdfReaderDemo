package com.thedariusz.pdfreaderdemo.model;

import java.util.regex.Pattern;

public record AlertType(String text) implements ExtractableText {
    private static final Pattern ALERT_TYPE_PATTERN = Pattern.compile("([A-Z][a-ząćęłńóśżź\\s]+)\\/");
    
    public AlertType(String text) {
        this.text = extractText(ALERT_TYPE_PATTERN, text);
    }
}

package com.thedariusz.pdfreaderdemo.model.example;

import java.util.regex.Pattern;

public class Degree implements ExtractableNumber {
    private static final Pattern ALERT_DEGREE_PATTERN = Pattern.compile("\\/(\\d)[ |\\n]");

    public final int value;
    
    public Degree(String rawText) {
        this.value = extractNumber(ALERT_DEGREE_PATTERN, rawText);
    }
}

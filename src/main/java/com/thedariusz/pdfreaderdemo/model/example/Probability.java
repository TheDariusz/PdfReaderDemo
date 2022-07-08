package com.thedariusz.pdfreaderdemo.model.example;

import java.util.regex.Pattern;

public class Probability implements ExtractableText {
    private static final Pattern ALERT_PROBABILITY = Pattern.compile("Prawdopodobieństwo (\\d+)%");
    
    public final String value;
    
    public Probability(String text) {
        this.value = extractText(ALERT_PROBABILITY, text);
    }
    
}

package com.thedariusz.pdfreaderdemo.model;

import java.util.regex.Pattern;

public class Description implements ExtractableText {
    private static final Pattern ALERT_DESCRIPTION = Pattern.compile("(?s)(?<=Przebieg )(.*?)(?=Instytut|SMS)");
    
    public final String value;
    
    public Description(String text) {
        this.value = extractText(ALERT_DESCRIPTION, text);
    }
    
}

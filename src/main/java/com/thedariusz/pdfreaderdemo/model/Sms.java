package com.thedariusz.pdfreaderdemo.model;

import java.util.regex.Pattern;

public class Sms implements ExtractableText {
    private static final Pattern ALERT_SMS = Pattern.compile("SMS ([\\S\\s\\n]+)RSO");
    
    public final String value;
    
    public Sms(String text) {
        this.value = extractText(ALERT_SMS, text);
    }
    
}

package com.thedariusz.pdfreaderdemo.model;

import java.util.Optional;
import java.util.regex.Pattern;

public class AlertTextPatterns {

    
    
    public static final String ALERT_DATE = "godz\\. (\\d{2}:\\d{2} dnia \\d+\\.\\d+\\.\\d+)";

    private AlertTextPatterns() {
        throw new IllegalStateException("Utility class");
    }
}

package com.thedariusz.pdfreaderdemo.model;

import java.util.Arrays;
import java.util.regex.Pattern;

public class AlertStatus implements ExtractableText {
    private static final Pattern ALERT_STATUS_PATTERN = Pattern.compile("\\/\\d\\s([A-ZĄĆĘŁŃÓŚŻŹ]+)");

    public enum Status {
        NEW("nowy"), CHANGE("zmiana"), CANCEL("odwołanie");

        public final String value;

        Status(String value) {
            this.value = value;
        }
    }
    
    public final String rawStatus;
    public final Status status;
    
    public AlertStatus(String text) {
        this.rawStatus = extractText(ALERT_STATUS_PATTERN, text);
        this.status = value(rawStatus);
    }

    private static Status value(String status) {
        return Arrays.stream(Status.values())
                .filter(alertStatus -> alertStatus.value.equalsIgnoreCase(status))
                .findFirst()
                .orElse(Status.NEW);
    }
}

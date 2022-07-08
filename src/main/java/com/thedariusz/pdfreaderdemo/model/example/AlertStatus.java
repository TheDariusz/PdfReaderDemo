package com.thedariusz.pdfreaderdemo.model.example;

import java.util.Arrays;
import java.util.regex.Pattern;

public class AlertStatus implements ExtractableText {
    private static final Pattern ALERT_STATUS_PATTERN = Pattern.compile("\\/\\d\\s([A-ZĄĆĘŁŃÓŚÓŻŹ]+)");

    public enum Status {
        NEW("nowy"), CHANGE("zmiana"), CANCEL("odwołanie");

        public final String status;

        Status(String status) {
            this.status = status;
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
                .filter(alertStatus -> alertStatus.status.equals(status))
                .findFirst()
                .orElse(Status.NEW);
    }
}

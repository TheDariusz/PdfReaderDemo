package com.thedariusz.pdfreaderdemo.model;

import java.time.LocalDateTime;
import java.util.Map;

public record MeteoWarning(
        int imgwNumber,
        String type,
        int degree,
        Action action,
        Map<String, Integer> counties,
        LocalDateTime start,
        LocalDateTime stop,
        String probability,
        String description,
        String textSms
) {
    private enum Action {
        NEW, CHANGE, CANCEL
    }
}

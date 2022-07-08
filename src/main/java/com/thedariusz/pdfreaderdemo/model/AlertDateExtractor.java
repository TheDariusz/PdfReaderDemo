package com.thedariusz.pdfreaderdemo.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Pattern;

import static com.thedariusz.pdfreaderdemo.model.AlertTextPatterns.getStringBasedOnSpecificPattern;

public class AlertDateExtractor {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("HH:mm 'dnia' dd.MM.yyyy");
    private static final LocalDateTime DATETIME_NOW = LocalDateTime.now();

    public static List<LocalDateTime> getAlertStartAndStopDate(String searchingText) {
        Pattern pattern = Pattern.compile(AlertTextPatterns.ALERT_DATE);
        return pattern.matcher(searchingText)
                .results()
                .map(matchResult -> matchResult.group(1))
                .filter(AlertDateExtractor::isValidDate)
                .map(textDate -> LocalDateTime.parse(textDate, DATE_FORMATTER))
                .toList();
    }

    public static LocalDateTime getAlertPublishDate(String headerOfActualAlerts) {
        return getStringBasedOnSpecificPattern(headerOfActualAlerts, AlertTextPatterns.ALERT_DATE)
                .filter(AlertDateExtractor::isValidDate)
                .map(textDate -> LocalDateTime.parse(textDate, DATE_FORMATTER))
                .orElse(DATETIME_NOW);
    }

    private static boolean isValidDate(String textDate) {
        try {
            LocalDateTime.parse(textDate, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

}

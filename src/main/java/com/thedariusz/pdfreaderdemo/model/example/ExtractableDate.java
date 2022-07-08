package com.thedariusz.pdfreaderdemo.model.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public interface ExtractableDate {
    LocalDateTime NOW = LocalDateTime.now();
    DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("HH:mm 'dnia' dd.MM.yyyy");
    
    default LocalDateTime extractDate(Pattern pattern, String rawText) {
        return pattern.matcher(rawText)
                .results()
                .map(matchResult -> matchResult.group(1))
                .filter(this::isValidDate)
                .map(textDate -> LocalDateTime.parse(textDate, DATE_FORMATTER))
                .findFirst()
                .orElse(NOW);
    }

    default boolean isValidDate(String textDate) {
        try {
            LocalDateTime.parse(textDate, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
package com.thedariusz.pdfreaderdemo.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

public class AlertTime implements ExtractableDate {
    
    private static final Pattern ALERT_DATE = Pattern.compile("godz\\. (\\d{2}:\\d{2} dnia \\d+\\.\\d+\\.\\d+)");
    private static final LocalDateTime DEFAULT_EMPTY_DATE = LocalDateTime.of(1900, 1, 1, 0, 0);
    
    public final LocalDateTime start;
    public final LocalDateTime stop;

    public AlertTime(String rawText) {
        List<LocalDateTime> dates = extractDates(rawText);
        
        this.start = getStart(dates);
        this.stop = getStop(dates);
    }

    private LocalDateTime getStop(List<LocalDateTime> startAndStopAlertDates) {
        return startAndStopAlertDates.size() < 2 ? DEFAULT_EMPTY_DATE : startAndStopAlertDates.get(1);
    }

    private LocalDateTime getStart(List<LocalDateTime> startAndStopAlertDates) {
        return startAndStopAlertDates.isEmpty() ? DEFAULT_EMPTY_DATE : startAndStopAlertDates.get(0);
    }

    private List<LocalDateTime> extractDates(String searchingText) {
        return ALERT_DATE.matcher(searchingText)
                .results()
                .map(matchResult -> matchResult.group(1))
                .filter(this::isValidDate)
                .map(textDate -> LocalDateTime.parse(textDate, DATE_FORMATTER))
                .toList();
    }

    
}

package com.thedariusz.pdfreaderdemo.model;

import com.thedariusz.pdfreaderdemo.model.example.AlertStatus;
import com.thedariusz.pdfreaderdemo.model.example.AlertType;
import com.thedariusz.pdfreaderdemo.model.example.Degree;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.thedariusz.pdfreaderdemo.model.AlertTextPatterns.ALERT_COUNTIES_ANY_LETTERS_AND_DIGITS_IN_BRACKETS;
import static com.thedariusz.pdfreaderdemo.model.AlertTextPatterns.extractDescription;
import static com.thedariusz.pdfreaderdemo.model.AlertTextPatterns.extractProbability;
import static com.thedariusz.pdfreaderdemo.model.AlertTextPatterns.extractSms;

public record ImgwLocalMeteoAlert(
        String type,
        int degree,
        AlertStatus.Status status,
        Map<String, Integer> counties,
        LocalDateTime start,
        LocalDateTime stop,
        String probability,
        String description,
        String textSms
) {

    public static final LocalDateTime DEFAULT_EMPTY_DATE = LocalDateTime.of(1900, 1, 1, 0, 0);
    
    public static ImgwLocalMeteoAlert fromLocalAlertText(String localAlertText) {
        List<LocalDateTime> startAndStopAlertDates = AlertDateExtractor.getAlertStartAndStopDate(localAlertText);

        AlertType alertType = new AlertType(localAlertText);
        Degree degree = new Degree(localAlertText);
        AlertStatus alertStatus = new AlertStatus(localAlertText);
        
        return new ImgwLocalMeteoAlert(
                alertType.text(),
                degree.value,
                alertStatus.status,
                getCounties(localAlertText),
                getStart(startAndStopAlertDates),
                getStop(startAndStopAlertDates),
                extractProbability(localAlertText),
                extractDescription(localAlertText),
                extractSms(localAlertText)
        );
    }

    private static LocalDateTime getStop(List<LocalDateTime> startAndStopAlertDates) {
        return startAndStopAlertDates.size() < 2 ? DEFAULT_EMPTY_DATE : startAndStopAlertDates.get(1);
    }

    private static LocalDateTime getStart(List<LocalDateTime> startAndStopAlertDates) {
        return startAndStopAlertDates.isEmpty() ? DEFAULT_EMPTY_DATE : startAndStopAlertDates.get(0);
    }

    private static Map<String, Integer> getCounties(String searchingText) {
        Pattern pattern = Pattern.compile(ALERT_COUNTIES_ANY_LETTERS_AND_DIGITS_IN_BRACKETS);
        return pattern.matcher(searchingText)
                .results()
                .collect(Collectors.toMap(matchResult -> matchResult.group(1),
                        ImgwLocalMeteoAlert::convertToInt));
    }

    private static Integer convertToInt(MatchResult matchResult) {
        try {
            return Integer.parseInt(matchResult.group(2));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

}

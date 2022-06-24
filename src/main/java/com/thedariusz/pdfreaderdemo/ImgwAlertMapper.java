package com.thedariusz.pdfreaderdemo;

import com.thedariusz.pdfreaderdemo.model.ImgwLocalMeteoWarning;
import com.thedariusz.pdfreaderdemo.model.ImgwLocalMeteoWarning.AlertStatus;
import com.thedariusz.pdfreaderdemo.model.ImgwMeteoWarning;
import com.thedariusz.pdfreaderdemo.model.Voivodeship;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ImgwAlertMapper {

    public static final int NUMBER_NOT_FOUND = -1;
    private static final String EMPTY_STRING = "";

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm 'dnia' dd.MM.yyyy");
    public ImgwMeteoWarning toModel(String alertText) {
        

        String[] alerts = alertText.split(TextPatterns.SPLIT_TEXT);

        if (alerts.length < 2) {
            throw new IllegalArgumentException("Problem with splitting text from pdf to fetch specific alerts");
        }

        String headerOfActualAlerts = alerts[0];
        Voivodeship voivodeship = Voivodeship.isInString(headerOfActualAlerts)
                .orElse(Voivodeship.UNDEFINED);

        int alertNumber = findSpecificNumber(headerOfActualAlerts, TextPatterns.ALERT_NUMBER_PATTERN)
                .orElse(NUMBER_NOT_FOUND);

        LocalDateTime alertPublishDate = findSpecificPattern(headerOfActualAlerts, TextPatterns.ALERT_DATE_PATTERN)
                .filter(this::isValid)
                .map(textDate -> LocalDateTime.parse(textDate, dateFormatter))
                .orElse(LocalDateTime.now());

        String[] textLocalAlerts = Arrays.copyOfRange(alerts, 1, alerts.length);
        List<ImgwLocalMeteoWarning> imgwLocalMeteoWarnings = new ArrayList<>();

        Arrays.stream(textLocalAlerts)
                .forEach(localAlertText -> {
                    String alertType = findSpecificPattern(localAlertText, TextPatterns.CAPITAL_LETTER_SMALL_LETTERS_SLASH)
                            .orElse("unspecified");

                    int alertDegree = findSpecificNumber(localAlertText, TextPatterns.SLASH_DIGIT_NEWLINE)
                            .orElse(NUMBER_NOT_FOUND);

                    String alertStatusText = findSpecificPattern(localAlertText, TextPatterns.SLASH_DIGIT_SPACE_CAPITAL_LETTERS)
                            .orElse("nowy");
                    AlertStatus alertStatus = AlertStatus.valueOfLabel(alertStatusText.toLowerCase())
                            .orElse(AlertStatus.NEW);

                    Map<String, Integer> countiesWithNumberOfAlert = findCounties(localAlertText, TextPatterns.ANY_LETTERS_AND_DIGITS_IN_BRACKETS);

                    List<LocalDateTime> startAndStopAlertDates = findStartAndStopAlert(localAlertText, TextPatterns.ALERT_DATE_PATTERN);

                    String alertProbability = findSpecificPattern(localAlertText, TextPatterns.PRAWDOPODOBIENSTWO_WITH_DIGITS_AND_PERCENTAGE)
                            .orElse(EMPTY_STRING);

                    String alertDescription = findSpecificPattern(localAlertText, TextPatterns.PRZEBIEG_ANY_TEXT_ENDED_WITH_SMS)
                            .orElse(EMPTY_STRING);

                    String alertSms = findSpecificPattern(localAlertText, TextPatterns.SMS_ANY_TEXT_ENDED_WITH_RSO)
                            .orElse(EMPTY_STRING);

                    imgwLocalMeteoWarnings.add(
                            new ImgwLocalMeteoWarning(
                                    alertType,
                                    alertDegree,
                                    alertStatus,
                                    countiesWithNumberOfAlert,
                                    startAndStopAlertDates.get(0),
                                    startAndStopAlertDates.get(1),
                                    alertProbability,
                                    alertDescription,
                                    alertSms
                            )
                    );
                });

        return new ImgwMeteoWarning(
                alertNumber,
                voivodeship,
                alertPublishDate,
                imgwLocalMeteoWarnings
        );
    }

    private boolean isValid(String textDate) {
        try {
            LocalDateTime.parse(textDate, dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    private Optional<String> findSpecificPattern(String searchingText, String stringPattern) {
        Pattern pattern = Pattern.compile(stringPattern);
        return pattern.matcher(searchingText)
                .results()
                .map(matchResult -> matchResult.group(1))
                .findFirst();
    }

    private OptionalInt findSpecificNumber(String headerOfActualAlerts, String stringPattern) {
        Pattern pattern = Pattern.compile(stringPattern);
        return pattern.matcher(headerOfActualAlerts)
                .results()
                .map(matchResult -> matchResult.group(1))
                .mapToInt(Integer::parseInt)
                .findFirst();
    }

    private Map<String, Integer> findCounties(String searchingText, String stringPattern) {
        Pattern pattern = Pattern.compile(stringPattern);
        return pattern.matcher(searchingText)
                .results()
                .collect(Collectors.toMap(matchResult -> matchResult.group(1),
                        ImgwAlertMapper::convertToInt));
    }

    private static Integer convertToInt(MatchResult matchResult) {
        try {
            return Integer.parseInt(matchResult.group(2));
        } catch (NumberFormatException e) {
            return NUMBER_NOT_FOUND;
        }
    }

    private List<LocalDateTime> findStartAndStopAlert(String searchingText, String stringPattern) {
        Pattern pattern = Pattern.compile(stringPattern);
        return pattern.matcher(searchingText)
                .results()
                .map(matchResult -> matchResult.group(1))
                .filter(this::isValid)
                .map(textDate -> LocalDateTime.parse(textDate, dateFormatter))
                .toList();
    }
}

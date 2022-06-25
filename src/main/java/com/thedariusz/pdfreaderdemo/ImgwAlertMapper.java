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
    public static final LocalDateTime DEFAULT_DATE = LocalDateTime.of(1900, 1, 1, 0, 0);
    public static final String UNSPECIFIED = "unspecified";
    public static final LocalDateTime DATETIME_NOW = LocalDateTime.now();

    public ImgwMeteoWarning toModel(String alertText) {

        String[] alerts = alertText.split(TextPatterns.SPLIT_TEXT);

        if (alerts.length < 2) {
            throw new IllegalArgumentException("Problem with splitting text from pdf to fetch specific alerts");
        }

        String headerOfActualAlerts = alerts[0];
        Voivodeship voivodeship = getVoivodeship(headerOfActualAlerts);
        int alertNumber = getAlertNumber(headerOfActualAlerts);
        LocalDateTime alertPublishDate = getAlertPublishDate(headerOfActualAlerts);

        String[] textLocalAlerts = Arrays.copyOfRange(alerts, 1, alerts.length);
        List<ImgwLocalMeteoWarning> imgwLocalMeteoWarnings = new ArrayList<>();
        Arrays.stream(textLocalAlerts)
                .forEach(localAlertText -> {
                    List<LocalDateTime> startAndStopAlertDates = getAlertStartAndStopDate(localAlertText);
                    imgwLocalMeteoWarnings.add(
                            new ImgwLocalMeteoWarning(
                                    getAlertType(localAlertText),
                                    getAlertDegree(localAlertText),
                                    getAlertStatus(localAlertText),
                                    getCounties(localAlertText),
                                    startAndStopAlertDates.isEmpty() ? DEFAULT_DATE : startAndStopAlertDates.get(0),
                                    startAndStopAlertDates.size() < 2 ? DEFAULT_DATE : startAndStopAlertDates.get(1),
                                    getAlertProbability(localAlertText),
                                    getAlertDescription(localAlertText),
                                    getAlertSms(localAlertText)
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

    private String getAlertSms(String localAlertText) {
        return findSpecificPattern(localAlertText, TextPatterns.SMS_ANY_TEXT_ENDED_WITH_RSO)
                .orElse(EMPTY_STRING);
    }

    private String getAlertDescription(String localAlertText) {
        return findSpecificPattern(localAlertText, TextPatterns.PRZEBIEG_ANY_TEXT_ENDED_WITH_SMS)
                .orElse(EMPTY_STRING);
    }

    private String getAlertProbability(String localAlertText) {
        return findSpecificPattern(localAlertText, TextPatterns.PRAWDOPODOBIENSTWO_WITH_DIGITS_AND_PERCENTAGE)
                .orElse(EMPTY_STRING);
    }

    private AlertStatus getAlertStatus(String localAlertText) {
        String alertStatusText = findSpecificPattern(localAlertText, TextPatterns.SLASH_DIGIT_SPACE_CAPITAL_LETTERS)
                .orElse("nowy");
        return AlertStatus.valueOfLabel(alertStatusText.toLowerCase())
                .orElse(AlertStatus.NEW);
    }

    private int getAlertDegree(String localAlertText) {
        return findSpecificNumber(localAlertText, TextPatterns.SLASH_DIGIT_NEWLINE)
                .orElse(NUMBER_NOT_FOUND);
    }

    private String getAlertType(String localAlertText) {
        return findSpecificPattern(localAlertText, TextPatterns.CAPITAL_LETTER_SMALL_LETTERS_SLASH)
                .orElse(UNSPECIFIED);
    }

    private LocalDateTime getAlertPublishDate(String headerOfActualAlerts) {
        return findSpecificPattern(headerOfActualAlerts, TextPatterns.ALERT_DATE_PATTERN)
                .filter(this::isValidDate)
                .map(textDate -> LocalDateTime.parse(textDate, dateFormatter))
                .orElse(DATETIME_NOW);
    }

    private int getAlertNumber(String headerOfActualAlerts) {
        return findSpecificNumber(headerOfActualAlerts, TextPatterns.ALERT_NUMBER_PATTERN)
                .orElse(NUMBER_NOT_FOUND);
    }

    private Voivodeship getVoivodeship(String headerOfActualAlerts) {
        return Voivodeship.isInString(headerOfActualAlerts)
                .orElse(Voivodeship.UNDEFINED);
    }

    private boolean isValidDate(String textDate) {
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

    private Map<String, Integer> getCounties(String searchingText) {
        Pattern pattern = Pattern.compile(TextPatterns.ANY_LETTERS_AND_DIGITS_IN_BRACKETS);
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

    private List<LocalDateTime> getAlertStartAndStopDate(String searchingText) {
        Pattern pattern = Pattern.compile(TextPatterns.ALERT_DATE_PATTERN);
        return pattern.matcher(searchingText)
                .results()
                .map(matchResult -> matchResult.group(1))
                .filter(this::isValidDate)
                .map(textDate -> LocalDateTime.parse(textDate, dateFormatter))
                .toList();
    }
}

package com.thedariusz.pdfreaderdemo.model;

import java.util.Optional;
import java.util.regex.Pattern;

public class AlertTextPatterns {

    public static final String ALERT_TYPE_SPLITTER = "Zjawisko/Stopień zagrożenia ";
    public static final String ALERT_NUMBER = "NR (\\d+)";
    public static final String ALERT_DATE = "godz\\. (\\d{2}:\\d{2} dnia \\d+\\.\\d+\\.\\d+)";
    public static final String ALERT_TYPE_CAPITAL_LETTER_SMALL_LETTERS_SLASH = "([A-Z][a-ząćęłńóśóżź\\s]+)\\/";
    public static final String ALERT_DEGREE_SLASH_DIGIT_NEWLINE = "\\/(\\d)[ |\\n]";
    public static final String ALERT_STATUS_SLASH_DIGIT_SPACE_CAPITAL_LETTERS = "\\/\\d\\s([A-ZĄĆĘŁŃÓŚÓŻŹ]+)";
    public static final String ALERT_COUNTIES_ANY_LETTERS_AND_DIGITS_IN_BRACKETS = "([A-ZĄĆĘŁŃÓŚÓŻŹa-ząćęłńóśóżź][A-ZĄĆĘŁŃÓŚÓŻŹa-ząćęłńóśóżź \\-]+)\\((\\d+)\\)";
    public static final String ALERT_PROBABILITY_WORD_WITH_DIGITS_AND_PERCENTAGE = "Prawdopodobieństwo (\\d+)%";
    public static final String ALERT_BODY_PRZEBIEG_ANY_TEXT_ENDED_WITH_SMS = "(?s)(?<=Przebieg )(.*?)(?=Instytut|SMS)";
    public static final String ALERT_SMS_ANY_TEXT_ENDED_WITH_RSO = "SMS ([\\S\\s\\n]+)RSO";

    private static final int NUMBER_NOT_FOUND = -1;
    private static final String EMPTY_STRING = "";
    private static final String UNSPECIFIED = "unspecified";

    private AlertTextPatterns() {
        throw new IllegalStateException("Utility class");
    }

    public static Optional<String> getStringBasedOnSpecificPattern(String searchingText, String stringPattern) {
        Pattern pattern = Pattern.compile(stringPattern);
        return pattern.matcher(searchingText)
                .results()
                .map(matchResult -> matchResult.group(1))
                .findFirst();
    }
    
    public static int getNumberBasedOdSpecificPattern(String headerOfActualAlerts, String stringPattern) {
        Pattern pattern = Pattern.compile(stringPattern);
        return pattern.matcher(headerOfActualAlerts)
                .results()
                .map(matchResult -> matchResult.group(1))
                .mapToInt(Integer::parseInt)
                .findFirst()
                .orElse(NUMBER_NOT_FOUND);
    }


    public static String extractSms(String localAlertText) {
        return getStringBasedOnSpecificPattern(localAlertText, ALERT_SMS_ANY_TEXT_ENDED_WITH_RSO)
                .orElse(EMPTY_STRING);
    }

    public static String extractDescription(String localAlertText) {
        return getStringBasedOnSpecificPattern(localAlertText, ALERT_BODY_PRZEBIEG_ANY_TEXT_ENDED_WITH_SMS)
                .orElse(EMPTY_STRING);
    }

    public static String extractProbability(String localAlertText) {
        return getStringBasedOnSpecificPattern(localAlertText, ALERT_PROBABILITY_WORD_WITH_DIGITS_AND_PERCENTAGE)
                .orElse(EMPTY_STRING);
    }

    public static int extractDegree(String localAlertText) {
        return getNumberBasedOdSpecificPattern(localAlertText, ALERT_DEGREE_SLASH_DIGIT_NEWLINE);
    }

    public static String extractType(String localAlertText) {
        return getStringBasedOnSpecificPattern(localAlertText, ALERT_TYPE_CAPITAL_LETTER_SMALL_LETTERS_SLASH)
                .orElse(UNSPECIFIED);
    }

    public static int extractNumber(String headerOfActualAlerts) {
        return getNumberBasedOdSpecificPattern(headerOfActualAlerts, ALERT_NUMBER);
    }
}

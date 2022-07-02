package com.thedariusz.pdfreaderdemo;

public class TextPatterns {

    public static final String PDF_TEXT_ALERTS_SPLITTER = "Zjawisko/Stopień zagrożenia ";
    public static final String ALERT_NUMBER = "NR (\\d+)";
    public static final String ALERT_DATE = "godz\\. (\\d{2}:\\d{2} dnia \\d+\\.\\d+\\.\\d+)";
    public static final String ALERT_TYPE_CAPITAL_LETTER_SMALL_LETTERS_SLASH = "([A-Z][a-ząćęłńóśóżź\\s]+)\\/";
    public static final String ALERT_DEGREE_SLASH_DIGIT_NEWLINE = "\\/(\\d)[ |\\n]";
    public static final String ALERT_STATUS_SLASH_DIGIT_SPACE_CAPITAL_LETTERS = "\\/\\d\\s([A-ZĄĆĘŁŃÓŚÓŻŹ]+)";
    public static final String ALERT_COUNTIES_ANY_LETTERS_AND_DIGITS_IN_BRACKETS = "([A-ZĄĆĘŁŃÓŚÓŻŹa-ząćęłńóśóżź][A-ZĄĆĘŁŃÓŚÓŻŹa-ząćęłńóśóżź \\-]+)\\((\\d+)\\)";
    public static final String ALERT_PROBABILITY_WORD_WITH_DIGITS_AND_PERCENTAGE = "Prawdopodobieństwo (\\d+)%";
    public static final String ALERT_BODY_PRZEBIEG_ANY_TEXT_ENDED_WITH_SMS = "Przebieg ([\\S\\s\\n]+)SMS";
    public static final String ALERT_SMS_ANY_TEXT_ENDED_WITH_RSO = "SMS ([\\S\\s\\n]+)RSO";

    private TextPatterns() {
        throw new IllegalStateException("Utility class");
    }
}

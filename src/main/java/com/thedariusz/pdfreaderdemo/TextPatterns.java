package com.thedariusz.pdfreaderdemo;

public class TextPatterns {

    public static final String SPLIT_TEXT = "Zjawisko/Stopień zagrożenia ";
    public static final String ALERT_NUMBER_PATTERN = "NR (\\d+)";
    public static final String ALERT_DATE_PATTERN = "godz\\. (\\d{2}:\\d{2} dnia \\d+\\.\\d+\\.\\d+)";
    public static final String CAPITAL_LETTER_SMALL_LETTERS_SLASH = "([A-Z][a-ząćęłńóśóżź\\s]+)\\/";
    public static final String SLASH_DIGIT_NEWLINE = "\\/(\\d)[ |\\n]";
    public static final String SLASH_DIGIT_SPACE_CAPITAL_LETTERS = "\\/\\d\\s([A-Z]+)";
    public static final String ANY_LETTERS_AND_DIGITS_IN_BRACKETS = "([A-ZĄĆĘŁŃÓŚÓŻŹa-ząćęłńóśóżź][A-ZĄĆĘŁŃÓŚÓŻŹa-ząćęłńóśóżź \\-]+)\\((\\d+)\\)";
    public static final String PRAWDOPODOBIENSTWO_WITH_DIGITS_AND_PERCENTAGE = "Prawdopodobieństwo (\\d+)%";
    public static final String PRZEBIEG_ANY_TEXT_ENDED_WITH_SMS = "Przebieg ([\\S\\s\\n]+)SMS";
    public static final String SMS_ANY_TEXT_ENDED_WITH_RSO = "SMS ([\\S\\s\\n]+)RSO";

    private TextPatterns() {
        throw new IllegalStateException("Utility class");
    }
}

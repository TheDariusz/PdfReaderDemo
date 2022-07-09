package com.thedariusz.pdfreaderdemo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


public class MeteoAlert implements ExtractableText, ExtractableNumber, ExtractableDate {

    private static final Pattern ALERT_NUMBER = Pattern.compile("NR (\\d+)");
    private static final String ALERT_TYPE_SPLITTER = "Zjawisko/Stopień zagrożenia ";
    private static final Pattern ALERT_DATE = Pattern.compile("godz\\. (\\d{2}:\\d{2} dnia \\d+\\.\\d+\\.\\d+)");

    public final int number;
    public final Voivodeship voivodeship;
    public final LocalDateTime published;
    public final List<LocalAlert> localMeteoWarnings;

    public MeteoAlert(String text) {
        String[] pdfAlertSections = text.split(ALERT_TYPE_SPLITTER, Pattern.CANON_EQ);
        if (pdfAlertSections.length < 2) {
            throw new IllegalArgumentException("Problem with splitting text from pdf to fetch specific alerts");
        }

        String headerOfLocalAlerts = pdfAlertSections[0];

        this.number = extractNumber(ALERT_NUMBER, headerOfLocalAlerts);
        this.voivodeship = Voivodeship.fromText(headerOfLocalAlerts);
        this.published = extractDate(ALERT_DATE, headerOfLocalAlerts);
        this.localMeteoWarnings = createLocalAlerts(pdfAlertSections);
    }

    private List<LocalAlert> createLocalAlerts(String[] pdfAlertSections) {
        String[] textBodyOfLocalAlerts = Arrays.copyOfRange(pdfAlertSections, 1, pdfAlertSections.length);
        List<LocalAlert> mappedLocalAlerts = new ArrayList<>();
        Arrays.stream(textBodyOfLocalAlerts)
                .forEach(localAlertText -> {
                    LocalAlert localMeteoAlert = LocalAlert.fromLocalAlertText(localAlertText);
                    mappedLocalAlerts.add(localMeteoAlert);
                });
        
        return mappedLocalAlerts;
    }

}

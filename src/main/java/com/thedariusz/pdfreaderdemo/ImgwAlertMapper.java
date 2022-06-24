package com.thedariusz.pdfreaderdemo;

import com.thedariusz.pdfreaderdemo.model.ImgwMeteoWarning;
import com.thedariusz.pdfreaderdemo.model.Voivodeship;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.regex.Pattern;

public class ImgwAlertMapper {

    public static final String SPLIT_TEXT = "Zjawisko/Stopień zagrożenia ";
    public static final String ALERT_NUMBER_PATTERN = "NR (\\d+)";
    public static final String ALERT_DATE_PATTERN = "godz\\. (\\d{2}:\\d{2} dnia \\d+\\.\\d+\\.\\d+)";
    public static final int NUMBER_NOT_FIND = -1;

    public ImgwMeteoWarning toModel(String alertText) {

        String[] alerts = alertText.split(SPLIT_TEXT);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm 'dnia' dd.MM.yyyy");

        if (alerts.length < 2) {
            throw new IllegalArgumentException("Problem with splitting text from pdf to fetch specific alerts");
        }

        String headerOfActualAlerts = alerts[0];
        Voivodeship voivodeship = Voivodeship.isInString(headerOfActualAlerts)
                .orElse(Voivodeship.UNDEFINED);

        int alertNumber = findAlertNumber(headerOfActualAlerts).orElse(NUMBER_NOT_FIND);
        LocalDateTime alertDateTime = findAlertDate(headerOfActualAlerts)
                .filter(textDate -> isValid(textDate, formatter))
                .map(textDate -> LocalDateTime.parse(textDate, formatter))
                .orElse(LocalDateTime.now());

        String[] textLocalAlerts = Arrays.copyOfRange(alerts, 1, alerts.length);



        return null;
    }

    private boolean isValid(String textDate, DateTimeFormatter formatter) {
        try {
            LocalDateTime.parse(textDate, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    private Optional<String> findAlertDate(String headerOfActualAlerts) {
        Pattern p2 = Pattern.compile(ALERT_DATE_PATTERN);
        return p2.matcher(headerOfActualAlerts)
                .results()
                .map(matchResult -> matchResult.group(1))
                .findFirst();
    }

    private OptionalInt findAlertNumber(String headerOfActualAlerts) {
        Pattern p1 = Pattern.compile(ALERT_NUMBER_PATTERN);
        return p1.matcher(headerOfActualAlerts)
                .results()
                .map(matchResult -> matchResult.group(1))
                .mapToInt(Integer::parseInt)
                .findFirst();
    }
}

package com.thedariusz.pdfreaderdemo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.thedariusz.pdfreaderdemo.model.AlertTextPatterns.extractNumber;
import static com.thedariusz.pdfreaderdemo.model.AlertDateExtractor.getAlertPublishDate;

public record ImgwMeteoAlert(
        int number,
        Voivodeship voivodeship,
        LocalDateTime published,
        List<ImgwLocalMeteoAlert> localMeteoWarnings
) {
    
    public static ImgwMeteoAlert fromText(String text) {
        String[] pdfAlertSections = text.split(AlertTextPatterns.ALERT_TYPE_SPLITTER);
        if (pdfAlertSections.length < 2) {
            throw new IllegalArgumentException("Problem with splitting text from pdf to fetch specific alerts");
        }

        String headerOfLocalAlerts = pdfAlertSections[0];

        String[] textBodyOfLocalAlerts = Arrays.copyOfRange(pdfAlertSections, 1, pdfAlertSections.length);
        List<ImgwLocalMeteoAlert> mappedImgwLocalMeteoAlerts = new ArrayList<>();
        Arrays.stream(textBodyOfLocalAlerts)
                .forEach(localAlertText -> {
                    ImgwLocalMeteoAlert localMeteoAlert = ImgwLocalMeteoAlert.fromLocalAlertText(localAlertText);
                    mappedImgwLocalMeteoAlerts.add(localMeteoAlert);
                });

        return new ImgwMeteoAlert(
                extractNumber(headerOfLocalAlerts),
                Voivodeship.fromText(headerOfLocalAlerts),
                getAlertPublishDate(headerOfLocalAlerts),
                mappedImgwLocalMeteoAlerts
        );
    }

}

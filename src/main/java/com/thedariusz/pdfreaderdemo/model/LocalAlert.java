package com.thedariusz.pdfreaderdemo.model;

import java.time.LocalDateTime;
import java.util.Map;

public record LocalAlert(
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

    public LocalAlert(AlertType alertType, 
                      Degree degree, 
                      AlertStatus alertStatus, 
                      Counties counties, 
                      AlertTime time,
                      Probability probability, 
                      Description description, 
                      Sms sms) {
        this(
                alertType.text(), 
                degree.value, 
                alertStatus.status,
                counties.value,
                time.start,
                time.stop,
                probability.value,
                description.value,
                sms.value
        );
    }

    public static LocalAlert fromLocalAlertText(String localAlertText) {
        return new LocalAlert(
                new AlertType(localAlertText),
                new Degree(localAlertText),
                new AlertStatus(localAlertText),
                new Counties(localAlertText),
                new AlertTime(localAlertText),
                new Probability(localAlertText),
                new Description(localAlertText),
                new Sms(localAlertText)
        );
    }
    

}

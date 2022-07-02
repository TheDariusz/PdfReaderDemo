package com.thedariusz.pdfreaderdemo.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public record ImgwLocalMeteoAlert(
        String type,
        int degree,
        AlertStatus alertStatus,
        Map<String, Integer> counties,
        LocalDateTime start,
        LocalDateTime stop,
        String probability,
        String description,
        String textSms
) {
    public enum AlertStatus {
        NEW("nowy"), CHANGE("zmiana"), CANCEL("odwołanie");

        public final String status;

        AlertStatus(String status) {
            this.status = status;
        }

        public static Optional<AlertStatus> valueOfLabel(String status) {
            return Arrays.stream(values())
                    .filter(alertStatus -> alertStatus.status.equals(status))
                    .findFirst();
        }
    }
}

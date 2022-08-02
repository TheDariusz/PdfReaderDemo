package com.thedariusz.pdfreaderdemo.repository;

import com.thedariusz.pdfreaderdemo.repository.entity.AlertEntity;
import com.thedariusz.pdfreaderdemo.repository.entity.VoivodeshipEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AlertEntityDto implements Serializable {
    private final int alertNumber;
    private final LocalDateTime publishedDate;
    private final VoivodeshipEntity voivodeship;

    public AlertEntityDto(int alertNumber, LocalDateTime publishedDate, VoivodeshipEntity voivodeship) {
        this.alertNumber = alertNumber;
        this.publishedDate = publishedDate;
        this.voivodeship = voivodeship;
    }

    public AlertEntity toEntity() {
        return new AlertEntity(
                alertNumber,
                publishedDate,
                voivodeship);
    }
}

package com.thedariusz.pdfreaderdemo.repository;

import com.thedariusz.pdfreaderdemo.model.MeteoAlert;
import com.thedariusz.pdfreaderdemo.repository.entity.AlertEntity;
import com.thedariusz.pdfreaderdemo.repository.entity.VoivodeshipEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AlertEntityDto implements Serializable {
    private int alertNumber;
    private LocalDateTime publishedDate;
    private VoivodeshipEntityDto voivodeship;

    public AlertEntityDto(int alertNumber, LocalDateTime publishedDate, VoivodeshipEntity toEntity) {
        this.alertNumber = alertNumber;
        this.publishedDate = publishedDate;
        this.voivodeship = new VoivodeshipEntityDto(toEntity.getCode(), toEntity.getName());
    }

    public int getAlertNumber() {
        return alertNumber;
    }

    public void setAlertNumber(int alertNumber) {
        this.alertNumber = alertNumber;
    }

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public VoivodeshipEntityDto getVoivodeship() {
        return voivodeship;
    }

    public void setVoivodeship(VoivodeshipEntityDto voivodeship) {
        this.voivodeship = voivodeship;
    }

    public static class VoivodeshipEntityDto implements Serializable {
        private String name;
        private String code;

        public String getName() {
            return name;
        }

        public VoivodeshipEntityDto(String name, String code) {
            this.name = name;
            this.code = code;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public VoivodeshipEntity toEntity() {
            return new VoivodeshipEntity(code, name);
        }
    }

    public AlertEntity toEntity() {
        return new AlertEntity(alertNumber, publishedDate, voivodeship.toEntity());
    }
}

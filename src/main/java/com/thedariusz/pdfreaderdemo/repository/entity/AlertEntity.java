package com.thedariusz.pdfreaderdemo.repository.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "meteo_alert")
public class AlertEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "alert_number")
    private int alertNumber;

    @Column(name = "published_at")
    private LocalDateTime publishedDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "voivodeship_id", referencedColumnName = "id")
    private VoivodeshipEntity voivodeship;

    public VoivodeshipEntity getVoivodeship() {
        return voivodeship;
    }

    public int getAlertNumber() {
        return alertNumber;
    }

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public void setAlertNumber(int alertNumber) {
        this.alertNumber = alertNumber;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setVoivodeship(VoivodeshipEntity voivodeship) {
        this.voivodeship = voivodeship;
    }
}
